package com.ftn.redditcloneprojekat.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;

@NoRepositoryBean
public interface CustomRepository<T, ID extends Serializable> extends Repository<T, ID> {

    Iterable<T> findAll(Pageable sort);

    <S extends T> S save(S entity);
}
