package br.com.futurodev.semana3.repository;

import br.com.futurodev.semana3.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {

        /*
      @Query(value = "select u from Usuario u where u.login = ?1")
    Usuario findUserByLogin(String login);
     */

    @Query("from ProdutoModel p where p.descricao like %?1%")
    List<ProdutoModel> getProdutosByDescricao(String descricao);

}