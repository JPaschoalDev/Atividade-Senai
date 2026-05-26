package Tarefas.controller;

import Tarefas.dao.UsuarioDAO;
import Tarefas.model.Usuario;
import java.sql.SQLException;

/**
 * CONTROLLER DO USUÁRIO
 * Responsável por receber os dados da View, aplicar
 * as regras de negócio e delegar ao DAO as operações no banco.
 */
public class UsuarioController {

    // INSTÂNCIA DO DAO — É ATRAVÉS DELA QUE ACESSAMOS O BANCO
    // "final" garante que essa referência nunca será substituída
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    /**
     * CADASTRA UM NOVO USUÁRIO NO SISTEMA
     * @param nome  — nome digitado pelo usuário
     * @param email — email digitado pelo usuário
     * @param senha — senha digitada pelo usuário
     * @return true se cadastrou com sucesso, false se falhou
     */
    public boolean cadastrar(String nome, String email, String senha) {
        try {

            // VERIFICA SE O EMAIL JÁ EXISTE NO BANCO ANTES DE TENTAR CADASTRAR
            // Evita duplicidade — o banco também tem UNIQUE, mas é boa prática verificar antes
            if (usuarioDAO.emailJaExiste(email)) {
                System.out.println("❌ E-mail já cadastrado!");
                return false;
            }

            // CRIA O OBJETO USUÁRIO COM OS DADOS RECEBIDOS
            // O construtor do model já valida os dados e define a dataCriacao automaticamente
            Usuario usuario = new Usuario(nome, email, senha);

            // DELEGA AO DAO A TAREFA DE SALVAR NO BANCO
            usuarioDAO.inserir(usuario);

            // SE CHEGOU AQUI, TUDO DEU CERTO
            return true;

        } catch (IllegalArgumentException e) {
            // CAPTURA ERROS DE VALIDAÇÃO DOS SETTERS DO MODEL
            // Exemplo: nome vazio, email em branco, senha nula
            System.out.println("❌ Erro de validação: " + e.getMessage());
            return false;

        } catch (SQLException e) {
            // CAPTURA ERROS DE BANCO DE DADOS
            // Exemplo: conexão perdida, erro no INSERT
            System.out.println("❌ Erro ao cadastrar usuário: " + e.getMessage());
            return false;
        }
    }

    /**
     * REALIZA O LOGIN DO USUÁRIO
     * @param email — email digitado na tela de login
     * @param senha — senha digitada na tela de login
     * @return objeto Usuario se encontrado, null se não encontrado
     */
    public Usuario login(String email, String senha) {
        try {

            // PEDE AO DAO QUE BUSQUE NO BANCO UM USUÁRIO COM ESSE EMAIL E SENHA
            // O DAO faz o SELECT e retorna o objeto montado, ou null se não achar
            Usuario usuario = usuarioDAO.buscarPorEmailESenha(email, senha);

            // SE O DAO RETORNOU NULL, AS CREDENCIAIS ESTÃO ERRADAS
            if (usuario == null) {
                System.out.println("❌ E-mail ou senha incorretos!");
                return null;
            }

            // SE ENCONTROU O USUÁRIO, EXIBE BOAS-VINDAS E RETORNA O OBJETO
            // Esse objeto será usado pelo resto da aplicação para saber quem está logado
            System.out.println("✅ Bem-vindo, " + usuario.getNome() + "!");
            return usuario;

        } catch (SQLException e) {
            // CAPTURA ERROS DE BANCO DURANTE A BUSCA
            System.out.println("❌ Erro ao realizar login: " + e.getMessage());
            return null;
        }
    }
}