/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.impl.dao.impl;

import com.fncapp.fncapp.api.dao.GroupeUtilisateurDaoBeanLocal;
import com.fncapp.fncapp.api.entities.Groupeutilisateur;
import com.fncapp.fncapp.api.entities.GroupeUtilisateurId;
import com.fncapp.fncapp.api.entities.Utilisateur;
import com.fncapp.fncapp.impl.dao.core.impl.BaseDaoBean;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Brendev
 */
@Stateless
public class GroupeUtilisateurDaoBean extends BaseDaoBean<Groupeutilisateur, GroupeUtilisateurId> implements GroupeUtilisateurDaoBeanLocal {

    public GroupeUtilisateurDaoBean() {
    }

    @Override
    public Class<Groupeutilisateur> getType() {
        return Groupeutilisateur.class;
    }

    @Override
    public List<Utilisateur> getUtilisateursGroupe() {
        return getEntityManager()
                .createQuery("SELECT p FROM Utilisateur p WHERE p NOT IN (SELECT pi.utilisateur FROM Groupeutilisateur pi)")
                .getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Utilisateur> getUtilisateursGroupee() {
        return getEntityManager()
                .createQuery("SELECT p FROM Utilisateur p,Groupeutilisateur pi WHERE pi.utilisateur = p")
                .getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Utilisateur> getUtilisateursNonGroupe() {
        return getEntityManager()
                .createQuery("SELECT p FROM Utilisateur p WHERE p IN (SELECT pi.utilisateur FROM Groupeutilisateur pi WHERE pi.utilisateur = p)")
                .getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Utilisateur> getUtilisateursNonGroupee() {
        return getEntityManager()
                .createQuery("SELECT p FROM Utilisateur p,Groupeutilisateur pi WHERE pi.utilisateur = p")
                .getResultList();
    }
    
    @Override
    public boolean supGroupeUtilisateurs(Groupeutilisateur groupeutilisateur) {
        try {
            em.createQuery("DELETE FROM Groupeutilisateur pr WHERE pr.groupe=:groupe AND pr.utilisateur=:utilisateur")
                    .setParameter("groupe", groupeutilisateur.getGroupe())
                    .setParameter("utilisateur", groupeutilisateur.getUtilisateur())
                    .executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
