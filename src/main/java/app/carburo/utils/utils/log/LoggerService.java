package app.carburo.utils.utils.log;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servicio encargado de centralizar el uso del sistema de logging
 * de la librería HTTP de Carburo para el uso de APIs.
 *
 * <p>Esta clase encapsula una instancia de {@link Logger} y proporciona
 * métodos auxiliares para escribir logs en distintos niveles
 * (debug, info, warn y error) añadiendo automáticamente una cabecera
 * común a todos los mensajes.</p>
 *
 * <p>Antes de utilizar el logger es obligatorio inicializarlo mediante
 * {@link #generateLogger()} o {@link #setLogger(Logger)}.</p>
 */
public class LoggerService {

	/**
	 * Cabecera que se añade automáticamente a todos los mensajes de log
	 * generados por la librería.
	 */
	public static final String LOG_HEADER = " -- CARBURO HTTP FUEL LIBRARY -- ";

	/**
	 * Instancia interna del logger utilizada por el servicio.
	 */
	private Logger logger;

	/**
	 * Constructor privado para evitar instanciación directa.
	 */
	private LoggerService() {}

	/**
	 * Devuelve la instancia única del LoggerService.
	 *
	 * @return instancia singleton del servicio de logging.
	 */
	public static LoggerService getInstance() {
		return InstanceHolder.instance;
	}

	/**
	 * Holder para implementar el patrón Singleton de forma thread-safe y lazy-loaded.
	 * La instancia se crea únicamente cuando se llama por primera vez a getInstance().
	 */
	private static final class InstanceHolder {
		private static final LoggerService instance = new LoggerService();
	}

	/**
	 * Establece manualmente la instancia de logger a utilizar.
	 *
	 * @param logger instancia de {@link Logger} que se utilizará para registrar los logs.
	 * @throws IllegalArgumentException si el logger proporcionado es nulo.
	 */
	public void setLogger(Logger logger) {
		if (logger == null)
			throw new IllegalArgumentException("El logger no puede ser nulo.");
		this.logger = logger;
	}

	/**
	 * Genera una nueva instancia de logger con el nombre "CarburoLogger".
	 *
	 * @throws IllegalStateException si el logger ya ha sido inicializado o si falla
	 *                               el proceso de inicialización.
	 */
	public void generateLogger() {
		this.logger = (Logger) LoggerFactory.getLogger("CarburoLogger");
		inicializationCheck();
	}

	/**
	 * Registra un mensaje de nivel DEBUG.
	 *
	 * @param message mensaje a registrar.
	 * @param args    argumentos opcionales para formatear el mensaje.
	 * @throws IllegalStateException si el logger no ha sido inicializado.
	 */
	public void logDebug(String message, Object... args) {
		inicializationCheck();
		messageCheck(message);
		logger.debug(LOG_HEADER + message, args);
	}

	/**
	 * Registra un mensaje de nivel INFO.
	 *
	 * @param message mensaje a registrar.
	 * @param args    argumentos opcionales para formatear el mensaje.
	 * @throws IllegalStateException si el logger no ha sido inicializado.
	 */
	public void logInfo(String message, Object... args) {
		inicializationCheck();
		messageCheck(message);
		logger.info(LOG_HEADER + message, args);
	}

	/**
	 * Registra un mensaje de nivel WARN.
	 *
	 * @param message mensaje a registrar.
	 * @param args    argumentos opcionales para formatear el mensaje.
	 * @throws IllegalStateException si el logger no ha sido inicializado.
	 */
	public void logWarm(String message, Object... args) {
		inicializationCheck();
		messageCheck(message);
		logger.warn(LOG_HEADER + message, args);
	}

	/**
	 * Registra un mensaje de nivel ERROR.
	 *
	 * @param message mensaje a registrar.
	 * @param args    argumentos opcionales para formatear el mensaje.
	 * @throws IllegalStateException si el logger no ha sido inicializado.
	 */
	public void logError(String message, Object... args) {
		inicializationCheck();
		messageCheck(message);
		logger.error(LOG_HEADER + message, args);
	}

	/**
	 * Registra un mensaje de nivel ERROR.
	 *
	 * @param message mensaje a registrar.
	 * @param t    excepción lanzada.
	 * @throws IllegalStateException si el logger no ha sido inicializado.
	 */
	public void logError(String message, Throwable t) {
		inicializationCheck();
		messageCheck(message);
		logger.error(LOG_HEADER + message, t);
	}

	/**
	 * Indica si el logger ha sido inicializado.
	 *
	 * @return {@code true} si el logger está inicializado, {@code false} en caso contrario.
	 */
	public boolean isLoggerInitialized() {
		return logger != null;
	}

	/**
	 * Comprueba que el mensaje proporcionado no sea nulo.
	 *
	 * @param message mensaje a validar.
	 * @throws IllegalStateException si el mensaje es nulo.
	 */
	private void messageCheck(String message) {
		if (message == null)
			throw new IllegalStateException("El mensaje he escribir no puede ser nulo.");
	}

	/**
	 * Comprueba que el logger haya sido inicializado antes de su uso.
	 *
	 * @throws IllegalStateException si el logger no ha sido inicializado.
	 */
	private void inicializationCheck() {
		if (logger == null) throw new IllegalStateException(
				"El logger ha se ser inicializado antes de su uso.");
	}

}