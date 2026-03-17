package app.carburo.utils.spainMitmaHTTP.services.eessTerrestresHistorico;

import app.carburo.utils.spainMitmaHTTP.services.SpainMitmaAPIClientAbstractService;
import app.carburo.utils.spainMitmaHTTP.shared.exceptions.SpainMitmaAPIClientException;
import app.carburo.utils.spainMitmaHTTP.shared.model.EstacionDeServicioTerrestreResponseDTO;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class GetEEESSTerrestresHistoricoService
		extends SpainMitmaAPIClientAbstractService {

	// ============================
	// ESTACIONES TERRESTRES (HISTÓRICAS)
	// ============================

	/**
	 * Obtiene todas las estaciones registradas en una fecha histórica
	 */
	public EstacionDeServicioTerrestreResponseDTO getListadoEstacionDeServicioTerrestreHist(
			LocalDate fecha) throws SpainMitmaAPIClientException {
		Map<String, Object> params = new HashMap<>();
		params.put(PARAM_FECHA, formatDate(fecha));
		JSONObject jsonObj = doHTTPCallAndReturnJSONResponse("estaciones.terrestres.hist",
															 params);
		return parseJsonObject(jsonObj, EstacionDeServicioTerrestreResponseDTO.class);
	}

	/**
	 * Filtra estaciones históricas por CCAA y fecha
	 */
	public EstacionDeServicioTerrestreResponseDTO getListadoEstacionDeServicioTerrestreHistByCCAA(
			LocalDate fecha, int idCCAA) throws SpainMitmaAPIClientException {
		Map<String, Object> params = new HashMap<>();
		params.put(PARAM_FECHA, formatDate(fecha));
		params.put(PARAM_IDCCAA, idCCAA);
		JSONObject jsonObj = doHTTPCallAndReturnJSONResponse(
				"estaciones.terrestres.hist.filtroCCAA", params);
		return parseJsonObject(jsonObj, EstacionDeServicioTerrestreResponseDTO.class);
	}

	/**
	 * Filtra estaciones históricas por provincia y fecha
	 */
	public EstacionDeServicioTerrestreResponseDTO getListadoEstacionDeServicioTerrestreHistByProvincia(
			LocalDate fecha, int idProvincia) throws SpainMitmaAPIClientException {
		Map<String, Object> params = new HashMap<>();
		params.put(PARAM_FECHA, formatDate(fecha));
		params.put(PARAM_IDPROVINCIA, idProvincia);
		JSONObject jsonObj = doHTTPCallAndReturnJSONResponse(
				"estaciones.terrestres.hist.filtroProvincia", params);
		return parseJsonObject(jsonObj, EstacionDeServicioTerrestreResponseDTO.class);
	}

	/**
	 * Filtra estaciones históricas por municipio y fecha
	 */
	public EstacionDeServicioTerrestreResponseDTO getListadoEstacionDeServicioTerrestreHistByMunicipio(
			LocalDate fecha, int idMunicipio) throws SpainMitmaAPIClientException {
		Map<String, Object> params = new HashMap<>();
		params.put(PARAM_FECHA, formatDate(fecha));
		params.put(PARAM_IDMUNICIPIO, idMunicipio);
		JSONObject jsonObj = doHTTPCallAndReturnJSONResponse(
				"estaciones.terrestres.hist.filtroMunicipio", params);
		return parseJsonObject(jsonObj, EstacionDeServicioTerrestreResponseDTO.class);
	}

	/**
	 * Filtra estaciones históricas por tipo de combustible y fecha
	 */
	public EstacionDeServicioTerrestreResponseDTO getListadoEstacionDeServicioTerrestreHistByProducto(
			LocalDate fecha, int combustible) throws SpainMitmaAPIClientException {
		Map<String, Object> params = new HashMap<>();
		params.put(PARAM_FECHA, formatDate(fecha));
		params.put(PARAM_IDPRODUCTO, combustible);
		JSONObject jsonObj = doHTTPCallAndReturnJSONResponse(
				"estaciones.terrestres.hist.filtroProducto", params);
		return parseJsonObject(jsonObj, EstacionDeServicioTerrestreResponseDTO.class);
	}

	/**
	 * Filtra estaciones históricas por provincia, producto y fecha
	 */
	public EstacionDeServicioTerrestreResponseDTO getListadoEstacionDeServicioTerrestreHistByProvinciaAndProducto(
			LocalDate fecha, int idProvincia, int combustible)
			throws SpainMitmaAPIClientException {
		Map<String, Object> params = new HashMap<>();
		params.put(PARAM_FECHA, formatDate(fecha));
		params.put(PARAM_IDPROVINCIA, idProvincia);
		params.put(PARAM_IDPRODUCTO, combustible);
		JSONObject jsonObj = doHTTPCallAndReturnJSONResponse(
				"estaciones.terrestres.hist.filtroProvinciaProducto", params);
		return parseJsonObject(jsonObj, EstacionDeServicioTerrestreResponseDTO.class);
	}

	/**
	 * Filtra estaciones históricas por CCAA, producto y fecha
	 */
	public EstacionDeServicioTerrestreResponseDTO getListadoEstacionDeServicioTerrestreHistByCCAAAndProducto(
			LocalDate fecha, int idCCAA, int combustible)
			throws SpainMitmaAPIClientException {
		Map<String, Object> params = new HashMap<>();
		params.put(PARAM_FECHA, formatDate(fecha));
		params.put(PARAM_IDCCAA, idCCAA);
		params.put(PARAM_IDPRODUCTO, combustible);
		JSONObject jsonObj = doHTTPCallAndReturnJSONResponse(
				"estaciones.terrestres.hist.filtroCCAAProducto", params);
		return parseJsonObject(jsonObj, EstacionDeServicioTerrestreResponseDTO.class);
	}

	/**
	 * Filtra estaciones históricas por municipio, producto y fecha
	 */
	public EstacionDeServicioTerrestreResponseDTO getListadoEstacionDeServicioTerrestreHistByMunicipioAndProducto(
			LocalDate fecha, int idMunicipio, int combustible)
			throws SpainMitmaAPIClientException {
		Map<String, Object> params = new HashMap<>();
		params.put(PARAM_FECHA, formatDate(fecha));
		params.put(PARAM_IDMUNICIPIO, idMunicipio);
		params.put(PARAM_IDPRODUCTO, combustible);
		JSONObject jsonObj = doHTTPCallAndReturnJSONResponse(
				"estaciones.terrestres.hist.filtroMunicipioProducto", params);
		return parseJsonObject(jsonObj, EstacionDeServicioTerrestreResponseDTO.class);
	}


}
