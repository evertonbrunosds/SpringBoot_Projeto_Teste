package sds.teste.teste.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sds.teste.teste.model.Produto;
import sds.teste.teste.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> obiterTodos() {
        return produtoRepository.obiterTodos();
    }

    public Optional<Produto> obiterPorId(long id) {
        return produtoRepository.obiterPorId(id);
    }

    public synchronized Produto adicionar(final Produto produto) {
        return produtoRepository.adicionar(produto);
    }

    public void deletar(long id) {
        produtoRepository.deletar(id);
    }

    public Produto atualizar(long id, Produto produto) {
        produto.setId(id);
        return produtoRepository.atualizar(produto);
    }

}
