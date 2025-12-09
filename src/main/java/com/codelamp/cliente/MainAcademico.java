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

public class MainAcademico {

    public static void main(String[] args) {

        Modulo modulo = new Modulo();
        modulo.setTitulo("Gestão Acadêmica");

        MestreDetalhe alunoSolicitacoes = new MestreDetalhe();
        alunoSolicitacoes.setTitulo("Solicitações");
        alunoSolicitacoes.setClasse("SolicitacaoSecretaria");

        alunoSolicitacoes.setCampoMestre(new CampoReferencia("Aluno", "Nome", "Aluno", 12)
        );

        alunoSolicitacoes.adicionar(new CampoEnum("Tipo", "Tipo", 6,  new String[]{"HISTORICO", "DECLARACAO", "BOLETIM", "ATESTADO", "OUTROS"}));
        alunoSolicitacoes.adicionar(new CampoDate("DataAbertura", "Abertura", 6));
        alunoSolicitacoes.adicionar(new CampoEnum("Status", "Status", 6, new String[]{"ABERTA", "EM_ANALISE", "CONCLUIDA", "CANCELADA"}));
        alunoSolicitacoes.adicionar(new CampoAreaTexto("Observacoes", "Observações", 6));

        modulo.adicionar(alunoSolicitacoes);


        MestreDetalhe livroEmprestimos = new MestreDetalhe();
        livroEmprestimos.setTitulo("Empréstimos");
        livroEmprestimos.setClasse("EmprestimoLivro");

        livroEmprestimos.setCampoMestre(
                new CampoReferencia("Livro", "Titulo", "Livro", 6)
        );

        livroEmprestimos.adicionar(new CampoReferencia("Aluno", "Nome", "Aluno", 6));
        livroEmprestimos.adicionar(new CampoDate("DataEmprestimo", "Empréstimo", 6));
        livroEmprestimos.adicionar(new CampoDate("DataPrevistaDevolucao", "Prev. Devolução", 6));
        livroEmprestimos.adicionar(new CampoDate("DataDevolucao", "Devolução", 6));
        livroEmprestimos.adicionar(new CampoEnum("Status", "Status", 6, new String[]{"EM_ANDAMENTO", "DEVOLVIDO", "ATRASADO"}));

        modulo.adicionar(livroEmprestimos);


        MestreDetalhe cursoDisciplinas = new MestreDetalhe();
        cursoDisciplinas.setTitulo("Matriz Curricular");
        cursoDisciplinas.setClasse("CursoDisciplina");

        cursoDisciplinas.setCampoMestre(new CampoReferencia("Curso", "Nome", "Curso", 12)
        );

        cursoDisciplinas.adicionar(new CampoReferencia("Disciplina", "Nome", "Disciplina", 6));
        cursoDisciplinas.adicionar(new CampoInteiro("Periodo", "Período/Série", 6));

        modulo.adicionar(cursoDisciplinas);


        MestreDetalhe disciplinaConteudo = new MestreDetalhe();
        disciplinaConteudo.setTitulo("Conteúdo Programático");
        disciplinaConteudo.setClasse("ConteudoProgramatico");

        disciplinaConteudo.setCampoMestre(new CampoReferencia("Disciplina", "Nome", "Disciplina", 6));

        disciplinaConteudo.adicionar(new CampoTexto("Titulo", "Título", 6));
        disciplinaConteudo.adicionar(new CampoAreaTexto("Descricao", "Descrição", 12));
        disciplinaConteudo.adicionar(new CampoInteiro("CargaHoraria", "Carga Horária (h)", 6));

        modulo.adicionar(disciplinaConteudo);


        MestreDetalhe turmaAlunos = new MestreDetalhe();
        turmaAlunos.setTitulo("Alunos da Turma");
        turmaAlunos.setClasse("TurmaAluno");

        turmaAlunos.setCampoMestre(new CampoReferencia("Turma", "Nome", "Turma", 12));

        turmaAlunos.adicionar(new CampoReferencia("Aluno", "Nome", "Aluno", 6));
        turmaAlunos.adicionar(new CampoEnum("Situacao", "Situação", 6, new String[]{"MATRICULADO", "TRANCADO", "CANCELADO"}));

        modulo.adicionar(turmaAlunos);
        

        Lote aulaFrequencias = new Lote();
        aulaFrequencias.setTitulo("Frequência dos Alunos");
        aulaFrequencias.setClasse("AulaFrequencia");

        aulaFrequencias.setCampoMestre(new CampoReferencia("TurmaAulaMinistrada", "Descricao", "Aula", 6));

        aulaFrequencias.setCampoRotulo(new CampoReferencia("TurmaAluno", "aluno.nome", "Aluno", 6));
        aulaFrequencias.setCampoValor(new CampoEnum("Presenca", "Presença", 6, new String[]{"PRESENTE", "AUSENTE", "JUSTIFICADA"}));
        
        modulo.adicionar(aulaFrequencias);


        MestreDetalhe turmaAulas = new MestreDetalhe();
        turmaAulas.setTitulo("Aulas Ministradas");
        turmaAulas.setClasse("TurmaAulaMinistrada");

        turmaAulas.setCampoMestre(
                new CampoReferencia("TurmaDisciplina", "disciplina.nome", "Disciplina", 6)
        );

        turmaAulas.adicionar(new CampoReferencia("ConteudoProgramatico", "Titulo", "Conteúdo", 6));
        turmaAulas.adicionar(new CampoDate("Data", "Data da Aula", 6));
        turmaAulas.adicionar(new CampoAreaTexto("Descricao", "Descrição da Aula", 6));
        
        turmaAulas.adicionarAcao(new Acao(aulaFrequencias, "Frequencia", "descricao"));

        modulo.adicionar(turmaAulas);


        Lote trabalhoNotas = new Lote();
        trabalhoNotas.setTitulo("Notas do Trabalho");
        trabalhoNotas.setClasse("TrabalhoNota");

        trabalhoNotas.setCampoMestre(
                new CampoReferencia("TurmaTrabalho", "Descricao", "Trabalho", 12)
        );
        
        trabalhoNotas.setCampoRotulo(new CampoReferencia("TurmaAluno", "aluno.nome", "Aluno da Turma", 6));
        trabalhoNotas.setCampoValor(new CampoDouble("Nota", "Nota", 6));

        modulo.adicionar(trabalhoNotas);
        

        MestreDetalhe turmaTrabalhos = new MestreDetalhe();
        turmaTrabalhos.setTitulo("Trabalhos da Turma");
        turmaTrabalhos.setClasse("TurmaTrabalho");

        turmaTrabalhos.setCampoMestre(new CampoReferencia("TurmaDisciplina", "disciplina.nome", "Disciplina", 6));

        turmaTrabalhos.adicionar(new CampoTexto("Titulo", "Título", 6));
        turmaTrabalhos.adicionar(new CampoDate("DataEntrega", "Entrega", 6));
        turmaTrabalhos.adicionar(new CampoAreaTexto("Descricao", "Descrição", 6));
        turmaTrabalhos.adicionar(new CampoEnum("Status", "Status", 6, new String[]{"ABERTO", "ENTREGUE", "ATRASADO"}));
        
        turmaTrabalhos.adicionarAcao(new Acao(trabalhoNotas, "Notas", "descricao"));

        modulo.adicionar(turmaTrabalhos);


        MestreDetalhe turmaDisciplinas = new MestreDetalhe();
        turmaDisciplinas.setTitulo("Disciplinas da Turma");
        turmaDisciplinas.setClasse("TurmaDisciplina");

        turmaDisciplinas.setCampoMestre(
                new CampoReferencia("Turma", "Nome", "Turma", 12)
        );

        turmaDisciplinas.adicionar(new CampoReferencia("Disciplina", "Nome", "Disciplina", 6));
        turmaDisciplinas.adicionar(new CampoReferencia("Professor", "Nome", "Professor", 6));
        turmaDisciplinas.adicionar(new CampoInteiro("CargaHoraria", "Carga Horária", 6));
        turmaDisciplinas.adicionar(new CampoTexto("Observacoes", "Observações", 6));

        turmaDisciplinas.adicionarAcao(new Acao(turmaAulas, "Aulas Ministradas", "disciplina.nome"));
        turmaDisciplinas.adicionarAcao(new Acao(turmaTrabalhos, "Avaliações / Tarefas", "disciplina.nome"));
        
        modulo.adicionar(turmaDisciplinas);
               

        // TURMA
        MestreDetalhe turma = new MestreDetalhe();
        turma.setTitulo("Turma");
        turma.setClasse("Turma");

        turma.setCampoMestre(new CampoReferencia("Curso", "Nome", "Curso", 12));
        
        turma.adicionar(new CampoTexto("Nome", "Nome da Turma", 6));
        turma.adicionar(new CampoTexto("Codigo", "Código", 6));
        turma.adicionar(new CampoInteiro("Ano", "Ano", 2));
        turma.adicionar(new CampoInteiro("Semestre", "Semestre", 2));
        turma.adicionar(new CampoEnum("Turno", "Turno", 4, new String[]{"MANHA", "TARDE", "NOITE", "INTEGRAL"}));
        turma.adicionar(new CampoInteiro("Capacidade", "Capacidade", 4));

        turma.adicionarAcao(new Acao(turmaAlunos, "Alunos", "nome"));
        turma.adicionarAcao(new Acao(turmaDisciplinas, "Disciplinas", "nome"));

        modulo.adicionar(turma);
        

        Kanban kanbanSolicitacoes = new Kanban();
        kanbanSolicitacoes.setTitulo("Acompanhamento de Solicitações");
        kanbanSolicitacoes.setClasse("AcompanhamentoSolicitacaoSecretaria");
        kanbanSolicitacoes.setEntidadeReferencia(new EntidadeReferencia(alunoSolicitacoes, "aluno.nome"));
        kanbanSolicitacoes.setCampoStatus(new CampoEnum("Status", "Status", 4, new String[]{"ABERTA", "EM_ANALISE", "CONCLUIDA", "CANCELADA"}));

        kanbanSolicitacoes.setAtributos(new String[]{
                "aluno.nome",
                "tipo",
                "status",
                "dataAbertura"
        });

        kanbanSolicitacoes.setMenuVisivel(true);
        modulo.adicionar(kanbanSolicitacoes);



        Entidade curso = new Entidade();
        curso.setTitulo("Curso");
        curso.setClasse("Curso");

        curso.adicionar(new CampoTexto("Nome", "Nome", 8));
        curso.adicionar(new CampoTexto("Codigo", "Código", 4));
        curso.adicionar(new CampoEnum("Nivel", "Nível", 4, new String[]{"FUNDAMENTAL", "MEDIO", "TECNICO", "GRADUACAO", "POS"}));
        curso.adicionar(new CampoInteiro("CargaHoraria", "Carga Horária", 4));
        curso.adicionar(new CampoEnum("Status", "Status", 4, new String[]{"ATIVO", "INATIVO"}));

        curso.adicionarAcao(new Acao(cursoDisciplinas, "Matriz Curricular", "nome"));
        curso.adicionarAcao(new Acao(turma, "Turmas", "nome"));
        
        modulo.adicionar(curso);


        Entidade disciplina = new Entidade();
        disciplina.setTitulo("Disciplina");
        disciplina.setClasse("Disciplina");

        disciplina.adicionar(new CampoTexto("Nome", "Nome", 8));
        disciplina.adicionar(new CampoTexto("Codigo", "Código", 4));
        disciplina.adicionar(new CampoInteiro("CargaHoraria", "Carga Horária (h)", 4));
        disciplina.adicionar(new CampoEnum("Tipo", "Tipo", 4, new String[]{"OBRIGATORIA", "OPTATIVA"}));

        disciplina.adicionarAcao(new Acao(disciplinaConteudo, "Conteúdo", "nome"));
        modulo.adicionar(disciplina);


        Entidade professor = new Entidade();
        professor.setTitulo("Professor");
        professor.setClasse("Professor");

        professor.adicionar(new CampoTexto("Nome", "Nome", 8));
        professor.adicionar(new CampoTexto("Cpf", "CPF", 4));
        professor.adicionar(new CampoEmail("Email", "E-mail", 6));
        professor.adicionar(new CampoTexto("Telefone", "Telefone", 4));
        professor.adicionar(new CampoEnum("Titulacao", "Titulação", 6, new String[]{"GRADUACAO", "ESP", "MESTRADO", "DOUTORADO"}));
        professor.adicionar(new CampoDate("DataAdmissao", "Admissão", 4));

        modulo.adicionar(professor);


        Entidade aluno = new Entidade();
        aluno.setTitulo("Aluno");
        aluno.setClasse("Aluno");

        aluno.adicionar(new CampoTexto("Nome", "Nome", 8));
        aluno.adicionar(new CampoTexto("Cpf", "CPF", 4));
        aluno.adicionar(new CampoDate("DataNascimento", "Nascimento", 4));
        aluno.adicionar(new CampoEmail("Email", "E-mail", 6));
        aluno.adicionar(new CampoTexto("Telefone", "Telefone", 4));
        aluno.adicionar(new CampoTexto("NomeResponsavel", "Responsável", 8));
        aluno.adicionar(new CampoTexto("DocumentoResponsavel", "Doc. Responsável", 4));
        aluno.adicionar(new CampoEnum("Status", "Status", 4, new String[]{"ATIVO", "INATIVO", "EGRESSO"}));

        aluno.adicionarAcao(new Acao(alunoSolicitacoes, "Solicitações", "nome"));
        modulo.adicionar(aluno);


        Entidade livro = new Entidade();
        livro.setTitulo("Livro");
        livro.setClasse("Livro");

        livro.adicionar(new CampoTexto("Titulo", "Título", 8));
        livro.adicionar(new CampoTexto("Autor", "Autor", 6));
        livro.adicionar(new CampoTexto("Editora", "Editora", 6));
        livro.adicionar(new CampoInteiro("AnoPublicacao", "Ano", 4));
        livro.adicionar(new CampoTexto("Isbn", "ISBN", 4));
        livro.adicionar(new CampoInteiro("QuantidadeTotal", "Qtd Total", 4));
        livro.adicionar(new CampoInteiro("QuantidadeDisponivel", "Disponíveis", 4));

        livro.adicionarAcao(new Acao(livroEmprestimos, "Empréstimos", "titulo"));
        modulo.adicionar(livro);


        Entidade usuario = new Entidade();
        usuario.setTitulo("Usuário");
        usuario.setClasse("Usuario");

        usuario.adicionar(new CampoTexto("Login", "Login", 4));
        usuario.adicionar(new CampoTexto("Nome", "Nome", 8));
        usuario.adicionar(new CampoEmail("Email", "E-mail", 6));
        usuario.adicionar(new CampoEnum("Perfil", "Perfil", 4, new String[]{"ALUNO", "PROFESSOR", "COORDENADOR", "SECRETARIA", "ADMIN"}));

        modulo.adicionar(usuario);

        modulo.gerar();
    }
}
