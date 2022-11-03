package br.com.futurodev.semana3.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "pedido")
public class PedidoModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(columnDefinition = "timestamp without time zone DEFAULT timezone('utc'::text, CURRENT_TIMESTAMP(0))", updatable = false)
    private OffsetDateTime dataHoraCadastro;


    @UpdateTimestamp
    @Column(columnDefinition = "timestamp without time zone")
    private OffsetDateTime dataHoraAlteracao;

    @ManyToOne
    @JoinColumn(name = "id_cliente", foreignKey = @ForeignKey(name ="fk_cliente"))
    // @JsonBackReference
    private ClienteModel cliente;

    @OneToOne
    @JoinColumn(name = "id_forma_pagamento", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_forma_pgto"), updatable = false)
    private FormaPagamentoModel formaPagamento;

    @OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ItemPedidoModel> itensPedido = new ArrayList<ItemPedidoModel>();

    @JsonManagedReference
    public List<ItemPedidoModel> getItensPedido() {
        return itensPedido;
    }

    @JsonBackReference
    public ClienteModel getCliente() {
        return cliente;
    }
}