package Clientes;

import ContaBancaria.ContaBancaria;

public class Cliente {

    private String cpf;
    private String nome;
    private String[] telefone;
    private String[] email;
    private ContaBancaria[] contas;

    public Cliente(String cpf, String nome, String telefone, String email) {
        this.contas = new ContaBancaria[2];
        this.email = new String[2];
        this.telefone = new String[2];

        this.cpf = cpf;
        this.nome = nome;
        this.telefone[0] = telefone;
        this.email[0] = email;
    }

    public void addConta(ContaBancaria novaConta) {
        int contasLength = this.contas.length;

        // VERIFICAR SE HÁ ESPAÇO NO ARRAY DE CONTAS
        for (int i = 0; i < contasLength; i++) {
            if (this.contas[i] == null) {
                this.contas[i] = novaConta;
                return;
            }
        }

        // EXPANDIR O ARRAY SE NECESSÁRIO (AUMENTAR EM 10% DO TAMANHO ATUAL)
        ContaBancaria[] novoArrayConta = new ContaBancaria[contasLength + 1];
        System.arraycopy(this.contas, 0, novoArrayConta, 0, contasLength);
        novoArrayConta[contasLength] = novaConta;
        this.contas = novoArrayConta;
    }

    public ContaBancaria[] getContas() {
        return this.contas;
    }

    public ContaBancaria buscarConta(int numero) {
        // BUSCANDO A CONTA COM BASE NO NÚMERO
        for (ContaBancaria conta : this.contas) {
            if (conta != null && conta.numero() == numero) {
                return conta;
            }
        }
        return null; // RETORNAR NULO SE A CONTA NÃO FOR ENCONTRADA
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    // Método para adicionar um telefone
    public boolean setTelefone(String telefone) {
        for (int i = 0; i < this.telefone.length; i++) {
            if (this.telefone[i] == null) {
                this.telefone[i] = telefone;
                return true;
            }
        }

        // Se o array estiver cheio, aumentar o tamanho e adicionar o telefone
        String[] novoArrayTelefone = new String[this.telefone.length + 1];
        System.arraycopy(this.telefone, 0, novoArrayTelefone, 0, this.telefone.length);
        novoArrayTelefone[this.telefone.length] = telefone;
        this.telefone = novoArrayTelefone;
        return true;
    }

    // Método para definir um telefone em um índice específico
    public boolean setTelefone(String telefone, int index) {
        if (index >= 0 && index < this.telefone.length) {
            this.telefone[index] = telefone;
            return true;
        }
        return false;
    }

    // Método para obter todos os telefones
    public String[] getTelefone() {
        return this.telefone;
    }

    // Método para contar quantos telefones estão cadastrados
    public int getQuantidadeTelefones() {
        int contador = 0;
        for (String tel : this.telefone) {
            if (tel != null) {
                contador++;
            }
        }
        return contador;
    }

    // Método para adicionar um email
    public boolean setEmail(String email) {
        for (int i = 0; i < this.email.length; i++) {
            if (this.email[i] == null) {
                this.email[i] = email;
                return true;
            }
        }

        // Se o array estiver cheio, aumentar o tamanho e adicionar o email
        String[] novoArrayEmail = new String[this.email.length + 1];
        System.arraycopy(this.email, 0, novoArrayEmail, 0, this.email.length);
        novoArrayEmail[this.email.length] = email;
        this.email = novoArrayEmail;
        return true;
    }

    // Método para definir um email em um índice específico
    public boolean setEmail(String email, int index) {
        if (index >= 0 && index < this.email.length) {
            this.email[index] = email;
            return true;
        }
        return false;
    }

    // Método para obter todos os emails
    public String[] getEmail() {
        return this.email;
    }

    // Método para contar quantos emails estão cadastrados
    public int getQuantidadeEmails() {
        int contador = 0;
        for (String em : this.email) {
            if (em != null) {
                contador++;
            }
        }
        return contador;
    }
}
