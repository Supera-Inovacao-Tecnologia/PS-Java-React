package br.com.banco.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionResponseDTO {
    String error;
    Integer status;
}
