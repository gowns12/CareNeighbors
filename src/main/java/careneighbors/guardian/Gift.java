package careneighbors.guardian;


import careneighbors.caregiver.Caregiver;
import jakarta.persistence.*;

@Entity
public class Gift {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private String message;



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Gift() {
    }

    public Caregiver getCaregiver() {
        return caregiver;
    }

    public void setCaregiver(Caregiver caregiver) {
        this.caregiver = caregiver;
    }

    public Gift(Caregiver caregiver, String message) {
        this.caregiver = caregiver;
        this.message = message;
    }

    @ManyToOne
    private Caregiver caregiver;




}
