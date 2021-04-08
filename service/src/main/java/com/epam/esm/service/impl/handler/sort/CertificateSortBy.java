package com.epam.esm.service.impl.handler.sort;

import com.epam.esm.pojo.CertificatePOJO;
import java.util.List;
import java.util.Map;

public interface CertificateSortBy {

    List<CertificatePOJO> sortOurCertificates(Map<String, String> request, int page, int size);

    String getType();
}
