package controllers;

import domain.invoice.Reseller;
import domain.login.Login;
import exceptions.ResellerException;
import services.ResellerService;

public class ResellerController {
    private final ResellerService service;

    public ResellerController() {
        this.service = new ResellerService();
    }

    public Login login() {
        if (!service.hasReseller()) throw new ResellerException("No resellers in the database!");
        return service.receiveLogin();
    }

    public Reseller create() {
        return service.createReseller();
    }

    public void add(Reseller reseller) {
        boolean sucess = service.addReseller(reseller);
        if (!sucess) throw new ResellerException("Reseller exist in the program!");
    }

    public void remove(Reseller reseller) {
        service.removeReseller(reseller);
    }

    public void update(Reseller reseller) {
        service.updateReseller(reseller);
    }


}
