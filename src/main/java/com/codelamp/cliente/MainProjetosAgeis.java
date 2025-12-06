package com.codelamp.cliente;

import com.codelamp.template.crud.Entidade;
import com.codelamp.template.dominio.Acao;
import com.codelamp.template.dominio.EntidadeReferencia;
import com.codelamp.template.dominio.Modulo;
import com.codelamp.template.dominio.campo.CampoAreaTexto;
import com.codelamp.template.dominio.campo.CampoDate;
import com.codelamp.template.dominio.campo.CampoDouble;
import com.codelamp.template.dominio.campo.CampoEmail;
import com.codelamp.template.dominio.campo.CampoEnum;
import com.codelamp.template.dominio.campo.CampoInteiro;
import com.codelamp.template.dominio.campo.CampoReferencia;
import com.codelamp.template.dominio.campo.CampoTexto;
import com.codelamp.template.kanban.Kanban;
import com.codelamp.template.lote.Lote;
import com.codelamp.template.md.MestreDetalhe;

public class MainProjetosAgeis {

    public static void main(String[] args) {

        Modulo modulo = new Modulo();
        modulo.setTitulo("Gestão de Projetos Ágeis");

        // ===========================================================
        // ===================== MESTRE-DETALHE =======================
        // ===========================================================

        // PROJETOS DO CLIENTE
        MestreDetalhe clienteProjetos = new MestreDetalhe();
        clienteProjetos.setTitulo("Projetos do Cliente");
        clienteProjetos.setClasse("Projeto");

        clienteProjetos.setCampoMestre(
                new CampoReferencia("Cliente", "Nome", "Cliente", 12)
        );

        clienteProjetos.adicionar(new CampoTexto("Nome", "Nome do Projeto", 8));
        clienteProjetos.adicionar(new CampoTexto("Codigo", "Código", 4));
        clienteProjetos.adicionar(new CampoDate("DataInicio", "Início", 4));
        clienteProjetos.adicionar(new CampoDate("DataFimPrevista", "Fim Previsto", 4));
        clienteProjetos.adicionar(new CampoEnum("Status", "Status", 4,
                new String[]{"PLANEJADO", "EM_ANDAMENTO", "CONCLUIDO", "CANCELADO"}));
        clienteProjetos.adicionar(new CampoAreaTexto("Descricao", "Descrição", 12));

        modulo.adicionar(clienteProjetos);


        // SPRINTS DO PROJETO
        MestreDetalhe projetoSprints = new MestreDetalhe();
        projetoSprints.setTitulo("Sprints do Projeto");
        projetoSprints.setClasse("Sprint");

        projetoSprints.setCampoMestre(
                new CampoReferencia("Projeto", "Nome", "Projeto", 12)
        );

        projetoSprints.adicionar(new CampoTexto("Nome", "Nome da Sprint", 6));
        projetoSprints.adicionar(new CampoInteiro("Numero", "Número", 2));
        projetoSprints.adicionar(new CampoDate("DataInicio", "Início", 4));
        projetoSprints.adicionar(new CampoDate("DataFim", "Fim", 4));
        projetoSprints.adicionar(new CampoEnum("Status", "Status", 4,
                new String[]{"PLANEJADA", "EM_ANDAMENTO", "CONCLUIDA", "CANCELADA"}));
        projetoSprints.adicionar(new CampoAreaTexto("Objetivo", "Objetivo", 12));

        modulo.adicionar(projetoSprints);


        // TAREFAS DA SPRINT
        MestreDetalhe sprintTarefas = new MestreDetalhe();
        sprintTarefas.setTitulo("Tarefas da Sprint");
        sprintTarefas.setClasse("Tarefa");

        sprintTarefas.setCampoMestre(
                new CampoReferencia("Sprint", "Nome", "Sprint", 12)
        );

        sprintTarefas.adicionar(new CampoTexto("Titulo", "Título", 8));
        sprintTarefas.adicionar(new CampoEnum("Tipo", "Tipo", 4,
                new String[]{"HISTORIA", "BUG", "TAREFA_TECNICA"}));
        sprintTarefas.adicionar(new CampoEnum("Prioridade", "Prioridade", 4,
                new String[]{"BAIXA", "MEDIA", "ALTA", "CRITICA"}));
        sprintTarefas.adicionar(new CampoEnum("Status", "Status", 4,
                new String[]{"BACKLOG", "EM_ANDAMENTO", "EM_TESTE", "CONCLUIDA"}));
        sprintTarefas.adicionar(new CampoInteiro("EstimativaPontos", "Pontos", 2));
        sprintTarefas.adicionar(new CampoReferencia("Membro", "Nome", "Responsável", 6));
        sprintTarefas.adicionar(new CampoAreaTexto("Descricao", "Descrição", 12));

        modulo.adicionar(sprintTarefas);


        // MEMBROS DO TIME (associativa TimeMembro)
        MestreDetalhe timeMembros = new MestreDetalhe();
        timeMembros.setTitulo("Membros do Time");
        timeMembros.setClasse("TimeMembro");

        timeMembros.setCampoMestre(
                new CampoReferencia("Time", "Nome", "Time", 12)
        );

        // ✅ Aqui está a correção: tipo "Membro", NÃO "TimeMembro"
        timeMembros.adicionar(
                new CampoReferencia("Membro", "Nome", "Membro", 8)
        );
        timeMembros.adicionar(
                new CampoInteiro("AlocacaoPercentual", "Alocação (%)", 4)
        );

        modulo.adicionar(timeMembros);


        // ===========================================================
        // ======================== LOTE =============================
        // ===========================================================

        Lote tarefaHoras = new Lote();
        tarefaHoras.setTitulo("Apontamento de Horas");
        tarefaHoras.setClasse("ApontamentoTarefa");

        tarefaHoras.setCampoMestre(
                new CampoReferencia("Tarefa", "Titulo", "Tarefa", 12)
        );

        tarefaHoras.setCampoRotulo(
                new CampoReferencia("Membro", "Nome", "Membro", 6)
        );
        tarefaHoras.setCampoValor(
                new CampoDouble("Horas", "Horas", 6)
        );

        modulo.adicionar(tarefaHoras);


        // ===========================================================
        // ======================= KANBAN =============================
        // ===========================================================

        Kanban kanbanTarefas = new Kanban();
        kanbanTarefas.setTitulo("Acompanhamento de Tarefas");
        kanbanTarefas.setClasse("AcompanhamentoTarefa");
        kanbanTarefas.setEntidadeReferencia(new EntidadeReferencia(sprintTarefas, "titulo"));
        kanbanTarefas.setCampoStatus(new CampoEnum("Status", "Status", 4,
                new String[]{"BACKLOG", "EM_ANDAMENTO", "EM_TESTE", "CONCLUIDA"}));

        kanbanTarefas.setAtributos(new String[]{
                "tipo",
                "prioridade",
                "membro.nome",
                "estimativaPontos"
        });

        kanbanTarefas.setMenuVisivel(true);
        modulo.adicionar(kanbanTarefas);


        // ===========================================================
        // ===================== ENTIDADES RAIZ =======================
        // ===========================================================

        // CLIENTE
        Entidade cliente = new Entidade();
        cliente.setTitulo("Cliente");
        cliente.setClasse("Cliente");

        cliente.adicionar(new CampoTexto("Nome", "Nome", 8));
        cliente.adicionar(new CampoTexto("Cnpj", "CNPJ", 4));
        cliente.adicionar(new CampoEmail("Email", "E-mail", 6));
        cliente.adicionar(new CampoTexto("Telefone", "Telefone", 6));

        // Cliente → Projetos
        cliente.adicionarAcao(new Acao(clienteProjetos, "Projetos", "nome"));

        modulo.adicionar(cliente);


        // TIME
        Entidade time = new Entidade();
        time.setTitulo("Time");
        time.setClasse("Time");

        time.adicionar(new CampoTexto("Nome", "Nome do Time", 8));
        time.adicionar(new CampoAreaTexto("Descricao", "Descrição", 12));

        // Time → Membros
        time.adicionarAcao(new Acao(timeMembros, "Membros", "nome"));

        modulo.adicionar(time);


        // MEMBRO
        Entidade membro = new Entidade();
        membro.setTitulo("Membro");
        membro.setClasse("Membro");

        membro.adicionar(new CampoTexto("Nome", "Nome", 8));
        membro.adicionar(new CampoEmail("Email", "E-mail", 6));
        membro.adicionar(new CampoTexto("Cargo", "Cargo", 6));

        modulo.adicionar(membro);


        // ===========================================================
        // ========= AÇÕES DE NAVEGAÇÃO ENTRE MESTRE-DETALHE =========
        // ===========================================================

        // Projeto (MestreDetalhe) → Sprints
        clienteProjetos.adicionarAcao(new Acao(projetoSprints, "Sprints", "nome"));

        // Sprint → Tarefas
        projetoSprints.adicionarAcao(new Acao(sprintTarefas, "Tarefas", "nome"));

        // Tarefa → Horas (lote)
        sprintTarefas.adicionarAcao(new Acao(tarefaHoras, "Horas", "titulo"));


        // ===========================================================
        // ===================== GERAR MÓDULO ========================
        // ===========================================================

        modulo.gerar();
    }
}
