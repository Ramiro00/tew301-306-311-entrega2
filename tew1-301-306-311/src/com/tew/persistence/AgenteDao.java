package com.tew.persistence;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import com.tew.model.Agente;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;

public interface AgenteDao {	
	
	void save(Agente a) throws AlreadyPersistedException, SQLIntegrityConstraintViolationException;
	Agente findByLogin(String login);
	void deleteAll();
	
}
