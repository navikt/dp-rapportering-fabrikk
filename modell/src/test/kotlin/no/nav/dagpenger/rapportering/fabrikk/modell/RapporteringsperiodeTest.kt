package no.nav.dagpenger.rapportering.fabrikk.modell
import io.kotest.matchers.shouldBe
import no.nav.dagpenger.rapportering.fabrikk.helpers.januar
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class RapporteringsperiodeTest {
    @Test
    fun `kan opprette rapporteringsperiode med gyldig fraOgMed-dato`() {
        val fraOgMed = 1.januar
        val rapporteringsperiode = Rapporteringsperiode(fraOgMed)

        rapporteringsperiode.periode.fraOgMed shouldBe fraOgMed
        rapporteringsperiode.periode.tilOgMed shouldBe 14.januar
        rapporteringsperiode.dager.size shouldBe 14
    }

    @Test
    @Disabled
    fun `kan ikke opprett rapporteringsperiode dersom fraOgMed ikke er en mandag`() {
        val fraOgMed = 2.januar
        assertThrows<IllegalArgumentException> {
            Rapporteringsperiode(fraOgMed)
        }
    }
}
