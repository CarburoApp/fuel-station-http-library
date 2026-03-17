package app.carburo.utils.spainMitmaHTTP;

import app.carburo.utils.spainMitmaHTTP.services.eessTerrestres.GetEESSTerrestresService;
import app.carburo.utils.spainMitmaHTTP.services.eessTerrestresHistorico.GetEESSTerrestresHistoricoService;
import app.carburo.utils.spainMitmaHTTP.services.listados.GetListadosService;
import app.carburo.utils.spainMitmaHTTP.services.postesMaritimos.GetPostesMaritimosService;

public class SpainMitmaAPIClient {

	/**
	 * Obtiene el servicio para estaciones terrestres actuales.
	 * @return instancia de GetEESSTerrestresService.
	 */
	public GetEESSTerrestresService getEESSTerrestresService() {
		return new GetEESSTerrestresService();
	}

	/**
	 * Obtiene el servicio para estaciones terrestres históricas.
	 * @return instancia de GetEESSTerrestresHistoricoService.
	 */
	public GetEESSTerrestresHistoricoService getEESSTerrestresHistoricoService() {
		return new GetEESSTerrestresHistoricoService();
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
