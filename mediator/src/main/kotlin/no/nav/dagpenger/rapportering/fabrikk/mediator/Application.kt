package no.nav.dagpenger.rapportering.fabrikk.mediator

import no.nav.dagpenger.rapportering.fabrikk.mediator.config.Configuration

fun main() {
    ApplicationBuilder(Configuration.config).start()
}
