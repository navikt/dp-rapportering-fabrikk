package no.nav.dagpenger.rapportering.fabrikk.modell

import java.time.LocalDate

data class Rapporteringsperiode(
    val id: Long,
    val periode: Periode,
    val dager: List<Dag>,
    val kanSendesFra: LocalDate,
    val kanSendes: Boolean,
    val kanEndres: Boolean,
    val status: RapporteringsperiodeStatus,
)
