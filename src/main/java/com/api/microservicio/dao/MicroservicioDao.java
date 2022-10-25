package com.api.microservicio.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.api.microservicio.model.Cliente;

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
}