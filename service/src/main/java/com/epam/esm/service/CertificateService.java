package com.epam.esm.service;

import com.epam.esm.pojo.CertificatePOJO;
import com.epam.esm.pojo.TagPOJO;
import java.util.List;
import java.util.Map;

public interface CertificateService {

    /**
     * This method delegates management to one of find methods.
     **/
    List<CertificatePOJO> findAll(Map<String, String> params, List<TagPOJO> tags,
                                  int page, int size);

    /**
     * This method find count of certificates.
     **/
    int getCertificatesCount(Map<String, String> request, List<TagPOJO> tags);

    List<CertificatePOJO> findAll(int page, int size);

    /**
     * This method finds concrete Certificate By Id
     *
     * @param id Certificate Id
     * @return Certificate
     **/
    CertificatePOJO find(long id);

    /**
     * This method delete concrete Certificate by transmitted id
     *
     * @param id certificate id which will be delete
     **/
    void delete(long id);

    /**
     * This method update concrete Certificate by transmitted id
     *
     * @param id          certificate id which will be edit
     * @param certificate edit data
     **/
    void update(long id, CertificatePOJO certificate);

    /**
     * This method update concrete Certificate field (price) by transmitted id and new price value
     *
     * @param id    certificate id which will be edit
     * @param newCertificateData edit data
     **/
    void updatePath(long id, CertificatePOJO newCertificateData);

    /**
     * This method add new Certificate using DAO
     *
     * @param certificate Certificate object
     **/
    CertificatePOJO create(CertificatePOJO certificate);

    /**
     * This method add new tag by transmitted data to certificate with transmitted id
     *
     * @param id  certificate id which will be edit
     * @param tag edit data
     **/
    void addTag(long id, TagPOJO tag);

    /**
     * This method add tag by transmitted tag id to certificate with transmitted certificate id
     *
     * @param idCertificate certificate id which will be edit
     * @param idTag         tag id which we will be add to certificate
     **/
    void addTag(long idCertificate, long idTag);

    /**
     * This method delete all tags
     **/
    void deleteTag(long idCertificate, long idTag);

}
