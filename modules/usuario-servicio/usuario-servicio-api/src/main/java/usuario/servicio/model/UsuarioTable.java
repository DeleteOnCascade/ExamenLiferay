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

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;zemsania_Usuario&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see Usuario
 * @generated
 */
public class UsuarioTable extends BaseTable<UsuarioTable> {

	public static final UsuarioTable INSTANCE = new UsuarioTable();

	public final Column<UsuarioTable, String> uuid = createColumn(
		"uuid_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<UsuarioTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<UsuarioTable, String> userName = createColumn(
		"userName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<UsuarioTable, String> userSurname = createColumn(
		"userSurname", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<UsuarioTable, Date> userBirthdate = createColumn(
		"userBirthdate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<UsuarioTable, String> userMail = createColumn(
		"userMail", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<UsuarioTable, Date> creationDate = createColumn(
		"creationDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);

	private UsuarioTable() {
		super("zemsania_Usuario", UsuarioTable::new);
	}

}