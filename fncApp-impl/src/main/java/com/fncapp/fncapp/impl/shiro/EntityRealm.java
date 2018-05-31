package com.fncapp.fncapp.impl.shiro;

import com.fncapp.fncapp.api.dao.GroupeRoleDaoBeanLocal;
import com.fncapp.fncapp.api.dao.GroupeUtilisateurDaoBeanLocal;
import com.fncapp.fncapp.api.dao.UtilisateurDaoBeanLocal;
import com.fncapp.fncapp.api.entities.Groupe;
import com.fncapp.fncapp.api.entities.GroupeRole;
import com.fncapp.fncapp.api.entities.Groupeutilisateur;
import com.fncapp.fncapp.api.entities.Utilisateur;
import java.util.ArrayList;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;

public class EntityRealm extends AuthorizingRealm {

    @EJB
    private GroupeUtilisateurDaoBeanLocal pudbl;
    private UtilisateurDaoBeanLocal udbl;

    private GroupeRoleDaoBeanLocal prdbl;

    private List<Groupeutilisateur> profilUtilisateurs;
    private static Groupeutilisateur profilUtilisateur;
    private static List<Utilisateur> utilisateurs;
    private static Utilisateur utilisateur;

    private static Groupe profil;
    private static List<GroupeRole> profilRoles;

    public EntityRealm() throws NamingException {
        this.profilUtilisateurs = new ArrayList<>();
        System.out.println("enter entity realm");
        this.setName("entityRealm");
        CredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher("SHA-256");
        this.setCredentialsMatcher(credentialsMatcher);
        InitialContext context = new InitialContext();
        //La classe session bean de l'utilisateur(précise la classe du sesion bean)
        this.pudbl = (GroupeUtilisateurDaoBeanLocal) context.lookup("java:global/fncApp-web/GroupeUtilisateurDaoBean");
        this.udbl = (UtilisateurDaoBeanLocal) context.lookup("java:global/fncApp-web/UtilisateurDaoBean");
        //La classe session bean des roles par profil(précise la classe du sesion bean)
        this.prdbl = (GroupeRoleDaoBeanLocal) context.lookup("java:global/fncApp-web/GroupeRoleDaoBean");
        System.out.println("out entity realm");
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {

        final UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

        setUtilisateur(getUdbl().getOneBy("login", token.getUsername()));

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo();
        try {
            if (getUtilisateur() != null) {

                simpleAuthenticationInfo = new SimpleAuthenticationInfo(getUtilisateur().getLogin(), getUtilisateur().getPasswd(), getName());

            } else {
                simpleAuthenticationInfo = null;
                throw new UnknownAccountException("Utilisateur inconnu");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return simpleAuthenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //????????????????????????????????????????
        String userId = (String) principals.fromRealm(this.getName()).iterator().next();
        //Dans les guillemets on met le nom dansla base de donnée, ensuite on met la valeur
        setUtilisateur(getUdbl().getBy("login", userId).get(0));
        if (getUtilisateur() != null) {
            final SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            setProfilUtilisateurs(this.getPudbl().getBy("utilisateur", getUtilisateur()));
            if (!profilUtilisateurs.isEmpty()) {
                for (Groupeutilisateur pu : getProfilUtilisateurs()) {
                    if (pu.getDateRevocation() == null) {
                        setProfilRoles(this.getPrdbl().getBy("groupe", pu.getGroupe()));
                    }
                }

            }

            final List<String> roles = new ArrayList<>();
            for (GroupeRole proRole : getProfilRoles()) {
                roles.add(proRole.getRole().getNom());
            }
            info.addRoles(roles);

            return info;
        } else {
            return null;
        }
    }

    public static Utilisateur getUser() {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            return getUtilisateur();
        }
        return null;
    }

    public static Long getUserId() {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            return getUtilisateur().getId();
        }
        return null;
    }

    @Produces @SessionScoped
    public static Subject getSubject() {
        return SecurityUtils.getSubject();

    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    public GroupeUtilisateurDaoBeanLocal getPudbl() {
        return pudbl;
    }

    public void setPudbl(GroupeUtilisateurDaoBeanLocal pudbl) {
        this.pudbl = pudbl;
    }

    /**
     * @return the udbl
     */
    public UtilisateurDaoBeanLocal getUdbl() {
        return udbl;
    }

    /**
     * @param udbl the udbl to set
     */
    public void setUdbl(UtilisateurDaoBeanLocal udbl) {
        this.udbl = udbl;
    }

    public GroupeRoleDaoBeanLocal getPrdbl() {
        return prdbl;
    }

    public void setPrdbl(GroupeRoleDaoBeanLocal prdbl) {
        this.prdbl = prdbl;
    }

    public List<Groupeutilisateur> getProfilUtilisateurs() {
        return profilUtilisateurs;
    }

    public void setProfilUtilisateurs(List<Groupeutilisateur> profilUtilisateurs) {
        this.profilUtilisateurs = profilUtilisateurs;
    }

    public static Groupeutilisateur getProfilUtilisateur() {
        return profilUtilisateur;
    }

    public static void setProfilUtilisateur(Groupeutilisateur profilUtilisateur) {
        EntityRealm.profilUtilisateur = profilUtilisateur;
    }

    public static List<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public static void setUtilisateurs(List<Utilisateur> utilisateurs) {
        EntityRealm.utilisateurs = utilisateurs;
    }

    public static Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public static void setUtilisateur(Utilisateur utilisateur) {
        EntityRealm.utilisateur = utilisateur;
    }

    public static Groupe getProfil() {
        return profil;
    }

    public static void setProfil(Groupe profil) {
        EntityRealm.profil = profil;
    }

    public static List<GroupeRole> getProfilRoles() {
        return profilRoles;
    }

    public static void setProfilRoles(List<GroupeRole> profilRoles) {
        EntityRealm.profilRoles = profilRoles;
    }

}
