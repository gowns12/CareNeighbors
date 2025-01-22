package careneighbors.guardian;


import jakarta.persistence.*;

@Entity
public class Guardian {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String phoneNumber;

    @Column(nullable = false)
    String residentNumber;

    @Column(nullable = false)
    String location;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getResidentNumber() {
        return residentNumber;
    }

    public String getLocation() {
        return location;
    }

    public Boolean getBlackList() {
        return isBlackList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setResidentNumber(String residentNumber) {
        this.residentNumber = residentNumber;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Guardian() {
    }

    public Guardian(String name, String phoneNumber, String residentNumber, String location) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.residentNumber = residentNumber;
        this.location = location;
    }

    Boolean isBlackList = false;


}
