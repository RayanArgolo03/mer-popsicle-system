package services;

import domain.invoice.Lot;
import domain.invoice.Reseller;
import domain.popsicle.*;
import enums.ConfirmChoice;
import enums.PriceConstant;
import enums.Packaging;
import enums.PopsicleType;
import exceptions.LotException;
import factory.PopsicleFactory;
import repository.LotRepository;

import java.math.BigDecimal;
import java.util.*;

public class LotService {

    private final LotRepository repository;

    public LotService() {
        this.repository = new LotRepository();
    }


    public boolean addLots(Collection<Lot> lots) {
        return repository.add(lots);
    }

    public void removeLots(List<Lot> lots) {
        repository.removeAll(lots);
    }

    public List<Lot> findLots(Reseller reseller) {
        return repository.findAllLots(reseller);
    }

    public boolean hasPurchases(Reseller reseller) {
        return !repository.findAllLots(reseller).isEmpty();
    }

    public Set<Lot> buyLots(Reseller reseller) {

        Map<PopsicleAbstract, Integer> popsiclesAndQuantities = new HashMap<>();
        Set<Lot> lots = new HashSet<>();

        ConfirmChoice confirmChoice;
        boolean newLot = true;
        PopsicleType popsicleType = null;

        System.out.println("Buying a few lots...");

        do {
            if (newLot) {
                //Exclusive lot set of type
                popsicleType = ReadService.readEnum("Choose the type of popsicles of this lot: ", PopsicleType.class);
                newLot = false;
            }

            System.out.println("Lot " + popsicleType + " of number " + (lots.size() + 1));

            Set<Flavor> flavors = addComponent("flavor");
            Set<Ingredient> ingredients = addComponent("ingredient");

            BigDecimal unitPrice = addUnitPrice(flavors.size());

            Packaging packaging = ReadService.readEnum("Choose the type of packaging for the popsicle:", Packaging.class);

            int quantity = receiveQuantity();

            PopsicleAbstract popsicle = PopsicleFactory
                    .newPopsicle(popsicleType, flavors, ingredients, unitPrice, packaging);

            if (popsiclesAndQuantities.containsKey(popsicle)) {
                System.out.println("This popsicle has alredy been puchased, incresing quantity..");
                increaseQuantity(popsiclesAndQuantities, popsicle, quantity);
            } else {
                addUniqueAtributtes(popsicle);
                popsiclesAndQuantities.put(popsicle, quantity);
            }

            confirmChoice = ReadService.readEnum("Do you want to keep buying popsicles in this lot?", ConfirmChoice.class);

            if (confirmChoice == ConfirmChoice.NO) {

                Lot lot = new Lot(popsiclesAndQuantities, reseller);
                boolean sucess = lots.add(lot);
                if (!sucess) {
                    System.out.println("Same lot as previously purchased, increasing last..");
                    increaseExistingPopsicles(lot, lots);
                }

                newLot = true;

                //New map
                popsiclesAndQuantities = new HashMap<>();
            }

            System.out.println();
            confirmChoice = ReadService.readEnum("Do you want to keep buying lots?", ConfirmChoice.class);

        } while (confirmChoice == ConfirmChoice.YES);

        return lots;
    }


    @SuppressWarnings("unchecked")
    private <T extends ComponentAbstract> Set<T> addComponent(final String title) {

        Set<T> components = new HashSet<>();
        ConfirmChoice confirmChoice;

        do {
            System.out.println("Enter the number " + (components.size() + 1) + " " + title + ": ");

            String name = ReadService.readString().toUpperCase();

            //Create enum for this?
            T component = switch (title) {
                case "flavor" -> (T) new Flavor(name);
                case "ingredient" -> (T) new Ingredient(name);
                default -> throw new LotException("Invalid component!");
            };

            boolean sucess = components.add(component);

            if (!sucess) {
                System.out.println("There's already this " + title + " in the popsicle!");
            }

            confirmChoice = ReadService.readEnum("Continue?", ConfirmChoice.class);

        } while (confirmChoice == ConfirmChoice.YES);

        return components;
    }

    private BigDecimal addUnitPrice(int flavorQuantity) {
        return PriceConstant.PRICE_PER_FLAVOR.getValue()
                .multiply(new BigDecimal(flavorQuantity));
    }

    private int receiveQuantity() {

        System.out.print("Quantity of popsicles: ");
        int quantity = ReadService.readInt();

        while (!validQuantity(quantity)) {
            System.out.println("Invalid!");
            System.out.print("Quantity of popsicles: ");
            quantity = ReadService.readInt();
        }

        return quantity;
    }

    private void increaseQuantity(Map<PopsicleAbstract, Integer> popsiclesAndQuantities, PopsicleAbstract popsicle, int quantity) {
        popsiclesAndQuantities.put(popsicle, popsiclesAndQuantities.get(popsicle) + quantity);
    }

    private void increaseExistingPopsicles(Lot repeatedLot, Set<Lot> lots) {

        //Memory adress poiting to the same location
        Map<PopsicleAbstract, Integer> existingLotMap = lots.stream()
                .filter(repeatedLot::equals)
                .findFirst().get()
                .getPopsiclesAndQuantities();

        repeatedLot.getPopsiclesAndQuantities().forEach((p, q) ->
                existingLotMap.merge(p, q, Integer::sum)
        );

    }

    private void addUniqueAtributtes(PopsicleAbstract popsicleAbstract) {
        if (popsicleAbstract instanceof NormalPopsicle normalPopsicle) {
            normalPopsicle.setNutritionalAdditives(MokService.getNutritionalAdditives());
        } else if (popsicleAbstract instanceof MilkPopsicle milkPopsicle) {
            milkPopsicle.setPreservatives(MokService.getPreservatives());
        }
    }

    private boolean validQuantity(int quantity) {
        return quantity > 0;
    }

}
