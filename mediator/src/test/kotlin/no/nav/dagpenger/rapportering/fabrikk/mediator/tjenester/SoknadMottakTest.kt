package no.nav.dagpenger.rapportering.fabrikk.mediator.tjenester

import io.mockk.mockk
import io.mockk.verify
import no.nav.dagpenger.rapportering.fabrikk.mediator.RapporteringMediator
import no.nav.helse.rapids_rivers.testsupport.TestRapid
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate

class SoknadMottakTest {
    private val testRapid = TestRapid()
    private val rapporteringMediator = mockk<RapporteringMediator>(relaxed = true)

    init {
        SoknadMottak(
            rapidsConnection = testRapid,
            rapporteringMediator = rapporteringMediator,
        )
    }

    @BeforeEach
    fun setup() {
        testRapid.reset()
    }

    @Test
    fun `vi kan motta søknader`() {
        testRapid.sendTestMessage(søknad_innsendt_event)
        verify(exactly = 1) { rapporteringMediator.behandle("12345678903", LocalDate.of(2024, 9, 1)) }
    }
}

private val søknad_innsendt_event =
    //language=json
    """
    {
      "@id": "675eb2c2-bfba-4939-926c-cf5aac73d163",
      "@event_name": "søknad_innsendt_varsel",
      "@opprettet": "2024-02-21T11:00:27.899791748",
      "søknadId": "123e4567-e89b-12d3-a456-426614174000",
      "ident": "12345678903",
      "søknadstidspunkt": "2024-09-01T11:00:27.899791748",
      "søknadData": {
        "søknad_uuid": "123e4567-e89b-12d3-a456-426614174000",
        "@opprettet": "2024-02-21T11:00:27.899791748",
        "seksjoner": [
          {
            "fakta": [
              {
                "id": "6001",
                "svar": "NOR",
                "type": "land",
                "beskrivendeId": "faktum.hvilket-land-bor-du-i"
              }
            ],
            "beskrivendeId": "bostedsland"
          }
        ]
      }
    }
    """.trimIndent()
