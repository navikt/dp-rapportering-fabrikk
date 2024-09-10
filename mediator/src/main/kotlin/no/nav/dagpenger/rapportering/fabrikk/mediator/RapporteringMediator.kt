package no.nav.dagpenger.rapportering.fabrikk.mediator

import no.nav.dagpenger.rapportering.fabrikk.mediator.Configuration.defaultObjectMapper
import no.nav.dagpenger.rapportering.fabrikk.modell.Rapporteringsperiode
import no.nav.helse.rapids_rivers.JsonMessage
import no.nav.helse.rapids_rivers.RapidsConnection
import java.time.LocalDate

class RapporteringMediator(
    private val rapidsConnection: RapidsConnection,
) {
    fun behandle(
        ident: String,
        fraOgMed: LocalDate,
    ) {
        Rapporteringsperiode(fraOgMed)
            .also {
                rapidsConnection.publish(
                    it.asMessage(ident = ident).toJson(),
                )
            }
    }
}

fun Rapporteringsperiode.asMessage(ident: String): JsonMessage =
    JsonMessage.newMessage(
        eventName = "ny_rapportering",
        map =
            mapOf(
                "ident" to ident,
                "rapporteringsperiode" to defaultObjectMapper.writeValueAsString(this),
            ),
    )
