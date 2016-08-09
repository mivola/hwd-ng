package com.voigt.hwd.server.dao;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.voigt.hwd.domain.IDomainObject;
import com.voigt.hwd.domain.exception.BaseException;

/**
 * This is an generic DAO Interface to serve as a base for all specific DAOs. It
 * offers the standard CRUD operations and type safety due to the use of
 * generics.
 * 
 * @author mvoigt
 * 
 * @param <T>
 *                Type/Class
 * @param <PK>
 *                Primary Key
 */
public interface IGenericDao<T, PK extends Serializable> {
    static final String ___version___ = "@(#) $Name:  $";

    /** flushes the current session */
    public void flushSession();

    /** Persist the new object into database */
    public PK save(T newObject);

    /** save a new object or update an existing one */
    public void saveOrUpdate(T object);

    /**
     * Retrieve an object that was previously persisted to the database using
     * the indicated id as primary key
     */
    public T getById(PK id);

    /** retrieves a list of all objects in the database */
    public List<T> getAll();

    /**
     * retrieves a list of all objects in the database
     * 
     * @param comparator
     *                a comperator that sorts the elements in the list
     * @return a sorted list
     */
    public List<T> getAll(Comparator<T> comparator);

    /** Save changes made to a persistent object. */
    public void update(T object);

    /** Remove an object from persistent storage in the database */
    public void delete(T object);

    /**
     * Remove an object identified by its PK from persistent storage in the
     * database
     */
    public void deleteById(PK id);

    /**
     * find the correct elements according to the given class; useful for
     * elements in an inheritance structure where you want to find only one
     * specific element
     * 
     * @param <E>
     *                generic, used to provide type safety; must be a subclass
     *                of T
     * @param clazz
     *                the clazz of the wanted elements
     * @param orderProperty
     *                the property of the depository object, after which the
     *                objects should be sorted by; ignored if "" or null
     * @return an ascending sorted list of all elements of the given type
     */
    public <E extends T> List<E> getAllByConcreteClass(E clazz, String orderProperty);

    /**
     * find the correct elements according to the given class; useful for
     * elements in an inheritance structure where you want to find only one
     * specific element
     * 
     * @param <E>
     *                generic, used to provide type safety; must be a subclass
     *                of T
     * @param clazz
     *                the clazz of the wanted elements
     * @param comparator
     *                used to compare the elements
     * @return an ascending sorted list of all elements of the given type
     */
    public <E extends T> List<E> getAllByConcreteClass(E clazz, Comparator<E> comparator);

    /** get all objects matching the given example */
    public List<T> findByExample(T exampleInstance);

    /**
     * tries to mark an object with the given id as deleted by setting the
     * boolean property "deleted" to true uses reflections to determine if the
     * object has such a property, if not an exception is thrown
     * 
     * @param id
     *                the id of the object to be marked as deleted
     * @throws ItcException
     *                 if no boolean property "deleted" can be found
     */
    public void markAsDeleted(T object) throws BaseException;

    /**
     * get a list of objects
     * 
     * @param sortColumns
     *                colums and direction for sorting
     * @param seachColumns
     *                colums and searchtext
     * @param seachObjectColumns
     *                column names and pattern objects from the business domain
     * @return list of objects
     */
    public List<T> getList(Map<String, Boolean> sortColumns, Map<String, String> seachColumns,
	    Map<String, ? extends IDomainObject> seachObjectColumns);

    /**
     * Truncates the table. Uses plain sql 'truncate' for performance issues.
     * The 'truncate' command syntax is the same for all major databases, so we
     * can use it with a modest risk, if type of database is changed.
     * 
     * @param tableName
     * 
     * @throws ItcException
     */
    public void truncate(String tableName) throws BaseException;
}
