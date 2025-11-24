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

public class MainProjetos {

	 public static void main(String[] args) {

	        // ===========================================================
	        // === MÓDULO PRINCIPAL
	        // ===========================================================
	        Modulo modulo = new Modulo();
	        modulo.setTitulo("Gestão de Projetos - v2");

	        // ===========================================================
	        // === MESTRES DETALHE (sempre primeiro)
	        // ===========================================================

	        // -----------------------------------------------------------
	        // (1) PROJETO → TAREFAS
	        // -----------------------------------------------------------
	        MestreDetalhe projetoTarefas = new MestreDetalhe();
	        projetoTarefas.setTitulo("Tarefas do Projeto");
	        projetoTarefas.setClasse("ProjetoTarefa");

	        projetoTarefas.setCampoMestre(
	                new CampoReferencia("Projeto", "nome", "Projeto", 6)
	        );

	        projetoTarefas.adicionar(new CampoTexto("titulo", "Título da Tarefa", 8));
	        projetoTarefas.adicionar(new CampoTexto("descricao", "Descrição", 12));
	        projetoTarefas.adicionar(new CampoReferencia("Recurso", "nome", "Responsável", 6));

	        projetoTarefas.adicionar(
	                new CampoEnum("prioridade", "Prioridade", 4,
	                        new String[]{"BAIXA", "MEDIA", "ALTA", "CRITICA"})
	        );

	        projetoTarefas.adicionar(
	                new CampoEnum("status", "Status", 4,
	                        new String[]{"BACKLOG", "EM_ANDAMENTO", "EM_REVISAO", "CONCLUIDA"})
	        );

	        projetoTarefas.adicionar(new CampoDate("dataInicio", "Data de Início", 4));
	        projetoTarefas.adicionar(new CampoDate("dataFim", "Data de Término", 4));
	        projetoTarefas.adicionar(new CampoInteiro("esforcoHoras", "Esforço (h)", 3));

	        modulo.adicionar(projetoTarefas);

	        // -----------------------------------------------------------
	        // (2) PROJETO → EQUIPE
	        // -----------------------------------------------------------
	        MestreDetalhe projetoEquipe = new MestreDetalhe();
	        projetoEquipe.setTitulo("Equipe do Projeto");
	        projetoEquipe.setClasse("ProjetoEquipe");

	        projetoEquipe.setCampoMestre(
	                new CampoReferencia("Projeto", "nome", "Projeto", 6)
	        );

	        projetoEquipe.adicionar(
	                new CampoReferencia("Recurso", "nome", "Membro da Equipe", 8)
	        );

	        projetoEquipe.adicionar(
	                new CampoTexto("papel", "Papel no Projeto", 8)
	        );

	        modulo.adicionar(projetoEquipe);

	        // -----------------------------------------------------------
	        // (3) PROJETO → SPRINTS
	        // -----------------------------------------------------------
	        MestreDetalhe projetoSprints = new MestreDetalhe();
	        projetoSprints.setTitulo("Sprints do Projeto");
	        projetoSprints.setClasse("ProjetoSprint");

	        projetoSprints.setCampoMestre(
	                new CampoReferencia("Projeto", "nome", "Projeto", 6)
	        );

	        projetoSprints.adicionar(new CampoTexto("nome", "Nome da Sprint", 8));
	        projetoSprints.adicionar(new CampoDate("dataInicio", "Data de Início", 4));
	        projetoSprints.adicionar(new CampoDate("dataFim", "Data de Término", 4));
	        projetoSprints.adicionar(new CampoInteiro("capacidadeHoras", "Capacidade (h)", 4));

	        projetoSprints.adicionar(
	                new CampoEnum("status", "Status", 4,
	                        new String[]{"PLANEJADA", "EM_ANDAMENTO", "CONCLUIDA"})
	        );

	        modulo.adicionar(projetoSprints);

	        // -----------------------------------------------------------
	        // (4) PROJETO → ROADMAP (MARCOS)
	        // -----------------------------------------------------------
	        MestreDetalhe projetoRoadmap = new MestreDetalhe();
	        projetoRoadmap.setTitulo("Roadmap / Marcos do Projeto");
	        projetoRoadmap.setClasse("ProjetoMarco");

	        projetoRoadmap.setCampoMestre(
	                new CampoReferencia("Projeto", "nome", "Projeto", 6)
	        );

	        projetoRoadmap.adicionar(new CampoTexto("titulo", "Título do Marco", 8));
	        projetoRoadmap.adicionar(new CampoTexto("descricao", "Descrição", 12));
	        projetoRoadmap.adicionar(new CampoDate("dataMarco", "Data do Marco", 4));
	        projetoRoadmap.adicionar(
	                new CampoEnum("status", "Status", 4,
	                        new String[]{"PLANEJADO", "EM_ANDAMENTO", "CONCLUIDO"})
	        );

	        modulo.adicionar(projetoRoadmap);

	        // -----------------------------------------------------------
	        // (5) PROJETO → INDICADORES (BURNUP / BURNDOWN)
	        // -----------------------------------------------------------
	        MestreDetalhe projetoIndicadores = new MestreDetalhe();
	        projetoIndicadores.setTitulo("Indicadores Burnup/Burndown");
	        projetoIndicadores.setClasse("ProjetoIndicador");

	        projetoIndicadores.setCampoMestre(
	                new CampoReferencia("Projeto", "nome", "Projeto", 6)
	        );

	        projetoIndicadores.adicionar(new CampoDate("data", "Data", 4));
	        projetoIndicadores.adicionar(new CampoInteiro("escopoHoras", "Escopo Total (h)", 4));
	        projetoIndicadores.adicionar(new CampoInteiro("horasPlanejadas", "Horas Planejadas (acumuladas)", 4));
	        projetoIndicadores.adicionar(new CampoInteiro("horasTrabalhadas", "Horas Trabalhadas (acumuladas)", 4));

	        modulo.adicionar(projetoIndicadores);

	        // -----------------------------------------------------------
	        // (6) RELEASE → PROJETOS
	        // -----------------------------------------------------------
	        MestreDetalhe releaseProjetos = new MestreDetalhe();
	        releaseProjetos.setTitulo("Projetos da Release");
	        releaseProjetos.setClasse("ReleaseProjeto");

	        releaseProjetos.setCampoMestre(
	                new CampoReferencia("Release", "nome", "Release", 6)
	        );

	        releaseProjetos.adicionar(
	                new CampoReferencia("Projeto", "nome", "Projeto", 8)
	        );

	        modulo.adicionar(releaseProjetos);

	        // ===========================================================
	        // === KANBAN 1 – BASEADO EM MESTREDETALHE (TAREFAS)
	        // ===========================================================
	        Kanban kanbanTarefas = new Kanban();
	        kanbanTarefas.setTitulo("Kanban de Tarefas");
	        kanbanTarefas.setClasse("KanbanTarefas");

	        kanbanTarefas.setMestreDetalhe(projetoTarefas);

	        // Todos estes atributos existem:
	        // projeto.nome    → CampoMestre (Projeto, "nome")
	        // titulo          → CampoTexto("titulo", ...)
	        // recurso.nome    → CampoReferencia("Recurso","nome",...)
	        // prioridade      → CampoEnum("prioridade", ...)
	        // status          → CampoEnum("status", ...)
	        // esforcoHoras    → CampoInteiro("esforcoHoras", ...)
	        kanbanTarefas.setAtributos(new String[]{
	                "projeto.nome",
	                "titulo",
	                "recurso.nome",
	                "prioridade",
	                "status",
	                "esforcoHoras"
	        });

	        modulo.adicionar(kanbanTarefas);

	        // ===========================================================
	        // === ENTIDADES
	        // ===========================================================

	        // -----------------------------------------------------------
	        // CLIENTE
	        // -----------------------------------------------------------
	        Entidade cliente = new Entidade();
	        cliente.setTitulo("Cliente");
	        cliente.setClasse("Cliente");

	        cliente.adicionar(new CampoTexto("nome", "Nome do Cliente", 8));
	        cliente.adicionar(new CampoTexto("segmento", "Segmento", 6));

	        modulo.adicionar(cliente);

	        // -----------------------------------------------------------
	        // RECURSO (pessoa / membro de equipe)
	        // -----------------------------------------------------------
	        Entidade recurso = new Entidade();
	        recurso.setTitulo("Recurso");
	        recurso.setClasse("Recurso");

	        recurso.adicionar(new CampoTexto("nome", "Nome", 8));
	        recurso.adicionar(new CampoEmail("email", "E-mail", 6));
	        recurso.adicionar(new CampoTexto("funcao", "Função", 6));

	        modulo.adicionar(recurso);

	        // -----------------------------------------------------------
	        // RELEASE
	        // -----------------------------------------------------------
	        Entidade release = new Entidade();
	        release.setTitulo("Release");
	        release.setClasse("Release");

	        release.adicionar(new CampoTexto("nome", "Nome da Release", 8));
	        release.adicionar(new CampoDate("dataInicio", "Data de Início", 4));
	        release.adicionar(new CampoDate("dataFim", "Data de Término", 4));
	        release.adicionar(new CampoTexto("objetivo", "Objetivo", 12));

	        release.adicionarAcao(new Acao(releaseProjetos, "Projetos", "nome"));

	        modulo.adicionar(release);

	        // -----------------------------------------------------------
	        // PROJETO
	        // -----------------------------------------------------------
	        Entidade projeto = new Entidade();
	        projeto.setTitulo("Projeto");
	        projeto.setClasse("Projeto");

	        projeto.adicionar(new CampoTexto("nome", "Nome do Projeto", 8));

	        projeto.adicionar(new CampoReferencia("Cliente", "nome", "Cliente", 6));
	        projeto.adicionar(new CampoReferencia("Recurso", "nome", "Gerente do Projeto", 6));
	        projeto.adicionar(new CampoReferencia("Release", "nome", "Release", 6));

	        projeto.adicionar(new CampoDate("dataInicio", "Data de Início", 4));
	        projeto.adicionar(new CampoDate("dataFim", "Data de Término", 4));
	        projeto.adicionar(new CampoDouble("orcamento", "Orçamento (R$)", 4));

	        projeto.adicionar(
	                new CampoEnum("status", "Status", 6,
	                        new String[]{"PLANEJADO", "EM_EXECUCAO", "EM_ESPERA", "CONCLUIDO", "CANCELADO"})
	        );

	        projeto.adicionarAcao(new Acao(projetoTarefas, "Tarefas", "nome"));
	        projeto.adicionarAcao(new Acao(projetoEquipe, "Equipe", "nome"));
	        projeto.adicionarAcao(new Acao(projetoSprints, "Sprints", "nome"));
	        projeto.adicionarAcao(new Acao(projetoRoadmap, "Roadmap", "nome"));
	        projeto.adicionarAcao(new Acao(projetoIndicadores, "Indicadores", "nome"));
	        projeto.adicionarAcao(new Acao(kanbanTarefas, "Kanban de Tarefas", "nome"));

	        modulo.adicionar(projeto);

	        // ===========================================================
	        // === KANBAN 2 – BASEADO EM ENTIDADE (PORTFÓLIO DE PROJETOS)
	        // ===========================================================
	        Kanban kanbanProjetos = new Kanban();
	        kanbanProjetos.setTitulo("Portfólio de Projetos");
	        kanbanProjetos.setClasse("KanbanProjetos");

	        kanbanProjetos.setEntidadeReferencia(
	                new EntidadeReferencia(projeto, "nome")
	        );

	        kanbanProjetos.setCampoReferencia(
	                new CampoReferencia("Projeto", "nome", "Projeto", 12)
	        );

	        kanbanProjetos.setCampoStatus(
	                new CampoEnum("status", "Status", 6,
	                        new String[]{"PLANEJADO", "EM_EXECUCAO", "EM_ESPERA", "CONCLUIDO", "CANCELADO"})
	        );

	        // Atributos conferidos:
	        // nome           → Projeto.nome
	        // cliente.nome   → Projeto.cliente + Cliente.nome
	        // recurso.nome   → Projeto.recurso + Recurso.nome
	        // release.nome   → Projeto.release + Release.nome
	        // orcamento      → Projeto.orcamento
	        kanbanProjetos.setAtributos(new String[]{
	                "nome",
	                "cliente.nome",
	                "recurso.nome",
	                "release.nome",
	                "orcamento"
	        });

	        kanbanProjetos.setMenuVisivel(true);

	        modulo.adicionar(kanbanProjetos);

	        // ===========================================================
	        // === GERAR
	        // ===========================================================
	        modulo.gerar();
	    }
}
