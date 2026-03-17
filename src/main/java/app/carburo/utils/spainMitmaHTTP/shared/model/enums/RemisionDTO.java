package app.carburo.utils.spainMitmaHTTP.shared.model.enums;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import static app.carburo.utils.spainMitmaHTTP.shared.model.constants.ResponseKeys.API_KEY_RESP_REMISION_DM;
import static app.carburo.utils.spainMitmaHTTP.shared.model.constants.ResponseKeys.API_KEY_RESP_REMISION_OM;

@Getter
public enum RemisionDTO {
	@SerializedName(API_KEY_RESP_REMISION_OM) OM,
	@SerializedName(API_KEY_RESP_REMISION_DM) DM
}