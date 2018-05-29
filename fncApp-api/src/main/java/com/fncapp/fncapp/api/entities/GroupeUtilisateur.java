/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.api.entities;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Brendev
 */
@Entity
@Table(name = "groupeutilisateur")
@NamedQueries({
    @NamedQuery(name = "GroupeUtilisateur.findAll", query = "SELECT g FROM GroupeUtilisateur g")})
public class GroupeUtilisateur extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private GroupeUtilisateurId id;

    @Column(name = "datecreation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreation;

    @Temporal(TemporalType.DATE)
    @Column(name = "dateRevocation")
    private Date dateRevocation;

    @Column(name = "rowvers")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rowvers;

    @Column(name = "utilisateur_login")
    private String utilisateurLogin;

    @JoinColumn(name = "groupe", insertable = true, updatable = true)
    @ManyToOne
    private Groupe groupe;

    @JoinColumn(name = "utilisateur", insertable = true, updatable = true)
    @ManyToOne
    private Utilisateur utilisateur;

    public GroupeUtilisateur() {
    }

    public GroupeUtilisateurId getId() {
        return id;
    }

    public void setId(GroupeUtilisateurId id) {
        this.id = id;
    }

    public Date getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }

    public Date getDateRevocation() {
        return dateRevocation;
    }

    public void setDateRevocation(Date dateRevocation) {
        this.dateRevocation = dateRevocation;
    }

    public Date getRowvers() {
        return rowvers;
    }

    public void setRowvers(Date rowvers) {
        this.rowvers = rowvers;
    }

    public String getUtilisateurLogin() {
        return utilisateurLogin;
    }

    public void setUtilisateurLogin(String utilisateurLogin) {
        this.utilisateurLogin = utilisateurLogin;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GroupeUtilisateur other = (GroupeUtilisateur) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Groupeutilisateur{" + "id=" + id + ", datecreation=" + datecreation + ", dateRevocation=" + dateRevocation + ", rowvers=" + rowvers + ", utilisateurLogin=" + utilisateurLogin + ", groupe=" + groupe + ", utilisateur=" + utilisateur + '}';
    }

}
