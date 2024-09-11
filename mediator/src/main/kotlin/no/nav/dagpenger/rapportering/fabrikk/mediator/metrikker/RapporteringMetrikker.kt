package no.nav.dagpenger.rapportering.fabrikk.mediator.metrikker

import io.prometheus.client.Counter

private const val NAMESPACE = "dp_rapportering_fabrikk"

object RapporteringMetrikker {
    val forespurt: Counter =
        Counter
            .build()
            .namespace(NAMESPACE)
            .name("antall_nye_rapporteringer_forespurt")
            .help("Indikerer antall rapporteringer som er forespurt")
            .register()

    val opprettet: Counter =
        Counter
            .build()
            .namespace(NAMESPACE)
            .name("antall_nye_rapporteringer_opprettet")
            .help("Indikerer antall rapporteringer som er opprettet")
            .register()
}
