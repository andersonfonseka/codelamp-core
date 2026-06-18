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
		projeto.setTitulo("Gestor de Atividades");
		
		Modulo modulo = new Modulo();
		modulo.setTitulo("Basico");
		
		projeto.addModulos(modulo);
		
		MestreDetalhe tarefas = new MestreDetalhe("Tarefas", "Tarefas", Mestre.build("Usuario", "Nome", "Usuario"));
		
		tarefas.adicionar(new CampoTexto("Titulo", "Titulo", 12));
		tarefas.adicionar(new CampoEnum("Status", "Status", 6, new String[] {"NAO INICIADO", "INICIADO", "CONCLUIDA"}));
		
		modulo.adicionar(tarefas);
		
		Kanban kanbanSolicitacoes = new Kanban("Acompanhamento", "Acompanhamento", Mestre.build("Usuario", "Nome", "Usuario"));
        kanbanSolicitacoes.setEntidadeReferencia(new EntidadeReferencia(tarefas, "Nome"));
        kanbanSolicitacoes.setCampoStatus((CampoEnum) tarefas.getCampo("Status"));
        
        kanbanSolicitacoes.setAtributos(new String[]{
                "Titulo"
        });

        modulo.adicionar(kanbanSolicitacoes);
        
		Entidade pessoa = new Entidade("Usuario", "Usuario");
		pessoa.adicionar(new CampoTexto("Nome", "Nome", 12));
		
		pessoa.adicionarAcao(new Acao(tarefas, "Tarefas", "Nome"));
		pessoa.adicionarAcaoKanban(new Acao(kanbanSolicitacoes, "Acompanhar", "Usuario.Nome"));
		
		modulo.adicionar(pessoa);

		projeto.gerar();
		
	}
	
	
}
