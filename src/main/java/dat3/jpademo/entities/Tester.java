package dat3.jpademo.entities;

import java.util.Iterator;
import java.util.List;
import javax.persistence.*;

public class Tester {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
       
        Person p1 = new Person("Kim", 1995);
        Person p2 = new Person("Hans", 1990);
        Address a1 = new Address("Store tov 1", 2330, "Kbh");
        Address a2 = new Address("gade", 2890, "Valby");
        
        p2.setAddress(a1);
        p1.setAddress(a2);
        
        Fee f1 = new Fee(100);
        Fee f2 = new Fee(300);
        Fee f3 = new Fee(300);
        Fee f4 = new Fee(300);
        
        p1.addFees(f1);
        p2.addFees(f2);
        p2.addFees(f3);
        p2.addFees(f4);
        
        
        em.getTransaction().begin();
            em.persist(p1);
            em.persist(p2);
        em.getTransaction().commit();
        
        System.out.println("Hans bor her: " + p2.getAddress().getStreet());
        System.out.println("Bi-directional mapping: " + a1.getPerson().getName());
        System.out.println("Hvem har betalt? "+f2.getPerson().getName());
        
        TypedQuery<Fee> q1 = em.createQuery("SELECT f FROM Fee f", Fee.class);
        List<Fee> fees = q1.getResultList();
        System.out.println("Hvem har betalt?");
        
        fees.forEach(f -> {
            System.out.println(f.getPerson().getName()+ ": "+ f.getAmount() + "kr. @ "+ f.getPayDate().getDate() + " Adrs: "+ f.getPerson().getAddress().getCity());
        });
    }
    
}
