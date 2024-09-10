package no.nav.dagpenger.rapportering.fabrikk.mediator.tjenester

import io.mockk.mockk
import io.mockk.verify
import no.nav.dagpenger.rapportering.fabrikk.mediator.RapporteringMediator
import no.nav.helse.rapids_rivers.testsupport.TestRapid
import org.junit.jupiter.api.BeforeEach
import java.time.LocalDate

class FabrikkMottakTest {
    private val rapidConnection = TestRapid()
    private val rapporteringMediator = mockk<RapporteringMediator>(relaxed = true)

    @BeforeEach
    fun setup() {
        FabrikkMottak(rapidConnection, rapporteringMediator)
    }

    @org.junit.jupiter.api.Test
    fun `test onPacket`() {
        rapidConnection.sendTestMessage(nyRapporteringHendelse("123", "2024-01-01"))
        verify(exactly = 1) { rapporteringMediator.behandle("123", LocalDate.of(2024, 1, 1)) }
    }
}

fun nyRapporteringHendelse(
    ident: String,
    fraOgMed: String,
): String =
    //language=JSON
    """
    {
      "@event_name": "behov",
      "@behov": "ny_rapportering",
      "@behovId": "c777cdb5-0518-4cd7-b171-148c8c6401c4",
      "ident": "$ident",
      "fom": "$fraOgMed"
    } 
    """.trimIndent()
