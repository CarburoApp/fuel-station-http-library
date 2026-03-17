package app.carburo.utils.spainMitmaHTTP.services.eessTerrestres;

import app.carburo.utils.spainMitmaHTTP.services.SpainMitmaAPIClientAbstractService;
import app.carburo.utils.spainMitmaHTTP.shared.exceptions.SpainMitmaAPIClientException;
import app.carburo.utils.spainMitmaHTTP.shared.model.EstacionDeServicioTerrestreResponseDTO;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GetEESSTerrestresService extends SpainMitmaAPIClientAbstractService {

	/**
	 * Obtiene todas las estaciones disponibles actualmente.
	 */
	public EstacionDeServicioTerrestreResponseDTO getListadoEstacionDeServicioTerrestre() throws
																						  SpainMitmaAPIClientException {
		JSONObject jsonObj = doHTTPCallAndReturnJSONResponse("estaciones.terrestres",
															 null);
		return parseJsonObject(jsonObj, EstacionDeServicioTerrestreResponseDTO.class);
	}

	/**
	 * Filtra estaciones por Comunidad Autónoma
	 */
	public EstacionDeServicioTerrestreResponseDTO getListadoEstacionDeServicioTerrestreByCCAA(int idCCAA) throws SpainMitmaAPIClientException {
		Map<String, Object> params = new HashMap<>();
		params.put(PARAM_IDCCAA, idCCAA);
		JSONObject jsonObj = doHTTPCallAndReturnJSONResponse(
				"estaciones.terrestres.filtroCCAA", params);
		return parseJsonObject(jsonObj, EstacionDeServicioTerrestreResponseDTO.class);
	}

	/**
	 * Filtra estaciones por provincia
	 */
	public EstacionDeServicioTerrestreResponseDTO getListadoEstacionDeServicioTerrestreByProvincia(int idProvincia) throws SpainMitmaAPIClientException {
		Map<String, Object> params = new HashMap<>();
		params.put(PARAM_IDPROVINCIA, idProvincia);
		JSONObject jsonObj = doHTTPCallAndReturnJSONResponse(
				"estaciones.terrestres.filtroProvincia", params);
		return parseJsonObject(jsonObj, EstacionDeServicioTerrestreResponseDTO.class);
	}

	/**
	 * Filtra estaciones por municipio
	 */
	public EstacionDeServicioTerrestreResponseDTO getListadoEstacionDeServicioTerrestreByMunicipio(int idMunicipio) throws SpainMitmaAPIClientException {
		Map<String, Object> params = new HashMap<>();
		params.put(PARAM_IDMUNICIPIO, idMunicipio);
		JSONObject jsonObj = doHTTPCallAndReturnJSONResponse(
				"estaciones.terrestres.filtroMunicipio", params);
		return parseJsonObject(jsonObj, EstacionDeServicioTerrestreResponseDTO.class);
	}

	/**
	 * Filtra estaciones por tipo de combustible
	 */
	public EstacionDeServicioTerrestreResponseDTO getListadoEstacionDeServicioTerrestreByProducto(int combustible) throws SpainMitmaAPIClientException {
		Map<String, Object> params = new HashMap<>();
		params.put(PARAM_IDPRODUCTO, combustible);
		JSONObject jsonObj = doHTTPCallAndReturnJSONResponse(
				"estaciones.terrestres.filtroProducto", params);
		return parseJsonObject(jsonObj, EstacionDeServicioTerrestreResponseDTO.class);
	}

	/**
	 * Filtra estaciones por provincia y producto
	 */
	public EstacionDeServicioTerrestreResponseDTO getListadoEstacionDeServicioTerrestreByProvinciaAndProducto(int idProvincia,
																											int combustible)
			throws SpainMitmaAPIClientException {
		Map<String, Object> params = new HashMap<>();
		params.put(PARAM_IDPROVINCIA, idProvincia);
		params.put(PARAM_IDPRODUCTO, combustible);
		JSONObject jsonObj = doHTTPCallAndReturnJSONResponse(
				"estaciones.terrestres.filtroProvinciaProducto", params);
		return parseJsonObject(jsonObj, EstacionDeServicioTerrestreResponseDTO.class);
	}

	/**
	 * Filtra estaciones por CCAA y producto
	 */
	public EstacionDeServicioTerrestreResponseDTO getListadoEstacionDeServicioTerrestreByCCAAAndProducto(int idCCAA, int combustible)
			throws SpainMitmaAPIClientException {
		Map<String, Object> params = new HashMap<>();
		params.put(PARAM_IDCCAA, idCCAA);
		params.put(PARAM_IDPRODUCTO, combustible);
		JSONObject jsonObj = doHTTPCallAndReturnJSONResponse(
				"estaciones.terrestres.filtroCCAAProducto", params);
		return parseJsonObject(jsonObj, EstacionDeServicioTerrestreResponseDTO.class);
	}

	/**
	 * Filtra estaciones por municipio y producto
	 */
	public EstacionDeServicioTerrestreResponseDTO getListadoEstacionDeServicioTerrestreByMunicipioAndProducto(int idMunicipio,
																											 int combustible)
			throws SpainMitmaAPIClientException {
		Map<String, Object> params = new HashMap<>();
		params.put(PARAM_IDMUNICIPIO, idMunicipio);
		params.put(PARAM_IDPRODUCTO, combustible);
		JSONObject jsonObj = doHTTPCallAndReturnJSONResponse(
				"estaciones.terrestres.filtroMunicipioProducto", params);
		return parseJsonObject(jsonObj, EstacionDeServicioTerrestreResponseDTO.class);
	}
}
