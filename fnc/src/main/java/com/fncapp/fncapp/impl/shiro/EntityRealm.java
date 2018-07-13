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

public class EntityRealm extends AuthorizingRealm {

    protected static List<Groupeutilisateur> profilUtilisateurs;
    protected static List<GroupeRole> profilRoles;
    protected static GroupeUtilisateurDaoBeanLocal pudbl;
    protected static UtilisateurDaoBeanLocal udbl;
    protected static GroupeRoleDaoBeanLocal prdbl;
    protected static Groupeutilisateur profilUtilisateur;
    protected static Utilisateur utilisateur;
    protected static Groupe profil;

    public EntityRealm() throws NamingException {
        CredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher("SHA-256");
        this.setCredentialsMatcher(credentialsMatcher);
        InitialContext context = new InitialContext();
        //La classe session bean de l'utilisateur(précise la classe du sesion bean)
        EntityRealm.pudbl = (GroupeUtilisateurDaoBeanLocal) context.lookup("java:global/fnc/GroupeUtilisateurDaoBean");
        EntityRealm.udbl = (UtilisateurDaoBeanLocal) context.lookup("java:global/fnc/UtilisateurDaoBean");
        //La classe session bean des roles par profil(précise la classe du sesion bean)
        EntityRealm.prdbl = (GroupeRoleDaoBeanLocal) context.lookup("java:global/fnc/GroupeRoleDaoBean");
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {

        final UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

        utilisateur = udbl.getOneBy("login", token.getUsername());

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo();
        try {
            if (utilisateur != null) {

                simpleAuthenticationInfo = new SimpleAuthenticationInfo(utilisateur, utilisateur.getPasswd(), getName());

            } else {
                simpleAuthenticationInfo = null;
                throw new UnknownAccountException("Utilisateur inconnu");
            }
        } catch (UnknownAccountException e) {
        }
        return simpleAuthenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //????????????????????????????????????????
        utilisateur = (Utilisateur) principals.fromRealm(this.getName()).iterator().next();
        final SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        try {
            if (utilisateur != null) {
                profilUtilisateurs = pudbl.getBy("utilisateur", utilisateur);
                if (!profilUtilisateurs.isEmpty()) {
                    for (Groupeutilisateur pu : profilUtilisateurs) {
                        profilRoles = prdbl.getBy("groupe", pu.getGroupe());

                        final List<String> roles = new ArrayList<>();
                        for (GroupeRole proRole : profilRoles) {
                            roles.add(proRole.getRole().getNom());
                        }
                        info.addRoles(roles);
                    }

                }
            } else {
                return null;
            }
        } catch (Exception e) {
        }
        //Dans les guillemets on met le nom dansla base de donnée, ensuite on met la valeur
        return info;
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();

    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }
}
