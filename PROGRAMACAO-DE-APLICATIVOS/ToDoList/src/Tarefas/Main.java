package Tarefas;

import Tarefas.controller.TarefaController;
import Tarefas.controller.UsuarioController;
import Tarefas.model.Tarefa;
import Tarefas.model.Usuario;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {

    // ============================================================
    // ATRIBUTOS GLOBAIS DA CLASSE
    // ============================================================
    private static final Scanner scanner = new Scanner(System.in);
    private static final UsuarioController usuarioController = new UsuarioController();
    private static final TarefaController tarefaController = new TarefaController();
    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    // Guarda o usuário logado durante a sessão
    private static Usuario usuarioLogado = null;

    // ============================================================
    // PONTO DE ENTRADA DA APLICAÇÃO
    // ============================================================
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════╗");
        System.out.println("║       BEM-VINDO À TODOLIST         ║");
        System.out.println("╚════════════════════════════════════╝");

        menuInicial();

        System.out.println("\n👋 Até logo!");
        scanner.close();
    }

    // ============================================================
    // MENU INICIAL — pra usuários NÃO logados
    // ============================================================
    private static void menuInicial() {
        while (usuarioLogado == null) {
            System.out.println("\n┌─────────── MENU ───────────┐");
            System.out.println("│ 1. Cadastrar novo usuário  │");
            System.out.println("│ 2. Fazer login             │");
            System.out.println("│ 0. Sair                    │");
            System.out.println("└────────────────────────────┘");
            System.out.print("Escolha: ");

            String opcao = scanner.nextLine().trim();

            switch (opcao) {
                case "1":
                    cadastrar();
                    break;
                case "2":
                    fazerLogin();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("⚠️ Opção inválida.");
            }
        }

        // Quando o usuário fizer login com sucesso, entra no menu principal
        menuPrincipal();
    }

    // ============================================================
    // CADASTRAR NOVO USUÁRIO
    // ============================================================
    private static void cadastrar() {
        System.out.println("\n--- CADASTRO ---");

        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();

        System.out.print("E-mail: ");
        String email = scanner.nextLine().trim();

        System.out.print("Senha: ");
        String senha = scanner.nextLine().trim();

        usuarioController.cadastrar(nome, email, senha);
    }

    // ============================================================
    // FAZER LOGIN
    // ============================================================
    private static void fazerLogin() {
        System.out.println("\n--- LOGIN ---");

        System.out.print("E-mail: ");
        String email = scanner.nextLine().trim();

        System.out.print("Senha: ");
        String senha = scanner.nextLine().trim();

        Usuario u = usuarioController.login(email, senha);
        if (u != null) {
            usuarioLogado = u;
        }
    }

    // ============================================================
    // MENU PRINCIPAL — pra usuários LOGADOS
    // ============================================================
    private static void menuPrincipal() {
        while (usuarioLogado != null) {
            System.out.println("\n┌─────── TODOLIST ───────┐");
            System.out.println("│ 1. Adicionar tarefa    │");
            System.out.println("│ 2. Listar todas        │");
            System.out.println("│ 3. Filtrar por status  │");
            System.out.println("│ 4. Marcar CONCLUÍDA    │");
            System.out.println("│ 5. Marcar ATRASADA     │");
            System.out.println("│ 6. Marcar PENDENTE     │");
            System.out.println("│ 7. Editar tarefa       │");
            System.out.println("│ 8. Remover tarefa      │");
            System.out.println("│ 0. Sair (logout)       │");
            System.out.println("└────────────────────────┘");
            System.out.println("Usuário: " + usuarioLogado.getNome());
            System.out.print("Escolha: ");

            String opcao = scanner.nextLine().trim();

            switch (opcao) {
                case "1":
                    adicionarTarefa();
                    break;
                case "2":
                    listarTarefas();
                    break;
                case "3":
                    filtrarPorStatus();
                    break;
                case "4":
                    mudarStatus(Tarefa.StatusTarefa.CONCLUIDA);
                    break;
                case "5":
                    mudarStatus(Tarefa.StatusTarefa.ATRASADA);
                    break;
                case "6":
                    mudarStatus(Tarefa.StatusTarefa.PENDENTE);
                    break;
                case "7":
                    editarTarefa();
                    break;
                case "8":
                    removerTarefa();
                    break;
                case "0":
                    System.out.println("\n👋 Logout efetuado.");
                    usuarioLogado = null;
                    break;
                default:
                    System.out.println("⚠️ Opção inválida.");
            }
        }
    }

    // ============================================================
    // ADICIONAR TAREFA
    // ============================================================
    private static void adicionarTarefa() {
        System.out.println("\n--- NOVA TAREFA ---");

        System.out.print("Título: ");
        String titulo = scanner.nextLine().trim();

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine().trim();

        tarefaController.adicionar(titulo, descricao, usuarioLogado.getId());
    }

    // ============================================================
    // LISTAR TODAS AS TAREFAS DO USUÁRIO
    // ============================================================
    private static void listarTarefas() {
        List<Tarefa> tarefas = tarefaController.listar(usuarioLogado.getId());
        exibirLista(tarefas, "TODAS AS TAREFAS");
    }

    // ============================================================
    // FILTRAR TAREFAS POR STATUS
    // ============================================================
    private static void filtrarPorStatus() {
        System.out.println("\nFiltrar por:");
        System.out.println("1. PENDENTE");
        System.out.println("2. ATRASADA");
        System.out.println("3. CONCLUIDA");
        System.out.print("Escolha: ");

        String opcao = scanner.nextLine().trim();
        Tarefa.StatusTarefa statusEscolhido;

        switch (opcao) {
            case "1":
                statusEscolhido = Tarefa.StatusTarefa.PENDENTE;
                break;
            case "2":
                statusEscolhido = Tarefa.StatusTarefa.ATRASADA;
                break;
            case "3":
                statusEscolhido = Tarefa.StatusTarefa.CONCLUIDA;
                break;
            default:
                System.out.println("⚠️ Opção inválida.");
                return;
        }

        List<Tarefa> filtradas = tarefaController.listarPorStatus(usuarioLogado.getId(), statusEscolhido);
        exibirLista(filtradas, "TAREFAS " + statusEscolhido);
    }

    // ============================================================
    // MUDAR STATUS DE UMA TAREFA
    // ============================================================
    private static void mudarStatus(Tarefa.StatusTarefa novoStatus) {
        int id = pedirId("Digite o ID da tarefa: ");
        if (id == -1) return;
        tarefaController.atualizarStatus(id, novoStatus);
    }

    // ============================================================
    // EDITAR TÍTULO E DESCRIÇÃO
    // ============================================================
    private static void editarTarefa() {
        int id = pedirId("Digite o ID da tarefa a editar: ");
        if (id == -1) return;

        System.out.print("Novo título: ");
        String novoTitulo = scanner.nextLine().trim();

        System.out.print("Nova descrição: ");
        String novaDescricao = scanner.nextLine().trim();

        tarefaController.atualizar(id, novoTitulo, novaDescricao);
    }

    // ============================================================
    // REMOVER TAREFA (com confirmação)
    // ============================================================
    private static void removerTarefa() {
        int id = pedirId("Digite o ID da tarefa a remover: ");
        if (id == -1) return;

        System.out.print("⚠️ Tem certeza? (s/n): ");
        String resposta = scanner.nextLine().trim().toLowerCase();

        if (resposta.equals("s")) {
            tarefaController.remover(id);
        } else {
            System.out.println("Operação cancelada.");
        }
    }

    // ============================================================
    // MÉTODO AUXILIAR — pedir ID com tratamento de erro
    // ============================================================
    private static int pedirId(String mensagem) {
        System.out.print(mensagem);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("⚠️ ID inválido. Digite apenas números.");
            return -1;
        }
    }

    // ============================================================
    // MÉTODO AUXILIAR — exibir uma lista de tarefas formatada
    // ============================================================
    private static void exibirLista(List<Tarefa> tarefas, String titulo) {
        System.out.println("\n═══ " + titulo + " ═══");

        if (tarefas.isEmpty()) {
            System.out.println("(nenhuma tarefa encontrada)");
            return;
        }

        for (Tarefa t : tarefas) {
            System.out.println("─────────────────────────");
            System.out.println("ID:        " + t.getId());
            System.out.println("Título:    " + t.getTitulo());
            System.out.println("Descrição: " + (t.getDescricao() != null ? t.getDescricao() : "(sem descrição)"));
            System.out.println("Status:    " + t.getStatus());
            System.out.println("Criada em: " + t.getDataCriacao().format(FORMATO_DATA));
        }
        System.out.println("─────────────────────────");
        System.out.println("Total: " + tarefas.size() + " tarefa(s)");
    }
}