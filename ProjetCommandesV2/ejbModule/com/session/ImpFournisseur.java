package com.session;

import java.util.List;

import javax.persistence.Query;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.entities.Fournisseur;

@Stateless
public class ImpFournisseur implements ILocalFournisseur {

	@PersistenceContext(unitName = "ProjetCommandes")
	private EntityManager em;

	@Override
	public void addFournisseur(Fournisseur fournisseur) {
		em.persist(fournisseur);

	}

	@Override
	public void deleteFournisseur(Fournisseur fournisseur) {
		Fournisseur fournisseur2Delete = em.find(Fournisseur.class, fournisseur.getCode());
		if (fournisseur2Delete != null) {
			em.remove(fournisseur);
		}
	}

	@Override
	public void updateFournisseur(Fournisseur fournisseur) {
		em.merge(fournisseur);

	}

	@Override
	public Fournisseur getFournisseur(Integer code) {

		return em.find(Fournisseur.class, code);

	}

	@Override
	public List<Fournisseur> getAllFournisseurs() {
		Query query = em.createQuery("SELECT f FROM Fournisseur f");
		return query.getResultList();
	}

}
