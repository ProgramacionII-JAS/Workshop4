package co.edu.unbosque.workshop4.dtos;

public class PetCase {


    private String createAt;
    private String type;
    private String description;

    private int petId;

    public PetCase(String createAt, String type, String description, int petId) {

        this.createAt = createAt;
        this.type = type;
        this.description = description;

        this.petId = petId;
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

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }
}
