package no.nav.dagpenger.rapportering.fabrikk.mediator.tjenester

import mu.KotlinLogging
import no.nav.dagpenger.rapportering.fabrikk.mediator.RapporteringMediator
import no.nav.dagpenger.rapportering.fabrikk.mediator.metrikker.RapporteringMetrikker
import no.nav.helse.rapids_rivers.JsonMessage
import no.nav.helse.rapids_rivers.MessageContext
import no.nav.helse.rapids_rivers.RapidsConnection
import no.nav.helse.rapids_rivers.River
import no.nav.helse.rapids_rivers.asLocalDateTime

class SoknadMottak(
    rapidsConnection: RapidsConnection,
    private val rapporteringMediator: RapporteringMediator,
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

        RapporteringMetrikker.forespurt.inc()

        rapporteringMediator.behandle(ident, fraOgMed)
    }

    companion object {
        private val logger = KotlinLogging.logger {}
    }
}