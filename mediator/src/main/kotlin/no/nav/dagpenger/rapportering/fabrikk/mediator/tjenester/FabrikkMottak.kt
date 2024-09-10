package no.nav.dagpenger.rapportering.fabrikk.mediator.tjenester

import mu.KotlinLogging
import no.nav.dagpenger.rapportering.fabrikk.mediator.RapporteringMediator
import no.nav.helse.rapids_rivers.JsonMessage
import no.nav.helse.rapids_rivers.MessageContext
import no.nav.helse.rapids_rivers.RapidsConnection
import no.nav.helse.rapids_rivers.River
import no.nav.helse.rapids_rivers.asLocalDate

class FabrikkMottak(
    rapidsConnection: RapidsConnection,
    private val rapporteringMediator: RapporteringMediator,
) : River.PacketListener {
    init {
        River(rapidsConnection)
            .apply {
                validate { it.demandValue("@event_name", "behov") }
                validate { it.demandValue("@behov", "ny_rapportering") }
                validate { it.requireKey("ident", "fom", "@behovId") }
                validate { it.rejectKey("@løsning") }
            }.register(this)
    }

    override fun onPacket(
        packet: JsonMessage,
        context: MessageContext,
    ) {
        val ident = packet["ident"].asText()
        val fraOgMed = packet["fom"].asLocalDate()

        logger.info { "Mottok ny rapportering" }
        sikkerlogg.info { "Mottok ny rapportering for ident=$ident" }

        rapporteringMediator.behandle(ident, fraOgMed)
    }

    companion object {
        private val logger = KotlinLogging.logger {}
        val sikkerlogg = KotlinLogging.logger("tjenestekall.FabrikkMottak")
    }
}
