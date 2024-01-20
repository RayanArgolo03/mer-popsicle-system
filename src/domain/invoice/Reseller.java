package domain.invoice;

import domain.login.Login;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Reseller {

    private static int incrementId = 1;
    private final int id;
    private String name;
    private Login login;
    private ContactPerson contactPerson;

    public Reseller(String name, Login login, ContactPerson contactPerson) {
        id = incrementId++;
        this.name = name;
        this.login = login;
        this.contactPerson = contactPerson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reseller reseller = (Reseller) o;
        return Objects.equals(login, reseller.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }

    @Override
    public String toString() {
        return "Reseller " + name;
    }
}
