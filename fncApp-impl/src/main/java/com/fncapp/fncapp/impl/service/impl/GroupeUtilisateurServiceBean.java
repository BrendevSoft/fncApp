/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.impl.service.impl;

import com.fncapp.fncapp.api.dao.GroupeUtilisateurDaoBeanLocal;
import com.fncapp.fncapp.api.dao.core.BaseDaoBeanLocal;
import com.fncapp.fncapp.api.entities.GroupeUtilisateur;
import com.fncapp.fncapp.api.entities.GroupeUtilisateurId;
import com.fncapp.fncapp.api.entities.Utilisateur;
import com.fncapp.fncapp.api.service.GroupeUtilisateurServiceBeanLocal;
import com.fncapp.fncapp.impl.service.core.impl.BaseServiceBean;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Brendev
 */
@Stateless
public class GroupeUtilisateurServiceBean extends BaseServiceBean<GroupeUtilisateur, GroupeUtilisateurId> implements GroupeUtilisateurServiceBeanLocal {

    @EJB
    private GroupeUtilisateurDaoBeanLocal gudbl;

    @Override
    public BaseDaoBeanLocal<GroupeUtilisateur, GroupeUtilisateurId> getDao() {
        return gudbl;
    }

    @Override
    public List<Utilisateur> getUtilisateursGroupe() {
        return this.gudbl.getUtilisateursGroupe();
    }

    @Override
    public List<Utilisateur> getUtilisateursNonGroupe() {
        return this.gudbl.getUtilisateursNonGroupe();
    }

    @Override
    public List<Utilisateur> getUtilisateursGroupee() {
        return this.gudbl.getUtilisateursGroupee();
    }

    @Override
    public List<Utilisateur> getUtilisateursNonGroupee() {
        return this.gudbl.getUtilisateursNonGroupee();
    }
}