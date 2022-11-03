package br.com.futurodev.semana3.repository;

import br.com.futurodev.semana3.model.ClienteModel;
import br.com.futurodev.semana3.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {

    @Query("select p from ClienteModel p where p.nome like %?1%")
    List<ClienteModel> getClienteByName(String nome);

}