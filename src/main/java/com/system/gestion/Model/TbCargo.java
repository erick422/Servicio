package com.system.gestion.Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="tb_cargo")
public class TbCargo implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tb_id_cargo")
    private Integer Id;
    @Column(name="tb_nombre_cargo")
    private String Nombre;
    @Column(name="tb_jerarquia_cargo")
    private Integer Jerarquia;
    @Column(name="tb_hora_est")
    private String HorEst;
    @Column(name="tb_hora_tol")
    private String HorTol;
    @Column(name="tb_estado_cargo")
    private String Estado;

    public TbCargo(){ }

    public TbCargo(Integer Id) {
        this.Id = Id;
    }

    public TbCargo(Integer Id, String Nombre, Integer Jerarquia, String HorEst, String HorTol, String Estado) {
        this.Id = Id;
        this.Nombre = Nombre;
        this.Jerarquia = Jerarquia;
        this.HorEst = HorEst;
        this.HorTol = HorTol;
        this.Estado = Estado;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public Integer getJerarquia() {
        return Jerarquia;
    }

    public void setJerarquia(Integer Jerarquia) {
        this.Jerarquia = Jerarquia;
    }

    public String getHorEst() {
        return HorEst;
    }

    public void setHorEst(String HorEst) {
        this.HorEst = HorEst;
    }

    public String getHorTol() {
        return HorTol;
    }

    public void setHorTol(String HorTol) {
        this.HorTol = HorTol;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

}