package br.com.futurodev.semana3.input;

import br.com.futurodev.semana3.model.ItemPedidoModel;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import java.time.OffsetDateTime;

@Data
public class ProdutoInput {

    private Long idProduto;
    private String descricao;

    private double compra;
    private double venda;

//    @CreationTimestamp
//    @Column(columnDefinition = "timestamp without time zone DEFAULT timezone('utc'::text, CURRENT_TIMESTAMP(0))", updatable = false)
//    private OffsetDateTime dataHoraCadastro;
//
//    @UpdateTimestamp
//    @Column(columnDefinition = "timestamp without time zone")
//    private OffsetDateTime dataHoraAlteracao;

}
