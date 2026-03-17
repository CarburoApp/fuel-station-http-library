package app.carburo.utils.spainMitmaHTTP.shared.model;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import static app.carburo.utils.spainMitmaHTTP.shared.model.constants.ResponseKeys.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ProvinciaResponseDTO {

	// TODO FALLO DE ESCRITURA EN LA API, SE ESCRIBE "Povincia" EN LUGAR DE "Provincia"
	@SerializedName(API_KEY_RESP_POVINCIA_ID_ERRONEO)
	private int idProvincia;

	@SerializedName(API_KEY_RESP_CA_ID)
	private int idCCAA;

	@SerializedName(API_KEY_RESP_PROVINCIA_DENOMINACION)
	private String provincia;

	@SerializedName(API_KEY_RESP_CA_DENOMINACION)
	private String ccaa;
}

