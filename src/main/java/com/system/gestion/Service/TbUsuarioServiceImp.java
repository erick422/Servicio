package com.system.gestion.Service;

/*import com.system.gestion.Dao.TbAsignacionDao;
import com.system.gestion.Dao.TbCargoDao;
import com.system.gestion.Dao.TbCargoOpcionDao;*/
import com.system.gestion.Dao.TbCargoOpcionDao;
import com.system.gestion.Dao.TbUsuarioDao;
import com.system.gestion.Model.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service("tbUsuarioService")
public class TbUsuarioServiceImp implements TbUsuarioService{
    
    @Autowired
    private TbUsuarioDao tbUsuarioDao;
   /* @Autowired
    private TbAsignacionDao tbAsignacionDao;*/
    @Autowired
    private TbCargoOpcionDao tbCargoOpcionDao;
   // @Autowired
   // private TbCargoDao tbCargoDao;
    @Autowired
    private SessionFactory sessionFactory;

    public TbUsuario loginUsuario(TbUsuario usuario) throws Exception{
        Session session = null;
        TbUsuario login = null;
        try{
            session = sessionFactory.openSession();
            session.getTransaction().begin();
            login = tbUsuarioDao.loginUsuario(session,usuario);
            
            if(login!=null){
                if(usuario.getPassword().toUpperCase().equals(login.getPassword().toUpperCase())){ 
                    if(login.getEstado().equals("H")){
                        /*TbAccesoSistema acceso = new TbAccesoSistema();
                        acceso.setIngreso(Calendar.getInstance(TimeZone.getTimeZone("America/Bogota")));
                        acceso.getIngreso().add(Calendar.HOUR, -5);
                        acceso.setIpv4Publica(usuario.getDni());
                        acceso.setNavegador(usuario.getRutaFoto());
                        acceso.setEstado("H");
                        acceso.setOtbUsuario(new TbUsuario(login.getId())); */
                        if(login.getOtbCargo().getJerarquia() < 2){
                            login.setMenuPri(tbCargoOpcionDao.ListarOpciones(session,0,login.getOtbCargo()));
                            login.setMenuSec(tbCargoOpcionDao.ListarOpciones(session,1,login.getOtbCargo()));
                           // session.save(acceso);
                        }else{
                           /* if(!tbAsignacionDao.ListarAsignacionesxIdUsuario(session,login.getId()).isEmpty()){
                                login.setMenuPri(tbCargoOpcionDao.ListarOpciones(session,0,login.getOtbCargo()));
                                login.setMenuSec(tbCargoOpcionDao.ListarOpciones(session,1,login.getOtbCargo()));
                                session.save(acceso);
                            }else{
                                throw new Exception("NO HAS SIDO ASIGNADO A UNA SUCURSAL.");
                            }*/
                        }
                    }else{
                        throw new Exception("USUARIO INACTIVO.");
                    }
                }else{
                    throw new Exception("CONTRASEÑA INCORRECTA.");
                }
            }else{
                throw new Exception("NO EXISTE USUARIO.");
            }
            session.getTransaction().commit();
        }catch(Exception e){
            Logger.getLogger(TbUsuarioServiceImp.class.getName()).log(Level.SEVERE, null, e);
            session.getTransaction().rollback();
            throw e;
        }finally {
            session.close();
        }
        return login;
    }

   /* @Override
    public Object[] ListarUsuarios(String descripcion,Integer JerarquiaUsuarioSession, Integer pagina, Integer cantidad,Integer IdCargo) {
        Session session = null;
        Object[] datos = new Object[2];
        try{
            session = sessionFactory.openSession();
            session.getTransaction().begin();
            datos[0] = tbUsuarioDao.ListarUsuarios(session,descripcion,JerarquiaUsuarioSession,pagina,cantidad,IdCargo,"1");
            datos[1] = tbUsuarioDao.CantidadUsuarios(session,descripcion,JerarquiaUsuarioSession,IdCargo);
            session.getTransaction().commit();
            session.close();
        }catch(HibernateException e){
            Logger.getLogger(TbUsuarioServiceImp.class.getName()).log(Level.SEVERE, null, e);
            session.getTransaction().rollback();
            session.close();
            throw e;
        }
        return datos;
    }

    @Override
    public String GuardarUsuario(TbUsuario usuario, String[] asignaciones,Integer Jerarquia) {
        Session session = null;
        String respuesta = "";
        try{
            session = sessionFactory.openSession();
            session.getTransaction().begin();
            
            Boolean existe = tbUsuarioDao.ExisteNombreUsuario(session,usuario.getUsername(),usuario.getId());
            
            if(!existe){
                existe = tbUsuarioDao.ExisteDNIUsuario(session,usuario.getDni(),usuario.getId());
                if(!existe){
                    existe = tbUsuarioDao.ExisteEmailUsuario(session,usuario.getEmail(),usuario.getId());
                    if(!existe){
                        Boolean seregistro = tbUsuarioDao.GuardarUsuario(session,usuario);
                        if(seregistro){
                            TbUsuario user = tbUsuarioDao.viewUsuarioxUsername(session,usuario.getUsername());
                            TbCargo cargo = tbCargoDao.viewCargo(session,usuario.getOtbCargo().getId());
                            if(cargo.getJerarquia()>=Jerarquia){
                                if(cargo.getJerarquia() >=3){
                                    if(asignaciones.length != 0){
                                        for (int i = 0; i < asignaciones.length; i++) {
                                            TbAsignacion asignacion = new TbAsignacion();
                                            asignacion.setId(0); 
                                            asignacion.setFechaAsig(new Date());
                                            asignacion.setOtbUsuario(user);
                                            asignacion.setOtbSucursal(new TbSucursal(Integer.parseInt(asignaciones[i])));
                                            asignacion.setEstado("H");

                                            tbAsignacionDao.GuardarAsignacion(session,asignacion);
                                        }
                                        respuesta = "OK";
                                    }else{
                                        respuesta = "Seleccione al menos una sucursal.";
                                    }
                                }else{
                                    respuesta = "OK";
                                }
                            }else{
                                respuesta = "No Tiene Permisos para registrar Usuario con este Cargo.";
                            }
                        }else{
                            respuesta = "Problemas al registrar Usuario.";
                        }
                    }else{
                        respuesta = "Ya Existe el Email del Usuario."; 
                    }
                }else{
                   respuesta = "Ya Existe el DNI del Usuario."; 
                }
            }else{
                respuesta = "Ya Existe el Nombre de Usuario.";
            }
            if(respuesta.equals("OK")){session.getTransaction().commit();}else{session.getTransaction().rollback();}
            session.close();
        }catch(Exception e){
            Logger.getLogger(TbUsuarioServiceImp.class.getName()).log(Level.SEVERE, null, e);
            session.getTransaction().rollback();
            session.close();
            throw e;
        }
        return respuesta;
    }

    @Override
    public TbUsuario viewUsuario(Integer IdUsuario) {
        Session sesion = null;
        try{
            sesion = sessionFactory.openSession();
            sesion.beginTransaction();
            TbUsuario usuario = tbUsuarioDao.viewUsuario(sesion,IdUsuario);
            usuario.setTbListAsig(tbAsignacionDao.ListarAsignacionesxIdUsuario(sesion,IdUsuario));
            sesion.getTransaction().commit();
            sesion.close();
            return usuario;
        }catch(Exception e){
            Logger.getLogger(TbUsuarioServiceImp.class.getName()).log(Level.SEVERE, null, e);
            sesion.getTransaction().rollback();
            sesion.close();
            throw e;
        }
    }

    @Override
    public String ModificarUsuario(TbUsuario usuario, String[] asignaciones, Integer Jerarquia) {
        String respuesta = "";
        Session session = null;
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            Boolean existe = tbUsuarioDao.ExisteNombreUsuario(session,usuario.getUsername(),usuario.getId());
            
            if(!existe){
                existe = tbUsuarioDao.ExisteDNIUsuario(session,usuario.getDni(),usuario.getId());
                if(!existe){
                    existe = tbUsuarioDao.ExisteEmailUsuario(session,usuario.getEmail(),usuario.getId());
                    if(!existe){
                        existe = tbUsuarioDao.ExisteCodigoUsuario(session,usuario.getCodigoVendedor(),usuario.getId());
                        if(!existe) {
                            TbUsuario usuarioM = (TbUsuario) session.load(TbUsuario.class, usuario.getId());
                            usuarioM.setDni(usuario.getDni());
                            usuarioM.setNombres(usuario.getNombres());
                            usuarioM.setApePat(usuario.getApePat());
                            usuarioM.setApeMat(usuario.getApeMat());
                            usuarioM.setGenero(usuario.getGenero());
                            usuarioM.setEmail(usuario.getEmail());
                            usuarioM.setDireccion(usuario.getDireccion());
                            usuarioM.setTelefono(usuario.getTelefono());
                            usuarioM.setCelular(usuario.getCelular());
                            usuarioM.setCodigoVendedor(usuario.getCodigoVendedor());
                            usuarioM.setUsername(usuario.getUsername());
                            usuarioM.setPassword(usuario.getPassword());
                            usuarioM.setOtbCargo(usuario.getOtbCargo());
                            usuarioM.setEstado(usuario.getEstado());

                            Boolean semodifico = tbUsuarioDao.ModificarUsuario(session, usuarioM);
                            if (semodifico) {
                                TbUsuario user = tbUsuarioDao.viewUsuarioxUsername(session, usuarioM.getUsername());
                                TbCargo cargo = tbCargoDao.viewCargo(session, usuarioM.getOtbCargo().getId());
                                if (cargo.getJerarquia() >= Jerarquia) {
                                    if (cargo.getJerarquia() >= 3) {
                                        if (asignaciones.length != 0) {
                                            tbAsignacionDao.EliminarAsignacionesxUsuario(session, usuarioM.getId());
                                            for (int i = 0; i < asignaciones.length; i++) {
                                                TbAsignacion asignacion = new TbAsignacion(0);
                                                asignacion.setFechaAsig(new Date());
                                                asignacion.setOtbUsuario(user);
                                                asignacion.setOtbSucursal(new TbSucursal(Integer.parseInt(asignaciones[i])));
                                                asignacion.setEstado("H");

                                                tbAsignacionDao.GuardarAsignacion(session, asignacion);
                                            }
                                            respuesta = "OK";
                                        } else {
                                            respuesta = "Seleccione al menos una sucursal.";
                                        }
                                    } else {
                                        respuesta = "OK";
                                    }
                                } else {
                                    respuesta = "No Tiene Permisos para registrar Usuario con este Cargo.";
                                }
                            } else {
                                respuesta = "Problemas al Modificar Usuario.";
                            }
                        }else{
                            respuesta = "Ya Existe el Codigo de Vendedor del Usuario.";
                        }
                    }else{
                        respuesta = "Ya Existe el Email del Usuario."; 
                    }
                }else{
                   respuesta = "Ya Existe el DNI del Usuario."; 
                }
            }else{
                respuesta = "Ya Existe el Nombre de Usuario.";
            }
            if(respuesta.equals("OK")){session.getTransaction().commit();}else{session.getTransaction().rollback();}
            session.close();
        }catch(Exception e){
            Logger.getLogger(TbUsuarioServiceImp.class.getName()).log(Level.SEVERE, null, e);
            session.getTransaction().rollback();
            session.close();
            throw e;
        }
        return respuesta;
    }

    @Override
    public String EliminarUsuario(TbUsuario usuario) {
        String respuesta = "";
        Session session = null;
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            Boolean existe = tbUsuarioDao.ExisteRelacionPedidoUsuario(session,usuario.getId());
            if(!existe){
                existe = tbUsuarioDao.ExisteRelacionCompraUsuario(session,usuario.getId());
                if(!existe){
                    existe = tbUsuarioDao.ExisteRelacionMovimientoUsuario(session,usuario.getId());
                    if(!existe){
                        tbAsignacionDao.EliminarAsignacionesxUsuario(session,usuario.getId());
                        if(tbUsuarioDao.EliminarUsuario(session,usuario)){
                            respuesta = "OK";
                        }else{
                            respuesta = "Problemas al Eliminar Usuario.";
                        }
                    }else{
                        respuesta = "Este Usuario tiene registros de Movimiento de Almacen relacionados, imposible eliminar."; 
                    }
                }else{
                   respuesta = "Este Usuario tiene registros de Compras relacionados, imposible eliminar."; 
                }
            }else{
                respuesta = "Este Usuario tiene registros de Ventas relacionados, imposible eliminar.";
            }
            if(respuesta.equals("OK")){session.getTransaction().commit();}else{session.getTransaction().rollback();}
            session.close();
        }catch(Exception e){
            Logger.getLogger(TbUsuarioServiceImp.class.getName()).log(Level.SEVERE, null, e);
            session.getTransaction().rollback();
            session.close();
            throw e;
        }
        return respuesta;
    }

    @Override
    public Object ActualizarPerfil(Integer id, String passAntigua, String passNueva) {
        Object seguardo;
        Session sesion = null;
        try{
            sesion = sessionFactory.openSession();
            sesion.beginTransaction();
            seguardo = tbUsuarioDao.ActualizarPerfil(sesion, id,passAntigua,passNueva,"U");
            sesion.getTransaction().commit();
        }catch(Exception e){
            Logger.getLogger(TbMarcaServiceImp.class.getName()).log(Level.SEVERE, null, e);
            sesion.getTransaction().rollback();
            throw e;
        }finally {
            sesion.close();
        }
        return seguardo;
    }
*/
}
