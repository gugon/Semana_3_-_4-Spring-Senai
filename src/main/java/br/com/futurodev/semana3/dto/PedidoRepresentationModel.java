package br.com.futurodev.semana3.dto;

import br.com.futurodev.semana3.model.ClienteModel;
import br.com.futurodev.semana3.model.FormaPagamentoModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class PedidoRepresentationModel {

    private Long id;

    private Long idCliente;

    private String nomeCliente;

    private Long idFormaPagamento;

    private String formaPagamentoDescricao;


    private List<ItemPedidoRepresentationModel> itensPedidoRepresentationModel = new ArrayList<ItemPedidoRepresentationModel>();
}
