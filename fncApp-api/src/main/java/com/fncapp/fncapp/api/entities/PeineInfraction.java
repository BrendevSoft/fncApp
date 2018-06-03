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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Brendev
 */
@Entity
public class PeineInfraction extends BaseEntity {

    @EmbeddedId
    PeineInfractionId id;

    @Column(name = "datecreation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreation;

    @Column(name = "rowvers")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rowvers;

    @JoinColumn(name = "peine", insertable = true, updatable = true)
    @ManyToOne(fetch = FetchType.EAGER)
    private Peine peine;

    @JoinColumn(name = "infraction", insertable = true, updatable = true)
    @ManyToOne(fetch = FetchType.EAGER)
    private Infraction infraction;

    public PeineInfraction() {
    }

    public PeineInfractionId getId() {
        return id;
    }

    public void setId(PeineInfractionId id) {
        this.id = id;
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

    public Peine getPeine() {
        return peine;
    }

    public void setPeine(Peine peine) {
        this.peine = peine;
    }

    public Infraction getInfraction() {
        return infraction;
    }

    public void setInfraction(Infraction infraction) {
        this.infraction = infraction;
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
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final PeineInfraction other = (PeineInfraction) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PeineInfraction{" + "id=" + id + ", datecreation=" + datecreation + ", rowvers=" + rowvers + ", peine=" + peine + ", infraction=" + infraction + '}';
    }

}
