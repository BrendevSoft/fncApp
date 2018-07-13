/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.impl.dao.impl;

import com.fncapp.fncapp.api.dao.CompteurDaoBeanLocal;
import com.fncapp.fncapp.api.dao.CondamnationDaoBeanLocal;
import com.fncapp.fncapp.api.entities.Compteur;
import com.fncapp.fncapp.api.entities.Condamnation;
import com.fncapp.fncapp.impl.dao.core.impl.BaseDaoBean;
import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author Edson PAKOU
 */
@Stateless
public class CondamnationDaoBean extends BaseDaoBean<Condamnation, Long> implements CondamnationDaoBeanLocal{

    public CondamnationDaoBean() {
    }
    
    @Override
    public Class<Condamnation> getType() {
        return Condamnation.class;
    }
}
