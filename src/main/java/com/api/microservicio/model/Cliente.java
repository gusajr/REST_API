package com.api.microservicio.model;

import lombok.Data;

@Data
public class Cliente{
    String idCliente;
    String nombres;
    String apellidos;
    String correo;
    String curp;
}