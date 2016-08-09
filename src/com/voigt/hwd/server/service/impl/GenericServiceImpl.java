/***************************************************************************************************
 * � 2006 itCampus GmbH. Alle Rechte vorbehalten.
 * 
 * Dieses Dokument enth�lt unver�ffentlichte, vertrauliche und gesch�tzte Informationen der itCampus
 * GmbH, die durch das Urheberrechtsgesetz und andere Gesetze des geistigen Eigentums gesch�tzt
 * werden.
 * 
 * Alle referenzierten Warenzeichen, Marken- und Handelsnamen sind Eigentum ihrer jeweiligen
 * Inhaber.
 * 
 * itCampus Software und die dazugeh�rigen Unterlagen werden abh�ngig von und nur gem�� den
 * Bedingungen einer Lizenzvereinbarung mit der itCampus GmbH zur Verf�gung gestellt und k�nnen nur
 * in �bereinstimmung mit den Bedingungen dieser Vereinbarung verwendet oder kopiert werden.
 * 
 * ALLE RECHTE, AUCH DIE DER �BERSETZUNG, DES NACHDRUCKS UND DER VERVIELF�LTIGUNG DIESES DOKUMENTES
 * ODER VON TEILEN DARAUS, BLEIBEN VORBEHALTEN. KEIN TEIL DIESES DOKUMENTES DARF OHNE SCHRIFTLICHE
 * GENEHMIGUNG DER ITCAMPUS GMBH IN IRGENDEINER FORM REPRODUZIERT ODER UNTER VERWENDUNG
 * ELEKTRONISCHER SYSTEME VERARBEITET, VERVIELF�LTIGT ODER VERBREITET WERDEN.
 * 
 * GEH�REN DIESE UNTERLAGEN ZU VON ITCAMPUS LIZENZIERTER SOFTWARE, DANN UNTERLIEGEN DIE
 * INFORMATIONEN HIERIN DEN BEDINGUNGEN DER GARANTIE IN ABH�NGIG DER LIZENZ- VEREINBARUNGEN. GEH�REN
 * DIESE UNTERLAGEN ZU SOFTWARE, DIE NICHT TEIL VON LIZENZVEREINBARUNGEN IST, DANN WERDEN DIE
 * INFORMATIONEN HIERIN UNVER�NDERT UND UNTER AUSSCHLUSS IRGENDWELCHER GARANTIE ZUR VERF�GUNG
 * GESTELLT. JEGLICHE HAFTUNG F�R DIREKTE WIE INDIREKTE SCH�DEN, DIE AUS DEM GEBRAUCH ODER DER
 * NICHTGEBRAUCHSF�HIKEIT DER HIERIN ENTHALTEN INFORMATIONEN ENSTANDEN SIND, WIRD HIERMIT
 * AUSGESCHLOSSEN, SOWEIT DIES GESETZLICH ZUL�SSIG IST.
 * 
 * 
 * Ge�ndert: $Date: 2008/11/24 08:22:46 $ 
 * Revision: $Revision: 1.7 $
 * 
 **************************************************************************************************/
package com.voigt.hwd.server.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.voigt.hwd.domain.IDomainObject;
import com.voigt.hwd.domain.exception.BaseException;
import com.voigt.hwd.server.dao.IGenericDao;
import com.voigt.hwd.server.service.IGenericService;

/**
 * standard implementation of the most common service functions
 */
public class GenericServiceImpl<T, PK extends Serializable> implements IGenericService<T, PK> {

    /** the standard version String */
    public static final String ___version___ = "@(#) $Name:  $";

    protected final Log log = LogFactory.getLog(this.getClass());

    protected IGenericDao<T, Serializable> dao;

    public void flushSession() {
	dao.flushSession();
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.itcampus.rpl.key.model.service.IGenericService#delete(java.lang.Object)
     */
    public void delete(T object) throws BaseException {
	try {
	    log.debug("deleting object: " + object);
	    dao.delete(object);
	} catch (Exception e) {
	    String msg = "Could not delete object " + object + ": " + e.toString();
	    log.error(msg, e);
	    throw new BaseException(msg, e);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.itcampus.bersy.service.IGenericService#saveList(java.util.List)
     */
    @SuppressWarnings("unchecked")
    public void deleteList(List<T> objectList) throws BaseException {
	for (T object : objectList) {
	    this.delete(object);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.itcampus.rpl.key.model.service.IGenericService#deleteById(java.io.Serializable)
     */
    public void deleteById(PK id) throws BaseException {
	try {
	    log.debug("deleting object of type " + this.dao.getClass() + " with id: " + id);
	    dao.deleteById(id);
	} catch (Exception e) {
	    String msg = "Could not delete object with id " + id + ": " + e.toString();
	    log.error(msg, e);
	    throw new BaseException(msg, e);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.itcampus.rpl.key.model.service.IGenericService#getAll()
     */
    public List<T> getAll() throws BaseException {
	try {
	    return dao.getAll();
	} catch (Exception e) {
	    String msg = "Could not get object " + e.toString();
	    log.error(msg, e);
	    throw new BaseException(msg, e);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.itcampus.rpl.key.model.service.IGenericService#getAll(java.util.Comparator<T>
     *      comparator)
     */
    public List<T> getAll(Comparator<T> comparator) throws BaseException {
	try {
	    return dao.getAll(comparator);
	} catch (Exception e) {
	    String msg = "Could not get object " + e.toString();
	    log.error(msg, e);
	    throw new BaseException(msg, e);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.itcampus.rpl.key.model.service.IGenericService#getById(java.io.Serializable)
     */
    public T getById(PK id) throws BaseException {
	try {
	    return dao.getById(id);
	} catch (Exception e) {
	    String msg = "Could not get object with id " + id + ": " + e.toString();
	    log.error(msg, e);
	    throw new BaseException(msg, e);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.itcampus.rpl.key.model.service.IGenericService#save(java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    public PK save(T newObject) throws BaseException {
	try {
	    PK pk = (PK) dao.save(newObject);
	    log.debug("saved object: " + newObject);
	    return pk;
	} catch (Exception e) {
	    String msg = "Could not save object " + newObject.toString() + ": " + e.toString();
	    log.error(msg, e);
	    throw new BaseException(msg, e);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.itcampus.bersy.service.IGenericService#saveList(java.util.List)
     */
    @SuppressWarnings("unchecked")
    public List<PK> saveList(List<T> objectList) throws BaseException {
	List<PK> listPK = new ArrayList<PK>();
	for (T object : objectList) {
	    PK pk = this.save(object);
	    listPK.add(pk);
	}
	return listPK;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.itcampus.rpl.key.model.service.IGenericService#saveOrUpdate(java.lang.Object)
     */
    public void saveOrUpdate(T object) throws BaseException {
	try {
	    dao.saveOrUpdate(object);
	    log.debug("saved or updated object: " + object);
	} catch (Exception e) {
	    String msg = "Could not save or update object " + object + ": " + e.toString();
	    log.error(msg, e);
	    throw new BaseException(msg, e);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.itcampus.rpl.key.model.service.IGenericService#update(java.lang.Object)
     */
    public void update(T object) throws BaseException {
	try {
	    dao.update(object);
	    log.debug("updated object: " + object);
	} catch (Exception e) {
	    String msg = "Could not update object " + object + ": " + e.toString();
	    log.error(msg, e);
	    throw new BaseException(msg, e);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.itcampus.bersy.service.IGenericService#saveList(java.util.List)
     */
    @SuppressWarnings("unchecked")
    public void updateList(List<T> objectList) throws BaseException {
	for (T object : objectList) {
	    this.update(object);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.itcampus.rpl.key.model.service.IGenericService#getAllByConcreteClass(java.lang.Object,
     *      java.lang.String)
     */
    public <E extends T> List<E> getAllByConcreteClass(E clazz, String orderProperty) {
	return dao.getAllByConcreteClass(clazz, orderProperty);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.itcampus.rpl.key.model.service.IGenericService#getAllByConcreteClass(java.lang.Object,
     *      java.util.Comparator)
     */
    public <E extends T> List<E> getAllByConcreteClass(E clazz, Comparator<E> comparator) throws BaseException {
	return dao.getAllByConcreteClass(clazz, comparator);
    }

    public void markAsDeleted(PK id) throws BaseException {
	T object = this.getById(id);
	dao.markAsDeleted(object);
	log.debug("marked object as deleted: " + object);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.itcampus.rpl.key.model.service.IGenericService#markAsDeleted(java.lang.Object)
     */
    public void markAsDeleted(T object) throws BaseException {
	dao.markAsDeleted(object);
	log.debug("marked object as deleted: " + object);
    }

    public IGenericDao<T, Serializable> getDao() {
	return dao;
    }

    public void setDao(IGenericDao<T, Serializable> dao) {
	this.dao = dao;
    }

    public List<T> getList(Map<String, Boolean> sortColumns, Map<String, String> seachColumns,
	    Map<String, ? extends IDomainObject> seachObjectColumns) {
	return dao.getList(sortColumns, seachColumns, seachObjectColumns);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.itcampus.bersy.service.IGenericService#saveOrUpdateList(java.util.List)
     */
    @Override
    public void saveOrUpdateList(List<T> objectList) throws BaseException {
	for (T object : objectList) {
	    this.saveOrUpdate(object);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.itcampus.bersy.service.IGenericService#saveList(java.util.List,
     *      boolean)
     */
    @Override
    public List<PK> saveListAfterTruncate(List<T> newObjectList) throws BaseException {
	// get all existing elements
	List<T> allElements = getAll();
	// remove them all
	deleteList(allElements);
	// save the new list
	List<PK> keys = saveList(newObjectList);
	return keys;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.itcampus.bersy.service.IGenericService#truncate()
     */
    @Override
    public void truncate(String tableName) throws BaseException {
	dao.truncate(tableName);
    }
}
