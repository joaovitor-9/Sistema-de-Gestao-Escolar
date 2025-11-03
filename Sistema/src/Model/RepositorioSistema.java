package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioSistema {
    private static RepositorioSistema instanciaUnica;
    private HashMap<String, Aluno> alunos;
    private HashMap<String, Professor> professores;
    private HashMap<String, Disciplina> disciplinas;
    private List<Nota> notas;
    private List<Frequencia> faltas;
    private HashMap<String, List<String>> alunosPorDisciplina;
    private HashMap<String, List<String>> disciplinasPorProfessor;
    private int proximoNumeroMatricula = 2025000;
    private int proximoIDProfessor = 2025001;
    private RepositorioSistema() {
        alunos = new HashMap<>();
        professores = new HashMap<>();
        disciplinas = new HashMap<>();
        notas = new ArrayList<>();
        faltas = new ArrayList<>();
        alunosPorDisciplina = new HashMap<>();
        disciplinasPorProfessor = new HashMap<>();
    }
    public static synchronized RepositorioSistema getInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new RepositorioSistema();
        }
        return instanciaUnica;
    }
    private void popularDadosDeTeste() { // TEMPORARIO ATE A 3 ENTREGA
        try {
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

            adicionarAluno(aluno1);
            adicionarAluno(aluno2);
            adicionarProfessor(prof1);
            adicionarProfessor(prof2);
            adicionarDisciplina(disc1);
            adicionarDisciplina(disc2);

            matricularAluno(aluno1, disc1);
            matricularAluno(aluno2, disc1);
            matricularAluno(aluno2, disc2);

            alocarProfessor(prof1, disc1);
            alocarProfessor(prof2, disc2);

            salvarNota(new Nota(aluno1, disc1, 8.5));
            salvarNota(new Nota(aluno2, disc1, 7.0));
            salvarFalta(new Frequencia(aluno1, disc1));

            System.out.println(">>> MODO DE TESTE: Repositório populado com dados iniciais.");
            System.out.println(">>> Aluno para login: " + matAluno1 + " (Alice)");
            System.out.println(">>> Professor para login: " + idProf1 + " (Dr. Carlos)");

        } catch (Exception e) {
            System.err.println("!!! FALHA AO POPULAR DADOS DE TESTE !!!");
            e.printStackTrace();
        }
    }
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
    public Aluno buscarAlunoPorMatricula(String matricula) {
        return alunos.get(matricula);
    }
    public Professor buscarProfessorPorId(String id) {
        return professores.get(id);
    }
    public Disciplina buscarDisciplinaPorCodigo(String codigo) {
        return disciplinas.get(codigo);
    }
    public List<Aluno> listarAlunos() {
        return new ArrayList<>(alunos.values());
    }
    public List<Professor> listarProfessores() {
        return new ArrayList<>(professores.values());
    }
    public List<Disciplina> listarDisciplinas() {
        return new ArrayList<>(disciplinas.values());
    }
    public void matricularAluno(Aluno aluno, Disciplina disciplina) {
        String matricula = aluno.getMatricula();
        String codigo = disciplina.getCodigo();
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
            List<String> matriculas = alunosPorDisciplina.get(codigoDisciplina);
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
        List<Disciplina> lista = new ArrayList<>();
        for (String codigoDisciplina : alunosPorDisciplina.keySet()) {
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
        return notas.stream()
                .filter(nota -> nota.getAluno().getMatricula().equals(matriculaAluno))
                .collect(Collectors.toList());
    }
    public List<Frequencia> buscarFaltasDoAluno(String matriculaAluno) {
        return faltas.stream()
                .filter(falta -> falta.getAluno().getMatricula().equals(matriculaAluno))
                .collect(Collectors.toList());
    }
    public boolean cpfJaCadastrado(String cpf) {
        for (Aluno a : alunos.values()) {
            if (a.getCpf() != null && a.getCpf().equals(cpf)) { return true; }
        }
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
    public List<Nota> buscarNotasPorDisciplina(String codigoDisciplina) {
        return notas.stream()
                .filter(nota -> nota.getDisciplina().getCodigo().equals(codigoDisciplina))
                .collect(Collectors.toList());
    }
    public List<Frequencia> buscarFaltasPorDisciplina(String codigoDisciplina) {
        return faltas.stream()
                .filter(falta -> falta.getDisciplina().getCodigo().equals(codigoDisciplina))
                .collect(Collectors.toList());
    }
}
