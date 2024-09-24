package no.nav.dagpenger.rapportering.fabrikk.mediator

import com.github.navikt.tbd_libs.rapids_and_rivers_api.RapidsConnection
import io.micrometer.core.instrument.Clock
import io.micrometer.prometheusmetrics.PrometheusConfig
import io.micrometer.prometheusmetrics.PrometheusMeterRegistry
import io.prometheus.metrics.model.registry.PrometheusRegistry
import mu.KotlinLogging
import no.nav.dagpenger.rapportering.fabrikk.mediator.api.internalApi
import no.nav.dagpenger.rapportering.fabrikk.mediator.metrikker.RapporteringMetrikker
import no.nav.dagpenger.rapportering.fabrikk.mediator.tjenester.FabrikkMottak
import no.nav.helse.rapids_rivers.RapidApplication

internal class ApplicationBuilder(
    configuration: Map<String, String>,
) : RapidsConnection.StatusListener {
    private val meterRegistry = PrometheusMeterRegistry(PrometheusConfig.DEFAULT, PrometheusRegistry.defaultRegistry, Clock.SYSTEM)
    private val rapporteringMetrikker = RapporteringMetrikker(meterRegistry)
    private val rapidsConnection =
        RapidApplication
            .create(configuration) { engine, _: RapidsConnection ->
                engine.application.internalApi()
            }

    init {
        rapidsConnection.register(this)
    }

    internal fun start() {
        rapidsConnection.start()
    }

    override fun onStartup(rapidsConnection: RapidsConnection) {
        val rapporteringMediator = RapporteringMediator(rapidsConnection, rapporteringMetrikker)
        FabrikkMottak(rapidsConnection, rapporteringMediator, rapporteringMetrikker).also {
            logger.info { "Startet FabrikkMottak" }
        }
//        SoknadMottak(rapidsConnection, rapporteringMediator)
    }

    private companion object {
        private val logger = KotlinLogging.logger {}
    }
}
