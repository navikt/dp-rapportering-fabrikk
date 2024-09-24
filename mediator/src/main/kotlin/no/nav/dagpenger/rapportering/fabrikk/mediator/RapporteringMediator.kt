package no.nav.dagpenger.rapportering.fabrikk.mediator

import com.github.navikt.tbd_libs.rapids_and_rivers.JsonMessage
import com.github.navikt.tbd_libs.rapids_and_rivers_api.RapidsConnection
import no.nav.dagpenger.rapportering.fabrikk.mediator.metrikker.RapporteringMetrikker
import no.nav.dagpenger.rapportering.fabrikk.modell.Rapporteringsperiode
import java.time.LocalDate

class RapporteringMediator(
    private val rapidsConnection: RapidsConnection,
    private val rapporteringMetrikker: RapporteringMetrikker,
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
                rapporteringMetrikker.opprettet.increment()
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
