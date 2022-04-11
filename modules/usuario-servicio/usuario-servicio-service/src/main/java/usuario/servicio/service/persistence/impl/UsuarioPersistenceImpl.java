/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package usuario.servicio.service.persistence.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.ArgumentsResolver;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

import usuario.servicio.exception.NoSuchUsuarioException;
import usuario.servicio.model.Usuario;
import usuario.servicio.model.UsuarioTable;
import usuario.servicio.model.impl.UsuarioImpl;
import usuario.servicio.model.impl.UsuarioModelImpl;
import usuario.servicio.service.persistence.UsuarioPersistence;
import usuario.servicio.service.persistence.impl.constants.zemsaniaPersistenceConstants;

/**
 * The persistence implementation for the usuario service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {UsuarioPersistence.class, BasePersistence.class})
public class UsuarioPersistenceImpl
	extends BasePersistenceImpl<Usuario> implements UsuarioPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>UsuarioUtil</code> to access the usuario persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		UsuarioImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByUuid;
	private FinderPath _finderPathWithoutPaginationFindByUuid;
	private FinderPath _finderPathCountByUuid;

	/**
	 * Returns all the usuarios where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching usuarios
	 */
	@Override
	public List<Usuario> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the usuarios where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UsuarioModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of usuarios
	 * @param end the upper bound of the range of usuarios (not inclusive)
	 * @return the range of matching usuarios
	 */
	@Override
	public List<Usuario> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the usuarios where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UsuarioModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of usuarios
	 * @param end the upper bound of the range of usuarios (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching usuarios
	 */
	@Override
	public List<Usuario> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Usuario> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the usuarios where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UsuarioModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of usuarios
	 * @param end the upper bound of the range of usuarios (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching usuarios
	 */
	@Override
	public List<Usuario> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Usuario> orderByComparator, boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid;
				finderArgs = new Object[] {uuid};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid;
			finderArgs = new Object[] {uuid, start, end, orderByComparator};
		}

		List<Usuario> list = null;

		if (useFinderCache) {
			list = (List<Usuario>)finderCache.getResult(finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (Usuario usuario : list) {
					if (!uuid.equals(usuario.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_USUARIO_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(UsuarioModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				list = (List<Usuario>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first usuario in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching usuario
	 * @throws NoSuchUsuarioException if a matching usuario could not be found
	 */
	@Override
	public Usuario findByUuid_First(
			String uuid, OrderByComparator<Usuario> orderByComparator)
		throws NoSuchUsuarioException {

		Usuario usuario = fetchByUuid_First(uuid, orderByComparator);

		if (usuario != null) {
			return usuario;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchUsuarioException(sb.toString());
	}

	/**
	 * Returns the first usuario in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching usuario, or <code>null</code> if a matching usuario could not be found
	 */
	@Override
	public Usuario fetchByUuid_First(
		String uuid, OrderByComparator<Usuario> orderByComparator) {

		List<Usuario> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last usuario in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching usuario
	 * @throws NoSuchUsuarioException if a matching usuario could not be found
	 */
	@Override
	public Usuario findByUuid_Last(
			String uuid, OrderByComparator<Usuario> orderByComparator)
		throws NoSuchUsuarioException {

		Usuario usuario = fetchByUuid_Last(uuid, orderByComparator);

		if (usuario != null) {
			return usuario;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchUsuarioException(sb.toString());
	}

	/**
	 * Returns the last usuario in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching usuario, or <code>null</code> if a matching usuario could not be found
	 */
	@Override
	public Usuario fetchByUuid_Last(
		String uuid, OrderByComparator<Usuario> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Usuario> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the usuarios before and after the current usuario in the ordered set where uuid = &#63;.
	 *
	 * @param userId the primary key of the current usuario
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next usuario
	 * @throws NoSuchUsuarioException if a usuario with the primary key could not be found
	 */
	@Override
	public Usuario[] findByUuid_PrevAndNext(
			long userId, String uuid,
			OrderByComparator<Usuario> orderByComparator)
		throws NoSuchUsuarioException {

		uuid = Objects.toString(uuid, "");

		Usuario usuario = findByPrimaryKey(userId);

		Session session = null;

		try {
			session = openSession();

			Usuario[] array = new UsuarioImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, usuario, uuid, orderByComparator, true);

			array[1] = usuario;

			array[2] = getByUuid_PrevAndNext(
				session, usuario, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Usuario getByUuid_PrevAndNext(
		Session session, Usuario usuario, String uuid,
		OrderByComparator<Usuario> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_USUARIO_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(UsuarioModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(usuario)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Usuario> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the usuarios where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Usuario usuario :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(usuario);
		}
	}

	/**
	 * Returns the number of usuarios where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching usuarios
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_USUARIO_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_2 = "usuario.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(usuario.uuid IS NULL OR usuario.uuid = '')";

	public UsuarioPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		setDBColumnNames(dbColumnNames);

		setModelClass(Usuario.class);

		setModelImplClass(UsuarioImpl.class);
		setModelPKClass(long.class);

		setTable(UsuarioTable.INSTANCE);
	}

	/**
	 * Caches the usuario in the entity cache if it is enabled.
	 *
	 * @param usuario the usuario
	 */
	@Override
	public void cacheResult(Usuario usuario) {
		entityCache.putResult(
			UsuarioImpl.class, usuario.getPrimaryKey(), usuario);
	}

	/**
	 * Caches the usuarios in the entity cache if it is enabled.
	 *
	 * @param usuarios the usuarios
	 */
	@Override
	public void cacheResult(List<Usuario> usuarios) {
		for (Usuario usuario : usuarios) {
			if (entityCache.getResult(
					UsuarioImpl.class, usuario.getPrimaryKey()) == null) {

				cacheResult(usuario);
			}
		}
	}

	/**
	 * Clears the cache for all usuarios.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(UsuarioImpl.class);

		finderCache.clearCache(UsuarioImpl.class);
	}

	/**
	 * Clears the cache for the usuario.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Usuario usuario) {
		entityCache.removeResult(UsuarioImpl.class, usuario);
	}

	@Override
	public void clearCache(List<Usuario> usuarios) {
		for (Usuario usuario : usuarios) {
			entityCache.removeResult(UsuarioImpl.class, usuario);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(UsuarioImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(UsuarioImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new usuario with the primary key. Does not add the usuario to the database.
	 *
	 * @param userId the primary key for the new usuario
	 * @return the new usuario
	 */
	@Override
	public Usuario create(long userId) {
		Usuario usuario = new UsuarioImpl();

		usuario.setNew(true);
		usuario.setPrimaryKey(userId);

		String uuid = PortalUUIDUtil.generate();

		usuario.setUuid(uuid);

		return usuario;
	}

	/**
	 * Removes the usuario with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userId the primary key of the usuario
	 * @return the usuario that was removed
	 * @throws NoSuchUsuarioException if a usuario with the primary key could not be found
	 */
	@Override
	public Usuario remove(long userId) throws NoSuchUsuarioException {
		return remove((Serializable)userId);
	}

	/**
	 * Removes the usuario with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the usuario
	 * @return the usuario that was removed
	 * @throws NoSuchUsuarioException if a usuario with the primary key could not be found
	 */
	@Override
	public Usuario remove(Serializable primaryKey)
		throws NoSuchUsuarioException {

		Session session = null;

		try {
			session = openSession();

			Usuario usuario = (Usuario)session.get(
				UsuarioImpl.class, primaryKey);

			if (usuario == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchUsuarioException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(usuario);
		}
		catch (NoSuchUsuarioException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected Usuario removeImpl(Usuario usuario) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(usuario)) {
				usuario = (Usuario)session.get(
					UsuarioImpl.class, usuario.getPrimaryKeyObj());
			}

			if (usuario != null) {
				session.delete(usuario);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (usuario != null) {
			clearCache(usuario);
		}

		return usuario;
	}

	@Override
	public Usuario updateImpl(Usuario usuario) {
		boolean isNew = usuario.isNew();

		if (!(usuario instanceof UsuarioModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(usuario.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(usuario);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in usuario proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Usuario implementation " +
					usuario.getClass());
		}

		UsuarioModelImpl usuarioModelImpl = (UsuarioModelImpl)usuario;

		if (Validator.isNull(usuario.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			usuario.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(usuario);
			}
			else {
				usuario = (Usuario)session.merge(usuario);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(UsuarioImpl.class, usuarioModelImpl, false, true);

		if (isNew) {
			usuario.setNew(false);
		}

		usuario.resetOriginalValues();

		return usuario;
	}

	/**
	 * Returns the usuario with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the usuario
	 * @return the usuario
	 * @throws NoSuchUsuarioException if a usuario with the primary key could not be found
	 */
	@Override
	public Usuario findByPrimaryKey(Serializable primaryKey)
		throws NoSuchUsuarioException {

		Usuario usuario = fetchByPrimaryKey(primaryKey);

		if (usuario == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchUsuarioException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return usuario;
	}

	/**
	 * Returns the usuario with the primary key or throws a <code>NoSuchUsuarioException</code> if it could not be found.
	 *
	 * @param userId the primary key of the usuario
	 * @return the usuario
	 * @throws NoSuchUsuarioException if a usuario with the primary key could not be found
	 */
	@Override
	public Usuario findByPrimaryKey(long userId) throws NoSuchUsuarioException {
		return findByPrimaryKey((Serializable)userId);
	}

	/**
	 * Returns the usuario with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param userId the primary key of the usuario
	 * @return the usuario, or <code>null</code> if a usuario with the primary key could not be found
	 */
	@Override
	public Usuario fetchByPrimaryKey(long userId) {
		return fetchByPrimaryKey((Serializable)userId);
	}

	/**
	 * Returns all the usuarios.
	 *
	 * @return the usuarios
	 */
	@Override
	public List<Usuario> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the usuarios.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UsuarioModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of usuarios
	 * @param end the upper bound of the range of usuarios (not inclusive)
	 * @return the range of usuarios
	 */
	@Override
	public List<Usuario> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the usuarios.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UsuarioModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of usuarios
	 * @param end the upper bound of the range of usuarios (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of usuarios
	 */
	@Override
	public List<Usuario> findAll(
		int start, int end, OrderByComparator<Usuario> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the usuarios.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UsuarioModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of usuarios
	 * @param end the upper bound of the range of usuarios (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of usuarios
	 */
	@Override
	public List<Usuario> findAll(
		int start, int end, OrderByComparator<Usuario> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<Usuario> list = null;

		if (useFinderCache) {
			list = (List<Usuario>)finderCache.getResult(finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_USUARIO);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_USUARIO;

				sql = sql.concat(UsuarioModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Usuario>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the usuarios from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Usuario usuario : findAll()) {
			remove(usuario);
		}
	}

	/**
	 * Returns the number of usuarios.
	 *
	 * @return the number of usuarios
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_USUARIO);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "userId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_USUARIO;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return UsuarioModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the usuario persistence.
	 */
	@Activate
	public void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		_argumentsResolverServiceRegistration = _bundleContext.registerService(
			ArgumentsResolver.class, new UsuarioModelArgumentsResolver(),
			new HashMapDictionary<>());

		_finderPathWithPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"uuid_"}, true);

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			true);

		_finderPathCountByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			false);
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(UsuarioImpl.class.getName());

		_argumentsResolverServiceRegistration.unregister();
	}

	@Override
	@Reference(
		target = zemsaniaPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = zemsaniaPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = zemsaniaPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	private BundleContext _bundleContext;

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_USUARIO =
		"SELECT usuario FROM Usuario usuario";

	private static final String _SQL_SELECT_USUARIO_WHERE =
		"SELECT usuario FROM Usuario usuario WHERE ";

	private static final String _SQL_COUNT_USUARIO =
		"SELECT COUNT(usuario) FROM Usuario usuario";

	private static final String _SQL_COUNT_USUARIO_WHERE =
		"SELECT COUNT(usuario) FROM Usuario usuario WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "usuario.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Usuario exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Usuario exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		UsuarioPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	private ServiceRegistration<ArgumentsResolver>
		_argumentsResolverServiceRegistration;

	private static class UsuarioModelArgumentsResolver
		implements ArgumentsResolver {

		@Override
		public Object[] getArguments(
			FinderPath finderPath, BaseModel<?> baseModel, boolean checkColumn,
			boolean original) {

			String[] columnNames = finderPath.getColumnNames();

			if ((columnNames == null) || (columnNames.length == 0)) {
				if (baseModel.isNew()) {
					return FINDER_ARGS_EMPTY;
				}

				return null;
			}

			UsuarioModelImpl usuarioModelImpl = (UsuarioModelImpl)baseModel;

			long columnBitmask = usuarioModelImpl.getColumnBitmask();

			if (!checkColumn || (columnBitmask == 0)) {
				return _getValue(usuarioModelImpl, columnNames, original);
			}

			Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
				finderPath);

			if (finderPathColumnBitmask == null) {
				finderPathColumnBitmask = 0L;

				for (String columnName : columnNames) {
					finderPathColumnBitmask |=
						usuarioModelImpl.getColumnBitmask(columnName);
				}

				_finderPathColumnBitmasksCache.put(
					finderPath, finderPathColumnBitmask);
			}

			if ((columnBitmask & finderPathColumnBitmask) != 0) {
				return _getValue(usuarioModelImpl, columnNames, original);
			}

			return null;
		}

		@Override
		public String getClassName() {
			return UsuarioImpl.class.getName();
		}

		@Override
		public String getTableName() {
			return UsuarioTable.INSTANCE.getTableName();
		}

		private static Object[] _getValue(
			UsuarioModelImpl usuarioModelImpl, String[] columnNames,
			boolean original) {

			Object[] arguments = new Object[columnNames.length];

			for (int i = 0; i < arguments.length; i++) {
				String columnName = columnNames[i];

				if (original) {
					arguments[i] = usuarioModelImpl.getColumnOriginalValue(
						columnName);
				}
				else {
					arguments[i] = usuarioModelImpl.getColumnValue(columnName);
				}
			}

			return arguments;
		}

		private static final Map<FinderPath, Long>
			_finderPathColumnBitmasksCache = new ConcurrentHashMap<>();

	}

}