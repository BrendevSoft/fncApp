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
public class GroupeUtilisateurId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "Groupe", insertable = false, updatable = false)
    private Long groupe;

    @Column(name = "utilisateur", insertable = false, updatable = false)
    private Long utilisateur;

    public GroupeUtilisateurId() {
    }

    public Long getGroupe() {
        return groupe;
    }

    public void setGroupe(Long groupe) {
        this.groupe = groupe;
    }

    public Long getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Long utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public String toString() {
        return "GroupeUtilisateurId{" + "groupe=" + groupe + ", utilisateur=" + utilisateur + '}';
    }

}
