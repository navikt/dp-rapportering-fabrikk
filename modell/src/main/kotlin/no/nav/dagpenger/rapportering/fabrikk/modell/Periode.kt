package no.nav.dagpenger.rapportering.fabrikk.modell

import java.time.LocalDate

data class Periode(
    val fraOgMed: LocalDate,
    val tilOgMed: LocalDate = fraOgMed.plusDays(13),
) {
    init {
        require(tilOgMed.minusDays(13) == fraOgMed) {
            "Perioden må være 14 dager lang"
        }
        require(fraOgMed.isBefore(tilOgMed)) {
            "Fra og med-dato kan ikke være etter til og med-dato"
        }
//        require(fraOgMed.dayOfWeek == DayOfWeek.MONDAY) {
//            "Fra og med-dato må være en mandag"
//        }
    }
}
