package app.carburo.utils.spainMitmaHTTP.services.listados;

import app.carburo.utils.spainMitmaHTTP.services.SpainMitmaAPIClientAbstractService;
import app.carburo.utils.spainMitmaHTTP.shared.exceptions.SpainMitmaAPIClientException;
import app.carburo.utils.spainMitmaHTTP.shared.model.CAResponseDTO;
import app.carburo.utils.spainMitmaHTTP.shared.model.MunicipioResponseDTO;
import app.carburo.utils.spainMitmaHTTP.shared.model.ProductoPetroliferoResponseDto;
import app.carburo.utils.spainMitmaHTTP.shared.model.ProvinciaResponseDTO;
import org.json.JSONArray;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetListadosService extends SpainMitmaAPIClientAbstractService {

	// ============================
	// LISTADOS
	// ============================

	/**
	 * Obtiene el listado completo de Comunidades Autónomas.
	 */
	public List<CAResponseDTO> getListadoCCAA() throws SpainMitmaAPIClientException {
		JSONArray array = doHTTPCallAndReturnJSONArrayResponse("listados.ccaa", null);
		return parseJsonArray(array, CAResponseDTO.class);
	}

	/**
	 * Obtiene el listado completo de Provincias.
	 */
	public List<ProvinciaResponseDTO> getListadoProvincia()
			throws SpainMitmaAPIClientException {
		JSONArray array = doHTTPCallAndReturnJSONArrayResponse("listados.provincias",
															   null);
		return parseJsonArray(array, ProvinciaResponseDTO.class);
	}

	/**
	 * Obtiene el listado de Provincias pertenecientes a una Comunidad Autónoma específica.
	 */
	public List<ProvinciaResponseDTO> getListadoProvinciaByCCAA(int idCCAA)
			throws SpainMitmaAPIClientException {
		Map<String, Object> params = new HashMap<>();
		params.put(PARAM_IDCCAA, idCCAA);
		JSONArray array = doHTTPCallAndReturnJSONArrayResponse(
				"listados.provinciasPorCCAA", params);
		return parseJsonArray(array, ProvinciaResponseDTO.class);
	}

	/**
	 * Obtiene el listado completo de Municipios.
	 */
	public List<MunicipioResponseDTO> getListadoMunicipio()
			throws SpainMitmaAPIClientException {
		JSONArray array = doHTTPCallAndReturnJSONArrayResponse("listados.municipios",
															   null);
		return parseJsonArray(array, MunicipioResponseDTO.class);
	}

	/**
	 * Obtiene el listado de Municipios pertenecientes a una Provincia específica.
	 */
	public List<MunicipioResponseDTO> getListadoMunicipioByProvincia(int idProvincia)
			throws SpainMitmaAPIClientException {
		Map<String, Object> params = new HashMap<>();
		params.put(PARAM_IDPROVINCIA, idProvincia);
		JSONArray array = doHTTPCallAndReturnJSONArrayResponse(
				"listados.municipiosPorProvincia", params);
		return parseJsonArray(array, MunicipioResponseDTO.class);

	}

	/**
	 * Obtiene el listado completo de productos petrolíferos.
	 */
	public List<ProductoPetroliferoResponseDto> getListadoProductoPetrolifero()
			throws SpainMitmaAPIClientException {
		JSONArray array = doHTTPCallAndReturnJSONArrayResponse("listados.productos",
															   null);
		return parseJsonArray(array, ProductoPetroliferoResponseDto.class);
	}
}
