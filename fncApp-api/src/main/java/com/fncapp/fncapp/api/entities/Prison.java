/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.api.entities;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "prison")
@NamedQueries({
    @NamedQuery(name = "Prison.findAll", query = "SELECT p FROM Prison p")})
public class Prison extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "libellecourt")
    private String libellecourt;

    @Column(name = "libellelong")
    private String libellelong;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "datecreation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreation;

    @Column(name = "rowvers")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rowvers;

    @OneToMany(mappedBy = "prison")
    private Collection<Situation> situationCollection;

    public Prison() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
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

    public Collection<Situation> getSituationCollection() {
        return situationCollection;
    }

    public void setSituationCollection(Collection<Situation> situationCollection) {
        this.situationCollection = situationCollection;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
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
        final Prison other = (Prison) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Prison{" + "id=" + id + ", libellecourt=" + libellecourt + ", libellelong=" + libellelong + ", adresse=" + adresse + ", datecreation=" + datecreation + ", rowvers=" + rowvers + ", situationCollection=" + situationCollection + '}';
    }

}
