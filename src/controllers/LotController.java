package controllers;

import domain.invoice.Lot;
import domain.invoice.Reseller;
import exceptions.LotException;
import services.LotService;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class LotController {
    private final LotService service;

    public LotController() {
        this.service = new LotService();
    }

    public Set<Lot> buy(Reseller reseller) {
        return service.buyLots(reseller);
    }

    //Collection interface for future implementations
    public void add(Collection<Lot> lots) {
        boolean sucess = service.addLots(lots);
        if (!sucess) throw new LotException("Error to adding all created lots!");
    }

    public void remove(List<Lot> lots) {
        service.removeLots(lots);
    }

    public List<Lot> find(Reseller reseller) {
        return service.findLots(reseller);
    }

    public boolean hasLotAndInvoice(Reseller reseller) {
        return service.hasPurchases(reseller);
    }

}
