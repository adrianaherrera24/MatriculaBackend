/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import AccesoDatos.GlobalException;
import AccesoDatos.NoDataException;

import AccesoDatos.ServicioAlumnos;
import AccesoDatos.ServicioCarreras;
import AccesoDatos.ServicioCursos;
import AccesoDatos.ServicioProfesores;
import AccesoDatos.ServicioUsuarios;
import LogicaNegocio.Alumno;
import LogicaNegocio.Carrera;
import LogicaNegocio.Curso;
import LogicaNegocio.Profesor;
import LogicaNegocio.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adriana Herrera
 */
public class Control {
    
    private ServicioAlumnos sa;
    private ServicioCarreras sc;
    private ServicioCursos scc;
    private ServicioProfesores sp;
    private ServicioUsuarios su;
    
    // Preguntar sobre instancia unica
    private static Control uniqueInstance;
    
    public static Control instance()
    {
        if (uniqueInstance == null)
        {
            uniqueInstance = new Control();
        }
        return uniqueInstance;
    }
    
    // Constructor
    public Control(){
        sa = new ServicioAlumnos();
        sc = new ServicioCarreras();
        scc = new ServicioCursos();
        sp = new ServicioProfesores();
        su = new ServicioUsuarios();
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////// Alumnos //////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Alumno> listarAlumnos() throws GlobalException, NoDataException{
        List<Alumno> alum = new ArrayList();
        alum = sa.listarAlumnos();
        return alum;
    }
    
    public void eliminarAlumnos(String id) throws GlobalException, NoDataException{
        sa.eliminarAlumnos(id);
    }
    
    public void insertarAlumnos(Alumno alum) throws GlobalException, NoDataException{
        sa.insertarAlumnos(alum);
    }
    
    public void modificarAlumnos(Alumno alum) throws GlobalException, NoDataException{
        sa.modificarAlumnos(alum);
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////// Carrera //////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Carrera> listarCarreras() throws GlobalException, NoDataException{
        List<Carrera> carrera = new ArrayList();
        carrera = sc.listarCarreras();
        return carrera;
    }
    
    public void eliminarCarreras(String id) throws GlobalException, NoDataException{
        sc.eliminarCarreras(id);
    }
    
    public void insertarCarreras(Carrera carrera) throws GlobalException, NoDataException{
        sc.insertarCarreras(carrera);
    }
    
    public void modificarCarreras(Carrera carrera) throws GlobalException, NoDataException{
        sc.modificarCarreras(carrera);
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////// Cursos ///////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Curso> listarCursos() throws GlobalException, NoDataException{
        List<Curso> curso = new ArrayList();
        curso = scc.listarCursos();
        return curso;
    }
    
    public void eliminarCursos(String id) throws GlobalException, NoDataException{
        scc.eliminarCursos(id);
    }
    
    public void insertarCursos(Curso curso) throws GlobalException, NoDataException{
        scc.insertarCursos(curso);
    }
    
    public void modificarCursos(Curso curso) throws GlobalException, NoDataException{
        scc.modificarCursos(curso);
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////// Profesores ///////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Profesor> listarProfesores() throws GlobalException, NoDataException{
        List<Profesor> prof = new ArrayList();
        prof = sp.listarProfesores();
        return prof;
    }
    
    public void eliminarProfesores(String id) throws GlobalException, NoDataException{
        sp.eliminarProfesores(id);
    }
    
    public void insertarProfesores(Profesor prof) throws GlobalException, NoDataException{
        sp.insertarProfesores(prof);
    }
    
    public void modificarProfesores(Profesor prof) throws GlobalException, NoDataException{
        sp.modificarProfesores(prof);
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////// Usuarios ///////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Usuario> listarUsuarios() throws GlobalException, NoDataException{
        List<Usuario> user = new ArrayList();
        user = su.listarUsuarios();
        return user;
    }
}
