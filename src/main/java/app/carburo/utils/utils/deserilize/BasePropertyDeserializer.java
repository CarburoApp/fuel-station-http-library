package app.carburo.utils.utils.deserilize;


import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Clase base para deserializadores que leen los nombres de los campos
 * desde un archivo jsonKey.properties cargado por PropertyLoader.
 *
 * @param <T> Tipo del DTO destino.
 */
public abstract class BasePropertyDeserializer<T> implements JsonDeserializer<T> {

	/**
	 * Implementa este método en el deserializer concreto para definir cómo se
	 * construye el DTO a partir del JsonObject.
	 */
	@Override
	public abstract T deserialize(JsonElement json, Type typeOfT,
								  JsonDeserializationContext context)
			throws JsonParseException;

}
