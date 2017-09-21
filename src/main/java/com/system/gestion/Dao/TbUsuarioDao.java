package com.system.gestion.Dao;

import com.system.gestion.Model.TbUsuario;
import org.hibernate.Session;

import java.util.List;

public interface TbUsuarioDao {
    
    public TbUsuario loginUsuario(Session session, TbUsuario usuario);
    /*public List<TbUsuario> ListarUsuarios(Session session, String Descripcion, Integer JerarquiaUsuarioSession, Integer pagina, Integer cantidad, Integer IdCargo, String tipo);
    public Integer CantidadUsuarios(Session session, String descripcion, Integer JerarquiaUsuarioSession, Integer IdCargo);
    public Boolean GuardarUsuario(Session session, TbUsuario usuario);
    public Boolean ModificarUsuario(Session session, TbUsuario usuario);
    public TbUsuario viewUsuarioxUsername(Session session, String username);
    public TbUsuario viewUsuarioxCodigo(Session session, String codVendedor);
    public TbUsuario viewUsuario(Session session, Integer IdUsuario);
    public Boolean ExisteNombreUsuario(Session session, String Username, Integer IdUsuario);
    public Boolean ExisteDNIUsuario(Session session, String Dni, Integer IdUsuario);
    public Boolean ExisteEmailUsuario(Session session, String Email, Integer IdUsuario);
    public Boolean ExisteCodigoUsuario(Session session, String codigo, Integer IdUsuario);
    public Boolean EliminarUsuario(Session session, TbUsuario usuario);
    public Boolean ExisteRelacionPedidoUsuario(Session session, Integer Id);
    public Boolean ExisteRelacionMovimientoUsuario(Session session, Integer Id);
    public Boolean ExisteRelacionCompraUsuario(Session session, Integer Id);
    public Object ActualizarPerfil(Session session, Integer id, String passAntigua, String passNueva, String operacion);*/
}
