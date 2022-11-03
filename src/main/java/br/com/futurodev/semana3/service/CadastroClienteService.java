package br.com.futurodev.semana3.service;

import br.com.futurodev.semana3.model.ClienteModel;
import br.com.futurodev.semana3.model.ProdutoModel;
import br.com.futurodev.semana3.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CadastroClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public ClienteModel salvar(ClienteModel cliente){
        return clienteRepository.save(cliente);
    }

    @Transactional
    public void delete(Long idCliente){
        clienteRepository.deleteById(idCliente);
    }


    public List<ClienteModel> getClientes(){
        return clienteRepository.findAll();
    }

    public ClienteModel getClienteById(Long idCliente){

        ClienteModel cliente = clienteRepository.findById(idCliente).get();

        return cliente;
    }

    public List<ClienteModel> getClienteByName(String nome){
        List<ClienteModel> cliente = clienteRepository.getClienteByName(nome);
        return cliente;
    }




}
