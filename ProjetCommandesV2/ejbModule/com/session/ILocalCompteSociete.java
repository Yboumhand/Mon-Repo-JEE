package com.session;

import java.util.List;

import javax.ejb.Local;

import com.entities.CompteSociete;

@Local
public interface ILocalCompteSociete {
	void addCompteSociete(CompteSociete compteSociete);

	void deleteCompteSociete(CompteSociete compteSociete);

	void updateCompteSociete(CompteSociete compteSociete);

	CompteSociete getCompteSociete(Integer code);

	List<CompteSociete> getAllComptesSociete();
}
