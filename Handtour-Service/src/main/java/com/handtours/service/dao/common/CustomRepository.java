package com.handtours.service.dao.common;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

/**
 * @author Administrator
 *
 *         CreatedTime  2016/8/8 0008 16:28
 */
@NoRepositoryBean
public interface CustomRepository<T, ID extends Serializable>
        extends PagingAndSortingRepository<T, ID> {

    <S extends T> S save(S s);
}
