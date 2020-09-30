/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udem.edu.co.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import udem.edu.co.entities.Vendedor;

/**
 *
 * @author MEDRANO
 */
@Stateless
public class VendedorFacade extends AbstractFacade<Vendedor> {
    
    
    @PersistenceContext(unitName = "tiendaWebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VendedorFacade() {
        super(Vendedor.class);
    }
    
    public Vendedor loginVendedor(Vendedor vendedor){
        Vendedor VendedorResponse = null;
        String queryVendedor;
       
        try {
            queryVendedor = "FROM Vendedor v WHERE v.user = ?1 and v.pass = ?2";//consulta
            Query query = em.createQuery(queryVendedor);
            query.setParameter(1, vendedor.getUser());
            query.setParameter(2, vendedor.getPass());
            List <Vendedor> lista = query.getResultList();
            
            if (lista.isEmpty()) {
                return null;
            } else {
                System.out.println("Encontrado");
                return lista.get(0);
            }

        } catch (Exception e) {
            System.out.println("error" + e.getMessage());

        }
        return VendedorResponse;
    
    }
    
}
