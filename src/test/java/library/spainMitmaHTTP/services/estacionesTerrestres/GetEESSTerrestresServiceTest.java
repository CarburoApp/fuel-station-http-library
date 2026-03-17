package library.spainMitmaHTTP.services.estacionesTerrestres;

import app.carburo.utils.spainMitmaHTTP.shared.exceptions.SpainMitmaAPIClientException;
import app.carburo.utils.spainMitmaHTTP.shared.model.EstacionDeServicioResponseDTO;
import app.carburo.utils.spainMitmaHTTP.shared.model.EstacionDeServicioTerrestreResponseDTO;
import library.spainMitmaHTTP.services.SpainMitmaAPIClientAbstractTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

class GetEESSTerrestresServiceTest extends SpainMitmaAPIClientAbstractTest {

	private static final String JSON_BASE = "/estacionesTerrestres/eessTerrestres.json";
	private static final String JSON_PRODUCTO = "/estacionesTerrestres/eessTerrestresFiltroProducto.json";

	// =========================
	// TEST PRINCIPAL (sin filtro)
	// =========================

	@Test
	void testGetListadoEESSTerrestresCompleto() throws Exception {

		stubJson("/EstacionesTerrestres/", JSON_BASE);

		EstacionDeServicioTerrestreResponseDTO res = api.getEEESSTerrestresService()
				.getListadoEstacionDeServicioTerrestre();

		validateBaseResponse(res);
		validateBaseStation(res.getListaEESS().getFirst());
		validateIntermediateStation(res.getListaEESS().get(4));
		validateLastStation(res.getListaEESS().getLast());
	}

	// =========================
	// FILTROS
	// =========================

	@Test
	void testFiltroCCAA() throws Exception {

		stubJson("/EstacionesTerrestres/FiltroCCAA/19", JSON_BASE);

		EstacionDeServicioTerrestreResponseDTO res = api.getEEESSTerrestresService()
				.getListadoEstacionDeServicioTerrestreByCCAA(19);

		validateBaseResponse(res);
		validateBaseStation(res.getListaEESS().getFirst());
	}

	@Test
	void testFiltroProvincia() throws Exception {

		stubJson("/EstacionesTerrestres/FiltroProvincia/52", JSON_BASE);

		EstacionDeServicioTerrestreResponseDTO res = api.getEEESSTerrestresService()
				.getListadoEstacionDeServicioTerrestreByProvincia(52);

		validateBaseResponse(res);
		validateBaseStation(res.getListaEESS().getFirst());
	}

	@Test
	void testFiltroMunicipio() throws Exception {

		stubJson("/EstacionesTerrestres/FiltroMunicipio/8111", JSON_BASE);

		EstacionDeServicioTerrestreResponseDTO res = api.getEEESSTerrestresService()
				.getListadoEstacionDeServicioTerrestreByMunicipio(8111);

		validateBaseResponse(res);
		validateBaseStation(res.getListaEESS().getFirst());
	}

	// =========================
	// FILTRO PRODUCTO
	// =========================

	@Test
	void testFiltroProducto() throws Exception {

		stubJson("/EstacionesTerrestres/FiltroProducto/1", JSON_PRODUCTO);

		EstacionDeServicioTerrestreResponseDTO res = api.getEEESSTerrestresService()
				.getListadoEstacionDeServicioTerrestreByProducto(1);

		validateProductoResponse(res);
	}

	@Test
	void testFiltroProvinciaProducto() throws Exception {

		stubJson("/EstacionesTerrestres/FiltroProvinciaProducto/52/1", JSON_PRODUCTO);

		EstacionDeServicioTerrestreResponseDTO res = api.getEEESSTerrestresService()
				.getListadoEstacionDeServicioTerrestreByProvinciaAndProducto(52, 1);

		validateProductoResponse(res);
	}

	@Test
	void testFiltroCCAAProducto() throws Exception {

		stubJson("/EstacionesTerrestres/FiltroCCAAProducto/19/1", JSON_PRODUCTO);

		EstacionDeServicioTerrestreResponseDTO res = api.getEEESSTerrestresService()
				.getListadoEstacionDeServicioTerrestreByCCAAAndProducto(19, 1);

		validateProductoResponse(res);
	}

	@Test
	void testFiltroMunicipioProducto() throws Exception {

		stubJson("/EstacionesTerrestres/FiltroMunicipioProducto/8111/1", JSON_PRODUCTO);

		EstacionDeServicioTerrestreResponseDTO res = api.getEEESSTerrestresService()
				.getListadoEstacionDeServicioTerrestreByMunicipioAndProducto(8111, 1);

		validateProductoResponse(res);
	}

	// =========================
	// ERRORES
	// =========================

	@Test
	void testJsonInvalido() {

		stubFor(get(urlEqualTo("/EstacionesTerrestres/")).willReturn(
				aResponse().withHeader("Content-Type", "application/json")
						.withBody("json_invalido")));

		Assertions.assertThrows(SpainMitmaAPIClientException.class,
								() -> api.getEEESSTerrestresService()
										.getListadoEstacionDeServicioTerrestre());
	}

	@Test
	void testErrorServidor() {

		stubFor(get(urlEqualTo("/EstacionesTerrestres/")).willReturn(
				aResponse().withStatus(500)));

		Assertions.assertThrows(SpainMitmaAPIClientException.class,
								() -> api.getEEESSTerrestresService()
										.getListadoEstacionDeServicioTerrestre());
	}

	// =========================
	// METODOS PRIVADOS
	// =========================

	private void stubJson(String endpoint, String jsonFile) throws Exception {

		String json = Files.readString(Path.of(JSON_UBI_BASE + jsonFile));

		stubFor(get(urlEqualTo(endpoint)).willReturn(
				aResponse().withHeader("Content-Type", "application/json")
						.withBody(json)));
	}

	private void validateBaseResponse(EstacionDeServicioTerrestreResponseDTO res) {

		Assertions.assertNotNull(res);
		Assertions.assertNotNull(res.getFecha());
		Assertions.assertNotNull(res.getNota());
		Assertions.assertEquals("OK", res.getResultadoConsulta());

		Assertions.assertNotNull(res.getListaEESS());
		Assertions.assertFalse(res.getListaEESS().isEmpty());
	}

	private void validateProductoResponse(EstacionDeServicioTerrestreResponseDTO res) {

		validateBaseResponse(res);

		EstacionDeServicioResponseDTO e = res.getListaEESS().getFirst();

		Assertions.assertEquals(10325, e.getIdeess());
		Assertions.assertEquals("SHELL", e.getRotulo());

		Assertions.assertNotNull(e.getPrecios());
		System.out.println(e.getPrecios());
		Assertions.assertEquals("1,278", e.getPrecios().getPrecio());
	}

	private void validateBaseStation(EstacionDeServicioResponseDTO e) {

		Assertions.assertEquals(10325, e.getIdeess());

		Assertions.assertEquals("SHELL", e.getRotulo());
		Assertions.assertEquals("ALFEREZ PROVISIONALES S/N", e.getDireccion());
		Assertions.assertEquals("L-S: 07:00-23:00", e.getHorario());

		Assertions.assertEquals("MELILLA", e.getLocalidad());
		Assertions.assertEquals("Melilla", e.getMunicipio());
		Assertions.assertEquals("MELILLA", e.getProvincia());

		Assertions.assertEquals(52001, e.getCp());

		Assertions.assertEquals(8111, e.getIdMunicipio());
		Assertions.assertEquals(52, e.getIdProvincia());
		Assertions.assertEquals(19, e.getIdCCAA());

		Assertions.assertEquals("35,290472", e.getLatitud());
		Assertions.assertEquals("-2,946306", e.getLongitud());

		Assertions.assertNotNull(e.getMargen());
		Assertions.assertNotNull(e.getRemision());
		Assertions.assertNotNull(e.getTipoVenta());

		Assertions.assertEquals("0,0", e.getBioEtanol());
		Assertions.assertEquals("0,0", e.getEsterMetilico());

		Assertions.assertNotNull(e.getPrecios());

		Assertions.assertEquals("1,198", e.getPrecios().getPrecioGasoleoA());
		Assertions.assertEquals("1,238", e.getPrecios().getPrecioGasoleoPremium());
		Assertions.assertEquals("1,278", e.getPrecios().getPrecioGasolina95E5());
	}

	private void validateIntermediateStation(EstacionDeServicioResponseDTO e) {

		Assertions.assertEquals("SHELL PUENTE TRIANA", e.getRotulo());
		Assertions.assertEquals("Melilla", e.getMunicipio());
		Assertions.assertEquals(8111, e.getIdMunicipio());
	}

	private void validateLastStation(EstacionDeServicioResponseDTO e) {

		Assertions.assertEquals(10324, e.getIdeess());
		Assertions.assertEquals("SHELL", e.getRotulo());
		Assertions.assertEquals("GENERAL POLAVIEJA Nº 2", e.getDireccion());
		Assertions.assertEquals("Melilla", e.getMunicipio());
	}

}