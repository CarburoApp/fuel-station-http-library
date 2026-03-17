package library.spainMitmaHTTP.services.estacionesTerrestresHistorico;

import app.carburo.utils.spainMitmaHTTP.shared.exceptions.SpainMitmaAPIClientException;
import app.carburo.utils.spainMitmaHTTP.shared.model.EstacionDeServicioResponseDTO;
import app.carburo.utils.spainMitmaHTTP.shared.model.EstacionDeServicioTerrestreResponseDTO;
import library.spainMitmaHTTP.services.SpainMitmaAPIClientAbstractTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

class GetEESSTerrestresHistoricoServiceTest extends SpainMitmaAPIClientAbstractTest {

	private static final String JSON_BASE = "/estacionesTerrestresHist/eessTerrestres.json";
	private static final String JSON_PRODUCTO = "/estacionesTerrestresHist/eessTerrestresFiltroProducto.json";

	private final LocalDate testFecha = LocalDate.of(2022, 5, 1);

	// =========================
	// TEST PRINCIPAL (sin filtro)
	// =========================
	@Test
	void testGetListadoEESSTerrestresHistCompleto() throws Exception {
		stubJson("/EstacionesTerrestresHist/01-05-2022", JSON_BASE);

		EstacionDeServicioTerrestreResponseDTO res = api.getEEESSTerrestresHistoricoService()
				.getListadoEstacionDeServicioTerrestreHist(testFecha);

		validateBaseResponse(res);
		validateBaseStation(res.getListaEESS().getFirst());
		validateIntermediateStation(res.getListaEESS().get(3));
		validateLastStation(res.getListaEESS().getLast());
		validateFecha(res.getFecha(), "01/05/2022 0:00:00");
	}

	// =========================
	// FILTROS HISTÓRICOS
	// =========================
	@Test
	void testFiltroCCAAHist() throws Exception {
		stubJson("/EstacionesTerrestresHist/FiltroCCAA/01-05-2022/19", JSON_BASE);

		EstacionDeServicioTerrestreResponseDTO res = api.getEEESSTerrestresHistoricoService()
				.getListadoEstacionDeServicioTerrestreHistByCCAA(testFecha, 19);

		validateBaseResponse(res);
		validateBaseStation(res.getListaEESS().getFirst());
	}

	@Test
	void testFiltroProvinciaHist() throws Exception {
		stubJson("/EstacionesTerrestresHist/FiltroProvincia/01-05-2022/52", JSON_BASE);

		EstacionDeServicioTerrestreResponseDTO res = api.getEEESSTerrestresHistoricoService()
				.getListadoEstacionDeServicioTerrestreHistByProvincia(testFecha, 52);

		validateBaseResponse(res);
		validateBaseStation(res.getListaEESS().getFirst());
	}

	@Test
	void testFiltroMunicipioHist() throws Exception {
		stubJson("/EstacionesTerrestresHist/FiltroMunicipio/01-05-2022/8111", JSON_BASE);

		EstacionDeServicioTerrestreResponseDTO res = api.getEEESSTerrestresHistoricoService()
				.getListadoEstacionDeServicioTerrestreHistByMunicipio(testFecha, 8111);

		validateBaseResponse(res);
		validateBaseStation(res.getListaEESS().getFirst());
	}

	// =========================
	// FILTROS PRODUCTO HISTÓRICO
	// =========================
	@Test
	void testFiltroProductoHist() throws Exception {
		stubJson("/EstacionesTerrestresHist/FiltroProducto/01-05-2022/1", JSON_PRODUCTO);

		EstacionDeServicioTerrestreResponseDTO res = api.getEEESSTerrestresHistoricoService()
				.getListadoEstacionDeServicioTerrestreHistByProducto(testFecha, 1);

		validateProductoResponse(res);
	}

	@Test
	void testFiltroProvinciaProductoHist() throws Exception {
		stubJson("/EstacionesTerrestresHist/FiltroProvinciaProducto/01-05-2022/52/1", JSON_PRODUCTO);

		EstacionDeServicioTerrestreResponseDTO res = api.getEEESSTerrestresHistoricoService()
				.getListadoEstacionDeServicioTerrestreHistByProvinciaAndProducto(testFecha, 52, 1);

		validateProductoResponse(res);
	}

	@Test
	void testFiltroCCAAProductoHist() throws Exception {
		stubJson("/EstacionesTerrestresHist/FiltroCCAAProducto/01-05-2022/19/1", JSON_PRODUCTO);

		EstacionDeServicioTerrestreResponseDTO res = api.getEEESSTerrestresHistoricoService()
				.getListadoEstacionDeServicioTerrestreHistByCCAAAndProducto(testFecha, 19, 1);

		validateProductoResponse(res);
	}

	@Test
	void testFiltroMunicipioProductoHist() throws Exception {
		stubJson("/EstacionesTerrestresHist/FiltroMunicipioProducto/01-05-2022/8111/1", JSON_PRODUCTO);

		EstacionDeServicioTerrestreResponseDTO res = api.getEEESSTerrestresHistoricoService()
				.getListadoEstacionDeServicioTerrestreHistByMunicipioAndProducto(testFecha, 8111, 1);

		validateProductoResponse(res);
	}

	// =========================
	// ERRORES
	// =========================
	@Test
	void testJsonInvalidoHist() {
		stubFor(get(urlEqualTo("/EstacionesTerrestresHist/01-05-2022")).willReturn(
				aResponse().withHeader("Content-Type", "application/json")
						.withBody("json_invalido")));

		Assertions.assertThrows(SpainMitmaAPIClientException.class,
								() -> api.getEEESSTerrestresHistoricoService()
										.getListadoEstacionDeServicioTerrestreHist(testFecha));
	}

	@Test
	void testErrorServidorHist() {
		stubFor(get(urlEqualTo("/EstacionesTerrestresHist/01-05-2022")).willReturn(
				aResponse().withStatus(500)));

		Assertions.assertThrows(SpainMitmaAPIClientException.class,
								() -> api.getEEESSTerrestresHistoricoService()
										.getListadoEstacionDeServicioTerrestreHist(testFecha));
	}

	// =========================
	// MÉTODOS PRIVADOS
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
		Assertions.assertEquals("1,448", e.getPrecios().getPrecio());
	}

	private void validateBaseStation(EstacionDeServicioResponseDTO e) {
		Assertions.assertEquals(10325, e.getIdeess());
		Assertions.assertEquals("SHELL", e.getRotulo());
		Assertions.assertEquals("ALFEREZ PROVISIONALES S/N", e.getDireccion());
		Assertions.assertEquals("L-S: 07:00-23:00", e.getHorario());
		Assertions.assertEquals("MELILLA", e.getLocalidad().trim());
		Assertions.assertEquals("Melilla", e.getMunicipio().trim());
		Assertions.assertEquals("MELILLA", e.getProvincia().trim());
		Assertions.assertEquals(52001, e.getCp());
		Assertions.assertEquals(8111, e.getIdMunicipio());
		Assertions.assertEquals(52, e.getIdProvincia());
		Assertions.assertEquals(19, e.getIdCCAA());
		Assertions.assertEquals("35,290472", e.getLatitud());
		Assertions.assertEquals("-2,946306", e.getLongitud());
		Assertions.assertNotNull(e.getMargen());
		Assertions.assertNotNull(e.getRemision());
		Assertions.assertNotNull(e.getTipoVenta());
	}

	private void validateIntermediateStation(EstacionDeServicioResponseDTO e) {
		Assertions.assertEquals("SHELL PUENTE TRIANA".trim(), e.getRotulo().trim());
		Assertions.assertEquals("Melilla".trim(), e.getMunicipio().trim());
		Assertions.assertEquals(8111, e.getIdMunicipio());
	}

	private void validateLastStation(EstacionDeServicioResponseDTO e) {
		Assertions.assertEquals(10324, e.getIdeess());
		Assertions.assertEquals("SHELL", e.getRotulo());
		Assertions.assertEquals("GENERAL POLAVIEJA Nº 2", e.getDireccion());
		Assertions.assertEquals("Melilla", e.getMunicipio());
	}

	private void validateFecha(String fechaRespuesta, String fechaEsperada) {
		Assertions.assertEquals(fechaEsperada, fechaRespuesta);
	}
}