/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio;

import java.io.Serializable;

/**
 *
 * @author Adriana Herrera
 */
public class Ciclo implements Serializable {
    
    private String anno;
    private String numCiclo;
    private String fechaInicio;
    private String fechaFinal;
   
    public void Ciclo(){
        anno = new String();
        numCiclo = new String();
        fechaInicio = new String();
        fechaFinal = new String();
    }
    public void Ciclo(String anno, String numCiclo, String fechaInicio, String fechaFinal){
        this.anno = anno;
        this.numCiclo = numCiclo;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
    }

    public String getAnno() {
        return anno;
    }

    public void setAnno(String anno) {
        this.anno = anno;
    }

    public String getNumCiclo() {
        return numCiclo;
    }

    public void setNumCiclo(String numCiclo) {
        this.numCiclo = numCiclo;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    @Override
    public String toString() {
        return "Ciclo{" + "anno=" + anno + ", numCiclo=" + numCiclo + ", fechaInicio=" + fechaInicio + ", fechaFinal=" + fechaFinal + '}';
    }
}
