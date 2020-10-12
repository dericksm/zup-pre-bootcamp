package com.derick.zupbootcamp.services;

import com.derick.zupbootcamp.domain.entities.BaseEntity;
import com.derick.zupbootcamp.repositories.BaseRepository;
import com.derick.zupbootcamp.services.exceptions.DataIntegrityException;
import com.derick.zupbootcamp.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public abstract class AbstractService<T extends BaseEntity> {

    public abstract BaseRepository<T> getRepository();

    public AbstractService() {
    }

    public T findById(Integer id) {
        T obj = getRepository().findById(id).orElseThrow(() -> new ObjectNotFoundException("Entity not found."));
        return obj;
    }

    public T insert(T obj) {
        return getRepository().save(obj);
    }

    public T update(T obj) {
        T newObj = findById(obj.getId());
        BeanUtils.copyProperties(obj, newObj);
        return getRepository().save(newObj);
    }

    public void deleteById(Integer id) {
        findById(id);
        try {
            getRepository().deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityException("Couldn't delete entity!");
        }
    }

    public List<T> findAll() {
        return getRepository().findAll();
    }

    public Page<T> findPage(Integer page, Integer size, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
        return getRepository().findAll(pageRequest);
    }
}
