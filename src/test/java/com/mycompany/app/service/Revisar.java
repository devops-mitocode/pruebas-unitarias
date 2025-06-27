package com.mycompany.app.service;

import com.mycompany.app.model.Mascota;
import com.mycompany.app.model.Propietario;
import com.mycompany.app.repository.MascotaRepository;
import com.mycompany.app.repository.PropietarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class Revisar {

    @Spy
    @InjectMocks
    MascotaService mascotaService;

    @Mock
    MascotaRepository mascotaRepository;

    @Mock
    ExternalService externalService;

     @Mock
     PropietarioRepository propietarioRepository;

    @Test
    @DisplayName("Registrar mascota correctamente")
    void testRegistrarMascotaCorrectamente(){

        // Arrange(Preparar)
        Propietario propietario = new Propietario("Dany", "Lima", "987654321");
        Mascota mascota = new Mascota();
        mascota.setNombre("Garfield");
        mascota.setPropietario(propietario);

        when(externalService.validarVacunas(any(Mascota.class))).thenReturn(true);
        when(externalService.verificarRegistroMunicipal(any(Mascota.class))).thenReturn(true);
        when(mascotaRepository.findByName(any(String.class))).thenReturn(Optional.empty());
         when(propietarioRepository.guardar(any(Propietario.class))).thenReturn(propietario);
        when(mascotaRepository.guardar(any(Mascota.class))).thenReturn(mascota);


        // Act(Actuar)
        Mascota registrada = mascotaService.registrarMascota(mascota);

        // Verifica que el objeto no es null.
        assertNotNull(registrada, "La mascota registrada no debería ser null.");

        // Verifica que dos referencias apunten al mismo objeto, útil para confirmar que la instancia no ha sido clonada o modificada inesperadamente.
        assertSame(mascota, registrada, "La mascota registrada debería ser la misma que la ingresada.");

        verify(mascotaService).enviarCorreoNotificacion(mascota);

//        Revisar: doReturn()
//        doReturn(Optional.of(mascota)).when(mascotaService).buscarMascotaPorId(any(Integer.class));
//        when(mascotaService.buscarMascotaPorId(any(Integer.class))).thenReturn(Optional.of(registrada));

        assertTrue(mascotaService.buscarMascotaPorId(1).isPresent());
    }

}