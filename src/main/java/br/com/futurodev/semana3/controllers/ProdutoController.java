package br.com.futurodev.semana3.controllers;

import br.com.futurodev.semana3.dto.ItemPedidoRepresentationModel;
import br.com.futurodev.semana3.dto.ProdutoRepresentationModel;
import br.com.futurodev.semana3.input.ProdutoInput;
import br.com.futurodev.semana3.model.ProdutoModel;
import br.com.futurodev.semana3.service.CadastroProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "Produtos")
@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

    @Autowired
    private CadastroProdutoService cadastroProdutoService;

    @ApiOperation("Salvar um produto")
    @PostMapping
    public ResponseEntity<ProdutoRepresentationModel> cadastrar(@RequestBody ProdutoInput produtoInput) {
        ProdutoModel produto = toDomainObject(produtoInput);
        cadastroProdutoService.salvar(produto);
        return new ResponseEntity<ProdutoRepresentationModel>(toModel(produto), HttpStatus.CREATED);
    }

    @ApiOperation("Atualiza um produto")
    @PutMapping
    public ResponseEntity<ProdutoRepresentationModel> atualizar(@RequestBody ProdutoInput produtoInput) {
        ProdutoModel produto = cadastroProdutoService.salvar(toDomainObject(produtoInput));
        return new ResponseEntity<ProdutoRepresentationModel>(toModel(produto), HttpStatus.OK);
    }

    @ApiOperation("Deleta um produto")
    @DeleteMapping
    @ResponseBody
    public ResponseEntity<String> delete(
            @ApiParam(value = "ID do produto", example = "1")
            @RequestParam Long idProduto) {
        cadastroProdutoService.deleteById(idProduto);
        return new ResponseEntity<String>("Produto ID: " + idProduto + ": deletado com sucesso!", HttpStatus.OK);
    }

    @ApiOperation("Busca um produto por ID")
    @GetMapping(value = "/{idProduto}")
    public ResponseEntity<ProdutoRepresentationModel> getProdutoById(
            @ApiParam(value = "Id do produto", example = "1")
            @PathVariable(value = "idProduto") Long idProduto) {
        ProdutoRepresentationModel produtoRepresentationModel = toModel(cadastroProdutoService.getProdutoById(idProduto));
        return new ResponseEntity<ProdutoRepresentationModel>(produtoRepresentationModel, HttpStatus.OK);
    }

    @ApiOperation("Busca produtos por descrição")
    @GetMapping(value = "/{descricao}")
    public ResponseEntity<List<ProdutoRepresentationModel>> getProdutosByName(
            @PathVariable(name = "descricao") String descricao) {

        List<ProdutoModel> produtos = cadastroProdutoService.getProdutosByDescricao(descricao);
        List<ProdutoRepresentationModel> produtosRepresentationModel = toCollectionModel(produtos);
        return new ResponseEntity<List<ProdutoRepresentationModel>>(produtosRepresentationModel, HttpStatus.OK);
    }

    @ApiOperation("Lista produtos")
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<ProdutoRepresentationModel>> getProdutos() {
        List<ProdutoModel> produtos = cadastroProdutoService.getProdutos();
        List<ProdutoRepresentationModel> produtosRepresentationModel = toCollectionModel(produtos);
        return new ResponseEntity<List<ProdutoRepresentationModel>>(produtosRepresentationModel, HttpStatus.OK);
    }


    private ProdutoModel toDomainObject(ProdutoInput produtoInput) {
        ProdutoModel produto = new ProdutoModel();
        produto.setId(produtoInput.getIdProduto());
        produto.setDescricao(produtoInput.getDescricao());
        produto.setPrecoCompra(produtoInput.getCompra());
        produto.setPrecoVenda(produtoInput.getVenda());
        return produto;
    }

    private ProdutoRepresentationModel toModel(ProdutoModel produto) {
        ProdutoRepresentationModel produtoRepresentationModel = new ProdutoRepresentationModel();
        produtoRepresentationModel.setId(produto.getId());
        produtoRepresentationModel.setDecricao(produto.getDescricao());
        produtoRepresentationModel.setPrecoCompra(produto.getPrecoCompra());
        produtoRepresentationModel.setPrecoVenda(produto.getPrecoVenda());
        return produtoRepresentationModel;
    }

    // Converte uma lista de objetos do tipo Produto para uma lista de objetos do tipo ProdutoRepresentationModel
    private List<ProdutoRepresentationModel> toCollectionModel(List<ProdutoModel> produtos) {
        return produtos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());

    }
}
