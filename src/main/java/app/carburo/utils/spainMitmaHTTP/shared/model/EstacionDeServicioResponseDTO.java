package app.carburo.utils.spainMitmaHTTP.shared.model;

import app.carburo.utils.spainMitmaHTTP.shared.model.enums.MargenDTO;
import app.carburo.utils.spainMitmaHTTP.shared.model.enums.RemisionDTO;
import app.carburo.utils.spainMitmaHTTP.shared.model.enums.VentaESDTO;
import com.google.gson.annotations.SerializedName;
import lombok.*;

import static app.carburo.utils.spainMitmaHTTP.shared.model.constants.ResponseKeys.*;

/**
 * DTO para la petición Estacion de Servicio (EESS).
 * Pensado para ser usado en peticiones de EESS.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class EstacionDeServicioResponseDTO {

	@SerializedName(API_KEY_RESP_EESS_ID)
	private int ideess;

	@SerializedName(API_KEY_RESP_EESS_ROTULO)
	private String rotulo;

	@SerializedName(API_KEY_RESP_EESS_DIRECCION)
	private String direccion;

	@SerializedName(API_KEY_RESP_EESS_HORARIO)
	private String horario;

	@SerializedName(API_KEY_RESP_EESS_LOCALIDAD)
	private String localidad;

	@SerializedName(API_KEY_RESP_MUNICIPIO_DENOMINACION)
	private String municipio;

	@SerializedName(API_KEY_RESP_EESS_CP)
	private int cp;

	@SerializedName(API_KEY_RESP_PROVINCIA_DENOMINACION)
	private String provincia;

	@SerializedName(API_KEY_RESP_MUNICIPIO_ID)
	private int idMunicipio;

	@SerializedName(API_KEY_RESP_PROVINCIA_ID)
	private int idProvincia;

	@SerializedName(API_KEY_RESP_CA_ID)
	private int idCCAA;

	@SerializedName(API_KEY_RESP_EESS_MARGEN)
	private MargenDTO margen;

	@SerializedName(API_KEY_RESP_EESS_REMISION)
	private RemisionDTO remision;

	@SerializedName(API_KEY_RESP_EESS_VENTA)
	private VentaESDTO tipoVenta;

	@SerializedName(API_KEY_RESP_EESS_LATITUD)
	private String latitud; // String por la coma, luego puedes convertir a double

	@SerializedName(API_KEY_RESP_EESS_LONGITUD)
	private String longitud; // idem

	@SerializedName(API_KEY_RESP_EESS_X_100_BIOETANOL)
	private String bioEtanol;

	@SerializedName(API_KEY_RESP_EESS_X_100_ESTER_METILICO)
	private String esterMetilico;

	/*
	 * Precios
	 */
	private PreciosCombustibleResponseDTO precios;
}
