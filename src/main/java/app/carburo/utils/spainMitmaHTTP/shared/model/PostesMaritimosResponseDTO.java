package app.carburo.utils.spainMitmaHTTP.shared.model;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.List;

import static app.carburo.utils.spainMitmaHTTP.shared.model.constants.ResponseKeys.*;


/**
 * DTO para la petición base de Postes Marítimos.
 * Pensado para ser usado en todas peticiones relacionadas con Postes Marítimos.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PostesMaritimosResponseDTO {

	@SerializedName(API_KEY_RESP_FECHA)
	private String fecha; // parsearemos a LocalDateTime más adelante si quieres

	@SerializedName(API_KEY_RESP_LISTADO_EESS)
	private List<PosteMaritimoResponseDTO> listaEESS;

	@SerializedName(API_KEY_RESP_NOTA)
	private String nota;

	@SerializedName(API_KEY_RESP_RES_CONSULTA)
	private String resultadoConsulta;


}
