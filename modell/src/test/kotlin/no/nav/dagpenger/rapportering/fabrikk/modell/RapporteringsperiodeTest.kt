package no.nav.dagpenger.rapportering.fabrikk.modell
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate

class RapporteringsperiodeTest {
    @Test
    fun `kan opprette rapporteringsperiode med gyldig fraOgMed-dato`() {
        val fraOgMed = LocalDate.of(2024, 1, 1)
        val rapporteringsperiode = Rapporteringsperiode(fraOgMed)

        assertEquals(fraOgMed, rapporteringsperiode.periode.fraOgMed)
        assertEquals(LocalDate.of(2024, 1, 14), rapporteringsperiode.periode.tilOgMed)
        assertEquals(14, rapporteringsperiode.dager.size)
    }

    @Test
    fun `kan ikke opprett rapporteringsperiode dersom fraOgMed ikke er en mandag`() {
        val fraOgMed = LocalDate.of(2024, 1, 2)
        assertThrows<IllegalArgumentException> {
            Rapporteringsperiode(fraOgMed)
        }
    }
}
