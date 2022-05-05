package com.generation.farmaciapopular.model;

@Entity
@Table(name = "tb_produto")

public class Produto {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	

	@Size(min = 5, max = 300)
	private String nomeProduto;
	
	private float valor;
	

	@Size(min = 5, max = 300)
	private String fornecedor;
	
	
	@Size(min = 2, max = 255)
	private String tipo;
	
	@Size(min = 5, max = 3000)
	private String bulaSimplificada;
	
	@ManyToOne
	@JsonIgnoreProperties("produto")
	private Categoria categoria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}


	public double getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getBulaSimplificada() {
		return bulaSimplificada;
	}

	public void setBulaSimplificada(String bulaSimplificada) {
		this.bulaSimplificada = bulaSimplificada;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}
