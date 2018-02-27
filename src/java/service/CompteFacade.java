/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Compte;
import bean.Operation;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author HP
 */
@Stateless
public class CompteFacade extends AbstractFacade<Compte> {

    @PersistenceContext(unitName = "test1PU")
    private EntityManager em;
    @EJB
    private OperationFacade operationFacade;

    public void remove(String id) {
        operationFacade.removeByCompte(id);
        super.remove(new Compte(id));

    }

    public int debiter(String id, Double montant) {
        Compte compte = find(id);
        if (compte.getSolde() < montant) {
            return -1;
        } else {
            compte.setSolde(compte.getSolde() - montant);
            edit(compte);
            Operation operation = new Operation();
            operation.setMontant(montant);
            operation.setType(2);
            operation.setCompte(compte);
            operationFacade.create(operation);
            return 1;
        }
    }

    public int crediter(String id, Double montant) {
        Compte compte = find(id);
        if (montant == 0) {
            return -1;
        } else {
            compte.setSolde(compte.getSolde() + montant);
            edit(compte);
            return 1;
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompteFacade() {
        super(Compte.class);
    }

}
