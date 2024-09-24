package no.nav.dagpenger.rapportering.fabrikk.mediator.tjenester

import com.github.navikt.tbd_libs.rapids_and_rivers.JsonMessage
import com.github.navikt.tbd_libs.rapids_and_rivers.River
import com.github.navikt.tbd_libs.rapids_and_rivers.asLocalDate
import com.github.navikt.tbd_libs.rapids_and_rivers_api.MessageContext
import com.github.navikt.tbd_libs.rapids_and_rivers_api.RapidsConnection
import mu.KotlinLogging
import no.nav.dagpenger.rapportering.fabrikk.mediator.RapporteringMediator
import no.nav.dagpenger.rapportering.fabrikk.mediator.metrikker.RapporteringMetrikker

class FabrikkMottak(
    rapidsConnection: RapidsConnection,
    private val rapporteringMediator: RapporteringMediator,
    private val rapporteringMetrikker: RapporteringMetrikker,
) : River.PacketListener {
    init {
        River(rapidsConnection)
            .apply {
                validate { it.demandValue("@event_name", "behov") }
                validate { it.demandValue("@behov", "ny_rapportering") }
                validate { it.requireKey("ident", "fraOgMed", "@behovId") }
                validate { it.rejectKey("@løsning") }
            }.register(this)
    }

    override fun onPacket(
        packet: JsonMessage,
        context: MessageContext,
    ) {
        val ident = packet["ident"].asText()
        val fraOgMed = packet["fraOgMed"].asLocalDate()

        logger.info { "Mottok ny rapportering" }
        sikkerlogg.info { "Mottok ny rapportering for ident=$ident" }

        rapporteringMetrikker.forespurt.increment()

        rapporteringMediator.behandle(ident, fraOgMed)
    }

    companion object {
        private val logger = KotlinLogging.logger {}
        val sikkerlogg = KotlinLogging.logger("tjenestekall.FabrikkMottak")
    }
}
