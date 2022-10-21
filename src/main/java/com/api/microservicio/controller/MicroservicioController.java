package com.api.microservicio.controller;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.api.microservicio.model.Cliente;

@RestController
public class MicroservicioController {
	
	ArrayList<Cliente> clientes = new ArrayList<Cliente>();

	@GetMapping("/")
	String correcto(){
		return "Servidor montado correctamente";
	}	

	@GetMapping("/cliente/{nombres}")
	String anadirCliente(@PathVariable (required = true) String nombre){
		Cliente cliente = new Cliente();
		cliente.setNombres(nombre);
		clientes.add(cliente);
		return "Cliente añadido correctamente";
	}

	@GetMapping("/clientes")
	String mostrarClientes(){
		return clientes.toString();
	}

}
