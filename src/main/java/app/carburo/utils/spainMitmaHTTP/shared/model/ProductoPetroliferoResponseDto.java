package app.carburo.utils.spainMitmaHTTP.shared.model;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import static app.carburo.utils.spainMitmaHTTP.shared.model.constants.ResponseKeys.*;

/**
 * DTO para los Productos Petrolíferos.
 * Pensado para ser usado en peticiones de listados de Productos Petrolíferos disponibles desde la API de carburantes.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ProductoPetroliferoResponseDto {

	@SerializedName(API_KEY_RESP_PRODUCTO_ID)
	private int idProducto;

	@SerializedName(API_KEY_RESP_PRODUCTO_DENOMINACION)
	private String nombreProducto;

	@SerializedName(API_KEY_RESP_PRODUCTO_ABREVIATURA)
	private String abreviaturaNombreProducto;
}
