package com.handtours.service.dao.common;

import com.handtours.service.runner.Runner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * @author Administrator
 *
 *         CreatedTime  2016/8/8 0008 16:28
 */
@NoRepositoryBean
public class BaseCustomRepository<T, ID extends Serializable>
        extends SimpleJpaRepository<T, ID> implements CustomRepository<T, ID> {
    private final static Logger logger = LoggerFactory.getLogger(Runner.class);

    private final EntityManager entityManager;

    public BaseCustomRepository(JpaEntityInformation entityInformation,
                            EntityManager entityManager) {
        super(entityInformation, entityManager);

        // Keep the EntityManager around to used from the newly introduced methods.
        this.entityManager = entityManager;
    }


    @Override
    public <S extends T> S save(S entity) {
        S save = super.save(entity);
        logger.debug("base save");
        return save;
    }
}
