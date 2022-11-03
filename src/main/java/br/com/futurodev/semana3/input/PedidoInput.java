package br.com.futurodev.semana3.input;

import br.com.futurodev.semana3.dto.ItemPedidoRepresentationModel;
import br.com.futurodev.semana3.model.ClienteModel;
import br.com.futurodev.semana3.model.FormaPagamentoModel;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class PedidoInput {

    private Long idPedido;

    private Long idCliente;

    private Long idFormaPagamento;

    private List<ItemPedidoInput> itensPedido;

}
