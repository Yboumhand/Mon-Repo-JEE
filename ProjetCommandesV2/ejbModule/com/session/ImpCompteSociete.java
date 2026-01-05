package com.session;

import com.entities.CompteSociete;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ImpCompteSociete implements ILocalCompteSociete {

	@PersistenceContext(unitName = "ProjetCommandes")
	private EntityManager em;

	@Override
	public void addCompteSociete(CompteSociete compte) {
		em.persist(compte);
	}

	@Override
	public void deleteCompteSociete(CompteSociete compte) {
		CompteSociete compteToDelete = em.find(CompteSociete.class, compte.getNumero());
		if (compteToDelete != null) {
			em.remove(compteToDelete);
		}
	}

	@Override
	public void updateCompteSociete(CompteSociete compte) {
		em.merge(compte);
	}

	@Override
	public CompteSociete getCompteSociete(Integer numero) {
		return em.find(CompteSociete.class, numero);
	}

	@Override
	public List<CompteSociete> getAllComptesSociete() {
		Query query = em.createQuery("SELECT cs FROM CompteSociete cs");
		return query.getResultList();
	}
}