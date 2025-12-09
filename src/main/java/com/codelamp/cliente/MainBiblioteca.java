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
import com.codelamp.template.dominio.campo.Mestre;
import com.codelamp.template.kanban.Kanban;
import com.codelamp.template.lote.Lote;
import com.codelamp.template.md.MestreDetalhe;

public class MainBiblioteca extends Modulo {

    public MainBiblioteca() {

        setTitulo("Biblioteca");

        MestreDetalhe livroEmprestimos = new MestreDetalhe("Empréstimos", "EmprestimoLivro", Mestre.build("Livro", "Titulo", "Livro"));

        livroEmprestimos.adicionar(new CampoReferencia("Aluno", "Nome", "Aluno", 6));
        livroEmprestimos.adicionar(new CampoDate("DataEmprestimo", "Empréstimo", 6));
        livroEmprestimos.adicionar(new CampoDate("DataPrevistaDevolucao", "Prev. Devolução", 6));
        livroEmprestimos.adicionar(new CampoDate("DataDevolucao", "Devolução", 6));
        livroEmprestimos.adicionar(new CampoEnum("Status", "Status", 6, new String[]{"EM_ANDAMENTO", "DEVOLVIDO", "ATRASADO"}));

        adicionar(livroEmprestimos);

        Entidade livro = new Entidade("Livro", "Livro");

        livro.adicionar(new CampoTexto("Titulo", "Título", 6));
        livro.adicionar(new CampoTexto("Autor", "Autor", 6));
        livro.adicionar(new CampoTexto("Editora", "Editora", 6));
        livro.adicionar(new CampoInteiro("AnoPublicacao", "Ano", 6));
        livro.adicionar(new CampoTexto("Isbn", "ISBN", 6));
        livro.adicionar(new CampoInteiro("QuantidadeTotal", "Qtd Total", 6));

        livro.adicionarAcao(new Acao(livroEmprestimos, "Empréstimos", "titulo"));
        adicionar(livro);

    }
}
