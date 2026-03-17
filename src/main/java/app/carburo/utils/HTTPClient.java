package app.carburo.utils;

import app.carburo.utils.spainMitmaHTTP.SpainMitmaAPIClient;
import app.carburo.utils.utils.log.LoggerService;
import app.carburo.utils.utils.properties.PropertyLoader;
import ch.qos.logback.classic.Logger;

public class HTTPClient {

	/**
	 * Constructor por defecto.
	 * Inicializa el logger y el cargador de propiedades de la librería.
	 *
	 * @throws IllegalStateException si el logger no se inicializa correctamente.
	 */
	public HTTPClient() {
		// Inicialización del loggerService con el logger parametrizado
		LoggerService loggerService = LoggerService.getInstance();
		loggerService.generateLogger();
		if (!loggerService.isLoggerInitialized()) throw new IllegalStateException(
				"El logger no ha podido ser inicializado correctamente.");

		// Inicialización del propertiesLoader para cargar los archivos de configuración
		PropertyLoader propertyLoader = PropertyLoader.getInstance();
	}

	/**
	 * Constructor por defecto.
	 * Inicializa el logger y el cargador de propiedades de la librería.
	 *
	 * @throws IllegalStateException si el logger no se inicializa correctamente.
	 */
	public HTTPClient(Logger logger) {
		// Inicialización del loggerService con el logger parametrizado
		LoggerService loggerService = LoggerService.getInstance();
		loggerService.setLogger(logger);
		if (!loggerService.isLoggerInitialized()) throw new IllegalStateException(
				"El logger no ha podido ser inicializado correctamente.");

		// Inicialización del propertiesLoader para cargar los archivos de configuración
		PropertyLoader propertyLoader = PropertyLoader.getInstance();
	}

	/**
	 * Obtiene un cliente de la API de SpainMitma.
	 *
	 * @return instancia de SpainMitmaAPIClient.
	 */
	public SpainMitmaAPIClient getSpainAPIClient() {
		return new SpainMitmaAPIClient();
	}

}
