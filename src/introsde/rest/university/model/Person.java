package introsde.rest.university.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import introsde.rest.university.dao.LifeCoachDao;


@Entity  // indicates that this class is an entity to persist in DB
@Table(name="Person") // to whate table must be persisted
@NamedQuery(name="Person.findAll", query="SELECT p FROM Person p")
@XmlRootElement(name="person")
public class Person implements Serializable {
	
    private static final long serialVersionUID = 1L;
    @TableGenerator(name = "Person_Gen", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "Pers_Gen", initialValue = 100)
    @Id // defines this attributed as the one that identifies the entity 
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Person_Gen")
    @Column(name="idPerson") // maps the following attribute to a column
    private int idPerson;
    @Column(name="lastname")
    private String lastname;
    @Column(name="firstname")
    private String firstname;
	@Column(name="birthdate")
    private String birthdate;
    @OneToMany(
    		//mappedBy="person",
    		cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Activity> activities;
    
    public Person() {}
    
    @XmlAttribute(name = "person_id")
	public int getIdPerson() {
		return idPerson;
	}
	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	@Override
	public String toString() {
		return "Person [idPerson=" + idPerson + ", lastname=" + lastname + ", name=" + firstname + ", birthdate=" + birthdate
				+ "]";
	}
	
	//@XmlElementWrapper(name = "Activities")
	@XmlElement(name = "activity")
	public List<Activity> getActivities() {
	    return activities;
	}
	public void setActivities(List<Activity> activities2) {
		this.activities=activities2;
		
	}
	
    public static Person getPersonById(int personId) {
        EntityManager em = LifeCoachDao.instance.createEntityManager();
        Person p = em.find(Person.class, personId);
        LifeCoachDao.instance.closeConnections(em);
        return p;
    }
    
    public static List<Person> getAll() {
        EntityManager em = LifeCoachDao.instance.createEntityManager();
        List<Person> list = em.createNamedQuery("Person.findAll", Person.class)
            .getResultList();
        LifeCoachDao.instance.closeConnections(em);
        return list;
    }

    public static Person savePerson(Person p){
        EntityManager em = LifeCoachDao.instance.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(p);
        tx.commit();
        Person person = em.find(Person.class, p.getIdPerson());
        LifeCoachDao.instance.closeConnections(em);
        return person;
    } 

    public static Person updatePerson(Person p) {
        EntityManager em = LifeCoachDao.instance.createEntityManager(); 
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        p=em.merge(p);
        tx.commit();
        Person person = em.find(Person.class, p.getIdPerson());
        LifeCoachDao.instance.closeConnections(em);
        return person;
    }

    public static void removePerson(Person p) {
        EntityManager em = LifeCoachDao.instance.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        p=em.merge(p);
        em.remove(p);
        tx.commit();
        LifeCoachDao.instance.closeConnections(em);
    }
    
    
}