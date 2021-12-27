package br.com.ebac.hz.modulo14;

import br.com.ebac.hz.modulo14.dao.ClienteMapDAO;
import br.com.ebac.hz.modulo14.dao.IClienteDAO;
import br.com.ebac.hz.modulo14.domain.Cliente;

import javax.swing.*;


public class App {

    private static IClienteDAO iClienteDAO;

    private static String mensagem = "Escolha uma opção:\n " +
            "1 - Cadastrar\n" +
            " 2 - Consultar\n " +
            "3 - Excluir\n " +
            "4 - Alterar\n" +
            " 5 - Sair";

    public static void main(String args[]) {
        iClienteDAO = new ClienteMapDAO();

        String opcao = JOptionPane.showInputDialog(null, mensagem,
                    "Cadastro", JOptionPane.INFORMATION_MESSAGE);

        while (!isOpcaoValida(opcao)){
            if ("".equals(opcao)) {
                sair();
            }
            opcao = JOptionPane.showInputDialog(null, "Opção Inválida\n" + mensagem,
                    "Cadastro", JOptionPane.INFORMATION_MESSAGE);
        }

        while (isOpcaoValida(opcao)) {

            if (isOpcaoSair(opcao)) {
                sair();
            } else if (isCadastro(opcao)) {
                String dados = JOptionPane.showInputDialog(null,
                        "Informe os dados do cliente separados por vígula,\n " +
                                "Exemplo: Nome, CPF, Telefone, Endereço, Número, Cidade e Estado",
                        "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                cadastrar(dados);
            } else if(isConsultar(opcao)) {
                String dados = JOptionPane.showInputDialog(null,
                        "Informe o CPF",
                        "Consultar", JOptionPane.INFORMATION_MESSAGE);
                consultar(dados);
            }else if (isExcluir(opcao)){
                String dados = JOptionPane.showInputDialog(null,
                        "Informe o CPF do cliente",
                        "Consulta cliente", JOptionPane.INFORMATION_MESSAGE);
                excluir(dados);
            }else{
                String dados1 = JOptionPane.showInputDialog(null,
                        "Informe o CPF que deseja alterar",
                        "Consulta cliente", JOptionPane.INFORMATION_MESSAGE);
                consultar(dados1);
                String dados = JOptionPane.showInputDialog(null,
                    "Digite os dados do cliente separados por vígula, conforme exemplo: Nome, CPF, Telefone, Endereço, Número, Cidade e Estado",
                    "Atualização", JOptionPane.INFORMATION_MESSAGE);
                atualizar(dados);
        }

            opcao = JOptionPane.showInputDialog(null, mensagem,
                            "Cadastro", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void cadastrar(String dados) {
        String[] dadosSeparados = dados.split(",");
        Cliente cliente = new Cliente(dadosSeparados[0],dadosSeparados[1],dadosSeparados[2],dadosSeparados[3],dadosSeparados[4],dadosSeparados[5],dadosSeparados[6]);
        if (dadosSeparados[0] == null){
            JOptionPane.showMessageDialog(null,
                    "Campo NOME obrigatório ",
                    "ERRO",JOptionPane.INFORMATION_MESSAGE);
        } else if (dadosSeparados[1] == null){
            JOptionPane.showMessageDialog(null,
                    "Campo CPF obrigatório ",
                    "ERRO",JOptionPane.INFORMATION_MESSAGE);
        } else {
            Boolean cadastrado = iClienteDAO.cadastrar(cliente);
            if (cadastrado) {
                JOptionPane.showMessageDialog(null,
                        "Cliente cadastrado com sucesso ",
                        "Sucesso",JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Cliente já se encontra cadastrado",
                        "Erro",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private static void consultar(String dados) {
        Cliente cliente = iClienteDAO.consultar(Long.parseLong(dados));
        if (cliente != null) {
            JOptionPane.showMessageDialog(null,
                    "Cliente encontrado: " + cliente.toString(),
                    "Sucesso",JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Cliente não encontrado: ",
                    "Erro",JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private static void excluir(String dados) {
            iClienteDAO.excluir(Long.parseLong(dados));
            JOptionPane.showMessageDialog(null,
                    "Cliente excluído com sucesso: ",
                    "Sucesso",JOptionPane.INFORMATION_MESSAGE);
    }

    private static void atualizar(String dados) {
        String[] dadosSeparados = dados.split(",");
        Cliente cliente = new Cliente(dadosSeparados[0],dadosSeparados[1],dadosSeparados[2],dadosSeparados[3],dadosSeparados[4],dadosSeparados[5],dadosSeparados[6]);
        iClienteDAO.alterar(cliente);
    }

    private static void sair() {
        JOptionPane.showMessageDialog(null,
                "Até logo: ",
                "Sair",JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    private static boolean isOpcaoValida(String opcao) {
        if ("1".equals(opcao) || "2".equals(opcao)
                || "3".equals(opcao) || "4".equals(opcao) || "5".equals(opcao)) {
            return true;
        }
        return false;
    }

    private static boolean isCadastro(String opcao) {
        if ("1".equals(opcao)) {
            return true;
        }
        return false;
    }

    private static boolean isConsultar(String opcao) {
        if ("2".equals(opcao)) {
            return true;
        }
        return false;
    }

    private static boolean isExcluir(String opcao) {
        if ("3".equals(opcao)) {
            return true;
        }
        return false;
    }

    private static boolean isOpcaoSair(String opcao) {
        if ("5".equals(opcao)) {
            return true;
        }
        return false;
    }
}