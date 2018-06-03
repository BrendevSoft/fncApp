/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.web.web;

import com.fncapp.fncapp.api.api.utils.MethodeJournalisation;
import com.fncapp.fncapp.api.entities.Groupe;
import com.fncapp.fncapp.api.entities.GroupeRole;
import com.fncapp.fncapp.api.entities.Groupeutilisateur;
import com.fncapp.fncapp.api.entities.Rolee;
import com.fncapp.fncapp.api.entities.Utilisateur;
import com.fncapp.fncapp.api.service.GroupeRoleServiceBeanLocal;
import com.fncapp.fncapp.api.service.GroupeServiceBeanLocal;
import com.fncapp.fncapp.api.service.GroupeUtilisateurServiceBeanLocal;
import com.fncapp.fncapp.api.service.RoleServiceBeanLocal;
import com.fncapp.fncapp.api.service.UtilisateurServiceBeanLocal;
import com.fncapp.fncapp.impl.shiro.EntityRealm;
import com.fncapp.fncapp.impl.transaction.TransactionManager;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.omnifaces.util.Faces;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Brendev
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private MethodeJournalisation journalisation;

    @EJB
    private RoleServiceBeanLocal rsl;

    @EJB
    private GroupeServiceBeanLocal psbl;

    @EJB
    private UtilisateurServiceBeanLocal usbl;

    @EJB
    private GroupeUtilisateurServiceBeanLocal pusbl;
    @EJB//HUM Tu m'as vraiment dérangé.plus de 24h avant de me rappeller de te mettre.hum
    private GroupeRoleServiceBeanLocal prsbl;

    private Date date = new Date();
    private String username;
    private String password;
    private String newPass;
    private String retapPass;
    private String lastPass;
    private String per = "";
    private String question;
    private String reponse;
    private String poste, profil,
            creerPoste, modifierPoste, creerProfil, modifierProfil, associerPoste, associerProfil, associerRole,
            activerCompte, desactiverCompte, administration, mission, rapport, etat, securite, personnel;
    private Utilisateur pers;
    private Utilisateur perse;
    private boolean remember = false;
    private boolean admin;
    

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
        pers = new Utilisateur();
        perse = new Utilisateur();
        journalisation = new MethodeJournalisation();
    }

    @PostConstruct
    public void init() {
        List<Rolee> all = rsl.getAll();
        if (all.isEmpty()) {
            this.rsl.saveOne(new Rolee("Créer poste"));
            this.rsl.saveOne(new Rolee("Modifier poste"));
            this.rsl.saveOne(new Rolee("Créer profil"));
            this.rsl.saveOne(new Rolee("Modifier profil"));
            this.rsl.saveOne(new Rolee("Associer poste"));
            this.rsl.saveOne(new Rolee("Associer profil"));
            this.rsl.saveOne(new Rolee("Associer role"));
            this.rsl.saveOne(new Rolee("Activer compte"));
            this.rsl.saveOne(new Rolee("Désactiver compte"));
        }

        List<Groupe> profils = psbl.getBy("nom", "Admin");

        UserTransaction tx = TransactionManager.getUserTransaction();
        try {
            tx.begin();
            if (profils.isEmpty()) {
                this.psbl.saveOne(new Groupe("Admin", "Administrateur du système"));
                GroupeRole pr;
                List<Rolee> roles = this.rsl.getAll();
                for (Rolee rolee : roles) {
                    pr = new GroupeRole();
                    pr.setRole(rolee);
                    pr.setGroupe(psbl.getBy("nom", "Admin").get(0));
                    prsbl.saveOne(pr);
                }

                Utilisateur u = new Utilisateur();
                u.setLogin("AdminFNC");
                u.setQuestion("AdminFNC");
                u.setReponse("AdminFNC");
                u.setNom("AdminFNC");
                u.setPrenom("AdminFNC");
                u.setMail("fnc@fnc.com");
                u.setPasswd(new Sha256Hash("@fnc2018").toHex());
                u.setProfilactif(true);
                usbl.saveOne(u);

                Groupeutilisateur pu = new Groupeutilisateur();
                pu.setUtilisateur(usbl.getBy("login", "AdminFNC").get(0));
                pu.setDateAffectation(date);
                pu.setGroupe(psbl.getBy("nom", "Admin").get(0));
                pusbl.saveOne(pu);
                tx.commit();
            }
        } catch (Exception e) {
            try {
                tx.rollback();
            } catch (IllegalStateException ex) {
                Logger.getLogger(LoginBean.class.getName()).log(Level.FATAL, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(LoginBean.class.getName()).log(Level.FATAL, null, ex);
            } catch (SystemException ex) {
                Logger.getLogger(LoginBean.class.getName()).log(Level.FATAL, null, ex);
            }
        }

    }

    @SuppressWarnings("deprecation")
    public void login() throws IOException {
        try {
            System.out.println("user=" + username);
            System.out.println("ps=" + password);
            pers = usbl.getOneBy("login", username);
            if (pers != null) {
                if (pers.getProfilactif() == false) {
                    RequestContext context = RequestContext.getCurrentInstance();
                    context.execute("PF('error').show();");
                    username = "";
                    return;
                }
            }

            if (pers != null) {
                boolean test = new Sha256Hash("admin").toHex().equals(pers.getPasswd());
                if (test && pers.getProfilactif() == true) {

                    RequestContext context = RequestContext.getCurrentInstance();
                    context.execute("PF('dialogpasse').show();");
                    context.execute("PF('dialogRecup').hide();");
                    return;
                }

            }

            UsernamePasswordToken token = new UsernamePasswordToken(username.trim(), password.trim());
            //  journalisation.saveLog4j(LoginBean.class.getName(), Level.INFO, "Journaliser");
            //”Remember Me” built-in, just do this:
            token.setRememberMe(true);
            //With most of Shiro, you'll always want to make sure you're working with 
            //the currently executing user, referred to as the subject
            SecurityUtils.getSubject().login(token);
            //Authenticate the subject by passing
            //the user name and password token
            //into the login method
            // currentUser.login(token);
//            Long total = this.psl.count();
//            List<Role> roleProf = new ArrayList<>();
//            if (total > 0) {
//                if (!username.equalsIgnoreCase("admin")) {
//                    pers = EntityRealm.getUser();
//                    if (pers != null) {
//                        roleProf = prsbl.getProfilRoles(pers.getProfil());
//                        System.out.println(roleProf);
//                    }
//                }
//            }

            List<Rolee> roles = this.rsl.getAll();
            Boolean avoir = false;
            Subject subject = EntityRealm.getSubject();
            for (Rolee role : roles) {
                if (subject.hasRole(role.getNom())) {
                    avoir = true;
                }
            }

            if (!username.equalsIgnoreCase("admin")) {

                if (subject.hasRole("Créer poste") || subject.hasRole("Modifier poste")
                        || subject.hasRole("Créer profil") || subject.hasRole("Modifier profil")
                        || subject.hasRole("Associer poste") || subject.hasRole("Associer profil")
                        || subject.hasRole("Associer role") || subject.hasRole("Activer compte")
                        || subject.hasRole("Désactiver compte")) {
                    this.securite = "true";
                } else {
                    this.securite = "false";
                }

                if (subject.hasRole("Créer poste") || subject.hasRole("Modifier poste")) {
                    this.poste = "true";
                } else {
                    this.poste = "false";
                }

                if (subject.hasRole("Créer poste")) {
                    this.creerPoste = "true";
                } else {
                    this.creerPoste = "false";
                }

                if (subject.hasRole("Modifier poste")) {
                    this.modifierPoste = "true";
                } else {
                    this.modifierPoste = "false";
                }

                if (subject.hasRole("Créer profil") || subject.hasRole("Modifier profil")) {
                    this.profil = "true";
                } else {
                    this.profil = "false";
                }

                if (subject.hasRole("Créer profil")) {
                    this.creerProfil = "true";
                } else {
                    this.creerProfil = "false";
                }

                if (subject.hasRole("Modifier profil")) {
                    this.modifierProfil = "true";
                } else {
                    this.modifierProfil = "false";
                }

                if (subject.hasRole("Associer poste")) {
                    this.associerPoste = "true";
                } else {
                    this.associerPoste = "false";
                }

                if (subject.hasRole("Associer profil")) {
                    this.associerProfil = "true";
                } else {
                    this.associerProfil = "false";
                }

                if (subject.hasRole("Associer role")) {
                    this.associerRole = "true";
                } else {
                    this.associerRole = "false";
                }

                if (subject.hasRole("Activer compte")) {
                    this.activerCompte = "true";
                } else {
                    this.activerCompte = "false";
                }

                if (subject.hasRole("Désactiver compte")) {
                    this.desactiverCompte = "true";
                } else {
                    this.desactiverCompte = "false";
                }

                if (!avoir) {
                    RequestContext context = RequestContext.getCurrentInstance();
                    context.execute("PF('error').show();");
                    username = "";
                    return;
                }

            }
            journalisation.saveLog4j(LoginBean.class.getSimpleName(), Level.INFO, "Connexion");

            SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(Faces.getRequest());
            Faces.redirect(savedRequest != null ? savedRequest.getRequestUrl() : "index.xhtml");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            FacesMessage mf = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Nom d'utlisateur ou mot de passe incorrect", "");
            FacesContext.getCurrentInstance().addMessage("", mf);
        }
        //return "index";
    }

    public String currentUser() {
        Utilisateur user = EntityRealm.getUser();
        if (user == null) {
            return "Admin";
        }
        return EntityRealm.getUser().getNom();
    }

    public Date sessionTime() {
        return EntityRealm.getSubject().getSession().getStartTimestamp();
    }

    public void logout() {
        try {
            //LogUtils.write("INFO", "DECONNEXION", EntityRealm.getUser().getNom() + ' ' + EntityRealm.getUser().getPrenom());

            journalisation.saveLog4j(LoginBean.class.getSimpleName(), Level.INFO, "Déconnexion");
            EntityRealm.getSubject().logout();
            Faces.redirect("login.xhtml");
            username = "";
        } catch (IOException ex) {
        }

    }

    public void modifierPasse() {
        if (newPass.trim().equals(retapPass.trim())) {
            pers.setPasswd(new Sha256Hash(newPass.trim()).toHex());
            pers.setQuestion(question);
            pers.setReponse(reponse);
            usbl.updateOne(pers);
            question = "";
            reponse = "";
            RequestContext.getCurrentInstance().execute("PF('dialogpasse').hide();");
            RequestContext.getCurrentInstance().execute("PF('dialogRecup').hide();");
            FacesMessage mf = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Mot de passe corriger", "");
            FacesContext.getCurrentInstance().addMessage("erreur_login", mf);
        } else {
            FacesMessage mf = new FacesMessage(FacesMessage.SEVERITY_FATAL,
                    "Les mots de passe ne concorde pas", "");
            FacesContext.getCurrentInstance().addMessage("erreur_login", mf);
        }
    }

    public void modifierPasse2() {
        if (new Sha256Hash(lastPass).toHex().equals(EntityRealm.getUser().getPasswd())) {
            if (newPass.trim().equals(retapPass.trim())) {
                if (new Sha256Hash(newPass).toHex().equals(EntityRealm.getUser().getPasswd())) {
                    FacesMessage mf = new FacesMessage(FacesMessage.SEVERITY_FATAL,
                            "Tapez un mot de passe différent de l'ancien", "");
                    FacesContext.getCurrentInstance().addMessage("erreur_login", mf);
                    newPass = "";
                    lastPass = "";
                    retapPass = "";
                } else {
                    EntityRealm.getUser().setPasswd(new Sha256Hash(newPass.trim()).toHex());
                    usbl.updateOne(EntityRealm.getUser());
                    FacesMessage mf = new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Mot de passe corrigé", "");
                    FacesContext.getCurrentInstance().addMessage("erreur_login", mf);
                    RequestContext context = RequestContext.getCurrentInstance();
                    context.execute("PF('dialogpasse').hide()");
                }
            } else {
                FacesMessage mf = new FacesMessage(FacesMessage.SEVERITY_FATAL,
                        "Les mots de passe ne concorde pas", "");
                FacesContext.getCurrentInstance().addMessage("erreur_login", mf);
                newPass = "";
                lastPass = "";
                retapPass = "";
            }

        } else {
            FacesMessage mf = new FacesMessage(FacesMessage.SEVERITY_FATAL,
                    "mot de passe incorrect!!!", "");
            FacesContext.getCurrentInstance().addMessage("erreur_login", mf);
            newPass = "";
            lastPass = "";
            retapPass = "";
        }
    }

    public void reinitialiserPasse() {
        Utilisateur pe = this.usbl.getOneBy("login", per);
        if (pe.getProfilactif() == true) {
            if (reponse.equals(pe.getReponse())) {
                pe.setPasswd(new Sha256Hash("admin").toHex());
                pe.setQuestion(null);
                pe.setReponse(null);
                usbl.updateOne(pe);
                question = "";
                reponse = "";
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('dialogRecup').hide();");
                FacesMessage mf = new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Mot de passe réinitialisé", "");
                FacesContext.getCurrentInstance().addMessage("erreur_login", mf);
            } else {
                FacesMessage mf = new FacesMessage(FacesMessage.SEVERITY_FATAL,
                        "La reponse est fausse", "");
                FacesContext.getCurrentInstance().addMessage("erreur_login", mf);
            }
        }

    }

    public String recupererQuestion() {
        if (!per.equals("")) {
            Utilisateur pe = this.usbl.getOneBy("login", per);
            String quest = "";
            if (pe != null) {
                if (!pe.getPasswd().equals(new Sha256Hash("admin").toHex())) {
                    if (pe.getProfilactif() == true) {
                        quest = pe.getQuestion();
                        RequestContext context = RequestContext.getCurrentInstance();
                        context.execute("PF('dialogRecup').show();");
                        context.execute("PF('dialogOublie').hide();");
                        return quest;
                    } else {
                        per = "";
                        RequestContext context = RequestContext.getCurrentInstance();
                        context.execute("PF('dialogOublie').hide();");
                        FacesMessage mf = new FacesMessage(FacesMessage.SEVERITY_FATAL,
                                "Votre compte est inactif,contactez l'administrateur", "");
                        FacesContext.getCurrentInstance().addMessage("erreur_login", mf);
                    }
                } else {
                    RequestContext context = RequestContext.getCurrentInstance();
                    context.execute("PF('dialogOublie').hide();");
                    FacesMessage mf = new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Connectez vous à votre compte pour changer votre mot de passe", "");
                    FacesContext.getCurrentInstance().addMessage("erreur_login", mf);
                }
            } else {
                per = "";
                FacesMessage mf = new FacesMessage(FacesMessage.SEVERITY_FATAL,
                        "le login saisi est inconnu", "");
                FacesContext.getCurrentInstance().addMessage("erreur_login", mf);
            }
        }
        return "";
    }

    public RoleServiceBeanLocal getRsl() {
        return rsl;
    }

    public void setRsl(RoleServiceBeanLocal rsl) {
        this.rsl = rsl;
    }

    public UtilisateurServiceBeanLocal getUsbl() {
        return usbl;
    }

    public void setUsbl(UtilisateurServiceBeanLocal usbl) {
        this.usbl = usbl;
    }

    public MethodeJournalisation getJournalisation() {
        return journalisation;
    }

    public void setJournalisation(MethodeJournalisation journalisation) {
        this.journalisation = journalisation;
    }

    public GroupeServiceBeanLocal getPsbl() {
        return psbl;
    }

    public void setPsbl(GroupeServiceBeanLocal psbl) {
        this.psbl = psbl;
    }

    public GroupeUtilisateurServiceBeanLocal getPusbl() {
        return pusbl;
    }

    public void setPusbl(GroupeUtilisateurServiceBeanLocal pusbl) {
        this.pusbl = pusbl;
    }

    public GroupeRoleServiceBeanLocal getPrsbl() {
        return prsbl;
    }

    public void setPrsbl(GroupeRoleServiceBeanLocal prsbl) {
        this.prsbl = prsbl;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getRetapPass() {
        return retapPass;
    }

    public void setRetapPass(String retapPass) {
        this.retapPass = retapPass;
    }

    public String getLastPass() {
        return lastPass;
    }

    public void setLastPass(String lastPass) {
        this.lastPass = lastPass;
    }

    public String getPer() {
        return per;
    }

    public void setPer(String per) {
        this.per = per;
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

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }

    public String getCreerPoste() {
        return creerPoste;
    }

    public void setCreerPoste(String creerPoste) {
        this.creerPoste = creerPoste;
    }

    public String getModifierPoste() {
        return modifierPoste;
    }

    public void setModifierPoste(String modifierPoste) {
        this.modifierPoste = modifierPoste;
    }

    public String getCreerProfil() {
        return creerProfil;
    }

    public void setCreerProfil(String creerProfil) {
        this.creerProfil = creerProfil;
    }

    public String getModifierProfil() {
        return modifierProfil;
    }

    public void setModifierProfil(String modifierProfil) {
        this.modifierProfil = modifierProfil;
    }

    public String getAssocierPoste() {
        return associerPoste;
    }

    public void setAssocierPoste(String associerPoste) {
        this.associerPoste = associerPoste;
    }

    public String getAssocierProfil() {
        return associerProfil;
    }

    public void setAssocierProfil(String associerProfil) {
        this.associerProfil = associerProfil;
    }

    public String getAssocierRole() {
        return associerRole;
    }

    public void setAssocierRole(String associerRole) {
        this.associerRole = associerRole;
    }

    public String getActiverCompte() {
        return activerCompte;
    }

    public void setActiverCompte(String activerCompte) {
        this.activerCompte = activerCompte;
    }

    public String getDesactiverCompte() {
        return desactiverCompte;
    }

    public void setDesactiverCompte(String desactiverCompte) {
        this.desactiverCompte = desactiverCompte;
    }

    public String getAdministration() {
        return administration;
    }

    public void setAdministration(String administration) {
        this.administration = administration;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public String getRapport() {
        return rapport;
    }

    public void setRapport(String rapport) {
        this.rapport = rapport;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getSecurite() {
        return securite;
    }

    public void setSecurite(String securite) {
        this.securite = securite;
    }

    public String getPersonnel() {
        return personnel;
    }

    public void setPersonnel(String personnel) {
        this.personnel = personnel;
    }

    public Utilisateur getPers() {
        return pers;
    }

    public void setPers(Utilisateur pers) {
        this.pers = pers;
    }

    public Utilisateur getPerse() {
        return perse;
    }

    public void setPerse(Utilisateur perse) {
        this.perse = perse;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

}
