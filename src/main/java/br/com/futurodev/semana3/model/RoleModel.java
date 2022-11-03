package br.com.futurodev.semana3.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@Entity
@Table(name = "role")
public class RoleModel implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeRole; /* Papel, ROLE_GERENTE, ROLE_ADMINISTRADOR*/

    @Override
    public String getAuthority() {
        return this.nomeRole;
    }

}
