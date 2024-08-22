import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String pessoasRaw = scan.nextLine();
        scan.close();

        Map<Character, List<Pessoa>> pessoas = Arrays.stream(pessoasRaw.split(", "))
                .map(p -> {
                    String[] t = p.split(" ");
                    return new Pessoa(t[0], Integer.parseInt(t[1]), t[2].charAt(0));
                })
                .collect(Collectors.groupingBy(Pessoa::getSexo));

        pessoas.get('F').forEach(System.out::println);
        pessoas.get('M').forEach(System.out::println);
    }

    //Explicação
    public static void convertString(String pessoasRaw){
        Map<Character, List<Pessoa>> pessoas = Arrays
                /*
                    Inicia a stream com o Array que retorna dp split
                */
                .stream(pessoasRaw.split(", "))
                .map(p -> {
                    /*
                        Para cada item desse array
                        Separa eles por espaço
                    */
                    String[] t = p.split(" ");
                    /*
                        Usa cada item desse array para criar uma nova pessoa
                        E retorna
                    */
                    return new Pessoa(t[0], Integer.parseInt(t[1]), t[2].charAt(0));
                })
                /*
                    Cada pessoa criada pelo map
                    É adicionada a um Map usando o conteudo de getSexo() como chave
                */
                .collect(
                        Collectors.groupingBy(Pessoa::getSexo)
                );
    }

}