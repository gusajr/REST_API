package com.api.microservicio.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.api.microservicio.dao.MicroservicioDao;
import com.api.microservicio.model.Cliente;
import com.api.microservicio.model.All;
import com.api.microservicio.model.Cita;
import com.api.microservicio.model.Especialidad;
import com.api.microservicio.model.Medico;
import com.google.gson.Gson;

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
		return new Gson().toJson(dao.obtenerClientes());
		//String respuesta = 
		//return new ResponseEntity<Object>(new Gson().toJson(dao.obtenerClientes());x, HttpStatus.OK);
	}

	@GetMapping("/citas")
	String mostrarCitas(){
		return new Gson().toJson(dao.obtenerCitas());
	}

	@GetMapping("/especialidades")
	String mostrarEspecialidades(){
		return new Gson().toJson(dao.obtenerEspecialidades());
	}

	@GetMapping("/medicos")
	String mostrarMedicos(){
		return new Gson().toJson(dao.obtenerMedicos().toString());
	}

	@GetMapping("/todo")
	String obtenerTodo(){
		All todo = new All();
		todo.setClientes(dao.obtenerClientes());
		todo.setCitas(dao.obtenerCitas());
		todo.setEspecialidades(dao.obtenerEspecialidades());
		todo.setMedicos(dao.obtenerMedicos());

		return new Gson().toJson(todo);
	}
}
