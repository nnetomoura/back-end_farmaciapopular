package com.generation.farmaciapopular.repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	public List<Categoria> findAllByCategoriaProdutoContainingIgnoreCase (@Param("categoriaProduto")String categoriaProduto);

}
