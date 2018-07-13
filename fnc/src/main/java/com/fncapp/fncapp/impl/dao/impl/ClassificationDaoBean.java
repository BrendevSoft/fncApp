/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.impl.dao.impl;

import com.fncapp.fncapp.api.dao.CategorieDaoBeanLocal;
import com.fncapp.fncapp.api.dao.ClassificationDaoBeanLocal;
import com.fncapp.fncapp.api.entities.Classification;
import com.fncapp.fncapp.impl.dao.core.impl.BaseDaoBean;
import javax.ejb.Stateless;

/**
 *
 * @author Edson PAKOU
 */
@Stateless
public class ClassificationDaoBean extends BaseDaoBean<Classification, Long> implements ClassificationDaoBeanLocal {

    public ClassificationDaoBean() {
    }

    @Override
    public Class<Classification> getType() {
        return Classification.class;
    }
}
