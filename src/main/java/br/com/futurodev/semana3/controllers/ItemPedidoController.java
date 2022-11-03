package br.com.futurodev.semana3.controllers;

import br.com.futurodev.semana3.dto.ItemPedidoRepresentationModel;
import br.com.futurodev.semana3.dto.ProdutoRepresentationModel;
import br.com.futurodev.semana3.input.ItemPedidoInput;
import br.com.futurodev.semana3.input.ProdutoInput;
import br.com.futurodev.semana3.model.ItemPedidoModel;
import br.com.futurodev.semana3.model.ProdutoModel;
import br.com.futurodev.semana3.service.CadastroItemPedidoService;
import br.com.futurodev.semana3.service.CadastroPedidoService;
import br.com.futurodev.semana3.service.CadastroProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "Itens Pedido")
@RestController
@RequestMapping(value = "/itemPedidos")
public class ItemPedidoController {

    @Autowired
    private CadastroItemPedidoService cadastroItemPedidoService;

    @Autowired
    private CadastroProdutoService cadastroProdutoService;

    @Autowired
    private CadastroPedidoService cadastroPedidoService;

    @ApiOperation("Salva itens de um pedido")
    @PostMapping(value = "/", produces ="application/json")
    public ResponseEntity<ItemPedidoRepresentationModel> cadastrar(@RequestBody ItemPedidoInput itemPedidoInput){
        ItemPedidoModel item = toDomainObject(itemPedidoInput);
        cadastroItemPedidoService.salvar(item);
        return new ResponseEntity<ItemPedidoRepresentationModel>(toModel(item), HttpStatus.CREATED);
    }

    @ApiOperation("Lista itens de um pedido")
    @GetMapping(value = "/", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<ItemPedidoRepresentationModel>> getItensPedido(){
        List<ItemPedidoModel> itens = cadastroItemPedidoService.getItensPedido();
        List<ItemPedidoRepresentationModel> itemPedidoRepresentationModels = toCollectionModel(itens);
        return new ResponseEntity<List<ItemPedidoRepresentationModel>>(itemPedidoRepresentationModels,HttpStatus.OK);
    }

    private List<ItemPedidoRepresentationModel> toCollectionModel(List<ItemPedidoModel> itemPedidoModel){
        return itemPedidoModel.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    private ItemPedidoModel toDomainObject(ItemPedidoInput itemPedidoInput){

        ItemPedidoModel itemPedidoModel = new ItemPedidoModel();
        itemPedidoModel.setId(itemPedidoInput.getId());
        itemPedidoModel.setQuantidade(itemPedidoInput.getQuantidade());
        itemPedidoModel.setValorItem(itemPedidoInput.getPrecoVenda());
        itemPedidoModel.setPedido(cadastroPedidoService.getPedidoById(itemPedidoInput.getIdPedido()));
        itemPedidoModel.setProduto(cadastroProdutoService.getProdutoById(itemPedidoInput.getIdProduto()));

        return itemPedidoModel;
    }

    private ItemPedidoRepresentationModel toModel(ItemPedidoModel itemPedidoModel){
        ItemPedidoRepresentationModel itemPedidoRepresentationModel = new ItemPedidoRepresentationModel();
        itemPedidoRepresentationModel.setId(itemPedidoModel.getId());
        itemPedidoRepresentationModel.setQuantidade(itemPedidoModel.getQuantidade());
        itemPedidoRepresentationModel.setValorItem(itemPedidoModel.getValorItem());
//        itemPedidoRepresentationModel.setIdPedido(itemPedidoModel.getPedido().getId());
        itemPedidoRepresentationModel.setIdProduto(itemPedidoModel.getProduto().getId());

        return itemPedidoRepresentationModel;
    }

}
