package no.nav.dagpenger.rapportering.fabrikk.modell

import no.nav.dagpenger.rapportering.fabrikk.modell.RapporteringsperiodeStatus.TilUtfylling
import java.util.UUID

data class Rapporteringsperiode(
    val id: UUID = UUID.randomUUID(),
    val periode: Periode,
    val dager: List<Dag>,
    val kanSendes: Boolean = false,
    val kanEndres: Boolean = true,
    val status: RapporteringsperiodeStatus = TilUtfylling,
)

enum class RapporteringsperiodeStatus {
    TilUtfylling,
}
