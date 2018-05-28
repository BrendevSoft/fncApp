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
@Table(name = "juridiction")
@NamedQueries({
    @NamedQuery(name = "Juridiction.findAll", query = "SELECT j FROM Juridiction j")})
public class Juridiction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "code")
    private String code;
    @Column(name = "adresse")
    private String adresse;
    @Column(name = "coderg")
    private String coderg;
    @Column(name = "datecreation")
    @Temporal(TemporalType.DATE)
    private Date datecreation;
    @Column(name = "libellecourt")
    private String libellecourt;
    @Column(name = "libellelong")
    private String libellelong;
    @Column(name = "precleprimaire")
    private String precleprimaire;
    @Column(name = "type")
    private String type;
    @Column(name = "rowvers")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rowvers;
    @OneToMany(mappedBy = "juridictionCode")
    private Collection<Juridiction> juridictionCollection;
    @JoinColumn(name = "juridiction_code", referencedColumnName = "code")
    @ManyToOne
    private Juridiction juridictionCode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "juridictionCode")
    private Collection<Condamnation> condamnationCollection;
    @OneToMany(mappedBy = "juridictionCode")
    private Collection<Utilisateur> utilisateurCollection;
    @OneToMany(mappedBy = "juridictionCode")
    private Collection<Service> serviceCollection;
    @OneToMany(mappedBy = "juridictionCode")
    private Collection<Statistique> statistiqueCollection;

    public Juridiction() {
    }

    public Juridiction(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCoderg() {
        return coderg;
    }

    public void setCoderg(String coderg) {
        this.coderg = coderg;
    }

    public Date getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }

    public String getLibellecourt() {
        return libellecourt;
    }

    public void setLibellecourt(String libellecourt) {
        this.libellecourt = libellecourt;
    }

    public String getLibellelong() {
        return libellelong;
    }

    public void setLibellelong(String libellelong) {
        this.libellelong = libellelong;
    }

    public String getPrecleprimaire() {
        return precleprimaire;
    }

    public void setPrecleprimaire(String precleprimaire) {
        this.precleprimaire = precleprimaire;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getRowvers() {
        return rowvers;
    }

    public void setRowvers(Date rowvers) {
        this.rowvers = rowvers;
    }

    public Collection<Juridiction> getJuridictionCollection() {
        return juridictionCollection;
    }

    public void setJuridictionCollection(Collection<Juridiction> juridictionCollection) {
        this.juridictionCollection = juridictionCollection;
    }

    public Juridiction getJuridictionCode() {
        return juridictionCode;
    }

    public void setJuridictionCode(Juridiction juridictionCode) {
        this.juridictionCode = juridictionCode;
    }

    public Collection<Condamnation> getCondamnationCollection() {
        return condamnationCollection;
    }

    public void setCondamnationCollection(Collection<Condamnation> condamnationCollection) {
        this.condamnationCollection = condamnationCollection;
    }

    public Collection<Utilisateur> getUtilisateurCollection() {
        return utilisateurCollection;
    }

    public void setUtilisateurCollection(Collection<Utilisateur> utilisateurCollection) {
        this.utilisateurCollection = utilisateurCollection;
    }

    public Collection<Service> getServiceCollection() {
        return serviceCollection;
    }

    public void setServiceCollection(Collection<Service> serviceCollection) {
        this.serviceCollection = serviceCollection;
    }

    public Collection<Statistique> getStatistiqueCollection() {
        return statistiqueCollection;
    }

    public void setStatistiqueCollection(Collection<Statistique> statistiqueCollection) {
        this.statistiqueCollection = statistiqueCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Juridiction)) {
            return false;
        }
        Juridiction other = (Juridiction) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fncapp.fncapp.api.entities.Juridiction[ code=" + code + " ]";
    }
    
}
