package app.carburo.utils.spainMitmaHTTP.shared.model.enums;


import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import static app.carburo.utils.spainMitmaHTTP.shared.model.constants.ResponseKeys.*;

@Getter
public enum MargenDTO {
	@SerializedName(API_KEY_RESP_MARGEN_DERECHO) DERECHO,
	@SerializedName(API_KEY_RESP_MARGEN_IZQUIERDO) IZQUIERDO,
	@SerializedName(API_KEY_RESP_MARGEN_NO_APLICA) NO_APLICA
}