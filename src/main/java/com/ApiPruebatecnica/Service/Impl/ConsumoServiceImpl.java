package com.ApiPruebatecnica.Service.Impl;



import com.ApiPruebatecnica.DTO.TicketRequestDto;
import com.ApiPruebatecnica.DTO.TicketResponseDto;
import com.ApiPruebatecnica.DTO.UsuarioRequestDto;
import com.ApiPruebatecnica.DTO.UsuarioResponseDto;
import com.ApiPruebatecnica.Entity.TicketEntity;
import com.ApiPruebatecnica.Entity.UsuarioEntity;
import com.ApiPruebatecnica.Repository.TicketRepository;
import com.ApiPruebatecnica.Repository.UsuarioRepository;
import com.ApiPruebatecnica.Service.ConsumoService;
import com.ApiPruebatecnica.utils.Utils;
import com.ApiPruebatecnica.utils.Validaciones;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class ConsumoServiceImpl implements ConsumoService {

    private static final Logger logger = LoggerFactory.getLogger(ConsumoServiceImpl.class);
    private final Utils utilLogs = new Utils();

    private Validaciones validar;


    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public UsuarioResponseDto CrearUsuario(UsuarioRequestDto requestEntity) {
        String stringRespuesta = "";
        UsuarioResponseDto response = new UsuarioResponseDto();
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setNombre(requestEntity.getNombre());
        usuarioEntity.setApellidos(requestEntity.getApellidos());
        usuarioEntity.setFechaCreacion(LocalDate.now());
        System.out.println("UsuariosEntity "+usuarioEntity);
        try{
            UsuarioEntity respuesta = usuarioRepository.save(usuarioEntity);
            System.out.println(respuesta);
           if (respuesta == null) {
                stringRespuesta = "El usuario no Creado";
                response.setSuccess(Boolean.FALSE);
                response.setCodigo("500");
                response.setMessage(stringRespuesta);
            }else {
                System.out.println("El usuario Creado en la base de datos");
                stringRespuesta = "El usuario Creado en la base de datos";
                response.setSuccess(Boolean.TRUE);
                response.setCodigo("200");
                response.setMessage(stringRespuesta);
                response.setUsuarioEntity(respuesta);
            }
        } catch (Exception e) {
            utilLogs.logApiError("Error al crear el usuario"+ e);
        }
        return response;
    }

    @Override
    public UsuarioResponseDto ActualizarUsuario(UsuarioRequestDto requestEntity) {
        String stringRespuesta = "";
        UsuarioResponseDto response = new UsuarioResponseDto();
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setIdUsuario(requestEntity.getId());
        usuarioEntity.setNombre(requestEntity.getNombre());
        usuarioEntity.setApellidos(requestEntity.getApellidos());
        usuarioEntity.setFechaActualizacion(LocalDate.now());
        System.out.println("UsuariosEntity "+usuarioEntity);
        try{
            UsuarioEntity respuesta = usuarioRepository.save(usuarioEntity);
            System.out.println(respuesta);
            if (respuesta == null) {
                stringRespuesta = "El usuario no Actualizado";
                response.setSuccess(Boolean.FALSE);
                response.setCodigo("500");
                response.setMessage(stringRespuesta);
            }else {
                System.out.println("El usuario Actualizado en la base de datos");
                stringRespuesta = "El usuario Actualizado en la base de datos";
                response.setSuccess(Boolean.TRUE);
                response.setCodigo("200");
                response.setMessage(stringRespuesta);
                response.setUsuarioEntity(respuesta);
            }
        } catch (Exception e) {
            utilLogs.logApiError("Error al Actualizado el usuario"+ e);
        }

        return response;
    }

    @Override
    public List<UsuarioEntity> ListarUsuario() {
        String stringRespuesta = "";
        UsuarioResponseDto response = new UsuarioResponseDto();
            List<UsuarioEntity> usuarioEntityList = usuarioRepository.findAll();
            System.out.println(usuarioEntityList);
            if (usuarioEntityList.size() == 0) {
                stringRespuesta = "Usuarios Listados";
                response.setSuccess(Boolean.FALSE);
                response.setCodigo("500");
                response.setMessage(stringRespuesta);

            }else {
                System.out.println(" Usuarios Listados en la base de datos");
                response.setSuccess(Boolean.TRUE);
                response.setCodigo("200");
                response.setMessage(stringRespuesta);
                response.setData(usuarioEntityList);

            }
        return usuarioEntityList;
    }

    @Override
    public UsuarioResponseDto ConsultarUsuario(UsuarioRequestDto requestEntity) {
        String stringRespuesta = "";
        UsuarioResponseDto response = new UsuarioResponseDto();

        try{
            Optional<UsuarioEntity> respuesta = usuarioRepository.findById(requestEntity.getId());

            System.out.println("Respuesta es: " + respuesta );
            if (respuesta.isEmpty()) {
                stringRespuesta = "El usuario no Consultado";
                response.setSuccess(Boolean.FALSE);
                response.setCodigo("500");
                response.setMessage(stringRespuesta);
            }else {
                System.out.println("usuario Consultado en la base de datos");
                response.setSuccess(Boolean.TRUE);
                response.setCodigo("200");
                response.setMessage(stringRespuesta);
                response.setData(respuesta);
            }
        } catch (Exception e) {
            utilLogs.logApiError("Error al Consultado el usuario"+ e);
        }

        return response;
    }

    @Override
    public String CrearTicket(TicketRequestDto requestEntity) {
        String stringRespuesta = "";
        TicketResponseDto response = new TicketResponseDto();
        TicketEntity ticketEntity = new TicketEntity();
        System.out.println("getidUsuario "+ requestEntity.getIdusuario());
        ticketEntity.setUsuario(usuarioRepository.findById(requestEntity.getIdusuario()).get());
        ticketEntity.setFechaCreacion(LocalDate.now());
        ticketEntity.setEstatus(TicketEntity.Estatus.valueOf(requestEntity.getEstatus()));
        System.out.println("ticketEntity "+ticketEntity);
        try{
            TicketEntity respuesta = ticketRepository.save(ticketEntity);
            System.out.println(respuesta);

            if (respuesta == null) {
                stringRespuesta = "El Ticket no Creado";
                response.setSuccess(Boolean.FALSE);
                response.setCodigo("500");
                response.setMessage(stringRespuesta);
                return respuesta.toString();
            }else {
                System.out.println("El Ticket Creado en la base de datos");
                stringRespuesta= "El Ticket Creado en la base de datos";
                response.setSuccess(true);
                response.setCodigo("200");
                response.setMessage(stringRespuesta);
                response.setData(respuesta);
                return respuesta.toString();
            }
        } catch (Exception e) {
            utilLogs.logApiError("Error al crear el Ticket"+ e);
        }

        return stringRespuesta.toString();
    }

    @Override
    public String EditarTicket(TicketRequestDto requestEntity) {
        String stringRespuesta = "";
        TicketResponseDto response = new TicketResponseDto();
        TicketEntity ticketEntity = new TicketEntity();
        System.out.println("getidUsuario "+ requestEntity.getIdusuario());
        ticketEntity.setIdTicket(requestEntity.getId());
        ticketEntity.setUsuario(usuarioRepository.findById(requestEntity.getIdusuario()).get());
        ticketEntity.setFechaActualizacion(LocalDate.now());
        ticketEntity.setEstatus(TicketEntity.Estatus.valueOf(requestEntity.getEstatus()));
        System.out.println("ticketEntity "+ticketEntity);
        try{
            TicketEntity respuesta = ticketRepository.save(ticketEntity);
            System.out.println(respuesta);

            if (respuesta == null) {
                stringRespuesta = "El Ticket no fue Editar";
                response.setSuccess(Boolean.FALSE);
                response.setCodigo("500");
                response.setMessage(stringRespuesta);
                return respuesta.toString();
            }else {
                System.out.println("El Ticket fue Editado en la base de datos");
                stringRespuesta= "El Ticket fue Editado en la base de datos";
                response.setSuccess(true);
                response.setCodigo("200");
                response.setMessage(stringRespuesta);
                response.setData(respuesta);
                return respuesta.toString();
            }
        } catch (Exception e) {
            utilLogs.logApiError("Error al Editar el Ticket"+ e);
        }

        return stringRespuesta.toString();
    }

    @Override
    public TicketResponseDto EliminarTicket(TicketRequestDto requestEntity) {
        String stringRespuesta = "";
        TicketResponseDto response = new TicketResponseDto();
        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setIdTicket(requestEntity.getId());

        System.out.println("ticketEntity "+ticketEntity);

        try{
            Optional<TicketEntity> ticketOptional = ticketRepository.findById(requestEntity.getId());

            if (ticketOptional.isPresent()) {
                ticketRepository.delete(ticketOptional.get());
                System.out.println("Ticket eliminado correctamente.");
                System.out.println("El Ticket fue Eliminado en la base de datos");
                stringRespuesta = "El Ticket fue Eliminado en la base de datos";
                response.setSuccess(Boolean.TRUE);
                response.setCodigo("200");
                response.setMessage(stringRespuesta);
                response.setTicketEntity(ticketEntity);

            } else {
                stringRespuesta = "El Ticket no fue Eliminado";
                response.setSuccess(Boolean.FALSE);
                response.setCodigo("500");
                response.setMessage(stringRespuesta);
                System.out.println("No se encontró el ticket con ID: " + requestEntity.getId());
            }


        } catch (Exception e) {
            utilLogs.logApiError("Error al  Eliminar Ticket "+ e);
        }
        return response;
    }

}
