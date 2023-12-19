package exceptions;

import java.io.Serial;

public class LotException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = -8399796078480391871L;

    public LotException(String message) {
        super(message);
    }
}
