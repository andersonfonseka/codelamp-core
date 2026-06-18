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

public class MainAcademico extends Modulo {

    public MainAcademico() {

        setTitulo("Academico");

        MestreDetalhe alunoSolicitacoes = new MestreDetalhe("Solicitações", "SolicitacaoSecretaria", Mestre.build("Aluno", "Nome", "Aluno"));

        alunoSolicitacoes.adicionar(new CampoEnum("Tipo", "Tipo", 6,  new String[]{"HISTORICO", "DECLARACAO", "BOLETIM", "ATESTADO", "OUTROS"}));
        alunoSolicitacoes.adicionar(new CampoDate("DataAbertura", "Abertura", 6));
        alunoSolicitacoes.adicionar(new CampoEnum("Status", "Status", 6, new String[]{"ABERTA", "EM_ANALISE", "CONCLUIDA", "CANCELADA"}));
        alunoSolicitacoes.adicionar(new CampoAreaTexto("Observacoes", "Observações", 6));

        adicionar(alunoSolicitacoes);

        
        MestreDetalhe cursoDisciplinas = new MestreDetalhe("Matriz Curricular", "CursoDisciplina", Mestre.build("Curso", "Nome", "Curso"));

        cursoDisciplinas.adicionar(new CampoReferencia("Disciplina", "Nome", "Disciplina", 6));
        cursoDisciplinas.adicionar(new CampoInteiro("Periodo", "Período/Série", 6));

        adicionar(cursoDisciplinas);


        MestreDetalhe disciplinaConteudo = new MestreDetalhe("Conteúdo Programático", "ConteudoProgramatico", Mestre.build("Disciplina", "Nome", "Disciplina"));

        disciplinaConteudo.adicionar(new CampoTexto("Titulo", "Título", 6));
        disciplinaConteudo.adicionar(new CampoAreaTexto("Descricao", "Descrição", 12));
        disciplinaConteudo.adicionar(new CampoInteiro("CargaHoraria", "Carga Horária (h)", 6));

        adicionar(disciplinaConteudo);


        MestreDetalhe turmaAlunos = new MestreDetalhe("Alunos da Turma", "TurmaAluno", Mestre.build("Turma", "Nome", "Turma"));

        turmaAlunos.adicionar(new CampoReferencia("Aluno", "Nome", "Aluno", 6));
        turmaAlunos.adicionar(new CampoEnum("Situacao", "Situação", 6, new String[]{"MATRICULADO", "TRANCADO", "CANCELADO"}));

        adicionar(turmaAlunos);
        

        Lote aulaFrequencias = new Lote("Frequência dos Alunos", "AulaFrequencia", Mestre.build("TurmaAulaMinistrada", "Descricao", "Aula"));

        aulaFrequencias.setCampoRotulo(new CampoReferencia("TurmaAluno", "aluno.nome", "Aluno", 6));
        aulaFrequencias.setCampoValor(new CampoEnum("Presenca", "Presença", 6, new String[]{"PRESENTE", "AUSENTE", "JUSTIFICADA"}));
        
        adicionar(aulaFrequencias);


        MestreDetalhe turmaAulas = new MestreDetalhe("Aulas Ministradas", "TurmaAulaMinistrada", Mestre.build("TurmaDisciplina", "disciplina.nome", "Disciplina"));

        turmaAulas.adicionar(new CampoReferencia("ConteudoProgramatico", "Titulo", "Conteúdo", 6));
        turmaAulas.adicionar(new CampoDate("Data", "Data da Aula", 6));
        turmaAulas.adicionar(new CampoAreaTexto("Descricao", "Descrição da Aula", 6));
        
        turmaAulas.adicionarAcao(new Acao(aulaFrequencias, "Frequencia", "descricao"));

        adicionar(turmaAulas);


        Lote trabalhoNotas = new Lote("Notas do Trabalho", "TrabalhoNota", Mestre.build("TurmaTrabalho", "Descricao", "Trabalho"));
        
        trabalhoNotas.setCampoRotulo(new CampoReferencia("TurmaAluno", "aluno.nome", "Aluno da Turma", 6));
        trabalhoNotas.setCampoValor(new CampoDouble("Nota", "Nota", 6));

        adicionar(trabalhoNotas);
        

        MestreDetalhe turmaTrabalhos = new MestreDetalhe("Trabalhos da Turma", "TurmaTrabalho", Mestre.build("TurmaDisciplina", "disciplina.nome", "Disciplina"));

        turmaTrabalhos.adicionar(new CampoTexto("Titulo", "Título", 6));
        turmaTrabalhos.adicionar(new CampoDate("DataEntrega", "Entrega", 6));
        turmaTrabalhos.adicionar(new CampoAreaTexto("Descricao", "Descrição", 6));
        turmaTrabalhos.adicionar(new CampoEnum("Status", "Status", 6, new String[]{"ABERTO", "ENTREGUE", "ATRASADO"}));
        
        turmaTrabalhos.adicionarAcao(new Acao(trabalhoNotas, "Notas", "descricao"));

        adicionar(turmaTrabalhos);


        MestreDetalhe turmaDisciplinas = new MestreDetalhe("Disciplinas da Turma", "TurmaDisciplina", Mestre.build("Turma", "Nome", "Turma"));

        turmaDisciplinas.adicionar(new CampoReferencia("Disciplina", "Nome", "Disciplina", 6));
        turmaDisciplinas.adicionar(new CampoReferencia("Professor", "Nome", "Professor", 6));
        turmaDisciplinas.adicionar(new CampoInteiro("CargaHoraria", "Carga Horária", 6));
        turmaDisciplinas.adicionar(new CampoTexto("Observacoes", "Observações", 6));

        turmaDisciplinas.adicionarAcao(new Acao(turmaAulas, "Aulas Ministradas", "disciplina.nome"));
        turmaDisciplinas.adicionarAcao(new Acao(turmaTrabalhos, "Avaliações / Tarefas", "disciplina.nome"));
        
        adicionar(turmaDisciplinas);
               

        // TURMA
        MestreDetalhe turma = new MestreDetalhe("Turma", "Turma", Mestre.build("Curso", "Nome", "Curso"));
        
        turma.adicionar(new CampoTexto("Nome", "Nome da Turma", 6));
        turma.adicionar(new CampoTexto("Codigo", "Código", 6));
        turma.adicionar(new CampoInteiro("Ano", "Ano", 2));
        turma.adicionar(new CampoInteiro("Semestre", "Semestre", 2));
        turma.adicionar(new CampoEnum("Turno", "Turno", 4, new String[]{"MANHA", "TARDE", "NOITE", "INTEGRAL"}));
        turma.adicionar(new CampoInteiro("Capacidade", "Capacidade", 4));

        turma.adicionarAcao(new Acao(turmaAlunos, "Alunos", "nome"));
        turma.adicionarAcao(new Acao(turmaDisciplinas, "Disciplinas", "nome"));

        adicionar(turma);
        

//        Kanban kanbanSolicitacoes = new Kanban("Acompanhamento de Solicitações", "AcompanhamentoSolicitacao");
//        kanbanSolicitacoes.setEntidadeReferencia(new EntidadeReferencia(alunoSolicitacoes, "aluno.nome"));
//        kanbanSolicitacoes.setCampoStatus((CampoEnum) alunoSolicitacoes.getCampo("Status"));
//        
//        kanbanSolicitacoes.setAtributos(new String[]{
//                "aluno.nome",
//                "tipo",
//                "status",
//                "dataAbertura"
//        });
//
//        kanbanSolicitacoes.setMenuVisivel(true);
//        adicionar(kanbanSolicitacoes);



        Entidade curso = new Entidade("Curso", "Curso");

        curso.adicionar(new CampoTexto("Nome", "Nome", 8));
        curso.adicionar(new CampoTexto("Codigo", "Código", 4));
        curso.adicionar(new CampoEnum("Nivel", "Nível", 4, new String[]{"FUNDAMENTAL", "MEDIO", "TECNICO", "GRADUACAO", "POS"}));
        curso.adicionar(new CampoInteiro("CargaHoraria", "Carga Horária", 4));
        curso.adicionar(new CampoEnum("Status", "Status", 4, new String[]{"ATIVO", "INATIVO"}));

        curso.adicionarAcao(new Acao(cursoDisciplinas, "Matriz Curricular", "nome"));
        curso.adicionarAcao(new Acao(turma, "Turmas", "nome"));
        
        adicionar(curso);


        Entidade disciplina = new Entidade("Disciplina", "Disciplina");

        disciplina.adicionar(new CampoTexto("Nome", "Nome", 8));
        disciplina.adicionar(new CampoTexto("Codigo", "Código", 4));
        disciplina.adicionar(new CampoInteiro("CargaHoraria", "Carga Horária (h)", 4));
        disciplina.adicionar(new CampoEnum("Tipo", "Tipo", 4, new String[]{"OBRIGATORIA", "OPTATIVA"}));

        disciplina.adicionarAcao(new Acao(disciplinaConteudo, "Conteúdo", "nome"));
        adicionar(disciplina);


        Entidade professor = new Entidade("Professor", "Professor");

        professor.adicionar(new CampoTexto("Nome", "Nome", 8));
        professor.adicionar(new CampoTexto("Cpf", "CPF", 4));
        professor.adicionar(new CampoEmail("Email", "E-mail", 6));
        professor.adicionar(new CampoTexto("Telefone", "Telefone", 4));
        professor.adicionar(new CampoEnum("Titulacao", "Titulação", 6, new String[]{"GRADUACAO", "ESP", "MESTRADO", "DOUTORADO"}));
        professor.adicionar(new CampoDate("DataAdmissao", "Admissão", 4));

        adicionar(professor);


        Entidade aluno = new Entidade("Aluno", "Aluno");

        aluno.adicionar(new CampoTexto("Nome", "Nome", 8));
        aluno.adicionar(new CampoTexto("Cpf", "CPF", 4));
        aluno.adicionar(new CampoDate("DataNascimento", "Nascimento", 4));
        aluno.adicionar(new CampoEmail("Email", "E-mail", 6));
        aluno.adicionar(new CampoTexto("Telefone", "Telefone", 4));
        aluno.adicionar(new CampoTexto("NomeResponsavel", "Responsável", 8));
        aluno.adicionar(new CampoTexto("DocumentoResponsavel", "Doc. Responsável", 4));
        aluno.adicionar(new CampoEnum("Status", "Status", 4, new String[]{"ATIVO", "INATIVO", "EGRESSO"}));

        aluno.adicionarAcao(new Acao(alunoSolicitacoes, "Solicitações", "nome"));
        adicionar(aluno);

        Entidade usuario = new Entidade("Usuário", "Usuario");

        usuario.adicionar(new CampoTexto("Login", "Login", 4));
        usuario.adicionar(new CampoTexto("Nome", "Nome", 8));
        usuario.adicionar(new CampoEmail("Email", "E-mail", 6));
        usuario.adicionar(new CampoEnum("Perfil", "Perfil", 4, new String[]{"ALUNO", "PROFESSOR", "COORDENADOR", "SECRETARIA", "ADMIN"}));

        adicionar(usuario);

    }
}
