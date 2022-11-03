package br.com.futurodev.semana3.input;

import br.com.futurodev.semana3.dto.TelefoneRepresentationModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
public class UsuarioInput {

    private Long id;

    private String nome;

    @NotBlank(message = "{login.not.blank}")
    private String login;
    private String senha;

    private List<TelefoneRepresentationModel> telefones = new ArrayList<TelefoneRepresentationModel>();
}
