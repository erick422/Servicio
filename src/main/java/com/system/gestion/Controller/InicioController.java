package com.system.gestion.Controller;

import com.system.gestion.Model.TbCargoOpcion;
import com.system.gestion.Model.TbUsuario;
import com.system.gestion.Utils.Metodos;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by ERICK on 1/08/2017.
 */

@Controller
public class InicioController {

    @RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public String getwelcomePage(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        TbUsuario user = (TbUsuario) session.getAttribute("usuario");
        evitBackPage(response);
        if (user != null){
            RestaurarMenu(request,"","");
            return "home";
        } else {
            return "login";
        }
    }

    @RequestMapping(value = { "/Home.html" }, method = RequestMethod.GET)
    public String getwelcomeHome(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        TbUsuario user = (TbUsuario) session.getAttribute("usuario");
        evitBackPage(response);
        if (user != null) {
            RestaurarMenu(request,"","");
            return "home";
        } else {
            return "login";
        }
    }

    @RequestMapping(value = {"/login.html"}, method = RequestMethod.GET)
    public String getLogin(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        TbUsuario user = (TbUsuario) session.getAttribute("usuario");
        evitBackPage(response);
        if (user != null){
            RestaurarMenu(request,"","");
            return "home";
        } else {
            return "login";
        }
    }

    @RequestMapping(value = "/logout.html", method = RequestMethod.GET)
    public String getLogout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        evitBackPage(response);
        if (session != null) {
            session.setAttribute("usuario", null);
            session.invalidate();
        }
        return "login";
    }

    private void evitBackPage(HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0);
    }

    private void RestaurarMenu(HttpServletRequest request,String Prin,String Sec){
        HttpSession session = request.getSession();
        TbUsuario usuario = (TbUsuario)session.getAttribute("usuario");
        if (usuario != null) {
            List<TbCargoOpcion> LiPri = usuario.getMenuPri();
            List<TbCargoOpcion> LiSec = usuario.getMenuSec();
            for (int i = 0; i < LiPri.size(); i++){
                if(Prin.equals(LiPri.get(i).getOtbOpcion().getNombre())){LiPri.get(i).getOtbOpcion().setEstado("A");}else{LiPri.get(i).getOtbOpcion().setEstado("H");}
            }
            for (int j = 0; j < LiSec.size(); j++) {
                if(Sec.equals(LiSec.get(j).getOtbOpcion().getNombre())){LiSec.get(j).getOtbOpcion().setEstado("A");}else{LiSec.get(j).getOtbOpcion().setEstado("H");}
            }
        }
    }

    @RequestMapping(value = {"/Archivo.html"}, method = RequestMethod.GET)
    public String WelcomeUsuarios(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        TbUsuario user = (TbUsuario) session.getAttribute("usuario");
        evitBackPage(response);
        if(user != null){
            if(Metodos.VerificarMenu(request,"ARCHIVOS")){
                RestaurarMenu(request,"REPOSITORIOS","ARCHIVOS");
                return "archivo";
            }else{
                return "home";
            }
        }else{
            return "login";
        }
    }

}