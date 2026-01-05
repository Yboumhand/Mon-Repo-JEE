package com.session;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.entities.Produit;

@Stateless
public class ImpProduit implements ILocalProduit {

	@PersistenceContext(unitName = "ProjetCommandes")
	private EntityManager em;

	@Override
	public void addProduit(Produit produit) {
		em.persist(produit);

	}

	@Override
	public void deleteProduit(Produit produit) {
		Produit produit2Delete = em.find(Produit.class, produit.getNumero());
		if (produit2Delete != null) {
			em.remove(produit2Delete);
		}
	}

	@Override
	public void updateProduit(Produit produit) {
		em.merge(produit);

	}

	@Override
	public Produit getProduit(Integer code) {
		return em.find(Produit.class, code);
	}

	@Override
	public List<Produit> getAllProduits() {
		Query query = em.createQuery("SELECT p FROM Produit p");
		return query.getResultList();
	}

}
