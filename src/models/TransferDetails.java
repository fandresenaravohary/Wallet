package models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class TransferDetails {
    private int id;
    private int IdDebitorTransaction;
    private int IdCreditorTransaction;
    private Date transferDate;


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
        this.transferDate = transferDate;
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
}
