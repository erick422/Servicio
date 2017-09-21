package com.system.gestion.Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="tb_cargo_opcion")
public class TbCargoOpcion implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tb_id_detalleopcion")
    private Integer Id;
    @OneToOne
    @JoinColumn(name="tb_id_opcion")
    private TbOpcion otbOpcion;
    @OneToOne
    @JoinColumn(name="tb_id_cargo")
    private TbCargo otbCargo;
    @Column(name="tb_cargo_opcion_estado")
    private String Estado;

    public TbCargoOpcion(){}

    public TbCargoOpcion(Integer Id, TbOpcion otbOpcion, TbCargo otbCargo, String Estado) {
        this.Id = Id;
        this.otbOpcion = otbOpcion;
        this.otbCargo = otbCargo;
        this.Estado = Estado;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public TbOpcion getOtbOpcion() {
        return otbOpcion;
    }

    public void setOtbOpcion(TbOpcion otbOpcion) {
        this.otbOpcion = otbOpcion;
    }

    public TbCargo getOtbCargo() {
        return otbCargo;
    }

    public void setOtbCargo(TbCargo otbCargo) {
        this.otbCargo = otbCargo;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }
     
}