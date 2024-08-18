import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        float[] notas = lerNotas();
        float   media = calculaMedia(notas);

        Tabela tab = new Tabela(notas, media);
        tab.mostra();

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

    public static class Tabela{

            private final String BRANCO   = "\u001B[37m";
            private final String VERMELHO = "\u001B[31m";
            private final String AMARELO  = "\u001B[33m";
            private final String VERDE    = "\u001B[32m";

            private final float[] notas;
            private final float   media;

            public Tabela(float[] _notas, float _media){
                this.notas = _notas;
                this.media = _media;
            }

            private void mostra(){
                System.out.println();
                this.imprimeCabecalho();
                this.imprimeNotas();
                this.imprimeMedia();

                this.imprimeSituacao();
            }

        /**
         * Imprime a primeira linha, onde fica cada nota lado a lado e ao fim o campo da media
         */
        private void imprimeCabecalho(){
                //Seta a cor inicial
                System.out.print(this.BRANCO);

                //Imprime Cada nota
                for(int i = 0; i < this.notas.length; i++){
                    System.out.printf("|   Nota  %d  ", i + 1);
                }

                System.out.print("|  Media  |");
                System.out.println();

            }

        /**
         * Imprime todas as notas e aplica uma coloração de acordo com o valor da nota.
         */
        private void imprimeNotas(){
            for(int i = 0; i < this.notas.length; i++){
                System.out.print("|   ");
                //Quando o numero for menor que 2 digitos, printa espaços adicionais no console manter o alinhamento
                if(numerosAntesDoPonto(this.notas[i]) == 1){
                    System.out.print(" ");
                }

                System.out.print(this.getCorNota(notas[i]));
                System.out.printf("%.2f    ", notas[i]);
                //Reseta a cor para o branco default
                System.out.print(this.BRANCO);
            }
        }

        /**
         * Imprime a media e aplica uma coloração de acordo com o valor.
         */
        private void imprimeMedia(){
            System.out.print("|");
            if(numerosAntesDoPonto(this.media) == 1){
                System.out.print(" ");
            }
            System.out.print(this.getCorNota(media));
            System.out.printf("  %.2f  %s|", media, this.BRANCO);
            System.out.println();
        }

        private void imprimeSituacao(){

            System.out.println();
            System.out.printf("Situação do aluno: %s%s.", this.testaCituacaoDoAluno(media), this.BRANCO);

        }

        /**
         * <ul>
         *     <li>Numeros maiores que 7 = Verde</li>
         *     <li>Numeros maiores que 5 = Amarelo</li>
         *     <li>Numeros menores que 5 = Vermelho</li>
         * </ul>
         * @return Uma cor de acordo com a nota passada.
         */
        private String getCorNota(float nota){
            if(nota >= 7){
                return this.VERDE;
            } else if (nota >= 5){
                return this.AMARELO;
            } else {
                return this.VERMELHO;
            }
        }

        /**
         * @return Retorna a situação do aluno propriamente colorida.<br>
         *
         * <ul>
         *     <li> >= 7 == APROVADO em verde. </li>
         *     <li> >= 5 == RECUPERAÇÃO em amarelo. </li>
         *     <li>  < 5 == REPROVADO em vermelho. </li>
         * </ul>
         */
        private String testaCituacaoDoAluno(float media){
            if(media >= 7){
                return this.VERDE + "APROVADO";
            }

            if(media >= 5){
                return this.AMARELO + "RECUPERAÇÃO";
            }

            return this.VERMELHO + "REPROVADO";
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

}