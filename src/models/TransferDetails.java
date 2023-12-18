package models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class TransferDetails {
    private int id;
    private int IdDebitorTransaction;
    private static int IdCreditorTransaction;
    private static Date transferDate;


    public TransferDetails(int i, int j, int k, Date date) {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdDebitorTransaction() {
        return IdDebitorTransaction;
    }
    public void setIdDebitorTransaction(int idDebitorTransaction) {
        IdDebitorTransaction = idDebitorTransaction;
    }
    public int getIdCreditorTransaction() {
        return IdCreditorTransaction;
    }
    public void setIdCreditorTransaction(int idCreditorTransaction) {
        IdCreditorTransaction = idCreditorTransaction;
    }
    public Date getTransferDate() {
        return transferDate;
    }
    public void setTransferDate(Date transferDate) {
        TransferDetails.transferDate = transferDate;
    }

    // Méthode pour obtenir l'historique des transferts dans une intervalle de date donnée
    public static List<TransferDetails> getTransfersInDateRange(List<TransferDetails> allTransfers, Date startDate, Date endDate) {
        List<TransferDetails> filteredTransfers = new ArrayList<>();
    
        for (TransferDetails transfer : allTransfers) {
            Date transferDate = transfer.getTransferDate();
            if (transferDate.after(startDate) || transferDate.equals(startDate)) {
                if (transferDate.before(endDate) || transferDate.equals(endDate)) {
                filteredTransfers.add(transfer);
                }
            }
        }
        
        return filteredTransfers;   
    }
    public String getAmount() {
        return null;
    }

    public class AriaryAccount {

        private List<TransferDetails> transfers;
    
        // Constructeur pour initialiser la liste de transferts
        public AriaryAccount() {
            this.transfers = new ArrayList<>();
        }
    
        // Méthode pour ajouter un transfert à la liste
        public void addTransfer(TransferDetails transfer) {
            transfers.add(transfer);
        }
    
        // Méthode pour calculer le solde actuel du compte Ariary
        public double calculateAriaryBalance(Date date) {
            double currentBalance = 0.0;
    
            for (TransferDetails transfer : transfers) {
                Date transferDate = transfer.getTransferDate();
                double euroAmount = getEuroAmount(transfer.getIdDebitorTransaction());
                double euroToAriaryRate = getEuroToAriaryRate(transferDate);
    
                if (transferDate.before(date) || transferDate.equals(date)) {
                    // Mettre à jour le solde en fonction du transfert
                    currentBalance += euroAmount * euroToAriaryRate;
                }
    
                // Vérifier si c'est une dépense et ajuster le solde en conséquence
                if (transferDate.equals(date) && transfer.getIdCreditorTransaction() == 0) {
                    // Supposons que les dépenses ont un IdCreditorTransaction de 0
                    currentBalance -= getExpenseAmount(); // Méthode à implémenter
                }
            }
    
            return currentBalance;
        }
    
        // Méthode à implémenter pour obtenir le montant en euro d'un transfert
        private double getEuroAmount(int debitorTransactionId) {
            return 1.0; // Exemple : montant en Euro
        }
    
        // Méthode à implémenter pour obtenir le taux de change Euro to Ariary à une date donnée
        private double getEuroToAriaryRate(Date date) {
            return 4600.0; // Exemple : taux de change
        }
    
        // Méthode à implémenter pour obtenir le montant de la dépense
        private double getExpenseAmount() {
            return 10000.0; // Exemple : montant de la dépense
        }
    
        // Exemple d'utilisation
        public static void main(String[] args) {
            TransferDetails transferDetails = new TransferDetails(IdCreditorTransaction, IdCreditorTransaction, IdCreditorTransaction, transferDate);
            TransferDetails.AriaryAccount account = transferDetails.new AriaryAccount();
    
            // Ajouter les transferts
            TransferDetails transfer1 = new TransferDetails(1, 1, 0, new Date(2023 - 1900)); // T1
            TransferDetails transfer2 = new TransferDetails(2, 1, 0, new Date(2023 - 1900)); // T2
            TransferDetails expense = new TransferDetails(3, 0, 1, new Date(2023 - 1900)); // Dépense
    
            account.addTransfer(transfer1);
            account.addTransfer(transfer2);
            account.addTransfer(expense);
    
            // Calculer le solde actuel à différentes dates
            System.out.println("Solde le 2023-12-05 12:00 PM : " + account.calculateAriaryBalance(new Date(2023 - 1900)));
            System.out.println("Solde le 2023-12-06 12:00 PM : " + account.calculateAriaryBalance(new Date(2023 - 1900)));
            System.out.println("Solde le 2023-12-06 4:00 PM : " + account.calculateAriaryBalance(new Date(2023 - 1900)));
        }
    }
    
}
