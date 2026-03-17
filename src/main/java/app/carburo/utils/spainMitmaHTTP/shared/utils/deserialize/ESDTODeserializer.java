package app.carburo.utils.spainMitmaHTTP.shared.utils.deserialize;

import app.carburo.utils.spainMitmaHTTP.shared.model.EstacionDeServicioResponseDTO;
import app.carburo.utils.spainMitmaHTTP.shared.model.PreciosCombustibleResponseDTO;
import app.carburo.utils.utils.deserilize.BasePropertyDeserializer;
import com.google.gson.*;

import java.lang.reflect.Type;

public class ESDTODeserializer
		extends BasePropertyDeserializer<EstacionDeServicioResponseDTO> {

	@Override
	public EstacionDeServicioResponseDTO deserialize(JsonElement json, Type typeOfT,
													 JsonDeserializationContext context)
			throws JsonParseException {

		JsonObject obj = json.getAsJsonObject();

		// Gson normal → respeta @SerializedName
		EstacionDeServicioResponseDTO es = new Gson().fromJson(obj,
															   EstacionDeServicioResponseDTO.class);
		// Sacamos los precios DESDE EL MISMO JSON
		PreciosCombustibleResponseDTO precios = context.deserialize(obj,
																	PreciosCombustibleResponseDTO.class);
		// Los inyectamos
		es.setPrecios(precios);
		return es;
	}
}
