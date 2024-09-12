package no.nav.dagpenger.rapportering.fabrikk.helpers

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate

class DatoerTest {
    @Test
    fun `skal opprette en LocalDate for januar`() {
        assertEquals(LocalDate.of(2024, 1, 1), 1 januar 2024)
        assertEquals(LocalDate.of(2024, 1, 31), 31 januar 2024)
    }

    @Test
    fun `skal opprette en LocalDate for februar`() {
        assertEquals(LocalDate.of(2024, 2, 1), 1 februar 2024)
        assertEquals(LocalDate.of(2024, 2, 29), 29 februar 2024)
        assertEquals(LocalDate.of(2023, 2, 28), 28 februar 2023)
    }

    @Test
    fun `skal opprette en LocalDate for mars`() {
        assertEquals(LocalDate.of(2024, 3, 1), 1 mars 2024)
        assertEquals(LocalDate.of(2024, 3, 31), 31 mars 2024)
    }

    @Test
    fun `skal opprette en LocalDate for april`() {
        assertEquals(LocalDate.of(2024, 4, 1), 1 april 2024)
        assertEquals(LocalDate.of(2024, 4, 30), 30 april 2024)
    }

    @Test
    fun `skal opprette en LocalDate for mai`() {
        assertEquals(LocalDate.of(2024, 5, 1), 1 mai 2024)
        assertEquals(LocalDate.of(2024, 5, 31), 31 mai 2024)
    }

    @Test
    fun `skal opprette en LocalDate for juni`() {
        assertEquals(LocalDate.of(2024, 6, 1), 1 juni 2024)
        assertEquals(LocalDate.of(2024, 6, 30), 30 juni 2024)
    }

    @Test
    fun `skal opprette en LocalDate for juli`() {
        assertEquals(LocalDate.of(2024, 7, 1), 1 juli 2024)
        assertEquals(LocalDate.of(2024, 7, 31), 31 juli 2024)
    }

    @Test
    fun `skal opprette en LocalDate for august`() {
        assertEquals(LocalDate.of(2024, 8, 1), 1 august 2024)
        assertEquals(LocalDate.of(2024, 8, 31), 31 august 2024)
    }

    @Test
    fun `skal opprette en LocalDate for september`() {
        assertEquals(LocalDate.of(2024, 9, 1), 1 september 2024)
        assertEquals(LocalDate.of(2024, 9, 30), 30 september 2024)
    }

    @Test
    fun `skal opprette en LocalDate for oktober`() {
        assertEquals(LocalDate.of(2024, 10, 1), 1 oktober 2024)
        assertEquals(LocalDate.of(2024, 10, 31), 31 oktober 2024)
    }

    @Test
    fun `skal opprette en LocalDate for november`() {
        assertEquals(LocalDate.of(2024, 11, 1), 1 november 2024)
        assertEquals(LocalDate.of(2024, 11, 30), 30 november 2024)
    }

    @Test
    fun `skal opprette en LocalDate for desember`() {
        assertEquals(LocalDate.of(2024, 12, 1), 1 desember 2024)
        assertEquals(LocalDate.of(2024, 12, 31), 31 desember 2024)
    }
}
