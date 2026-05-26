package Tarefas.model;

import java.time.LocalDateTime;

// CLASSE "Usuario" PARA GERENCIAR OS USUÁRIOS DO SISTEMA
public class Usuario {

    // ATRIBUTOS PRIVADOS DA CLASSE USUÁRIO
    private int id;
    private String nome;
    private String email;
    private String senha;
    private LocalDateTime dataCriacao;

    // CONSTRUTOR PARA CRIAR NOVOS USUÁRIOS
    public Usuario(String nome, String email, String senha) {
        this.setNome(nome);
        this.setEmail(email);
        this.setSenha(senha);
        this.dataCriacao = LocalDateTime.now();
    }

    // CONSTRUTOR VAZIO PARA USO EM CASOS ESPECÍFICOS
    public Usuario() {
    }

    // GETTER E SETTER ID
    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("iD DEVE SER POSITIVO.");
        this.id = id;
    }

    // GETTER E SETTER NOME
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("NOME DO USUÁRIO NÃO PODE SER VAZIO.");
        this.nome = nome.trim();
    }

    // GETTER E SETTER EMAIL
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.isBlank())
            throw new IllegalArgumentException("E-MAIL DO USUÁRIO NÃO PODE SER VAZIO.");
        this.email = email.trim();
    }

    // GETTER E SETTER SENHA
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if (senha == null || senha.isBlank())
            throw new IllegalArgumentException("SENHA DO USUÁRIO NÃO PODE SER VAZIA.");
        this.senha = senha.trim();
    }

    // GETTER E SETTER DATA DE CRIAÇÃO
    public String getDataCriacao() {
        return dataCriacao.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
