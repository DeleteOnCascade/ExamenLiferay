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

package usuario.servicio.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

import usuario.servicio.model.Usuario;
import usuario.servicio.model.UsuarioModel;
import usuario.servicio.model.UsuarioSoap;

/**
 * The base model implementation for the Usuario service. Represents a row in the &quot;zemsania_Usuario&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>UsuarioModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link UsuarioImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UsuarioImpl
 * @generated
 */
@JSON(strict = true)
public class UsuarioModelImpl
	extends BaseModelImpl<Usuario> implements UsuarioModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a usuario model instance should use the <code>Usuario</code> interface instead.
	 */
	public static final String TABLE_NAME = "zemsania_Usuario";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"userSurname", Types.VARCHAR},
		{"userBirthdate", Types.TIMESTAMP}, {"userMail", Types.VARCHAR},
		{"creationDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("userSurname", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("userBirthdate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("userMail", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("creationDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table zemsania_Usuario (uuid_ VARCHAR(75) null,userId LONG not null primary key,userName VARCHAR(75) null,userSurname VARCHAR(75) null,userBirthdate DATE null,userMail VARCHAR(75) null,creationDate DATE null)";

	public static final String TABLE_SQL_DROP = "drop table zemsania_Usuario";

	public static final String ORDER_BY_JPQL = " ORDER BY usuario.userId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY zemsania_Usuario.userId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long USERID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
	}

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static Usuario toModel(UsuarioSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Usuario model = new UsuarioImpl();

		model.setUuid(soapModel.getUuid());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setUserSurname(soapModel.getUserSurname());
		model.setUserBirthdate(soapModel.getUserBirthdate());
		model.setUserMail(soapModel.getUserMail());
		model.setCreationDate(soapModel.getCreationDate());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static List<Usuario> toModels(UsuarioSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Usuario> models = new ArrayList<Usuario>(soapModels.length);

		for (UsuarioSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public UsuarioModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _userId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setUserId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _userId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Usuario.class;
	}

	@Override
	public String getModelClassName() {
		return Usuario.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Usuario, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Usuario, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Usuario, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((Usuario)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Usuario, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Usuario, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(Usuario)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Usuario, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Usuario, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, Usuario>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			Usuario.class.getClassLoader(), Usuario.class, ModelWrapper.class);

		try {
			Constructor<Usuario> constructor =
				(Constructor<Usuario>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException
							reflectiveOperationException) {

					throw new InternalError(reflectiveOperationException);
				}
			};
		}
		catch (NoSuchMethodException noSuchMethodException) {
			throw new InternalError(noSuchMethodException);
		}
	}

	private static final Map<String, Function<Usuario, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<Usuario, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<Usuario, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<Usuario, Object>>();
		Map<String, BiConsumer<Usuario, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<Usuario, ?>>();

		attributeGetterFunctions.put("uuid", Usuario::getUuid);
		attributeSetterBiConsumers.put(
			"uuid", (BiConsumer<Usuario, String>)Usuario::setUuid);
		attributeGetterFunctions.put("userId", Usuario::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<Usuario, Long>)Usuario::setUserId);
		attributeGetterFunctions.put("userName", Usuario::getUserName);
		attributeSetterBiConsumers.put(
			"userName", (BiConsumer<Usuario, String>)Usuario::setUserName);
		attributeGetterFunctions.put("userSurname", Usuario::getUserSurname);
		attributeSetterBiConsumers.put(
			"userSurname",
			(BiConsumer<Usuario, String>)Usuario::setUserSurname);
		attributeGetterFunctions.put(
			"userBirthdate", Usuario::getUserBirthdate);
		attributeSetterBiConsumers.put(
			"userBirthdate",
			(BiConsumer<Usuario, Date>)Usuario::setUserBirthdate);
		attributeGetterFunctions.put("userMail", Usuario::getUserMail);
		attributeSetterBiConsumers.put(
			"userMail", (BiConsumer<Usuario, String>)Usuario::setUserMail);
		attributeGetterFunctions.put("creationDate", Usuario::getCreationDate);
		attributeSetterBiConsumers.put(
			"creationDate",
			(BiConsumer<Usuario, Date>)Usuario::setCreationDate);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_uuid = uuid;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalUuid() {
		return getColumnOriginalValue("uuid_");
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userName = userName;
	}

	@JSON
	@Override
	public String getUserSurname() {
		if (_userSurname == null) {
			return "";
		}
		else {
			return _userSurname;
		}
	}

	@Override
	public void setUserSurname(String userSurname) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userSurname = userSurname;
	}

	@JSON
	@Override
	public Date getUserBirthdate() {
		return _userBirthdate;
	}

	@Override
	public void setUserBirthdate(Date userBirthdate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userBirthdate = userBirthdate;
	}

	@JSON
	@Override
	public String getUserMail() {
		if (_userMail == null) {
			return "";
		}
		else {
			return _userMail;
		}
	}

	@Override
	public void setUserMail(String userMail) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userMail = userMail;
	}

	@JSON
	@Override
	public Date getCreationDate() {
		return _creationDate;
	}

	@Override
	public void setCreationDate(Date creationDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_creationDate = creationDate;
	}

	public long getColumnBitmask() {
		if (_columnBitmask > 0) {
			return _columnBitmask;
		}

		if ((_columnOriginalValues == null) ||
			(_columnOriginalValues == Collections.EMPTY_MAP)) {

			return 0;
		}

		for (Map.Entry<String, Object> entry :
				_columnOriginalValues.entrySet()) {

			if (!Objects.equals(
					entry.getValue(), getColumnValue(entry.getKey()))) {

				_columnBitmask |= _columnBitmasks.get(entry.getKey());
			}
		}

		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			0, Usuario.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Usuario toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, Usuario>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		UsuarioImpl usuarioImpl = new UsuarioImpl();

		usuarioImpl.setUuid(getUuid());
		usuarioImpl.setUserId(getUserId());
		usuarioImpl.setUserName(getUserName());
		usuarioImpl.setUserSurname(getUserSurname());
		usuarioImpl.setUserBirthdate(getUserBirthdate());
		usuarioImpl.setUserMail(getUserMail());
		usuarioImpl.setCreationDate(getCreationDate());

		usuarioImpl.resetOriginalValues();

		return usuarioImpl;
	}

	@Override
	public int compareTo(Usuario usuario) {
		long primaryKey = usuario.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Usuario)) {
			return false;
		}

		Usuario usuario = (Usuario)object;

		long primaryKey = usuario.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isEntityCacheEnabled() {
		return true;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isFinderCacheEnabled() {
		return true;
	}

	@Override
	public void resetOriginalValues() {
		_columnOriginalValues = Collections.emptyMap();

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<Usuario> toCacheModel() {
		UsuarioCacheModel usuarioCacheModel = new UsuarioCacheModel();

		usuarioCacheModel.uuid = getUuid();

		String uuid = usuarioCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			usuarioCacheModel.uuid = null;
		}

		usuarioCacheModel.userId = getUserId();

		usuarioCacheModel.userName = getUserName();

		String userName = usuarioCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			usuarioCacheModel.userName = null;
		}

		usuarioCacheModel.userSurname = getUserSurname();

		String userSurname = usuarioCacheModel.userSurname;

		if ((userSurname != null) && (userSurname.length() == 0)) {
			usuarioCacheModel.userSurname = null;
		}

		Date userBirthdate = getUserBirthdate();

		if (userBirthdate != null) {
			usuarioCacheModel.userBirthdate = userBirthdate.getTime();
		}
		else {
			usuarioCacheModel.userBirthdate = Long.MIN_VALUE;
		}

		usuarioCacheModel.userMail = getUserMail();

		String userMail = usuarioCacheModel.userMail;

		if ((userMail != null) && (userMail.length() == 0)) {
			usuarioCacheModel.userMail = null;
		}

		Date creationDate = getCreationDate();

		if (creationDate != null) {
			usuarioCacheModel.creationDate = creationDate.getTime();
		}
		else {
			usuarioCacheModel.creationDate = Long.MIN_VALUE;
		}

		return usuarioCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Usuario, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(4 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Usuario, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Usuario, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((Usuario)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<Usuario, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<Usuario, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Usuario, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((Usuario)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, Usuario>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private String _uuid;
	private long _userId;
	private String _userName;
	private String _userSurname;
	private Date _userBirthdate;
	private String _userMail;
	private Date _creationDate;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<Usuario, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((Usuario)this);
	}

	public <T> T getColumnOriginalValue(String columnName) {
		if (_columnOriginalValues == null) {
			return null;
		}

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		return (T)_columnOriginalValues.get(columnName);
	}

	private void _setColumnOriginalValues() {
		_columnOriginalValues = new HashMap<String, Object>();

		_columnOriginalValues.put("uuid_", _uuid);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("userSurname", _userSurname);
		_columnOriginalValues.put("userBirthdate", _userBirthdate);
		_columnOriginalValues.put("userMail", _userMail);
		_columnOriginalValues.put("creationDate", _creationDate);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("uuid_", "uuid");

		_attributeNames = Collections.unmodifiableMap(attributeNames);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("uuid_", 1L);

		columnBitmasks.put("userId", 2L);

		columnBitmasks.put("userName", 4L);

		columnBitmasks.put("userSurname", 8L);

		columnBitmasks.put("userBirthdate", 16L);

		columnBitmasks.put("userMail", 32L);

		columnBitmasks.put("creationDate", 64L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private Usuario _escapedModel;

	@Override
	public Usuario cloneWithOriginalValues() {
		// TODO Auto-generated method stub
		return null;
	}

}