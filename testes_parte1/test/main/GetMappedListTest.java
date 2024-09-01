package main;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class GetMappedListTest {
    @Test
    public void createTest(){

        Map<Character, List<Pessoa>> testList = Main.getMappedList("Mateus Alves - 20 - M, Khelbia - 30 - F");

        testList.get('M').stream().forEach(
                pessoa -> {
                    Assert.assertEquals(pessoa.getSexo(), (Character) 'M');
                }
        );

        testList.get('F').stream().forEach(
                pessoa -> {
                    Assert.assertEquals(pessoa.getSexo(), (Character) 'F');
                }
        );

    }
}
