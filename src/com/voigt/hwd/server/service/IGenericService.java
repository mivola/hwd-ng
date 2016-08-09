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
 * Ge�ndert: $Date: 2008/11/25 18:03:15 $ 
 * Revision: $Revision: 1.7 $
 * 
 **************************************************************************************************/
package com.voigt.hwd.server.service;

import java.io.Serializable;
import java.security.KeyException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.voigt.hwd.domain.IDomainObject;
import com.voigt.hwd.domain.exception.BaseException;

/**
 * This is an generic Service Interface to serve as a base for all specific
 * business services. It offers the standard operations such as save, delete etc
 * and type safety due to the use of generics. Extend this interface with a
 * business specific interface and implementation if needed
 * 
 * @author mvoigt
 * 
 * @param <T>
 *            Type/Class
 * @param <PK>
 *            Primary Key
 */
public interface IGenericService<T, PK extends Serializable> {

	static final String ___version___ = "@(#) $Name:  $";

	/**
	 * flushes the current session
	 */
	public void flushSession();

	/**
	 * Persist the new object
	 * 
	 * @throws KeyException
	 */
	public PK save(T newObject) throws BaseException;

	/**
	 * Persist a list of new objects
	 * 
	 * @throws KeyException
	 */
	public List<PK> saveList(List<T> newObjectList) throws BaseException;

	/**
	 * Persist a list of new objects, after all entriey in this database table
	 * are deleted (all elements will be removed)!!!
	 * 
	 * @throws KeyException
	 */
	public List<PK> saveListAfterTruncate(List<T> newObjectList) throws BaseException;

	/**
	 * save a new object or update an existing one
	 * 
	 * @throws KeyException
	 */
	public void saveOrUpdate(T object) throws BaseException;

	/**
	 * save a list of new objects or update existing ones
	 * 
	 * @throws KeyException
	 */
	public void saveOrUpdateList(List<T> object) throws BaseException;

	/**
	 * Retrieve an object using the indicated id as primary key
	 * 
	 * @throws BaseException
	 */
	public T getById(PK id) throws BaseException;

	/**
	 * retrieves a list of all objects
	 * 
	 * @throws BaseException
	 */
	public List<T> getAll() throws BaseException;

	/**
	 * retrieves a list of all objects
	 * 
	 * @param comparator
	 *            a comperator that sorts the elements in the list
	 * @throws BaseException
	 */
	public List<T> getAll(Comparator<T> comparator) throws BaseException;

	/**
	 * Save changes made to a persistent object.
	 * 
	 * @throws BaseException
	 */
	public void update(T object) throws BaseException;

	/**
	 * Save changes made to a set of persistent objects.
	 * 
	 * @throws KeyException
	 */
	public void updateList(List<T> newObjectList) throws BaseException;

	/**
	 * Remove an object
	 * 
	 * @throws BaseException
	 */
	public void delete(T object) throws BaseException;

	/**
	 * Remove a set of objects.
	 * 
	 * @throws KeyException
	 */
	public void deleteList(List<T> newObjectList) throws BaseException;

	/**
	 * Remove an object identified by its PK
	 * 
	 * @throws BaseException
	 */
	public void deleteById(PK id) throws BaseException;

	/**
	 * find the correct elements according to the given class; useful for
	 * elements in an inheritance structure where you want to find only one
	 * specific element
	 * 
	 * @param <E>
	 *            generic, used to provide type safety; must be a subclass of T
	 * @param clazz
	 *            the clazz of the wanted elements
	 * @param comparator
	 *            used to sort the elements
	 * @return an ascending sorted list of all elements of the given type
	 */
	public <E extends T> List<E> getAllByConcreteClass(E clazz, Comparator<E> comparator) throws BaseException;

	/**
	 * find the correct elements according to the given class; useful for
	 * elements in an inheritance structure where you want to find only one
	 * specific element
	 * 
	 * @param <E>
	 *            generic, used to provide type safety; must be a subclass of T
	 * @param clazz
	 *            the clazz of the wanted elements
	 * @param orderProperty
	 *            the property of the depository object, after which the objects
	 *            should be sorted by; ignored if "" or null
	 * @return an ascending sorted list of all elements of the given type
	 */
	public <E extends T> List<E> getAllByConcreteClass(E clazz, String orderProperty) throws BaseException;

	/**
	 * tries to mark an object with the given id as deleted by setting the
	 * boolean property "deleted" to true in the dao layer
	 * 
	 * @param id
	 *            the id of the object to be marked as deleted
	 * @throws BaseException
	 *             if no boolean property "deleted" can be found
	 */
	public void markAsDeleted(PK id) throws BaseException;

	/**
	 * see markAsDeleted(PK id)
	 * 
	 * @param object
	 *            the object to be marked as deleted
	 * @throws BaseException
	 *             if no boolean property "deleted" can be found
	 */
	public void markAsDeleted(T object) throws BaseException;

	/**
	 * get a list of objects
	 * 
	 * @param sortColumns
	 *            columns and direction for sorting
	 * @param seachColumns
	 *            columns and searchtext
	 * @param seachObjectColumns
	 *            columns and referenced domain objects
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
	 *            The table name is given, because in GenericDao we don't have
	 *            access to the NamingStrategy.
	 * 
	 * @throws BaseException
	 */
	public void truncate(String tableName) throws BaseException;

}
