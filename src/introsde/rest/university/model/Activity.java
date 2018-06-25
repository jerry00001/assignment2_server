package introsde.rest.university.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import introsde.rest.university.dao.LifeCoachDao;


@Entity  // indicates that this class is an entity to persist in DB
@Table(name="Activity") // to whate table must be persisted
@NamedQuery(name="Activity.findAll", query="SELECT p FROM Activity p")
@XmlRootElement(name="activity")
public class Activity implements Serializable {
	
    private static final long serialVersionUID = 1L;
    @Id // defines this attributed as the one that identifies the entity
    @TableGenerator(name = "Activity_Gen", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "Act_Gen", initialValue = 500)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Activity_Gen")
    @Column(name="idActivity") // maps the following attribute to a column
    private int idActivity;
    @Column(name="name")
    private String name;
    @Column(name="description")
    private String description;
    @Column(name="place")
    private String place;
    @Column(name="type")
    private ActivityType type;
    @ElementCollection
    Set<String> oldTypes;
	@Column(name="startdate")
    private String startdate;
    //@ManyToOne
	//@JoinColumn(name="idPerson",referencedColumnName="idPerson")
    //private Person person;

	public Activity() {}
    
    @XmlAttribute(name = "activity_id")
	public int getIdActivity() {
		return idActivity;
	}
	public void setIdActivity(int idActivity) {
		this.idActivity = idActivity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public ActivityType getType() {
		return type;
	}
	public void setType(ActivityType type) {
		this.type = type;
	}
	public Set<String> getOldTypes() {
		return oldTypes;
	}
	public void setOldTypes(Set<String> oldTypes) {
		this.oldTypes = oldTypes;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	@Override
	public String toString() {
		return "Activity [idActivity=" + idActivity + ", name=" + name + ", description=" + description + ", place="
				+ place + ", type=" + type.name() + ", startdate=" + startdate + "]";
	}
	
	// we make this transient for JAXB to avoid and infinite loop on serialization
//	@XmlTransient
//	public Person getPerson() {
//	    return person;
//	}
//	public void setPerson(Person person) {
//		this.person = person;
//	}
	
	public static Activity saveActivity(Activity ac) {
        EntityManager em = LifeCoachDao.instance.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(ac);
        tx.commit();
        LifeCoachDao.instance.closeConnections(em);
        return ac;
    }
	
	 
	public static Activity getActivityById(int lifestatusId) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		Activity p = em.find(Activity.class, lifestatusId);
		LifeCoachDao.instance.closeConnections(em);
		return p;
	}
	
	public static List<Activity> getAll() {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
	    List<Activity> list = em.createNamedQuery("Activity.findAll", Activity.class).getResultList();
	    LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static Activity updateActivity(Activity p) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		p=em.merge(p);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return p;
	}
	
	public static void removeActivity(Activity p) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    p=em.merge(p);
	    em.remove(p);
	    tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	}
	
    
    
}
