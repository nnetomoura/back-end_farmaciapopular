package com.generation.farmaciapopular.repository;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	public List<Produto> findAllByNomeProdutoContainingIgnoreCase (@Param("nomeProduto")String nomeProduto);
}