package com.codelamp.template.dominio;

import java.util.ArrayList;
import java.util.List;

public class ValidadorResultado {
	
	private boolean valido = true;
	
	private List<String> mensagens = new ArrayList<>();

	public boolean isValido() {
		return valido;
	}

	public void setValido(boolean valido) {
		this.valido = valido;
	}

	public String getMensagem() {
		
		StringBuilder sb = new StringBuilder();
		
		for (String msg : mensagens) {
			sb.append(msg + "\n");
		}
		
		return sb.toString();
	}

	public void addMensagem(String mensagem) {
		this.mensagens.add(mensagem);
	}
	
}
