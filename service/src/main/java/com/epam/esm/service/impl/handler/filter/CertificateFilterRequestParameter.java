package com.epam.esm.service.impl.handler.filter;

import com.epam.esm.pojo.CertificatePOJO;
import com.epam.esm.pojo.TagPOJO;
import java.util.List;
import java.util.Map;

public interface CertificateFilterRequestParameter {

    List<CertificatePOJO> filterOutOurCertificates( Map<String, String> request, List<TagPOJO> tags,
                                                    int page, int size);

    int getCountFoundPOJO( Map<String, String> request, List<TagPOJO> tags);

    String getType();
}
