package com.codelamp.template.dominio.campo;

public class CampoArquivo extends Campo {

	public CampoArquivo(String nome, String rotulo, int posicao) {
		super("String", nome, rotulo, posicao);
		this.setTipoView("file");
	}

}
