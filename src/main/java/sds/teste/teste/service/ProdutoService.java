package sds.teste.teste.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sds.teste.teste.model.Produto;
import sds.teste.teste.model.exceptions.ResourceNotFoundException;
import sds.teste.teste.repository.ProdutoRepository;
import sds.teste.teste.shared.ProdutoDTO;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoDTO> obiterTodos() {
        final List<Produto> produtos = produtoRepository.findAll();
        return produtos
                .stream()
                .map(produto -> new ModelMapper().map(produto, ProdutoDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<ProdutoDTO> obiterPorId(int id) {
        final Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isEmpty()) {
            throw new ResourceNotFoundException("Produto com id: " + id + " não encontrado.");
        } else {
            final ProdutoDTO produtoDTO = new ModelMapper().map(produto.get(), ProdutoDTO.class);
            return Optional.of(produtoDTO);
        }
    }

    public synchronized ProdutoDTO adicionar(final ProdutoDTO produtoDTO) {
        produtoDTO.setId(null);
        final ModelMapper mapper = new ModelMapper();
        Produto produto = mapper.map(produtoDTO, Produto.class);
        produto = produtoRepository.save(produto);
        produtoDTO.setId(produto.getId());
        return produtoDTO;
    }

    public void deletar(int id) {
        final Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isEmpty()) {
            throw new ResourceNotFoundException("Não foi possível deletar o produto com id: " + id);
        } else {
            produtoRepository.deleteById(id);
        }
    }

    public ProdutoDTO atualizar(int id, ProdutoDTO produtoDTO) {
        produtoDTO.setId(id);
        final ModelMapper mapper = new ModelMapper();
        final Produto produto = mapper.map(produtoDTO, Produto.class);
        produtoRepository.save(produto);
        return produtoDTO;
    }

}
