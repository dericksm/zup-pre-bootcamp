package com.derick.zupbootcamp.domain.dto;

import com.derick.zupbootcamp.domain.entities.BaseEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;

public class BaseConverter<T extends BaseEntity, D extends BaseDTO> {

    @Autowired
    private ModelMapper modelMapper;

    protected final Class<D> dtoEntityType;
    protected final Class<T> entityType;

    public BaseConverter() {
        this.entityType = (Class<T>) GenericTypeResolver.resolveTypeArguments(getClass(), BaseConverter.class)[0];
        this.dtoEntityType = (Class<D>) GenericTypeResolver.resolveTypeArguments(getClass(), BaseConverter.class)[1];
    }

    public D convertToDto(T post) {
        D postDto = modelMapper.map(post, dtoEntityType);
        return postDto;
    }

    public T convertToEntity(D postDto) {
        T post = modelMapper.map(postDto, entityType);
        return post;
    }
}
