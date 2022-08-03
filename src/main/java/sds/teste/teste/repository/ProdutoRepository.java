package sds.teste.teste.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sds.teste.teste.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
