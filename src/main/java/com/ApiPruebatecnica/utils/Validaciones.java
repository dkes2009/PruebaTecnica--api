package com.ApiPruebatecnica.utils;

import com.ApiPruebatecnica.DTO.TicketRequestDto;
import com.ApiPruebatecnica.DTO.UsuarioRequestDto;
import com.ApiPruebatecnica.Entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Validaciones {
    private static final Pattern numeros = Pattern.compile("[0-9-]*");
    private static final Pattern letrasYNumeros = Pattern.compile("[A-Za-z0-9._-]*");
    private static final Pattern letras = Pattern.compile("[A-Za-z._-]*");
    private static final String ERRORDATA = "ERROR EN EL INGRESO DE DATOS: ";
    private final Utils utilLogs;
    private static final Pattern contrasena = Pattern.compile("[A-Za-z0-9.£'&;`_@~*>?-\\[\\]]*");
    public Validaciones(Utils utilLogs) {
        this.utilLogs = utilLogs;
    }

    public boolean validarNumeros(String cadena, Integer longitud){
        try {
            if (cadena.length() <= longitud) {
                Matcher mat = numeros.matcher(cadena);
                if (mat.matches()) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }catch (Exception e){
            utilLogs.logApiError(ERRORDATA+e);
            return false;
        }
    }

    public boolean validarLetras(String cadena, Integer longitud){
        try{
            if(cadena.length()<=longitud) {
                Matcher mat = letras.matcher(cadena);
                if(mat.matches()){
                    return true;
                } else {
                    return false;
                }
            }else {
                return false;
            }
        }catch (Exception e){
            utilLogs.logApiError(ERRORDATA+e);
            return false;
        }
    }

    public boolean validarLetrasYNumeros(String cadena, Integer longitud) {
        try {
            if (cadena.length() <= longitud) {
                Matcher mat = letrasYNumeros.matcher(cadena);
                if (mat.matches()) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            utilLogs.logApiError(ERRORDATA+ e);
            return false;
        }
    }

    public boolean validarAutenticacion(UserEntity dto) {
        if (validarLetras(dto.getUserName().equals("") ? null : dto.getUserName(),43) &&
                validarContrasena(dto.getPassword().equals("") ? null : dto.getPassword(),16)){
            return true;
        }else{
            return false;
        }
    }


    public boolean validarContrasena(String cadena, Integer longitud){
        try {
            if (cadena.length() <= longitud) {
                Matcher mat = contrasena.matcher(cadena);
                if (mat.matches()) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }catch (Exception e){
            utilLogs.logApiError(ERRORDATA+e);
            return false;
        }
    }


    public boolean validarCrearUsuario(UsuarioRequestDto dto) {
        if (dto.getNombre() != null &&
                dto.getNombre() != "" &&
                validarInyeccionDatos(dto.getNombre(), 100)  &&
                validarLetras(dto.getNombre(),35)  &&
                dto.getApellidos() != null &&
                dto.getApellidos()  != "" &&
                validarInyeccionDatos(dto.getApellidos(), 100)  &&
                validarLetras(dto.getApellidos(),35)
        ){
            return true;
        }else{
            return false;
        }
    }




    public boolean validarActualizarUsuario(UsuarioRequestDto dto) {
        if (dto.getNombre() != null &&
                dto.getNombre() != "" &&
                validarInyeccionDatos(dto.getNombre(), 100)  &&
                validarLetras(dto.getNombre(),35)  &&
                dto.getApellidos() != null &&
                dto.getApellidos()  != "" &&
                validarInyeccionDatos(dto.getApellidos(), 100)  &&
                validarLetras(dto.getApellidos(),35) &&
                validarNumeros(String.valueOf(dto.getId()), 1000)
        ){
            return true;
        }else{
            return false;
        }
    }

    public boolean validarConsultarUsuario(UsuarioRequestDto dto) {
        if (
                validarInyeccionDatos(String.valueOf(dto.getId()), 100)
        ){
            return true;
        }else{
            return false;
        }
    }

    public boolean validarCrearTicket(TicketRequestDto dto) {
        if (
                validarInyeccionDatos(String.valueOf(dto.getIdusuario()), 1000)
        ){
            return true;
        }else{
            return false;
        }
    }

    public boolean validarEliminarTicket(TicketRequestDto dto) {
        if (
                validarInyeccionDatos(String.valueOf(dto.getId()), 1000)
        ){
            return true;
        }else{
            return false;
        }
    }





    public boolean validarInyeccionDatos(String cadena, Integer longitud) {
        try {
            if (cadena.length() <= longitud) {
                cadena=cadena.toUpperCase();
                List<String> palabrasNoPermitidas =  Arrays.asList("SCRIPT"," SELECT", "INSERT", "UPDATE", "DELETE", "DROP", "ALTER", "--", ";" , "EXEC", "<" ,">" , "\",","'", "&");

                for (String noPermitido : palabrasNoPermitidas) {
                    if (cadena.contains(noPermitido)) {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            utilLogs.logApiError(ERRORDATA+ e);
            return false;
        }
    }


}
