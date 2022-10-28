package com.api.microservicio.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.microservicio.dao.MicroservicioDao;
import com.api.microservicio.model.Cliente;
import com.api.microservicio.model.All;
import com.api.microservicio.model.Busqueda;
import com.api.microservicio.model.Cita;
import com.api.microservicio.model.Respuesta;

@RestController
public class MicroservicioController {
	
	ArrayList<Cliente> clientes = new ArrayList<Cliente>();

	@Autowired
	MicroservicioDao dao;

	@GetMapping("/")
	String correcto(){
		return "Servidor montado correctamente";
	}

	/* CLIENTE */

	@GetMapping("/cliente/{idCliente}")
	String verCliente(@PathVariable (required = true) String idCliente){
		return dao.obtenerCliente(idCliente).toString();
	}

	@GetMapping("/clientes")
	ResponseEntity<Object> mostrarClientes(){
		return new ResponseEntity<Object>(dao.obtenerClientes(), HttpStatus.OK);
	}

	@PostMapping("/cliente")
	ResponseEntity<Object> crearCliente(@RequestBody Cliente cliente){
		Respuesta respuesta = new Respuesta();
		if(dao.crearCliente(cliente)==1){
			respuesta.setCodigo(200);
			respuesta.setMensaje("Ejecutado correctamente");
			return new ResponseEntity<Object>(respuesta, HttpStatus.OK);
		}else{
			respuesta.setCodigo(500);
			respuesta.setMensaje("No se pudo insertar el nuevo cliente");
			return new ResponseEntity<Object>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/cliente")
	ResponseEntity<Object> actualizarCliente(@RequestBody Cliente cliente){
		Respuesta respuesta = new Respuesta();
		if(dao.actualizarCliente(cliente)==1){
			respuesta.setCodigo(200);
			respuesta.setMensaje("Ejecutado correctamente");
			return new ResponseEntity<Object>(respuesta, HttpStatus.OK);
		}else{
			respuesta.setCodigo(500);
			respuesta.setMensaje("No se pudo actualizar la información del cliente");
			return new ResponseEntity<Object>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/* CITA */

	@GetMapping("/citas")
	ResponseEntity<Object> obtenerCitas(){
		return new ResponseEntity<Object>(dao.obtenerCitas(), HttpStatus.OK);
	}

	@PostMapping("/cita")
	ResponseEntity<Object> crearCita(@RequestBody Cita cita){
		Respuesta respuesta = new Respuesta();
		if(dao.crearCita(cita)==1){
			respuesta.setCodigo(200);
			respuesta.setMensaje("Ejecutado correctamente");
			return new ResponseEntity<Object>(respuesta, HttpStatus.OK);
		}else{
			respuesta.setCodigo(500);
			respuesta.setMensaje("No se pudo insertar el nuevo cliente");
			return new ResponseEntity<Object>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/busqueda")
	ResponseEntity<Object> obtenerCitaPorCurp(@RequestBody Busqueda busqueda){
		return new ResponseEntity<Object>(dao.obtenerCitas(busqueda), HttpStatus.OK);
	}

	@PutMapping("/cita")
	ResponseEntity<Object> actualizarCita(@RequestBody Cita cita){
		Respuesta respuesta = new Respuesta();
		if(dao.actualizarCita(cita)==1){
			respuesta.setCodigo(200);
			respuesta.setMensaje("Ejecutado correctamente");
			return new ResponseEntity<Object>(respuesta, HttpStatus.OK);
		}else{
			respuesta.setCodigo(500);
			respuesta.setMensaje("No se pudo actualizar la información de la cita");
			return new ResponseEntity<Object>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/cita")
	ResponseEntity<Object> eliminarCita(@RequestBody Cita cita){
		Respuesta respuesta = new Respuesta();
		if(dao.eliminarCita(cita)==1){
			respuesta.setCodigo(200);
			respuesta.setMensaje("Ejecutado correctamente");
			return new ResponseEntity<Object>(respuesta, HttpStatus.OK);
		}else{
			respuesta.setCodigo(500);
			respuesta.setMensaje("No se pudo eliminar la cita");
			return new ResponseEntity<Object>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/* ESPECIALIDAD */

	@GetMapping("/especialidades")
	ResponseEntity<Object> mostrarEspecialidades(){
		return new ResponseEntity<Object>(dao.obtenerEspecialidades(), HttpStatus.OK);
	}

	/* MEDICO */

	@GetMapping("/medicos")
	ResponseEntity<Object> mostrarMedicos(){
		return new ResponseEntity<Object>(dao.obtenerMedicos().toString(), HttpStatus.OK);
	}

	/* INFORMACION COMPLETA */

	@GetMapping("/todo")
	ResponseEntity<Object> obtenerTodo(){
		All todo = new All();
		todo.setClientes(dao.obtenerClientes());
		todo.setCitas(dao.obtenerCitas());
		todo.setEspecialidades(dao.obtenerEspecialidades());
		todo.setMedicos(dao.obtenerMedicos());

		return new ResponseEntity<Object>(todo, HttpStatus.OK);
	}
}
