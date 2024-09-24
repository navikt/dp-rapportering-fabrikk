package no.nav.dagpenger.rapportering.fabrikk.mediator

import com.github.navikt.tbd_libs.rapids_and_rivers.asLocalDate
import com.github.navikt.tbd_libs.rapids_and_rivers.test_support.TestRapid
import io.kotest.matchers.shouldBe
import io.micrometer.core.instrument.Clock
import io.micrometer.prometheusmetrics.PrometheusConfig
import io.micrometer.prometheusmetrics.PrometheusMeterRegistry
import io.prometheus.metrics.model.registry.PrometheusRegistry
import no.nav.dagpenger.rapportering.fabrikk.helpers.januar
import no.nav.dagpenger.rapportering.fabrikk.mediator.metrikker.RapporteringMetrikker
import org.junit.jupiter.api.Test

class RapporteringMediatorTest {
    @Test
    fun `skal opprette og publisere løsning for rapporteringsperiode`() {
        val rapidsConnection = TestRapid()
        val rapporteringMetrikker =
            RapporteringMetrikker(PrometheusMeterRegistry(PrometheusConfig.DEFAULT, PrometheusRegistry.defaultRegistry, Clock.SYSTEM))
        val rapporteringMediator = RapporteringMediator(rapidsConnection, rapporteringMetrikker)
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
