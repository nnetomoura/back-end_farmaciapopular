package com.generation.farmaciapopular.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "*" , allowedHeaders = "*")

public class CategoriaController {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> getAll() {
		return ResponseEntity.ok(categoriaRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> getById(@PathVariable Long id) {
		return categoriaRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/pesquisa/{categoriaProduto}")
	public ResponseEntity<List<Categoria>> getByTitle(@PathVariable String categoriaProduto){
		return ResponseEntity.ok(categoriaRepository.findAllByCategoriaProdutoContainingIgnoreCase(categoriaProduto));
	}
	
	@PostMapping
	public ResponseEntity<Categoria> post(@Valid @RequestBody Categoria categoria) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(categoriaRepository.save(categoria));
	}
	
	@PutMapping
	public ResponseEntity<Categoria> put(@Valid @RequestBody Categoria categoria) {
		return categoriaRepository.findById(categoria.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED)
					.body(categoriaRepository.save(categoria)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		java.util.Optional<Categoria> categoria = categoriaRepository.findById(id);
		
		if(categoria.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		categoriaRepository.deleteById(id);
	}
	
	
	
	
	

}
