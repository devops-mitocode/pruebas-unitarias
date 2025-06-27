package com.mycompany.app.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MedicamentoServiceJUnitAnnotationsTest {

    private MedicamentoService medicamentoService = new MedicamentoService();

    @Test
    @Tag("one")
    @DisplayName("Registrar medicamento correctamente")
    void testRegistrarMedicamentoCorrectamente() {
        String resultado = medicamentoService.registrarMedicamento("Antibiótico", 10);
        assertEquals("Medicamento registrado: Antibiótico con cantidad: 10", resultado);
    }

    @Test
    @Disabled("Disabled until feature XYZ is implemented")
    void testDisabled() {
        // Esta prueba está deshabilitada
    }

    @Test
    @Tag("two")
    void testTagTwo() {
        String resultado = medicamentoService.registrarMedicamento("Analgésico", 20);
        assertEquals("Medicamento registrado: Analgésico con cantidad: 20", resultado);
    }

    @RepeatedTest(5)
    void testRepeated() {
        String resultado = medicamentoService.registrarMedicamento("Antiinflamatorio", 30);
        assertEquals("Medicamento registrado: Antiinflamatorio con cantidad: 30", resultado);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Vacuna", "Desparasitante"})
    void testParameterizedWithStrings(String nombre) {
        String resultado = medicamentoService.registrarMedicamento(nombre, 5);
        assertEquals("Medicamento registrado: " + nombre + " con cantidad: 5", resultado);
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 20, 30})
    void testParameterizedWithInts(int cantidad) {
        String resultado = medicamentoService.registrarMedicamento("Suplemento", cantidad);
        assertEquals("Medicamento registrado: Suplemento con cantidad: " + cantidad, resultado);
    }

    @ParameterizedTest
    @CsvSource({"101, 'Antibiótico'", "102, 'Analgésico'", "103, 'Antiinflamatorio'", "104, 'Vacuna'", "105, 'Desparasitante'"})
    void testCsvSource(int code, String expectedMedicamento) {
        String medicamento = medicamentoService.convertCodeToMedicamento(code);
        assertEquals(expectedMedicamento, medicamento);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/medicamentos.csv", numLinesToSkip = 1)
    void testConvertCodeToMedicamentoFromResources(int code, String expectedMedicamento) {
        String medicamento = medicamentoService.convertCodeToMedicamento(code);
        assertEquals(expectedMedicamento, medicamento);
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/medicamentos.csv", numLinesToSkip = 1)
    void testConvertCodeToMedicamentoFromFiles(int code, String expectedMedicamento) {
        String medicamento = medicamentoService.convertCodeToMedicamento(code);
        assertEquals(expectedMedicamento, medicamento);
    }
}