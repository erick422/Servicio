package com.system.gestion.Service;

import com.system.gestion.Model.TbUsuario;

public interface TbUsuarioService {
    
    public TbUsuario loginUsuario(TbUsuario usuario) throws Exception;
    /*public Object[] ListarUsuarios(String descripcion, Integer JerarquiaUsuarioSession, Integer pagina, Integer cantidad, Integer IdCargo);
    public String GuardarUsuario(TbUsuario usuario, String[] asignaciones, Integer Jerarquia);
    public String ModificarUsuario(TbUsuario usuario, String[] asignaciones, Integer Jerarquia);
    public TbUsuario viewUsuario(Integer IdUsuario);
    public String EliminarUsuario(TbUsuario usuario);
    public Object ActualizarPerfil(Integer id, String passAntigua, String passNueva);*/
}