package com.codelamp.template.dominio.campo;

public abstract class Campo {

	private String tipo;
	
	private String tipoView;

	private String nome;

	private String referencia;

	private String campoReferencia;
	
	private String rotulo;
	
	private int posicao = 12;
	
	private String[] valores;
	
	private boolean referenciado = false;
	
	private boolean entradaLote;

	public Campo(String tipo, String nome) {
		super();
		this.tipo = tipo;
		this.nome = nome;
	}
	
	public Campo(String tipo, String nome, String rotulo, int posicao) {
		super();
		this.tipo = tipo;
		this.nome = nome;
		this.rotulo = rotulo;
		this.posicao = posicao;
	}
	
	public Campo(String tipo, String nome, String rotulo, int posicao, String...valores) {
		super();
		this.tipo = tipo;
		this.nome = nome;
		this.rotulo = rotulo;
		this.valores = valores;
		this.posicao = posicao;
	}
	
	public Campo(String tipo, String nome, String tipoReferencia, String campoReferencia, String rotulo, int posicao) {
		super();
		this.tipo = tipo;
		this.nome = nome;
		this.referencia = tipoReferencia;
		this.campoReferencia = campoReferencia;
		this.rotulo = rotulo;
		this.posicao = posicao;
	}

	public String getRotulo() {
		return rotulo;
	}

	public void setRotulo(String rotulo) {
		this.rotulo = rotulo;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getCampoReferencia() {
		return campoReferencia;
	}

	public void setCampoReferencia(String campoReferencia) {
		this.campoReferencia = campoReferencia;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String[] getValores() {
		return valores;
	}

	public void setValores(String[] valores) {
		this.valores = valores;
	}
	
	public int getPosicao() {
		return posicao;
	}

	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}

	public String getTipoEntrada() {
		return obterTipoEntrada(this.tipo);
	}
	
	public boolean isReferenciado() {
		return referenciado;
	}

	public void setReferenciado(boolean referenciado) {
		this.referenciado = referenciado;
	}

	public boolean isEntradaLote() {
		return entradaLote;
	}

	public void setEntradaLote(boolean entradaLote) {
		this.entradaLote = entradaLote;
	}

	public String metodoGet() {
		return "get"+ this.nome.substring(0,1).toUpperCase() + this.nome.substring(1);
	}
	
	private String obterTipoEntrada(String tipo) {
		
		String valor = "";
		
		switch (tipo) {
			case "String":
				valor = "text";
				break;
			case "Date":
				valor = "date";
				break;
			case "double":
				valor = "number";
				break;
			case "long":
				valor = "number";
				break;
			case "int":
				valor = "number";
				break;
			default:
				valor = "text";
		}
		
		if (this instanceof CampoEmail 
				|| this instanceof CampoURL 
				|| this instanceof CampoAreaTexto 
				|| this instanceof CampoArquivo
				|| this instanceof CampoImagem) {
			
			valor = this.tipoView;
		}
		
		return valor;
	}
	
	public String getTipoView() {
		return tipoView;
	}

	public void setTipoView(String tipoView) {
		this.tipoView = tipoView;
	}

	public boolean isEnum() {
		return this.tipo.equals("Enum");
	}
	
	public boolean isReferencia() {
		return this instanceof CampoReferencia;
	}
	
	public String getVarNome() {
		return nome.substring(0, 1).toLowerCase()+nome.substring(1);
	}
	
	public String getVarTipo() {
		return tipo.substring(0, 1).toLowerCase()+tipo.substring(1);
	}


}
