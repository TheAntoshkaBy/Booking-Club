package com.epam.esm.converter;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ModelConverter<T,V> {
    List<T> convertToModel(List<V> var);
    List<V> convertFromModel(List<T> models);

    V convertFromModel (T var);
    T convertToModel (V var);
}
