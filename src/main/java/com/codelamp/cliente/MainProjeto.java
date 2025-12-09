package com.codelamp.cliente;

import com.codelamp.template.dominio.Projeto;

public class MainProjeto {

	public static void main(String[] args) {
		
		Projeto projeto = new Projeto();
		projeto.setTitulo("Gestao Academica");
		
		projeto.addModulos(new MainAcademico());
		projeto.addModulos(new MainBiblioteca());
		projeto.addModulos(new MainFinanceiro());
		
		projeto.gerar();
		
		
	}
	
}
