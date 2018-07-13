/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.impl.dao.impl;

import com.fncapp.fncapp.api.dao.CorpsDaoBeanLocal;
import com.fncapp.fncapp.api.entities.Corps;
import com.fncapp.fncapp.impl.dao.core.impl.BaseDaoBean;
import javax.ejb.Stateless;

/**
 *
 * @author Edson PAKOU
 */
@Stateless
public class CorpsDaoBean extends BaseDaoBean<Corps, Long> implements CorpsDaoBeanLocal{

    public CorpsDaoBean() {
    }
    
    @Override
    public Class<Corps> getType() {
        return Corps.class;
    }
}

