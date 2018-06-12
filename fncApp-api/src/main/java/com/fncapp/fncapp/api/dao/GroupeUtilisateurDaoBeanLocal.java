/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.api.dao;

import com.fncapp.fncapp.api.dao.core.BaseDaoBeanLocal;
import com.fncapp.fncapp.api.entities.GroupeUtilisateurId;
import com.fncapp.fncapp.api.entities.Groupeutilisateur;
import com.fncapp.fncapp.api.entities.Utilisateur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Brendev
 */
@Local
public interface GroupeUtilisateurDaoBeanLocal extends BaseDaoBeanLocal<Groupeutilisateur, GroupeUtilisateurId> {

    public List<Utilisateur> getUtilisateursGroupe();

    public List<Utilisateur> getUtilisateursGroupee();

    public List<Utilisateur> getUtilisateursNonGroupe();

    public List<Utilisateur> getUtilisateursNonGroupee();

    public boolean supGroupeUtilisateurs(Groupeutilisateur groupeutilisateur);
}
