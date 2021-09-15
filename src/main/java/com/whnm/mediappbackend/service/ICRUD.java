package com.whnm.mediappbackend.service;

import java.util.List;
import java.util.Optional;

public interface ICRUD<T, ID> {
	T registrar (T p);
	T modificar (T p);
	List<T> listar ();
	Optional<T> listarPorId (ID id);
	void eliminar (ID id);
}
