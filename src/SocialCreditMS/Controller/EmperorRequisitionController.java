package SocialCreditMS.Controller;

import SocialCreditMS.Model.Citizen;
import SocialCreditMS.Model.EmperorRequisition;
import SocialCreditMS.Util.ViewMessage;
import SocialCreditMS.View.EmperorRequisitionView;
import SocialCreditMS.db.EmperorRequisitionRepository;

import java.util.UUID;

public class EmperorRequisitionController {

    private static final EmperorRequisitionRepository erRepo = new EmperorRequisitionRepository();

    public static void menu() {
        EmperorRequisitionView.menu();
    }

    public static ViewMessage create(String name, String description, Citizen requester) {
        var requisition = EmperorRequisition.create(name, description, requester);
        boolean saveSuccess = erRepo.save(requisition);
        if(saveSuccess) {
            return ViewMessage.Success("Successfully submitted");
        }
        return ViewMessage.Fail("Error in submitting");
    }

    public static void list() {
        var requisitions = erRepo.getAll();
        EmperorRequisitionView.list(requisitions);
    }

    public static ViewMessage delete(UUID userId) {
        var requisition = erRepo.getById(userId);
        if(requisition == null) {
            return ViewMessage.Fail("Requisition not Found");
        }
        boolean deleteSuccess = erRepo.delete(requisition);
        if(deleteSuccess) {
            return ViewMessage.Success("Requisition was Deleted");
        }
        return ViewMessage.Fail("Requisition not Found");
    }

    public static ViewMessage edit(EmperorRequisition requisition, String name, String description, Citizen requester) {
        if(!name.isEmpty())
            requisition.setName(name);
        if(!description.isEmpty())
            requisition.setDescription(description);
        requisition.setRequester(requester);
        boolean editSuccess = erRepo.save(requisition);
        if(editSuccess) {
            return ViewMessage.Success("Requisition was Updated");
        }
        return ViewMessage.Fail("Failed in saving changes");
    }

    public static void listResponses() {
        var requisitions = erRepo.getBy((r) -> r.getEmperorResponse().isEmpty());
        EmperorRequisitionView.listResponses(requisitions);
    }

    public static ViewMessage answer(EmperorRequisition requisition, boolean approved, String response) {
        requisition.setApproved(approved);
        requisition.setEmperorResponse(response);
        boolean editSuccess = erRepo.save(requisition);
        if(editSuccess) {
            return ViewMessage.Success("Requisition was answered");
        }
        return ViewMessage.Fail("Failed in saving changes");
    }
}
