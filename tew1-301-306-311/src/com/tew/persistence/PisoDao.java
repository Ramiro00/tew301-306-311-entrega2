package com.tew.persistence;

import java.util.List;
import com.tew.model.Piso;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;

public interface PisoDao {

	List<Piso> getPisos();

	void update(Piso p) throws NotPersistedException;

	Piso findById(int id);

	List<Piso> getPisos(String login);

	void save(Piso p, String login) throws AlreadyPersistedException;

	void delete(int id, String login) throws NotPersistedException;

	void deletevisitas(int id, String login) throws NotPersistedException;

	void deleteAll();

	Piso getPiso(Piso p);

}