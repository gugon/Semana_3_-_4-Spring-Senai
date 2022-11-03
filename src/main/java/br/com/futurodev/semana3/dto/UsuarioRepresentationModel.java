package br.com.futurodev.semana3.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class UsuarioRepresentationModel {

    private Long id;
    private String nome;
    private String login;
    private String senha;
    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataAtualizacao;

    @JsonIgnoreProperties(value = "tipo", allowGetters = true)
    private List<TelefoneRepresentationModel> telefones = new ArrayList<TelefoneRepresentationModel>();

    public List<TelefoneRepresentationModel> getTelefones() {
        return telefones;
    }
//
//    public void setTelefones(List<TelefoneRepresentationModel> telefones) {
//        this.telefones = telefones;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getNome() {
//        return nome;
//    }
//
//
//    public void setNome(String nome) {
//        this.nome = nome;
//    }
//
//    public String getLogin() {
//        return login;
//    }
//
//    public void setLogin(String login) {
//        this.login = login;
//    }
//
//
//    public OffsetDateTime getDataCadastro() {
//        return dataCadastro;
//    }
//
//    public void setDataCadastro(OffsetDateTime dataCadastro) {
//        this.dataCadastro = dataCadastro;
//    }
//
//    public OffsetDateTime getDataAtualizacao() {
//        return dataAtualizacao;
//    }
//
//    public void setDataAtualizacao(OffsetDateTime dataAtualizacao) {
//        this.dataAtualizacao = dataAtualizacao;
//    }

}

