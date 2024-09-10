package no.nav.dagpenger.rapportering.fabrikk.modell

import no.nav.dagpenger.rapportering.fabrikk.modell.RapporteringsperiodeStatus.TilUtfylling
import java.time.LocalDate
import java.util.UUID

class Rapporteringsperiode private constructor(
    val id: UUID = UUID.randomUUID(),
    val periode: Periode,
    val dager: List<Dag>,
    val kanSendes: Boolean = false,
    val kanEndres: Boolean = true,
    val status: RapporteringsperiodeStatus = TilUtfylling,
) {
    constructor(
        fraOgMed: LocalDate,
    ) : this(
        periode = Periode(fraOgMed = fraOgMed),
        dager =
            (0..13).map { dagIndex ->
                Dag(dato = fraOgMed.plusDays(dagIndex.toLong()), dagIndex = dagIndex)
            },
    )
}

enum class RapporteringsperiodeStatus {
    TilUtfylling,
}
