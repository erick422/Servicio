package com.system.gestion.Dao;

import com.system.gestion.Model.TbUsuario;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.jdbc.ReturningWork;
import org.springframework.stereotype.Repository;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository("tbUsuarioDao")
public class TbUsuarioDaoImp implements TbUsuarioDao {

    public TbUsuario loginUsuario(Session session,TbUsuario usuario) {
        TbUsuario usuarioLogin = null;
        try{
            usuarioLogin = (TbUsuario)session.createQuery("from TbUsuario Where upper(Username)='"+usuario.getUsername().toUpperCase()+"'").uniqueResult();
        }catch(HibernateException e){
            Logger.getLogger(TbUsuarioDaoImp.class.getName()).log(Level.SEVERE, null, e);
            throw e;
        }
        return usuarioLogin;
    }

   /* public List<TbUsuario> ListarUsuarios(Session session, String Descripcion,Integer JerarquiaUsuarioSession, Integer pagina, Integer cantidad,Integer IdCargo, String tipo) {
        List<TbUsuario> lista = new ArrayList<TbUsuario>();
        try{
            Query query = null;
            if(tipo.equals("1")){
                String sql = "from TbUsuario Where otbCargo.Jerarquia >= :jerarquia AND upper(Nombres) like :searchDes";
                if(IdCargo!=0){sql = sql + " AND otbCargo.Id=:idcargo";}
                sql = sql + " Order By Nombres";
                query = session.createQuery(sql);
                query.setParameter("jerarquia",JerarquiaUsuarioSession);
                query.setParameter("searchDes","%"+Descripcion.toUpperCase()+"%");
                if(IdCargo!=0){query.setParameter("idcargo",IdCargo);}
                query.setMaxResults(cantidad);
                query.setFirstResult(pagina);
            }else{
                query = session.createQuery("from TbUsuario Where otbCargo.Jerarquia >= :jerarquia Order By Nombres");
                query.setParameter("jerarquia",JerarquiaUsuarioSession);
            }
            lista = query.list();
        }catch(HibernateException e){
            Logger.getLogger(TbUsuarioDaoImp.class.getName()).log(Level.SEVERE, null, e);
            throw e;
        }
        return lista;
    }

    @Override
    public Integer CantidadUsuarios(Session session, String Descripcion,Integer IdCargo,Integer JerarquiaUsuarioSession) {
        Integer cantidad  = 0;
        try{
            String sql = "select count(Id) from TbUsuario where otbCargo.Jerarquia >= :jerarquia AND upper(Nombres) like :searchDes";
            if(IdCargo!=0){sql = sql + " AND otbCargo.Id=:idcargo";}
            Query query = session.createQuery(sql);
            query.setParameter("jerarquia",JerarquiaUsuarioSession);
            query.setParameter("searchDes","%"+Descripcion.toUpperCase()+"%");
            if(IdCargo!=0){query.setParameter("idcargo",IdCargo);}
            cantidad = ((Long)query.uniqueResult()).intValue();
        }catch(HibernateException e){
            Logger.getLogger(TbUsuarioDaoImp.class.getName()).log(Level.SEVERE, null, e);
            throw e;
        }
        return cantidad;
    }

    @Override
    public Boolean GuardarUsuario(Session session, TbUsuario usuario) {
        Boolean seguardo = false;
        try{
            session.save(usuario);
            seguardo = true;
        }catch(HibernateException e){
            Logger.getLogger(TbUsuarioDaoImp.class.getName()).log(Level.SEVERE, null, e);
            throw e;
        }
        return seguardo;
    }

    @Override
    public Boolean ExisteNombreUsuario(Session session, String Username, Integer IdUsuario) {
        Boolean existe = false;
        try{
            Query query = session.createQuery("select count(Id) from TbUsuario where upper(Username)=:user AND Id!=:idusuario");
            query.setParameter("user",Username.toUpperCase());
            query.setParameter("idusuario",IdUsuario);
            Integer cantidad = ((Long)query.uniqueResult()).intValue();
            if(cantidad != 0){
                existe = true;
            }
        }catch(HibernateException e){
            Logger.getLogger(TbUsuarioDaoImp.class.getName()).log(Level.SEVERE, null, e);
            throw e;
        }
        return existe;
    }

    @Override
    public Boolean ExisteDNIUsuario(Session session, String Dni, Integer IdUsuario) {
        Boolean existe = false;
        try{
            Query query = session.createQuery("select count(Id) from TbUsuario where Dni=:dni AND Id<>:idusuario");
            query.setParameter("dni",Dni);
            query.setParameter("idusuario",IdUsuario);
            Integer cantidad = ((Long)query.uniqueResult()).intValue();
            if(cantidad != 0){
                existe = true;
            }
        }catch(HibernateException e){
            Logger.getLogger(TbUsuarioDaoImp.class.getName()).log(Level.SEVERE, null, e);
            throw e;
        }
        return existe;
    }

    @Override
    public Boolean ExisteEmailUsuario(Session session, String Email, Integer IdUsuario) {
        Boolean existe = false;
        try{
            if(Email != null){
                Query query = session.createQuery("select count(Id) from TbUsuario where Email=:email AND Id<>:idusuario");
                query.setParameter("email",Email);
                query.setParameter("idusuario",IdUsuario);
                Integer cantidad = ((Long)query.uniqueResult()).intValue();
                if(cantidad != 0){
                    existe = true;
                }
            }
        }catch(HibernateException e){
            Logger.getLogger(TbUsuarioDaoImp.class.getName()).log(Level.SEVERE, null, e);
            throw e;
        }
        return existe;
    }

    @Override
    public Boolean ExisteCodigoUsuario(Session session, String codigo, Integer IdUsuario) {
        Boolean existe = false;
        try{
            if(codigo != null){
                Query query = session.createQuery("select count(Id) from TbUsuario where codigoVendedor=:codven AND Id<>:idusuario");
                query.setParameter("codven",codigo);
                query.setParameter("idusuario",IdUsuario);
                Integer cantidad = ((Long)query.uniqueResult()).intValue();
                if(cantidad != 0){
                    existe = true;
                }
            }
        }catch(HibernateException e){
            Logger.getLogger(TbUsuarioDaoImp.class.getName()).log(Level.SEVERE, null, e);
            throw e;
        }
        return existe;
    }

    @Override
    public TbUsuario viewUsuarioxUsername(Session session,String username) {
        TbUsuario usuario = null;
        try{
            usuario = (TbUsuario)session.createQuery("from TbUsuario Where Username='"+username+"'").uniqueResult();
            return usuario;
        }catch(HibernateException e){
            Logger.getLogger(TbUsuarioDaoImp.class.getName()).log(Level.SEVERE, null, e);
            throw e;
        }
    }

    @Override
    public TbUsuario viewUsuarioxCodigo(Session session, String codVendedor) {
        TbUsuario usuario = null;
        try{
            usuario = (TbUsuario)session.createQuery("from TbUsuario Where codigoVendedor='"+codVendedor+"'").uniqueResult();
            return usuario;
        }catch(HibernateException e){
            Logger.getLogger(TbUsuarioDaoImp.class.getName()).log(Level.SEVERE, null, e);
            throw e;
        }
    }

    @Override
    public TbUsuario viewUsuario(Session session, Integer IdUsuario) {
        try{
            TbUsuario usuario = session.get(TbUsuario.class, IdUsuario);
            return usuario;
        }catch(HibernateException e){
            Logger.getLogger(TbUsuarioDaoImp.class.getName()).log(Level.SEVERE, null, e);
            throw e;
        }
    }

    @Override
    public Boolean ModificarUsuario(Session session, TbUsuario usuario) {
        Boolean seguardo = false;
        try{
            session.saveOrUpdate(usuario);
            seguardo = true;
        }catch(HibernateException e){
            Logger.getLogger(TbUsuarioDaoImp.class.getName()).log(Level.SEVERE, null, e);
            throw e;
        }
        return seguardo;
    }

    @Override
    public Boolean EliminarUsuario(Session session, TbUsuario usuario) {
        Boolean seguardo = false;
        try{
            session.delete(usuario);
            seguardo = true;
        }catch(HibernateException e){
            Logger.getLogger(TbUsuarioDaoImp.class.getName()).log(Level.SEVERE, null, e);
            throw e;
        }
        return seguardo;
    }

    @Override
    public Boolean ExisteRelacionPedidoUsuario(Session session, Integer Id) {
        Boolean seborra = false;
        try{
            Query query = session.createQuery("select count(Id) from TbPedido where otbUsuario.Id=:idusu");
            query.setParameter("idusu",Id);
            Integer cantidad = ((Long)query.uniqueResult()).intValue();
            if(cantidad != 0){
                seborra = true;
            }
        }catch(HibernateException e){
            Logger.getLogger(TbUsuarioDaoImp.class.getName()).log(Level.SEVERE, null, e);
            throw e;
        }
        return seborra;
    }

    @Override
    public Boolean ExisteRelacionMovimientoUsuario(Session session, Integer Id) {
        Boolean seborra = false;
        try{
            Query query = session.createQuery("select count(Id) from TbMovimiento where otbUsuarioGenera.Id=:idusu or otbUsuarioEnvia.Id=:idusu or otbUsuarioTransporta.Id=:idusu or otbUsuarioRecibe.Id=:idusu");
            query.setParameter("idusu",Id);
            Integer cantidad = ((Long)query.uniqueResult()).intValue();
            if(cantidad != 0){
                seborra = true;
            }
        }catch(HibernateException e){
            Logger.getLogger(TbUsuarioDaoImp.class.getName()).log(Level.SEVERE, null, e);
            throw e;
        }
        return seborra;
    }

    @Override
    public Boolean ExisteRelacionCompraUsuario(Session session, Integer Id) {
        Boolean seborra = false;
        try{
            Query query = session.createQuery("select count(Id) from TbCompra where otbUsuario.Id=:idusu");
            query.setParameter("idusu",Id);
            Integer cantidad = ((Long)query.uniqueResult()).intValue();
            if(cantidad != 0){
                seborra = true;
            }
        }catch(HibernateException e){
            Logger.getLogger(TbUsuarioDaoImp.class.getName()).log(Level.SEVERE, null, e);
            throw e;
        }
        return seborra;
    }

    @Override
    public Object ActualizarPerfil(Session session,final Integer id,final String passAntigua,final String passNueva,final String operacion) {
        return session.doReturningWork(new ReturningWork<Object>() {
            public Object execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall(" { ? = call update_perfil(?,?,?,?)}");
                    cst.registerOutParameter(1, Types.INTEGER);
                    cst.setObject(2,operacion,Types.VARCHAR);
                    cst.setObject(3,id,Types.INTEGER);
                    cst.setObject(4,passAntigua,Types.VARCHAR);
                    cst.setObject(5,passNueva,Types.VARCHAR);
                    cst.execute();
                    Object dato = cst.getInt(1);
                    return dato;
                }catch ( HibernateException e) {
                    ///log.debug("Ocurrio una excepcion "+e.getMessage());
                    throw e;
                }
            }
        });
    }*/

}