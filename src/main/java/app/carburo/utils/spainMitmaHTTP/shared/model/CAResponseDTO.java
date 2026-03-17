package app.carburo.utils.spainMitmaHTTP.shared.model;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import static app.carburo.utils.spainMitmaHTTP.shared.model.constants.ResponseKeys.API_KEY_RESP_CA_DENOMINACION;
import static app.carburo.utils.spainMitmaHTTP.shared.model.constants.ResponseKeys.API_KEY_RESP_CA_ID;

/**
 * DTO para las Comunidades Autónomas (CCAA).
 * Pensado para ser usado en peticiones de listados de CCAA desde la API de carburantes.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CAResponseDTO {

	@SerializedName(API_KEY_RESP_CA_ID)
	private int idCA;

	@SerializedName(API_KEY_RESP_CA_DENOMINACION)
	private String ca;
}

