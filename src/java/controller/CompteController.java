/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Compte;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import service.CompteFacade;

/**
 *
 * @author HP
 */
@Named(value = "compteController")
@SessionScoped
public class CompteController implements Serializable {

    private Compte selected;
    private List<Compte> items;
    @EJB
    private CompteFacade ejbFacade;
    @EJB
    private Double montant;

    public String create() {
        ejbFacade.create(selected);
        selected = null;
        return "List";
    }

    public String debiter() {
        ejbFacade.debiter(selected.getId(), montant);
        selected = null;
        return "List";

    }

    public void remove() {

    }

    public void detail() {

    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Compte getSelected() {
        if (selected == null) {
            selected = new Compte();
        }
        return selected;
    }

    public void setSelected(Compte selected) {
        this.selected = selected;
    }

    public List<Compte> getItems() {
        ejbFacade.findAll();
        return items;
    }

    public void setItems(List<Compte> items) {
        this.items = items;
    }

    public CompteFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(CompteFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    /**
     * Creates a new instance of CompteController
     */
    public CompteController() {
    }

}
