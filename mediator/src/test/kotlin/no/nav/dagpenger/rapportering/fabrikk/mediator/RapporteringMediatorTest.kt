package no.nav.dagpenger.rapportering.fabrikk.mediator

import io.kotest.matchers.shouldBe
import no.nav.dagpenger.rapportering.fabrikk.helpers.januar
import no.nav.helse.rapids_rivers.asLocalDate
import no.nav.helse.rapids_rivers.testsupport.TestRapid
import org.junit.jupiter.api.Test

class RapporteringMediatorTest {
    @Test
    fun `skal opprette og publisere løsning for rapporteringsperiode`() {
        val rapidsConnection = TestRapid()
        val rapporteringMediator = RapporteringMediator(rapidsConnection)
        val ident = "12345678901"
        val fraOgMed = 1.januar
        val tilOgMed = 14.januar

        rapporteringMediator.behandle(ident, fraOgMed)

        rapidsConnection.inspektør.apply {
            size shouldBe 1
            message(0)["ident"].asText() shouldBe ident
            message(0)["@løsning"].also { løsning ->
                løsning["periode"]["fraOgMed"].asLocalDate() shouldBe fraOgMed
                løsning["periode"]["tilOgMed"].asLocalDate() shouldBe tilOgMed
                løsning["dager"].size() shouldBe 14
                løsning["status"].asText() shouldBe "TilUtfylling"
            }
        }
    }
}
