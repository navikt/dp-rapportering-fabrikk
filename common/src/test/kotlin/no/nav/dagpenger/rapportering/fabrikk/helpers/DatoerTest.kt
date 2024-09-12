package no.nav.dagpenger.rapportering.fabrikk.helpers

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate

class DatoerTest {
    @Test
    fun `skal opprette en LocalDate for januar`() {
        assertEquals(LocalDate.of(2024, 1, 1), 1.januar)
        assertEquals(LocalDate.of(2024, 1, 31), 31.januar)
    }

    @Test
    fun `skal opprette en LocalDate for februar`() {
        assertEquals(LocalDate.of(2024, 2, 1), 1.februar)
        assertEquals(LocalDate.of(2024, 2, 29), 29.februar)
        assertEquals(LocalDate.of(2024, 2, 28), 28.februar)
    }

    @Test
    fun `skal opprette en LocalDate for mars`() {
        assertEquals(LocalDate.of(2024, 3, 1), 1.mars)
        assertEquals(LocalDate.of(2024, 3, 31), 31.mars)
    }

    @Test
    fun `skal opprette en LocalDate for april`() {
        assertEquals(LocalDate.of(2024, 4, 1), 1.april)
        assertEquals(LocalDate.of(2024, 4, 30), 30.april)
    }

    @Test
    fun `skal opprette en LocalDate for mai`() {
        assertEquals(LocalDate.of(2024, 5, 1), 1.mai)
        assertEquals(LocalDate.of(2024, 5, 31), 31.mai)
    }

    @Test
    fun `skal opprette en LocalDate for juni`() {
        assertEquals(LocalDate.of(2024, 6, 1), 1.juni)
        assertEquals(LocalDate.of(2024, 6, 30), 30.juni)
    }

    @Test
    fun `skal opprette en LocalDate for juli`() {
        assertEquals(LocalDate.of(2024, 7, 1), 1.juli)
        assertEquals(LocalDate.of(2024, 7, 31), 31.juli)
    }

    @Test
    fun `skal opprette en LocalDate for august`() {
        assertEquals(LocalDate.of(2024, 8, 1), 1.august)
        assertEquals(LocalDate.of(2024, 8, 31), 31.august)
    }

    @Test
    fun `skal opprette en LocalDate for september`() {
        assertEquals(LocalDate.of(2024, 9, 1), 1.september)
        assertEquals(LocalDate.of(2024, 9, 30), 30.september)
    }

    @Test
    fun `skal opprette en LocalDate for oktober`() {
        assertEquals(LocalDate.of(2024, 10, 1), 1.oktober)
        assertEquals(LocalDate.of(2024, 10, 31), 31.oktober)
    }

    @Test
    fun `skal opprette en LocalDate for november`() {
        assertEquals(LocalDate.of(2024, 11, 1), 1.november)
        assertEquals(LocalDate.of(2024, 11, 30), 30.november)
    }

    @Test
    fun `skal opprette en LocalDate for desember`() {
        assertEquals(LocalDate.of(2024, 12, 1), 1.desember)
        assertEquals(LocalDate.of(2024, 12, 31), 31.desember)
    }
}
