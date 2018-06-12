/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.impl.dao.impl;

import com.fncapp.fncapp.api.dao.UtilisateurDaoBeanLocal;
import com.fncapp.fncapp.api.entities.Utilisateur;
import com.fncapp.fncapp.impl.dao.core.impl.BaseDaoBean;
import javax.ejb.Stateless;

/**
 *
 * @author Brendev
 */
@Stateless
public class UtilisateurDaoBean extends BaseDaoBean<Utilisateur, Long> implements UtilisateurDaoBeanLocal {

    public UtilisateurDaoBean() {
    }

    public Class<Utilisateur> getType() {
        return Utilisateur.class;
    }

}
