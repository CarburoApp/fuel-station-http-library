package app.carburo.utils.spainMitmaHTTP.shared.model;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import static app.carburo.utils.spainMitmaHTTP.shared.model.constants.ResponseKeys.*;

/**
 * DTO para los Municipios.
 * Pensado para ser usado en peticiones de listados de Municipios desde la API de carburantes.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class MunicipioResponseDTO {

	@SerializedName(API_KEY_RESP_MUNICIPIO_ID)
	private int idMunicipio;

	@SerializedName(API_KEY_RESP_PROVINCIA_ID)
	private int idProvincia;

	@SerializedName(API_KEY_RESP_CA_ID)
	private int idComunidadAutonoma;

	@SerializedName(API_KEY_RESP_MUNICIPIO_DENOMINACION)
	private String municipio;

	@SerializedName(API_KEY_RESP_PROVINCIA_DENOMINACION)
	private String provincia;

	@SerializedName(API_KEY_RESP_CA_DENOMINACION)
	private String comunidadAutonoma;
}

