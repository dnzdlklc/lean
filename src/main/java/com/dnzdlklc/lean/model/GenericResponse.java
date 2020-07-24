package com.dnzdlklc.lean.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by denizdalkilic on 20/07/2020 @ 20:34.
 */
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericResponse {
    private Integer status;
    private String message;
    private Boolean success;
}

