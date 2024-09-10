package no.nav.dagpenger.rapportering.fabrikk.mediator

import io.kotest.matchers.shouldBe
import no.nav.helse.rapids_rivers.testsupport.TestRapid
import org.junit.jupiter.api.Test
import java.time.LocalDate

class RapporteringMediatorTest {
    @Test
    fun `skal opprette og publisere rapporteringsperiode`() {
        val rapidsConnection = TestRapid()
        val rapporteringMediator = RapporteringMediator(rapidsConnection)

        val ident = "12345678901"
        val fraOgMed = LocalDate.of(2024, 1, 1)

        rapporteringMediator.behandle(ident, fraOgMed)

        rapidsConnection.inspektør.size shouldBe 1
        rapidsConnection.inspektør.message(0).also {
            it["@event_name"].asText() shouldBe "ny_rapportering"
            it["ident"].asText() shouldBe ident
        }
    }
}
