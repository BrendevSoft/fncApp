/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.impl.service.impl;

import com.fncapp.fncapp.api.dao.CompteurDaoBeanLocal;
import com.fncapp.fncapp.api.dao.core.BaseDaoBeanLocal;
import com.fncapp.fncapp.api.entities.Compteur;
import com.fncapp.fncapp.api.service.CompteurServiceBeanLocal;
import com.fncapp.fncapp.impl.service.core.impl.BaseServiceBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Edson PAKOU
 */
@Stateless
public class CompteurServiceBean extends BaseServiceBean<Compteur, Long> implements CompteurServiceBeanLocal{

    @EJB
    private CompteurDaoBeanLocal cdbl;
    
    @Override
    protected BaseDaoBeanLocal<Compteur, Long> getDao(){
        return cdbl;
    }
}