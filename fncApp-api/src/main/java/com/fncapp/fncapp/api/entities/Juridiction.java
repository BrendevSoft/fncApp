/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.api.entities;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;
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
@Table(name = "juridiction")
@NamedQueries({
    @NamedQuery(name = "Juridiction.findAll", query = "SELECT j FROM Juridiction j")})
public class Juridiction extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

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

    @Column(name = "ville")
    private String ville;

    @Column(name = "rowvers")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rowvers;

    @OneToMany(mappedBy = "juridiction")
    private Collection<Juridiction> juridictionCollection;

    @JoinColumn(name = "juridiction")
    @ManyToOne
    private Juridiction juridiction;

    @JoinColumn(name = "courtAppel")
    @ManyToOne
    private CourtAppel courtAppel;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "juridiction")
    private Collection<Condamnation> condamnationCollection;

    @OneToMany(mappedBy = "juridiction")
    private Collection<Utilisateur> utilisateurCollection;

    @OneToMany(mappedBy = "juridiction")
    private Collection<Service> serviceCollection;

    @OneToMany(mappedBy = "juridiction")
    private Collection<Statistique> statistiqueCollection;

    public Juridiction() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Juridiction getJuridiction() {
        return juridiction;
    }

    public void setJuridiction(Juridiction juridiction) {
        this.juridiction = juridiction;
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

    public CourtAppel getCourtAppel() {
        return courtAppel;
    }

    public void setCourtAppel(CourtAppel courtAppel) {
        this.courtAppel = courtAppel;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
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
        hash = 71 * hash + Objects.hashCode(this.id);
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
        final Juridiction other = (Juridiction) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Juridiction{" + "id=" + id + ", code=" + code + ", adresse=" + adresse + ", coderg=" + coderg + ", datecreation=" + datecreation + ", libellecourt=" + libellecourt + ", libellelong=" + libellelong + ", precleprimaire=" + precleprimaire + ", type=" + type + ", ville=" + ville + ", rowvers=" + rowvers + ", juridiction=" + juridiction + ", courtAppel=" + courtAppel + '}';
    }

}
