/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avantica.serviciosparte2.crud;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author branlap
 * @param <T>
 */
public abstract class CrudJpa<T> {
    
    private static final String PERSISTENCE_UNIT_JPA = "puDataRepo";
    private final EntityManagerFactory emf;
    protected final EntityManager em;
    private final Class<T> objeto;
    
    public CrudJpa(Class<T> objeto) {
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_JPA);
        em = emf.createEntityManager();
        this.objeto = objeto;
    }
    
    public void crear(T t) {
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
    }
    
    public void actualizar(T t) {
        em.getTransaction().begin();
        em.merge(t);
        em.getTransaction().commit();
    }
    
    public void eliminar(Long id) {
        T t = buscar(id);
        em.getTransaction().begin();
        em.remove(t);
        em.getTransaction().commit();
    }
    
    public List<T> listar() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(objeto);
        Root<T> s = cq.from(objeto);
        cq.select(s);
        TypedQuery<T> q = em.createQuery(cq);
        List<T> lista = q.getResultList();
        return lista;
    }
    
    public T buscar(Long id) {
        T t = em.find(objeto, id);
        return t;
    }
    
}
