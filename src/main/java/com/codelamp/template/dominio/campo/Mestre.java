package com.codelamp.template.dominio.campo;

public class Mestre extends CampoReferencia {

	private Mestre(String tipo, String campoReferencia, String rotulo, int posicao) {
		super(tipo, campoReferencia, rotulo, posicao);
	}
	
	public static Mestre build(String tipo, String campoReferencia, String rotulo) {
		return new Mestre(tipo, campoReferencia, rotulo, 12);
	}

}
