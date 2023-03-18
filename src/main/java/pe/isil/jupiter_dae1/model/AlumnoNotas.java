/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.isil.jupiter_dae1.model;

/**
 *
 * @author RL123
 */
public class AlumnoNotas {
    private int id_alumno;
    private String alumno;
    private String curso;
    private Double ep1;
    private Double ep2;
    private Double ep3;
    private Double ep4;
    private Long promedio_eps;
    private Double ep_parcial;
    private Double ep_final;
    private Long nota;

    public AlumnoNotas() {
    }

    public int getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(int id_alumno) {
        this.id_alumno = id_alumno;
    }

    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Double getEp1() {
        return ep1;
    }

    public void setEp1(Double ep1) {
        this.ep1 = ep1;
    }

    public Double getEp2() {
        return ep2;
    }

    public void setEp2(Double ep2) {
        this.ep2 = ep2;
    }

    public Double getEp3() {
        return ep3;
    }

    public void setEp3(Double ep3) {
        this.ep3 = ep3;
    }

    public Double getEp4() {
        return ep4;
    }

    public void setEp4(Double ep4) {
        this.ep4 = ep4;
    }

    public Long getPromedio_eps() {
        return promedio_eps;
    }

    public void setPromedio_eps(Long promedio_eps) {
        this.promedio_eps = promedio_eps;
    }

    public Double getEp_parcial() {
        return ep_parcial;
    }

    public void setEp_parcial(Double ep_parcial) {
        this.ep_parcial = ep_parcial;
    }

    public Double getEp_final() {
        return ep_final;
    }

    public void setEp_final(Double ep_final) {
        this.ep_final = ep_final;
    }

    public Long getNota() {
        return nota;
    }

    public void setNota(Long nota) {
        this.nota = nota;
    }
    
       
}
