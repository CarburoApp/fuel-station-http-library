package app.carburo.utils.spainMitmaHTTP.services.postesMaritimos;

import app.carburo.utils.spainMitmaHTTP.services.SpainMitmaAPIClientAbstractService;
import app.carburo.utils.spainMitmaHTTP.shared.exceptions.SpainMitmaAPIClientException;
import app.carburo.utils.spainMitmaHTTP.shared.model.PostesMaritimosResponseDTO;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GetPostesMaritimosService extends SpainMitmaAPIClientAbstractService {

	public PostesMaritimosResponseDTO getListadoPosteMaritimo()
			throws SpainMitmaAPIClientException {
		JSONObject jsonObj = doHTTPCallAndReturnJSONResponse("postes.maritimos", null);
		return parseJsonObject(jsonObj, PostesMaritimosResponseDTO.class);
	}

	public PostesMaritimosResponseDTO getListadoPosteMaritimoByCCAA(int idCCAA)
			throws SpainMitmaAPIClientException {
		Map<String, Object> params = new HashMap<>();
		params.put(PARAM_IDCCAA, idCCAA);
		JSONObject jsonObj = doHTTPCallAndReturnJSONResponse(
				"postes.maritimos.filtroCCAA", params);
		return parseJsonObject(jsonObj, PostesMaritimosResponseDTO.class);
	}

	public PostesMaritimosResponseDTO getListadoPosteMaritimoByProvincia(int idProvincia)
			throws SpainMitmaAPIClientException {
		Map<String, Object> params = new HashMap<>();
		params.put(PARAM_IDPROVINCIA, idProvincia);
		JSONObject jsonObj = doHTTPCallAndReturnJSONResponse(
				"postes.maritimos.filtroProvincia", params);
		return parseJsonObject(jsonObj, PostesMaritimosResponseDTO.class);
	}

	public PostesMaritimosResponseDTO getListadoPosteMaritimoByMunicipio(int idMunicipio)
			throws SpainMitmaAPIClientException {
		Map<String, Object> params = new HashMap<>();
		params.put(PARAM_IDMUNICIPIO, idMunicipio);
		JSONObject jsonObj = doHTTPCallAndReturnJSONResponse(
				"postes.maritimos.filtroMunicipio", params);
		return parseJsonObject(jsonObj, PostesMaritimosResponseDTO.class);
	}

	public PostesMaritimosResponseDTO getListadoPosteMaritimoByProducto(int combustible)
			throws SpainMitmaAPIClientException {
		Map<String, Object> params = new HashMap<>();
		params.put(PARAM_IDPRODUCTO, combustible);
		JSONObject jsonObj = doHTTPCallAndReturnJSONResponse(
				"postes.maritimos.filtroProducto", params);
		return parseJsonObject(jsonObj, PostesMaritimosResponseDTO.class);
	}

	public PostesMaritimosResponseDTO getListadoPosteMaritimoByProvinciaAndProducto(
			int idProvincia, int combustible) throws SpainMitmaAPIClientException {
		Map<String, Object> params = new HashMap<>();
		params.put(PARAM_IDPROVINCIA, idProvincia);
		params.put(PARAM_IDPRODUCTO, combustible);
		JSONObject jsonObj = doHTTPCallAndReturnJSONResponse(
				"postes.maritimos.filtroProvinciaProducto", params);
		return parseJsonObject(jsonObj, PostesMaritimosResponseDTO.class);
	}

	public PostesMaritimosResponseDTO getListadoPosteMaritimoByCCAAAndProducto(int idCCAA,
																			   int combustible)
			throws SpainMitmaAPIClientException {
		Map<String, Object> params = new HashMap<>();
		params.put(PARAM_IDCCAA, idCCAA);
		params.put(PARAM_IDPRODUCTO, combustible);
		JSONObject jsonObj = doHTTPCallAndReturnJSONResponse(
				"postes.maritimos.filtroCCAAProducto", params);
		return parseJsonObject(jsonObj, PostesMaritimosResponseDTO.class);
	}

	public PostesMaritimosResponseDTO getListadoPosteMaritimoByMunicipioAndProducto(
			int idMunicipio, int combustible) throws SpainMitmaAPIClientException {
		Map<String, Object> params = new HashMap<>();
		params.put(PARAM_IDMUNICIPIO, idMunicipio);
		params.put(PARAM_IDPRODUCTO, combustible);
		JSONObject jsonObj = doHTTPCallAndReturnJSONResponse(
				"postes.maritimos.filtroMunicipioProducto", params);
		return parseJsonObject(jsonObj, PostesMaritimosResponseDTO.class);
	}
}
