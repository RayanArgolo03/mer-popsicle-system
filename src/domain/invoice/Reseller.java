package domain.invoice;

import domain.login.Login;

import java.util.*;

public class Reseller {
    private String name;
    private Login login;
    private ContactPerson contactPerson;
    private boolean online = false;

    public Reseller(String name, Login login, ContactPerson contactPerson) {
        this.name = name;
        this.login = login;
        this.contactPerson = contactPerson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContactPerson getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(ContactPerson contactPerson) {
        this.contactPerson = contactPerson;
    }

    public Login getLogin() {
        return login;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline() {
        this.online = true;
    }

    public void setOffline() {
        this.online = false;
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
