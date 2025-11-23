package com.codelamp.cliente;

import com.codelamp.template.crud.Entidade;
import com.codelamp.template.dominio.Acao;
import com.codelamp.template.dominio.Modulo;
import com.codelamp.template.dominio.campo.*;
import com.codelamp.template.md.MestreDetalhe;
import com.codelamp.template.kanban.Kanban;

public class MainHelpDesk {

    public static void main(String[] args) {

        // ===========================================================
        // === MÓDULO PRINCIPAL
        // ===========================================================
        Modulo modulo = new Modulo();
        modulo.setTitulo("Help Desk");

        // ===========================================================
        // === MESTRE DETALHE: ATUALIZAÇÕES DO TICKET
        // ===========================================================
        MestreDetalhe atualizacoes = new MestreDetalhe();
        atualizacoes.setTitulo("Atualizações do Ticket");
        atualizacoes.setClasse("AtualizacaoTicket");

        // Ticket é o mestre
        atualizacoes.setCampoMestre(
                new CampoReferencia("Ticket", "titulo", "Ticket", 6)
        );

        // Usuário (responsável pela atualização)
        atualizacoes.adicionar(new CampoReferencia("Usuario", "nome", "Responsável", 6));

        // Conteúdo da atualização
        atualizacoes.adicionar(new CampoAreaTexto("descricao", "Descrição da Atualização", 12));

        // Status do ticket (reflete o pipeline)
        atualizacoes.adicionar(new CampoEnum("status", "Status",
                4,
                new String[]{
                        "Novo",
                        "Em Atendimento",
                        "Aguardando Cliente",
                        "Resolvido",
                        "Fechado"
                }
        ));

        // Data
        atualizacoes.adicionar(new CampoDate("dataMovimento", "Data", 4));

        modulo.adicionar(atualizacoes);

        // ===========================================================
        // === KANBAN (corrigido)
        // ===========================================================
        Kanban kanban = new Kanban();
        kanban.setTitulo("Pipeline de Tickets");
        kanban.setClasse("KanbanTickets");

        // O Kanban é baseado no MestreDetalhe Atualização
        kanban.setMestreDetalhe(atualizacoes);

        // Agora usamos atributos REAIS do MestreDetalhe via atributos aninhados
        kanban.setAtributos(new String[]{"usuario.nome", "usuario.email", "descricao", "dataMovimentoStr"});

        modulo.adicionar(kanban);

        // ===========================================================
        // === ENTIDADE: USUÁRIO (Cliente ou Técnico)
        // ===========================================================
        Entidade usuario = new Entidade();
        usuario.setTitulo("Usuário");
        usuario.setClasse("Usuario");

        usuario.adicionar(new CampoTexto("nome", "Nome", 8));
        usuario.adicionar(new CampoEmail("email", "E-mail", 6));
        usuario.adicionar(new CampoEnum("tipo", "Tipo", 4,
                new String[]{"Cliente", "Técnico"}));

        modulo.adicionar(usuario);

        // ===========================================================
        // === ENTIDADE: CATEGORIA
        // ===========================================================
        Entidade categoria = new Entidade();
        categoria.setTitulo("Categoria");
        categoria.setClasse("Categoria");

        categoria.adicionar(new CampoTexto("nome", "Nome da Categoria", 8));

        modulo.adicionar(categoria);

        // ===========================================================
        // === ENTIDADE: TICKET
        // ===========================================================
        Entidade ticket = new Entidade();
        ticket.setTitulo("Ticket");
        ticket.setClasse("Ticket");

        ticket.adicionar(new CampoTexto("titulo", "Título do Ticket", 8));
        ticket.adicionar(new CampoReferencia("Usuario", "nome", "Solicitante", 6));
        ticket.adicionar(new CampoReferencia("Categoria", "nome", "Categoria", 6));

        ticket.adicionar(new CampoEnum("status", "Status", 4,
                new String[]{
                        "Novo",
                        "Em Atendimento",
                        "Aguardando Cliente",
                        "Resolvido",
                        "Fechado"
                }
        ));

        ticket.adicionar(new CampoDate("dataAbertura", "Data de Abertura", 4));

        // Ações
        ticket.adicionarAcao(new Acao(atualizacoes, "Atualizações", "titulo"));
        ticket.adicionarAcao(new Acao(kanban, "Kanban", "titulo"));

        modulo.adicionar(ticket);

        // ===========================================================
        // === GERAR SISTEMA
        // ===========================================================
        modulo.gerar();
    }
}
