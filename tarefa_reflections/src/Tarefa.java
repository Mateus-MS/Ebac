import java.lang.annotation.*;

public class Tarefa {
    public static void main(String[] args) {

        Tabela tabela = new Tabela();
        Test   test   = new Test();

        System.out.println(getTabelaNome(tabela));
        System.out.println(getTabelaNome(test));

    }

    public static <T> String getTabelaNome(T tabela){
        Class clazz = tabela.getClass();

        TabelaAnnotation annontation;
        annontation = (TabelaAnnotation) clazz.getAnnotation(TabelaAnnotation.class);

        return annontation.nome();
    }

    @TabelaAnnotation(nome = "Principal")
    public static class Tabela{}

    @TabelaAnnotation(nome = "Test")
    public static class Test{}

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface TabelaAnnotation{
        String nome();
    }
}