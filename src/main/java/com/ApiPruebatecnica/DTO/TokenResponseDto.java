package com.ApiPruebatecnica.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class TokenResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String trasactionID;
    private String connid;
    private String jwtToken;

    public String getConnid() {
        return connid;
    }

    public void setConnid(String connid) {
        this.connid = connid;
    }

    public String getTrasactionID() {
        return trasactionID;
    }

    public void setTrasactionID(String trasactionID) {
        this.trasactionID = trasactionID;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    @Override
    public String toString() {
        return "TokenResponseDto{" +
                "trasactionID='" + trasactionID + '\'' +
                ", connid='" + connid + '\'' +
                ", jwtToken='" + jwtToken + '\'' +
                '}';
    }
}
