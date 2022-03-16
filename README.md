# Processo Seletivo Java

## Descrição 

- Este teste consiste em construir uma camada de serviço, para uma operação muito realizada em bancos, para emissão de extrato bancário.


## Como executar a aplicação 

- Você pode executar a aplicação da maneira que quiser e utilizando a IDE de sua preferência. 
- Caso queira executar a aplicação via linha de comando, execute primeiramente o comando:

                   ./mvnw clean package  para linux.

                   .\mvnw clean package  para windows.
- Após isso execute o comando: 

                             java -jar <...caminhoParaSeuJar>

## Requisitos de sistema

- Possuir a JDK 11 
- Uma IDE ou editor de sua preferência

## Requisitos do Projeto

- A sua api deve fornecer os dados de transferência de acordo com o número da conta bacária.
- Caso não seja informado nenhum filtro, retornar  todos os dados de transferência.
- Caso seja informado um período de tempo, retornar todas as transferências relacionadas à aquele período de tempo.
- Caso seja informado o nome do operador da transação, retornar todas as transferências relacionados à aquele operador.
- Caso todos os filtros sejam informados, retornar todas as transferências com base no período de tempo informado e o nome do operador.
- Operador de transação nada mais é que, o nome do responsável de destino da transação caso seja uma operação de transferência de saida ou o nome do responsável de onde se originou a transação caso seja uma operação de transferência de entrada.
- Os valores devem ser de ponto flutuante, e deve-se considerar apenas duas casas decimais.
- O frontend deve seguir como exemplo o protótipo informado no documento do processo seletivo.
- No frontend o usuário deve ser capaz de informar um período de tem e/ou nome do operador da transasção como filtros para buscar as transações.
- As transações devem ser exibidas junto com o saldo total e o saldo total no período de acordo com o protótipo do documento.

## O que iremos avaliar
- Cumprimento dos requisitos
- Qualidade do projeto de API e fluidez da DX
- Organização do código e boas práticas
- Domínio das linguagens, bibliotecas e ferramentas utilizadas
- Organização dos commits
- Escrita e cobertura de testes

## Sobre a entrega
- Utilizar o padrão RESTFul para a construção da sua API.
- Existe um script sql no pacote resources que cotém a modelagem do banco que pode ser seguida, e valores iniciais.
- Caso julge necessário você poderá criar mais tablas, porém a estrutura inicial não deve ser alterada.