public class App {
    public static void main(String[] args) throws Exception {
        String aluno = "Anthony";
        double n1 = 8.5;
        double n2 = 7.5;
        double n3 = 7.0;
        double media;
        
        
        media = (n1 + n2 + n3)/3;
        System.out.printf("%s , media das suas notas  são: %.2f%n", aluno, media);
        
        if(media >=7){ 
            System.err.println("Aprovado");
        }
        else if(media >=5){
            System.err.println("Repuperação");

        }else{
            System.out.println("Reprovado");
        }
    }
}
