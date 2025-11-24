package com.codelamp.cliente;

import com.codelamp.template.crud.Entidade;
import com.codelamp.template.dominio.Acao;
import com.codelamp.template.dominio.EntidadeReferencia;
import com.codelamp.template.dominio.Modulo;
import com.codelamp.template.dominio.campo.CampoDate;
import com.codelamp.template.dominio.campo.CampoDouble;
import com.codelamp.template.dominio.campo.CampoEmail;
import com.codelamp.template.dominio.campo.CampoEnum;
import com.codelamp.template.dominio.campo.CampoInteiro;
import com.codelamp.template.dominio.campo.CampoReferencia;
import com.codelamp.template.dominio.campo.CampoTexto;
import com.codelamp.template.kanban.Kanban;
import com.codelamp.template.md.MestreDetalhe;

public class MainCRM {

	public static void main(String[] args) {

		// ===========================================================
		// === MÓDULO PRINCIPAL
		// ===========================================================
		Modulo modulo = new Modulo();
		modulo.setTitulo("CRM Comercial");

		// ===========================================================
		// === MESTRES DETALHE (sempre primeiro)
		// ===========================================================

		// -----------------------------------------------------------
		// (1) EMPRESA → CONTATOS
		// -----------------------------------------------------------
		MestreDetalhe empresaContatos = new MestreDetalhe();
		empresaContatos.setTitulo("Contatos da Empresa");
		empresaContatos.setClasse("EmpresaContato");

		empresaContatos.setCampoMestre(new CampoReferencia("Empresa", "nome", "Empresa", 6));

		empresaContatos.adicionar(new CampoReferencia("Contato", "nome", "Contato", 6));

		modulo.adicionar(empresaContatos);

		// -----------------------------------------------------------
		// (2) EMPRESA → OPORTUNIDADES
		// -----------------------------------------------------------
		MestreDetalhe empresaOportunidades = new MestreDetalhe();
		empresaOportunidades.setTitulo("Oportunidades da Empresa");
		empresaOportunidades.setClasse("EmpresaOportunidade");

		empresaOportunidades.setCampoMestre(new CampoReferencia("Empresa", "nome", "Empresa", 6));

		empresaOportunidades.adicionar(new CampoReferencia("Oportunidade", "titulo", "Oportunidade", 6));

		modulo.adicionar(empresaOportunidades);

		// -----------------------------------------------------------
		// (3) OPORTUNIDADE → ATIVIDADES
		// -----------------------------------------------------------
		MestreDetalhe oportunidadeAtividades = new MestreDetalhe();
		oportunidadeAtividades.setTitulo("Atividades da Oportunidade");
		oportunidadeAtividades.setClasse("OportunidadeAtividade");

		oportunidadeAtividades.setCampoMestre(new CampoReferencia("Oportunidade", "titulo", "Oportunidade", 6));

		oportunidadeAtividades.adicionar(new CampoTexto("descricao", "Descrição da Atividade", 12));

		oportunidadeAtividades.adicionar(new CampoDate("data", "Data", 4));

		oportunidadeAtividades.adicionar(
				new CampoEnum("status", "Status", 4, new String[] { "FOLLOW_UP", "REUNIAO", "PROPOSTA", "LIGACAO" }));

		modulo.adicionar(oportunidadeAtividades);

		// -----------------------------------------------------------
		// (4) OPORTUNIDADE → ITENS (PRODUTOS)
		// -----------------------------------------------------------
		MestreDetalhe oportunidadeItens = new MestreDetalhe();
		oportunidadeItens.setTitulo("Itens da Oportunidade");
		oportunidadeItens.setClasse("OportunidadeItem");

		oportunidadeItens.setCampoMestre(new CampoReferencia("Oportunidade", "titulo", "Oportunidade", 6));

		oportunidadeItens.adicionar(new CampoReferencia("Produto", "nome", "Produto", 6));

		oportunidadeItens.adicionar(new CampoInteiro("quantidade", "Quantidade", 3));

		oportunidadeItens.adicionar(new CampoDouble("valorUnitario", "Valor Unitário", 3));

		modulo.adicionar(oportunidadeItens);

		// ===========================================================
		// === KANBAN DO FUNIL DE VENDAS
		// ===========================================================
		Kanban kanban = new Kanban();
		kanban.setTitulo("Funil de Vendas");
		kanban.setClasse("KanbanVendas");

		kanban.setMestreDetalhe(oportunidadeAtividades);

		kanban.setAtributos(new String[] { "oportunidade.descricao", "oportunidade.empresa.nome", "oportunidade.valorPrevisto",
				"oportunidade.previsaoFechamentoStr" });

		modulo.adicionar(kanban);

		// ===========================================================
		// === ENTIDADES
		// ===========================================================

		// -----------------------------------------------------------
		// EMPRESA
		// -----------------------------------------------------------
		Entidade empresa = new Entidade();
		empresa.setTitulo("Empresa");
		empresa.setClasse("Empresa");

		empresa.adicionar(new CampoTexto("nome", "Nome da Empresa", 8));
		empresa.adicionar(new CampoTexto("cnpj", "CNPJ", 6));
		empresa.adicionar(new CampoTexto("segmento", "Segmento", 6));

		empresa.adicionarAcao(new Acao(empresaContatos, "Contatos", "nome"));
		empresa.adicionarAcao(new Acao(empresaOportunidades, "Oportunidades", "nome"));

		modulo.adicionar(empresa);

		// -----------------------------------------------------------
		// CONTATO
		// -----------------------------------------------------------
		Entidade contato = new Entidade();
		contato.setTitulo("Contato");
		contato.setClasse("Contato");

		contato.adicionar(new CampoTexto("nome", "Nome", 8));
		contato.adicionar(new CampoEmail("email", "E-mail", 6));
		contato.adicionar(new CampoTexto("telefone", "Telefone", 6));
		contato.adicionar(new CampoReferencia("Empresa", "nome", "Empresa", 6));

		modulo.adicionar(contato);

		// -----------------------------------------------------------
		// VENDEDOR
		// -----------------------------------------------------------
		Entidade vendedor = new Entidade();
		vendedor.setTitulo("Vendedor");
		vendedor.setClasse("Vendedor");

		vendedor.adicionar(new CampoTexto("nome", "Nome", 8));
		vendedor.adicionar(new CampoEmail("email", "E-mail", 6));

		modulo.adicionar(vendedor);

		// -----------------------------------------------------------
		// PRODUTO
		// -----------------------------------------------------------
		Entidade produto = new Entidade();
		produto.setTitulo("Produto");
		produto.setClasse("Produto");

		produto.adicionar(new CampoTexto("nome", "Nome do Produto", 8));
		produto.adicionar(new CampoDouble("preco", "Preço", 4));

		modulo.adicionar(produto);

		// -----------------------------------------------------------
		// OPORTUNIDADE
		// -----------------------------------------------------------
		Entidade oportunidade = new Entidade();
		oportunidade.setTitulo("Oportunidade");
		oportunidade.setClasse("Oportunidade");

		oportunidade.adicionar(new CampoTexto("titulo", "Título da Oportunidade", 8));

		oportunidade.adicionar(new CampoReferencia("Empresa", "nome", "Empresa", 6));
		oportunidade.adicionar(new CampoReferencia("Vendedor", "nome", "Vendedor", 6));

		oportunidade.adicionar(new CampoDouble("valorPrevisto", "Valor Previsto", 4));
		oportunidade.adicionar(new CampoDate("previsaoFechamento", "Previsão de Fechamento", 4));

		oportunidade.adicionar(new CampoEnum("status", "Status", 6,
				new String[] { "PROSPECÇÃO", "QUALIFICAÇÃO", "PROPOSTA", "NEGOCIAÇÃO", "FECHADO" }));

		oportunidade.adicionarAcao(new Acao(oportunidadeAtividades, "Atividades", "titulo"));
		oportunidade.adicionarAcao(new Acao(oportunidadeItens, "Itens", "titulo"));
		oportunidade.adicionarAcao(new Acao(kanban, "Kanban", "titulo"));

		modulo.adicionar(oportunidade);
		
		
		// ===========================================================
		// === KANBAN DO FUNIL DE VENDAS
		// ===========================================================
		Kanban kanbanOpp = new Kanban();
		kanbanOpp.setTitulo("Funil de Oportunidades");
		kanbanOpp.setClasse("KanbanOportunidade");

		kanbanOpp.setEntidadeReferencia(new EntidadeReferencia(oportunidade, "titulo"));
		kanbanOpp.setCampoReferencia(new CampoReferencia("Oportunidade", "Oportunidade", "oportunidade", 12));
		kanbanOpp.setCampoStatus(new CampoEnum("status", "Status", 6, new String[] { "PROSPECÇÃO", "QUALIFICAÇÃO", "PROPOSTA", "NEGOCIAÇÃO", "FECHADO" }));
		
		kanbanOpp.setAtributos(new String[] {"titulo", "empresa.nome", "valorPrevisto"});

		kanbanOpp.setMenuVisivel(true);
		
		modulo.adicionar(kanbanOpp);

		// ===========================================================
		// === GERAR
		// ===========================================================
		modulo.gerar();
	}
}
