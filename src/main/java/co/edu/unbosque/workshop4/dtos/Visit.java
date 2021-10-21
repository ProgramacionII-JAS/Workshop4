package co.edu.unbosque.workshop4.dtos;

public class Visit {

    private int idVisit;
    private String date;
    private String type;
    private String description;
    private String vetId;
    private int petId;

    public Visit(int idVisit,String date,String type, String description,String vetId,int petId){
        this.idVisit = idVisit;
        this.date = date;
        this.type = type;
        this.description = description;
        this.vetId = vetId;
        this.petId = petId;
    }
    public int getIdVisit() { return idVisit;}
    public void setIdVisit(int  idVisit) {
        this.idVisit = idVisit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

