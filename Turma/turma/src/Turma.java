import java.util.ArrayList;
import java.util.List;

public class Turma {
    private String nomeTurma;
    private Professor professor;
    private List<Aluno> alunos;

    public Turma(String nomeTurma, Professor professor) {
        this.nomeTurma = nomeTurma;
        this.professor = professor;
        this.alunos = new ArrayList<>();
    }

    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    public void exibirTurma() {
        System.out.println("=== Turma: " + nomeTurma + " ===");
        professor.exibirInfo();
        System.out.println("--- Alunos ---");
        for (Aluno a : alunos) {
            a.exibirInfo();
        }
    }
}