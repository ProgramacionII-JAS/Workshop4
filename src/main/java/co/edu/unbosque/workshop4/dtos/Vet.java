package co.edu.unbosque.workshop4.dtos;

public class Vet {

    private String userName;
    private String name;
    private String address;
    private String neighborhood;

    public Vet(String userName, String name, String address, String neighborhood){
        this.userName = userName;
        this.name = name;
        this.address = address;
        this.neighborhood = neighborhood;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }
}
