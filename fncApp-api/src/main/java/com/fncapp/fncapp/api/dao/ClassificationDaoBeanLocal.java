/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.api.dao;

import com.fncapp.fncapp.api.dao.core.BaseDaoBeanLocal;
import com.fncapp.fncapp.api.entities.Classification;
import javax.ejb.Local;

/**
 *
 * @author Edson PAKOU
 */
@Local
public interface ClassificationDaoBeanLocal extends BaseDaoBeanLocal<Classification, Long>{
    
}
