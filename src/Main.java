import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import models.TransferDetails;

public class Main {
    public static void main(String[] args) {
            // Supposons que vous avez une liste de tous les transferts
    List<TransferDetails> allTransfers = new ArrayList<>();

    TransferDetails transfer1 = new TransferDetails();
    TransferDetails transfer2 = new TransferDetails();

    // Spécifiez l'intervalle de dates
    Date startDate = ...; // Remplacez par la date de début souhaitée
    Date endDate = ...;   // Remplacez par la date de fin souhaitée

    // Obtenez l'historique des transferts dans l'intervalle de dates
    List<TransferDetails> filteredTransfers = getTransfersInDateRange(allTransfers, startDate, endDate);

    // Affichez les résultats
    for (TransferDetails transfer : filteredTransfers) {
        System.out.println("Compte débiteur: " + transfer.getIdDebitorTransaction());
        System.out.println("Compte créditeur: " + transfer.getIdCreditorTransaction());
        System.out.println("Montant du transfert: " + transfer.getAmount()); // Ajoutez la méthode appropriée si nécessaire
        System.out.println("Date du transfert: " + transfer.getTransferDate());
        System.out.println("--------------");
    }
    }

    private static List<TransferDetails> getTransfersInDateRange(List<TransferDetails> allTransfers, Date startDate,
            Date endDate) {
        return null;
    }
}