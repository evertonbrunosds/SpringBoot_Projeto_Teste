package sds.teste.teste.repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import sds.teste.teste.model.Produto;
import sds.teste.teste.model.exceptions.ResourceNotFoundException;

@Repository
@Deprecated
public class ProdutoRepository_OLD {

    private List<Produto> produtos;
    private int ultimoId;

    public ProdutoRepository_OLD() {
        produtos = new LinkedList<>();
        ultimoId = 0;
    }

    public List<Produto> obiterTodos() {
        return produtos;
    }

    public Optional<Produto> obiterPorId(long id) {
        return produtos
                .stream()
                .filter(produto -> produto.getId() == id)
                .findAny();
    }

    public synchronized Produto adicionar(final Produto produto) {
        ultimoId++;
        produtos.add(produto);
        produto.setId(ultimoId);
        return produto;
    }

    public void deletar(long id) {
        produtos.removeIf(produto -> produto.getId() == id);
    }

    public Produto atualizar(Produto produto) {
        final Optional<Produto> produtoEncontrado = obiterPorId(produto.getId());
        if (produtoEncontrado.isEmpty()) {
            throw new ResourceNotFoundException("Produto não encontrado.");
        }
        deletar(produto.getId());
        produtos.add(produto);
        return produto;
    }

}
