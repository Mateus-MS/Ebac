package services.client;

import annotations.Serviceidentifier;

@Serviceidentifier("getCpf")
public class Client {
    private final String    name;
    private final int       age;
    private final String    address;
    private final Character sex;
    private final String    cpf;

    public Client(String _name, int _age, String _address, Character _sex, String _cpf){
        this.name    = _name;
        this.age     = _age;
        this.address = _address;
        this.sex     = _sex;
        this.cpf     = _cpf;
    }

    //The way to uniquely identify a Client
    public String getCpf(){
        return this.cpf;
    }

    @Override
    public String toString(){
        return  "Name     : " + this.name + System.lineSeparator() +
                "Age      : " + this.age + System.lineSeparator() +
                "Address  : " + this.address + System.lineSeparator() +
                "Sex      : " + this.sex + System.lineSeparator() +
                "CPF      : " + this.cpf + System.lineSeparator();
    }

    //Getters
    public String getName(){
        return this.name;
    }

    public int getAge(){
        return this.age;
    }

    public String getAddress() {
        return this.address;
    }

    public Character getSex(){
        return this.sex;
    }

}
