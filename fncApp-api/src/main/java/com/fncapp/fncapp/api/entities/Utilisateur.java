/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.api.entities;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
public class Utilisateur extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

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

    @Column(name = "question")
    private String question;

    @Column(name = "reponse")
    private String reponse;

    @OneToMany(mappedBy = "utilisateur")
    private Collection<Groupeutilisateur> groupeutilisateurCollection;

    @JoinColumn(name = "corps")
    @ManyToOne
    private Corps corps;

    @JoinColumn(name = "juridiction")
    @ManyToOne
    private Juridiction juridiction;

    @JoinColumn(name = "service")
    @ManyToOne
    private Service service;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "utilisateur")
    private Collection<Logs> logsCollection;

    public Utilisateur() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Corps getCorps() {
        return corps;
    }

    public void setCorps(Corps corps) {
        this.corps = corps;
    }

    public Juridiction getJuridiction() {
        return juridiction;
    }

    public void setJuridiction(Juridiction juridiction) {
        this.juridiction = juridiction;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Collection<Logs> getLogsCollection() {
        return logsCollection;
    }

    public void setLogsCollection(Collection<Logs> logsCollection) {
        this.logsCollection = logsCollection;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final Utilisateur other = (Utilisateur) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", login=" + login + ", civilite=" + civilite + ", mail=" + mail + ", telephone=" + telephone + ", passwd=" + passwd + ", nom=" + nom + ", nomprenoms=" + nomprenoms + ", prenom=" + prenom + ", datecreation=" + datecreation + ", rowvers=" + rowvers + ", profilactif=" + profilactif + ", question=" + question + ", reponse=" + reponse + ", groupeutilisateurCollection=" + groupeutilisateurCollection + ", corps=" + corps + ", juridiction=" + juridiction + ", service=" + service + ", logsCollection=" + logsCollection + '}';
    }

}
