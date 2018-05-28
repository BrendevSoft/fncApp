/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.api.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Brendev
 */
@Entity
@Table(name = "utilisateur")
@NamedQueries({
    @NamedQuery(name = "Utilisateur.findAll", query = "SELECT u FROM Utilisateur u")})
public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Column(name = "login")
    private String login;
    @Column(name = "civilite")
    private String civilite;
    @Column(name = "mail")
    private String mail;
    @Column(name = "telephone")
    private String telephone;
    @Column(name = "passwd")
    private String passwd;
    @Column(name = "nom")
    private String nom;
    @Column(name = "nomprenoms")
    private String nomprenoms;
    @Column(name = "prenom")
    private String prenom;
    @Column(name = "datecreation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreation;
    @Column(name = "rowvers")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rowvers;
    @Column(name = "profilactif")
    private Boolean profilactif;
    @OneToMany(mappedBy = "utilisateurId")
    private Collection<Groupeutilisateur> groupeutilisateurCollection;
    @JoinColumn(name = "corps_code", referencedColumnName = "code")
    @ManyToOne
    private Corps corpsCode;
    @JoinColumn(name = "juridiction_code", referencedColumnName = "code")
    @ManyToOne
    private Juridiction juridictionCode;
    @JoinColumn(name = "service_code", referencedColumnName = "code")
    @ManyToOne
    private Service serviceCode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "utilisateurId")
    private Collection<Logs> logsCollection;

    public Utilisateur() {
    }

    public Utilisateur(String id) {
        this.id = id;
    }

    public Utilisateur(String id, String login) {
        this.id = id;
        this.login = login;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNomprenoms() {
        return nomprenoms;
    }

    public void setNomprenoms(String nomprenoms) {
        this.nomprenoms = nomprenoms;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
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

    public Boolean getProfilactif() {
        return profilactif;
    }

    public void setProfilactif(Boolean profilactif) {
        this.profilactif = profilactif;
    }

    public Collection<Groupeutilisateur> getGroupeutilisateurCollection() {
        return groupeutilisateurCollection;
    }

    public void setGroupeutilisateurCollection(Collection<Groupeutilisateur> groupeutilisateurCollection) {
        this.groupeutilisateurCollection = groupeutilisateurCollection;
    }

    public Corps getCorpsCode() {
        return corpsCode;
    }

    public void setCorpsCode(Corps corpsCode) {
        this.corpsCode = corpsCode;
    }

    public Juridiction getJuridictionCode() {
        return juridictionCode;
    }

    public void setJuridictionCode(Juridiction juridictionCode) {
        this.juridictionCode = juridictionCode;
    }

    public Service getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(Service serviceCode) {
        this.serviceCode = serviceCode;
    }

    public Collection<Logs> getLogsCollection() {
        return logsCollection;
    }

    public void setLogsCollection(Collection<Logs> logsCollection) {
        this.logsCollection = logsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utilisateur)) {
            return false;
        }
        Utilisateur other = (Utilisateur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fncapp.fncapp.api.entities.Utilisateur[ id=" + id + " ]";
    }
    
}
