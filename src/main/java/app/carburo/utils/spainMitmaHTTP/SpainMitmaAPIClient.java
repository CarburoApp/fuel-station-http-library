package app.carburo.utils.spainMitmaHTTP;

import app.carburo.utils.spainMitmaHTTP.services.eessTerrestres.GetEESSTerrestresService;
import app.carburo.utils.spainMitmaHTTP.services.eessTerrestresHistorico.GetEEESSTerrestresHistoricoService;
import app.carburo.utils.spainMitmaHTTP.services.listados.GetListadosService;
import app.carburo.utils.spainMitmaHTTP.services.postesMaritimos.GetPostesMaritimosService;

public class SpainMitmaAPIClient {

	/**
	 * Obtiene el servicio para estaciones terrestres actuales.
	 * @return instancia de GetEESSTerrestresService.
	 */
	public GetEESSTerrestresService getEEESSTerrestresService() {
		return new GetEESSTerrestresService();
	}

	/**
	 * Obtiene el servicio para estaciones terrestres históricas.
	 * @return instancia de GetEEESSTerrestresHistoricoService.
	 */
	public GetEEESSTerrestresHistoricoService getEEESSTerrestresHistoricoService() {
		return new GetEEESSTerrestresHistoricoService();
	}

	/**
	 * Obtiene el servicio de listados de CCAA, provincias, municipios y productos.
	 * @return instancia de GetListadosService.
	 */
	public GetListadosService getListadosService() {
		return new GetListadosService();
	}

	/**
	 * Obtiene el servicio para postes marítimos.
	 * @return instancia de GetPostesMaritimosService.
	 */
	public GetPostesMaritimosService getPostesMaritimosService() {
		return new GetPostesMaritimosService();
	}
}
