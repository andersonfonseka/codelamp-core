package com.codelamp.cliente;

import com.codelamp.template.crud.Entidade;
import com.codelamp.template.dominio.Acao;
import com.codelamp.template.dominio.Modulo;
import com.codelamp.template.dominio.campo.CampoArquivo;
import com.codelamp.template.dominio.campo.CampoDate;
import com.codelamp.template.dominio.campo.CampoDouble;
import com.codelamp.template.dominio.campo.CampoEmail;
import com.codelamp.template.dominio.campo.CampoInteiro;
import com.codelamp.template.dominio.campo.CampoReferencia;
import com.codelamp.template.dominio.campo.CampoTexto;
import com.codelamp.template.md.MestreDetalhe;

public class MainAlocacao {

    public static void main(String[] args) {

        // ===========================================================
        // === MÓDULO PRINCIPAL
        // ===========================================================
        Modulo modulo = new Modulo();
        modulo.setTitulo("Alocação de Profissionais");

        // ===========================================================
        // === MESTRES DETALHE (sempre primeiro)
        // ===========================================================

        // -----------------------------------------------------------
        // (1) CÉLULA → COLABORADORES
        // -----------------------------------------------------------
        MestreDetalhe celulaColaboradores = new MestreDetalhe();
        celulaColaboradores.setTitulo("Colaboradores da Célula");
        celulaColaboradores.setClasse("CelulaColaborador");

        celulaColaboradores.setCampoMestre(
                new CampoReferencia("Celula", "nome", "Célula", 6)
        );

        celulaColaboradores.adicionar(
                new CampoReferencia("Colaborador", "nome", "Colaborador", 6)
        );

        modulo.adicionar(celulaColaboradores);

        // -----------------------------------------------------------
        // (2) CÉLULA → CLIENTES
        // -----------------------------------------------------------
        MestreDetalhe celulaClientes = new MestreDetalhe();
        celulaClientes.setTitulo("Clientes da Célula");
        celulaClientes.setClasse("CelulaCliente");

        celulaClientes.setCampoMestre(
                new CampoReferencia("Celula", "nome", "Célula", 6)
        );

        celulaClientes.adicionar(
                new CampoReferencia("Cliente", "nome", "Cliente", 6)
        );

        modulo.adicionar(celulaClientes);

        // -----------------------------------------------------------
        // (3) PROJETO → ALOCAÇÃO DE PROFISSIONAIS
        // -----------------------------------------------------------
        MestreDetalhe alocacaoProjeto = new MestreDetalhe();
        alocacaoProjeto.setTitulo("Alocação no Projeto");
        alocacaoProjeto.setClasse("Alocacao");

        alocacaoProjeto.setCampoMestre(
                new CampoReferencia("Projeto", "nome", "Projeto", 6)
        );

        alocacaoProjeto.adicionar(
                new CampoReferencia("Colaborador", "nome", "Colaborador", 6)
        );

        alocacaoProjeto.adicionar(
                new CampoDate("dataInicio", "Início da Alocação", 4)
        );

        alocacaoProjeto.adicionar(
                new CampoDate("dataFim", "Término da Alocação", 4)
        );

        alocacaoProjeto.adicionar(
                new CampoDouble("percentual", "Percentual Alocado (%)", 4)
        );

        modulo.adicionar(alocacaoProjeto);

        // ===========================================================
        // === ENTIDADES
        // ===========================================================

        // -----------------------------------------------------------
        // CÉLULA
        // -----------------------------------------------------------
        Entidade celula = new Entidade();
        celula.setTitulo("Célula");
        celula.setClasse("Celula");

        celula.adicionar(new CampoTexto("nome", "Nome da Célula", 8));

        celula.adicionarAcao(new Acao(celulaColaboradores, "Colaboradores", "nome"));
        celula.adicionarAcao(new Acao(celulaClientes, "Clientes", "nome"));

        modulo.adicionar(celula);

        // -----------------------------------------------------------
        // ÁREA
        // -----------------------------------------------------------
        Entidade area = new Entidade();
        area.setTitulo("Área");
        area.setClasse("Area");

        area.adicionar(new CampoTexto("nome", "Nome da Área", 8));

        modulo.adicionar(area);

        // -----------------------------------------------------------
        // CARGO
        // -----------------------------------------------------------
        Entidade cargo = new Entidade();
        cargo.setTitulo("Cargo");
        cargo.setClasse("Cargo");

        cargo.adicionar(new CampoTexto("titulo", "Título do Cargo", 8));

        // relação: cargo pertence a uma área
        cargo.adicionar(new CampoReferencia("Area", "nome", "Área", 6));

        modulo.adicionar(cargo);

        // -----------------------------------------------------------
        // COLABORADOR
        // -----------------------------------------------------------
        Entidade colaborador = new Entidade();
        colaborador.setTitulo("Colaborador");
        colaborador.setClasse("Colaborador");

        colaborador.adicionar(new CampoTexto("nome", "Nome", 8));
        colaborador.adicionar(new CampoReferencia("Cargo", "titulo", "Cargo", 6));
        colaborador.adicionar(new CampoEmail("email", "E-mail", 6));
        colaborador.adicionar(new CampoInteiro("matricula", "Matrícula", 4));
        colaborador.adicionar(new CampoArquivo("foto", "Foto", 6));

        modulo.adicionar(colaborador);

        // -----------------------------------------------------------
        // CLIENTE
        // -----------------------------------------------------------
        Entidade cliente = new Entidade();
        cliente.setTitulo("Cliente");
        cliente.setClasse("Cliente");

        cliente.adicionar(new CampoTexto("nome", "Nome do Cliente", 8));

        modulo.adicionar(cliente);

        // -----------------------------------------------------------
        // PROJETO
        // -----------------------------------------------------------
        Entidade projeto = new Entidade();
        projeto.setTitulo("Projeto");
        projeto.setClasse("Projeto");

        projeto.adicionar(new CampoTexto("nome", "Nome do Projeto", 8));
        projeto.adicionar(new CampoReferencia("Cliente", "nome", "Cliente", 6));
        projeto.adicionar(new CampoDate("dataInicio", "Data de Início", 4));
        projeto.adicionar(new CampoDate("dataFim", "Data de Término", 4));

        projeto.adicionarAcao(new Acao(alocacaoProjeto, "Alocações", "nome"));

        modulo.adicionar(projeto);

        // ===========================================================
        // === GERAR
        // ===========================================================
        modulo.gerar();
    }
}
