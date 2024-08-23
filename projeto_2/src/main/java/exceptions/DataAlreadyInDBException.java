package main.java.exceptions;

public class DataAlreadyInDBException extends RuntimeException{
    public DataAlreadyInDBException(String err){
        super(err);
    }
}
