/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.impl.service.impl;

import com.fncapp.fncapp.api.dao.ClassificationDaoBeanLocal;
import com.fncapp.fncapp.api.dao.core.BaseDaoBeanLocal;
import com.fncapp.fncapp.api.entities.Classification;
import com.fncapp.fncapp.api.service.ClassificationServiceBeanLocal;
import com.fncapp.fncapp.impl.service.core.impl.BaseServiceBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Edson PAKOU
 */
@Stateless
public class ClassificationServiceBean extends BaseServiceBean<Classification, Long> implements ClassificationServiceBeanLocal {

    @EJB
    private ClassificationDaoBeanLocal cdbl;
    
    @Override
    protected BaseDaoBeanLocal<Classification, Long> getDao(){
        return cdbl;
    }

}
