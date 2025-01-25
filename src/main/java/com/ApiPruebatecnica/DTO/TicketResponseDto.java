package com.ApiPruebatecnica.DTO;


import com.ApiPruebatecnica.Entity.TicketEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPOJOBuilder
@Data
public class TicketResponseDto extends RespuestaApiDto {


            private TicketEntity ticketEntity;

    @Override
    public String toString() {
        return "TicketResponseDto{" +
                "ticketEntity=" + ticketEntity +
                '}';
    }

}
