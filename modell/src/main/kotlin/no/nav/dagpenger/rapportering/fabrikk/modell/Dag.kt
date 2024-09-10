package no.nav.dagpenger.rapportering.fabrikk.modell

import java.time.LocalDate

data class Dag(
    val dato: LocalDate,
    val aktiviteter: List<Aktivitet> = emptyList(),
    val dagIndex: Int,
)
