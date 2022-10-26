package com.api.microservicio.model;

import lombok.Data;

@Data
public class Cita {
    String idCita;
    String idCliente;
    String idEspecialidad;
    String fechaHoraCita;
    boolean activa;
    String motivo;
}
