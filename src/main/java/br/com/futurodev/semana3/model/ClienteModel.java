package br.com.futurodev.semana3.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "cliente")
public class ClienteModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 80)
    private String nome;

    @Column(unique = true, length = 11)
    private String cpf;

    private String rg;


    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    //@JsonManagedReference
    private List<PedidoModel> pedidos;

    @JsonManagedReference
    public List<PedidoModel> getPedidos() {
        return pedidos;
    }

}