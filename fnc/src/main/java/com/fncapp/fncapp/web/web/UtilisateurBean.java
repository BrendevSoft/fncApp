/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.web.web;

import com.fncapp.fncapp.impl.shiro.Constante;
import com.fncapp.fncapp.api.api.utils.MethodeJournalisation;
import com.fncapp.fncapp.api.entities.Groupe;
import com.fncapp.fncapp.api.entities.GroupeUtilisateurId;
import com.fncapp.fncapp.api.entities.Groupeutilisateur;
import com.fncapp.fncapp.api.entities.Juridiction;
import com.fncapp.fncapp.api.entities.Utilisateur;
import com.fncapp.fncapp.api.service.GroupeServiceBeanLocal;
import com.fncapp.fncapp.api.service.GroupeUtilisateurServiceBeanLocal;
import com.fncapp.fncapp.api.service.JuridictionServiceBeanLocal;
import com.fncapp.fncapp.api.service.UtilisateurServiceBeanLocal;
import com.fncapp.fncapp.impl.transaction.TransactionManager;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.ServletContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author Administrateur
 */
@Named(value = "utilisateurBean")
@ViewScoped
public class UtilisateurBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Utilisateur utilisateur;
    private Utilisateur utilisateur1;
    private Juridiction juridiction;
    private List<Utilisateur> utilisateursTotal;
    private List<Utilisateur> utilisateursFilter;
    private List<Juridiction> juridictions;
    private Groupeutilisateur profilUtilisateur;
    private Groupeutilisateur profilUtilisateurNew;
    private List<Groupeutilisateur> profilUtilisateurs;
    private List<Utilisateur> utilisateurs;
    private List<Utilisateur> utilisateurs2;
    private List<Utilisateur> list;
    private Groupe profil;
    private List<Groupe> profils;
    private boolean skip;
    private String space = "  ";
    private List<String> situation;
    private List<String> pays;
    private List<Utilisateur> utilisateurs1;
    private Date date = new Date();
    private Utilisateur u;
    private MethodeJournalisation journalisation;

    private InputStream inptStrm;

    @EJB
    private JuridictionServiceBeanLocal jsbl;
    @EJB
    private UtilisateurServiceBeanLocal usbl;
    @EJB
    private GroupeServiceBeanLocal psbl1;
    @EJB
    private GroupeUtilisateurServiceBeanLocal pusbl;

    /**
     * Creates a new instance of UtilisateurBean
     */
    public UtilisateurBean() {
        this.utilisateursTotal = new ArrayList<>();
        this.utilisateur = new Utilisateur();
        this.utilisateur1 = new Utilisateur();
        this.utilisateurs = new ArrayList<>();
        this.utilisateurs1 = new ArrayList<>();
        this.utilisateurs2 = new ArrayList<>();
        this.profil = new Groupe();
        this.profils = new ArrayList<>();
        this.situation = new ArrayList<>();
        this.pays = new ArrayList<>();
        this.u = new Utilisateur();
        this.profilUtilisateur = new Groupeutilisateur();
        this.profilUtilisateurNew = new Groupeutilisateur();
        this.profilUtilisateurs = new ArrayList<>();
        this.journalisation = new MethodeJournalisation();
        this.list = new ArrayList<>();
        this.juridictions = new ArrayList<>();
        this.juridiction = new Juridiction();
    }

    public void save(java.awt.event.ActionEvent actionEvent) throws SystemException {
        FacesContext context = FacesContext.getCurrentInstance();
        UserTransaction tx = TransactionManager.getUserTransaction();
        try {
            tx.begin();
            if (this.utilisateur.getId() == null) {
                this.utilisateur.setJuridiction(juridiction);
                this.utilisateur.setDatecreation(new Date());
                this.utilisateur.setRowvers(new Date());
                this.utilisateur.setProfilactif(false);
                utilisateur.setLogin(utilisateur.getPrenom().charAt(0) + "." + utilisateur.getNom());
                if (usbl.getBy("login", utilisateur.getLogin()).isEmpty()) {
                    utilisateur.setPasswd(new Sha256Hash(Constante.MOT_DE_PASSE_DEFAUT).toHex());
                    usbl.saveOne(utilisateur);
                    journalisation.saveLog4j(UtilisateurBean.class.getName(), Level.INFO, "Enregistrement d'un personnel :" + utilisateur.getNom() + " " + utilisateur.getPrenom());
                    utilisateur = new Utilisateur();
                    context.addMessage(null, new FacesMessage(Constante.ENREGISTREMENT_REUSSIT));
                } else {
                    int test = 1;
                    do {
                        test++;
                    } while (usbl.getBy("login", utilisateur.getLogin() + "" + test).isEmpty() == false);
                    utilisateur.setPasswd(new Sha256Hash(Constante.MOT_DE_PASSE_DEFAUT).toHex());
                    utilisateur.setLogin(utilisateur.getLogin() + "" + test);
                    usbl.saveOne(utilisateur);

                    journalisation.saveLog4j(UtilisateurBean.class.getName(), Level.INFO, "Enregistrement d'un personnel :" + utilisateur.getNom() + " " + utilisateur.getPrenom());
                    utilisateur = new Utilisateur();
                    context.addMessage(null, new FacesMessage(Constante.ENREGISTREMENT_REUSSIT));

                }

            } else {
                this.utilisateur.setJuridiction(juridiction);
                this.utilisateur.setRowvers(new Date());
                this.usbl.updateOne(utilisateur);
                context.addMessage(null, new FacesMessage(Constante.MODIFICATION_REUSSIT));
            }
            this.juridiction = new Juridiction();
            this.utilisateur = new Utilisateur();
            tx.commit();
        } catch (Exception e) {
            e.getMessage();
            context.addMessage(null, new FacesMessage(Constante.ENREGISTREMENT_ECHOUE));
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

    public void handleFileUpload(FileUploadEvent event) {
        try {
            String image = String.valueOf((int) (Math.random() * 10000000));
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String newFileName = servletContext.getRealPath("") + File.separator + "resources" + File.separator + "images" + File.separator + image + event.getFile().getFileName();
            InputStream inputStream = event.getFile().getInputstream();
            inptStrm = event.getFile().getInputstream();
            ImageOutputStream out = new FileImageOutputStream(new File(newFileName));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            inputStream.close();
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    public void importer() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        if (inptStrm != null) {
            ArrayList<String> values = new ArrayList<String>();
            try {
                InputStream input = inptStrm;
                XSSFWorkbook wb = new XSSFWorkbook(input);
                XSSFSheet sheet = wb.getSheetAt(0);
                XSSFRow v = sheet.getRow(0);
                Iterator rows = sheet.rowIterator();
                while (rows.hasNext()) {
                    values.clear();
                    XSSFRow row = (XSSFRow) rows.next();
                    //if (row.getRowNum() > 0) {
                    Iterator cells = row.cellIterator();

                    while (cells.hasNext()) {
                        XSSFCell cell = (XSSFCell) cells.next();

                        if (XSSFCell.CELL_TYPE_NUMERIC == cell.getCellType()) {
                            values.add(Integer.toString((int) cell.getNumericCellValue()));
                        } else if (XSSFCell.CELL_TYPE_STRING == cell.getCellType()) {
                            values.add(cell.getStringCellValue());
                        }
                    }

                    if (values.size() > 4) {
                        if (values.get(0).length() > 1 && values.get(1).length() > 1 && values.get(2).length() > 1 && values.get(3).matches("^[00228|+228]?[9|2][\\d]{7,}$") && values.get(4).matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Zaz0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
                            if (values.get(2).equals("Masculin") || values.get(2).equals("Feminin")) {
                                utilisateur.setNom(values.get(0));
                                utilisateur.setPrenom(values.get(1));
                                utilisateur.setSexe(values.get(2));
                                adresse.setEmail(values.get(4).replace(",", "."));
                                adresse.setTelephone(values.get(3));
                                utilisateur.setAdresse(adresse);
                                utilisateur.setLogin(utilisateur.getPrenom().charAt(0) + "." + utilisateur.getNom());
                                if (usbl.getBy("login", utilisateur.getLogin()).isEmpty()) {
                                    utilisateur.setMotPasse(new Sha256Hash(Constante.MOT_DE_PASSE_DEFAUT).toHex());
                                    usbl.saveOne(utilisateur);
                                    journalisation.saveLog4j(UtilisateurBean.class.getName(), Level.INFO, "Enregistrement d'un personnel :" + utilisateur.getNom() + " " + utilisateur.getPrenom());
                                    adresse = new Adresse();
                                    utilisateur = new Utilisateur();
                                    context.addMessage(null, new FacesMessage("Enrégistrement réussi!"));

                                } else {
                                    int test = 1;
                                    do {
                                        test++;
                                    } while (usbl.getBy("login", utilisateur.getLogin() + "" + test).isEmpty() == false);
                                    utilisateur.setMotPasse(new Sha256Hash(Constante.MOT_DE_PASSE_DEFAUT).toHex());
                                    utilisateur.setLogin(utilisateur.getLogin() + "" + test);
                                    usbl.saveOne(utilisateur);

                                    journalisation.saveLog4j(UtilisateurBean.class.getName(), Level.INFO, "Enregistrement d'un personnel :" + utilisateur.getNom() + " " + utilisateur.getPrenom());
                                    adresse = new Adresse();
                                    utilisateur = new Utilisateur();
                                    context.addMessage(null, new FacesMessage("Enrégistrement réussi!"));

                                }
                            } else {
                                int nbrLigne1 = row.getRowNum() + 1;
                                context.addMessage(null, new FacesMessage("Le sexe saisit Ã la ligne " + nbrLigne1 + " est incorrect , le sexe doit être Masculun ou Feminin svp!"));
                            }
                        } else {
                            int nbrLigne2 = row.getRowNum() + 1;
                            context.addMessage(null, new FacesMessage("Syntaxe de la ligne " + nbrLigne2 + " est incorrect !"));
                        }
                    } else {
                        int nbrLigne = row.getRowNum() + 1;
                        context.addMessage(null, new FacesMessage("Valeurs insuffisantes Ã  la ligne " + nbrLigne + " !"));
                        values.clear();
                    }
                }

                inptStrm = null;
            } catch (Exception ex) {
                System.out.println("Erreur");
                ex.printStackTrace();
            }
        } else {
            context.addMessage(null, new FacesMessage("Veuillez choisir le fichier a importer svp !"));
        }

    }
     */
    public void annulerImporter() {
        inptStrm = null;
    }

    public void nouveau(ActionEvent actionEvent) {
        this.utilisateur = new Utilisateur();
    }

    public void cancel(ActionEvent actionEvent) {
        this.juridiction = new Juridiction();
        this.utilisateur = new Utilisateur();
    }

    public void cancelProfil(ActionEvent actionEvent) {
        this.profil = new Groupe();
        this.utilisateur = new Utilisateur();
        this.profilUtilisateur = new Groupeutilisateur();
    }

    public Date max() {
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.YEAR, -15);
        return ca.getTime();
    }

    public void getUsersObject(Long id) {
        this.utilisateur = this.usbl.find(id);
        if (this.utilisateur.getJuridiction() != null) {
            this.juridiction = this.utilisateur.getJuridiction();
        }
    }

    public void getObject(GroupeUtilisateurId id) {
        this.profilUtilisateur = this.pusbl.find(id);
        this.profil = this.profilUtilisateur.getGroupe();
        this.utilisateur = this.profilUtilisateur.getUtilisateur();

    }

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    public void associerProfil() {
        FacesContext context = FacesContext.getCurrentInstance();
        UserTransaction tx = TransactionManager.getUserTransaction();
        try {
            tx.begin();
            for (Utilisateur utilisateur1 : utilisateurs1) {
                this.profilUtilisateur.setGroupe(profil);
                this.profilUtilisateur.setUtilisateur(utilisateur1);
                this.profilUtilisateur.setDatecreation(new Date());
                this.pusbl.saveOne(profilUtilisateur);

                journalisation.saveLog4j(UtilisateurBean.class.getName(), Level.INFO, "Affectation du profil:" + profil.getNom() + " à l'utilisateur :" + utilisateur1.getNom() + " " + utilisateur1.getPrenom());
            }
            this.profilUtilisateur = new Groupeutilisateur();
            this.profil = new Groupe();
            context.addMessage(null, new FacesMessage(Constante.ENREGISTREMENT_REUSSIT));
            tx.commit();
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(Constante.ENREGISTREMENT_ECHOUE));
            try {
                tx.rollback();
            } catch (IllegalStateException ex) {
                Logger.getLogger(UtilisateurBean.class.getName()).log(Level.FATAL, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(UtilisateurBean.class.getName()).log(Level.FATAL, null, ex);
            } catch (SystemException ex) {
                Logger.getLogger(UtilisateurBean.class.getName()).log(Level.FATAL, null, ex);
            }
        }
    }

    public void modifierAssocierProfil() {
        FacesContext context = FacesContext.getCurrentInstance();
        UserTransaction tx = TransactionManager.getUserTransaction();
        try {
            tx.begin();
            if (utilisateur != null) {
                List<Groupeutilisateur> gs = this.pusbl.getBy("utilisateur", utilisateur);
                System.out.println(gs.get(0));
                for (Groupeutilisateur g : gs) {
                    this.pusbl.supGroupeUtilisateurs(g);
                }
                gs.clear();

                this.profilUtilisateur.setGroupe(profil);
                this.profilUtilisateur.setUtilisateur(utilisateur);
                this.profilUtilisateur.setDatecreation(new Date());
                this.pusbl.saveOne(profilUtilisateur);
                journalisation.saveLog4j(UtilisateurBean.class.getName(), Level.INFO, "Modification du profil:" + profil.getNom() + " à l'utilisateur :" + profilUtilisateur.getUtilisateur().getNom() + " " + profilUtilisateur.getUtilisateur().getPrenom());

                this.profil = new Groupe();
                this.utilisateur = new Utilisateur();
                this.profilUtilisateur = new Groupeutilisateur();
                context.addMessage(null, new FacesMessage(Constante.MODIFICATION_REUSSIT));
            }
            tx.commit();
        } catch (Exception e) {
            e.getMessage();
            this.profil = new Groupe();
            this.utilisateur = new Utilisateur();
            this.profilUtilisateur = new Groupeutilisateur();
            context.addMessage(null, new FacesMessage("Veillez selectionner l'utilisateur à modifier dans le tableau"));
            //  context.addMessage(null, new FacesMessage(Constante.ENREGISTREMENT_ECHOUE));
            try {
                tx.rollback();
            } catch (IllegalStateException ex) {
                Logger.getLogger(UtilisateurBean.class.getName()).log(Level.FATAL, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(UtilisateurBean.class.getName()).log(Level.FATAL, null, ex);
            } catch (SystemException ex) {
                Logger.getLogger(UtilisateurBean.class.getName()).log(Level.FATAL, null, ex);
            }
        }

    }

    public List<Utilisateur> utilisateurIsProfil() {
        List<Utilisateur> us = this.usbl.getAll();
        List<Utilisateur> us1 = new ArrayList<>();
        for (Utilisateur us11 : us) {
            /* if (us11.getProfil() != null) {
             us1.add(us11);
             }*/
        }
        return us1;
    }

    public List<Utilisateur> utilisateursNonProfil() {
        return this.pusbl.getUtilisateursGroupe();
    }

    public void utilisateursSelectProfil() {
        for (Utilisateur utilisateur1 : utilisateursNonProfil()) {
            utilisateurs1.add(utilisateur1);
        }
    }

    public void activer() {
        System.out.println("testt");
    }

    public void activerUtilisateur(Long u) {
        FacesContext context = FacesContext.getCurrentInstance();
        UserTransaction tx = TransactionManager.getUserTransaction();
        try {
            tx.begin();
            List<Utilisateur> us = this.usbl.getBy("id", u);
            Utilisateur u1 = new Utilisateur();
            u1 = us.get(0);
            u1.setProfilactif(true);
            this.usbl.updateOne(u1);

            journalisation.saveLog4j(UtilisateurBean.class.getName(), Level.INFO, "Activation de l'utilisateur:" + u1.getNom() + " " + u1.getPrenom());
            context.addMessage(null, new FacesMessage(u1.getLogin().concat(space) + "activé"));
            u1 = new Utilisateur();
            tx.commit();
        } catch (Exception e) {
            e.getMessage();
            context.addMessage(null, new FacesMessage(Constante.DESACTIVATION_ECHOU));
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

    public void desactiverUtilisateur(Long u) {
        FacesContext context = FacesContext.getCurrentInstance();
        UserTransaction tx = TransactionManager.getUserTransaction();
        try {
            tx.begin();
            List<Utilisateur> us = this.usbl.getBy("id", u);
            Utilisateur u1 = new Utilisateur();
            u1 = us.get(0);
            u1.setProfilactif(false);
            this.usbl.updateOne(u1);
            journalisation.saveLog4j(UtilisateurBean.class.getName(), Level.INFO, "Désactivation de l'utilisateur:" + u1.getNom() + " " + u1.getPrenom());
            context.addMessage(null, new FacesMessage(u1.getLogin().concat(space) + "désactivé"));
            u1 = new Utilisateur();
            tx.commit();
        } catch (Exception e) {
            e.getMessage();
            context.addMessage(null, new FacesMessage(Constante.DESACTIVATION_ECHOU));
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

    public List<Utilisateur> utilisateurActif() {
        List<Utilisateur> us = this.pusbl.getUtilisateursNonGroupe();
        List<Utilisateur> us1 = new ArrayList<>();
        for (Utilisateur us11 : us) {
            if (us11.getProfilactif() == true) {
                us1.add(us11);
            }
        }
        return us1;
    }

    public List<Utilisateur> utilisateurInactif() {
        List<Utilisateur> us = this.pusbl.getUtilisateursNonGroupe();
        List<Utilisateur> us1 = new ArrayList<>();
        for (Utilisateur us11 : us) {
            if (us11.getProfilactif() == false) {
                us1.add(us11);
            }
        }
        return us1;
    }

    public void modifierPersonnelProfil() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            // this.utilisateur.setProfil(profil);
            this.usbl.updateOne(utilisateur);
            context.addMessage(null, new FacesMessage(Constante.MODIFICATION_REUSSIT));
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public boolean checkIntConnection() {
        boolean status = false;
        Socket sock = new Socket();
        InetSocketAddress address = new InetSocketAddress("www.google.com", 80);
        try {
            sock.connect(address, 3000);
            if (sock.isConnected()) {
                status = true;
            }
        } catch (Exception e) {

        } finally {
            try {
                sock.close();
            } catch (Exception e) {

            }
        }

        return status;
    }

    public List<Groupe> getProfils() {
        profils = this.psbl1.getAll();
        return profils;
    }

    public List<Groupeutilisateur> getProfilUtilisateurs() {
        this.profilUtilisateurs = this.pusbl.getAll();
        return profilUtilisateurs;
    }

    public List<Utilisateur> getUtilisateursTotal() {
        this.utilisateursTotal = this.usbl.getAll();
        return utilisateursTotal;
    }

    public List<Utilisateur> getUtilisateurs2() {
        this.utilisateurs2 = this.usbl.getAll();
        return utilisateurs2;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Juridiction getJuridiction() {
        return juridiction;
    }

    public void setJuridiction(Juridiction juridiction) {
        this.juridiction = juridiction;
    }

    public List<Utilisateur> getUtilisateursFilter() {
        return utilisateursFilter;
    }

    public void setUtilisateursFilter(List<Utilisateur> utilisateursFilter) {
        this.utilisateursFilter = utilisateursFilter;
    }

    public List<Juridiction> getJuridictions() {
        return juridictions;
    }

    public void setJuridictions(List<Juridiction> juridictions) {
        this.juridictions = juridictions;
    }

    public Groupeutilisateur getProfilUtilisateur() {
        return profilUtilisateur;
    }

    public void setProfilUtilisateur(Groupeutilisateur profilUtilisateur) {
        this.profilUtilisateur = profilUtilisateur;
    }

    public Groupeutilisateur getProfilUtilisateurNew() {
        return profilUtilisateurNew;
    }

    public void setProfilUtilisateurNew(Groupeutilisateur profilUtilisateurNew) {
        this.profilUtilisateurNew = profilUtilisateurNew;
    }

    public List<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(List<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    public List<Utilisateur> getList() {
        return list;
    }

    public void setList(List<Utilisateur> list) {
        this.list = list;
    }

    public Groupe getProfil() {
        return profil;
    }

    public void setProfil(Groupe profil) {
        this.profil = profil;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public List<String> getSituation() {
        return situation;
    }

    public void setSituation(List<String> situation) {
        this.situation = situation;
    }

    public List<String> getPays() {
        return pays;
    }

    public void setPays(List<String> pays) {
        this.pays = pays;
    }

    public List<Utilisateur> getUtilisateurs1() {
        return utilisateurs1;
    }

    public void setUtilisateurs1(List<Utilisateur> utilisateurs1) {
        this.utilisateurs1 = utilisateurs1;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Utilisateur getU() {
        return u;
    }

    public void setU(Utilisateur u) {
        this.u = u;
    }

    public MethodeJournalisation getJournalisation() {
        return journalisation;
    }

    public void setJournalisation(MethodeJournalisation journalisation) {
        this.journalisation = journalisation;
    }

    public InputStream getInptStrm() {
        return inptStrm;
    }

    public void setInptStrm(InputStream inptStrm) {
        this.inptStrm = inptStrm;
    }

    public JuridictionServiceBeanLocal getJsbl() {
        return jsbl;
    }

    public void setJsbl(JuridictionServiceBeanLocal jsbl) {
        this.jsbl = jsbl;
    }

    public UtilisateurServiceBeanLocal getUsbl() {
        return usbl;
    }

    public void setUsbl(UtilisateurServiceBeanLocal usbl) {
        this.usbl = usbl;
    }

    public GroupeServiceBeanLocal getPsbl1() {
        return psbl1;
    }

    public void setPsbl1(GroupeServiceBeanLocal psbl1) {
        this.psbl1 = psbl1;
    }

    public GroupeUtilisateurServiceBeanLocal getPusbl() {
        return pusbl;
    }

    public void setPusbl(GroupeUtilisateurServiceBeanLocal pusbl) {
        this.pusbl = pusbl;
    }

    public Utilisateur getUtilisateur1() {
        return utilisateur1;
    }

    public void setUtilisateur1(Utilisateur utilisateur1) {
        this.utilisateur1 = utilisateur1;
    }

}
