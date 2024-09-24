package no.nav.dagpenger.rapportering.fabrikk.mediator.tjenester

import com.github.navikt.tbd_libs.rapids_and_rivers.test_support.TestRapid
import io.micrometer.core.instrument.Clock
import io.micrometer.prometheusmetrics.PrometheusConfig
import io.micrometer.prometheusmetrics.PrometheusMeterRegistry
import io.mockk.mockk
import io.mockk.verify
import io.prometheus.metrics.model.registry.PrometheusRegistry
import no.nav.dagpenger.rapportering.fabrikk.mediator.RapporteringMediator
import no.nav.dagpenger.rapportering.fabrikk.mediator.metrikker.RapporteringMetrikker
import org.junit.jupiter.api.BeforeEach
import java.time.LocalDate

class FabrikkMottakTest {
    private val rapidConnection = TestRapid()
    val rapporteringMetrikker =
        RapporteringMetrikker(PrometheusMeterRegistry(PrometheusConfig.DEFAULT, PrometheusRegistry.defaultRegistry, Clock.SYSTEM))
    private val rapporteringMediator = mockk<RapporteringMediator>(relaxed = true)

    @BeforeEach
    fun setup() {
        FabrikkMottak(rapidConnection, rapporteringMediator, rapporteringMetrikker)
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
      "fraOgMed": "$fraOgMed"
    } 
    """.trimIndent()
