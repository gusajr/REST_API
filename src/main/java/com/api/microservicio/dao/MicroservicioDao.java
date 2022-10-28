package com.api.microservicio.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.api.microservicio.model.Busqueda;
import com.api.microservicio.model.Cita;
import com.api.microservicio.model.Cliente;
import com.api.microservicio.model.Especialidad;
import com.api.microservicio.model.Medico;

@Repository
@Transactional
public class MicroservicioDao{
    
    @Autowired
    private JdbcTemplate template;

    Logger logger;

    public void setTemplate(JdbcTemplate template) {    
        this.template = template;    
    }

    /* CLIENTE */

    public List<Cliente> obtenerCliente(String id){
        String sql="select * from cliente where idCliente="+id;      
        return template.query(sql, new BeanPropertyRowMapper<Cliente>(Cliente.class));       
    }    

    public List<Cliente> obtenerClientes(){
        String sql="select * from cliente";      
        return template.query(sql, new BeanPropertyRowMapper<Cliente>(Cliente.class));       
    }   
    
    public int crearCliente(Cliente cliente){
        try{
            Object[] clienteObj = new Object[] { cliente.getNombres(), cliente.getApellidos(),
                cliente.getCorreo(), cliente.getCurp() };
            String sql="insert into cliente (nombres,apellidos,correo,curp) values (?,?,?,?)";      
            return template.update(sql,clienteObj);
        }catch(Exception e){
            logger.info("ERROR: "+e.toString());
            return 0;
        }
    }   

    public int actualizarCliente(Cliente cliente){
        try{
            Object[] clienteObj = new Object[] { cliente.getNombres(), cliente.getApellidos(),
                cliente.getCorreo(), cliente.getCurp(), cliente.getIdCliente()};
            String sql="update cliente set nombres = ?,  apellidos = ?, correo = ?, curp = ? where idCliente = ?";      
            return template.update(sql,clienteObj);
        }catch(Exception e){

            return 0;
        }
    }   


    /* CITA */

    public List<Cita> obtenerCitas(){
        String sql="select * from cita";      
        return template.query(sql, new BeanPropertyRowMapper<Cita>(Cita.class));       
    }   

    public List<Cita> obtenerCitas(Busqueda busqueda){
        String sql = "";
        if(busqueda.getFechaHoraCita()!=null){
            sql="select * from cita where fechaHoraCita>"+busqueda.getFechaHoraCita();
        }else{
            sql="select * from cita as ca inner join cliente as ce on ca.idCliente = ce.idCliente where ce.curp = '"+busqueda.getCurp()+"'";
        }
        return template.query(sql, new BeanPropertyRowMapper<Cita>(Cita.class));        
    } 

    public int crearCita(Cita cita){
        try{
            Object[] citaObj = new Object[] { cita.getIdCliente(), cita.getIdEspecialidad(),
                cita.getFechaHoraCita(), cita.getActiva(), cita.getMotivo() };
            String sql="insert into cita (idCliente,idEspecialidad,fechaHoraCita,activa,motivo) values (?,?,?,?,?)";      
            return template.update(sql,citaObj);
        }catch(Exception e){
            return 0;
        }
    }

    public int actualizarCita(Cita cita){
        try{
            Object[] citaObj = new Object[] { cita.getIdCliente(), cita.getIdEspecialidad(),
                cita.getFechaHoraCita(), cita.getActiva(), cita.getMotivo(), cita.getIdCita() };
            String sql="update cita set idCliente=?,idEspecialidad=?,fechaHoraCita=?,activa=?,motivo=? where idCita=?";      
            return template.update(sql,citaObj);
        }catch(Exception e){
            return 0;
        }
    }

    public int eliminarCita(Cita cita){
        try{
            Object[] citaObj = new Object[] { cita.getIdCita() };
            String sql="delete from cita where idCita=?";      
            return template.update(sql,citaObj);
        }catch(Exception e){
            return 0;
        }
    }

    /* ESPECIALIDAD */

    public List<Especialidad> obtenerEspecialidades(){
        String sql="select * from especialidad";      
        return template.query(sql, new BeanPropertyRowMapper<Especialidad>(Especialidad.class));       
    }

    /* MEDICO */

    public List<Medico> obtenerMedicos(){
        String sql="select * from medico";      
        return template.query(sql, new BeanPropertyRowMapper<Medico>(Medico.class));       
    }

}