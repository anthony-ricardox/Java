public class Main {
    public static void main(String[] args) {
        Professor prof = new Professor("Carlos", 40, "Matemática");

        Turma turma = new Turma("9º Ano A", prof);

        Aluno a1 = new Aluno("Ana", 14, "2024001");
        a1.setNota(8.5);

        Aluno a2 = new Aluno("Bruno", 15, "2024002");
        a2.setNota(7.0);

        turma.adicionarAluno(a1);
        turma.adicionarAluno(a2);

        turma.exibirTurma();
    }
}