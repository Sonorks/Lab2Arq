/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.ejb;

import com.udea.modelo.Estudiante;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author CASA555
 */
@Stateless
public class EstudianteFacade extends AbstractFacade <Estudiante> implements EstudianteFacadeLocal  {
    @PersistenceContext(unitName = "com.udea_Lab2UdeA_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstudianteFacade() {
        super(Estudiante.class);
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
