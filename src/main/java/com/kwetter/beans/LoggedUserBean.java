package com.kwetter.beans;

/**
 * Created by Niek on 23-3-2017.
 */

import com.kwetter.model.User;
import com.kwetter.service.KweetService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

/**
 * Created by Niek on 23-3-2017.
 */
@RequestScoped
@Named
public class LoggedUserBean implements Serializable {

    @Inject
    HttpServletRequest request;
    @Inject
    HttpSession session;

    @EJB
    KweetService kwetterService;

    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

    public User getUser() {
        return kwetterService.findByUserName(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
    }

    public boolean getLoggedIn() {
        return FacesContext.getCurrentInstance().getExternalContext().getRemoteUser() != null;
    }
    public void signOut() {
        try {
            request.logout();
            session.invalidate();
            System.out.println("Signout invoked");
            FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation
                    (FacesContext.getCurrentInstance(), null, "/index.xhtml");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error Signout -" + ex.getMessage());
        }
    }
}

