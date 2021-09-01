package dat3.jpademo.entities.facades;

import dat3.jpademo.entities.Person;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class PersonFacade {
    private static EntityManagerFactory emf;
    private static PersonFacade instance;

    private PersonFacade() {
    }

    public static PersonFacade getPersonFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    public Person createPerson(Person p){
        Person person = new Person(p.getName(), p.getYear());
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
            return person;
        }finally {
            em.close();
        }
    }

    public Person getPersonById(long p_id){
        EntityManager em = emf.createEntityManager();
        try{
            Person person = em.find(Person.class, p_id);
            return person;
        }finally {
            em.close();
        }
    }


    public List<Person> getAllPersons(){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Person> query = em.createQuery("Select name, p_id FROM Person p", Person.class);
            return query.getResultList();
        }finally {
            em.close();
        }
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        PersonFacade facade = PersonFacade.getPersonFacade(emf);
        //Add Person
        Person p1 = facade.createPerson(new Person("Person 1",2001));
        Person p2 = facade.createPerson(new Person("Person 2",2002));
        //Find Person by ID
        System.out.println("#"+facade.getPersonById(p1.getP_id()).getP_id() + " Person navn: "+facade.getPersonById(p1.getP_id()).getName());
        //Find all Persons
//        for (Person p: facade.getAllPersons()) {
//            System.out.println(p.getName());
//        }


    }

}
