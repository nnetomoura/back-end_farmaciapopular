package com.generation.farmaciapopular.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos")
@CrossOrigin("*")

public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@GetMapping
	public ResponseEntity<List<Produto>> GetAll() {
		return ResponseEntity.ok(produtoRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable Long id) {
		return produtoRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@GetMapping("/pesquisa/{nomeProduto}")
	public ResponseEntity<List<Produto>> getByTitle(@PathVariable String nomeProduto) {
		return ResponseEntity.ok(produtoRepository.findAllByNomeProdutoContainingIgnoreCase(nomeProduto));
	}

	@PostMapping
	public ResponseEntity<Produto> post(@Valid @RequestBody Produto produto) {
		if (categoriaRepository.existsById(produto.getCategoria().getId()))
			return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	@PutMapping
	public ResponseEntity<Produto> put(@Valid @RequestBody Produto produto) {
		if (produtoRepository.existsById(produto.getId())) {
			if (categoriaRepository.existsById(produto.getId()))
				return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		java.util.Optional<Produto> produto = produtoRepository.findById(id);
		
		if (produto.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		produtoRepository.deleteById(id);
	}
	
	
	
	
	
	

}
