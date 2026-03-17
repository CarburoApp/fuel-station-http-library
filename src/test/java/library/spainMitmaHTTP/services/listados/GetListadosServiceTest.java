package library.spainMitmaHTTP.services.listados;

import app.carburo.utils.spainMitmaHTTP.shared.model.CAResponseDTO;
import app.carburo.utils.spainMitmaHTTP.shared.model.MunicipioResponseDTO;
import app.carburo.utils.spainMitmaHTTP.shared.model.ProductoPetroliferoResponseDto;
import app.carburo.utils.spainMitmaHTTP.shared.model.ProvinciaResponseDTO;
import library.spainMitmaHTTP.services.SpainMitmaAPIClientAbstractTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

class GetListadosServiceTest extends SpainMitmaAPIClientAbstractTest {

	protected static final String JSON_UBI_LISTADOS = "/listados";

	@Test
	void testGetListadoProvincias() throws Exception {
		String json = Files.readString(
				Path.of(JSON_UBI_BASE + JSON_UBI_LISTADOS + "/provincias.json"));

		stubFor(get(urlEqualTo("/Listados/Provincias/")).willReturn(
				aResponse().withHeader("Content-Type", "application/json")
						.withBody(json)));

		List<ProvinciaResponseDTO> provincias = api.getListadosService()
				.getListadoProvincia();

		// ===== VALIDACIONES GENERALES =====

		Assertions.assertNotNull(provincias);
		Assertions.assertEquals(6, provincias.size());

		// ===== VALIDAR PRIMER ELEMENTO =====

		ProvinciaResponseDTO p1 = provincias.getFirst();

		Assertions.assertEquals(2, p1.getIdProvincia());
		Assertions.assertEquals(7, p1.getIdCCAA());
		Assertions.assertEquals("ALBACETE", p1.getProvincia());
		Assertions.assertEquals("Castilla la Mancha", p1.getCcaa());

		// ===== VALIDAR ELEMENTO INTERMEDIO =====

		ProvinciaResponseDTO p3 = provincias.get(2);

		Assertions.assertEquals(10, p3.getIdProvincia());
		Assertions.assertEquals("CÁCERES", p3.getProvincia());
		Assertions.assertEquals("Extremadura", p3.getCcaa());

		// ===== VALIDAR ÚLTIMO ELEMENTO =====

		ProvinciaResponseDTO last = provincias.getLast();

		Assertions.assertEquals(18, last.getIdProvincia());
		Assertions.assertEquals("GRANADA", last.getProvincia());
		Assertions.assertEquals("Andalucia", last.getCcaa());
	}

	@Test
	void testProvinciasServerError() {

		stubFor(get(urlEqualTo("/Listados/Provincias/")).willReturn(
				aResponse().withStatus(500)));

		Assertions.assertThrows(Exception.class,
								() -> api.getListadosService().getListadoProvincia());
	}

	@Test
	void testProvinciasJsonInvalido() {

		stubFor(get(urlEqualTo("/Listados/Provincias/")).willReturn(
				aResponse().withHeader("Content-Type", "application/json")
						.withBody("{esto no es json}")));

		Assertions.assertThrows(Exception.class,
								() -> api.getListadosService().getListadoProvincia());
	}

	@Test
	void testGetListadoMunicipios() throws Exception {

		String json = Files.readString(
				Path.of(JSON_UBI_BASE + JSON_UBI_LISTADOS + "/municipios.json"));

		stubFor(get(urlEqualTo("/Listados/Municipios/")).willReturn(
				aResponse().withHeader("Content-Type", "application/json")
						.withBody(json)));

		List<MunicipioResponseDTO> municipios = api.getListadosService()
				.getListadoMunicipio();

		// ===== VALIDACIONES GENERALES =====

		Assertions.assertNotNull(municipios);
		Assertions.assertEquals(9, municipios.size());

		// ===== VALIDAR PRIMER ELEMENTO =====

		MunicipioResponseDTO m1 = municipios.getFirst();

		Assertions.assertEquals(1, m1.getIdMunicipio());
		Assertions.assertEquals(1, m1.getIdProvincia());
		Assertions.assertEquals(16, m1.getIdComunidadAutonoma());
		Assertions.assertEquals("Alegría-Dulantzi", m1.getMunicipio());
		Assertions.assertEquals("ARABA/ÁLAVA", m1.getProvincia());
		Assertions.assertEquals("País Vasco", m1.getComunidadAutonoma());

		// ===== VALIDAR ELEMENTO INTERMEDIO =====

		MunicipioResponseDTO m4 = municipios.get(3);

		Assertions.assertEquals(1798, m4.getIdMunicipio());
		Assertions.assertEquals("Tarifa", m4.getMunicipio());
		Assertions.assertEquals("CÁDIZ", m4.getProvincia());
		Assertions.assertEquals("Andalucia", m4.getComunidadAutonoma());

		// ===== VALIDAR ÚLTIMO ELEMENTO =====

		MunicipioResponseDTO last = municipios.getLast();

		Assertions.assertEquals(8112, last.getIdMunicipio());
		Assertions.assertEquals(38, last.getIdProvincia());
		Assertions.assertEquals(5, last.getIdComunidadAutonoma());
		Assertions.assertEquals("Pinar de El Hierro, El", last.getMunicipio());
		Assertions.assertEquals("SANTA CRUZ DE TENERIFE", last.getProvincia());
		Assertions.assertEquals("Canarias", last.getComunidadAutonoma());
	}

	@Test
	void testMunicipiosNotFound() {

		stubFor(get(urlEqualTo("/Listados/Municipios/")).willReturn(
				aResponse().withStatus(404)));

		Assertions.assertThrows(Exception.class,
								() -> api.getListadosService().getListadoMunicipio());
	}

	@Test
	void testGetListadoProductos() throws Exception {

		String json = Files.readString(
				Path.of(JSON_UBI_BASE + JSON_UBI_LISTADOS + "/productos.json"));

		stubFor(get(urlEqualTo("/Listados/ProductosPetroliferos/")).willReturn(
				aResponse().withHeader("Content-Type", "application/json")
						.withBody(json)));

		List<ProductoPetroliferoResponseDto> productos = api.getListadosService()
				.getListadoProductoPetrolifero();

		// ===== VALIDACIONES GENERALES =====

		Assertions.assertNotNull(productos);
		Assertions.assertEquals(30, productos.size());

		// ===== VALIDAR PRIMER ELEMENTO =====

		ProductoPetroliferoResponseDto p1 = productos.getFirst();

		Assertions.assertEquals(1, p1.getIdProducto());
		Assertions.assertEquals("Gasolina 95 E5", p1.getNombreProducto());
		Assertions.assertEquals("G95E5", p1.getAbreviaturaNombreProducto());

		// ===== VALIDAR ELEMENTO INTERMEDIO =====

		ProductoPetroliferoResponseDto p10 = productos.get(8);

		Assertions.assertEquals(5, p10.getIdProducto());
		Assertions.assertEquals("Gasóleo Premium", p10.getNombreProducto());
		Assertions.assertEquals("GOA+", p10.getAbreviaturaNombreProducto());

		// ===== VALIDAR ÚLTIMO ELEMENTO =====

		ProductoPetroliferoResponseDto last = productos.getLast();

		Assertions.assertEquals(32, last.getIdProducto());
		Assertions.assertEquals("Biogas natural licuado", last.getNombreProducto());
		Assertions.assertEquals("BGNL", last.getAbreviaturaNombreProducto());
	}

	@Test
	void testProductosListaVacia() throws Exception {

		stubFor(get(urlEqualTo("/Listados/ProductosPetroliferos/")).willReturn(
				aResponse().withHeader("Content-Type", "application/json")
						.withBody("[]")));

		List<ProductoPetroliferoResponseDto> productos = api.getListadosService()
				.getListadoProductoPetrolifero();

		Assertions.assertNotNull(productos);
		Assertions.assertTrue(productos.isEmpty());
	}

	@Test
	void testGetListadoCCAA() throws Exception {

		String json = Files.readString(
				Path.of(JSON_UBI_BASE + JSON_UBI_LISTADOS + "/ccaa.json"));

		stubFor(get(urlEqualTo("/Listados/ComunidadesAutonomas/")).willReturn(
				aResponse().withHeader("Content-Type", "application/json")
						.withBody(json)));

		List<CAResponseDTO> comunidades = api.getListadosService().getListadoCCAA();

		// ===== VALIDACIONES GENERALES =====

		Assertions.assertNotNull(comunidades);
		Assertions.assertEquals(19, comunidades.size());

		// ===== VALIDAR PRIMER ELEMENTO =====

		CAResponseDTO c1 = comunidades.getFirst();

		Assertions.assertEquals(1, c1.getIdCA());
		Assertions.assertEquals("Andalucia", c1.getCa());

		// ===== VALIDAR ELEMENTO INTERMEDIO =====

		CAResponseDTO c10 = comunidades.get(9);

		Assertions.assertEquals(10, c10.getIdCA());
		Assertions.assertEquals("Comunidad Valenciana", c10.getCa());

		// ===== VALIDAR ÚLTIMO ELEMENTO =====

		CAResponseDTO last = comunidades.getLast();

		Assertions.assertEquals(19, last.getIdCA());
		Assertions.assertEquals("Melilla", last.getCa());
	}
}
