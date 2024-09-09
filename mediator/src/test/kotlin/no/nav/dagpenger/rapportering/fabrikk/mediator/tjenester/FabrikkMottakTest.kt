package no.nav.dagpenger.rapportering.fabrikk.mediator.tjenester

import io.mockk.mockk
import io.mockk.verify
import no.nav.dagpenger.rapportering.fabrikk.mediator.Mediator
import no.nav.helse.rapids_rivers.testsupport.TestRapid
import org.junit.jupiter.api.BeforeEach

class FabrikkMottakTest {
    private val rapidConnection = TestRapid()
    private val mediator = mockk<Mediator>(relaxed = true)

    @BeforeEach
    fun setup() {
        FabrikkMottak(rapidConnection, mediator)
    }

    @org.junit.jupiter.api.Test
    fun `test onPacket`() {
        rapidConnection.sendTestMessage(nyRapporteringHendelse("123"))
        verify(exactly = 1) { mediator.behandle("123") }
    }
}

fun nyRapporteringHendelse(ident: String): String =
    //language=JSON
    """
    {
      "ident": "$ident",
      "@event_name": "ny_rapportering"
    } 
    """.trimIndent()
