package com.demo.fileupload.service.mapper;

public interface EnityMapper<D, E> {
    D toDTO(E entity);
    E toEntity(D dto);
}
