package com.epam.esm.converter;

import java.util.List;

public interface DtoConverter<T,V> {
    List<T> convert(List<V> var);

    V convert (T var);
}