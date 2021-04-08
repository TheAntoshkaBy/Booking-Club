package com.epam.esm.service.impl.handler.filter.impl;

import com.epam.esm.exception.ServiceBadRequestException;
import com.epam.esm.exception.constant.ErrorTextMessageConstants;
import com.epam.esm.pojo.CertificatePOJO;
import com.epam.esm.pojo.InvalidDataMessage;
import com.epam.esm.pojo.TagPOJO;
import com.epam.esm.service.CertificateInternalService;
import com.epam.esm.service.impl.handler.filter.CertificateFilterRequestParameter;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ByNamePartFilter implements CertificateFilterRequestParameter {

    private static final String FILTER_TYPE = "namePart";
    private CertificateInternalService certificateInternalService;

    @Autowired
    public void setCertificateInternalService(
        CertificateInternalService certificateInternalService) {
        this.certificateInternalService = certificateInternalService;
    }

    @Override
    public List<CertificatePOJO> filterOutOurCertificates(Map<String, String> request,
                                                          List<TagPOJO> reviews, int page, int size) {
        String text = request.get("searchingName");
        if (text == null) {
            throw new ServiceBadRequestException(
                new InvalidDataMessage(ErrorTextMessageConstants.FILTER_TYPE_NOT_EXIST)
            );
        }

        return certificateInternalService.findByAllCertificatesByNamePart(text);
    }

    @Override
    public int getCountFoundPOJO(Map<String, String> request, List<TagPOJO> reviews) {
        return certificateInternalService.getAllCertificateCount();
    }

    @Override
    public String getType() {
        return FILTER_TYPE;
    }
}
