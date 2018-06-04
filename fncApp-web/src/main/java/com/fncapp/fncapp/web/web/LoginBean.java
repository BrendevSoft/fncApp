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
    private String reponse, recupQuestion;
    private String consulterProfil, consulterRole, consulterUtilisateur, consulterCompte, consulterAssocierProfil,
            consulterAcourtAppel, consulterTribunaux, consulterInfraction, consulterPrison,
            creerPoste, modifierPoste, ajouterProfil, modifierProfil, associerPoste, associerProfil, associerRole,
            ajouterUtilisateur, modifierUtilisateur, consulterSecurite, consulterAdministration, consulterTableauBord,
            consulterCondamnation, ajouterCourtAppel, modifierCourtAppel, ajouterTribunaux, modifierTribunaux,
            ajouterInfraction, modifierInfraction, ajouterPrison, modifierPrison, ajouterCondamnation,
            modifierCondamnation, condamnation, activerCompte, desactiverCompte;
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
            this.rsl.saveOne(new Rolee("Ajouter profil"));
            this.rsl.saveOne(new Rolee("Modifier profil"));
            this.rsl.saveOne(new Rolee("Associer profil"));
            this.rsl.saveOne(new Rolee("Associer role"));
            this.rsl.saveOne(new Rolee("Activer compte"));
            this.rsl.saveOne(new Rolee("Désactiver compte"));

            this.rsl.saveOne(new Rolee("Ajouter utilisateur"));
            this.rsl.saveOne(new Rolee("Modifier utilisateur"));
            this.rsl.saveOne(new Rolee("Ajouter Court d'Appel"));
            this.rsl.saveOne(new Rolee("Modifier Court d'Appel"));
            this.rsl.saveOne(new Rolee("Ajouter juridiction"));
            this.rsl.saveOne(new Rolee("Modifier juridiction"));
            this.rsl.saveOne(new Rolee("Ajouter infraction"));
            this.rsl.saveOne(new Rolee("Modifier infraction"));
            this.rsl.saveOne(new Rolee("Ajouter prison"));
            this.rsl.saveOne(new Rolee("Modifier prison"));
            this.rsl.saveOne(new Rolee("Ajouter condamnation"));
            this.rsl.saveOne(new Rolee("Modifier condamnation"));
            this.rsl.saveOne(new Rolee("Tableau de bord"));

            this.rsl.saveOne(new Rolee("Consulter condamnation"));
            this.rsl.saveOne(new Rolee("Consulter juridiction"));
            this.rsl.saveOne(new Rolee("Consulter Court d'Appel"));
            this.rsl.saveOne(new Rolee("Consulter infraction"));
            this.rsl.saveOne(new Rolee("Consulter prison"));
            this.rsl.saveOne(new Rolee("Consulter profil"));
            this.rsl.saveOne(new Rolee("Consulter associer profil"));
            this.rsl.saveOne(new Rolee("Consulter associer role"));
            this.rsl.saveOne(new Rolee("Consulter utilisateur"));
            this.rsl.saveOne(new Rolee("Consulter compte"));

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
                    Faces.redirect("error.xhtml");
                    username = "";
                    return;
                }
            }

            if (pers != null) {
                boolean test = new Sha256Hash("admin").toHex().equals(pers.getPasswd());
                if (test && pers.getProfilactif() == true) {

                    Faces.redirect("firstConnect.xhtml");
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

                if (subject.hasRole("Ajouter profil") || subject.hasRole("Modifier profil")
                        || subject.hasRole("Associer profil") || subject.hasRole("Associer role")
                        || subject.hasRole("Activer compte") || subject.hasRole("Désactiver compte")
                        || subject.hasRole("Ajouter utilisateur") || subject.hasRole("Modifier utilisateur")
                        || subject.hasRole("Consulter profil") || subject.hasRole("Consulter associer profil")
                        || subject.hasRole("Consulter associer role") || subject.hasRole("Consulter compte")
                        || subject.hasRole("Consulter utilisateur")) {
                    this.consulterSecurite = "true";
                } else {
                    this.consulterSecurite = "false";
                }

                if (subject.hasRole("Ajouter profil") || subject.hasRole("Modifier profil")
                        || subject.hasRole("Consulter profil")) {
                    this.consulterProfil = "true";
                } else {
                    this.consulterProfil = "false";
                }

                if (subject.hasRole("Ajouter profil")) {
                    this.ajouterProfil = "true";
                } else {
                    this.ajouterProfil = "false";
                }

                if (subject.hasRole("Modifier profil")) {
                    this.modifierProfil = "true";
                } else {
                    this.modifierProfil = "false";
                }

                if (subject.hasRole("Associer profil") || subject.hasRole("Consulter associer profil")) {
                    this.consulterAssocierProfil = "true";
                } else {
                    this.consulterAssocierProfil = "false";
                }

                if (subject.hasRole("Associer profil")) {
                    this.associerProfil = "true";
                } else {
                    this.associerProfil = "false";
                }

                if (subject.hasRole("Associer role") || subject.hasRole("Consulter associer role")) {
                    this.consulterRole = "true";
                } else {
                    this.consulterRole = "false";
                }

                if (subject.hasRole("Activer compte") || subject.hasRole("Désactiver Compte")
                        || subject.hasRole("Consulter compte")) {
                    this.consulterCompte = "true";
                } else {
                    this.consulterCompte = "false";
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

                if (subject.hasRole("Ajouter utilisateur") || subject.hasRole("Modifier utilisateur")
                        || subject.hasRole("Consulter utilisateur")) {
                    this.consulterUtilisateur = "true";
                } else {
                    this.consulterUtilisateur = "false";
                }

                if (subject.hasRole("Ajouter utilisateur")) {
                    this.ajouterUtilisateur = "true";
                } else {
                    this.ajouterUtilisateur = "false";
                }

                if (subject.hasRole("Modifier utilisateur")) {
                    this.modifierUtilisateur = "true";
                } else {
                    this.modifierUtilisateur = "false";
                }

                if (subject.hasRole("Ajouter Court d'Appel") || subject.hasRole("Modifier Court d'Appel")
                        || subject.hasRole("Ajouter juridiction") || subject.hasRole("Modifier juridiction")
                        || subject.hasRole("Ajouter infraction") || subject.hasRole("Modifier infraction")
                        || subject.hasRole("Ajouter prison") || subject.hasRole("Modifier prison")
                        || subject.hasRole("Consulter infraction") || subject.hasRole("Consulter juridiction")
                        || subject.hasRole("Consulter Court d'Appel") || subject.hasRole("Consulter prison")) {
                    this.consulterAdministration = "true";
                } else {
                    this.consulterAdministration = "false";
                }

                if (subject.hasRole("Ajouter Court d'Appel") || subject.hasRole("Modifier Court d'Appel")
                        || subject.hasRole("Consulter Court d'Appel")) {
                    this.consulterAcourtAppel = "true";
                } else {
                    this.consulterAcourtAppel = "false";
                }

                if (subject.hasRole("Ajouter Court d'Appel")) {
                    this.ajouterCourtAppel = "true";
                } else {
                    this.ajouterCourtAppel = "false";
                }

                if (subject.hasRole("Modifier Court d'Appel")) {
                    this.modifierCourtAppel = "true";
                } else {
                    this.modifierCourtAppel = "false";
                }

                if (subject.hasRole("Ajouter juridiction") || subject.hasRole("Modifier juridiction")
                        || subject.hasRole("Consulter juridiction")) {
                    this.consulterTribunaux = "true";
                } else {
                    this.consulterTribunaux = "false";
                }

                if (subject.hasRole("Ajouter juridiction")) {
                    this.ajouterTribunaux = "true";
                } else {
                    this.ajouterTribunaux = "false";
                }

                if (subject.hasRole("Modifier juridiction")) {
                    this.modifierTribunaux = "true";
                } else {
                    this.modifierTribunaux = "false";
                }

                if (subject.hasRole("Ajouter infraction") || subject.hasRole("Modifier infraction")
                        || subject.hasRole("Consulter infraction")) {
                    this.consulterInfraction = "true";
                } else {
                    this.consulterInfraction = "false";
                }

                if (subject.hasRole("Ajouter infraction")) {
                    this.ajouterInfraction = "true";
                } else {
                    this.ajouterInfraction = "false";
                }

                if (subject.hasRole("Modifier infraction")) {
                    this.modifierInfraction = "true";
                } else {
                    this.modifierInfraction = "false";
                }

                if (subject.hasRole("Ajouter prison") || subject.hasRole("Modifier prison")
                        || subject.hasRole("Consulter prison")) {
                    this.consulterPrison = "true";
                } else {
                    this.consulterPrison = "false";
                }

                if (subject.hasRole("Ajouter prison")) {
                    this.ajouterPrison = "true";
                } else {
                    this.ajouterPrison = "false";
                }

                if (subject.hasRole("Modifier prison")) {
                    this.modifierPrison = "true";
                } else {
                    this.modifierPrison = "false";
                }

                if (subject.hasRole("Ajouter condamnation") || subject.hasRole("Modifier condamnation")
                        || subject.hasRole("Consulter condamnation")) {
                    this.condamnation = "true";
                } else {
                    this.condamnation = "false";
                }

                if (subject.hasRole("Ajouter condamnation")) {
                    this.ajouterCondamnation = "true";
                } else {
                    this.ajouterCondamnation = "false";
                }

                if (subject.hasRole("Modifier condamnation") || subject.hasRole("Consulter condamnation")) {
                    this.consulterCondamnation = "true";
                } else {
                    this.consulterCondamnation = "false";
                }

                if (subject.hasRole("Modifier condamnation")) {
                    this.modifierCondamnation = "true";
                } else {
                    this.modifierCondamnation = "false";
                }

                if (subject.hasRole("Tableau de bord")) {
                    this.consulterTableauBord = "true";
                } else {
                    this.consulterTableauBord = "false";
                }

                if (!avoir) {
                    Faces.redirect("error.xhtml");
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
            journalisation.saveLog4j(LoginBean.class.getSimpleName(), Level.INFO, "Déconnexion");
            EntityRealm.getSubject().logout();
            Faces.redirect("acceuil.xhtml");
            username = "";
        } catch (IOException ex) {
        }

    }

    public void modifierPasse() {
        try {
            if (newPass.trim().equals(retapPass.trim())) {
                pers.setPasswd(new Sha256Hash(newPass.trim()).toHex());
                pers.setQuestion(question);
                pers.setReponse(reponse);
                usbl.updateOne(pers);
                journalisation.saveLog4j(LoginBean.class.getSimpleName(), Level.INFO, "Modification du mot de passe de l'Utilisateur"+pers.getLogin());
                question = "";
                reponse = "";

                FacesMessage mf = new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Mot de passe corriger", "");
                FacesContext.getCurrentInstance().addMessage("", mf);
                Faces.redirect("login.xhtml");
            } else {
                FacesMessage mf = new FacesMessage(FacesMessage.SEVERITY_FATAL,
                        "Les mots de passe ne concorde pas", "");
                FacesContext.getCurrentInstance().addMessage("", mf);
            }
        } catch (Exception e) {
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

    public void reinitialiserPasse() throws IOException {
        Utilisateur pe = this.usbl.getOneBy("login", recupQuestion);
        if (pe.getProfilactif() == true) {
            if (reponse.equals(pe.getReponse())) {
                pe.setPasswd(new Sha256Hash("admin").toHex());
                pe.setQuestion(null);
                pe.setReponse(null);
                usbl.updateOne(pe);
                    journalisation.saveLog4j(LoginBean.class.getSimpleName(), Level.INFO, "Réinitialisation du mot de passe de l'Utilisateur"+pe.getLogin());
            
                question = "";
                reponse = "";
                FacesMessage mf = new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Mot de passe réinitialisé", "");
                FacesContext.getCurrentInstance().addMessage("", mf);
                Faces.redirect("login.xhtml");
            } else {
                FacesMessage mf = new FacesMessage(FacesMessage.SEVERITY_FATAL,
                        "La reponse saisie est incorreste", "");
                FacesContext.getCurrentInstance().addMessage("", mf);
            }
        }

    }

    public void recupererQuestion() {
        try {
            if (!per.equals("")) {
                Utilisateur pe = this.usbl.getOneBy("login", per);
                if (pe != null) {
                    if (!pe.getPasswd().equals(new Sha256Hash("admin").toHex())) {
                        if (pe.getProfilactif() == true) {
                            this.recupQuestion = pe.getQuestion();
                            Faces.redirect("reinitPass.xhtml");
                        } else {
                            per = "";
                            FacesMessage mf = new FacesMessage(FacesMessage.SEVERITY_FATAL,
                                    "Votre compte est inactif,contactez l'administrateur", "");
                            FacesContext.getCurrentInstance().addMessage("", mf);
                        }
                    } else {
                        FacesMessage mf = new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Connectez vous à votre compte pour changer votre mot de passe", "");
                        FacesContext.getCurrentInstance().addMessage("", mf);
                    }
                } else {
                    per = "";
                    FacesMessage mf = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Le login saisi est incorrect", "");
                    FacesContext.getCurrentInstance().addMessage("", mf);
                }
            }
        } catch (Exception e) {
        }
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

    public String getConsulterProfil() {
        return consulterProfil;
    }

    public void setConsulterProfil(String consulterProfil) {
        this.consulterProfil = consulterProfil;
    }

    public String getConsulterRole() {
        return consulterRole;
    }

    public void setConsulterRole(String consulterRole) {
        this.consulterRole = consulterRole;
    }

    public String getConsulterUtilisateur() {
        return consulterUtilisateur;
    }

    public void setConsulterUtilisateur(String consulterUtilisateur) {
        this.consulterUtilisateur = consulterUtilisateur;
    }

    public String getConsulterCompte() {
        return consulterCompte;
    }

    public void setConsulterCompte(String consulterCompte) {
        this.consulterCompte = consulterCompte;
    }

    public String getConsulterAssocierProfil() {
        return consulterAssocierProfil;
    }

    public void setConsulterAssocierProfil(String consulterAssocierProfil) {
        this.consulterAssocierProfil = consulterAssocierProfil;
    }

    public String getConsulterAcourtAppel() {
        return consulterAcourtAppel;
    }

    public void setConsulterAcourtAppel(String consulterAcourtAppel) {
        this.consulterAcourtAppel = consulterAcourtAppel;
    }

    public String getConsulterTribunaux() {
        return consulterTribunaux;
    }

    public void setConsulterTribunaux(String consulterTribunaux) {
        this.consulterTribunaux = consulterTribunaux;
    }

    public String getConsulterInfraction() {
        return consulterInfraction;
    }

    public void setConsulterInfraction(String consulterInfraction) {
        this.consulterInfraction = consulterInfraction;
    }

    public String getConsulterPrison() {
        return consulterPrison;
    }

    public void setConsulterPrison(String consulterPrison) {
        this.consulterPrison = consulterPrison;
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

    public String getAjouterProfil() {
        return ajouterProfil;
    }

    public void setAjouterProfil(String ajouterProfil) {
        this.ajouterProfil = ajouterProfil;
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

    public String getAjouterUtilisateur() {
        return ajouterUtilisateur;
    }

    public void setAjouterUtilisateur(String ajouterUtilisateur) {
        this.ajouterUtilisateur = ajouterUtilisateur;
    }

    public String getModifierUtilisateur() {
        return modifierUtilisateur;
    }

    public void setModifierUtilisateur(String modifierUtilisateur) {
        this.modifierUtilisateur = modifierUtilisateur;
    }

    public String getConsulterSecurite() {
        return consulterSecurite;
    }

    public void setConsulterSecurite(String consulterSecurite) {
        this.consulterSecurite = consulterSecurite;
    }

    public String getConsulterAdministration() {
        return consulterAdministration;
    }

    public void setConsulterAdministration(String consulterAdministration) {
        this.consulterAdministration = consulterAdministration;
    }

    public String getConsulterTableauBord() {
        return consulterTableauBord;
    }

    public void setConsulterTableauBord(String consulterTableauBord) {
        this.consulterTableauBord = consulterTableauBord;
    }

    public String getConsulterCondamnation() {
        return consulterCondamnation;
    }

    public void setConsulterCondamnation(String consulterCondamnation) {
        this.consulterCondamnation = consulterCondamnation;
    }

    public String getAjouterCourtAppel() {
        return ajouterCourtAppel;
    }

    public void setAjouterCourtAppel(String ajouterCourtAppel) {
        this.ajouterCourtAppel = ajouterCourtAppel;
    }

    public String getModifierCourtAppel() {
        return modifierCourtAppel;
    }

    public void setModifierCourtAppel(String modifierCourtAppel) {
        this.modifierCourtAppel = modifierCourtAppel;
    }

    public String getAjouterTribunaux() {
        return ajouterTribunaux;
    }

    public void setAjouterTribunaux(String ajouterTribunaux) {
        this.ajouterTribunaux = ajouterTribunaux;
    }

    public String getModifierTribunaux() {
        return modifierTribunaux;
    }

    public void setModifierTribunaux(String modifierTribunaux) {
        this.modifierTribunaux = modifierTribunaux;
    }

    public String getAjouterInfraction() {
        return ajouterInfraction;
    }

    public void setAjouterInfraction(String ajouterInfraction) {
        this.ajouterInfraction = ajouterInfraction;
    }

    public String getModifierInfraction() {
        return modifierInfraction;
    }

    public void setModifierInfraction(String modifierInfraction) {
        this.modifierInfraction = modifierInfraction;
    }

    public String getAjouterPrison() {
        return ajouterPrison;
    }

    public void setAjouterPrison(String ajouterPrison) {
        this.ajouterPrison = ajouterPrison;
    }

    public String getModifierPrison() {
        return modifierPrison;
    }

    public void setModifierPrison(String modifierPrison) {
        this.modifierPrison = modifierPrison;
    }

    public String getAjouterCondamnation() {
        return ajouterCondamnation;
    }

    public void setAjouterCondamnation(String ajouterCondamnation) {
        this.ajouterCondamnation = ajouterCondamnation;
    }

    public String getModifierCondamnation() {
        return modifierCondamnation;
    }

    public void setModifierCondamnation(String modifierCondamnation) {
        this.modifierCondamnation = modifierCondamnation;
    }

    public String getCondamnation() {
        return condamnation;
    }

    public void setCondamnation(String condamnation) {
        this.condamnation = condamnation;
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

    public String getRecupQuestion() {
        return recupQuestion;
    }

    public void setRecupQuestion(String recupQuestion) {
        this.recupQuestion = recupQuestion;
    }

}
