package main.test.generic;

import main.java.annotations.Getter;
import main.java.exceptions.DataAlreadyInDBException;
import main.java.exceptions.DataNotPresentInDBException;
import main.java.generics.DAO.IGenericDAO;
import main.java.generics.service.IGenericService;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

public abstract class GenericServiceTest<T> {

    protected IGenericService<T> service;
    protected IGenericDAO<T> dao;

    protected abstract IGenericDAO<T> createDAO();
    protected abstract IGenericService<T> createService(IGenericDAO<T> dao);
    protected abstract T createTestEntity(Double code);

    @Before
    public void init(){
        this.dao = createDAO();
        this.service = createService(this.dao);
        assertNotNull(this.dao);
        assertNotNull(this.service);
    }

    @Test
    public void restRegister(){
        T entity = createTestEntity(123123D);
        assertTrue(this.service.register(entity));
    }

    @Test(expected = DataAlreadyInDBException.class)
    public void testRegisterDuplicate(){
        T entity = createTestEntity(123331D);
        this.service.register(entity);
        //Isso deve levantar o erro
        this.service.register(entity);
    }

    @Test
    public void testSearch(){
        T entity = createTestEntity(3445D);
        this.service.register(entity);
        T result = this.service.pesquisa(getEntityIdentifier(entity));
        assertEquals(entity, result);
    }

    @Test
    public void testSearchNull(){
        //Tenta pesquisar por um code que não existe registrado
        assertNull(this.service.pesquisa(45535345D));
    }

    @Test
    public void testAtualiza(){
        //Cria uma nova instancia
        T entity = createTestEntity(4124124D);
        //Registra no banco de dados
        assertTrue(this.service.register(entity));

        //Qualquer entidade passada com o mesmo identificador
        //Permite que a atualização seja feita

        //Uma boa seria impedir atualizar os dados se todos os dados forem iguais
        //Não sei se vale a pena ou se faz alguma diferença
        assertTrue(this.service.atualiza(entity));
    }

    @Test(expected = DataNotPresentInDBException.class)
    public void testAtualizaNull(){
        //Cria uma nova instancia
        T entity = createTestEntity(4124552D);
        //Tenta atualizar essa instancia que ainda não existe no banco de dados
        this.service.atualiza(entity);
    }

    @Test
    public void testExcluir(){
        //Cria uam nova instancia
        T entity = createTestEntity(12441242D);
        //Registra no banco de dados
        this.service.register(entity);
        //Tenta excluir essa mesma instancia
        assertTrue(this.service.excluir(getEntityIdentifier(entity)));
    }

    @Test(expected = DataNotPresentInDBException.class)
    public void testExcluirNull(){
        //Tenta excluir do banco de dados uma entidade que não existe
        this.service.excluir(124124D);
    }

    public Double getEntityIdentifier(T entity) {

        //Pega o nome do metodo responsavel
        //por retornar o identificador unico
        //que fica guardado na annotation
        String methodName = entity.getClass().getAnnotation(Getter.class).value();
        Method method;

        try{
            //Tenta pegar o metodo
             method = entity.getClass().getMethod(methodName);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        try {
            //Tenta usar o metodo para pegar o identificador unico
            return Double.parseDouble(method.invoke(entity).toString());
        } catch (InvocationTargetException | IllegalAccessException e){
            throw new RuntimeException(e);
        }

    }

}
