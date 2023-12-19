package exceptions;

import java.io.Serial;

public class ResellerException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = -8399796078480391871L;

    public ResellerException(String message) {
        super(message);
    }
}
