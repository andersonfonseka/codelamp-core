package com.codelamp.cliente;

import com.codelamp.template.crud.Entidade;
import com.codelamp.template.dominio.Acao;
import com.codelamp.template.dominio.Modulo;
import com.codelamp.template.dominio.campo.Campo;
import com.codelamp.template.dominio.campo.CampoAreaTexto;
import com.codelamp.template.dominio.campo.CampoDate;
import com.codelamp.template.dominio.campo.CampoDouble;
import com.codelamp.template.dominio.campo.CampoEmail;
import com.codelamp.template.dominio.campo.CampoEnum;
import com.codelamp.template.dominio.campo.CampoReferencia;
import com.codelamp.template.dominio.campo.CampoTexto;
import com.codelamp.template.md.MestreDetalhe;

public class MainFinanceiroSimples {

    public static void main(String[] args) {

        // ===========================================================
        // === MÓDULO PRINCIPAL
        // ===========================================================
        Modulo modulo = new Modulo();
        modulo.setTitulo("Financeiro Simples");

        // ===========================================================
        // === MESTRE-DETALHE: PAGAMENTOS DA CONTA A PAGAR
        // ===========================================================
        MestreDetalhe pagamentosConta = new MestreDetalhe();
        pagamentosConta.setTitulo("Pagamentos da Conta");
        pagamentosConta.setClasse("PagamentoConta");

        // Campo mestre → Conta a Pagar
        pagamentosConta.setCampoMestre(
                new CampoReferencia("ContaPagar", "descricao", "Conta a Pagar", 6)
        );

        // Detalhes do pagamento
        pagamentosConta.adicionar(new CampoDate("dataPagamento", "Data do Pagamento", 4));
        pagamentosConta.adicionar(new CampoDouble("valorPago", "Valor Pago", 4));
        pagamentosConta.adicionar(new CampoAreaTexto("observacao", "Observação", 12));

        modulo.adicionar(pagamentosConta);

        // ===========================================================
        // === MESTRE-DETALHE: RECEBIMENTOS DA CONTA A RECEBER
        // ===========================================================
        MestreDetalhe recebimentosConta = new MestreDetalhe();
        recebimentosConta.setTitulo("Recebimentos da Conta");
        recebimentosConta.setClasse("RecebimentoConta");

        // Campo mestre → Conta a Receber
        recebimentosConta.setCampoMestre(
                new CampoReferencia("ContaReceber", "descricao", "Conta a Receber", 6)
        );

        // Detalhes do recebimento
        recebimentosConta.adicionar(new CampoDate("dataRecebimento", "Data do Recebimento", 4));
        recebimentosConta.adicionar(new CampoDouble("valorRecebido", "Valor Recebido", 4));
        recebimentosConta.adicionar(new CampoAreaTexto("observacao", "Observação", 12));

        modulo.adicionar(recebimentosConta);

        // ===========================================================
        // === ENTIDADE: FORNECEDOR
        // ===========================================================
        Entidade fornecedor = new Entidade();
        fornecedor.setTitulo("Fornecedor");
        fornecedor.setClasse("Fornecedor");

        fornecedor.adicionar(new CampoTexto("nome", "Nome do Fornecedor", 8));
        fornecedor.adicionar(new CampoEmail("email", "E-mail", 6));
        fornecedor.adicionar(new CampoTexto("telefone", "Telefone", 4));

        modulo.adicionar(fornecedor);

        // ===========================================================
        // === ENTIDADE: CLIENTE
        // ===========================================================
        Entidade cliente = new Entidade();
        cliente.setTitulo("Cliente");
        cliente.setClasse("Cliente");

        cliente.adicionar(new CampoTexto("nome", "Nome do Cliente", 8));
        cliente.adicionar(new CampoEmail("email", "E-mail", 6));
        cliente.adicionar(new CampoTexto("telefone", "Telefone", 4));

        modulo.adicionar(cliente);

        // ===========================================================
        // === ENTIDADE: CONTA A PAGAR
        // ===========================================================
        Entidade contaPagar = new Entidade();
        contaPagar.setTitulo("Conta a Pagar");
        contaPagar.setClasse("ContaPagar");

        contaPagar.adicionar(new CampoTexto("descricao", "Descrição", 8));
        contaPagar.adicionar(new CampoReferencia("Fornecedor", "nome", "Fornecedor", 6));
        contaPagar.adicionar(new CampoDouble("valorTotal", "Valor Total", 4));
        contaPagar.adicionar(new CampoDate("dataVencimento", "Data de Vencimento", 4));

        contaPagar.adicionar(new CampoEnum("status", "Status", 4,
                new String[]{"Aberta", "Parcialmente Paga", "Paga"}));

        // Ação → acessar pagamentos
        contaPagar.adicionarAcao(new Acao(pagamentosConta, "Pagamentos", "descricao"));

        modulo.adicionar(contaPagar);

        // ===========================================================
        // === ENTIDADE: CONTA A RECEBER
        // ===========================================================
        Entidade contaReceber = new Entidade();
        contaReceber.setTitulo("Conta a Receber");
        contaReceber.setClasse("ContaReceber");

        contaReceber.adicionar(new CampoTexto("descricao", "Descrição", 8));
        contaReceber.adicionar(new CampoReferencia("Cliente", "nome", "Cliente", 6));
        contaReceber.adicionar(new CampoDouble("valorTotal", "Valor Total", 4));
        contaReceber.adicionar(new CampoDate("dataVencimento", "Data de Vencimento", 4));

        contaReceber.adicionar(new CampoEnum("status", "Status", 4,
                new String[]{"Aberta", "Parcialmente Recebida", "Recebida"}));

        // Ação → acessar recebimentos
        contaReceber.adicionarAcao(new Acao(recebimentosConta, "Recebimentos", "descricao"));

        modulo.adicionar(contaReceber);

        // ===========================================================
        // === ENTIDADE: USUÁRIO
        // ===========================================================
        Entidade usuario = new Entidade();
        usuario.setTitulo("Usuário");
        usuario.setClasse("Usuario");

        usuario.adicionar(new CampoTexto("nome", "Nome", 8));
        usuario.adicionar(new CampoEmail("email", "E-mail", 6));

        usuario.adicionar(new CampoEnum("perfil", "Perfil", 4,
                new String[]{"Financeiro", "Administrador"}));

        modulo.adicionar(usuario);

        // ===========================================================
        // === GERAR SISTEMA
        // ===========================================================
        modulo.gerar();
    }
}
