package com.system.gestion.Controller;

import com.system.gestion.Model.TbCargoOpcion;
import com.system.gestion.Model.TbUsuario;
import com.system.gestion.Service.TbUsuarioService;
import com.system.gestion.Utils.Metodos;
import com.system.gestion.Utils.Respuesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {
    
    @Autowired
    private TbUsuarioService tbUsuarioService;
    
    @RequestMapping(value = {"/login.html"}, method = RequestMethod.POST)
    public String Logeo(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        try{
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String ipv4Publica = Metodos.parsingEmpty(request.getParameter("keykey"));
            String navegador = Metodos.parsingEmpty(request.getParameter("navi"));

            TbUsuario usuario = new TbUsuario();
            usuario.setUsername(username); 
            usuario.setPassword(password);
            //usuario.setDni(ipv4Publica);
           // usuario.setRutaFoto(navegador);

            evitBackPage(response);
            TbUsuario login = tbUsuarioService.loginUsuario(usuario);
            if(login != null){
                session.setAttribute("usuario", login);
                return "home";
            }else{
                request.setAttribute("error", "ERROR");
                request.setAttribute("msj_login","NO EXISTE USUARIO.");
                return "login";
            }
        }catch(Exception e){
            request.setAttribute("error", "ERROR");
            request.setAttribute("msj_login",e.getMessage());
            return "login";
        }
    }
    
    @RequestMapping(value = "/logout.html", method = RequestMethod.POST)
    public String getSalir(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        evitBackPage(response);
        if (session != null) {
            session.setAttribute("usuario", null);
            session.invalidate();
            //request.setAttribute("msj_logout", "OK");
        }
        return "login";
    }
    
    @RequestMapping(value = "/upload_menu", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> getMenus(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> records = new HashMap<String, Object>();
        try{
            HttpSession session = request.getSession();
            TbUsuario usuario = (TbUsuario)session.getAttribute("usuario");
            evitBackPage(response);
            String html = "";
            String submenu = "";
            if (usuario != null) {
                List<TbCargoOpcion> LiPri = usuario.getMenuPri();
                List<TbCargoOpcion> LiSec = usuario.getMenuSec();
                for (int i = 0; i < LiPri.size(); i++) {
                    submenu = "";
                    for (int j = 0; j < LiSec.size(); j++) {
                        if(LiPri.get(i).getOtbOpcion().getId().intValue() == LiSec.get(j).getOtbOpcion().getOtbOpcion().getId() && LiSec.get(j).getEstado().equals("H")){
                            submenu+="<li class='"+(LiSec.get(j).getOtbOpcion().getEstado().equals("A")?"active":"")+"'>";
                            submenu+="<a href=\""+LiSec.get(j).getOtbOpcion().getLink()+"\">"+LiSec.get(j).getOtbOpcion().getNomPagina()+"</a>";
                            submenu+="</li>";
                        }
                    }
                    if(!submenu.equals("")){
                        html+="<li class='"+(LiPri.get(i).getOtbOpcion().getEstado().equals("A")?"active":"")+"'>";
                        html+="<a href='#'>";
                        html+="<i class=\"icon-stack2\"></i> ";
                        html+="<span >"+LiPri.get(i).getOtbOpcion().getNomPagina()+"</span>";
                        html+="</a>";

                        html+="<ul>"+submenu+"</ul>";
                        html+="</li>";
                    }
                }
            }
            records.put("menu",html);
        }catch(Exception e){
            records.put("error",e.getMessage());
        }
        return records;
    }

    private void evitBackPage(HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0);
    }

   /* @RequestMapping(value = "/save_myperfil", method = RequestMethod.POST)
    public @ResponseBody
    Respuesta CambiarClave(HttpServletRequest request, HttpServletResponse response){
        Respuesta respuesta = new Respuesta();
        try{
            HttpSession session = request.getSession();
            TbUsuario usuario = (TbUsuario)session.getAttribute("usuario");
            String Anterior = Metodos.parsingEmpty(request.getParameter("anterior"));
            String Nueva = Metodos.parsingEmpty(request.getParameter("nueva"));
            String ReNueva = Metodos.parsingEmpty(request.getParameter("renueva"));
            respuesta.setDato("ERROR");
            respuesta.setListado(this.ValidarData(Anterior,Nueva,ReNueva));
            if(respuesta.getListado().isEmpty()){
                if (usuario != null) {
                    Object dato = tbUsuarioService.ActualizarPerfil(usuario.getId(),Anterior,Nueva);
                    if (dato.toString().equals("1")) {
                        respuesta.setDato("OK");
                        respuesta.setMsj("La Clave se cambio correctamente.");
                    }else if(dato.toString().equals("4")){
                        respuesta.setMsj("La Clave Anterior es Incorrecta.");
                    }else{
                        respuesta.setMsj("Problemas con la actualizacion.");
                    }
                } else {
                    respuesta.setMsj("Usuario no ha Iniciado Session.");
                }
            }
        }catch(Exception e){
            respuesta.setDato("ERROR");
            respuesta.setMsj(e.getMessage());
        }
        return respuesta;
    }*/

    private List<String> ValidarData(String Anterior,String Nueva,String Renueva){
        List<String> errores = new ArrayList<String>();

        if(Anterior == null){
            errores.add("E1");
        }else if(Anterior.equals("")){
            errores.add("E1");
        }
        if(Nueva == null){
            errores.add("E2");
        }else if(Nueva.equals("")){
            errores.add("E2");
        }
        if(Renueva == null){
            errores.add("E3");
        }else if(Renueva.equals("")){
            errores.add("E3");
        }
        if(Nueva != null && Renueva != null){
            if(!Nueva.equals("") && !Renueva.equals("")){
                if(!Renueva.equals(Nueva)){
                    errores.add("E4");
                }
            }
        }
        return errores;
    }

    @RequestMapping(value = "/reload_session", method = RequestMethod.POST)
    public @ResponseBody
    Respuesta ReloadSession(HttpServletRequest request, HttpServletResponse response){
        Respuesta respuesta = new Respuesta();
        try{
            HttpSession session = request.getSession();
            TbUsuario usuario = (TbUsuario)session.getAttribute("usuario");
            respuesta.setDato("ERROR");
            if(usuario != null){
                respuesta.setDato("OK");
            }else{
                respuesta.setMsj("Su Session ha Caducado, cierre e inicie session nuevamente.");
            }
        }catch(Exception e){
            respuesta.setDato("ERROR");
            respuesta.setMsj(e.getMessage());
        }
        return respuesta;
    }
    
}