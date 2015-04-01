/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import facades.MenuItemRatingFacade;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Can Jee
 */
@ManagedBean
@SessionScoped
public class ViewItemRatingsBean extends BaseBean{
    
    @ManagedProperty(value="#{menuItemRatingFacade}")
    MenuItemRatingFacade menuItemRatingFacade;
    
    private String orderBy = "name";
    private boolean ascending = true;
    
}
