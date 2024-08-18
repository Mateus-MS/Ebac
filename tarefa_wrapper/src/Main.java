import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.print("Insira um valor int: ");
        int valor = scan.nextInt();
        Integer valorConvertido = (Integer) valor;

        System.out.printf("Esse int convertido para Integer é: %d", valorConvertido);

    }
}