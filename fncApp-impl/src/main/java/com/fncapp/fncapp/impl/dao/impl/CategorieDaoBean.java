/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.impl.dao.impl;

import com.fncapp.fncapp.api.dao.AnneDaoBeanLocal;
import com.fncapp.fncapp.api.dao.CategorieDaoBeanLocal;
import com.fncapp.fncapp.api.entities.Annee;
import com.fncapp.fncapp.api.entities.Categorie;
import com.fncapp.fncapp.impl.dao.core.impl.BaseDaoBean;
import javax.ejb.Stateless;

/**
 *
 * @author Edson PAKOU
 */
@Stateless
public class CategorieDaoBean extends BaseDaoBean<Categorie, Long> implements CategorieDaoBeanLocal{

    public CategorieDaoBean() {
    }

    @Override
    public Class<Categorie> getType() {
        return Categorie.class;
    }

}
