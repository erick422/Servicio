package com.system.gestion.Dao;

import com.system.gestion.Model.TbCargo;
import com.system.gestion.Model.TbCargoOpcion;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by ERICK on 2/08/2017.
 */
@Repository("tbCargoOpcionDao")
public class TbCargoOpcionDaoImp implements TbCargoOpcionDao{

    public List<TbCargoOpcion> ListarOpciones(Session session, Integer jerarquia, TbCargo cargo) {
        List<TbCargoOpcion> lista = new ArrayList<TbCargoOpcion>();
        try{
            Query query = session.createQuery("from TbCargoOpcion c Where c.otbCargo=? AND c.otbOpcion.Jerarquia=?");
            query.setLong(0, cargo.getId());
            query.setLong(1,jerarquia);
            lista =  query.list();
        }catch(HibernateException e){
            Logger.getLogger(TbCargoOpcionDaoImp.class.getName()).log(Level.SEVERE, null, e);
            throw e;
        }
        return lista;
    }

    public Boolean EliminarCargoOpcionesxIdCargo(Session session, Integer IdCargo) throws Exception {
        Boolean seborro = false;
        try{
            Query query = session.createQuery("delete from TbCargoOpcion where otbCargo.Id=:idcargo");
            query.setParameter("idcargo",IdCargo);
            Integer resultado = query.executeUpdate();
            if(resultado > 0){
                seborro = true;
            }
        }catch(Exception e){
            Logger.getLogger(TbCargoOpcionDaoImp.class.getName()).log(Level.SEVERE, null, e);
            throw e;
        }
        return seborro;
    }

}