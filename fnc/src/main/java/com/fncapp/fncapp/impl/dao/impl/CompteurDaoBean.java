/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.impl.dao.impl;


import com.fncapp.fncapp.api.dao.CompteurDaoBeanLocal;
import com.fncapp.fncapp.api.entities.Compteur;
import com.fncapp.fncapp.impl.dao.core.impl.BaseDaoBean;
import javax.ejb.Stateless;

/**
 *
 * @author Edson PAKOU
 */
@Stateless
public class CompteurDaoBean extends BaseDaoBean<Compteur, Long> implements CompteurDaoBeanLocal{

    public CompteurDaoBean() {
    }
    
    @Override
    public Class<Compteur> getType() {
        return Compteur.class;
    }
}