package br.com.futurodev.semana3.service;

import br.com.futurodev.semana3.model.ClienteModel;
import br.com.futurodev.semana3.model.FormaPagamentoModel;
import br.com.futurodev.semana3.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CadastroFormaPagamentoService {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Transactional
    public FormaPagamentoModel salvar(FormaPagamentoModel pagamento){
        return formaPagamentoRepository.save(pagamento);
    }

    @Transactional
    public void delete(Long idFormaPagamentoModel){
        formaPagamentoRepository.deleteById(idFormaPagamentoModel);
    }


    public List<FormaPagamentoModel> getFormaPagamentos(){
        return formaPagamentoRepository.findAll();
    }

    public FormaPagamentoModel getFormaPagamentoById(Long idFormaPagamento){
        return formaPagamentoRepository.findById(idFormaPagamento).get();
    }
}
