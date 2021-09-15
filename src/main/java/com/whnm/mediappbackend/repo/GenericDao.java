package com.whnm.mediappbackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericDao<T, ID> extends JpaRepository<T, ID> {

}
