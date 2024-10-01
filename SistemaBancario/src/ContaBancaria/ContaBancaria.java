package ContaBancaria;

public class ContaBancaria {
    private int numero;
    private double saldo;

    public ContaBancaria(int numero) {
        this.numero = numero;
        this.saldo = 0.0; // O saldo inicial é zero
    }

    public void depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
            System.out.println("Depósito de R$" + valor + " realizado com sucesso.");
        } else {
            System.out.println("Valor inválido para depósito.");
        }
    }

    public void sacar(double valor) {
        if (valor > 0 && valor <= this.saldo) {
            this.saldo -= valor;
            System.out.println("Saque de R$" + valor + " realizado com sucesso.");
        } else {
            System.out.println("Saldo insuficiente ou valor inválido.");
        }
    }

    public double getSaldo() {
        return this.saldo;
    }

    public int numero() {
        return this.numero;
    }
}
