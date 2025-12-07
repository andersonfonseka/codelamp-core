package com.codelamp.cliente;

import com.codelamp.template.crud.Entidade;
import com.codelamp.template.dominio.Acao;
import com.codelamp.template.dominio.Modulo;
import com.codelamp.template.dominio.campo.CampoInteiro;
import com.codelamp.template.dominio.campo.CampoReferencia;
import com.codelamp.template.dominio.campo.CampoTexto;
import com.codelamp.template.md.MestreDetalhe;

public class MainProdutos {
	
	public static void main(String[] args) {
		
		 Modulo modulo = new Modulo();
         modulo.setTitulo("Gestão de Produtos");

	     MestreDetalhe produto = new MestreDetalhe();
	     produto.setTitulo("Produtos");
	     produto.setClasse("Produto");

	     produto.setCampoMestre(new CampoReferencia("Categoria", "Descricao", "Categoria", 12));

	     produto.adicionar(new CampoTexto("Descricao", "Descrição", 8));
	     produto.adicionar(new CampoInteiro("Quantidade", "Quantidade", 4));
	     
	     modulo.adicionar(produto);
	     
	     Entidade categoria = new Entidade();
	     categoria.setTitulo("Categoria");
	     categoria.setClasse("Categoria");
	     
	     categoria.adicionar(new CampoTexto("Descricao", "Descrição", 12));
	     categoria.adicionarAcao(new Acao(produto, "Produtos", "descricao"));
	     
	     modulo.adicionar(categoria);

	     modulo.gerar();
		
	}

}
