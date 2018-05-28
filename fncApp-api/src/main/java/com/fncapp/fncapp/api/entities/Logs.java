/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.api.entities;

import java.io.Serializable;
import java.util.Date;
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
public class Logs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "log_id")
    private Long logId;
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
    @JoinColumn(name = "utilisateur_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Utilisateur utilisateurId;

    public Logs() {
    }

    public Logs(Long logId) {
        this.logId = logId;
    }

    public Logs(Long logId, Date logDate, Date logDateHeure) {
        this.logId = logId;
        this.logDate = logDate;
        this.logDateHeure = logDateHeure;
    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
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

    public Utilisateur getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(Utilisateur utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (logId != null ? logId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Logs)) {
            return false;
        }
        Logs other = (Logs) object;
        if ((this.logId == null && other.logId != null) || (this.logId != null && !this.logId.equals(other.logId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fncapp.fncapp.api.entities.Logs[ logId=" + logId + " ]";
    }
    
}
