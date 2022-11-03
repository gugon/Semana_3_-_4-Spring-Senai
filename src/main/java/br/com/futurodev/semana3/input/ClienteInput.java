package br.com.futurodev.semana3.input;

import br.com.futurodev.semana3.dto.PedidoRepresentationModel;
import lombok.Data;

@Data
public class ClienteInput {
    private Long id;

    private String nome;

    private String cpf;

    private String rg;


}
