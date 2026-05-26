package Tarefas.controller;

import Tarefas.dao.TarefaDAO;
import Tarefas.model.Tarefa;
import Tarefas.model.Tarefa.StatusTarefa;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * CONTROLLER DA TAREFA
 * Responsável por receber os dados da View, aplicar
 * as regras de negócio e delegar ao DAO as operações no banco.
 */
public class TarefaController {

    // INSTÂNCIA DO DAO — É ATRAVÉS DELA QUE ACESSAMOS O BANCO
    // "final" garante que essa referência nunca será substituída
    private final TarefaDAO tarefaDAO = new TarefaDAO();

    /**
     * ADICIONA UMA NOVA TAREFA NO SISTEMA
     * @param titulo    — título digitado pelo usuário
     * @param descricao — descrição digitada pelo usuário
     * @param usuarioId — id do usuário logado (dono da tarefa)
     * @return true se adicionou com sucesso, false se falhou
     */
    public boolean adicionar(String titulo, String descricao, int usuarioId) {
        try {

            // CRIA O OBJETO TAREFA COM OS DADOS RECEBIDOS
            // O construtor já define status=PENDENTE e dataCriacao automaticamente
            Tarefa tarefa = new Tarefa(titulo, descricao, usuarioId);

            // DELEGA AO DAO A TAREFA DE SALVAR NO BANCO
            tarefaDAO.inserir(tarefa);

            System.out.println("✅ Tarefa adicionada com sucesso!");
            return true;

        } catch (IllegalArgumentException e) {
            // CAPTURA ERROS DE VALIDAÇÃO DOS SETTERS DO MODEL
            // Exemplo: título vazio, descrição em branco
            System.out.println("❌ Erro de validação: " + e.getMessage());
            return false;

        } catch (SQLException e) {
            // CAPTURA ERROS DE BANCO DE DADOS
            // Exemplo: conexão perdida, erro no INSERT
            System.out.println("❌ Erro ao adicionar tarefa: " + e.getMessage());
            return false;
        }
    }

    /**
     * LISTA TODAS AS TAREFAS DO USUÁRIO LOGADO
     * @param usuarioId — id do usuário logado
     * @return lista de tarefas do usuário, ou lista vazia se der erro
     */
    public List<Tarefa> listar(int usuarioId) {
        try {

            // PEDE AO DAO QUE BUSQUE NO BANCO TODAS AS TAREFAS DESSE USUÁRIO
            // O filtro por usuarioId garante que cada usuário vê só as suas tarefas
            return tarefaDAO.listarPorUsuario(usuarioId);

        } catch (SQLException e) {
            // SE DER ERRO, RETORNA LISTA VAZIA PARA NÃO QUEBRAR A APLICAÇÃO
            System.out.println("❌ Erro ao listar tarefas: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    /**
     * ATUALIZA O TÍTULO E DESCRIÇÃO DE UMA TAREFA
     * @param idTarefa      — id da tarefa a ser atualizada
     * @param novoTitulo    — novo título digitado pelo usuário
     * @param novaDescricao — nova descrição digitada pelo usuário
     * @return true se atualizou com sucesso, false se falhou
     */
    public boolean atualizar(int idTarefa, String novoTitulo, String novaDescricao) {
        try {

            // DELEGA AO DAO A ATUALIZAÇÃO DOS DADOS NO BANCO
            tarefaDAO.atualizar(idTarefa, novoTitulo, novaDescricao);

            System.out.println("✅ Tarefa atualizada com sucesso!");
            return true;

        } catch (IllegalArgumentException e) {
            // CAPTURA ERROS DE VALIDAÇÃO
            // Exemplo: novo título vazio
            System.out.println("❌ Erro de validação: " + e.getMessage());
            return false;

        } catch (SQLException e) {
            // CAPTURA ERROS DE BANCO DURANTE O UPDATE
            System.out.println("❌ Erro ao atualizar tarefa: " + e.getMessage());
            return false;
        }
    }

    /**
     * ATUALIZA APENAS O STATUS DE UMA TAREFA
     * @param idTarefa   — id da tarefa a ter o status alterado
     * @param novoStatus — novo status: PENDENTE, ATRASADA ou CONCLUIDA
     * @return true se atualizou com sucesso, false se falhou
     */
    public boolean atualizarStatus(int idTarefa, StatusTarefa novoStatus) {
        try {

            // DELEGA AO DAO A ATUALIZAÇÃO DO STATUS NO BANCO
            // O status é do tipo enum — garante que só valores válidos são aceitos
            tarefaDAO.atualizarStatus(idTarefa, novoStatus);

            System.out.println("✅ Status atualizado para: " + novoStatus);
            return true;

        } catch (SQLException e) {
            // CAPTURA ERROS DE BANCO DURANTE O UPDATE DE STATUS
            System.out.println("❌ Erro ao atualizar status: " + e.getMessage());
            return false;
        }
    }

    /**
     * REMOVE UMA TAREFA DO SISTEMA
     * @param idTarefa — id da tarefa a ser removida
     * @return true se removeu com sucesso, false se falhou
     */
    public boolean remover(int idTarefa) {
        try {

            // DELEGA AO DAO A REMOÇÃO DA TAREFA NO BANCO
            tarefaDAO.remover(idTarefa);

            System.out.println("✅ Tarefa removida com sucesso!");
            return true;

        } catch (SQLException e) {
            // CAPTURA ERROS DE BANCO DURANTE O DELETE
            System.out.println("❌ Erro ao remover tarefa: " + e.getMessage());
            return false;
        }
    }

    /**
     * LISTA AS TAREFAS POR STATUS
     * @param usuarioId — id do usuário logado
     * @param status — status para filtrar (PENDENTE, ATRASADA ou CONCLUIDA)
     * @return lista de tarefas filtradas, ou lista vazia se der erro
     */
    public List<Tarefa> listarPorStatus(int usuarioId, Tarefa.StatusTarefa status) {
        try {
            return tarefaDAO.listarPorStatus(usuarioId, status);
        } catch (SQLException e) {
            System.out.println("❌ Erro ao filtrar tarefas: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}