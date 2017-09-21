package com.system.gestion.Dao;

import com.system.gestion.Model.TbCargo;
import com.system.gestion.Model.TbCargoOpcion;
import org.hibernate.Session;
import java.util.List;

/**
 * Created by ERICK on 2/08/2017.
 */
public interface TbCargoOpcionDao {

    public List<TbCargoOpcion> ListarOpciones(Session session, Integer jerarquia, TbCargo cargo);
    public Boolean EliminarCargoOpcionesxIdCargo(Session session,Integer IdCargo) throws Exception;

}