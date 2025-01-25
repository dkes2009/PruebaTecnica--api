package com.ApiPruebatecnica.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPOJOBuilder
@Data
public class TicketRequestDto {
    private long id;
    private long idusuario;
    private String estatus;

    @Override
    public String toString() {
        return "TicketRequestDto{" +
                "id=" + id +
                ", idusuario=" + idusuario +
                ", estatus='" + estatus + '\'' +
                '}';
    }
}
