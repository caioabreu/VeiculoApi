package com.exemplo.cadastroveiculo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Veiculo {

//    private Long id;
    private String veiculo;
    private String marca;
    private Integer ano;
    private String descricao;
    private boolean vendido;
    private Date created;
    private Date updated;
}
