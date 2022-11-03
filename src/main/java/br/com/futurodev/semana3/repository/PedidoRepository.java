package br.com.futurodev.semana3.repository;

import br.com.futurodev.semana3.model.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoModel, Long> {

    @Query("select p from PedidoModel p where p.cliente.id = ?1")
    List<PedidoModel> getPedidosByIdCliente(Long idCliente);

}