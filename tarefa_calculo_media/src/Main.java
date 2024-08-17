import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        float[] notas = lerNotas();

        float   media = calculaMedia(notas);

        mostraNotas(notas, media);

    }

    /**
     * Irá ler 4 notas no console, se digitado algo alem de números, irá dar erro...
     * <br><br>
     * <strong>Atenção</strong>: Hardcoded para 4 notas, mas é facilmente melhorado para receber N notas. <br>
     *
     * @return uma lista que contem todas as notas.
     */
    public static float[] lerNotas(){
        float[] notas = new float[4];
        Scanner scan = new Scanner(System.in);

        for(int i = 0; i < 4; i++){
            System.out.print("Insira a " + (i + 1) + "ª nota: ");
            notas[i] = scan.nextFloat();
        }

        return notas;
    }

    /**
     * Recebe uma lista de qualquer tamanho e calcula a média dos valores
     * <br><br>
     * Para ver como conseguir a lista de notas use:
     * @see Main#lerNotas()
     */
    public static float calculaMedia(float[] notas){
        float soma = 0;

        for(int i = 0; i < notas.length; i++){
            soma += notas[i];
        }

        return soma / notas.length;
    }

    /** Printa no console uma tabela organizada das notas e da média.
     *
     * @see Main#lerNotas()
     * @see Main#calculaMedia(float[])
     */
    public static void mostraNotas(float[] notas, float media){

        for(int i = 0; i < notas.length; i++){
            System.out.printf("|   Nota  %d  ", i + 1);
        }
        System.out.print("|  Media  |");

        System.out.println();

        for(int i = 0; i < notas.length; i++){
            System.out.print("|   ");
            //Quando o numero for menor que 2 digitos, printa espaços adicionais no console manter o alinhamento
            if(numerosAntesDoPonto(notas[i]) == 1){
                System.out.print(" ");
            }
            System.out.printf("%.2f    ", notas[i]);
        }
        System.out.print("|");
        if(numerosAntesDoPonto(media) == 1){
            System.out.print(" ");
        }
        System.out.printf("  %.2f  |", media);

    }

    /**
     * Conta quantos números um número flutuante tem antes do "." <br>
     * Exemplo: 231.44 output = 3
     *
     * <br><br>
     * Pode ser facilmente refatorado para dizer quantos numeros há depois do ponto também...
     */
    private static int numerosAntesDoPonto(float numero){
        String numero_string = Float.toString(numero);
        String[] numero_metades = numero_string.split("\\.");

        return numero_metades[0].length();
    }

}