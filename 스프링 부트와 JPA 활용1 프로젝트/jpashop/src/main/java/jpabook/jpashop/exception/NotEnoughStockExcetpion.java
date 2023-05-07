package jpabook.jpashop.exception;

public class NotEnoughStockExcetpion extends RuntimeException{

    public NotEnoughStockExcetpion() {
        super();
    }

    public NotEnoughStockExcetpion(String message) {
        super(message);
    }

    public NotEnoughStockExcetpion(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughStockExcetpion(Throwable cause) {
        super(cause);
    }
}
