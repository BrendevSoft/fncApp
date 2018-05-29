/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.impl.service.impl;

import com.fncapp.fncapp.api.dao.UtilisateurDaoBeanLocal;
import com.fncapp.fncapp.api.dao.core.BaseDaoBeanLocal;
import com.fncapp.fncapp.api.entities.Utilisateur;
import com.fncapp.fncapp.api.service.UtilisateurServiceBeanLocal;
import com.fncapp.fncapp.impl.service.core.impl.BaseServiceBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Brendev
 */
@Stateless
public class UtilisateurServiceBean extends BaseServiceBean<Utilisateur, Long> implements UtilisateurServiceBeanLocal {

    @EJB
    private UtilisateurDaoBeanLocal udbl;

    @Override
    public BaseDaoBeanLocal<Utilisateur, Long> getDao() {
        return udbl;
    }
}
