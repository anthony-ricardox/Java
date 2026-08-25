public class Aluno extends Pessoa {
    private String matricula;
    private double nota;

    public Aluno(String nome, int idade, String matricula) {
        super(nome, idade); // chama o construtor de Pessoa
        this.matricula = matricula;
        this.nota = 0.0;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public double getNota() {
        return nota;
    }

    @Override
    public void exibirInfo() {
        System.out.println("Aluno: " + nome + " | Idade: " + idade 
            + " | Matrícula: " + matricula + " | Nota: " + nota);
    }
}