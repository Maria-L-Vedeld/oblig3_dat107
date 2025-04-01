package no.hvl.dat107;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class AvdelingDAO {
	private EntityManager em;

	public AvdelingDAO(EntityManager em) {
		this.em = em;
	}

	public Avdeling finnAvdelingMedId(int id) {
		return em.find(Avdeling.class, id);
	}

	public void leggTilAvdeling(Avdeling avdeling) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(avdeling);
		tx.commit();
	}
}
