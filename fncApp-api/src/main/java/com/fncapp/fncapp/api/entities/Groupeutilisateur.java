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
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Brendev
 */
@Entity
@Table(name = "groupeutilisateur")
@NamedQueries({
    @NamedQuery(name = "Groupeutilisateur.findAll", query = "SELECT g FROM Groupeutilisateur g")})
public class Groupeutilisateur extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    GroupeUtilisateurId id;

    @Column(name = "dateRevocation")
    @Temporal(TemporalType.DATE)
    private Date dateRevocation;

    @Column(name = "dateAffectation")
    @Temporal(TemporalType.DATE)
    private Date dateAffectation;

    @Column(name = "datecreation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreation;

    @Column(name = "rowvers")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rowvers;

    @Size(max = 255)
    @Column(name = "utilisateur_login")
    private String utilisateurLogin;

    @JoinColumn(name = "groupe", insertable = true, updatable = true)
    @ManyToOne(fetch = FetchType.EAGER)
    private Groupe groupe;

    @JoinColumn(name = "utilisateur", insertable = true, updatable = true)
    @ManyToOne(fetch = FetchType.EAGER)
    private Utilisateur utilisateur;

    public Groupeutilisateur() {
    }

    public GroupeUtilisateurId getId() {
        return id;
    }

    public void setId(GroupeUtilisateurId id) {
        this.id = id;
    }

    public Date getDateRevocation() {
        return dateRevocation;
    }

    public void setDateRevocation(Date dateRevocation) {
        this.dateRevocation = dateRevocation;
    }

    public Date getDateAffectation() {
        return dateAffectation;
    }

    public void setDateAffectation(Date dateAffectation) {
        this.dateAffectation = dateAffectation;
    }

    public Date getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final Groupeutilisateur other = (Groupeutilisateur) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Groupeutilisateur{" + "id=" + id + ", dateRevocation=" + dateRevocation + ", dateAffectation=" + dateAffectation + ", datecreation=" + datecreation + ", rowvers=" + rowvers + ", utilisateurLogin=" + utilisateurLogin + '}';
    }

}
