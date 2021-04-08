package com.epam.esm.service.impl.handler.filter.impl;

import com.epam.esm.pojo.CertificatePOJO;
import com.epam.esm.pojo.TagPOJO;
import com.epam.esm.service.CertificateInternalService;
import com.epam.esm.service.impl.handler.filter.CertificateFilterRequestParameter;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ComplexFilter implements CertificateFilterRequestParameter {

    private static final String FILTER_TYPE = "complex";
    private CertificateInternalService certificateInternalService;

    @Autowired
    public void setCertificateInternalService(
        CertificateInternalService certificateInternalService) {
        this.certificateInternalService = certificateInternalService;
    }

    @Override
    public List<CertificatePOJO> filterOutOurCertificates(Map<String, String> request,
                                                          List<TagPOJO> reviews, int page, int size) {
        return certificateInternalService.findAllComplex(request, reviews, page, size);
    }

    @Override
    public int getCountFoundPOJO(Map<String, String> request, List<TagPOJO> reviews) {
        return certificateInternalService.getCountComplex(request, reviews);
    }

    @Override
    public String getType() {
        return FILTER_TYPE;
    }
}
