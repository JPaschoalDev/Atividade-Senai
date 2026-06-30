import java.util.Random;
import java.util.Scanner;

/**
 * ============================================================================
 *  LISTA DE EXERCÍCIOS — LÓGICA DE PROGRAMAÇÃO
 * ----------------------------------------------------------------------------
 *  Programa único de console em Java contendo a resolução dos 12 exercícios.
 *  Cada exercício é um metodo estático, chamado a partir do menu principal
 *  através de um switch-case que roda em loop até o usuário digitar 0.
 *
 *  Organização:
 *    - Utilitários de entrada (lerInteiro / lerDouble / lerTexto)
 *    - Utilitários visuais (caixa de menu, títulos, separadores)
 *    - Métodos exercicio01 ... exercicio12
 *    - Classes auxiliares (registros): Aluno e Contato
 * ============================================================================
 */
public class Main {
    // Scanner único compartilhado para o programa inteiro.
    private static final Scanner scanner = new Scanner(System.in);

    // Largura interna padrão das "caixas" desenhadas no console.
    private static final int LARGURA = 54;

    // ------------------------------------------------------------------------
    //  METODO PRINCIPAL
    // ------------------------------------------------------------------------
    public static void main(String[] args) {
        int opcao;

        // do-while garante que o menu apareça ao menos uma vez antes de testar.
        do {
            exibirMenuPrincipal();
            opcao = lerInteiro("Escolha uma opção: ");

            // O switch direciona para o exercício escolhido.
            switch (opcao) {
                case 1:  exercicio01(); break;
                case 2:  exercicio02(); break;
                case 3:  exercicio03(); break;
                case 4:  exercicio04(); break;
                case 5:  exercicio05(); break;
                case 6:  exercicio06(); break;
                case 7:  exercicio07(); break;
                case 8:  exercicio08(); break;
                case 9:  exercicio09(); break;
                case 10: exercicio10(); break;
                case 11: exercicio11(); break;
                case 12: exercicio12(); break;
                case 0:
                    System.out.println("\nEncerrando o programa. Até logo!");
                    break;
                default:
                    System.out.println("\n  [!] Opção inválida. Escolha um número de 0 a 12.");
            }

            // Pausa entre um exercício e a volta ao menu (exceto ao sair).
            if (opcao != 0) {
                pausar();
            }

        } while (opcao != 0);

        scanner.close();
    }

    // ========================================================================
    //  EXERCÍCIO 1 — Calculadora de média escolar
    //  Lê as notas dos 4 bimestres e classifica: aprovado / recuperação /
    //  reprovado. Os parênteses na soma garantem a precedência correta.
    // ========================================================================
    private static void exercicio01() {
        titulo("Exercício 1 — Calculadora de média escolar");

        double b1 = lerDouble("Nota do 1º bimestre: ");
        double b2 = lerDouble("Nota do 2º bimestre: ");
        double b3 = lerDouble("Nota do 3º bimestre: ");
        double b4 = lerDouble("Nota do 4º bimestre: ");

        // Parênteses obrigatórios: soma primeiro, divisão depois.
        double media = (b1 + b2 + b3 + b4) / 4.0;

        System.out.printf("%nMédia final: %.2f%n", media);

        if (media >= 7.0) {
            System.out.println("Situação: APROVADO");
        } else if (media >= 5.0) {
            System.out.println("Situação: RECUPERAÇÃO");
        } else {
            System.out.println("Situação: REPROVADO");
        }
    }

    // ========================================================================
    //  EXERCÍCIO 2 — Tabuada interativa
    //  Exibe a tabuada de 1 a 10 do número informado usando um laço for.
    // ========================================================================
    private static void exercicio02() {
        titulo("Exercício 2 — Tabuada interativa");

        int numero = lerInteiro("Digite um número para ver a tabuada: ");

        System.out.println();
        // O laço vai de 1 a 10 (condição i <= 10 inclui o 10).
        for (int i = 1; i <= 10; i++) {
            System.out.printf("%2d x %2d = %3d%n", numero, i, numero * i);
        }
    }

    // ========================================================================
    //  EXERCÍCIO 3 — Pares e ímpares em sequência
    //  Lê 10 inteiros e conta quantos são pares e quantos são ímpares.
    //  Os contadores são inicializados ANTES do laço (zero), nunca dentro.
    // ========================================================================
    private static void exercicio03() {
        titulo("Exercício 3 — Contagem de pares e ímpares");

        int pares = 0;
        int impares = 0;

        for (int i = 1; i <= 10; i++) {
            int n = lerInteiro("Digite o " + i + "º número inteiro: ");

            // Operador módulo: resto da divisão por 2.
            if (n % 2 == 0) {
                pares++;
            } else {
                impares++;
            }
        }

        System.out.printf("%nQuantidade de pares:   %d%n", pares);
        System.out.printf("Quantidade de ímpares: %d%n", impares);
    }

    // ========================================================================
    //  EXERCÍCIO 4 — Conversor de temperatura
    //  Menu para converter Celsius -> Fahrenheit ou Celsius -> Kelvin.
    //  A opção é validada antes de processar.
    // ========================================================================
    private static void exercicio04() {
        titulo("Exercício 4 — Conversor de temperatura");

        System.out.println("1 - Celsius para Fahrenheit");
        System.out.println("2 - Celsius para Kelvin");
        int opcao = lerInteiro("Escolha o tipo de conversão: ");

        // Valida a opção ANTES de pedir a temperatura.
        if (opcao != 1 && opcao != 2) {
            System.out.println("\n  [!] Opção inválida. Operação cancelada.");
            return;
        }

        double celsius = lerDouble("Digite a temperatura em Celsius: ");

        switch (opcao) {
            case 1:
                double fahrenheit = (celsius * 9.0 / 5.0) + 32.0; // Cálculo de fahrenheit
                System.out.printf("%n%.2f °C equivalem a %.2f °F%n", celsius, fahrenheit);
                break;
            case 2:
                double kelvin = celsius + 273.15; // Cálculo de kelvin
                System.out.printf("%n%.2f °C equivalem a %.2f K%n", celsius, kelvin);
                break;
        }
    }

    // ========================================================================
    //  EXERCÍCIO 5 — Fatorial e Fibonacci
    //  Dois subprogramas separados; o usuário escolhe qual executar.
    //  O fatorial é recursivo, com caso base definido antes de tudo.
    // ========================================================================
    private static void exercicio05() {
        titulo("Exercício 5 — Fatorial e Fibonacci");

        System.out.println("1 - Calcular fatorial");
        System.out.println("2 - Exibir sequência de Fibonacci");
        int opcao = lerInteiro("Escolha uma opção: ");

        switch (opcao) {
            case 1:
                int n = lerInteiro("Digite um número para o fatorial: ");
                if (n < 0) {
                    System.out.println("\n  [!] Não existe fatorial de número negativo.");
                } else {
                    System.out.printf("%n%d! = %d%n", n, fatorial(n));
                }
                break;
            case 2:
                int termos = lerInteiro("Quantos termos de Fibonacci exibir? ");
                exibirFibonacci(termos);
                break;
            default:
                System.out.println("\n  [!] Opção inválida.");
        }
    }

    /** Fatorial recursivo. Caso base: 0! e 1! valem 1 (evita loop infinito). */
    private static long fatorial(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * fatorial(n - 1);
    }

    /** Exibe os N primeiros termos da sequência de Fibonacci. */
    private static void exibirFibonacci(int termos) {
        if (termos <= 0) {
            System.out.println("\n  [!] Informe um número de termos maior que zero.");
            return;
        }

        long anterior = 0;
        long atual = 1;

        System.out.print("\nFibonacci: ");
        for (int i = 1; i <= termos; i++) {
            System.out.print(anterior + (i < termos ? ", " : ""));
            long proximo = anterior + atual;
            anterior = atual;
            atual = proximo;
        }
        System.out.println();
    }

    // ========================================================================
    //  EXERCÍCIO 6 — Busca em vetor
    //  Preenche um vetor com 15 inteiros e faz busca linear.
    //  Internamente o índice é base zero; ao usuário mostramos posição + 1.
    // ========================================================================
    private static void exercicio06() {
        titulo("Exercício 6 — Busca linear em vetor");

        int[] vetor = new int[15];

        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = lerInteiro("Digite o " + (i + 1) + "º valor: ");
        }

        int procurado = lerInteiro("\nQual número deseja procurar? ");

        int posicao = -1; // -1 indica "não encontrado".
        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] == procurado) {
                posicao = i;
                break; // para na primeira ocorrência.
            }
        }

        if (posicao == -1) {
            System.out.printf("%nO número %d NÃO está no vetor.%n", procurado);
        } else {
            // posicao + 1 deixa a contagem mais intuitiva para o usuário.
            System.out.printf("%nO número %d foi encontrado na posição %d.%n",
                    procurado, posicao + 1);
        }
    }

    // ========================================================================
    //  EXERCÍCIO 7 — Ordenação manual (Bubble Sort)
    //  Ordena 10 inteiros em ordem crescente sem usar métodos prontos.
    //  A troca usa uma variável auxiliar temporária.
    // ========================================================================
    private static void exercicio07() {
        titulo("Exercício 7 — Ordenação Bubble Sort");

        int[] vetor = new int[10];
        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = lerInteiro("Digite o " + (i + 1) + "º número: ");
        }

        // Bubble Sort: laços aninhados comparando vizinhos.
        for (int i = 0; i < vetor.length - 1; i++) {
            for (int j = 0; j < vetor.length - 1 - i; j++) {
                if (vetor[j] > vetor[j + 1]) {
                    // Troca com variável auxiliar para não perder valor.
                    int aux = vetor[j];
                    vetor[j] = vetor[j + 1];
                    vetor[j + 1] = aux;
                }
            }
        }

        System.out.print("\nVetor ordenado: ");
        for (int i = 0; i < vetor.length; i++) {
            System.out.print(vetor[i] + (i < vetor.length - 1 ? ", " : ""));
        }
        System.out.println();
    }

    // ========================================================================
    //  EXERCÍCIO 8 — Registro de alunos (struct) com filtro e ordenação
    //  Armazena até 5 alunos (nome, matrícula, média).
    //  Primeiro preenche tudo, depois filtra aprovados e ordena por média.
    // ========================================================================
    private static void exercicio08() {
        titulo("Exercício 8 — Registro de alunos");

        Aluno[] alunos = new Aluno[5];

        // Etapa 1: preencher todos os registros.
        for (int i = 0; i < alunos.length; i++) {
            System.out.println("\n--- Aluno " + (i + 1) + " ---");
            String nome = lerTexto("Nome: ");
            String matricula = lerTexto("Matrícula: ");
            double media = lerDouble("Média: ");
            alunos[i] = new Aluno(nome, matricula, media);
        }

        // Etapa 2: ordenar por média (maior para menor) com Bubble Sort.
        for (int i = 0; i < alunos.length - 1; i++) {
            for (int j = 0; j < alunos.length - 1 - i; j++) {
                if (alunos[j].media < alunos[j + 1].media) {
                    Aluno aux = alunos[j];
                    alunos[j] = alunos[j + 1];
                    alunos[j + 1] = aux;
                }
            }
        }

        // Etapa 3: exibir apenas os aprovados (média >= 7).
        System.out.println("\nAlunos aprovados (média ≥ 7), do maior para o menor:");
        boolean encontrouAprovado = false;
        for (Aluno a : alunos) {
            if (a.media >= 7.0) {
                System.out.printf("  %-15s | Matrícula: %-10s | Média: %.2f%n",
                        a.nome, a.matricula, a.media);
                encontrouAprovado = true;
            }
        }

        if (!encontrouAprovado) {
            System.out.println("  (Nenhum aluno foi aprovado.)");
        }
    }

    // ========================================================================
    //  EXERCÍCIO 9 — Jogo da adivinhação com tentativas limitadas
    //  Sorteia 1..100; o usuário tem 7 tentativas; dicas de maior/menor.
    //  Uma variável booleana indica acerto e evita condicionais duplicadas.
    // ========================================================================
    private static void exercicio09() {
        titulo("Exercício 9 — Jogo da adivinhação");

        Random random = new Random();
        int secreto = random.nextInt(100) + 1; // 1 a 100
        int maxTentativas = 7;
        boolean acertou = false;
        int tentativasUsadas = 0;

        System.out.println("Adivinhe o número entre 1 e 100. Você tem 7 tentativas!\n");

        for (int t = 1; t <= maxTentativas; t++) {
            int palpite = lerInteiro("Tentativa " + t + " de " + maxTentativas + ": ");
            tentativasUsadas = t;

            if (palpite == secreto) {
                acertou = true;
                break;
            } else if (palpite < secreto) {
                System.out.println("  O número secreto é MAIOR.");
            } else {
                System.out.println("  O número secreto é MENOR.");
            }
        }

        // Relatório final usando a flag booleana.
        System.out.println("\n----- Relatório -----");
        System.out.println("Número secreto: " + secreto);
        System.out.println("Tentativas usadas: " + tentativasUsadas);
        if (acertou) {
            System.out.println("Resultado: VOCÊ GANHOU! 🎉");
        } else {
            System.out.println("Resultado: VOCÊ PERDEU.");
        }
    }

    // ========================================================================
    //  EXERCÍCIO 10 — Análise de texto
    //  Conta caracteres, vogais, consoantes, espaços e inverte a frase.
    //  A frase é normalizada para minúsculas antes da contagem de vogais.
    // ========================================================================
    private static void exercicio10() {
        titulo("Exercício 10 — Análise de texto");

        String frase = lerTexto("Digite uma frase: ");
        String fraseMinuscula = frase.toLowerCase();

        int vogais = 0;
        int consoantes = 0;
        int espacos = 0;

        for (int i = 0; i < fraseMinuscula.length(); i++) {
            char c = fraseMinuscula.charAt(i);

            if (c == ' ') {
                espacos++;
            } else if (ehVogal(c)) {
                vogais++;
            } else if (Character.isLetter(c)) {
                // É letra, mas não é vogal -> consoante (ç entra aqui).
                consoantes++;
            }
            // Demais símbolos (números, pontuação) são ignorados na classificação.
        }

        // Inverter a frase usando StringBuilder.
        String invertida = new StringBuilder(frase).reverse().toString();

        System.out.printf("%nTotal de caracteres: %d%n", frase.length());
        System.out.printf("Vogais:     %d%n", vogais);
        System.out.printf("Consoantes: %d%n", consoantes);
        System.out.printf("Espaços:    %d%n", espacos);
        System.out.println("Frase invertida: " + invertida);
    }

    /** Considera vogais simples e acentuadas. */
    private static boolean ehVogal(char c) {
        return "aeiouáàâãéêíóôõúü".indexOf(c) >= 0;
    }

    // ========================================================================
    //  EXERCÍCIO 11 — Caixa eletrônico
    //  Valor múltiplo de 10. Calcula a menor quantidade de cédulas usando
    //  algoritmo guloso: da maior denominação para a menor.
    // ========================================================================
    private static void exercicio11() {
        titulo("Exercício 11 — Caixa eletrônico");

        int valor = lerInteiro("Valor a sacar (múltiplo de 10): ");

        if (valor <= 0 || valor % 10 != 0) {
            System.out.println("\n  [!] Valor inválido. Deve ser positivo e múltiplo de 10.");
            return;
        }

        // Denominações em ordem decrescente — a ordem é essencial no guloso.
        int[] cedulas = {100, 50, 20, 10};
        int restante = valor;

        System.out.printf("%nPara sacar R$ %d:%n", valor);
        for (int c : cedulas) {
            int quantidade = restante / c; // divisão inteira
            if (quantidade > 0) {
                System.out.printf("  %2d cédula(s) de R$ %3d%n", quantidade, c);
            }
            restante = restante % c; // sobra para a próxima denominação
        }
    }

    // ========================================================================
    //  EXERCÍCIO 12 — Agenda de contatos (CRUD)
    //  Vetor de registros com cadastrar, listar, buscar e excluir.
    //  Ao excluir, os elementos seguintes são deslocados para não deixar buracos.
    //  Os dados são estáticos: permanecem se você reentrar no exercício 12.
    // ========================================================================
    private static final int CAPACIDADE_AGENDA = 50;
    private static final Contato[] agenda = new Contato[CAPACIDADE_AGENDA];
    private static int totalContatos = 0; // quantos contatos ativos existem

    private static void exercicio12() {
        int opcao;

        do {
            titulo("Exercício 12 — Agenda de contatos");
            System.out.println("1 - Cadastrar contato");
            System.out.println("2 - Listar contatos");
            System.out.println("3 - Buscar por nome");
            System.out.println("4 - Excluir contato");
            System.out.println("0 - Voltar ao menu principal");
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1: cadastrarContato(); break;
                case 2: listarContatos();   break;
                case 3: buscarContato();    break;
                case 4: excluirContato();   break;
                case 0: System.out.println("\nVoltando ao menu principal..."); break;
                default: System.out.println("\n  [!] Opção inválida.");
            }
        } while (opcao != 0);
    }

    /** Cadastra um novo contato no fim do vetor, se houver espaço. */
    private static void cadastrarContato() {
        if (totalContatos >= CAPACIDADE_AGENDA) {
            System.out.println("\n  [!] Agenda cheia.");
            return;
        }
        String nome = lerTexto("Nome: ");
        String telefone = lerTexto("Telefone: ");
        agenda[totalContatos] = new Contato(nome, telefone);
        totalContatos++;
        System.out.println("\nContato cadastrado com sucesso!");
    }

    /** Lista todos os contatos ativos. */
    private static void listarContatos() {
        if (totalContatos == 0) {
            System.out.println("\n  (Agenda vazia.)");
            return;
        }
        System.out.println("\n--- Contatos cadastrados ---");
        for (int i = 0; i < totalContatos; i++) {
            System.out.printf("  %d) %-20s | Tel: %s%n",
                    i + 1, agenda[i].nome, agenda[i].telefone);
        }
    }

    /** Busca o primeiro contato cujo nome corresponde (ignorando maiúsculas). */
    private static void buscarContato() {
        if (totalContatos == 0) {
            System.out.println("\n  (Agenda vazia.)");
            return;
        }
        String alvo = lerTexto("Nome a buscar: ");
        for (int i = 0; i < totalContatos; i++) {
            if (agenda[i].nome.equalsIgnoreCase(alvo)) {
                System.out.printf("%nEncontrado: %s | Tel: %s%n",
                        agenda[i].nome, agenda[i].telefone);
                return;
            }
        }
        System.out.println("\nContato não encontrado.");
    }

    /** Exclui um contato e desloca os seguintes para trás (sem buracos). */
    private static void excluirContato() {
        if (totalContatos == 0) {
            System.out.println("\n  (Agenda vazia.)");
            return;
        }
        String alvo = lerTexto("Nome a excluir: ");

        int posicao = -1;
        for (int i = 0; i < totalContatos; i++) {
            if (agenda[i].nome.equalsIgnoreCase(alvo)) {
                posicao = i;
                break;
            }
        }

        if (posicao == -1) {
            System.out.println("\nContato não encontrado.");
            return;
        }

        // Desloca cada elemento seguinte uma posição para trás.
        for (int i = posicao; i < totalContatos - 1; i++) {
            agenda[i] = agenda[i + 1];
        }
        agenda[totalContatos - 1] = null; // limpa a sobra no fim
        totalContatos--;                  // decrementa o contador de ativos

        System.out.println("\nContato excluído com sucesso!");
    }

    // ========================================================================
    //  CLASSES AUXILIARES (registros)
    // ========================================================================

    /** Registro de aluno usado no exercício 8. */
    private static class Aluno {
        String nome;
        String matricula;
        double media;

        Aluno(String nome, String matricula, double media) {
            this.nome = nome;
            this.matricula = matricula;
            this.media = media;
        }
    }

    /** Registro de contato usado no exercício 12. */
    private static class Contato {
        String nome;
        String telefone;

        Contato(String nome, String telefone) {
            this.nome = nome;
            this.telefone = telefone;
        }
    }

    // ========================================================================
    //  UTILITÁRIOS DE ENTRADA (com validação para não quebrar o programa)
    // ========================================================================

    /** Lê uma linha inteira de texto. */
    private static String lerTexto(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    /** Lê um inteiro; repete a pergunta enquanto a entrada for inválida. */
    private static int lerInteiro(String prompt) {
        while (true) {
            System.out.print(prompt);
            String linha = scanner.nextLine().trim();
            try {
                return Integer.parseInt(linha);
            } catch (NumberFormatException e) {
                System.out.println("  [!] Entrada inválida. Digite um número inteiro.");
            }
        }
    }

    /** Lê um número real; aceita vírgula ou ponto e valida a entrada. */
    private static double lerDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String linha = scanner.nextLine().trim().replace(',', '.');
            try {
                return Double.parseDouble(linha);
            } catch (NumberFormatException e) {
                System.out.println("  [!] Entrada inválida. Digite um número (ex.: 7.5).");
            }
        }
    }

    // ========================================================================
    //  UTILITÁRIOS VISUAIS
    // ========================================================================

    /** Desenha o menu principal dentro de uma caixa. */
    private static void exibirMenuPrincipal() {
        String linha = "═".repeat(LARGURA);
        System.out.println();
        System.out.println("╔" + linha + "╗");
        imprimirLinhaCaixa("  LISTA DE EXERCÍCIOS — LÓGICA DE PROGRAMAÇÃO");
        imprimirLinhaCaixa("              Menu Principal");
        System.out.println("╠" + linha + "╣");
        imprimirLinhaCaixa(" NÍVEL MÉDIO");
        imprimirLinhaCaixa("   1 - Calculadora de média escolar");
        imprimirLinhaCaixa("   2 - Tabuada interativa");
        imprimirLinhaCaixa("   3 - Pares e ímpares em sequência");
        imprimirLinhaCaixa("   4 - Conversor de temperatura");
        imprimirLinhaCaixa(" NÍVEL INTERMEDIÁRIO");
        imprimirLinhaCaixa("   5 - Fatorial e Fibonacci");
        imprimirLinhaCaixa("   6 - Busca em vetor");
        imprimirLinhaCaixa("   7 - Ordenação Bubble Sort");
        imprimirLinhaCaixa("   8 - Registro de alunos (struct)");
        imprimirLinhaCaixa(" NÍVEL DIFÍCIL");
        imprimirLinhaCaixa("   9 - Jogo da adivinhação");
        imprimirLinhaCaixa("  10 - Análise de texto");
        imprimirLinhaCaixa("  11 - Caixa eletrônico");
        imprimirLinhaCaixa("  12 - Agenda de contatos (CRUD)");
        System.out.println("╠" + linha + "╣");
        imprimirLinhaCaixa("   0 - Encerrar o programa");
        System.out.println("╚" + linha + "╝");
    }

    /** Imprime uma linha de texto alinhada dentro da caixa do menu. */
    private static void imprimirLinhaCaixa(String texto) {
        System.out.printf("║%-" + LARGURA + "s║%n", texto);
    }

    /** Título padronizado no topo de cada exercício. */
    private static void titulo(String t) {
        String linha = "─".repeat(LARGURA);
        System.out.println();
        System.out.println("┌" + linha + "┐");
        System.out.printf("│%-" + LARGURA + "s│%n", " " + t);
        System.out.println("└" + linha + "┘");
    }

    /** Pausa até o usuário pressionar Enter, antes de voltar ao menu. */
    private static void pausar() {
        System.out.print("\nPressione Enter para voltar ao menu...");
        scanner.nextLine();
    }
}