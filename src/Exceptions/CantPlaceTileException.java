package Exceptions;

public class CantPlaceTileException extends IndexOutOfBoundsException{
    public CantPlaceTileException(String msg) {
        super(msg);
    }
}
