/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.impl.dao.impl;

import com.fncapp.fncapp.api.dao.InfractionDaoBeanLocal;
import com.fncapp.fncapp.api.dao.PersonneDaoBeanLocal;
import com.fncapp.fncapp.api.entities.Infraction;
import com.fncapp.fncapp.api.entities.Personne;
import com.fncapp.fncapp.impl.dao.core.impl.BaseDaoBean;
import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author Brendev
 */
@Stateless
public class PersonneDaoBean extends BaseDaoBean<Personne, Long> implements PersonneDaoBeanLocal{

    public PersonneDaoBean() {
    }
    
    @Override
    public Class<Personne> getType() {
        return Personne.class;
    }
}
