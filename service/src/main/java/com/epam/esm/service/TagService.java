package com.epam.esm.service;

import java.util.List;

/**
 * @author Anton Vedenichev (https://github.com/TheAntoshkaBy)
 */
public interface TagService {

    /**
     * This method finds all tags from database
     **/
    List<Review> findAll(int offset, int limit);

    int getTagCount();

    /**
     * This method finds concrete Tag By Id
     *
     * @param id Tag Id
     * @return Tag
     **/
    Review find(long id);

    Review findMostWidelyUsedTag();

    /**
     * This method delete tag by transmitted tag id to certificate with transmitted certificate id
     *
     * @param id certificate id which will be edit
     **/
    void delete(long id);

    /**
     * This method add new Tag
     *
     * @param tag Tag object
     **/
    Review create(Review tag);
}
