package services;

import controllers.InvoiceController;
import controllers.LotController;
import controllers.ResellerController;
import domain.invoice.Invoice;
import domain.invoice.Lot;
import domain.invoice.Reseller;
import enums.ConfirmChoice;
import enums.ResellerOption;
import enums.SystemChoice;
import exceptions.InvoiceException;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Set;

public final class RunService {

    public static void run() {

        ResellerController resellerController = new ResellerController();
        LotController lotController = new LotController();
        InvoiceController invoiceController = new InvoiceController();

        outer:
        do {
            try {

                //Login automatically if account has been created
                SystemChoice systemChoice = (resellerController.isAutomaticLogin())
                        ? SystemChoice.LOGIN
                        : ReadService.readEnum("Choose your action", SystemChoice.class);

                switch (systemChoice) {

                    case LOGIN -> {

                        //Log in or get the latest reseller addition
                        Reseller reseller = resellerController.setInstance();
                        System.out.println("Welcome, " + reseller.getName() + "!");

                        inner:
                        while (true) {

                            try {

                                ResellerOption resellerOption = ReadService.readEnum("Choose your action: ", ResellerOption.class);

                                switch (resellerOption) {

                                    case BUY -> {
                                        //Buy popsicles
                                        Set<Lot> lots = lotController.buy(reseller);
                                        lotController.add(lots);

                                        Invoice invoice = invoiceController.create(lots);
                                        invoiceController.add(invoice);
                                        System.out.println("Emission invoice date: " + invoice.getIssueDateInString());
                                    }

                                    case SEARCH -> {
                                        //Search invoice in the purchases
                                        if (lotController.hasLotAndInvoice(reseller)) {

                                            Invoice invoice = invoiceController.find(reseller)
                                                    .orElseThrow(() -> new InvoiceException("Not found!"));

                                            System.out.println(invoice);

                                        } else {
                                            System.out.println("Without invoices!");
                                        }


                                    }

                                    case UPDATE_INFORMATIONS -> {
                                        //Update informations of reseller log
                                        resellerController.update(reseller);
                                        System.out.println("Updated!");
                                    }

                                    case DELETE_ACCOUNT -> {

                                        ConfirmChoice confirmChoice = ReadService.readEnum("Are you sure you want to delete your account?", ConfirmChoice.class);
                                        if (confirmChoice.equals(ConfirmChoice.YES)) {

                                            if (lotController.hasLotAndInvoice(reseller)) {

                                                List<Lot> lots = lotController.find(reseller);
                                                int quantity = lots.size();
                                                lotController.remove(lots);

                                                System.out.println(quantity + " Lots removed!");

                                                List<Invoice> invoices = invoiceController.findInvoices(reseller);
                                                quantity = invoices.size();
                                                invoiceController.remove(invoices);

                                                System.out.println(quantity + " Invoices removed!");
                                            }


                                            resellerController.remove(reseller);
                                            break inner;
                                        }
                                    }

                                    case LOGOUT -> {

                                        if (lotController.hasLotAndInvoice(reseller)) {

                                            System.out.println("Your invoices: ");
                                            invoiceController.findInvoices(reseller)
                                                    .forEach(System.out::println);
                                        }

                                        System.out.println(reseller.getName() + " offline!");
                                        break inner;
                                    }

                                }

                            }

                            //Data type error on input
                            catch (InputMismatchException e) {
                                System.out.println("Invalid entry!");
                                break outer;
                            }

                            //Error inside of program logic
                            catch (Exception e) {
                                System.out.println("Error! " + e.getMessage());
                            }

                        }


                    }

                    case CREATE_RESELLER -> {
                        Reseller reseller = resellerController.create();
                        resellerController.add(reseller);
                        System.out.println("Create!");

                        //Will automatically log you into the next pass
                        resellerController.setAutomaticLogin(true);
                    }

                    case STOP -> {
                        break outer;
                    }

                }

            }//Data type error on input
            catch (InputMismatchException e) {
                System.out.println("Invalid entry!");
                break outer;
            }

            //Error outside of program logic
            catch (Exception e) {
                System.out.println("Error! " + e.getMessage());
            }

        } while (true);


    }

}
