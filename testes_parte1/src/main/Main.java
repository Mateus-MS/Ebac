package main;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
    }

    public static Map<Character, List<Pessoa>> getMappedList(String rawInput){

        return Arrays.stream(rawInput.split(", "))
                    .map(p -> {
                        String[] t = p.split("-");
                        return new Pessoa(t[0], Integer.parseInt(t[1].replaceAll(" ", "")), t[2].replaceAll(" ", "").charAt(0));
                    })
                    .collect(Collectors.groupingBy(Pessoa::getSexo));

    }

}