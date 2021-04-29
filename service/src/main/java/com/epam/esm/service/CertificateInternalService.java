package com.epam.esm.service;

import com.epam.esm.entity.Book;
import java.util.List;
import java.util.Map;

/**
 * @author Anton Vedenichev (https://github.com/TheAntoshkaBy)
 */
public interface CertificateInternalService {

    /**
     * This method delegates management to one of find methods.
     *
     * @return*/
    List<Book> findAll(int page, int size);

    int getAllCertificateCount();

    /**
     * This method finds all certificates from database
     *
     * @param request Certificate request data
     * @param tags    Certificate tags
     * @param page    pagination offset
     * @param size    pagination limit
     **/
    List<Book> findAllComplex(Map<String, String> request, List<TagPOJO> tags, int page,
                              int size);

    /**
     * This method finds certificates count
     *
     * @param request Certificate request data
     * @param tags    Certificate tags
     **/
    int getCountComplex(Map<String, String> request, List<TagPOJO> tags);

    /**
     * This method finds all certificates which contain string in name from database
     *
     * @param text Certificate name part
     **/
    List<Book> findByAllCertificatesByNamePart(String text);
}