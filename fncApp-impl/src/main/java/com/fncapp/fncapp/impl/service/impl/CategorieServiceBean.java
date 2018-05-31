/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.impl.service.impl;

import com.fncapp.fncapp.api.dao.CategorieDaoBeanLocal;
import com.fncapp.fncapp.api.dao.core.BaseDaoBeanLocal;
import com.fncapp.fncapp.api.entities.Categorie;
import com.fncapp.fncapp.api.service.CategorieServiceBeanLocal;
import com.fncapp.fncapp.impl.service.core.impl.BaseServiceBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Edson PAKOU
 */
@Stateless
public class CategorieServiceBean extends BaseServiceBean<Categorie, Long> implements CategorieServiceBeanLocal {

    @EJB
    private CategorieDaoBeanLocal cdbl;
    
    @Override
    protected BaseDaoBeanLocal<Categorie, Long> getDao(){
        return cdbl;
    }


}
