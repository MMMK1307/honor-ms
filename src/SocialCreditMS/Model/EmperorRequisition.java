package SocialCreditMS.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.json.JSONObject;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class EmperorRequisition extends BaseModel {

    private EmperorRequisition() {}

    private EmperorRequisition(UUID id, String name, String description, Citizen requester, LocalDateTime createdAt, boolean approved, String emperorResponse) {
        super(id, "emperor_requisition");
        this.name = name;
        this.description = description;
        this.requester = requester;
        this.createdAt = createdAt;
        this.approved = approved;
        this.emperorResponse = emperorResponse;
        if(requester != null) {
            _requester_id = requester.getId();
        }
    }

    private String name;

    private String description;

    @JsonIgnore
    private Citizen requester;
    private UUID _requester_id;

    private LocalDateTime createdAt;
    private boolean approved;
    private String emperorResponse;


    public static EmperorRequisition create(String name, String description, Citizen requester) {
        LocalDateTime createAt = LocalDateTime.now();
        return new EmperorRequisition(
                UUID.randomUUID(), name,  description, requester, createAt, false, ""
        );
    }

    public static EmperorRequisition createFromJson(JSONObject jsonData) {
        return new ObjectMapper().readValue(jsonData.toString(), EmperorRequisition.class);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getRequesterName() {
        if (requester == null) {
            return "";
        }
        return requester.getName();
    }

    public Citizen getRequester() {
        return requester;
    }

    public UUID getRequesterId() {
        return _requester_id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getFormattedCreatedAt() {
        return createdAt.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }

    public boolean isApproved() {
        return approved;
    }

    public String getEmperorResponse() {
        return emperorResponse;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRequester(Citizen requester) {
        this.requester = requester;
        if(requester != null) {
            this._requester_id = requester.getId();
        } else {
            this._requester_id = null;
        }
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public void setEmperorResponse(String emperorResponse) {
        this.emperorResponse = emperorResponse;
    }
}
