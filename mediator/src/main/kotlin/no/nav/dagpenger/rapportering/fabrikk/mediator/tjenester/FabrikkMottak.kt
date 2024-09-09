package no.nav.dagpenger.rapportering.fabrikk.mediator.tjenester

import mu.KotlinLogging
import no.nav.dagpenger.rapportering.fabrikk.mediator.Mediator
import no.nav.helse.rapids_rivers.JsonMessage
import no.nav.helse.rapids_rivers.MessageContext
import no.nav.helse.rapids_rivers.RapidsConnection
import no.nav.helse.rapids_rivers.River

class FabrikkMottak(
    rapidsConnection: RapidsConnection,
    private val mediator: Mediator,
) : River.PacketListener {
    init {
        River(rapidsConnection)
            .apply {
                validate { it.demandValue("@event_name", "ny_rapportering") }
                validate { it.requireKey("ident") }
                validate { it.rejectKey("@løsning") }
            }.register(this)
    }

    override fun onPacket(
        packet: JsonMessage,
        context: MessageContext,
    ) {
        val ident = packet["ident"].asText()
        logger.info { "Mottatt ny rapportering" }
        sikkerlogg.info { "Mottatt ny rapportering for ident=$ident" }
        mediator.behandle(ident)
    }

    companion object {
        private val logger = KotlinLogging.logger {}
        val sikkerlogg = KotlinLogging.logger("tjenestekall.FabrikkMottak")
    }
}
