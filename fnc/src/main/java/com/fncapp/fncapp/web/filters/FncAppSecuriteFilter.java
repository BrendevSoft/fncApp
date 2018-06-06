/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.web.filters;

import com.fncapp.fncapp.impl.shiro.EntityRealm;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Mikel
 */
public class FncAppSecuriteFilter implements Filter {

    private static final String PAGE_ERROR = "../error.xhtml";

    private FilterConfig filterConfig = null;

    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        System.out.println("Initialisation du filtre :" + this.filterConfig.getFilterName());
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        HttpServletRequest request2 = (HttpServletRequest) request;
        String page = request2.getRequestURI().substring(request2.getContextPath().length() + 1);
        try {

            switch (page) {
                case "securite/utilisateurs.xhtml":
                    if (EntityRealm.getSubject().hasRole("Consulter utilisateur") || EntityRealm.getSubject().hasRole("Ajouter utilisateur")
                            || EntityRealm.getSubject().hasRole("Modifier utilisateur")) {
                        request2.getRequestDispatcher("utilisateurs.xhtml").forward(request, response);
                    } else {
                        EntityRealm.getSubject().logout();
                        request2.getRequestDispatcher(PAGE_ERROR).forward(request, response);
                    }
                    break;

                case "securite/profil.xhtml":
                    if (EntityRealm.getSubject().hasRole("Consulter profil") || EntityRealm.getSubject().hasRole("Ajouter profil")
                            || EntityRealm.getSubject().hasRole("Modifier profil")) {
                        request2.getRequestDispatcher("profil.xhtml").forward(request, response);
                    } else {
                        EntityRealm.getSubject().logout();
                        request2.getRequestDispatcher(PAGE_ERROR).forward(request, response);
                    }
                    break;

                case "securite/profils.xhtml":
                    if (EntityRealm.getSubject().hasRole("Consulter associer profil") || EntityRealm.getSubject().hasRole("Associer profil")) {
                        request2.getRequestDispatcher("profils.xhtml").forward(request, response);
                    } else {
                        EntityRealm.getSubject().logout();
                        request2.getRequestDispatcher(PAGE_ERROR).forward(request, response);
                    }
                    break;

                case "securite/roles.xhtml":
                    if (EntityRealm.getSubject().hasRole("Consulter associer role") || EntityRealm.getSubject().hasRole("Associer role")) {
                        request2.getRequestDispatcher("roles.xhtml").forward(request, response);
                    } else {
                        EntityRealm.getSubject().logout();
                        request2.getRequestDispatcher(PAGE_ERROR).forward(request, response);
                    }
                    break;

                case "securite/compte.xhtml":
                    if (EntityRealm.getSubject().hasRole("Consulter compte") || EntityRealm.getSubject().hasRole("Activer compte")) {
                        request2.getRequestDispatcher("compte.xhtml").forward(request, response);
                    } else {
                        EntityRealm.getSubject().logout();
                        request2.getRequestDispatcher(PAGE_ERROR).forward(request, response);
                    }
                    break;

                case "tableau/tableau.xhtml":
                    if (EntityRealm.getSubject().hasRole("Tableau de bord")) {
                        request2.getRequestDispatcher("utilisateurs.xhtml").forward(request, response);
                    } else {
                        EntityRealm.getSubject().logout();
                        request2.getRequestDispatcher(PAGE_ERROR).forward(request, response);
                    }
                    break;

                case "condamnation/saisie_condamnation.xhtml":
                    if (EntityRealm.getSubject().hasRole("Ajouter condamnation")) {
                        request2.getRequestDispatcher("saisie_condamnation.xhtml").forward(request, response);
                    } else {
                        EntityRealm.getSubject().logout();
                        request2.getRequestDispatcher(PAGE_ERROR).forward(request, response);
                    }
                    break;

                case "condamnation/liste_condamnation.xhtml":
                    if (EntityRealm.getSubject().hasRole("Consulter condamnation") || EntityRealm.getSubject().hasRole("Modifier condamnation")) {
                        request2.getRequestDispatcher("liste_condamnation.xhtml").forward(request, response);
                    } else {
                        EntityRealm.getSubject().logout();
                        request2.getRequestDispatcher(PAGE_ERROR).forward(request, response);
                    }
                    break;

                case "administration/courtAppel.xhtml":
                    if (EntityRealm.getSubject().hasRole("Consulter Court d'Appel") || EntityRealm.getSubject().hasRole("Ajouter Court d'Appel")
                            || EntityRealm.getSubject().hasRole("Modifier Court d'Appel")) {
                        request2.getRequestDispatcher("courtAppel.xhtml").forward(request, response);
                    } else {
                        EntityRealm.getSubject().logout();
                        request2.getRequestDispatcher(PAGE_ERROR).forward(request, response);
                    }
                    break;
                case "administration/infractions.xhtml":
                    if (EntityRealm.getSubject().hasRole("Consulter infraction") || EntityRealm.getSubject().hasRole("Ajouter juridiction")
                            || EntityRealm.getSubject().hasRole("Modifier juridiction")) {
                        request2.getRequestDispatcher("infractions.xhtml").forward(request, response);
                    } else {
                        EntityRealm.getSubject().logout();
                        request2.getRequestDispatcher(PAGE_ERROR).forward(request, response);
                    }
                    break;
                case "administration/prisons.xhtml":
                    if (EntityRealm.getSubject().hasRole("Consulter prison") || EntityRealm.getSubject().hasRole("Ajouter prison")
                            || EntityRealm.getSubject().hasRole("Modifier prison")) {
                        request2.getRequestDispatcher("prisons.xhtml").forward(request, response);
                    } else {
                        EntityRealm.getSubject().logout();
                        request2.getRequestDispatcher(PAGE_ERROR).forward(request, response);
                    }
                    break;
                case "administration/tribunaux.xhtml":
                    if (EntityRealm.getSubject().hasRole("Consulter juridiction") || EntityRealm.getSubject().hasRole("Ajouter juridiction")
                            || EntityRealm.getSubject().hasRole("Modifier juridiction")) {
                        request2.getRequestDispatcher("tribunaux.xhtml").forward(request, response);
                    } else {
                        EntityRealm.getSubject().logout();
                        request2.getRequestDispatcher(PAGE_ERROR).forward(request, response);
                    }
                    break;
                default:
                    chain.doFilter(request, response);
            }

        } catch (Exception e) {
            //erreur dans le filtre
            System.out.println("Erreur dans le filtre FiltreJournalisation");
        }
    }

    public void destroy() {
        System.out.println("Destruction du filtre : " + this.filterConfig.getFilterName());
    }

}
