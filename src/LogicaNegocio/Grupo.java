/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Adriana Herrera
 */
public class Grupo implements Serializable {
    
    private Ciclo ciclo;
    private Curso curso;
    private String horario;
    private Profesor profesor;
    private int nota;
    private List<Alumno> listaAlumnos;
   
    public void Grupo(){
        ciclo = new Ciclo();
        curso = new Curso();
        horario = new String();
        profesor = new Profesor();
        nota = 0;
        listaAlumnos = null;
    }
    public void Grupo(Ciclo ciclo, Curso curso, String horario, Profesor profesor, int nota, List<Alumno> listaAlumnos){
        this.ciclo = ciclo;
        this.curso = curso;
        this.horario = horario;
        this.profesor = profesor;
        this.nota = nota;
        this.listaAlumnos=listaAlumnos;
    }

    public Ciclo getCiclo() {
        return ciclo;
    }

    public void setCiclo(Ciclo ciclo) {
        this.ciclo = ciclo;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public List<Alumno> getListaAlumnos() {
        return listaAlumnos;
    }

    public void setListaAlumnos(List<Alumno> listaAlumnos) {
        this.listaAlumnos = listaAlumnos;
    }
}
