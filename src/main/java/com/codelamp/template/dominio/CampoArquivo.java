package com.codelamp.template.dominio;

public class CampoArquivo extends Campo {

	public CampoArquivo(String nome, String rotulo, int posicao) {
		super("String", nome.substring(0, 1).toLowerCase()+nome.substring(1), rotulo, posicao);
		this.setTipoView("file");
	}

}
