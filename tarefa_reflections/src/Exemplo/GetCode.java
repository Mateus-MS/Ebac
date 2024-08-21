package Exemplo;

import Exemplo.Annotations.GetFuncAnnotation;
import Exemplo.Exceptions.SemAnotacaoException;

import java.lang.reflect.Method;

public class GetCode {

    public static <I, O> O getCode(I entity) {
        //Pega a classe
        Class clazz = entity.getClass();

        if(!temAnotacao(clazz)){
            throw new SemAnotacaoException("A entity enviada não tem a annotation necessaria") ;
        }

        GetFuncAnnotation annotation = pegaAnnotation(clazz);

        //Pega o valor guardado na annotation
        String metodoNome = annotation.value();

        Method metodo = pegaMetodo(clazz, metodoNome);

        return chamaMetodo(metodo, entity);

    }

    private static GetFuncAnnotation pegaAnnotation(Class clazz){
        return (GetFuncAnnotation) clazz.getAnnotation(GetFuncAnnotation.class);
    }

    private static boolean temAnotacao(Class clazz){
        return clazz.isAnnotationPresent(GetFuncAnnotation.class);
    }

    private static Method pegaMetodo(Class clazz, String metodoNome){
        Method metodo;
        try {
            metodo = clazz.getMethod(metodoNome);
        } catch(NoSuchMethodException e) {
            //TODO Tratar do erro
            throw new RuntimeException(e);
        }
        return metodo;
    }

    private static <I, O> O chamaMetodo(Method metodo, I entity){
        O result;
        try {
            result = (O) metodo.invoke(entity);
        } catch (Exception e) {
            //TODO Tratar do erro
            throw new RuntimeException(e);
        }
        return result;
    }

}
