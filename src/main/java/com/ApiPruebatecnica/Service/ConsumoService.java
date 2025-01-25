package com.ApiPruebatecnica.Service;



import com.ApiPruebatecnica.DTO.TicketRequestDto;
import com.ApiPruebatecnica.DTO.TicketResponseDto;
import com.ApiPruebatecnica.DTO.UsuarioRequestDto;
import com.ApiPruebatecnica.DTO.UsuarioResponseDto;
import com.ApiPruebatecnica.Entity.UsuarioEntity;

import java.util.List;

public interface ConsumoService {

    UsuarioResponseDto CrearUsuario(UsuarioRequestDto requestEntity);

    UsuarioResponseDto ActualizarUsuario(UsuarioRequestDto requestEntity);

    List<UsuarioEntity> ListarUsuario ();

    UsuarioResponseDto ConsultarUsuario(UsuarioRequestDto requestEntity);

    String CrearTicket(TicketRequestDto requestEntity);


    String EditarTicket(TicketRequestDto requestEntity);


    TicketResponseDto EliminarTicket (TicketRequestDto requestEntity);

}
