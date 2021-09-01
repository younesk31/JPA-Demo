package dat3.jpademo.entities;

import javax.persistence.*;

public class Tester {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
       
        Person p1 = new Person("Kim", 1995);
        Person p2 = new Person("Hans", 1990);
        
        em.getTransaction().begin();
        em.persist(p1);
        em.persist(p2);
        em.getTransaction().commit();
        
        System.out.println("P1: " + p1.getP_id() + " -  P2: " + p2.getP_id());
        
        
    }
    
}