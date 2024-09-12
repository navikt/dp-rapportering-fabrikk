package no.nav.dagpenger.rapportering.fabrikk.helpers

import java.time.LocalDate

infix fun Int.januar(year: Int): LocalDate = LocalDate.of(year, 1, this)

infix fun Int.februar(year: Int): LocalDate = LocalDate.of(year, 2, this)

infix fun Int.mars(year: Int): LocalDate = LocalDate.of(year, 3, this)

infix fun Int.april(year: Int): LocalDate = LocalDate.of(year, 4, this)

infix fun Int.mai(year: Int): LocalDate = LocalDate.of(year, 5, this)

infix fun Int.juni(year: Int): LocalDate = LocalDate.of(year, 6, this)

infix fun Int.juli(year: Int): LocalDate = LocalDate.of(year, 7, this)

infix fun Int.august(year: Int): LocalDate = LocalDate.of(year, 8, this)

infix fun Int.september(year: Int): LocalDate = LocalDate.of(year, 9, this)

infix fun Int.oktober(year: Int): LocalDate = LocalDate.of(year, 10, this)

infix fun Int.november(year: Int): LocalDate = LocalDate.of(year, 11, this)

infix fun Int.desember(year: Int): LocalDate = LocalDate.of(year, 12, this)
