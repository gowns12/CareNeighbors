package careneighbors.guardian;


import jakarta.persistence.*;

@Entity
public class Guardian {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String residentNumber;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private Boolean isBlackList = false;

    protected Guardian() {
    }

    public Guardian(String name, String phoneNumber, String residentNumber, String location) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.residentNumber = residentNumber;
        this.location = location;
    }

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
}





