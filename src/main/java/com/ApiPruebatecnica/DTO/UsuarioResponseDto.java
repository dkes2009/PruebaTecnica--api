package com.ApiPruebatecnica.DTO;


import com.ApiPruebatecnica.Entity.UsuarioEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPOJOBuilder
@Data

public class UsuarioResponseDto extends RespuestaApiDto {

            private UsuarioEntity usuarioEntity;

    @Override
    public String toString() {
        return "UsuarioResponseDto{" +
                "usuarioEntity=" + usuarioEntity +
                '}';
    }



}
