package com.epam.esm.converter;

import java.util.List;

public interface DtoConverter<T,V> {
    List<T> convertToDto(List<V> var);
    List<V> convertFromDto(List<T> dto);
    V convertFromDto (T var);
    T convertToDto (V var);
}