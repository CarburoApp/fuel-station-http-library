package app.carburo.utils.spainMitmaHTTP.shared.model.enums;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import static app.carburo.utils.spainMitmaHTTP.shared.model.constants.ResponseKeys.*;

@Getter
public enum VentaPMDTO {
	@SerializedName(API_KEY_RESP_VENTA_BARCOS_DEPORTIVOS_O_RECREO) BARCOS_DEPORTIVO_O_RECREO,
}