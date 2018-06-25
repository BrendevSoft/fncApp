/*
 * Copyright 2009-2014 PrimeTek.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fncapp.fncapp.web.template.filter;

import com.fncapp.fncapp.api.api.utils.MethodeJournalisation;
import com.fncapp.fncapp.impl.shiro.EntityRealm;
import com.fncapp.fncapp.web.web.LoginBean;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Level;

public class CharacterEncodingFilter implements Filter {

    private MethodeJournalisation journalisation;
    private static final String PAGE_ERROR = "../error.xhtml";

    private FilterConfig filterConfig = null;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        System.out.println("Initialisation du filtre :" + this.filterConfig.getFilterName());
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        HttpServletRequest req2 = (HttpServletRequest) req;
        String page = req2.getRequestURI().substring(req2.getContextPath().length() + 1);
        try {
            switch (page) {
                case "login.xhtml":

                    req2.getRequestDispatcher("login.xhtml").forward(req, resp);
                    break;

                case "firstConnect.xhtml":
                    req2.getRequestDispatcher("firstConnect.xhtml").forward(req, resp);
                    break;

                case "loginVerif.xhtml":
                    req2.getRequestDispatcher("loginVerif.xhtml").forward(req, resp);
                    break;

                case "reinitPass.xhtml":
                    req2.getRequestDispatcher("reinitPass.xhtml").forward(req, resp);
                    break;

                case "error.xhtml":
                    req2.getRequestDispatcher("reinitPass.xhtml").forward(req, resp);
                    break;

                case "acceuil.xhtml":
                    if (EntityRealm.getUser() != null) {
                        journalisation = new MethodeJournalisation();
                        journalisation.saveLog4j(LoginBean.class.getSimpleName(), Level.INFO, "Déconnexion");

                        EntityRealm.getSubject().logout();
                    }
                    req2.getRequestDispatcher("acceuil.xhtml").forward(req, resp);
                    break;

                case "securite/utilisateurs.xhtml":
                    if (EntityRealm.getSubject().hasRole("Consulter utilisateur") || EntityRealm.getSubject().hasRole("Ajouter utilisateur")
                            || EntityRealm.getSubject().hasRole("Modifier utilisateur")) {
                        req2.getRequestDispatcher("utilisateurs.xhtml").forward(req, resp);
                    } else {
                        EntityRealm.getSubject().logout();
                        req2.getRequestDispatcher(PAGE_ERROR).forward(req, resp);
                    }
                    break;

                case "securite/profil.xhtml":
                    if (EntityRealm.getSubject().hasRole("Consulter profil") || EntityRealm.getSubject().hasRole("Ajouter profil")
                            || EntityRealm.getSubject().hasRole("Modifier profil")) {
                        req2.getRequestDispatcher("profil.xhtml").forward(req, resp);
                    } else {
                        EntityRealm.getSubject().logout();
                        req2.getRequestDispatcher(PAGE_ERROR).forward(req, resp);
                    }
                    break;

                case "securite/associerProfil.xhtml":
                    if (EntityRealm.getSubject().hasRole("Consulter associer profil") || EntityRealm.getSubject().hasRole("Associer profil")) {
                        req2.getRequestDispatcher("associerProfil.xhtml").forward(req, resp);
                    } else {
                        EntityRealm.getSubject().logout();
                        req2.getRequestDispatcher(PAGE_ERROR).forward(req, resp);
                    }
                    break;
                case "securite/modifierProfil.xhtml":
                    if (EntityRealm.getSubject().hasRole("Consulter associer profil") || EntityRealm.getSubject().hasRole("Associer profil")) {
                        req2.getRequestDispatcher("modifierProfil.xhtml").forward(req, resp);
                    } else {
                        EntityRealm.getSubject().logout();
                        req2.getRequestDispatcher(PAGE_ERROR).forward(req, resp);
                    }
                    break;

                case "securite/roles.xhtml":
                    if (EntityRealm.getSubject().hasRole("Consulter associer role") || EntityRealm.getSubject().hasRole("Associer role")) {
                        req2.getRequestDispatcher("roles.xhtml").forward(req, resp);
                    } else {
                        EntityRealm.getSubject().logout();
                        req2.getRequestDispatcher(PAGE_ERROR).forward(req, resp);
                    }
                    break;

                case "securite/compte.xhtml":
                    if (EntityRealm.getSubject().hasRole("Consulter compte") || EntityRealm.getSubject().hasRole("Activer compte")) {
                        req2.getRequestDispatcher("compte.xhtml").forward(req, resp);
                    } else {
                        EntityRealm.getSubject().logout();
                        req2.getRequestDispatcher(PAGE_ERROR).forward(req, resp);
                    }
                    break;

                case "tableau/tableau.xhtml":
                    if (EntityRealm.getSubject().hasRole("Tableau de bord")) {
                        req2.getRequestDispatcher("tableau.xhtml").forward(req, resp);
                    } else {
                        EntityRealm.getSubject().logout();
                        req2.getRequestDispatcher(PAGE_ERROR).forward(req, resp);
                    }
                    break;

                case "condamnation/saisie_condamnation.xhtml":
                    if (EntityRealm.getSubject().hasRole("Ajouter condamnation")) {
                        req2.getRequestDispatcher("saisie_condamnation.xhtml").forward(req, resp);
                    } else {
                        EntityRealm.getSubject().logout();
                        req2.getRequestDispatcher(PAGE_ERROR).forward(req, resp);
                    }
                    break;

                case "condamnation/liste_condamnation.xhtml":
                    if (EntityRealm.getSubject().hasRole("Consulter condamnation") || EntityRealm.getSubject().hasRole("Modifier condamnation")) {
                        req2.getRequestDispatcher("liste_condamnation.xhtml").forward(req, resp);
                    } else {
                        EntityRealm.getSubject().logout();
                        req2.getRequestDispatcher(PAGE_ERROR).forward(req, resp);
                    }
                    break;

                case "condamnation/statistique.xhtml":
                    if (EntityRealm.getSubject().hasRole("Tableau de bord")) {
                        req2.getRequestDispatcher("statistique.xhtml").forward(req, resp);
                    } else {
                        EntityRealm.getSubject().logout();
                        req2.getRequestDispatcher(PAGE_ERROR).forward(req, resp);
                    }
                    break;

                case "condamnation/recherche.xhtml":
                    if (EntityRealm.getSubject().hasRole("Recherche")) {
                        req2.getRequestDispatcher("recherche.xhtml").forward(req, resp);
                    } else {
                        EntityRealm.getSubject().logout();
                        req2.getRequestDispatcher(PAGE_ERROR).forward(req, resp);
                    }
                    break;

                case "administration/courtAppel.xhtml":
                    if (EntityRealm.getSubject().hasRole("Consulter Court d'Appel") || EntityRealm.getSubject().hasRole("Ajouter Court d'Appel")
                            || EntityRealm.getSubject().hasRole("Modifier Court d'Appel")) {
                        req2.getRequestDispatcher("courtAppel.xhtml").forward(req, resp);
                    } else {
                        EntityRealm.getSubject().logout();
                        req2.getRequestDispatcher(PAGE_ERROR).forward(req, resp);
                    }
                    break;
                case "administration/infractions.xhtml":
                    if (EntityRealm.getSubject().hasRole("Consulter infraction") || EntityRealm.getSubject().hasRole("Ajouter juridiction")
                            || EntityRealm.getSubject().hasRole("Modifier juridiction")) {
                        req2.getRequestDispatcher("infractions.xhtml").forward(req, resp);
                    } else {
                        EntityRealm.getSubject().logout();
                        req2.getRequestDispatcher(PAGE_ERROR).forward(req, resp);
                    }
                    break;
                case "administration/prisons.xhtml":
                    if (EntityRealm.getSubject().hasRole("Consulter prison") || EntityRealm.getSubject().hasRole("Ajouter prison")
                            || EntityRealm.getSubject().hasRole("Modifier prison")) {
                        req2.getRequestDispatcher("prisons.xhtml").forward(req, resp);
                    } else {
                        EntityRealm.getSubject().logout();
                        req2.getRequestDispatcher(PAGE_ERROR).forward(req, resp);
                    }
                    break;
                case "administration/tribunaux.xhtml":
                    if (EntityRealm.getSubject().hasRole("Consulter juridiction") || EntityRealm.getSubject().hasRole("Ajouter juridiction")
                            || EntityRealm.getSubject().hasRole("Modifier juridiction")) {
                        req2.getRequestDispatcher("tribunaux.xhtml").forward(req, resp);
                    } else {
                        EntityRealm.getSubject().logout();
                        req2.getRequestDispatcher(PAGE_ERROR).forward(req, resp);
                    }
                    break;

                default:
                    chain.doFilter(req, resp);
            }
        } catch (IOException e) {
            //erreur dans le filtre
            // e.getMessage();
            e.getStackTrace();
            System.out.println("Erreur dans le filtre FiltreJournalisation");
        }

    }

    public void destroy() {
        System.out.println("Destruction du filtre : " + this.filterConfig.getFilterName());
    }

}
