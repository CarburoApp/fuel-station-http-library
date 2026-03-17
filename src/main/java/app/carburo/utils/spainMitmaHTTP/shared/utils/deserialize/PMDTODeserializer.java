package app.carburo.utils.spainMitmaHTTP.shared.utils.deserialize;

import app.carburo.utils.spainMitmaHTTP.shared.model.PosteMaritimoResponseDTO;
import app.carburo.utils.spainMitmaHTTP.shared.model.PreciosCombustibleResponseDTO;
import app.carburo.utils.utils.deserilize.BasePropertyDeserializer;
import com.google.gson.*;

import java.lang.reflect.Type;

public class PMDTODeserializer
		extends BasePropertyDeserializer<PosteMaritimoResponseDTO> {

	@Override
	public PosteMaritimoResponseDTO deserialize(JsonElement json, Type typeOfT,
												JsonDeserializationContext context)
			throws JsonParseException {

		JsonObject obj = json.getAsJsonObject();

		// Gson normal → respeta @SerializedName
		PosteMaritimoResponseDTO pm = new Gson().fromJson(obj,
														  PosteMaritimoResponseDTO.class);
		// Sacamos los precios DESDE EL MISMO JSON
		PreciosCombustibleResponseDTO precios = context.deserialize(obj,
																	PreciosCombustibleResponseDTO.class);
		// Los inyectamos
		pm.setPrecios(precios);
		return pm;
	}
}
