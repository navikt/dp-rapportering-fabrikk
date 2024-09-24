package no.nav.dagpenger.rapportering.fabrikk.mediator.metrikker

import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.MeterRegistry

private const val NAMESPACE = "dp_rapportering_fabrikk"

class RapporteringMetrikker(
    meterRegistry: MeterRegistry,
) {
    val forespurt: Counter =
        Counter
            .builder("${NAMESPACE}_antall_nye_rapporteringer_forespurt")
            .description("Indikerer antall rapporteringer som er forespurt")
            .register(meterRegistry)

    val opprettet: Counter =
        Counter
            .builder("${NAMESPACE}_antall_nye_rapporteringer_opprettet")
            .description("Indikerer antall rapporteringer som er opprettet")
            .register(meterRegistry)
}
