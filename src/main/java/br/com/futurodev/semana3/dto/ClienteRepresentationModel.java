package br.com.futurodev.semana3.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ClienteRepresentationModel {

    private Long id;
    private String nome;
    private String cpf;
    private String rg;
//    private List<PedidoRepresentationModel> pedidos = new ArrayList<PedidoRepresentationModel>();

}
