package com.tew.persistence;

import java.sql.SQLIntegrityConstraintViolationException;

import com.tew.model.Cliente;
import com.tew.persistence.exception.AlreadyPersistedException;

public interface ClienteDao {

	void save(Cliente c) throws AlreadyPersistedException, SQLIntegrityConstraintViolationException;
	Cliente findByLogin(String login);
	void deleteAll();
}