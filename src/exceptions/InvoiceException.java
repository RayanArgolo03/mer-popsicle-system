package exceptions;

import java.io.Serial;

public class InvoiceException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = -8399796078480391871L;

    public InvoiceException(String message) {
        super(message);
    }
}
