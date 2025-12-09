package com.codelamp.cliente;

import com.codelamp.template.crud.Entidade;
import com.codelamp.template.dominio.Acao;
import com.codelamp.template.dominio.Modulo;
import com.codelamp.template.dominio.campo.CampoDate;
import com.codelamp.template.dominio.campo.CampoDouble;
import com.codelamp.template.dominio.campo.CampoEnum;
import com.codelamp.template.dominio.campo.CampoInteiro;
import com.codelamp.template.dominio.campo.CampoReferencia;
import com.codelamp.template.dominio.campo.Mestre;
import com.codelamp.template.md.MestreDetalhe;

public class MainFinanceiro extends Modulo {

    public MainFinanceiro() {

        setTitulo("Financeiro");

        MestreDetalhe pagamentos = new MestreDetalhe("Pagamentos", "Pagamentos", Mestre.build("Contrato", "numero", "Contrato"));

        pagamentos.adicionar(new CampoDate("DataPagamento", "Data do Pagamento", 4));
        pagamentos.adicionar(new CampoDouble("ValorPago", "Valor Pago", 4));
        pagamentos.adicionar(new CampoEnum("MeioPagamento", "Meio de Pagamento", 4, new String[]{"PIX", "BOLETO", "TRANSFERENCIA"}));

        adicionar(pagamentos);
        
        Entidade contratos = new Entidade("Contrato", "Contrato");
        
        contratos.adicionar(new CampoInteiro("Numero", "Numero", 4));
        contratos.adicionar(new CampoReferencia("Aluno", "Nome", "Aluno", 8));
        contratos.adicionar(new CampoDate("DataInicio", "Inicio", 4));
        contratos.adicionar(new CampoDate("DataTermino", "Termino", 4));
        contratos.adicionar(new CampoDouble("Mensalidade", "Mensalidade", 4));
        
        contratos.adicionarAcao(new Acao(pagamentos, "Pagamentos", "aluno.nome"));

        adicionar(contratos);

    }
}
