package Tarefas.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// CLASSE "Tarefa" PARA GERENCIAR AS TAREFAS DO SISTEMA
public class Tarefa {

    // ENUM PARA GERENCIAR O STATUS DAS TAREFAS
    public enum StatusTarefa {
        PENDENTE,
        ATRASADA,
        CONCLUIDA
    }

    // ATRIBUTOS PRIVADOS DA CLASSE TAREFA
    private int id;
    private String titulo;
    private String descricao;
    private StatusTarefa status;
    private LocalDateTime dataCriacao;
    private int usuarioId;

    // CONSTRUTOR PARA CRIAR NOVAS TAREFAS
    public Tarefa(String titulo, String descricao, int usuarioId) {
        this.setTitulo(titulo);
        this.setDescricao(descricao);
        this.usuarioId = usuarioId;
        this.status = StatusTarefa.PENDENTE;
        this.dataCriacao = LocalDateTime.now();
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

    // GETTER E SETTER DESCRIÇÃO
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        if (descricao == null || descricao.isBlank()) throw new IllegalArgumentException("DESCRIÇÃO DA TAREFA NÃO PODE SER VAZIA.");
        this.descricao = descricao.trim();
    }

    // GETTER E SETTER STATUS
    public StatusTarefa getStatus() {
        return status;
    }
    public void setStatus(StatusTarefa status) {
        if (status == null) throw new IllegalArgumentException("STATUS DA TAREFA NÃO PODE SER NULO.");
        this.status = status;
    }

    // GETTER E SETTER DATA DE CRIAÇÃO
    public String getDataCriacao() {
        return dataCriacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }
    public void setDataCriacao(LocalDateTime dataCriacao) {
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