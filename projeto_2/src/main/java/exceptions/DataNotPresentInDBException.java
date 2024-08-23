package main.java.exceptions;

public class DataNotPresentInDBException extends RuntimeException{
    public DataNotPresentInDBException(String err){
        super(err);
    }
}
