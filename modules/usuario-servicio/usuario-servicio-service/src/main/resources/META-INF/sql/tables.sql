create table zemsania_Usuario (
	uuid_ VARCHAR(75) null,
	userId LONG not null primary key,
	userName VARCHAR(75) null,
	userSurname VARCHAR(75) null,
	userBirthdate DATE null,
	userMail VARCHAR(75) null,
	creationDate DATE null
);