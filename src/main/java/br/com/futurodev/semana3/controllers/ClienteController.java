package br.com.futurodev.semana3.controllers;

import br.com.futurodev.semana3.dto.ClienteRepresentationModel;
import br.com.futurodev.semana3.dto.ItemPedidoRepresentationModel;
import br.com.futurodev.semana3.dto.PedidoRepresentationModel;
import br.com.futurodev.semana3.input.ClienteInput;
import br.com.futurodev.semana3.input.PedidoInput;
import br.com.futurodev.semana3.model.ClienteModel;
import br.com.futurodev.semana3.model.PedidoModel;
import br.com.futurodev.semana3.service.CadastroClienteService;
import br.com.futurodev.semana3.service.CadastroFormaPagamentoService;
import br.com.futurodev.semana3.service.CadastroItemPedidoService;
import br.com.futurodev.semana3.service.CadastroProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "Clientes")
@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    private CadastroClienteService cadastroClienteService;

    @Autowired
    private CadastroFormaPagamentoService cadastroFormaPagamentoService;

    @Autowired
    private CadastroProdutoService cadastroProdutoService;

    @Autowired
    private CadastroItemPedidoService itemPedidoService;

    @ApiOperation("Salva um cliente")
    @PostMapping(value = "/", produces ="application/json")
    public ResponseEntity<ClienteRepresentationModel> cadastrar(@RequestBody ClienteInput clienteInput){
        ClienteModel cli = toDomainObject(clienteInput);
        cadastroClienteService.salvar(cli);
        return new ResponseEntity<ClienteRepresentationModel>(toModel(cli), HttpStatus.CREATED);
    }

    @ApiOperation("Atualiza um cliente")
    @PutMapping(value = "/", produces ="application/json")
    public ResponseEntity<ClienteRepresentationModel> atualizar(@RequestBody ClienteInput clienteInput) {
        ClienteModel cliente = cadastroClienteService.salvar(toDomainObject(clienteInput));
        return new ResponseEntity<ClienteRepresentationModel>(toModel(cliente), HttpStatus.OK);
    }

    @ApiOperation("Deleta um cliente através do idCliente")
    @DeleteMapping
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long idCliente) {
        cadastroClienteService.delete(idCliente);
        return new ResponseEntity<String>("Cliente de ID: " + idCliente + " deletado.", HttpStatus.OK);
    }

    @ApiOperation("Busca cliente através do nome")
    @GetMapping(value = "/nome", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<ClienteRepresentationModel>> getClienteByName(@RequestParam (value = "nome") String nome){
        List<ClienteModel> cliente = cadastroClienteService.getClienteByName(nome);
        List<ClienteRepresentationModel> clienteRepresentationModels = toCollectionModel(cliente);
        return new ResponseEntity<List<ClienteRepresentationModel>>(clienteRepresentationModels, HttpStatus.OK);
    }


    @ApiOperation("Listar clientes")
    @GetMapping(value = "/", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<ClienteRepresentationModel>> getClientes(){
        List<ClienteModel> clientes = cadastroClienteService.getClientes();
        List<ClienteRepresentationModel> clientesRepresentationModel = toCollectionModel(clientes);
        return new ResponseEntity<List<ClienteRepresentationModel>>(clientesRepresentationModel,HttpStatus.OK);
    }


    private List<ClienteRepresentationModel> toCollectionModel(List<ClienteModel> clientesModel){
        return clientesModel.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    private ClienteModel toDomainObject(ClienteInput clienteInput){

        ClienteModel clienteModel = new ClienteModel();
        clienteModel.setId(clienteInput.getId());
        clienteModel.setNome(clienteInput.getNome());
        clienteModel.setCpf(clienteInput.getCpf());
        clienteModel.setRg(clienteInput.getRg());

        return clienteModel;
    }

    private ClienteRepresentationModel toModel(ClienteModel cli) {
        ClienteRepresentationModel clienteRepresentationModel = new ClienteRepresentationModel();
        clienteRepresentationModel.setId(cli.getId());
        clienteRepresentationModel.setNome(cli.getNome());
        clienteRepresentationModel.setCpf(cli.getCpf());
        clienteRepresentationModel.setRg(cli.getRg());

        return clienteRepresentationModel;
    }

}
