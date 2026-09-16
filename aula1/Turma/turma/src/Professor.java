public class Professor extends Pessoa {
    private String disciplina;

    public Professor(String nome, int idade, String disciplina) {
        super(nome, idade);
        this.disciplina = disciplina;
    }

    public String getDisciplina() {
        return disciplina;
    }

    @Override
    public void exibirInfo() {
        System.out.println("Professor: " + nome + " | Idade: " + idade 
            + " | Disciplina: " + disciplina);
    }
}