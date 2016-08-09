package com.voigt.hwd.server.util.criteria;

/**
 * 
 * @author Roberto Mozzicato
 * @author Infracom IT s.p.a.
 * 
 * @param <T>
 */
public interface ICriterion<T> {
    public boolean pass(T oggetto);
}
