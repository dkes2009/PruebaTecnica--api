package com.ApiPruebatecnica.Controller;

import com.ApiPruebatecnica.DTO.TicketRequestDto;
import com.ApiPruebatecnica.DTO.TicketResponseDto;
import com.ApiPruebatecnica.DTO.UsuarioRequestDto;
import com.ApiPruebatecnica.DTO.UsuarioResponseDto;
import com.ApiPruebatecnica.Entity.UsuarioEntity;
import com.ApiPruebatecnica.utils.Utils;
import com.ApiPruebatecnica.utils.Validaciones;
import com.ApiPruebatecnica.Service.ConsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class ConsumeController {


    private final Utils utilLogs;
    private final Validaciones validar;
    private long startTime;
    private long endTime;
    private long totalTime;
    private static int consecutivo;
    private String logsMessage;
    private static final String ERRORDATA = "ERROR EN EL INGRESO DE DATOS";

    @Autowired
    private ConsumoService endpointService;

    public ConsumeController(Utils utilLogs, Validaciones validar) {
        this.utilLogs = utilLogs;
        this.validar = validar;
    }


    @PostMapping("/CrearUsuario")
    public UsuarioResponseDto CrearUsuario(@RequestBody UsuarioRequestDto requestEntity, HttpServletRequest request, HttpServletResponse httpResponse) {
        consecutivo = utilLogs.consecutivo();
        startTime = System.nanoTime();
        UsuarioResponseDto response = new UsuarioResponseDto();
        System.out.println("RequestEntity: " + requestEntity);
        utilLogs.logApi(0, "" + requestEntity.toString(), request.getRemoteHost(), consecutivo, "CrearUsuario");

        if (validar.validarCrearUsuario(requestEntity)) {
            response = endpointService.CrearUsuario(requestEntity);
            System.out.println(response);
            logsMessage = (String) response.getData();

        } else {
            httpResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setCodigo("400");
            response.setMessage(ERRORDATA);
            logsMessage = response.getMessage();
            utilLogs.logApiError(response.getMessage());
        }
        endTime = System.nanoTime();
        totalTime = (endTime - startTime) / 1000000;
        utilLogs.logApi(1, logsMessage + " Demoro: " + totalTime, request.getRemoteAddr(), consecutivo, "CrearUsuario");
        return response;
    }


    @PostMapping("/ActualizarUsuario")
    public UsuarioResponseDto ActualizarUsuario(@RequestBody UsuarioRequestDto requestEntity, HttpServletRequest request, HttpServletResponse httpResponse) {
        consecutivo = utilLogs.consecutivo();
        startTime = System.nanoTime();
        UsuarioResponseDto response = new UsuarioResponseDto();
        System.out.println("RequestEntity: " + requestEntity);
        utilLogs.logApi(0, "" + requestEntity.toString(), request.getRemoteHost(), consecutivo, "ActualizarUsuario");

        if (validar.validarActualizarUsuario(requestEntity)) {
            response = endpointService.ActualizarUsuario(requestEntity);
            logsMessage = (String) response.getData();

        } else {
            httpResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setCodigo("400");
            response.setMessage(ERRORDATA);
            logsMessage = response.getMessage();
            utilLogs.logApiError(response.getMessage());
        }
        endTime = System.nanoTime();
        totalTime = (endTime - startTime) / 1000000;
        utilLogs.logApi(1, logsMessage + " Demoro: " + totalTime, request.getRemoteAddr(), consecutivo, "ActualizarUsuario");
        return response;
    }




    @PostMapping("/ListarUsuario")
    public UsuarioResponseDto ListarUsuario(HttpServletRequest request, HttpServletResponse httpResponse) {
        consecutivo = utilLogs.consecutivo();
        startTime = System.nanoTime();
        UsuarioResponseDto response = new UsuarioResponseDto();

        utilLogs.logApi(0, "" + null, request.getRemoteHost(), consecutivo, "ListarUsuario");
        List<UsuarioEntity> usuarios = endpointService.ListarUsuario();
        response.setSuccess(true);
        response.setCodigo("200");
        response.setMessage(usuarios.size() + " Usuarios encontrados");
        response.setData(usuarios);


        endTime = System.nanoTime();
        totalTime = (endTime - startTime) / 1000000;
        utilLogs.logApi(1, logsMessage + " Demoro: " + totalTime, request.getRemoteAddr(), consecutivo, "ListarUsuario");
        return response;
    }



    @PostMapping("/ConsultarUsuario")
    public UsuarioResponseDto ConsultarUsuario(@RequestBody UsuarioRequestDto requestEntity,HttpServletRequest request, HttpServletResponse httpResponse) {
        consecutivo = utilLogs.consecutivo();
        startTime = System.nanoTime();
        UsuarioResponseDto response = new UsuarioResponseDto();
        utilLogs.logApi(0, "" + null, request.getRemoteHost(), consecutivo, "ListarUsuario");

        if (validar.validarConsultarUsuario(requestEntity)) {
        System.out.println("RequestEntity: " + response);
        response = endpointService.ConsultarUsuario(requestEntity);

        } else {
            httpResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setCodigo("400");
            response.setMessage(ERRORDATA);
            logsMessage = (String) response.getData();
            utilLogs.logApiError(response.getMessage());
        }
        endTime = System.nanoTime();
        totalTime = (endTime - startTime) / 1000000;
        utilLogs.logApi(1, logsMessage + " Demoro: " + totalTime, request.getRemoteAddr(), consecutivo, "ListarUsuario");
        return response;
    }


    @PostMapping("/CrearTicket")
    public TicketResponseDto CrearTicket(@RequestBody TicketRequestDto requestEntity, HttpServletRequest request, HttpServletResponse httpResponse) {
        consecutivo = utilLogs.consecutivo();
        startTime = System.nanoTime();
        TicketResponseDto response = new TicketResponseDto();
        System.out.println("RequestEntity: " + requestEntity);
        utilLogs.logApi(0, "" + requestEntity.toString(), request.getRemoteHost(), consecutivo, "CrearTicket");

        if (validar.validarCrearTicket(requestEntity)) {
            String respuesta = endpointService.CrearTicket(requestEntity);
            System.out.println("respuesta: " + respuesta);
            logsMessage = (String) response.getData();
            response.setSuccess(Boolean.TRUE);
            response.setCodigo("200");
            response.setData(respuesta);


        } else {
            httpResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setCodigo("400");
            response.setMessage(ERRORDATA);
            logsMessage = response.getMessage();
            utilLogs.logApiError(response.getMessage());
        }
        endTime = System.nanoTime();
        totalTime = (endTime - startTime) / 1000000;
        utilLogs.logApi(1, logsMessage + " Demoro: " + totalTime, request.getRemoteAddr(), consecutivo, "CrearTicket");
        return response;
    }


    @PostMapping("/EditarTicket")
    public TicketResponseDto EditarTicket(@RequestBody TicketRequestDto requestEntity, HttpServletRequest request, HttpServletResponse httpResponse) {
        consecutivo = utilLogs.consecutivo();
        startTime = System.nanoTime();
        TicketResponseDto response = new TicketResponseDto();
        System.out.println("RequestEntity: " + requestEntity);
        utilLogs.logApi(0, "" + requestEntity.toString(), request.getRemoteHost(), consecutivo, "CrearTicket");

        if (validar.validarCrearTicket(requestEntity)) {
            String respuesta = endpointService.EditarTicket(requestEntity);
            System.out.println("respuesta: " + respuesta);
            logsMessage = (String) response.getData();
            response.setSuccess(Boolean.TRUE);
            response.setCodigo("200");
            response.setData(respuesta);


        } else {
            httpResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setCodigo("400");
            response.setMessage(ERRORDATA);
            logsMessage = response.getMessage();
            utilLogs.logApiError(response.getMessage());
        }
        endTime = System.nanoTime();
        totalTime = (endTime - startTime) / 1000000;
        utilLogs.logApi(1, logsMessage + " Demoro: " + totalTime, request.getRemoteAddr(), consecutivo, "CrearTicket");
        return response;
    }


    @PostMapping("/EliminarTicket")
    public TicketResponseDto EliminarTicket(@RequestBody TicketRequestDto requestEntity, HttpServletRequest request, HttpServletResponse httpResponse) {
        consecutivo = utilLogs.consecutivo();
        startTime = System.nanoTime();
        TicketResponseDto response = new TicketResponseDto();
        System.out.println("RequestEntity: " + requestEntity);
        utilLogs.logApi(0, "" + requestEntity.toString(), request.getRemoteHost(), consecutivo, "EliminarTicket");

        if (validar.validarEliminarTicket(requestEntity)) {
            response= endpointService.EliminarTicket(requestEntity);
            logsMessage = (String) response.getData();
            response.setSuccess(Boolean.TRUE);
            response.setCodigo("200");

        } else {
            httpResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setCodigo("400");
            response.setMessage(ERRORDATA);
            logsMessage = response.getMessage();
            utilLogs.logApiError(response.getMessage());
        }
        endTime = System.nanoTime();
        totalTime = (endTime - startTime) / 1000000;
        utilLogs.logApi(1, logsMessage + " Demoro: " + totalTime, request.getRemoteAddr(), consecutivo, "EliminarTicket");
        return response;
    }






}


