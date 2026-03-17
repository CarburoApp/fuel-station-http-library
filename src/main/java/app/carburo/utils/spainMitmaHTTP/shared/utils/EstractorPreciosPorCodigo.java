package app.carburo.utils.spainMitmaHTTP.shared.utils;

import app.carburo.utils.spainMitmaHTTP.shared.model.PreciosCombustibleResponseDTO;

import java.util.Map;
import java.util.function.Function;

public class EstractorPreciosPorCodigo {

	private EstractorPreciosPorCodigo() {}

	public static final Map<String, Function<PreciosCombustibleResponseDTO, String>> PRECIO_EXTRACTORS = Map.ofEntries(

			// ==== GASOLINAS ====
			Map.entry("G95E5", PreciosCombustibleResponseDTO::getPrecioGasolina95E5),
			Map.entry("G95E5+",
					  PreciosCombustibleResponseDTO::getPrecioGasolina95E5Premium),
			Map.entry("G95E10", PreciosCombustibleResponseDTO::getPrecioGasolina95E10),
			Map.entry("G95E25", PreciosCombustibleResponseDTO::getPrecioGasolina95E25),
			Map.entry("G95E85", PreciosCombustibleResponseDTO::getPrecioGasolina95E85),
			Map.entry("G98E5", PreciosCombustibleResponseDTO::getPrecioGasolina98E5),
			Map.entry("G98E10", PreciosCombustibleResponseDTO::getPrecioGasolina98E10),
			Map.entry("GREN", PreciosCombustibleResponseDTO::getPrecioGasolinaRenovable),

			// ==== GASÓLEOS ====
			Map.entry("GOA", PreciosCombustibleResponseDTO::getPrecioGasoleoA),
			Map.entry("GOA+", PreciosCombustibleResponseDTO::getPrecioGasoleoPremium),
			Map.entry("GOB", PreciosCombustibleResponseDTO::getPrecioGasoleoB),
			Map.entry("DREN", PreciosCombustibleResponseDTO::getPrecioDieselRenovable),

			// ==== BIOCOMBUSTIBLES / OTROS ====
			Map.entry("BIO", PreciosCombustibleResponseDTO::getPrecioBiodiesel),
			Map.entry("BIE", PreciosCombustibleResponseDTO::getPrecioBioetanol),
			Map.entry("MET", PreciosCombustibleResponseDTO::getPrecioMetanol),
			Map.entry("AMO", PreciosCombustibleResponseDTO::getPrecioAmoniaco),

			// ==== GASES ====
			Map.entry("GLP", PreciosCombustibleResponseDTO::getPrecioGLP),
			Map.entry("GNC", PreciosCombustibleResponseDTO::getPrecioGasNaturalComprimido),
			Map.entry("GNL", PreciosCombustibleResponseDTO::getPrecioGasNaturalLicuado),
			Map.entry("BGNC",
					  PreciosCombustibleResponseDTO::getPrecioBiogasNaturalComprimido),
			Map.entry("BGNL", PreciosCombustibleResponseDTO::getPrecioBiogasNaturalLicuado),

			// ==== OTROS ====
			Map.entry("ADB", PreciosCombustibleResponseDTO::getPrecioAdblue),
			Map.entry("H2", PreciosCombustibleResponseDTO::getPrecioHidrogeno));

}
