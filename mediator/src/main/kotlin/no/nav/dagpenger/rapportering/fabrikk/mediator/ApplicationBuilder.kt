package no.nav.dagpenger.rapportering.fabrikk.mediator

import mu.KotlinLogging
import no.nav.dagpenger.rapportering.fabrikk.mediator.api.internalApi
import no.nav.dagpenger.rapportering.fabrikk.mediator.tjenester.FabrikkMottak
import no.nav.dagpenger.rapportering.fabrikk.mediator.tjenester.SoknadMottak
import no.nav.helse.rapids_rivers.RapidApplication
import no.nav.helse.rapids_rivers.RapidsConnection

internal class ApplicationBuilder(
    configuration: Map<String, String>,
) : RapidsConnection.StatusListener {
    private val rapidsConnection =
        RapidApplication
            .Builder(RapidApplication.RapidApplicationConfig.fromEnv(configuration))
            .withKtorModule {
                internalApi()
            }.build()

    init {
        rapidsConnection.register(this)
    }

    internal fun start() {
        rapidsConnection.start()
    }

    override fun onStartup(rapidsConnection: RapidsConnection) {
        val rapporteringMediator = RapporteringMediator(rapidsConnection)
        FabrikkMottak(rapidsConnection, rapporteringMediator).also {
            logger.info { "Startet FabrikkMottak" }
        }
        SoknadMottak(rapidsConnection, rapporteringMediator)
    }

    private companion object {
        private val logger = KotlinLogging.logger {}
    }
}
