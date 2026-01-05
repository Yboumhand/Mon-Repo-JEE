package com.session;

import java.util.List;

import javax.ejb.Local;

import com.entities.Produit;

@Local
public interface ILocalProduit {

	void addProduit(Produit produit);

	void deleteProduit(Produit produit);

	void updateProduit(Produit produit);

	Produit getProduit(Integer code);

	List<Produit> getAllProduits();

}
