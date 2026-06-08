package SocialCreditMS.View;

import AltStd.Altio.Print;
import AltStd.Altio.Reader;
import AltStd.Colors;
import SocialCreditMS.Controller.EmperorRequisitionController;
import SocialCreditMS.Model.Citizen;
import SocialCreditMS.Model.EmperorRequisition;
import SocialCreditMS.Util.ViewMessage;

import java.util.ArrayList;

public class EmperorRequisitionView {
    private static Reader sc = new Reader();
    private static ViewMessage message = null;

    private static void printMessage() {
        if(message == null) {
            return;
        }
        message.printMessage();
        message = null;
    }

    public static void menu() {
        int option = -1;
        while(option != 0) {
            printMessage();
            Print.nl(Colors.Blue, "\n-- Requisitions for the Emperor -- ");
            Print.nl(Colors.Default, "[1] Create Requisition [2]: List Requisitions [0]: Exit");
            option = sc.UntilInt("Option: ", "Invalid Option. Try again: ", (x) -> x >= 0 && x <= 2);
            switch(option) {
                case 1:
                    create();
                    break;
                case 2:
                    EmperorRequisitionController.list();
                    break;
            }
        }
    }

    public static void create() {
        Print.nl(Colors.Blue, "\n-- Create Requisition for the Emperor --");
        String name = sc.String(" Name: ");
        String description = sc.String(" Description: ");
        Citizen requester = null;
        message = EmperorRequisitionController.create(name, description, requester);
    }

    public static void edit(EmperorRequisition requisition) {
        Print.nl(Colors.Blue, "\n-- Edit Requisition for the Emperor --");
        String name = sc.String(" Name [" + requisition.getName() + "]: ");
        String description = sc.String(" Description: [" + requisition.getDescription() + "]: ");
        Citizen requester = null;
        message = EmperorRequisitionController.edit(requisition, name, description, requester);
    }

    public static void list(ArrayList<EmperorRequisition> requisitions) {
        if(requisitions.isEmpty()) {
            Print.nl(Colors.Yellow, "\n--- NO REQUISITIONS ---");
            return;
        }
        Print.nl(Colors.Blue, "\n---------------------------------------------------------------------------------------------------------------------------------------------------------------");
        Print.fl("| %-3s | | %-20s | | %-20s | | %-20s | | %-20s | | %-20s | | %-20s | |", "ID", "NAME", "DESC", "REQUESTER", "CREATION", "APPROVED", "RESPONSE");
        Print.nl(Colors.Blue, "---------------------------------------------------------------------------------------------------------------------------------------------------------------");
        for(int i = 0 ; i < requisitions.size(); i++) {
            var requisition = requisitions.get(i);
            Print.fl("| %-3d | | %-20s | | %-20s | | %-20s | | %-20s | | %-20s | | %-20s | |",
                    i + 1, requisition.getName(), requisition.getDescription(), requisition.getRequesterName(),
                    requisition.getFormattedCreatedAt(), requisition.isApproved() ? "YES" : "NO",
                    requisition.getEmperorResponse()
            );
        }
        Print.n(Colors.Default);

        Print.nl("\n[1] Edit [2] Delete [0] Exit");
        var option = sc.UntilInt("Option: ", "Invalid Option. Try Again: ", (x) -> x >= 0 && x < 3);

        if(option == 0) {
            return;
        }
        var requisitionId = sc.UntilInt("Id/Position: ", "Invalid Id/Position. Try Again: ", (x) -> x >= 0 && x <= requisitions.size());
        requisitionId--;

        if(requisitionId < 0) {
           return;
        }

        var requisition = requisitions.get(requisitionId);

        switch(option) {
            case 1:
                edit(requisition);
                break;
            case 2:
                message = EmperorRequisitionController.delete(requisition.getId());
                break;
        }
    }
}
