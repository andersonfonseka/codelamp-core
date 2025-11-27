package com.codelamp.cliente;

import com.codelamp.template.crud.Entidade;
import com.codelamp.template.dominio.Acao;
import com.codelamp.template.dominio.EntidadeReferencia;
import com.codelamp.template.dominio.Modulo;
import com.codelamp.template.dominio.campo.*;
import com.codelamp.template.kanban.Kanban;
import com.codelamp.template.md.MestreDetalhe;

public class MainAcademico {

    public static void main(String[] args) {

        Modulo modulo = new Modulo();
        modulo.setTitulo("Gestão Acadêmica");
        
        // ===========================================================
        // MESTRE-DETALHE
        // ===========================================================

        // -----------------------------------------------------------
        // ALUNO → SOLICITAÇÕES
        // -----------------------------------------------------------
        MestreDetalhe alunoSolicitacoes = new MestreDetalhe();
        alunoSolicitacoes.setTitulo("Solicitações do Aluno");
        alunoSolicitacoes.setClasse("SolicitacaoSecretaria");

        alunoSolicitacoes.setCampoMestre(
                new CampoReferencia("Aluno", "nome", "Aluno", 8)
        );

        alunoSolicitacoes.adicionar(new CampoEnum("tipo", "Tipo", 6,
                new String[]{"HISTORICO", "DECLARACAO", "BOLETIM", "ATESTADO", "OUTROS"}));
        alunoSolicitacoes.adicionar(new CampoDate("dataAbertura", "Abertura", 4));
        alunoSolicitacoes.adicionar(new CampoEnum("status", "Status", 4,
                new String[]{"ABERTA", "EM_ANALISE", "CONCLUIDA", "CANCELADA"}));
        alunoSolicitacoes.adicionar(new CampoAreaTexto("observacoes", "Observações", 12));

        modulo.adicionar(alunoSolicitacoes);


        // -----------------------------------------------------------
        // CURSO → DISCIPLINAS DA MATRIZ
        // -----------------------------------------------------------
        MestreDetalhe cursoDisciplinas = new MestreDetalhe();
        cursoDisciplinas.setTitulo("Matriz Curricular");
        cursoDisciplinas.setClasse("CursoDisciplina");

        cursoDisciplinas.setCampoMestre(
                new CampoReferencia("Curso", "nome", "Curso", 6)
        );

        cursoDisciplinas.adicionar(new CampoReferencia("Disciplina", "nome", "Disciplina", 8));
        cursoDisciplinas.adicionar(new CampoInteiro("periodo", "Período/Série", 4));

        modulo.adicionar(cursoDisciplinas);


        // -----------------------------------------------------------
        // DISCIPLINA → CONTEÚDO PROGRAMÁTICO
        // -----------------------------------------------------------
        MestreDetalhe disciplinaConteudo = new MestreDetalhe();
        disciplinaConteudo.setTitulo("Conteúdo Programático");
        disciplinaConteudo.setClasse("ConteudoProgramatico");

        disciplinaConteudo.setCampoMestre(
                new CampoReferencia("Disciplina", "nome", "Disciplina", 8)
        );

        disciplinaConteudo.adicionar(new CampoTexto("titulo", "Título", 8));
        disciplinaConteudo.adicionar(new CampoAreaTexto("descricao", "Descrição", 12));
        disciplinaConteudo.adicionar(new CampoInteiro("cargaHoraria", "Carga Horária (h)", 4));
        disciplinaConteudo.adicionar(new CampoInteiro("sequencia", "Sequência", 4));

        modulo.adicionar(disciplinaConteudo);


        // -----------------------------------------------------------
        // CONTEÚDO PROGRAMÁTICO → AULAS MINISTRADAS
        // (Agora vinculando também a TURMA)
        // -----------------------------------------------------------
        MestreDetalhe conteudoAulas = new MestreDetalhe();
        conteudoAulas.setTitulo("Aulas Ministradas");
        conteudoAulas.setClasse("AulaMinistrada");

        conteudoAulas.setCampoMestre(
                new CampoReferencia("ConteudoProgramatico", "titulo", "Conteúdo", 8)
        );

        // Turma da aula (para saber em qual turma o conteúdo foi dado)
        conteudoAulas.adicionar(new CampoReferencia("Turma", "nome", "Turma", 6));
        conteudoAulas.adicionar(new CampoDate("data", "Data da Aula", 4));
        conteudoAulas.adicionar(new CampoAreaTexto("descricao", "Descrição da Aula", 12));

        modulo.adicionar(conteudoAulas);

        // -----------------------------------------------------------
        // AULA MINISTRADA → FREQUÊNCIA DE ALUNOS
        // -----------------------------------------------------------
        MestreDetalhe aulaFrequencias = new MestreDetalhe();
        aulaFrequencias.setTitulo("Frequência dos Alunos");
        aulaFrequencias.setClasse("AulaFrequencia");

        aulaFrequencias.setCampoMestre(
                new CampoReferencia("AulaMinistrada", "data", "Aula", 6)
        );

        // SOMENTE alunos da turma (conceitualmente via TurmaAluno)
        aulaFrequencias.adicionar(
                new CampoReferencia("TurmaAluno", "aluno.nome", "Aluno da Turma", 8)
        );

        aulaFrequencias.adicionar(new CampoEnum("presenca", "Presença", 4,
                new String[]{"PRESENTE", "AUSENTE", "JUSTIFICADA"}));
        aulaFrequencias.adicionar(new CampoAreaTexto("observacao", "Observação", 12));

        modulo.adicionar(aulaFrequencias);

        // -----------------------------------------------------------
        // TURMA → ALUNOS
        // -----------------------------------------------------------
        MestreDetalhe turmaAlunos = new MestreDetalhe();
        turmaAlunos.setTitulo("Alunos da Turma");
        turmaAlunos.setClasse("TurmaAluno");

        turmaAlunos.setCampoMestre(
                new CampoReferencia("Turma", "nome", "Turma", 6)
        );

        turmaAlunos.adicionar(new CampoReferencia("Aluno", "nome", "Aluno", 8));
        turmaAlunos.adicionar(new CampoEnum("situacao", "Situação", 4,
                new String[]{"MATRICULADO", "TRANCADO", "CANCELADO"}));

        modulo.adicionar(turmaAlunos);


        // -----------------------------------------------------------
        // TURMA → DISCIPLINAS DA TURMA
        // -----------------------------------------------------------
        MestreDetalhe turmaDisciplinas = new MestreDetalhe();
        turmaDisciplinas.setTitulo("Disciplinas da Turma");
        turmaDisciplinas.setClasse("TurmaDisciplina");

        turmaDisciplinas.setCampoMestre(
                new CampoReferencia("Turma", "nome", "Turma", 6)
        );

        turmaDisciplinas.adicionar(new CampoReferencia("Disciplina", "nome", "Disciplina", 8));
        turmaDisciplinas.adicionar(new CampoReferencia("Professor", "nome", "Professor", 6));
        turmaDisciplinas.adicionar(new CampoInteiro("cargaHoraria", "Carga Horária (h)", 4));
        turmaDisciplinas.adicionar(new CampoTexto("observacoes", "Observações", 12));

        modulo.adicionar(turmaDisciplinas);


        // -----------------------------------------------------------
        // LIVRO → EMPRÉSTIMOS
        // -----------------------------------------------------------
        MestreDetalhe livroEmprestimos = new MestreDetalhe();
        livroEmprestimos.setTitulo("Empréstimos");
        livroEmprestimos.setClasse("EmprestimoLivro");

        livroEmprestimos.setCampoMestre(
                new CampoReferencia("Livro", "titulo", "Livro", 8)
        );

        livroEmprestimos.adicionar(new CampoReferencia("Aluno", "nome", "Aluno", 8));
        livroEmprestimos.adicionar(new CampoDate("dataEmprestimo", "Empréstimo", 4));
        livroEmprestimos.adicionar(new CampoDate("dataPrevistaDevolucao", "Prev. Devolução", 4));
        livroEmprestimos.adicionar(new CampoDate("dataDevolucao", "Devolução", 4));
        livroEmprestimos.adicionar(new CampoEnum("status", "Status", 4,
                new String[]{"EM_ANDAMENTO", "DEVOLVIDO", "ATRASADO"}));

        modulo.adicionar(livroEmprestimos);


        // -----------------------------------------------------------
        // TRABALHO → NOTAS (somente alunos da turma)
        // -----------------------------------------------------------
        MestreDetalhe trabalhoNotas = new MestreDetalhe();
        trabalhoNotas.setTitulo("Notas do Trabalho");
        trabalhoNotas.setClasse("TrabalhoNota");

        trabalhoNotas.setCampoMestre(
                new CampoReferencia("Trabalho", "titulo", "Trabalho", 8)
        );

        trabalhoNotas.adicionar(
                new CampoReferencia("TurmaAluno", "aluno.nome", "Aluno da Turma", 8)
        );

        trabalhoNotas.adicionar(new CampoInteiro("nota", "Nota", 4));
        trabalhoNotas.adicionar(new CampoAreaTexto("observacao", "Observação", 12));

        modulo.adicionar(trabalhoNotas);


        // ===========================================================
        // KANBAN DE SOLICITAÇÕES
        // ===========================================================

        Kanban kanbanSolicitacoes = new Kanban();
        kanbanSolicitacoes.setTitulo("Kanban de Solicitações");
        kanbanSolicitacoes.setClasse("KanbanSolicitacaoSecretaria");
        kanbanSolicitacoes.setEntidadeReferencia(new EntidadeReferencia(alunoSolicitacoes, "aluno.nome"));
        kanbanSolicitacoes.setCampoStatus(new CampoEnum("status", "Status", 4,
                new String[]{"ABERTA", "EM_ANALISE", "CONCLUIDA", "CANCELADA"}));

        kanbanSolicitacoes.setAtributos(new String[]{
                "aluno.nome",
                "tipo",
                "status",
                "dataAbertura"
        });

        kanbanSolicitacoes.setMenuVisivel(true);
        modulo.adicionar(kanbanSolicitacoes);


        // ===========================================================
        // ENTIDADES
        // ===========================================================

        // AULA MINISTRADA (agora com TURMA)
        Entidade aulaMinistrada = new Entidade();
        aulaMinistrada.setTitulo("Aula Ministrada");
        aulaMinistrada.setClasse("AulaMinistrada");

        aulaMinistrada.adicionar(new CampoReferencia("Turma", "nome", "Turma", 6));
        aulaMinistrada.adicionar(new CampoReferencia("ConteudoProgramatico", "titulo", "Conteúdo", 8));
        aulaMinistrada.adicionar(new CampoDate("data", "Data da Aula", 4));
        aulaMinistrada.adicionar(new CampoAreaTexto("descricao", "Descrição da Aula", 12));
        
        aulaMinistrada.adicionarAcao(new Acao(aulaFrequencias, "Frequência", "data"));
        modulo.adicionar(aulaMinistrada);
        
        // ----------------------------
        // CURSO
        // ----------------------------
        Entidade curso = new Entidade();
        curso.setTitulo("Curso");
        curso.setClasse("Curso");

        curso.adicionar(new CampoTexto("nome", "Nome", 8));
        curso.adicionar(new CampoTexto("codigo", "Código", 4));
        curso.adicionar(new CampoEnum("nivel", "Nível", 4,
                new String[]{"FUNDAMENTAL", "MEDIO", "TECNICO", "GRADUACAO", "POS"}));
        curso.adicionar(new CampoInteiro("cargaHoraria", "Carga Horária", 4));
        curso.adicionar(new CampoEnum("status", "Status", 4,
                new String[]{"ATIVO", "INATIVO"}));

        curso.adicionarAcao(new Acao(cursoDisciplinas, "Matriz Curricular", "nome"));
        modulo.adicionar(curso);


        // ----------------------------
        // DISCIPLINA
        // ----------------------------
        Entidade disciplina = new Entidade();
        disciplina.setTitulo("Disciplina");
        disciplina.setClasse("Disciplina");

        disciplina.adicionar(new CampoTexto("nome", "Nome", 8));
        disciplina.adicionar(new CampoTexto("codigo", "Código", 4));
        disciplina.adicionar(new CampoInteiro("cargaHoraria", "Carga Horária (h)", 4));
        disciplina.adicionar(new CampoEnum("tipo", "Tipo", 4,
                new String[]{"OBRIGATORIA", "OPTATIVA"}));

        disciplina.adicionarAcao(new Acao(disciplinaConteudo, "Conteúdo Programático", "nome"));
        modulo.adicionar(disciplina);


        // ----------------------------
        // PROFESSOR
        // ----------------------------
        Entidade professor = new Entidade();
        professor.setTitulo("Professor");
        professor.setClasse("Professor");

        professor.adicionar(new CampoTexto("nome", "Nome", 8));
        professor.adicionar(new CampoTexto("cpf", "CPF", 4));
        professor.adicionar(new CampoEmail("email", "E-mail", 6));
        professor.adicionar(new CampoTexto("telefone", "Telefone", 4));
        professor.adicionar(new CampoEnum("titulacao", "Titulação", 6,
                new String[]{"GRADUACAO", "ESP", "MESTRADO", "DOUTORADO"}));
        professor.adicionar(new CampoDate("dataAdmissao", "Admissão", 4));

        modulo.adicionar(professor);


        // ----------------------------
        // ALUNO
        // ----------------------------
        Entidade aluno = new Entidade();
        aluno.setTitulo("Aluno");
        aluno.setClasse("Aluno");

        aluno.adicionar(new CampoTexto("nome", "Nome", 8));
        aluno.adicionar(new CampoTexto("cpf", "CPF", 4));
        aluno.adicionar(new CampoDate("dataNascimento", "Nascimento", 4));
        aluno.adicionar(new CampoEmail("email", "E-mail", 6));
        aluno.adicionar(new CampoTexto("telefone", "Telefone", 4));
        aluno.adicionar(new CampoTexto("nomeResponsavel", "Responsável", 8));
        aluno.adicionar(new CampoTexto("documentoResponsavel", "Doc. Responsável", 4));
        aluno.adicionar(new CampoEnum("status", "Status", 4,
                new String[]{"ATIVO", "INATIVO", "EGRESSO"}));

        aluno.adicionarAcao(new Acao(alunoSolicitacoes, "Solicitações", "nome"));
        modulo.adicionar(aluno);


        // ----------------------------
        // TURMA
        // ----------------------------
        Entidade turma = new Entidade();
        turma.setTitulo("Turma");
        turma.setClasse("Turma");

        turma.adicionar(new CampoTexto("nome", "Nome da Turma", 6));
        turma.adicionar(new CampoTexto("codigo", "Código", 4));
        turma.adicionar(new CampoReferencia("Curso", "nome", "Curso", 6));
        turma.adicionar(new CampoInteiro("ano", "Ano", 4));
        turma.adicionar(new CampoInteiro("semestre", "Semestre", 4));
        turma.adicionar(new CampoEnum("turno", "Turno", 4,
                new String[]{"MANHA", "TARDE", "NOITE", "INTEGRAL"}));
        turma.adicionar(new CampoInteiro("capacidade", "Capacidade", 4));

        turma.adicionarAcao(new Acao(turmaAlunos, "Alunos", "nome"));
        turma.adicionarAcao(new Acao(turmaDisciplinas, "Disciplinas", "nome"));

        modulo.adicionar(turma);


        // ----------------------------
        // TRABALHO
        // ----------------------------
        Entidade trabalho = new Entidade();
        trabalho.setTitulo("Trabalho Acadêmico");
        trabalho.setClasse("Trabalho");

        trabalho.adicionar(new CampoReferencia("Turma", "nome", "Turma", 6));
        trabalho.adicionar(new CampoReferencia("Disciplina", "nome", "Disciplina", 6));
        trabalho.adicionar(new CampoTexto("titulo", "Título", 8));
        trabalho.adicionar(new CampoAreaTexto("descricao", "Descrição", 12));
        trabalho.adicionar(new CampoDate("dataEntrega", "Entrega", 4));
        trabalho.adicionar(new CampoEnum("status", "Status", 4,
                new String[]{"ABERTO", "ENTREGUE", "ATRASADO"}));

        trabalho.adicionarAcao(new Acao(trabalhoNotas, "Notas", "titulo"));
        modulo.adicionar(trabalho);


        // ----------------------------
        // LIVRO
        // ----------------------------
        Entidade livro = new Entidade();
        livro.setTitulo("Livro");
        livro.setClasse("Livro");

        livro.adicionar(new CampoTexto("titulo", "Título", 8));
        livro.adicionar(new CampoTexto("autor", "Autor", 6));
        livro.adicionar(new CampoTexto("editora", "Editora", 6));
        livro.adicionar(new CampoInteiro("anoPublicacao", "Ano", 4));
        livro.adicionar(new CampoTexto("isbn", "ISBN", 4));
        livro.adicionar(new CampoInteiro("quantidadeTotal", "Qtd Total", 4));
        livro.adicionar(new CampoInteiro("quantidadeDisponivel", "Disponíveis", 4));

        livro.adicionarAcao(new Acao(livroEmprestimos, "Empréstimos", "titulo"));
        modulo.adicionar(livro);


        // ----------------------------
        // USUÁRIO
        // ----------------------------
        Entidade usuario = new Entidade();
        usuario.setTitulo("Usuário");
        usuario.setClasse("Usuario");

        usuario.adicionar(new CampoTexto("login", "Login", 4));
        usuario.adicionar(new CampoTexto("nome", "Nome", 8));
        usuario.adicionar(new CampoEmail("email", "E-mail", 6));
        usuario.adicionar(new CampoEnum("perfil", "Perfil", 4,
                new String[]{"ALUNO", "PROFESSOR", "COORDENADOR", "SECRETARIA", "ADMIN"}));

        modulo.adicionar(usuario);


        // ===========================================================
        // GERAR SISTEMA
        // ===========================================================
        modulo.gerar();
    }
}