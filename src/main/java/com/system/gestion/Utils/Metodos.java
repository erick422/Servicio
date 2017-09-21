package com.system.gestion.Utils;

import com.system.gestion.Model.TbCargoOpcion;
import com.system.gestion.Model.TbUsuario;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class Metodos {

    public static Boolean VerificarMenu(HttpServletRequest request, String menuPrincipal){
        HttpSession session = request.getSession();
        TbUsuario usuario = (TbUsuario)session.getAttribute("usuario");
        if (usuario != null) {
            List<TbCargoOpcion> LiPri = usuario.getMenuSec();
            for (int i = 0; i < LiPri.size(); i++){
                if(menuPrincipal.equals(LiPri.get(i).getOtbOpcion().getNombre())){
                    if(LiPri.get(i).getEstado().equals("H")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /*public static String GenerarDigito(){
        String num = "";
        try{
            Integer dig = 0;
            while (dig == 0){
                dig = (int)(Math.random()*(10));
                num = dig.toString();
            }
            for (int i = 0; i < 6; i++) {
                dig = (int)(Math.random()*(10));
                num+=dig.toString();
            }
        }catch(Exception e){
            throw e;
        }
        return num;
    }*/
    
    public static Date stringToDate(String sFecha,String sFormat)throws Exception{
        try {
            if (sFecha==null) {
                return null;
            }
            SimpleDateFormat sdf=new SimpleDateFormat(sFormat);
            return sdf.parse(sFecha);
        } catch (ParseException e) {
           return null;
        }
    }
    
    public static String dateToString(Date dFecha,String sFormat)throws Exception{
        try {
            if (dFecha==null) {
                return null;
            }
            SimpleDateFormat sdf=new SimpleDateFormat(sFormat);
            return sdf.format(dFecha);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public static boolean verificarNumero(String cadena) {//SOLO NUMEROS DE 9 DIGITOS COMO MAXIMO
        Boolean esnumero = true;
        try{
            Integer.parseInt(cadena);
        }catch(Exception e){
            esnumero = false;
        }
        return esnumero;
    }
    
    public static boolean verificarNumeroLargo(String cadena) {//NUMERO DE DIGITOS MUY LARGO
        cadena = cadena != null && !cadena.equals("") ? cadena : "_";
        boolean validacion = true;
        char[] numeros = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
        for (int i = 0; i < cadena.length(); i++) {
            char caracter = cadena.charAt(i);
            boolean verificar = false;
            for (int j = 0; j < numeros.length; j++) {
                if (caracter == numeros[j]) {
                    verificar = true;
                }
            }
            validacion = verificar;
            if (!verificar) {
                break;
            }
        }
        return validacion;
    }
    
    public static boolean verificarNumeroDecimal(String cadena) {
        Boolean esnumero = true;
        try{
            Double.parseDouble(cadena);
        }catch(Exception e){
            esnumero = false;
        }
        return esnumero;
    }
    
    public static String parsingEmpty(String cadena) throws Exception {
        try {
            if (cadena==null) {
                return null;
            }
            return cadena.trim().equals("")?null:cadena.trim();
        } catch (Exception e) {
            throw e;
        }
    }
    
    /*public static String parsingEmptyUppercase(String cadena){
        try {
            if (cadena==null) {
                return null;
            }
            return cadena.trim().equals("")?null:cadena.trim().toUpperCase();
        } catch (Exception e) {
            throw e;
        }
    }*/
    
    public static String RetornarStringConDosDecimales(Double numero){
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat formato = new DecimalFormat("0.00",simbolos);
        return formato.format(numero);
    }
    
    public static boolean isValidEmailAddress(String email) {
        if (email == null) {
            return false;
        }
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
    
    public static boolean isSoloLetras(String frase) {
        if (frase == null) {
            return false;
        }
        String ePattern = "^[A-Za-z]+";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(frase);
        return m.matches();
    } 
    
    public static String returnStringHoraxFecha(Date fecha) {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss"); 
        return dateFormat.format(fecha.getTime());
    }

    public static ByteArrayOutputStream convertPDFToByteArrayOutputStream(String fileName) {
        InputStream inputStream = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            inputStream = new FileInputStream(fileName);
            byte[] buffer = new byte[1024];
            baos = new ByteArrayOutputStream();

            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
        }catch( FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        }finally{
            if(inputStream != null){
                try{
                    inputStream.close();
                }catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return baos;
    }

    public static boolean existsDirectoryOrFile(String path){
        try {
            //if (path == null) {
            //    return false;
           // }
            File directory = new File(path);
            return directory.exists();
        } catch (Exception e) {
            return false;
        }
    }

    public static String stringFormatDateCurrent() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd-HH.mm.ss");
        //get current date time with Date()
        Date date = new Date();
       /// System.out.println(dateFormat.format(date));

        //get current date time with Calendar()
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }

    public static String obtenerTamanioFile(float tamanioBytes) {
        DecimalFormat df = new DecimalFormat("#.00");
        String tamanio;

        if(tamanioBytes>1024000000)
            tamanio = df.format(tamanioBytes/1024000000) + " Gb";
        else if(tamanioBytes>1024000)
            tamanio = df.format(tamanioBytes/1024000) + " Mb";
        else if(tamanioBytes>1024)
            tamanio = df.format(tamanioBytes/1024) + " Kb";
        else
            tamanio = df.format(tamanioBytes) + " bytes";

        return tamanio;
    }

    public static boolean isMayoroIgual(Date dia1,Date dia2){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(dia1);
        int d1_dia=calendar.get(Calendar.DATE);
        int d1_mes=calendar.get(Calendar.MONTH)+1;
        int d1_anio=calendar.get(Calendar.YEAR);

        calendar.setTime(dia2);
        int d2_dia=calendar.get(Calendar.DATE);
        int d2_mes=calendar.get(Calendar.MONTH)+1;
        int d2_anio=calendar.get(Calendar.YEAR);


        if(d1_anio==d2_anio){
            if(d1_mes==d2_mes){
                if(d1_dia==d2_dia){
                    return true;
                } else{
                    return d1_dia>d2_dia;
                }
            }else{
                return d1_mes>d2_mes;
            }
        }else{
            return d1_anio>d2_anio;
        }
    }

    private String RetornarCadenaCodigos(String[] Listado){//NECESITAA RECIBIR STRING[] CON UN VALOR O MAS.
        if(Listado == null){
            return null;
        }
        String cadenaCodigos = "";
        for (int i = 0 ; i < Listado.length;i++){
            if(Listado[i] != null){
                if( i == Listado.length-1){
                    cadenaCodigos+=Listado[i];
                }else{
                    cadenaCodigos+=Listado[i]+",";
                }
            }
        }
        if(cadenaCodigos.equals("") || cadenaCodigos.equals("null")){
            cadenaCodigos = null;
        }
        return cadenaCodigos;
    }

   /* public static void main(String[] args) {
        String letras = "gtattAS";

        Boolean nuevo = Metodos.isSoloLetras(letras);
        System.out.println(nuevo);
    }*/


}
