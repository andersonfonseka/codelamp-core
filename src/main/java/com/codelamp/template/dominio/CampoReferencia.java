package com.codelamp.template.dominio;

public class CampoReferencia extends Campo {

	public CampoReferencia(String tipo, String campoReferencia, String rotulo,
			int posicao) {
		super(tipo, tipo.substring(0, 1).toLowerCase()+tipo.substring(1), tipo, campoReferencia, rotulo, posicao);
	}

}
