package com.codelamp.template.dominio.campo;

public class CampoEnum extends Campo {

	public CampoEnum(String nome, String rotulo, int posicao, String[] valores) {
		super("Enum", nome, rotulo, posicao, valores);
	}
	
	public CampoEnum(String nome, String rotulo, int posicao) {
		super("Enum", nome, rotulo, posicao);
	}

	public CampoEnum opcoes(String[] valores) {
		super.setValores(valores);
		return this;
	}
	
	
}
