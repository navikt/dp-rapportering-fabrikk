package no.nav.dagpenger.rapportering.fabrikk.mediator

import no.nav.dagpenger.rapportering.fabrikk.modell.Rapporteringsperiode
import java.time.LocalDate

class Mediator {
    fun behandle(
        ident: String,
        fraOgMed: LocalDate,
    ) {
        Rapporteringsperiode(fraOgMed).also { rapporteringsperiode ->
            println("Behandler rapporteringsperiode for ident=$ident")
            println("Rapporteringsperiode: $rapporteringsperiode")
        }
    }
}
