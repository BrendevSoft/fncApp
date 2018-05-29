/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.api.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "logs")
@NamedQueries({
    @NamedQuery(name = "Logs.findAll", query = "SELECT l FROM Logs l")})
public class Logs extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String Id;

    @Column(name = "log_action")
    private String logAction;

    @Basic(optional = false)
    @Column(name = "log_date")
    @Temporal(TemporalType.DATE)
    private Date logDate;

    @Basic(optional = false)
    @Column(name = "log_date_heure")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logDateHeure;

    @Column(name = "log_remote_ip")
    private String logRemoteIp;

    @Column(name = "log_remote_mac")
    private String logRemoteMac;

    @JoinColumn(name = "id")
    @ManyToOne(optional = false)
    private Utilisateur utilisateur;

    public Logs() {
    }

    public Logs(String Id, String logAction, Date logDate, Date logDateHeure, String logRemoteIp, String logRemoteMac, Utilisateur utilisateur) {
        this.Id = Id;
        this.logAction = logAction;
        this.logDate = logDate;
        this.logDateHeure = logDateHeure;
        this.logRemoteIp = logRemoteIp;
        this.logRemoteMac = logRemoteMac;
        this.utilisateur = utilisateur;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getLogAction() {
        return logAction;
    }

    public void setLogAction(String logAction) {
        this.logAction = logAction;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    public Date getLogDateHeure() {
        return logDateHeure;
    }

    public void setLogDateHeure(Date logDateHeure) {
        this.logDateHeure = logDateHeure;
    }

    public String getLogRemoteIp() {
        return logRemoteIp;
    }

    public void setLogRemoteIp(String logRemoteIp) {
        this.logRemoteIp = logRemoteIp;
    }

    public String getLogRemoteMac() {
        return logRemoteMac;
    }

    public void setLogRemoteMac(String logRemoteMac) {
        this.logRemoteMac = logRemoteMac;
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
        hash = 37 * hash + Objects.hashCode(this.Id);
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
        final Logs other = (Logs) obj;
        if (!Objects.equals(this.Id, other.Id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Logs{" + "Id=" + Id + ", logAction=" + logAction + ", logDate=" + logDate + ", logDateHeure=" + logDateHeure + ", logRemoteIp=" + logRemoteIp + ", logRemoteMac=" + logRemoteMac + ", utilisateur=" + utilisateur + '}';
    }

}
