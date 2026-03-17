package app.carburo.utils.spainMitmaHTTP.shared.model.enums;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import static app.carburo.utils.spainMitmaHTTP.shared.model.constants.ResponseKeys.API_KEY_RESP_VENTA_PUBLICA;
import static app.carburo.utils.spainMitmaHTTP.shared.model.constants.ResponseKeys.API_KEY_RESP_VENTA_RESTRINGIDA;

@Getter
public enum VentaESDTO {
	@SerializedName(API_KEY_RESP_VENTA_PUBLICA) PUBLICA,
	@SerializedName(API_KEY_RESP_VENTA_RESTRINGIDA) RESTRINGIDA
}