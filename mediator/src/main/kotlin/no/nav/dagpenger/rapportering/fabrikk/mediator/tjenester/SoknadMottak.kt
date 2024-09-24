package no.nav.dagpenger.rapportering.fabrikk.mediator.tjenester

import com.github.navikt.tbd_libs.rapids_and_rivers.JsonMessage
import com.github.navikt.tbd_libs.rapids_and_rivers.River
import com.github.navikt.tbd_libs.rapids_and_rivers.asLocalDateTime
import com.github.navikt.tbd_libs.rapids_and_rivers_api.MessageContext
import com.github.navikt.tbd_libs.rapids_and_rivers_api.RapidsConnection
import mu.KotlinLogging
import no.nav.dagpenger.rapportering.fabrikk.mediator.RapporteringMediator
import no.nav.dagpenger.rapportering.fabrikk.mediator.metrikker.RapporteringMetrikker

class SoknadMottak(
    rapidsConnection: RapidsConnection,
    private val rapporteringMediator: RapporteringMediator,
    private val rapporteringMetrikker: RapporteringMetrikker,
) : River.PacketListener {
    init {
        River(rapidsConnection)
            .apply {
                validate { it.demandValue("@event_name", "søknad_innsendt_varsel") }
                validate { it.requireKey("ident", "søknadId", "søknadstidspunkt", "søknadData") }
                validate { it.interestedIn("@id", "@opprettet") }
            }.register(this)
    }

    override fun onPacket(
        packet: JsonMessage,
        context: MessageContext,
    ) {
        logger.info { "Mottok søknad innsendt hendelse for søknad ${packet["søknadId"]}" }
        logger.info { "Søknadstidspunkt: ${packet["søknadstidspunkt"]}" }

        val ident = packet["ident"].asText()
        val fraOgMed = packet["søknadstidspunkt"].asLocalDateTime().toLocalDate()

        rapporteringMetrikker.forespurt.increment()

        rapporteringMediator.behandle(ident, fraOgMed)
    }

    companion object {
        private val logger = KotlinLogging.logger {}
    }
}
