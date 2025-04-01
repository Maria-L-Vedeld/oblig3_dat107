package no.hvl.dat107;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class AnsattDAO {
    private EntityManager em;

    public AnsattDAO(EntityManager em) {
        this.em = em;
    }

    // Finne ansatt basert på ID
    public Ansatt finnAnsattMedId(int id) {
        return em.find(Ansatt.class, id);
    }

    // Finne ansatt basert på brukernavn
    public Ansatt finnAnsattMedBrukernavn(String brukernavn) {
        TypedQuery<Ansatt> query = em.createQuery("SELECT a FROM Ansatt a WHERE a.brukernavn = :brukernavn", Ansatt.class);
        query.setParameter("brukernavn", brukernavn);
        List<Ansatt> resultater = query.getResultList();
        return resultater.isEmpty() ? null : resultater.get(0);
    }

    // Liste ut alle ansatte
    public List<Ansatt> hentAlleAnsatte() {
        TypedQuery<Ansatt> query = em.createQuery("SELECT a FROM Ansatt a", Ansatt.class);
        return query.getResultList();
    }

    // Oppdatere stilling eller lønn
    public void oppdaterAnsatt(int id, String nyStilling, Double nyLonn) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Ansatt ansatt = em.find(Ansatt.class, id);
        if (ansatt != null) {
            if (nyStilling != null) ansatt.setStilling(nyStilling);
            if (nyLonn != null) ansatt.setManedslonn(nyLonn);
            em.merge(ansatt);
        }
        tx.commit();
    }

    // Legge til ny ansatt
    public void leggTilAnsatt(Ansatt ansatt) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(ansatt);
        tx.commit();
    }
}
