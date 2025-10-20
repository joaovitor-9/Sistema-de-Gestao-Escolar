import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

    public class RepositorioSistema {
        // Usando HashMap para busca rápida, key é a matrícula/ID/código
        private static HashMap<String, Aluno> alunos = new HashMap<>();
        private static HashMap<String, Professor> professores = new HashMap<>();
        private static int proximoNumeroMatricula = 2025000;
        private static int proximoIDProfessor = 2025001;

        // Métodos para Alunos (CRUD Básico)
        public static String gerarNovaMatricula() {
            // Formato simples: "MAT" + número sequencial
            String novaMatricula = "MAT" + proximoNumeroMatricula;
            proximoNumeroMatricula++;
            return novaMatricula;
        }

        public static void adicionarAluno(Aluno aluno) {
            alunos.put(aluno.getMatricula(), aluno);
        }
        public static Aluno buscarAlunoPorMatricula(String matricula) {
            return alunos.get(matricula);
        }
        public static List<Aluno> listarAlunos() {
            return new ArrayList<>(alunos.values());
        }

        public static boolean cpfJaCadastrado(String cpf) {
            // Checa em Alunos e Professores
            for (Aluno a : alunos.values()) {
                if (a.getCpf() != null && a.getCpf().equals(cpf)) { return true; }
            }
            for (Professor p : professores.values()) {
                if (p.getCpf() != null && p.getCpf().equals(cpf)) { return true; }
            }
            return false;
        }

        // Métodos para Professores (CRUD Básico)
        public static String gerarNovoIDProfessor() {
            String novoID = "PROF" + proximoIDProfessor;
            proximoIDProfessor++;
            return novoID;
        }
        public static void adicionarProfessor(Professor professor) {
            professores.put(professor.getID(), professor);
        }
        public static List<Professor> listarProfessores() {
            return new ArrayList<>(professores.values());
        }


    }

