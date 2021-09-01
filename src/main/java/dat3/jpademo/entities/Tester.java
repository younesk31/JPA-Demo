package dat3.jpademo.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.*;

public class Tester {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        List<Person> persons = new ArrayList<Person>();
        persons.add(new Person("Albert", 1990));
        persons.add(new Person("Ben", 1990));
        persons.add(new Person("Claus", 1990));
        persons.add(new Person("David", 1990));
        persons.add(new Person("Esther", 1990));
        persons.add(new Person("Frank", 1990));
        persons.add(new Person("Gustav", 1990));
        persons.add(new Person("Hans", 1990));
        persons.add(new Person("Irma", 1990));
        persons.add(new Person("Jens", 1990));
        persons.add(new Person("Kim", 1990));

        for (int i = 0; i < 10; i++) {
            persons.get(i).setAddress(new Address("Store tov "+(i+1), 2890+i, "Kbh"));
        }
 
        for (int i = 0; i < 10; i++) {
            persons.get(i).addFees(new Fee(100*i));
        };

        SwimStyle s1 = new SwimStyle("Crawl");
        SwimStyle s2 = new SwimStyle("Butterfly");

        for (int i = 0; i < 4; i++) {
            persons.get(i).addSwimStyle(s1);
        };
        for (int i = 5; i < 10; i++) {
            persons.get(i).addSwimStyle(s2);
        };
        
        em.getTransaction().begin();
           for (int i = 0; i < 10; i++) {
            em.persist(persons.get(i));
        }
        em.getTransaction().commit();


        System.out.println("Alberts gade: " + persons.get(0).getAddress().getStreet());
        System.out.println("Lad os se om to-vejs virker: " + persons.get(0).getAddress().getPerson().getName());
        System.out.println("Hvem har betalt? Det har: ");
        for (int i = 0; i < 10; i++) {
            System.out.print((i+1)+": "+persons.get(i).getFees().get(0).getPerson().getName() +" ");
        }


        TypedQuery<Fee> q1 = em.createQuery("SELECT f FROM Fee f", Fee.class);
        List<Fee> fees = q1.getResultList();

        System.out.println("\n\n------ Hvad der er blevet betalt i alt ------");
        for (Fee f : fees) {
            System.out.println(f.getPerson().getName() + ": " + f.getAmount()
                    + "kr. Adr: " + f.getPerson().getAddress().getStreet());
        }

    }
    
}
