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

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

import usuario.servicio.model.Usuario;

/**
 * The cache model class for representing Usuario in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class UsuarioCacheModel implements CacheModel<Usuario>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof UsuarioCacheModel)) {
			return false;
		}

		UsuarioCacheModel usuarioCacheModel = (UsuarioCacheModel)object;

		if (userId == usuarioCacheModel.userId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, userId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", userSurname=");
		sb.append(userSurname);
		sb.append(", userBirthdate=");
		sb.append(userBirthdate);
		sb.append(", userMail=");
		sb.append(userMail);
		sb.append(", creationDate=");
		sb.append(creationDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Usuario toEntityModel() {
		UsuarioImpl usuarioImpl = new UsuarioImpl();

		if (uuid == null) {
			usuarioImpl.setUuid("");
		}
		else {
			usuarioImpl.setUuid(uuid);
		}

		usuarioImpl.setUserId(userId);

		if (userName == null) {
			usuarioImpl.setUserName("");
		}
		else {
			usuarioImpl.setUserName(userName);
		}

		if (userSurname == null) {
			usuarioImpl.setUserSurname("");
		}
		else {
			usuarioImpl.setUserSurname(userSurname);
		}

		if (userBirthdate == Long.MIN_VALUE) {
			usuarioImpl.setUserBirthdate(null);
		}
		else {
			usuarioImpl.setUserBirthdate(new Date(userBirthdate));
		}

		if (userMail == null) {
			usuarioImpl.setUserMail("");
		}
		else {
			usuarioImpl.setUserMail(userMail);
		}

		if (creationDate == Long.MIN_VALUE) {
			usuarioImpl.setCreationDate(null);
		}
		else {
			usuarioImpl.setCreationDate(new Date(creationDate));
		}

		usuarioImpl.resetOriginalValues();

		return usuarioImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		userSurname = objectInput.readUTF();
		userBirthdate = objectInput.readLong();
		userMail = objectInput.readUTF();
		creationDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		if (userSurname == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userSurname);
		}

		objectOutput.writeLong(userBirthdate);

		if (userMail == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userMail);
		}

		objectOutput.writeLong(creationDate);
	}

	public String uuid;
	public long userId;
	public String userName;
	public String userSurname;
	public long userBirthdate;
	public String userMail;
	public long creationDate;

}