package no.nav.dagpenger.rapportering.fabrikk.mediator

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
        eventName = "behov",
        map =
            mapOf(
                "@behov" to "ny_rapportering",
                "@behovId" to "c777cdb5-0518-4cd7-b171-148c8c6401c4",
                "ident" to ident,
                "fom" to periode.fraOgMed.toString(),
                "@løsning" to this,
            ),
    )
