package app.carburo.utils.utils.network;

import app.carburo.utils.spainMitmaHTTP.shared.exceptions.SpainMitmaAPIClientException;
import app.carburo.utils.utils.log.LoggerService;
import app.carburo.utils.utils.properties.PropertyLoader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.time.Duration;

/**
 * Clase OwnHttpClient
 * ----------------
 * Cliente HTTP basado en {@link HttpClient} para realizar peticiones GET y obtener respuestas JSON.
 *
 * <p>Se utiliza en la aplicación para realizar llamadas a servicios REST externos
 * (por ejemplo, los endpoints del Ministerio para la Transición Ecológica).</p>
 */
public class OwnHttpClient {


	// ---------------------------------------------------------------------------------------------
	// Constantes
	// ---------------------------------------------------------------------------------------------

	/**
	 * Tiempo máximo de espera para conexión y lectura (en segundos).
	 */
	private static final int DEFAULT_TIMEOUT_SEGUNDOS = 20;

	/**
	 * Nombre de la propiedad para configurar timeout HTTP en segundos
	 */
	private static final String PROP_TIMEOUT_SEGUNDOS = "httpCliente.request.timeOutSegundos";

	/**
	 * Cabecera estándar utilizada en todas las peticiones HTTP.
	 */
	private static final String CABECERA_ACCEPT = "Accept";

	/**
	 * Valor de la cabecera Accept para indicar que se espera una respuesta JSON.
	 */
	private static final String TIPO_JSON = "application/json";

	// Datos del certificado ssl
	private static final String PROP_CERT_ENABLED = "ssl.cert.enabled";
	private static final String PROP_CERT_FILE = "ssl.cert.file";
	private static final String PROP_CERT_TYPE = "ssl.cert.type";
	private static final String DEFAULT_CERT_TYPE = "X.509";

	// ---------------------------------------------------------------------------------------------
	// Atributos
	// ---------------------------------------------------------------------------------------------
	private final HttpClient clienteHttp;
	private final LoggerService logger;

	/**
	 * Constructor que inicializa el cliente Http con los tiempos de espera configurados y la configuración de ssl definida.
	 */
	public OwnHttpClient() {
		this.logger = LoggerService.getInstance();
		if (!logger.isLoggerInitialized()) throw new IllegalArgumentException(
				"El logger debe de ser inicializado antes de usar el cliente HTTP.");

		// Leemos el timeout desde properties, usando valor por defecto si no existe
		int tiempoEsperaSegundos = Integer.parseInt(PropertyLoader.getInstance()
															.getApplicationProperty(
																	PROP_TIMEOUT_SEGUNDOS,
																	String.valueOf(
																			DEFAULT_TIMEOUT_SEGUNDOS)));
		boolean sslEnabled = Boolean.parseBoolean(PropertyLoader.getInstance()
														  .getApplicationProperty(
																  PROP_CERT_ENABLED,
																  "false"));
		HttpClient.Builder builder = HttpClient.newBuilder()
				.connectTimeout(Duration.ofSeconds(tiempoEsperaSegundos));

		if (sslEnabled) {
			String certFileName = PropertyLoader.getInstance()
					.getApplicationProperty(PROP_CERT_FILE);
			String certType = PropertyLoader.getInstance()
					.getApplicationProperty(PROP_CERT_TYPE, DEFAULT_CERT_TYPE);

			Path certPath = Paths.get(System.getProperty("user.dir"), certFileName);

			if (!Files.exists(certPath)) throw new RuntimeException(
					"No se encontró el certificado en: " + certPath);
			try (InputStream is = Files.newInputStream(certPath)) {
				CertificateFactory cf = CertificateFactory.getInstance(certType);
				Certificate cert = cf.generateCertificate(is);

				KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
				keyStore.load(null, null);
				keyStore.setCertificateEntry("mitma-cert", cert);

				TrustManagerFactory tmf = TrustManagerFactory.getInstance(
						TrustManagerFactory.getDefaultAlgorithm());
				tmf.init(keyStore);

				SSLContext sslContext = SSLContext.getInstance("TLS");
				sslContext.init(null, tmf.getTrustManagers(), null);

				builder.sslContext(sslContext);

				logger.logInfo("SSL configurado usando certificado externo: {}",
							   certPath);
			} catch (Exception e) {
				throw new RuntimeException(
						"Error configurando SSL con certificado externo", e);
			}
		}
		this.clienteHttp = builder.build();
	}

	// ---------------------------------------------------------------------------------------------
	// Métodos públicos
	// ---------------------------------------------------------------------------------------------

	/**
	 * Realiza una petición GET a la URL indicada y devuelve la respuesta como objeto JSON.
	 *
	 * @param urlString URL completa del recurso a consultar.
	 * @return {@link JSONObject} con el contenido de la respuesta.
	 * @throws SpainMitmaAPIClientException Si ocurre un error de conexión o lectura.
	 */
	public JSONObject getJsonObjet(String urlString) throws SpainMitmaAPIClientException {
		String cuerpoRespuesta = ejecutarPeticion(urlString);
		try {
			return new JSONObject(cuerpoRespuesta);
		} catch (JSONException e) {
			throw new SpainMitmaAPIClientException(
					"El JSON recibido ha causado un error al intentar ser convertido a JSON",
					e);
		}
	}

	/**
	 * Realiza una petición GET a la URL indicada y devuelve la respuesta como array JSON.
	 *
	 * @param urlString URL completa del recurso a consultar.
	 * @return {@link JSONArray} con el contenido de la respuesta.
	 * @throws SpainMitmaAPIClientException Si ocurre un error de conexión o lectura.
	 */
	public JSONArray getJsonArray(String urlString) throws SpainMitmaAPIClientException {
		String cuerpoRespuesta = ejecutarPeticion(urlString);
		try {
			return new JSONArray(cuerpoRespuesta);
		} catch (JSONException e) {
			throw new SpainMitmaAPIClientException(
					"El JSON recibido ha causado un error al intentar ser convertido a JSON",
					e);
		}
	}

	// ---------------------------------------------------------------------------------------------
	// Métodos privados auxiliares
	// ---------------------------------------------------------------------------------------------

	/**
	 * Ejecuta una petición HTTP GET y devuelve el cuerpo de la respuesta como String.
	 *
	 * @param urlString URL completa del recurso a consultar.
	 * @return Contenido del cuerpo de la respuesta.
	 * @throws SpainMitmaAPIClientException Si ocurre un error de conexión, lectura o respuesta vacía.
	 */
	private String ejecutarPeticion(String urlString)
			throws SpainMitmaAPIClientException {
		try {
			HttpRequest request = construirPeticion(urlString);
			HttpResponse<String> response = clienteHttp.send(request,
															 HttpResponse.BodyHandlers.ofString());

			if (response.statusCode() / 100 != 2) {
				logger.logError("Error HTTP {} al acceder a {}", response.statusCode(),
								urlString);
				throw new SpainMitmaAPIClientException(
						"Error HTTP " + response.statusCode() + " al acceder a: " +
								urlString);
			}

			String body = response.body();
			if (body == null) {
				logger.logError("Respuesta vacía al acceder a {}", urlString);
				throw new SpainMitmaAPIClientException(
						"Respuesta HTTP vacía al acceder a: " + urlString);
			}

			return body;
		} catch (IOException e) {
			logger.logError("Error ejecutando petición GET a {}: {}", urlString,
							e.getMessage());
			throw new SpainMitmaAPIClientException(
					"Error en la ejecución de una petición GET.", e);
		} catch (InterruptedException e) {
			// Restauramos el estado de interrupción
			Thread.currentThread().interrupt();
			logger.logError(
					"Error de interrupción en la ejecución de la petición petición GET a {}: {}",
					urlString, e.getMessage());
			throw new SpainMitmaAPIClientException(
					"Error en la ejecución de una petición GET.", e);
		}
	}

	/**
	 * Construye una petición GET estándar con cabecera JSON.
	 */
	private HttpRequest construirPeticion(String urlString) {
		return HttpRequest.newBuilder().uri(URI.create(urlString)).GET()
				.header(CABECERA_ACCEPT, TIPO_JSON)
				.timeout(Duration.ofSeconds(DEFAULT_TIMEOUT_SEGUNDOS)).build();
	}
}
