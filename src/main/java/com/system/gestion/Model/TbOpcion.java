package com.system.gestion.Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="tb_opcion")
public class TbOpcion implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tb_id_opcion")
    private Integer Id;
    @Column(name="tb_nombre_opc")
    private String Nombre;
    @Column(name="tb_jerarquia")
    private Integer Jerarquia;
    @OneToOne
    @JoinColumn(name="tb_menu_ref_id")
    private TbOpcion otbOpcion;
    @Column(name="tb_img_opcion")
    private String Imagen;
    @Column(name="tb_link")
    private String Link;
    @Column(name="tb_nombre_pag")
    private String NomPagina;
    @Column(name="tb_menu_estado")
    private String Estado;
  
    public TbOpcion(){
        
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

    public TbOpcion getOtbOpcion() {
        return otbOpcion;
    }

    public void setOtbOpcion(TbOpcion otbOpcion) {
        this.otbOpcion = otbOpcion;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String Imagen) {
        this.Imagen = Imagen;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String Link) {
        this.Link = Link;
    }

    public String getNomPagina() {
        return NomPagina;
    }

    public void setNomPagina(String NomPagina) {
        this.NomPagina = NomPagina;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }
    
}