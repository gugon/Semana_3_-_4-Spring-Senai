package br.com.futurodev.semana3.input;

import lombok.Data;

@Data
public class ItemPedidoInput {

    private Long id;

    private Long idPedido;

    private Long idProduto;

    private double quantidade;

    private double precoVenda;
}
