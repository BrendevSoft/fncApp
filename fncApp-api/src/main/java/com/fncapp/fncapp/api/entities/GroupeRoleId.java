/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.api.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Brendev
 */
@Embeddable
public class GroupeRoleId implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Column(name = "Groupe",insertable = false,updatable = false)
    private String groupe;
    
    @Column(name = "rolee",insertable = false,updatable = false)
    private Long role;

    public GroupeRoleId() {
    }

    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

   

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "GroupeRoleId{" + "groupe=" + groupe + ", role=" + role + '}';
    }

}
