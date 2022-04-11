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

package usuario.servicio.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Usuario}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Usuario
 * @generated
 */
public class UsuarioWrapper
	extends BaseModelWrapper<Usuario>
	implements ModelWrapper<Usuario>, Usuario {

	public UsuarioWrapper(Usuario usuario) {
		super(usuario);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("userSurname", getUserSurname());
		attributes.put("userBirthdate", getUserBirthdate());
		attributes.put("userMail", getUserMail());
		attributes.put("creationDate", getCreationDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		String userSurname = (String)attributes.get("userSurname");

		if (userSurname != null) {
			setUserSurname(userSurname);
		}

		Date userBirthdate = (Date)attributes.get("userBirthdate");

		if (userBirthdate != null) {
			setUserBirthdate(userBirthdate);
		}

		String userMail = (String)attributes.get("userMail");

		if (userMail != null) {
			setUserMail(userMail);
		}

		Date creationDate = (Date)attributes.get("creationDate");

		if (creationDate != null) {
			setCreationDate(creationDate);
		}
	}

	/**
	 * Returns the creation date of this usuario.
	 *
	 * @return the creation date of this usuario
	 */
	@Override
	public Date getCreationDate() {
		return model.getCreationDate();
	}

	/**
	 * Returns the primary key of this usuario.
	 *
	 * @return the primary key of this usuario
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the user birthdate of this usuario.
	 *
	 * @return the user birthdate of this usuario
	 */
	@Override
	public Date getUserBirthdate() {
		return model.getUserBirthdate();
	}

	/**
	 * Returns the user ID of this usuario.
	 *
	 * @return the user ID of this usuario
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user mail of this usuario.
	 *
	 * @return the user mail of this usuario
	 */
	@Override
	public String getUserMail() {
		return model.getUserMail();
	}

	/**
	 * Returns the user name of this usuario.
	 *
	 * @return the user name of this usuario
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user surname of this usuario.
	 *
	 * @return the user surname of this usuario
	 */
	@Override
	public String getUserSurname() {
		return model.getUserSurname();
	}

	/**
	 * Returns the user uuid of this usuario.
	 *
	 * @return the user uuid of this usuario
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this usuario.
	 *
	 * @return the uuid of this usuario
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the creation date of this usuario.
	 *
	 * @param creationDate the creation date of this usuario
	 */
	@Override
	public void setCreationDate(Date creationDate) {
		model.setCreationDate(creationDate);
	}

	/**
	 * Sets the primary key of this usuario.
	 *
	 * @param primaryKey the primary key of this usuario
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the user birthdate of this usuario.
	 *
	 * @param userBirthdate the user birthdate of this usuario
	 */
	@Override
	public void setUserBirthdate(Date userBirthdate) {
		model.setUserBirthdate(userBirthdate);
	}

	/**
	 * Sets the user ID of this usuario.
	 *
	 * @param userId the user ID of this usuario
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user mail of this usuario.
	 *
	 * @param userMail the user mail of this usuario
	 */
	@Override
	public void setUserMail(String userMail) {
		model.setUserMail(userMail);
	}

	/**
	 * Sets the user name of this usuario.
	 *
	 * @param userName the user name of this usuario
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user surname of this usuario.
	 *
	 * @param userSurname the user surname of this usuario
	 */
	@Override
	public void setUserSurname(String userSurname) {
		model.setUserSurname(userSurname);
	}

	/**
	 * Sets the user uuid of this usuario.
	 *
	 * @param userUuid the user uuid of this usuario
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this usuario.
	 *
	 * @param uuid the uuid of this usuario
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	protected UsuarioWrapper wrap(Usuario usuario) {
		return new UsuarioWrapper(usuario);
	}

	@Override
	public Usuario cloneWithOriginalValues() {
		// TODO Auto-generated method stub
		return null;
	}

}