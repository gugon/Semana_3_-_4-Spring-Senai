package br.com.futurodev.semana3.service;

import br.com.futurodev.semana3.model.UsuarioModel;
import br.com.futurodev.semana3.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CadastroUsuarioService implements UserDetailsService{
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public UsuarioModel salvar(UsuarioModel usuario){
        //Aqui neste ponto seriam as regras de negócio
        //Outras operações como delete, update, insert
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void delete(Long idUsuario){
        usuarioRepository.deleteById(idUsuario);
    }


    @Transactional(readOnly = true)
    public UsuarioModel getUserById(Long idUsuario){
        UsuarioModel usuario = usuarioRepository.findById(idUsuario).get();
        usuario.getTelefones().size();
        return usuario;
    }


    @Transactional(readOnly = true)
    public List<UsuarioModel> getUserByName(String nome){

        List<UsuarioModel> usuarios = usuarioRepository.getUserByName(nome);

        for (UsuarioModel usuario: usuarios) {
            usuario.getTelefones().isEmpty();
        }
        return  usuarios;
    }


    @Transactional(readOnly = true)
    public List<UsuarioModel> getUsers(){

        List<UsuarioModel> usuarios = usuarioRepository.findAll();

        for (UsuarioModel usuario: usuarios) {
            usuario.getTelefones().isEmpty();
        }
        return usuarios;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UsuarioModel usuario = usuarioRepository.findUserByLogin(username);
        if( usuario == null){
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        return new User(usuario.getLogin(), usuario.getPassword(), usuario.getAuthorities());
    }
}
