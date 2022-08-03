package sds.teste.teste.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import sds.teste.teste.shared.ProdutoDTO;
import sds.teste.teste.view.model.ProdutoRequest;
import sds.teste.teste.view.model.ProdutoResponse;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> obiterTodos() {
        final List<ProdutoDTO> produtos = produtoService.obiterTodos();
        final ModelMapper mapper = new ModelMapper();
        final List<ProdutoResponse> response = produtos
                .stream()
                .map(produto -> mapper.map(produto, ProdutoResponse.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> adicionar(@RequestBody final ProdutoRequest produtoRequest) {
        final ModelMapper mapper = new ModelMapper();
        ProdutoDTO produtoDTO = mapper.map(produtoRequest, ProdutoDTO.class);
        produtoDTO = produtoService.adicionar(produtoDTO);
        final ProdutoResponse produtoResponse = mapper.map(produtoDTO, ProdutoResponse.class);
        return new ResponseEntity<>(produtoResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProdutoResponse>> obiterPorId(@PathVariable int id) {
        try {
            final Optional<ProdutoDTO> produtoDTO = produtoService.obiterPorId(id);
            final ProdutoResponse response = new ModelMapper().map(produtoDTO, ProdutoResponse.class);
            return new ResponseEntity<>(Optional.of(response), HttpStatus.OK);
        } catch (final Exception ex) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable int id) {
        produtoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> atualizar(@PathVariable final int id, @RequestBody final ProdutoRequest produtoRequest) {
        final ModelMapper mapper = new ModelMapper();
        ProdutoDTO produtoDTO = mapper.map(produtoRequest, ProdutoDTO.class);
        produtoDTO = produtoService.atualizar(id, produtoDTO);
        final ProdutoResponse produtoResponse = mapper.map(produtoDTO, ProdutoResponse.class);
        return new ResponseEntity<>(produtoResponse, HttpStatus.OK);
    }

}
