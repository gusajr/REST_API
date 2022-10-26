package com.api.microservicio.model;

import java.util.List;

import lombok.Data;

@Data
public class All {
    private List<Cliente> clientes;
	private List<Cita> citas;
	private List<Especialidad> especialidades;
	private List<Medico> medicos;
}
