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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link usuario.servicio.service.http.UsuarioServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class UsuarioSoap implements Serializable {

	public static UsuarioSoap toSoapModel(Usuario model) {
		UsuarioSoap soapModel = new UsuarioSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setUserSurname(model.getUserSurname());
		soapModel.setUserBirthdate(model.getUserBirthdate());
		soapModel.setUserMail(model.getUserMail());
		soapModel.setCreationDate(model.getCreationDate());

		return soapModel;
	}

	public static UsuarioSoap[] toSoapModels(Usuario[] models) {
		UsuarioSoap[] soapModels = new UsuarioSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static UsuarioSoap[][] toSoapModels(Usuario[][] models) {
		UsuarioSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new UsuarioSoap[models.length][models[0].length];
		}
		else {
			soapModels = new UsuarioSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static UsuarioSoap[] toSoapModels(List<Usuario> models) {
		List<UsuarioSoap> soapModels = new ArrayList<UsuarioSoap>(
			models.size());

		for (Usuario model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new UsuarioSoap[soapModels.size()]);
	}

	public UsuarioSoap() {
	}

	public long getPrimaryKey() {
		return _userId;
	}

	public void setPrimaryKey(long pk) {
		setUserId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public String getUserSurname() {
		return _userSurname;
	}

	public void setUserSurname(String userSurname) {
		_userSurname = userSurname;
	}

	public Date getUserBirthdate() {
		return _userBirthdate;
	}

	public void setUserBirthdate(Date userBirthdate) {
		_userBirthdate = userBirthdate;
	}

	public String getUserMail() {
		return _userMail;
	}

	public void setUserMail(String userMail) {
		_userMail = userMail;
	}

	public Date getCreationDate() {
		return _creationDate;
	}

	public void setCreationDate(Date creationDate) {
		_creationDate = creationDate;
	}

	private String _uuid;
	private long _userId;
	private String _userName;
	private String _userSurname;
	private Date _userBirthdate;
	private String _userMail;
	private Date _creationDate;

}