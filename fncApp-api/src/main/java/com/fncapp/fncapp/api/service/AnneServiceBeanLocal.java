/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.api.service;

import com.fncapp.fncapp.api.dao.*;
import com.fncapp.fncapp.api.dao.core.BaseDaoBeanLocal;
import com.fncapp.fncapp.api.entities.Annee;
import com.fncapp.fncapp.api.service.core.BaseServiceBeanLocal;
import javax.ejb.Remote;

/**
 *
 * @author Edson PAKOU
 */
@Remote
public interface AnneServiceBeanLocal extends BaseServiceBeanLocal<Annee, Long>{
    
}