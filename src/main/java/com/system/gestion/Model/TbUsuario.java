package com.system.gestion.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="tb_usuario")
public class TbUsuario implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tb_usuario_id", updatable = false)
    private Integer Id;
    @Column(name="tb_dni")
    private String Dni;
    @Column(name="tb_nombres")
    private String Nombres;
    @Column(name="tb_apepat")
    private String ApePat;
    @Column(name="tb_apemat")
    private String ApeMat;
    @Column(name="tb_genero")
    private String Genero;
    @Column(name="tb_email")
    private String Email;
    @Column(name="tb_direccion")
    private String Direccion;
    @Column(name="tb_telefono")
    private String Telefono;
    @Column(name="tb_celular")
    private String Celular;
    @Column(name="tb_rutafoto")
    private String RutaFoto;
    @Column(name="tb_codigo")
    private String codigoVendedor;
    @Column(name="tb_username")
    private String Username;
    @Column(name="tb_password")
    private String Password;
    @OneToOne
    @JoinColumn(name="tb_id_cargo")
    private TbCargo otbCargo;
    @Column(name="tb_estado_con")
    private String EstadoCon;
    @Column(name="tb_estado")
    private String Estado;
    @Transient 
    private List<TbCargoOpcion> MenuPri;
    @Transient 
    private List<TbCargoOpcion> MenuSec;

   // @Transient
    //private TbSucursal oSucursal;
    //tb_ultacceso_usu timestamp without time zone,
    //tb_id_ubigeo integer,
    public TbUsuario() {
    }

    public TbUsuario(Integer Id) {
        this.Id = Id;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getDni() {
        return Dni;
    }

    public void setDni(String Dni) {
        this.Dni = Dni;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String Nombres) {
        this.Nombres = Nombres;
    }

    public String getApePat() {
        return ApePat;
    }

    public void setApePat(String ApePat) {
        this.ApePat = ApePat;
    }

    public String getApeMat() {
        return ApeMat;
    }

    public void setApeMat(String ApeMat) {
        this.ApeMat = ApeMat;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String Genero) {
        this.Genero = Genero;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getCelular() {
        return Celular;
    }

    public void setCelular(String Celular) {
        this.Celular = Celular;
    }

    public String getRutaFoto() {
        return RutaFoto;
    }

    public void setRutaFoto(String RutaFoto) {
        this.RutaFoto = RutaFoto;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public TbCargo getOtbCargo() {
        return otbCargo;
    }

    public void setOtbCargo(TbCargo otbCargo) {
        this.otbCargo = otbCargo;
    }

    public String getEstadoCon() {
        return EstadoCon;
    }

    public void setEstadoCon(String EstadoCon) {
        this.EstadoCon = EstadoCon;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public List<TbCargoOpcion> getMenuPri() {
        return MenuPri;
    }

    public void setMenuPri(List<TbCargoOpcion> MenuPri) {
        this.MenuPri = MenuPri;
    }

    public List<TbCargoOpcion> getMenuSec() {
        return MenuSec;
    }

    public void setMenuSec(List<TbCargoOpcion> MenuSec) {
        this.MenuSec = MenuSec;
    }

    public String getCodigoVendedor() {
        return codigoVendedor;
    }

    public void setCodigoVendedor(String codigoVendedor) {
        this.codigoVendedor = codigoVendedor;
    }

    /*public TbSucursal getoSucursal() {
        return oSucursal;
    }

    public void setoSucursal(TbSucursal oSucursal) {
        this.oSucursal = oSucursal;
    }*/

    @Override
    public String toString() {
        return this.Nombres+" "+this.ApePat+" "+this.ApeMat;
    }
    
    public void setNombreCompleto(TbUsuario usuario){
        this.Nombres = usuario.getNombres();
        this.ApePat = usuario.getApePat();
        this.ApeMat = usuario.getApeMat();
    }
    
}