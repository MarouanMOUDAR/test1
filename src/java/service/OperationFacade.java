/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Operation;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author HP
 */
@Stateless
public class OperationFacade extends AbstractFacade<Operation> {

    @PersistenceContext(unitName = "test1PU")
    private EntityManager em;

    public List<Operation> findByCompte(String id) {
        return em.createQuery("SELECT op FROM Operation op WHERE op.compte.id'" + id + "'").getResultList();
    }

    public int removeByCompte(String id) {
        return em.createQuery("DELETE FROM Operation op WHERE op.compte.id'" + id + "'").executeUpdate();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OperationFacade() {
        super(Operation.class);
    }

}
