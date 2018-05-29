/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.api.service;

import com.fncapp.fncapp.api.entities.GroupeUtilisateur;
import com.fncapp.fncapp.api.entities.GroupeUtilisateurId;
import com.fncapp.fncapp.api.entities.Utilisateur;
import com.fncapp.fncapp.api.service.core.BaseServiceBeanLocal;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Brendev
 */
@Remote
public interface GroupeUtilisateurServiceBeanLocal extends BaseServiceBeanLocal<GroupeUtilisateur,GroupeUtilisateurId> {

    public List<Utilisateur> getUtilisateursGroupe();

    public List<Utilisateur> getUtilisateursNonGroupe();

    public List<Utilisateur> getUtilisateursGroupee();

    public List<Utilisateur> getUtilisateursNonGroupee();
}
