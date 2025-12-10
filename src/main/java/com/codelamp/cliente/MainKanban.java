package com.codelamp.cliente;

import com.codelamp.template.crud.Entidade;
import com.codelamp.template.dominio.Acao;
import com.codelamp.template.dominio.EntidadeReferencia;
import com.codelamp.template.dominio.Modulo;
import com.codelamp.template.dominio.Projeto;
import com.codelamp.template.dominio.campo.CampoEnum;
import com.codelamp.template.dominio.campo.CampoTexto;
import com.codelamp.template.dominio.campo.Mestre;
import com.codelamp.template.kanban.Kanban;
import com.codelamp.template.md.MestreDetalhe;

public class MainKanban {

	public static void main(String[] args) {
		
		Projeto projeto = new Projeto();
		projeto.setTitulo("Kanban");
		
		Modulo modulo = new Modulo();
		modulo.setTitulo("Basico");
		
		projeto.addModulos(modulo);
		
		MestreDetalhe tarefas = new MestreDetalhe("Tarefas", "Tarefas", Mestre.build("Pessoa", "Nome", "Pessoa"));
		
		tarefas.adicionar(new CampoTexto("Titulo", "Titulo", 12));
		tarefas.adicionar(new CampoEnum("Status", "Status", 6, new String[] {"INICIADO", "NAO INICIADO", "CONCLUIDA"}));
		
		modulo.adicionar(tarefas);
		
		Kanban kanbanSolicitacoes = new Kanban("Acompanhamento", "Acompanhamento", Mestre.build("Pessoa", "Nome", "Pessoa"));
        kanbanSolicitacoes.setEntidadeReferencia(new EntidadeReferencia(tarefas, "nome"));
        kanbanSolicitacoes.setCampoStatus((CampoEnum) tarefas.getCampo("Status"));
        
        kanbanSolicitacoes.setAtributos(new String[]{
                "titulo"
        });

        modulo.adicionar(kanbanSolicitacoes);
        
		Entidade pessoa = new Entidade("Pessoa", "Pessoa");
		pessoa.adicionar(new CampoTexto("Nome", "Nome", 12));
		
		pessoa.adicionarAcao(new Acao(tarefas, "Tarefas", "nome"));
		pessoa.adicionarAcaoKanban(new Acao(kanbanSolicitacoes, "Acompanhar", "pessoa.nome"));
		
		modulo.adicionar(pessoa);


		projeto.gerar();
		
	}
	
	
}
