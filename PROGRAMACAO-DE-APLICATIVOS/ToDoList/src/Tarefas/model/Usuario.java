package Tarefas.model;

// CLASSE "Usuario" PARA GERENCIAR OS USUÁRIOS DO SISTEMA
public class Usuario {

    // ATRIBUTOS PRIVADOS DA CLASSE USUÁRIO
    private int id;
    private String nome;
    private String email;
    private String senha;

    // ATRIBUTOS AUXILIARES PARA EXIBIÇÃO
    public Usuario (String nome, String login, String senha) {
        this.setNome(nome);
        this.setEmail(email);
        this.setSenha(senha);
    }

    // GETTER E SETTER ID
    public int getId() {
        return id;
    }
    public void setId (int id){
        if (id <= 0) throw new IllegalArgumentException("iD DEVE SER POSITIVO.");
        this.id = id;
    }

    // GETTER E SETTER NOME
    public String getNome() {
        return nome;
    }
    public void setNome (String nome){
        if (nome ==null || nome.isBlank()) throw new IllegalArgumentException("NOME DO USUÁRIO NÃO PODE SER VAZIO.");
        this.nome = nome.trim();
    }

    // GETTER E SETTER LOGIN
    public String getEmail() {
        return email;
    }
    public void setEmail (String email){
        if (email ==null || email.isBlank()) throw new IllegalArgumentException("LOGIN DO USUÁRIO NÃO PODE SER VAZIO.");
        this.email = email.trim();
    }

    // GETTER E SETTER SENHA
    public String getSenha() {
        return senha;
    }
    public void setSenha (String senha){
        if (senha ==null || senha.isBlank()) throw new IllegalArgumentException("SENHA DO USUÁRIO NÃO PODE SER VAZIA.");
        this.senha = senha.trim();
    }

    // GE
}
