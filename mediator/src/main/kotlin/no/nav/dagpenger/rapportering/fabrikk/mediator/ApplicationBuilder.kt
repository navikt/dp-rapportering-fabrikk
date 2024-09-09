package no.nav.dagpenger.rapportering.fabrikk.mediator

import no.nav.dagpenger.rapportering.fabrikk.mediator.api.internalApi
import no.nav.dagpenger.rapportering.fabrikk.mediator.config.apiKonfigurasjon
import no.nav.helse.rapids_rivers.RapidApplication
import no.nav.helse.rapids_rivers.RapidsConnection

internal class ApplicationBuilder(
    configuration: Map<String, String>,
) : RapidsConnection.StatusListener {
    private val rapidsConnection =
        RapidApplication
            .Builder(RapidApplication.RapidApplicationConfig.fromEnv(configuration))
            .withKtorModule {
                apiKonfigurasjon()
                internalApi()
            }.build()

    init {
        rapidsConnection.register(this)
    }

    internal fun start() {
        rapidsConnection.start()
    }

    override fun onStartup(rapidsConnection: RapidsConnection) {
    }
}