package sds.teste.teste.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sds.teste.teste.model.Produto;
import sds.teste.teste.service.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> obiterTodos() {
        return produtoService.obiterTodos();
    }

    @PostMapping
    public Produto adicionar(@RequestBody final Produto produto) {
        return produtoService.adicionar(produto);
    }

    @GetMapping("/{id}")
    public Optional<Produto> obiterPorId(@PathVariable long id) {
        return produtoService.obiterPorId(id);
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable long id) {
        produtoService.deletar(id);
        return "Produto com Id: " + id + " foi deletado com sucesso.";
    }

    @PutMapping("/{id}")
    public Produto atualizar(@PathVariable final long id, @RequestBody final Produto produto) {
        return produtoService.atualizar(id, produto);
    }

}
