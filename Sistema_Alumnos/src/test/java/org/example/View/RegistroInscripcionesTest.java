package org.example.View;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RegistroInscripcionesTest {
    @Test
    void parseFechaInscripcionAcceptsDayFirstAndIsoFormats() {
        assertEquals(
                LocalDate.of(2026, 11, 7),
                RegistroInscripciones.parseFechaInscripcion("7/11/2026"));
        assertEquals(
                LocalDate.of(2026, 11, 7),
                RegistroInscripciones.parseFechaInscripcion("2026-11-07"));
    }

    @Test
    void parseFechaInscripcionRejectsIncompleteDate() {
        assertThrows(
                DateTimeParseException.class,
                () -> RegistroInscripciones.parseFechaInscripcion("2"));
    }
}
