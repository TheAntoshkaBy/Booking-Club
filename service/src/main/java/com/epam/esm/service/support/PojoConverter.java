package com.epam.esm.service.support;

import java.util.List;

public interface PojoConverter<T, V> {

    static int convertPaginationPageToDbOffsetParameter(int page, int size) {
        if (page != 1) {
            return size * (page - 1) + 1;
        } else {
            return page;
        }
    }

    List<T> convert(List<V> var);

    V convert(T var);
}
