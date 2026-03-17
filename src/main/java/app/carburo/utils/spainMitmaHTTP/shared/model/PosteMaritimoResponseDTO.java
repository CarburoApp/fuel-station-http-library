package app.carburo.utils.spainMitmaHTTP.shared.model;

import app.carburo.utils.spainMitmaHTTP.shared.model.enums.RemisionDTO;
import app.carburo.utils.spainMitmaHTTP.shared.model.enums.VentaPMDTO;
import com.google.gson.annotations.SerializedName;
import lombok.*;

import static app.carburo.utils.spainMitmaHTTP.shared.model.constants.ResponseKeys.*;

/**
 * DTO para la petición de Poste Maritimo.
 * Pensado para ser usado en peticiones de Postes Maritimos.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PosteMaritimoResponseDTO {

	@SerializedName(API_KEY_POSTE_ID)
	private int idPosteMaritimo;

	@SerializedName(API_KEY_POSTE_ROTULO)
	private String rotulo;

	@SerializedName(API_KEY_POSTE_DIRECCION)
	private String direccion;

	@SerializedName(API_KEY_POSTE_HORARIO)
	private String horario;

	@SerializedName(API_KEY_POSTE_LOCALIDAD)
	private String localidad;

	@SerializedName(API_KEY_RESP_MUNICIPIO_DENOMINACION)
	private String municipio;

	@SerializedName(API_KEY_POSTE_CP)
	private int cp;

	@SerializedName(API_KEY_RESP_PROVINCIA_DENOMINACION)
	private String provincia;

	@SerializedName(API_KEY_RESP_MUNICIPIO_ID)
	private int idMunicipio;

	@SerializedName(API_KEY_RESP_PROVINCIA_ID)
	private int idProvincia;

	@SerializedName(API_KEY_RESP_CA_ID)
	private int idCCAA;

	@SerializedName(API_KEY_POSTE_REMISION)
	private RemisionDTO remision;

	@SerializedName(API_KEY_POSTE_VENTA)
	private VentaPMDTO tipoVenta;

	@SerializedName(API_KEY_POSTE_LATITUD)
	private String latitud; // String por la coma, luego puedes convertir a double

	@SerializedName(API_KEY_POSTE_LONGITUD)
	private String longitud; // idem

	@SerializedName(API_KEY_POSTE_PUERTO)
	private String puerto;


	/*
	 * Precios
	 */
	private PreciosCombustibleResponseDTO precios;
}
