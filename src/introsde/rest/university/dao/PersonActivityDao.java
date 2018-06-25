package introsde.rest.university.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public enum PersonActivityDao {
    instance;
    private EntityManagerFactory emf;

    private PersonActivityDao() {
        if (emf!=null) {
            emf.close();
        }
      emf = Persistence.createEntityManagerFactory("asgn2");
        

        

    }

    public EntityManager createEntityManager() {
        try {
            return emf.createEntityManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;    
    }

    public void closeConnections(EntityManager em) {
        em.close();
    }

    public EntityTransaction getTransaction(EntityManager em) {
        return em.getTransaction();
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
    
    
}
