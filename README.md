# Projeto Função de 1º Grau

## Visão Geral do Sistema

Este projeto implementa um sistema gráfico para calcular e exibir funções do 1º grau na forma `f(x) = ax + b`. Desenvolvido em Java utilizando a biblioteca Swing para a interface gráfica, o sistema oferece uma interface amigável para inserir os valores de `a` e `b`, calcular o ponto onde `f(x) = 0`, e exibir o gráfico da função.

## Funcionalidades Principais

- **Entrada de Dados**: O usuário pode inserir os valores de `a` e `b` através de uma interface gráfica.
- **Validação**: Verifica se o valor de `a` não é zero e se os valores inseridos são numéricos válidos.
- **Cálculo do Zero da Função**: Calcula o valor de `x` onde `f(x) = 0`.
- **Exibição do Gráfico**: Desenha o gráfico da função do 1º grau com eixos cartesianos.

## Organização do Código

### Model
- **FunctionModel.java**: Representa o modelo de dados para a função, contendo os valores de `a` e `b` e métodos para calcular o zero da função.

### View
- **InputView.java**: Interface gráfica para inserção dos valores de `a` e `b`.
- **GraphView.java**: Interface gráfica para exibir o gráfico da função.

### Controller
- **FunctionController.java**: Controlador que gerencia a interação entre a interface gráfica e o modelo de dados, incluindo a validação dos dados e a lógica de cálculo.

## Como Executar

1. Clone o repositório: `git clone https://github.com/seu-usuario/seu-repositorio.git`
2. Compile o projeto: `javac -d bin -sourcepath src src/com/GroupFunction/funcao/Main.java`
3. Execute o projeto: `java -cp bin com.GroupFunction.funcao.Main`

## Exemplo de Uso

1. Insira os valores de `a` e `b`.
2. Clique em "Calcular e Gerar Resultado".
3. Visualize o zero da função e, opcionalmente, exiba o gráfico da função.



