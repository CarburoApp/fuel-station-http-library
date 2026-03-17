package app.carburo.utils.spainMitmaHTTP.services;

import app.carburo.utils.spainMitmaHTTP.shared.exceptions.SpainMitmaAPIClientException;
import app.carburo.utils.spainMitmaHTTP.shared.model.EstacionDeServicioResponseDTO;
import app.carburo.utils.spainMitmaHTTP.shared.model.PosteMaritimoResponseDTO;
import app.carburo.utils.spainMitmaHTTP.shared.utils.deserialize.ESDTODeserializer;
import app.carburo.utils.spainMitmaHTTP.shared.utils.deserialize.PMDTODeserializer;
import app.carburo.utils.utils.log.LoggerService;
import app.carburo.utils.utils.network.OwnHttpClient;
import app.carburo.utils.utils.properties.PropertyLoader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * Clase encargada de realizar peticiones HTTP hacia los endpoints del servicio de gasolineras.
 * Permite consultar datos actuales e históricos de estaciones, así como listados de entidades geográficas
 * (CCAA, provincias, municipios) y productos (combustibles).
 * <p>
 * Los endpoints se definen en el archivo: resources/endpoints.properties
 */
public abstract class SpainMitmaAPIClientAbstractService {

	// ============================
	// PLACEHOLDERS DE ENDPOINTS
	// ============================

	public static final String PARAM_FECHA = "FECHA";
	public static final String PARAM_IDCCAA = "IDCCAA";
	public static final String PARAM_IDPROVINCIA = "IDPROVINCIA";
	public static final String PARAM_IDMUNICIPIO = "IDMUNICIPIO";
	public static final String PARAM_IDPRODUCTO = "IDPRODUCTO";

	//Utilitarios
	private final OwnHttpClient httpClient;
	private final PropertyLoader propertyLoader;
	private final Gson gson;

	public SpainMitmaAPIClientAbstractService() {
		//Inicialización de los archivos de propiedades
		this.propertyLoader = PropertyLoader.getInstance();

		//Inicializamos el cliente Http
		try {
			//Inicializamos el cliente HTTP
			this.httpClient = new OwnHttpClient();
		} catch (Exception e) { //Comprobamos las excepciones del cliente HTTP
			LoggerService.getInstance().logError("Error al crear el cliente Http.", e);
			throw new IllegalStateException("No se ha podido cargar el cliente HTTP", e);
		}

		// Configurar el GSON
		this.gson = configureGson();
	}

	/**
	 * Configura Gson con todos los deserializadores que necesitemos
	 */
	private Gson configureGson() {
		return new GsonBuilder().registerTypeAdapter(EstacionDeServicioResponseDTO.class,
													 new ESDTODeserializer())
				.registerTypeAdapter(PosteMaritimoResponseDTO.class,
									 new PMDTODeserializer()).create();
	}

	// ---------------------------------------------------------------------------------------------
	// MÉTODOS UTILITARIOS
	// ---------------------------------------------------------------------------------------------

	/**
	 * Construye la URL completa del endpoint, sustituyendo placeholders por los valores dados.
	 *
	 * @param key    La clave del endpoint en endpoints.properties
	 * @param params Los valores para sustituir en la ruta del endpoint
	 * @return La URL completa del endpoint
	 * @throws IllegalArgumentException Si alguno de los valores no es válido.
	 * @throws IllegalStateException    Si no se encuentran los valores o la URL base no está definida.
	 */
	private String buildUrl(String key, Map<String, Object> params)
			throws IllegalArgumentException, IllegalStateException {
		if (key == null || key.isBlank()) throw new IllegalArgumentException(
				"Argumento clave no válido para construir la URL: " + key);

		// Cargamos la url base
		String base = propertyLoader.getEndpointProperty("base.url");
		if (base == null) //Comprobamos su validez
			throw new IllegalStateException(
					"No se ha encontrado la URL base en endpoints.properties.");

		// Cargamos la ruta del endpoint definida por la clave
		String path = propertyLoader.getEndpointProperty(key);
		if (path == null) //Comprobamos su validez
			throw new IllegalArgumentException(
					"No se ha encontrado ningún endpoint con la clave: " + key);

		// Formateamos la ruta con los valores proporcionados
		if (params != null) {
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				String placeholder = "{" + entry.getKey() + "}";
				if (!path.contains(placeholder)) {
					throw new IllegalArgumentException("El placeholder " + placeholder +
															   " no existe en el endpoint " +
															   key);
				}
				path = path.replace(placeholder, entry.getValue().toString());
			}
		}

		return base + path;
	}

	/**
	 * Realiza una petición GET al endpoint indicado y devuelve el resultado en formato JSON.
	 */
	protected JSONObject doHTTPCallAndReturnJSONResponse(String key,
														 Map<String, Object> params)
			throws SpainMitmaAPIClientException {
		String url = buildUrl(key, params);
		return httpClient.getJsonObjet(url);
	}

	/**
	 * Realiza una petición GET al endpoint indicado y devuelve el resultado en formato JSON.
	 */
	protected JSONArray doHTTPCallAndReturnJSONArrayResponse(String key,
															 Map<String, Object> params)
			throws SpainMitmaAPIClientException {
		String url = buildUrl(key, params);
		return httpClient.getJsonArray(url);
	}

	/**
	 * Convierte un JSONArray de la API en una lista de DTOs de tipo T usando Gson.
	 *
	 * @param <T>   Tipo del DTO destino
	 * @param array JSONArray a convertir
	 * @param clazz Clase del DTO
	 * @return Lista de objetos de tipo T
	 */
	protected <T> List<T> parseJsonArray(JSONArray array, Class<T> clazz) {
		Type listType = TypeToken.getParameterized(List.class, clazz).getType();
		return gson.fromJson(array.toString(), listType);
	}

	/**
	 * Convierte un JSONObject de la API en un objeto de tipo T usando Gson.
	 *
	 * @param <T>   Tipo del DTO destino
	 * @param obj   JSONObject a convertir
	 * @param clazz Clase del DTO
	 * @return Objeto de tipo T
	 */
	protected <T> T parseJsonObject(JSONObject obj, Class<T> clazz) {
		return gson.fromJson(obj.toString(), clazz);
	}

	/**
	 * Formatea la fecha según el patrón definido en endpoints.properties
	 */
	protected String formatDate(LocalDate fecha) {
		return fecha.format(DateTimeFormatter.ofPattern(
				propertyLoader.getEndpointProperty(
						"estaciones.terrestres.hist.pattern")));
	}
}
