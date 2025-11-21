package com.codelamp.cliente;

import com.codelamp.template.crud.Entidade;
import com.codelamp.template.dominio.Acao;
import com.codelamp.template.dominio.Modulo;
import com.codelamp.template.dominio.campo.CampoAreaTexto;
import com.codelamp.template.dominio.campo.CampoArquivo;
import com.codelamp.template.dominio.campo.CampoDate;
import com.codelamp.template.dominio.campo.CampoEmail;
import com.codelamp.template.dominio.campo.CampoEnum;
import com.codelamp.template.dominio.campo.CampoReferencia;
import com.codelamp.template.dominio.campo.CampoTexto;
import com.codelamp.template.dominio.campo.CampoURL;
import com.codelamp.template.md.MestreDetalhe;


public class MainRecrutamento {

    public static void main(String[] args) {
    	
        // ===========================================================
        // === MÓDULO PRINCIPAL
        // ===========================================================
        Modulo modulo = new Modulo();
        modulo.setTitulo("Recrutamento e Seleção");
    	
    	MestreDetalhe etapasProcesso = new MestreDetalhe();
        etapasProcesso.setTitulo("Etapas do Processo");
        etapasProcesso.setClasse("EtapasProcesso");
        etapasProcesso.setCampoMestre(new CampoReferencia("ProcessoSeletivo", "titulo", "Processo Seletivo", 6));
        etapasProcesso.adicionar(new CampoReferencia("Candidato", "nome", "Candidato", 6));
        etapasProcesso.adicionar(new CampoEnum("status", "Status", 6, new String[]{"Triagem", "Entrevista", "Testes", "Aprovado", "Reprovado"}));
        etapasProcesso.adicionar(new CampoDate("dataMovimento", "Data da Movimentacao", 6));

        modulo.adicionar(etapasProcesso);
        
        // ===========================================================
        // === ENTIDADE: VAGA
        // ===========================================================
        Entidade vaga = new Entidade();
        vaga.setTitulo("Vaga");
        vaga.setClasse("Vaga");

        vaga.adicionar(new CampoTexto("titulo", "Título", 8));
        vaga.adicionar(new CampoAreaTexto("descricao", "Descrição", 12));
        vaga.adicionar(new CampoAreaTexto("requisitos", "Requisitos", 12));

        vaga.adicionar(new CampoEnum("status", "Status", 4,
                new String[]{"Aberta", "Fechada"}));
        
        modulo.adicionar(vaga);


        // ===========================================================
        // === ENTIDADE: CANDIDATO
        // ===========================================================
        Entidade candidato = new Entidade();
        candidato.setTitulo("Candidato");
        candidato.setClasse("Candidato");

        candidato.adicionar(new CampoTexto("nome", "Nome", 8));
        candidato.adicionar(new CampoArquivo("foto", "Foto", 4));
        candidato.adicionar(new CampoEmail("email", "E-mail", 6));
        candidato.adicionar(new CampoTexto("telefone", "Telefone", 4));
        candidato.adicionar(new CampoURL("linkedin", "LinkedIn", 12));
        candidato.adicionar(new CampoAreaTexto("observacoes", "Observações", 12));
        
        
        modulo.adicionar(candidato);


        // ===========================================================
        // === ENTIDADE: PROCESSO SELETIVO
        // ===========================================================
        Entidade processo = new Entidade();
        processo.setTitulo("Processo Seletivo");
        processo.setClasse("ProcessoSeletivo");

        // Campo essencial para melhorar UX
        processo.adicionar(new CampoTexto("titulo", "Título do Processo", 8));
        processo.adicionar(new CampoReferencia("Vaga", "titulo", "Vaga", 6));
        processo.adicionar(new CampoEnum("status", "Status", 4, new String[]{"Novo", "Em Andamento", "Concluído"}));
        
        processo.adicionarAcao(new Acao(etapasProcesso, "Etapas", "titulo"));

        modulo.adicionar(processo);
        
        // ===========================================================
        // === ENTIDADE: USUÁRIO
        // ===========================================================
        Entidade usuario = new Entidade();
        usuario.setTitulo("Usuário");
        usuario.setClasse("Usuario");

        usuario.adicionar(new CampoTexto("nome", "Nome", 8));
        usuario.adicionar(new CampoEmail("email", "E-mail", 6));

        usuario.adicionar(new CampoEnum("perfil", "Perfil", 4,
                new String[]{"RH", "Gestor"}));

        modulo.adicionar(usuario);

        // ===========================================================
        // === GERAR SISTEMA
        // ===========================================================
        modulo.gerar();
    }
}
