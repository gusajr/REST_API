package com.api.microservicio.controller;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.api.microservicio.dao.MicroservicioDao;
import com.api.microservicio.model.Cliente;

@RestController
public class MicroservicioController {
	
	ArrayList<Cliente> clientes = new ArrayList<Cliente>();

	@Autowired
	MicroservicioDao dao;

	@GetMapping("/")
	String correcto(){
		return "Servidor montado correctamente";
	}	

	// @GetMapping("/cliente/{nombres}")
	// String anadirCliente(@PathVariable (required = true) String nombre){
	// 	Cliente cliente = new Cliente();
	// 	cliente.setNombres(nombre);
	// 	clientes.add(cliente);
	// 	return "Cliente añadido correctamente";
	// }

	@GetMapping("/cliente/{idCliente}")
	String verCliente(@PathVariable (required = true) String idCliente){
		return dao.obtenerCliente(idCliente).toString();
	}

	@GetMapping("/clientes")
	String mostrarClientes(){
		return dao.obtenerClientes().toString();
	}

}
