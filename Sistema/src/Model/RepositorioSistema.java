package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Esta classe é o Repositório de Dados (DAO / Camada de Persistência).
 * Ela é implementada como um Singleton para garantir que exista apenas UMA
 * fonte de dados (os HashMaps) em todo o sistema.
 * * NENHUMA outra classe deve ter HashMaps de dados.
 * NENHUMA lógica de negócio (validação) deve existir aqui.
 * NENHUMA classe de View (System.out) deve ser chamada.
 */
public class RepositorioSistema {

    // --- 1. Implementação do Padrão Singleton ---
    private static RepositorioSistema instanciaUnica;

    // --- 2. "Banco de Dados" em Memória ---
    // Tabelas principais
    private HashMap<String, Aluno> alunos;
    private HashMap<String, Professor> professores;
    private HashMap<String, Disciplina> disciplinas;

    // Tabelas de "fatos"
    private List<Nota> notas;
    private List<Frequencia> faltas;

    // Tabelas de Relacionamento (Muitos-para-Muitos)
    // Mapeia <codigoDisciplina, Lista<matriculaAluno>>
    private HashMap<String, List<String>> alunosPorDisciplina;
    // Mapeia <idProfessor, Lista<codigoDisciplina>>
    private HashMap<String, List<String>> disciplinasPorProfessor;

    // --- 3. Geradores de ID  ---
    private int proximoNumeroMatricula = 2025000;
    private int proximoIDProfessor = 2025001;


    /**
     * Construtor privado (parte do Singleton).
     * Inicializa todas as coleções de dados.
     */
    private RepositorioSistema() {
        alunos = new HashMap<>();
        professores = new HashMap<>();
        disciplinas = new HashMap<>();
        notas = new ArrayList<>();
        faltas = new ArrayList<>();
        alunosPorDisciplina = new HashMap<>();
        disciplinasPorProfessor = new HashMap<>();
    }

    /**
     * Ponto de acesso global (parte do Singleton).
     * 'synchronized' garante segurança em ambientes com threads.
     */
    public static synchronized RepositorioSistema getInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new RepositorioSistema();
        }
        return instanciaUnica;
    }


    private void popularDadosDeTeste() { // TEMPORARIO ATE A 3 ENTREGA
        try {
            // 1. Criar Entidades
            // (Note que usamos os IDs que serão gerados primeiro)
            String matAluno1 = "MAT" + proximoNumeroMatricula;
            proximoNumeroMatricula++;
            Aluno aluno1 = new Aluno("Alice Silva", 20, "111.111.111-11", matAluno1);

            String matAluno2 = "MAT" + proximoNumeroMatricula;
            proximoNumeroMatricula++;
            Aluno aluno2 = new Aluno("Bruno Costa", 22, "222.222.222-22", matAluno2);

            String idProf1 = "PROF" + proximoIDProfessor;
            proximoIDProfessor++;
            Professor prof1 = new Professor("Dr. Carlos", "333.333.333-33", 45, 5000.0, "Matematica");
            prof1.setId(idProf1);

            String idProf2 = "PROF" + proximoIDProfessor;
            proximoIDProfessor++;
            Professor prof2 = new Professor("Dra. Diana", "444.444.444-44", 38, 4500.0, "Portuges");
            prof2.setId(idProf2);

            Disciplina disc1 = new Disciplina("Matematica", "SI101");
            Disciplina disc2 = new Disciplina("Lingua Portugesa", "SI102");

            // 2. Salvar Entidades nas "Tabelas"
            adicionarAluno(aluno1);
            adicionarAluno(aluno2);
            adicionarProfessor(prof1);
            adicionarProfessor(prof2);
            adicionarDisciplina(disc1);
            adicionarDisciplina(disc2);

            // 3. Criar Relacionamentos
            // Alice (aluno1) em mat (disc1)
            matricularAluno(aluno1, disc1);
            // Bruno (aluno2) em mat (disc1) e LP (disc2)
            matricularAluno(aluno2, disc1);
            matricularAluno(aluno2, disc2);

            // Dr. Carlos (prof1) leciona mat (disc1)
            alocarProfessor(prof1, disc1);
            // Dra. Diana (prof2) leciona LP (disc2)
            alocarProfessor(prof2, disc2);

            // 4. (Opcional) Criar "Fatos" (Notas/Faltas)
            salvarNota(new Nota(aluno1, disc1, 8.5));
            salvarNota(new Nota(aluno2, disc1, 7.0));
            salvarFalta(new Frequencia(aluno1, disc1));

            // 5. Exibir um aviso no console
            System.out.println(">>> MODO DE TESTE: Repositório populado com dados iniciais.");
            System.out.println(">>> Aluno para login: " + matAluno1 + " (Alice)");
            System.out.println(">>> Professor para login: " + idProf1 + " (Dr. Carlos)");

        } catch (Exception e) {
            System.err.println("!!! FALHA AO POPULAR DADOS DE TESTE !!!");
            e.printStackTrace();
        }
    }
    // --- 4. Métodos CRUD (Create, Read, Update, Delete) ---

    // Adicionar/Salvar
    public void adicionarAluno(Aluno aluno) {
        alunos.put(aluno.getMatricula(), aluno);
    }

    public void adicionarProfessor(Professor professor) {
        professores.put(professor.getID(), professor);
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        disciplinas.put(disciplina.getCodigo(), disciplina);
    }

    public void salvarNota(Nota nota) {
        notas.add(nota);
    }

    public void salvarFalta(Frequencia falta) {
        faltas.add(falta);
    }

    // Buscar Entidades Únicas
    public Aluno buscarAlunoPorMatricula(String matricula) {
        return alunos.get(matricula);
    }

    public Professor buscarProfessorPorId(String id) {
        return professores.get(id);
    }

    public Disciplina buscarDisciplinaPorCodigo(String codigo) {
        return disciplinas.get(codigo);
    }

    // Listar Tudo
    public List<Aluno> listarAlunos() {
        return new ArrayList<>(alunos.values());
    }

    public List<Professor> listarProfessores() {
        return new ArrayList<>(professores.values());
    }

    public List<Disciplina> listarDisciplinas() {
        return new ArrayList<>(disciplinas.values());
    }

    // --- 5. Métodos de Relacionamento e Consulta ---

    public void matricularAluno(Aluno aluno, Disciplina disciplina) {
        String matricula = aluno.getMatricula();
        String codigo = disciplina.getCodigo();

        // Garante que a lista existe antes de adicionar
        alunosPorDisciplina.putIfAbsent(codigo, new ArrayList<>());
        alunosPorDisciplina.get(codigo).add(matricula);
    }

    public void alocarProfessor(Professor professor, Disciplina disciplina) {
        String idProf = professor.getID();
        String codDisc = disciplina.getCodigo();

        disciplinasPorProfessor.putIfAbsent(idProf, new ArrayList<>());
        disciplinasPorProfessor.get(idProf).add(codDisc);
    }

    public List<Aluno> buscarAlunosPorDisciplina(String codigoDisciplina) {
        List<Aluno> lista = new ArrayList<>();
        if (alunosPorDisciplina.containsKey(codigoDisciplina)) {
            // Pega a lista de MATRICULAS
            List<String> matriculas = alunosPorDisciplina.get(codigoDisciplina);
            // Converte as matriculas em objetos Aluno
            for (String matricula : matriculas) {
                Aluno a = buscarAlunoPorMatricula(matricula);
                if (a != null) {
                    lista.add(a);
                }
            }
        }
        return lista;
    }

    public List<Disciplina> buscarDisciplinasPorProfessor(String idProfessor) {
        List<Disciplina> lista = new ArrayList<>();
        if (disciplinasPorProfessor.containsKey(idProfessor)) {
            List<String> codigos = disciplinasPorProfessor.get(idProfessor);
            for (String codigo : codigos) {
                Disciplina d = buscarDisciplinaPorCodigo(codigo);
                if (d != null) {
                    lista.add(d);
                }
            }
        }
        return lista;
    }

    public List<Disciplina> buscarDisciplinasDoAluno(String matriculaAluno) {
        // Esta é uma consulta mais complexa (inversa)
        List<Disciplina> lista = new ArrayList<>();
        // Itera em todas as disciplinas
        for (String codigoDisciplina : alunosPorDisciplina.keySet()) {
            // Vê se a lista de alunos daquela disciplina contém este aluno
            if (alunosPorDisciplina.get(codigoDisciplina).contains(matriculaAluno)) {
                Disciplina d = buscarDisciplinaPorCodigo(codigoDisciplina);
                if (d != null) {
                    lista.add(d);
                }
            }
        }
        return lista;
    }

    public List<Nota> buscarNotasDoAluno(String matriculaAluno) {
        // Usando "stream" para filtrar a lista. É o jeito moderno.
        return notas.stream()
                .filter(nota -> nota.getAluno().getMatricula().equals(matriculaAluno))
                .collect(Collectors.toList());
    }

    public List<Frequencia> buscarFaltasDoAluno(String matriculaAluno) {
        return faltas.stream()
                .filter(falta -> falta.getAluno().getMatricula().equals(matriculaAluno))
                .collect(Collectors.toList());
    }

    // --- 6. Métodos de Verificação e Geração (do seu código original) ---

    public boolean cpfJaCadastrado(String cpf) {
        // Checa em Alunos
        for (Aluno a : alunos.values()) {
            if (a.getCpf() != null && a.getCpf().equals(cpf)) { return true; }
        }
        // Checa em Professores
        for (Professor p : professores.values()) {
            if (p.getCpf() != null && p.getCpf().equals(cpf)) { return true; }
        }
        return false;
    }

    public boolean disciplinaExiste(String codigo) {
        return disciplinas.containsKey(codigo);
    }

    public boolean professorLecionaDisciplina(String idProfessor, String codigoDisciplina) {
        if (!disciplinasPorProfessor.containsKey(idProfessor)) {
            return false;
        }
        // Verifica se a lista de disciplinas do professor contém o código
        return disciplinasPorProfessor.get(idProfessor).contains(codigoDisciplina);
    }

    public String gerarNovaMatricula() {
        String novaMatricula = "MAT" + proximoNumeroMatricula;
        proximoNumeroMatricula++;
        return novaMatricula;
    }

    public String gerarNovoIDProfessor() {
        String novoID = "PROF" + proximoIDProfessor;
        proximoIDProfessor++;
        return novoID;
    }
}