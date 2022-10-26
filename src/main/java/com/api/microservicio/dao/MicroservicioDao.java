package com.api.microservicio.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.api.microservicio.model.Cita;
import com.api.microservicio.model.Cliente;
import com.api.microservicio.model.Especialidad;
import com.api.microservicio.model.Medico;

@Repository
@Transactional
public class MicroservicioDao{
    @Autowired
    private JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {    
        this.template = template;    
    }

    public List<Cliente> obtenerCliente(String id){
        String sql="select * from cliente where idCliente="+id;      
        return template.query(sql, new BeanPropertyRowMapper<Cliente>(Cliente.class));       
    }    

    public List<Cliente> obtenerClientes(){
        String sql="select * from cliente";      
        return template.query(sql, new BeanPropertyRowMapper<Cliente>(Cliente.class));       
    }    

    public List<Cita> obtenerCitas(){
        String sql="select * from cita";      
        return template.query(sql, new BeanPropertyRowMapper<Cita>(Cita.class));       
    }   

    public List<Especialidad> obtenerEspecialidades(){
        String sql="select * from especialidad";      
        return template.query(sql, new BeanPropertyRowMapper<Especialidad>(Especialidad.class));       
    }

    public List<Medico> obtenerMedicos(){
        String sql="select * from medico";      
        return template.query(sql, new BeanPropertyRowMapper<Medico>(Medico.class));       
    }

}