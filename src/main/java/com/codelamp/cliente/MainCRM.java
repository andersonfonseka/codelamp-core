package com.codelamp.cliente;

import com.codelamp.template.crud.Entidade;
import com.codelamp.template.dominio.Acao;
import com.codelamp.template.dominio.EntidadeReferencia;
import com.codelamp.template.dominio.Modulo;
import com.codelamp.template.dominio.Projeto;
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
import com.codelamp.template.md.MestreDetalhe;

public class MainCRM extends Modulo {

    public MainCRM() {

        setTitulo("CRM");

        // ===========================================================
        // === MESTRE-DETALHE
        // ===========================================================

        // Empresa -> Contatos
        MestreDetalhe empresaContatos =
                new MestreDetalhe("Contatos", "EmpresaContato", Mestre.build("Empresa", "Nome", "Empresa"));

        empresaContatos.adicionar(new CampoTexto("Nome", "Nome", 6));
        empresaContatos.adicionar(new CampoEmail("Email", "E-mail", 6));
        empresaContatos.adicionar(new CampoTexto("Telefone", "Telefone", 4));
        empresaContatos.adicionar(new CampoTexto("Cargo", "Cargo", 4));
        empresaContatos.adicionar(new CampoEnum("Tipo", "Tipo", 4,
                new String[]{"DECISOR", "INFLUENCIADOR", "OPERACIONAL", "FINANCEIRO"}));

        adicionar(empresaContatos);

        // Empresa -> Oportunidades
        MestreDetalhe empresaOportunidades =
                new MestreDetalhe("Oportunidades", "EmpresaOportunidade", Mestre.build("Empresa", "Nome", "Empresa"));

        empresaOportunidades.adicionar(new CampoTexto("Titulo", "Título", 8));
        empresaOportunidades.adicionar(new CampoAreaTexto("Descricao", "Descrição", 12));
        empresaOportunidades.adicionar(new CampoDouble("ValorPrevisto", "Valor Previsto (R$)", 4));
        empresaOportunidades.adicionar(new CampoInteiro("Probabilidade", "Probabilidade (%)", 4));

        empresaOportunidades.adicionar(new CampoEnum("Status", "Status", 6,
                new String[]{"PROSPECCAO", "QUALIFICACAO", "PROPOSTA", "NEGOCIACAO", "FECHADO", "PERDIDO"}));

        empresaOportunidades.adicionar(new CampoDate("DataCriacao", "Data de Criação", 4));
        empresaOportunidades.adicionar(new CampoDate("DataFechamento", "Data de Fechamento", 4));

        adicionar(empresaOportunidades);

        // Empresa -> Interações
        MestreDetalhe empresaInteracoes =
                new MestreDetalhe("Interações", "EmpresaInteracao", Mestre.build("Empresa", "Nome", "Empresa"));

        empresaInteracoes.adicionar(new CampoEnum("Tipo", "Tipo", 4,
                new String[]{"LIGACAO", "EMAIL", "REUNIAO", "WHATSAPP", "VISITA"}));
        empresaInteracoes.adicionar(new CampoDate("Data", "Data", 4));
        empresaInteracoes.adicionar(new CampoAreaTexto("Descricao", "Descrição", 12));

        adicionar(empresaInteracoes);

        // ===========================================================
        // === ENTIDADES
        // ===========================================================

        // Contato (cadastro simples também)
        Entidade contato = new Entidade("Contato", "Contato");
        contato.adicionar(new CampoTexto("Nome", "Nome", 6));
        contato.adicionar(new CampoEmail("Email", "E-mail", 6));
        contato.adicionar(new CampoTexto("Telefone", "Telefone", 4));
        contato.adicionar(new CampoTexto("Cargo", "Cargo", 4));
        contato.adicionar(new CampoReferencia("Empresa", "Nome", "Empresa", 6));
        adicionar(contato);
        
        // Interação (cadastro simples também)
        Entidade interacao = new Entidade("Interacao", "Interacao");
        interacao.adicionar(new CampoEnum("Tipo", "Tipo", 4,
                new String[]{"LIGACAO", "EMAIL", "REUNIAO", "WHATSAPP", "VISITA"}));
        interacao.adicionar(new CampoDate("Data", "Data", 4));
        interacao.adicionar(new CampoAreaTexto("Descricao", "Descrição", 12));
        interacao.adicionar(new CampoReferencia("Empresa", "Nome", "Empresa", 6));
        adicionar(interacao);

        // ===========================================================
        // === KANBAN (Funil)
        // ===========================================================
        Kanban funil = new Kanban("Funil", "FunilOportunidades", Mestre.build("Empresa", "Nome", "Empresa"));
        funil.setEntidadeReferencia(new EntidadeReferencia(empresaOportunidades, "nome"));
        funil.setCampoStatus((CampoEnum) empresaOportunidades.getCampo("Status"));
        funil.setAtributos(new String[]{"titulo", "valorPrevisto", "probabilidade"});

        adicionar(funil);
        
        // Empresa
        Entidade empresa = new Entidade("Empresa", "Empresa");
        empresa.adicionar(new CampoTexto("Nome", "Nome", 8));
        empresa.adicionar(new CampoTexto("Cnpj", "CNPJ", 4));
        empresa.adicionar(new CampoTexto("Telefone", "Telefone", 4));
        empresa.adicionar(new CampoEmail("Email", "E-mail", 4));
        empresa.adicionar(new CampoTexto("Cidade", "Cidade", 4));
        empresa.adicionar(new CampoTexto("Segmento", "Segmento", 4));

        empresa.adicionar(new CampoEnum("Status", "Status", 4,
                new String[]{"ATIVO", "PROSPECT", "INATIVO"}));

        empresa.adicionarAcao(new Acao(empresaContatos, "Contatos", "nome"));
        empresa.adicionarAcao(new Acao(empresaOportunidades, "Oportunidades", "nome"));
        empresa.adicionarAcao(new Acao(empresaInteracoes, "Interações", "nome"));
        // Ação no mestre para abrir o Kanban
        empresa.adicionarAcaoKanban(new Acao(funil, "Funil", "empresa.nome"));
        
        adicionar(empresa);

    }
    
    public static void main(String[] args) {
		
    	Projeto projeto = new Projeto();
    	projeto.setTitulo("Customer Relationship Management");
    	
    	projeto.addModulos(new MainCRM());
    	
    	projeto.gerar();
    	
    	
	}
}
