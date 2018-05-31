/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.api.entities;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Brendev
 */
@Entity
@Table(name = "grouperole")
@XmlRootElement
public class GroupeRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    @Column(name = "id", nullable = false)
    GroupeRoleId id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "groupe", insertable = true, updatable = true)
    private Groupe groupe;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rolee", insertable = true, updatable = true)
    private Rolee role;

    public GroupeRole() {
    }

    public void detruire() {

    }

    public GroupeRole(Groupe groupe, Rolee role) {
        this.groupe = groupe;
        this.role = role;
    }

    public GroupeRoleId getId() {
        return id;
    }

    public void setId(GroupeRoleId id) {
        this.id = id;
    }

    public Rolee getRole() {
        return role;
    }

    public void setRole(Rolee role) {
        this.role = role;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.id);
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
        final GroupeRole other = (GroupeRole) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GroupelRole{" + "id=" + id + ", groupe=" + groupe + ", role=" + role + '}';
    }

}
