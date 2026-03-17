package library.spainMitmaHTTP.services;

import app.carburo.utils.HTTPClient;
import app.carburo.utils.spainMitmaHTTP.SpainMitmaAPIClient;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;

public class SpainMitmaAPIClientAbstractTest {

	protected static final int PUERTO = 8089;
	protected static final String DOMINIO = "localhost";
	protected static final String JSON_UBI_BASE = "src/test/resources/wiremock/apiMitmaESP";


	private static WireMockServer wireMockServer;
	protected static SpainMitmaAPIClient api;
	protected static HTTPClient httpClient;

	@BeforeAll
	static void startServer() {
		wireMockServer = new WireMockServer(PUERTO);
		wireMockServer.start();
		configureFor(DOMINIO, PUERTO);

		// Cliente único para todos los tests
		HTTPClient httpClient = new HTTPClient();
		api = httpClient.getSpainAPIClient();
	}

	@AfterAll
	static void stopServer() {
		if (wireMockServer != null) wireMockServer.stop();
	}
}
