package com.epam.esm.service.ext;

public interface BookingClubService<T> {

    T create(T t);

    T findById(long id);

    void delete(long id);

    T update(T t, long id);
}
