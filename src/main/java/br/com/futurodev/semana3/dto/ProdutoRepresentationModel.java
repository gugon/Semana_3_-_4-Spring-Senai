package br.com.futurodev.semana3.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class ProdutoRepresentationModel {
    private Long id;

    private String decricao;

    private String descricaoReduzida;

    private double precoCompra;

    private double precoVenda;

}
