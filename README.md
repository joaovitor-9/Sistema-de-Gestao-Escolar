# Sistema-de-Gestao-Escolar

### Descriçâo
Um sistema de gestão escolar desenvolvido em Java, projetado para ser executado no terminal. A aplicação permite o gerenciamento completo de entidades acadêmicas (alunos, professores e disciplinas) e o registro de notas e frequências.
O projeto foi construído utilizando a arquitetura **MVC (Model-View-Controller)** e aplica padrões de projeto como **Singleton** para simulação de persistência de dados.

## Tecnologias Utilizadas
* **Linguagem:** Java (JDK 8+)
* **Arquitetura:** MVC (Model-View-Controller)
* **Padrão de Projeto:** Singleton (no Repositório)
* **Modelagem:** PlantUML
  
### Funcionalidades
O sistema possui três níveis de acesso (Atores), cada um com permissões específicas:

## Administrador
- Cadastrar novos Alunos e Professores.
- Criar novas Disciplinas.
- Matricular alunos em disciplinas.
- Alocar professores em disciplinas.
- Consultar dados gerais (todas as disciplinas, notas e faltas de qualquer aluno).

## Professor
- Visualizar as disciplinas que leciona.
- Consultar lista de alunos matriculados em suas disciplinas.
- **Lançar Notas** para os alunos.
- **Lançar Faltas** (Frequência).

## Aluno
- Consultar boletim (Minhas Notas).
- Consultar histórico de frequência (Minhas Faltas).
- Visualizar grade de disciplinas matriculadas.
  
### Protótipo de baixa fidelidade
(https://www.figma.com/proto/nuA4NATVkdSw6haFZD6eRK/Sistema-de-Gest%C3%A3o-Escolar?node-id=1-6567&p=f&t=9wxn6QCflegQ12h3-1&scaling=min-zoom&content-scaling=fixed&page-id=0%3A1)

## Como Executar
1.  Clone este repositório.
2.  Abra o projeto em sua IDE Java favorita (IntelliJ, Eclipse, VS Code).
3.  Execute o arquivo principal: `src/Main.java`.
4.  Utilize os menus numéricos no terminal para navegar.
   
### Autores
(Elias Manuel Fonseca Moreira e João Vitor Farias de Amorim)
