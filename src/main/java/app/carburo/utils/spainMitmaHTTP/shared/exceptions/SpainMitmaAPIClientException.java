package app.carburo.utils.spainMitmaHTTP.shared.exceptions;

/**
 * Excepción personalizada para errores en las llamadas a la API de Spain Mitma.
 */
public class SpainMitmaAPIClientException extends Exception {

	/**
	 * Constructor con mensaje de error.
	 *
	 * @param message mensaje descriptivo del error.
	 */
	public SpainMitmaAPIClientException(String message) {
		super(message);
	}

	/**
	 * Constructor con mensaje y causa original de la excepción.
	 *
	 * @param message mensaje descriptivo del error.
	 * @param cause   excepción original que provocó el error.
	 */
	public SpainMitmaAPIClientException(String message, Throwable cause) {
		super(message, cause);
	}
}