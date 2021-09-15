package com.whnm.mediappbackend.service.impl;

import java.util.List;
import java.util.Optional;

import com.whnm.mediappbackend.repo.GenericDao;
import com.whnm.mediappbackend.service.ICRUD;

public abstract class CRUDImpl<T, ID> implements ICRUD<T, ID> {

	protected abstract GenericDao<T, ID> getRepo();
	
	@Override
	public T registrar(T p) {
		return getRepo().save(p);
	}

	@Override
	public T modificar(T p) {
		return getRepo().save(p);
	}

	@Override
	public List<T> listar() {
		return getRepo().findAll();
	}

	@Override
	public Optional<T> listarPorId(ID id) {
		return getRepo().findById(id);
	}

	@Override
	public void eliminar(ID id) {
		getRepo().deleteById(id);
	}

}
