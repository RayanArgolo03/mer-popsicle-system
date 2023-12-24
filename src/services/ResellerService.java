package services;

import domain.invoice.ContactPerson;
import domain.invoice.Reseller;
import domain.login.Login;
import enums.UpdateOption;
import exceptions.ResellerException;
import repository.ResellerRepository;

import java.util.Optional;

public final class ResellerService {

    private final ResellerRepository repository;
    private boolean newAccountCreated;

    public ResellerService() {
        this.repository = new ResellerRepository();
        newAccountCreated = false;
    }

    public boolean hasReseller() {
        return !repository.getResellers().isEmpty();
    }

    public boolean hasNewAccountCreated() {
        return newAccountCreated;
    }

    public void toggleAccountStatus(boolean status) {
        newAccountCreated = status;
    }

    //Mok reseller
    public Reseller createReseller() {

        String name = ReadService.readString("name");
        if (!validName(name)) throw new ResellerException("Invalid name!");

        String username = ReadService.readString("username");
        if (!validLoginInformation(username)) throw new ResellerException("Invalid username!");

        String password = ReadService.readString("password");
        if (!validLoginInformation(password)) throw new ResellerException("Invalid password!");

        String contactPersonName = ReadService.readString("contact person name");
        if (!validName(contactPersonName)) throw new ResellerException("Invalid contact person name!");

        String phoneNumber = ReadService.readString("contact person phone number");
        if (!validPhoneNumber(phoneNumber)) throw new ResellerException("Invalid phone number!");

        return new Reseller(name, new Login(username, password),
                new ContactPerson(name, phoneNumber));
    }

    public Login receiveLogin() {

        String username = ReadService.readString("username");
        if (!validLoginInformation(username)) throw new ResellerException("Invalid username!");

        String password = ReadService.readString("password");
        if (!validLoginInformation(password)) throw new ResellerException("Invalid password!");

        return new Login(username, password);
    }

    public boolean addReseller(Reseller reseller) {
        return repository.add(reseller);
    }

    public void removeReseller(Reseller reseller) {
        repository.remove(reseller);
    }

    public Optional<Reseller> findReseller(Login login) {
        return repository.findByLogin(login);
    }

    public Reseller getLastResellerAdded() {
        return repository.getLastReseller();
    }

    public void updateReseller(Reseller reseller) {
        UpdateOption updateOption = ReadService.readEnum("Choose your reseller update option: ", UpdateOption.class);
        switch (updateOption) {
            case NAME -> updateName(reseller);
            case CONTACT -> updateContact(reseller);
        }
    }

    private void updateName(Reseller reseller) {
        String name = ReadService.readString("name");
        if (!validName(name, reseller.getName()))
            throw new ResellerException("Name is the same as the old name or uses illegal characters!");
        reseller.setName(name);
    }

    private void updateContact(Reseller reseller) {

        String name = ReadService.readString("name");
        if (!validName(name)) throw new ResellerException("Invalid name!");

        String phoneNumber = ReadService.readString("phone number");
        if (!validPhoneNumber(phoneNumber)) throw new ResellerException("Invalid name!");

        ContactPerson contactPerson = new ContactPerson(name, phoneNumber);
        if (!validNewContactPerson(contactPerson, reseller.getContactPerson()))
            throw new ResellerException("Contact person exists!");

        reseller.setContactPerson(contactPerson);
    }

    //Generic method for compare to equals objects
    private <T> boolean areEqual(T t1, T t2) {
        return t1.equals(t2);
    }


    private boolean validName(String name) {
        //Generic condition
        return name.length() > 2;
    }

    private boolean validLoginInformation(String information) {
        return information.length() > 3;
    }

    private boolean validName(String name, String oldName) {
        return !areEqual(name, oldName)
                && validName(name);
    }


    private boolean validPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\(?(\\b[0-9]{2,3}|0((x|[0-9]){2,3}[0-9]{2}))\\)?\\s*[0-9]{4,5}[- ]*[0-9]{4}\\b");
    }

    private boolean validNewContactPerson(ContactPerson contactPerson, ContactPerson oldContactPerson) {
        return !areEqual(contactPerson, oldContactPerson);
    }


}
