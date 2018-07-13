/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.impl.dao.impl;

import com.fncapp.fncapp.api.dao.InfractionDaoBeanLocal;
import com.fncapp.fncapp.api.dao.JuridictionDaoBeanLocal;
import com.fncapp.fncapp.api.entities.Infraction;
import com.fncapp.fncapp.api.entities.Juridiction;
import com.fncapp.fncapp.impl.dao.core.impl.BaseDaoBean;
import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author Edson PAKOU
 */
@Stateless
public class JuridictionDaoBean extends BaseDaoBean<Juridiction, Long> implements JuridictionDaoBeanLocal{

    public JuridictionDaoBean() {
    }
    
    @Override
    public Class<Juridiction> getType() {
        return Juridiction.class;
    }
}
