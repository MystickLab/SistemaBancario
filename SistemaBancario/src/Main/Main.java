package Main;

import AgenciaeAtendente.Agencia;
import AgenciaeAtendente.Atendente;
import Banco.Banco;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String opt;
        int numeroAgencia;
        boolean loop = true;

        // Criação do banco com 2 agências
        Banco banco = new Banco("Banco Seu");

        // Adicionando atendentes para as agências (exemplo)
        for (int i = 0; i < 2; i++) {
            Agencia agencia = banco.acessarAgencia(i);
            if (agencia != null) {
                agencia.adicionarAtendente(new Atendente("Atendente " + (i + 1), agencia));
            }
        }

        while (loop) {
            opt = JOptionPane.showInputDialog(MENU_PRINCIPAL());

            if (opt == null) {
                loop = false;
                break;
            }

            switch (opt) {
                case "1":
                    String[] dadosAcesso = ACESSAR_CONTA();
                    try {
                        numeroAgencia = Integer.parseInt(dadosAcesso[0]);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Por favor, insira um número válido para a agência.");
                        break;
                    }

                    Agencia agencia = banco.acessarAgencia(numeroAgencia);
                    if (agencia != null) {
                        Atendente atendente = agencia.acessarAtendente(0); // Aqui selecionamos o primeiro atendente
                        boolean acessoValido = atendente.verificarConta(dadosAcesso[1], dadosAcesso[2], dadosAcesso[3]);
                        if (acessoValido) {
                            JOptionPane.showMessageDialog(null, "Acesso concedido! Bem-vindo(a) à sua conta.");
                            // Aqui você pode adicionar mais opções para o cliente após o acesso
                        } else {
                            JOptionPane.showMessageDialog(null, "Acesso negado. Verifique seus dados.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "AGÊNCIA NÃO ENCONTRADA.");
                    }
                    break;

                case "2":
                    Agencia[] agencias = banco.getAgencias();
                    numeroAgencia = MENU_AGENCIAS_DISPONIVEIS(agencias);

                    if (numeroAgencia != -1) {
                        Agencia agenciaSelecionada = banco.acessarAgencia(numeroAgencia);
                        if (agenciaSelecionada != null) {
                            Atendente atendente = agenciaSelecionada.acessarAtendente(0); // Selecionando o primeiro atendente
                            MENU_ATENDENTE(atendente);
                        } else {
                            JOptionPane.showMessageDialog(null, "AGÊNCIA NÃO ENCONTRADA.");
                        }
                    }
                    break;

                case "3":
                    String[] dadosDeposito = DEPOSITAR();
                    try {
                        numeroAgencia = Integer.parseInt(dadosDeposito[0]);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Por favor, insira um número válido para a agência.");
                        break;
                    }

                    Agencia agenciaParaDeposito = banco.acessarAgencia(numeroAgencia);
                    if (agenciaParaDeposito != null) {
                        Atendente atendenteDeposito = agenciaParaDeposito.acessarAtendente(0); // Selecionando o primeiro atendente
                        double valorDeposito;
                        try {
                            valorDeposito = Double.parseDouble(dadosDeposito[3]);
                        } catch (NumberFormatException e) {
                        	JOptionPane.showMessageDialog(null, "Valor de depósito inválido.");
                            break;
                        }
                        boolean sucessoDeposito = atendenteDeposito.depositar(dadosDeposito[1], dadosDeposito[2], valorDeposito);
                        if (sucessoDeposito) {
                            JOptionPane.showMessageDialog(null, "Depósito realizado com sucesso!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Erro ao realizar o depósito.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "AGÊNCIA NÃO ENCONTRADA.");
                    }
                    break;

                case "4":
                    INFO();
                    break;

                case "0":
                    loop = false;
                    BYE();
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "OPÇÃO INVÁLIDA! TENTE NOVAMENTE.");
                    break;
            }
        }
    }

    private static void MENU_ATENDENTE(Atendente atendente) {
        JOptionPane.showMessageDialog(null, "Você está com o atendente: " + atendente.getNome());
    }

    private static String MENU_PRINCIPAL() {
        return "------------- Banco Seu -------------\n" +
                "Bem-Vindo(a) ao SEU Banco!\n\n" +
                "[1] - Acessar conta\n" +
                "[2] - Atendente virtual\n" +
                "[3] - Depósito\n" +
                "[4] - Sobre\n\n" +
                "[0] - Sair do sistema\n";
    }

    private static int MENU_AGENCIAS_DISPONIVEIS(Agencia[] ages) {
        StringBuilder menu = new StringBuilder();
        menu.append("Olá, O ").append(ages[0].getBancoId()).append(" possui ").append(ages.length).append(" agência(s).\n");
        menu.append("Por favor, confirme qual agência deseja utilizar.\n\n");

        for (int i = 0; i < ages.length; i++) {
            menu.append("[").append(i + 1).append("] - ").append(ages[i].getNome()).append("\n");
        }
        menu.append("\n[0] - Voltar\n");

        String input = JOptionPane.showInputDialog(menu.toString());
        return input != null ? Integer.parseInt(input) - 1 : -1;
    }

    private static String[] ACESSAR_CONTA() {
        return new String[]{
                JOptionPane.showInputDialog("Agência: "),
                JOptionPane.showInputDialog("CPF: "),
                JOptionPane.showInputDialog("Número da conta: "),
                JOptionPane.showInputDialog("Senha de acesso: ")
        };
    }

    private static String[] DEPOSITAR() {
        return new String[]{
                JOptionPane.showInputDialog("Agência: "),
                JOptionPane.showInputDialog("Tipo da conta (1 - Corrente, 2 - Poupança): "),
                JOptionPane.showInputDialog("Número da conta: "),
                JOptionPane.showInputDialog("Informe o valor do depósito: ")
        };
    }

    private static void INFO() {
        String info = "### Informações do Sistema ###\n" +
                "[Tipo] [Valor] => Significado\n\n" +
                "[1] [Corrente] => Conta Corrente: conta destinada ao dia a dia, com possibilidade de cheques e cartões.\n" +
                "[2] [Poupança] => Conta Poupança: conta destinada a poupança, com rendimento sobre o valor aplicado.\n" +
                "[3] [Investimento] => Conta de Investimento: conta destinada a aplicações financeiras, com foco em rentabilidade.\n" +
                "[4] [Empréstimo] => Empréstimos: serviços de crédito pessoal e financiamento.\n" +
                "[5] [Cartão] => Cartão de Crédito: serviços relacionados a cartões de crédito e suas funcionalidades.\n" +
                "\nUtilize os serviços conforme suas necessidades e lembre-se de verificar as taxas e regras de cada tipo de conta!";
        JOptionPane.showMessageDialog(null, info);
    }

    private static void BYE() {
        JOptionPane.showMessageDialog(null, "Obrigado por usar o Banco Seu. Até logo!");
    }
}
