package Tarefas.model;

// CLASSE "Tarefa" PARA GERENCIAR AS TAREFAS DO SISTEMA
public class Tarefa {

    // ATRIBUTOS PRIVADOS DA CLASSE TAREFA
    private int id;
    private String titulo;
    private boolean concluida;
    private String dataCriacao;
    private int usuarioId;

    // CONSTRUTOR PARA CRIAR NOVAS TAREFAS
    public Tarefa(String titulo, int usuarioId) {
        this.titulo = titulo;
        this.usuarioId = usuarioId;
        this.concluida = false;
        this.dataCriacao = java.time.LocalDateTime.now().toString();
    }

    // CONSTRUTOR VAZIO PARA USO EM CASOS ESPECÍFICOS
    public Tarefa() {
    }

    // GETTER E SETTER ID
    public int getId() {
        return id;
    }
    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID DA TAREFA NÃO PODE SER NEGATIVO.");
        this.id = id;
    }

    // GETTER E SETTER TÍTULO
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) throw new IllegalArgumentException("TÍTULO DA TAREFA NÃO PODE SER VAZIO.");
        this.titulo = titulo.trim();
    }

    // GETTER E SETTER CONCLUÍDA
    public boolean isConcluida() {
        return concluida;
    }
    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    // GETTER E SETTER DATA DE CRIAÇÃO
    public String getDataCriacao() {
        return dataCriacao;
    }
    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    // GETTER E SETTER ID DO USUÁRIO
    public int getUsuarioId() {
        return usuarioId;
    }
    public void setUsuarioId(int usuarioId) {
        if (usuarioId <= 0) throw new IllegalArgumentException("ID DO USUÁRIO NÃO PODE SER NEGATIVO.");
        this.usuarioId = usuarioId;
    }
}
