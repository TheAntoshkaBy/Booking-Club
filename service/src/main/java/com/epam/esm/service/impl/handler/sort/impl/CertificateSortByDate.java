package com.epam.esm.service.impl.handler.sort.impl;

import com.epam.esm.pojo.CertificatePOJO;
import com.epam.esm.service.CertificateInternalService;
import com.epam.esm.service.impl.handler.sort.CertificateSortBy;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CertificateSortByDate implements CertificateSortBy {

    private static final String SORT_TYPE = "date";
    private CertificateInternalService certificateInternalService;

    @Autowired
    public void setCertificateInternalService(
        CertificateInternalService certificateInternalService) {
        this.certificateInternalService = certificateInternalService;
    }

    @Override
    public List<CertificatePOJO> sortOurCertificates(Map<String, String> request,
                                                     int page, int size) {
        return certificateInternalService.findAllCertificatesByDate(--page, size);
    }

    public String getType() {
        return SORT_TYPE;
    }
}
