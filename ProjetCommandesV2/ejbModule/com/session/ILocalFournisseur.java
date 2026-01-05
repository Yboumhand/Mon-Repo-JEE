package com.session;

import java.util.List;

import javax.ejb.Local;

import com.entities.Fournisseur;

@Local
public interface ILocalFournisseur {

	void addFournisseur(Fournisseur fournisseur);

	void deleteFournisseur(Fournisseur fournisseur);

	void updateFournisseur(Fournisseur fournisseur);

	Fournisseur getFournisseur(Integer code);

	List<Fournisseur> getAllFournisseurs();

}
