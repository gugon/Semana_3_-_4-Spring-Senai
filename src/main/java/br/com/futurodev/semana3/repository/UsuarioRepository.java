package br.com.futurodev.semana3.repository;

import br.com.futurodev.semana3.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long>{

    //@Query("select u.userName from User u inner join u.area ar where ar.idArea = :idArea")
    //@Query(value = "select p from PedidoModel p inner join p.cliente c where c.nome like %?1%")

    @Query(value = "select u from UsuarioModel u where u.nome like %?1%")
    ArrayList<UsuarioModel> getUserByName(String nome);

    @Query(value = "select u from UsuarioModel u where u.login = ?1")
    UsuarioModel findUserByLogin(String login);
}
