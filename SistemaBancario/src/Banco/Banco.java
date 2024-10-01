package Banco;

import AgenciaeAtendente.Agencia;

public class Banco {
    private String nome;
    private Agencia[] agencias;

    public Banco(String nome) {
        this.nome = nome;
        this.agencias = new Agencia[2]; // Exemplo com 2 agências
        // Inicialização de agências
        this.agencias[0] = new Agencia("Agência Centro", this, 1);
        this.agencias[1] = new Agencia("Agência Sul", this, 2);
    }

    public String getNome() {
        return nome;
    }

    public Agencia[] getAgencias() {
        return agencias;
    }

    public Agencia acessarAgencia(int index) {
        if (index >= 0 && index < agencias.length) {
            return agencias[index];
        } else {
            System.out.println("Agência não encontrada.");
            return null;
        }
    }
}
