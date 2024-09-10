package no.nav.dagpenger.rapportering.fabrikk.mediator

import io.kotest.matchers.shouldBe
import no.nav.helse.rapids_rivers.asLocalDate
import no.nav.helse.rapids_rivers.testsupport.TestRapid
import org.junit.jupiter.api.Test
import java.time.LocalDate

class RapporteringMediatorTest {
    @Test
    fun `skal opprette og publisere løsning for rapporteringsperiode`() {
        val rapidsConnection = TestRapid()
        val rapporteringMediator = RapporteringMediator(rapidsConnection)
        val ident = "12345678901"
        val fraOgMed = LocalDate.of(2024, 1, 1)

        rapporteringMediator.behandle(ident, fraOgMed)

        rapidsConnection.inspektør.apply {
            size shouldBe 1
            message(0)["ident"].asText() shouldBe ident
            message(0)["@løsning"].also { løsning ->
                løsning["periode"]["fraOgMed"].asLocalDate() shouldBe fraOgMed
                løsning["periode"]["tilOgMed"].asLocalDate() shouldBe LocalDate.of(2024, 1, 14)
                løsning["dager"].size() shouldBe 14
                løsning["status"].asText() shouldBe "TilUtfylling"
            }
        }
    }
}
