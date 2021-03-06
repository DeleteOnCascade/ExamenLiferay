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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the Usuario service. Represents a row in the &quot;zemsania_Usuario&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>usuario.servicio.model.impl.UsuarioModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>usuario.servicio.model.impl.UsuarioImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Usuario
 * @generated
 */
@ProviderType
public interface UsuarioModel extends BaseModel<Usuario> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a usuario model instance should use the {@link Usuario} interface instead.
	 */

	/**
	 * Returns the primary key of this usuario.
	 *
	 * @return the primary key of this usuario
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this usuario.
	 *
	 * @param primaryKey the primary key of this usuario
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this usuario.
	 *
	 * @return the uuid of this usuario
	 */
	@AutoEscape
	public String getUuid();

	/**
	 * Sets the uuid of this usuario.
	 *
	 * @param uuid the uuid of this usuario
	 */
	public void setUuid(String uuid);

	/**
	 * Returns the user ID of this usuario.
	 *
	 * @return the user ID of this usuario
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this usuario.
	 *
	 * @param userId the user ID of this usuario
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this usuario.
	 *
	 * @return the user uuid of this usuario
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this usuario.
	 *
	 * @param userUuid the user uuid of this usuario
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this usuario.
	 *
	 * @return the user name of this usuario
	 */
	@AutoEscape
	public String getUserName();

	/**
	 * Sets the user name of this usuario.
	 *
	 * @param userName the user name of this usuario
	 */
	public void setUserName(String userName);

	/**
	 * Returns the user surname of this usuario.
	 *
	 * @return the user surname of this usuario
	 */
	@AutoEscape
	public String getUserSurname();

	/**
	 * Sets the user surname of this usuario.
	 *
	 * @param userSurname the user surname of this usuario
	 */
	public void setUserSurname(String userSurname);

	/**
	 * Returns the user birthdate of this usuario.
	 *
	 * @return the user birthdate of this usuario
	 */
	public Date getUserBirthdate();

	/**
	 * Sets the user birthdate of this usuario.
	 *
	 * @param userBirthdate the user birthdate of this usuario
	 */
	public void setUserBirthdate(Date userBirthdate);

	/**
	 * Returns the user mail of this usuario.
	 *
	 * @return the user mail of this usuario
	 */
	@AutoEscape
	public String getUserMail();

	/**
	 * Sets the user mail of this usuario.
	 *
	 * @param userMail the user mail of this usuario
	 */
	public void setUserMail(String userMail);

	/**
	 * Returns the creation date of this usuario.
	 *
	 * @return the creation date of this usuario
	 */
	public Date getCreationDate();

	/**
	 * Sets the creation date of this usuario.
	 *
	 * @param creationDate the creation date of this usuario
	 */
	public void setCreationDate(Date creationDate);

}