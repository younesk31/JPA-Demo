package dat3.jpademo.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
public class Fee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int amount;
    @Temporal(TemporalType.DATE)
    private Date payDate;
    
    @ManyToOne
    private Person person;

    public Fee() {
    }

    public Fee(int amount) {
        this.amount = amount;
        this.payDate = new Date();
    }

    public Long getId() {
        return id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }
    
    
    

    
}
