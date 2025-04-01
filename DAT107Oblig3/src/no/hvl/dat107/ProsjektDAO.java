package no.hvl.dat107;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class ProsjektDAO {
    private EntityManager em;

    public ProsjektDAO(EntityManager em) {
        this.em = em;
    }

    public void leggTilProsjekt(Prosjekt prosjekt) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(prosjekt);
        tx.commit();
    }

    public Prosjekt finnProsjektMedId(int id) {
        return em.find(Prosjekt.class, id);
    }
}
