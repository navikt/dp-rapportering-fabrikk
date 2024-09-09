package no.nav.dagpenger.rapportering.fabrikk.modell

import java.util.UUID

data class Aktivitet(
    val id: UUID,
    val type: AktivitetsType,
    val timer: String?,
) {
    enum class AktivitetsType {
        Arbeid,
        Syk,
        Utdanning,
        Fravaer,
    }
}
