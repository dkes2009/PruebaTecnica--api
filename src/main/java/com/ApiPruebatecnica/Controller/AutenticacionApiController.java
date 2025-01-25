package com.ApiPruebatecnica.Controller;

import com.ApiPruebatecnica.DTO.RespuestaApiDto;
import com.ApiPruebatecnica.DTO.TokenResponseDto;
import com.ApiPruebatecnica.Entity.UserEntity;
import com.ApiPruebatecnica.ServiceImpl.JwtUtilService;
import com.ApiPruebatecnica.ServiceImpl.UsuarioDetailsService;
import com.ApiPruebatecnica.utils.Utils;

import com.ApiPruebatecnica.utils.Validaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class AutenticacionApiController {

    private final UsuarioDetailsService usuarioDetailsService;
    private final JwtUtilService jwtUtilService;
    private final Utils utilLogs;
    private long startTime;
    private long endTime;
    private long totalTime;
    private static int consecutivo;

    @Autowired
    private Validaciones validar;

    public AutenticacionApiController(UsuarioDetailsService usuarioDetailsService, JwtUtilService jwtUtilService, Utils utilLogs) {
        this.usuarioDetailsService = usuarioDetailsService;
        this.jwtUtilService = jwtUtilService;
        this.utilLogs = utilLogs;
    }

    @PostMapping("/publico/authenticate")
    public RespuestaApiDto authenticate(@RequestBody UserEntity authenticationReq, javax.servlet.http.HttpServletRequest request, HttpServletResponse res) {
        consecutivo = utilLogs.consecutivo();
        startTime = System.nanoTime();
        String tramaRespon;
        RespuestaApiDto response = new RespuestaApiDto(false, null, null);
        utilLogs.logApi(0, "Autenticando al usuario " + authenticationReq.getUserName(), request.getRemoteAddr(), consecutivo, "authenticate");
        final Boolean validatePassword = usuarioDetailsService.validatePassword(authenticationReq.getUserName(), authenticationReq.getPassword());
        final UserDetails userDetails = usuarioDetailsService.loadUserByUsername(authenticationReq.getUserName());
        if (validar.validarAutenticacion(authenticationReq)) {
            if (validatePassword && userDetails != null) {
                final String jwt = jwtUtilService.generateToken(userDetails, request.getRemoteAddr());
                TokenResponseDto tokenInfo = new TokenResponseDto();
                tokenInfo.setJwtToken(jwt);
                response.setSuccess(true);
                response.setCodigo("000");
                response.setMessage("Token created successfully");
                response.setData(tokenInfo);
                tramaRespon = response.getMessage();
            } else {
                res.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setCodigo("TK-001");
                response.setMessage("Incorrect user or password");
                tramaRespon = response.getCodigo() + " " + response.getCodigo() + " username: " + authenticationReq.getUserName();
                utilLogs.logApiError(tramaRespon);
            }
        } else {
            res.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setCodigo("400");
            response.setMessage("ERROR EN EL INGRESO DE DATOS");
            tramaRespon = response.getCodigo() + " " + response;
            utilLogs.logApiError(response.getMessage());
        }
        endTime = System.nanoTime();
        totalTime = (endTime - startTime) / 1000000;
        utilLogs.logApi(1, tramaRespon + " Demoro: " + totalTime, request.getRemoteAddr(), consecutivo, "authenticate");
        return response;
    }


}
