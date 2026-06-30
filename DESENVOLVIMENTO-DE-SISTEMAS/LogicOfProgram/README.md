<div align="center">

# 🧠 Lista de Exercícios — Lógica de Programação

**Um sistema de console em Java, único e modular, criado para fixar os conceitos fundamentais da lógica de programação.**

![Java](https://img.shields.io/badge/Java-8%2B-orange?style=flat-square&logo=openjdk)
![Tipo](https://img.shields.io/badge/Tipo-Console-blue?style=flat-square)
![Foco](https://img.shields.io/badge/Foco-Educacional-green?style=flat-square)
![Exercícios](https://img.shields.io/badge/Exerc%C3%ADcios-12-purple?style=flat-square)

</div>

---

## 📌 Sobre o projeto

Este programa reúne **12 exercícios de lógica de programação** — do nível médio ao difícil — em um **único arquivo Java**. Cada exercício é encapsulado em seu próprio **método**, e todos são acessados a partir de um **menu principal** controlado por um `switch-case` que roda em **loop** até o usuário digitar `0` para encerrar.

A proposta é **educacional**: em vez de 12 arquivos soltos, tudo vive em um sistema coeso e navegável, no qual cada conceito pode ser executado, testado e revisitado isoladamente. É uma ferramenta de **fixação e revisão** — ideal para reforçar fundamentos como estruturas condicionais, laços, vetores, recursão, registros (`struct`) e operações CRUD.

---

## 🛠️ O que foi usado

O projeto se apoia exclusivamente em recursos **nativos da linguagem Java**, sem bibliotecas externas:

| Recurso | Onde aparece |
|---|---|
| **`Scanner`** | Leitura de toda a entrada do usuário pelo console |
| **Estruturas condicionais** (`if/else`, `switch`) | Menu principal e classificações (notas, opções) |
| **Laços de repetição** (`for`, `while`, `do-while`) | Tabuadas, contagens, menus em loop |
| **Operadores aritméticos e módulo** (`%`) | Médias, par/ímpar, caixa eletrônico |
| **Vetores (arrays)** | Busca linear, ordenação, registros |
| **Recursão** | Cálculo do fatorial (Exercício 5) |
| **Classes aninhadas (registros)** | `Aluno` e `Contato` |
| **Tratamento de exceções** (`try/catch`) | Validação de entrada numérica |
| **`StringBuilder` e métodos de `String`** | Análise e inversão de texto |
| **`Random`** | Jogo da adivinhação |
| **`printf` / `String.format`** | Saída formatada e alinhada no console |

---

## 🧩 Estrutura do programa

O arquivo `ListaExercicios.java` é organizado em camadas claras:

```
ListaExercicios.java
│
├── main()                      → menu principal + loop (switch-case, 0 encerra)
│
├── exercicio01() ... exercicio12()
│                               → cada exercício isolado em um método
│
├── Métodos de apoio
│   ├── fatorial() / exibirFibonacci()
│   ├── cadastrar / listar / buscar / excluirContato()  (CRUD do Ex. 12)
│   └── ehVogal()
│
├── Classes auxiliares (registros)
│   ├── Aluno    (nome, matrícula, média)
│   └── Contato  (nome, telefone)
│
├── Utilitários de entrada (com validação)
│   ├── lerInteiro()   → repete enquanto a entrada for inválida
│   ├── lerDouble()    → aceita vírgula ou ponto
│   └── lerTexto()
│
└── Utilitários visuais
    ├── exibirMenuPrincipal() / titulo()
    └── pausar()
```

> 💡 **Decisão de design:** a leitura de entrada foi centralizada em utilitários próprios (`lerInteiro`, `lerDouble`, `lerTexto`) para evitar o clássico bug de buffer do `Scanner` (mistura de `nextInt()` com `nextLine()`) e para **validar** os dados — se o usuário digitar algo inválido, a pergunta é repetida em vez de o programa quebrar.

---

## 📚 Os exercícios

### 🟢 Nível Médio
| Nº | Exercício | Conceito reforçado |
|----|-----------|--------------------|
| 1 | Calculadora de média escolar | Condicionais e aritmética |
| 2 | Tabuada interativa | Laços de repetição e saída formatada |
| 3 | Pares e ímpares em sequência | Operador módulo e contadores |
| 4 | Conversor de temperatura | Seleção múltipla e validação |

### 🟡 Nível Intermediário
| Nº | Exercício | Conceito reforçado |
|----|-----------|--------------------|
| 5 | Fatorial e Fibonacci | Funções, parâmetros e recursão |
| 6 | Busca em vetor | Arrays e busca linear |
| 7 | Ordenação Bubble Sort | Laços aninhados e troca de valores |
| 8 | Registro de alunos (`struct`) | Tipos estruturados, filtro e ordenação |

### 🔴 Nível Difícil
| Nº | Exercício | Conceito reforçado |
|----|-----------|--------------------|
| 9 | Jogo da adivinhação | Números aleatórios e controle de fluxo |
| 10 | Análise de texto | Manipulação de strings |
| 11 | Caixa eletrônico | Algoritmo guloso, divisão inteira e módulo |
| 12 | Agenda de contatos (CRUD) | CRUD em memória e gerenciamento de vetor |

---

## ▶️ Como executar

O arquivo está codificado em **UTF-8**, para que os acentos e as bordas das caixas apareçam corretamente.

**Em uma IDE** (IntelliJ, VS Code, Eclipse) — funciona direto: basta compilar e executar a classe `ListaExercicios`.

**No terminal:**

```bash
# Compilar (use -encoding UTF-8 para preservar acentos)
javac -encoding UTF-8 ListaExercicios.java

# Executar
java ListaExercicios
```

> 🪟 **No Windows:** antes de rodar, execute `chcp 65001` no terminal para que o console interprete UTF-8 e exiba acentos e bordas corretamente.

---

## 🎯 Por que foi feito

Este sistema foi construído com **foco educacional**. O objetivo não é apenas "resolver a lista", mas transformá-la em um **ambiente de estudo interativo**, onde cada conceito da lógica de programação:

- pode ser **executado e testado** individualmente, sem recompilar nada;
- está **documentado com comentários** que explicam o *porquê* de cada decisão;
- segue **boas práticas** de organização (responsabilidade única por método, validação de entrada, separação entre lógica e apresentação).

A meta é que o código sirva tanto como **resposta correta** quanto como **material de revisão** — algo que possa ser lido, entendido e reaproveitado para reforçar os fundamentos.

---

<div align="center">

*Feito com foco em clareza, organização e aprendizado.* ✨

</div>