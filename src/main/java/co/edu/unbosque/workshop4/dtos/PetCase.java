package co.edu.unbosque.workshop4.dtos;

public class PetCase {

    private int caseId;
    private String createAt;
    private String type;
    private String description;
    private String vetId;
    private int petId;

    public PetCase(int caseId, String createAt, String type, String description, String vetId, int petId) {
        this.caseId = caseId;
        this.createAt = createAt;
        this.type = type;
        this.description = description;
        this.vetId = vetId;
        this.petId = petId;
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVetId() {
        return vetId;
    }

    public void setVetId(String vetId) {
        this.vetId = vetId;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }
}
