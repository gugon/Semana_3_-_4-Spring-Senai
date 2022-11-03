package br.com.futurodev.semana3.controllers;

import br.com.futurodev.semana3.dto.ItemPedidoRepresentationModel;
import br.com.futurodev.semana3.dto.PedidoRepresentationModel;
import br.com.futurodev.semana3.input.PedidoInput;
import br.com.futurodev.semana3.model.ItemPedidoModel;
import br.com.futurodev.semana3.model.PedidoModel;
import br.com.futurodev.semana3.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Api(tags = "Pedidos")
@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

    @Autowired
    private CadastroPedidoService cadastroPedidoService;

    @Autowired
    private CadastroClienteService cadastroClienteService;

    @Autowired
    private CadastroFormaPagamentoService cadastroFormaPagamentoService;

    @Autowired
    private CadastroProdutoService cadastroProdutoService;

    @Autowired
    private CadastroItemPedidoService itemPedidoService;


    @ApiOperation("Cadastra um pedido")
    @PostMapping(value = "/", produces ="application/json")
    public ResponseEntity<PedidoRepresentationModel> cadastrar(@RequestBody PedidoInput pedidoInput) {
        PedidoModel pedido = cadastroPedidoService.salvar(toDomainObject(pedidoInput));
        return new ResponseEntity<PedidoRepresentationModel>(toRepresentatioModel(pedido), HttpStatus.CREATED);
    }

    @ApiOperation("Atualiza um pedido")
    @PutMapping(value = "/", produces ="application/json")
    public ResponseEntity<PedidoRepresentationModel> atualizar(@RequestBody PedidoInput pedidoInput) {
        PedidoModel pedido = cadastroPedidoService.salvar(toDomainObject(pedidoInput));
        return new ResponseEntity<PedidoRepresentationModel>(toRepresentatioModel(pedido), HttpStatus.OK);
    }

    @ApiOperation("Deleta um pedido ataravés do idPedido")
    @DeleteMapping
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long idPedido) {
        cadastroPedidoService.deletePedidoById(idPedido);
        return new ResponseEntity<String>("Pedido de ID: " + idPedido + " deletado.", HttpStatus.OK);
    }

    @ApiOperation("Busca pedido pelo idPedido")
    @GetMapping(value = "/{idPedido}")
    public ResponseEntity<PedidoRepresentationModel> getPedidoById(
            @PathVariable(value = "idPedido") Long idPedido) {

        PedidoRepresentationModel pedidoRepresentationModel =
                toRepresentatioModel(cadastroPedidoService.getPedidoById(idPedido));

        return new ResponseEntity<PedidoRepresentationModel>(pedidoRepresentationModel, HttpStatus.OK);

    }

    @ApiOperation("Listar pedidos")
    @GetMapping(value = "/", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<PedidoRepresentationModel>> getPedidos() {
        List<PedidoModel> pedidos = cadastroPedidoService.getPedidos();

        return
                new ResponseEntity<List<PedidoRepresentationModel>>(toCollectionRepresentationModel(pedidos), HttpStatus.OK);
    }

    @ApiOperation("Busca pedidos pelo idCliente")
    @GetMapping(value = "/cliente/{idCliente}")
    public ResponseEntity<List<PedidoRepresentationModel>> getPedidoByIdCliente(
            @PathVariable(name = "idCliente") Long idCliente) {

        List<PedidoModel> pedidos = cadastroPedidoService.getPedidoByIdCliente(idCliente);
        List<PedidoRepresentationModel> pedidosRM = toCollectionRepresentationModel(pedidos);

        return new ResponseEntity<List<PedidoRepresentationModel>>(pedidosRM, HttpStatus.OK);
    }

    @ApiOperation("Deleta um itemPedido através de um pedido")
    @DeleteMapping(value = "/{idPedido}/item/{idItemPedido}")
    @ResponseBody
    public ResponseEntity<String> deleteItemPedidoById(@RequestParam(name = "idPedido") Long idPedido,
                                                       @RequestParam(name = "idItemPedido") Long idItemPedido) {

        ItemPedidoModel itemPedido = itemPedidoService.getItemPedidoById(idItemPedido);
        itemPedidoService.deleteItemPedido(itemPedido);
        return new ResponseEntity<String>("Item de ID: " + idItemPedido + " deletado.", HttpStatus.OK);
    }


    //converte de Model para DTO de Reposta
    private PedidoRepresentationModel toRepresentatioModel(PedidoModel pedido) {
        PedidoRepresentationModel pedidoRepresentationModel = new PedidoRepresentationModel();
        pedidoRepresentationModel.setId(pedido.getId());
        pedidoRepresentationModel.setIdCliente(pedido.getCliente().getId());
        pedidoRepresentationModel.setNomeCliente(pedido.getCliente().getNome());
        pedidoRepresentationModel.setIdFormaPagamento(pedido.getFormaPagamento().getId());
        pedidoRepresentationModel.setFormaPagamentoDescricao(pedido.getFormaPagamento().getDescricao());

        for (int i = 0; i < pedido.getItensPedido().size(); i++) {
            ItemPedidoRepresentationModel itemPedidoRepresentationModel = new ItemPedidoRepresentationModel();
            itemPedidoRepresentationModel.setId(pedido.getItensPedido().get(i).getId());
            itemPedidoRepresentationModel.setIdProduto(pedido.getItensPedido().get(i).getProduto().getId());
            itemPedidoRepresentationModel.setDescricaoProduto(pedido.getItensPedido().get(i).getProduto().getDescricao());
            itemPedidoRepresentationModel.setQuantidade(pedido.getItensPedido().get(i).getQuantidade());
            itemPedidoRepresentationModel.setValorItem(pedido.getItensPedido().get(i).getValorItem());

            pedidoRepresentationModel.getItensPedidoRepresentationModel().add(itemPedidoRepresentationModel);

        }


        return pedidoRepresentationModel;
    }


    //Converte de DTO de entrada para Model
    private PedidoModel toDomainObject(PedidoInput pedidoInput) {

        PedidoModel pedido = new PedidoModel();
        pedido.setId(pedidoInput.getIdPedido());
        pedido.setCliente(cadastroClienteService.getClienteById(pedidoInput.getIdCliente()));
        pedido.setFormaPagamento(cadastroFormaPagamentoService.getFormaPagamentoById(pedidoInput.getIdFormaPagamento()));

        for (int i = 0; i < pedidoInput.getItensPedido().size(); i++) {
            ItemPedidoModel itemPedido = new ItemPedidoModel();
            itemPedido.setId(pedidoInput.getItensPedido().get(i).getId());
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(cadastroProdutoService.getProdutoById(pedidoInput.getItensPedido().get(i).getIdProduto()));

            // se o ID do itemPedido for null busca o preço do cadastro de produto
            if (pedidoInput.getItensPedido().get(i).getId() == null) {
                itemPedido.setValorItem(cadastroProdutoService
                        .getProdutoById(pedidoInput
                                .getItensPedido().get(i)
                                .getIdProduto()).getPrecoVenda());
                // se não pega o preco do Json de entrada
            } else {
                itemPedido.setValorItem(pedidoInput.getItensPedido().get(i).getPrecoVenda());
            }
            itemPedido.setQuantidade(pedidoInput.getItensPedido().get(i).getQuantidade());

            // adiciono a lista de itensPedido do model
            pedido.getItensPedido().add(itemPedido);
        }
        return pedido;
    }

    private List<PedidoRepresentationModel> toCollectionRepresentationModel(List<PedidoModel> pedidos) {
        return pedidos.stream()
                .map(this::toRepresentatioModel)
                .collect(Collectors.toList());
    }
}

