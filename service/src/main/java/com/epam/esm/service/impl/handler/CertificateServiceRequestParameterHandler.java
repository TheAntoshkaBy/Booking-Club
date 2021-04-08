package com.epam.esm.service.impl.handler;

import com.epam.esm.exception.ServiceBadRequestException;
import com.epam.esm.exception.constant.ErrorTextMessageConstants;
import com.epam.esm.pojo.CertificatePOJO;
import com.epam.esm.pojo.InvalidDataMessage;
import com.epam.esm.pojo.TagPOJO;
import com.epam.esm.service.CertificateInternalService;
import com.epam.esm.service.impl.handler.and.ComplexFilter;
import com.epam.esm.service.impl.handler.filter.CertificateFilterRequestParameter;
import com.epam.esm.service.impl.handler.sort.CertificateSortBy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CertificateServiceRequestParameterHandler {

    private CertificateInternalService certificateInternalService;

    @Autowired
    private List<CertificateFilterRequestParameter> certificateFilterRequestParameterList;
    @Autowired
    private List<CertificateSortBy> certificateSortRequestParameterList;
    @Autowired
    private List<ComplexFilter> filters;

    public List<CertificatePOJO> find(
        Map<String, String> request, List<TagPOJO> reviews, int page, int size) {
        return filter(request, reviews, page, size);
    }

    private CertificateFilterRequestParameter findFilter(Map<String, String> request) {
        CertificateFilterRequestParameter resultFilter;
        try {
            resultFilter =
                certificateFilterRequestParameterList
                    .stream()
                    .filter(certificateFilter -> certificateFilter
                        .getType()
                        .equals(request.get("filter")))
                    .findFirst()
                    .get();
        } catch (NoSuchElementException e) {
            throw new ServiceBadRequestException(
                new InvalidDataMessage(ErrorTextMessageConstants.FILTER_TYPE_NOT_EXIST)
            );
        }
        return resultFilter;
    }

    private List<CertificatePOJO> filter(Map<String, String> request, List<TagPOJO> reviews,
                                         int page, int size) {
        List<CertificatePOJO> resultList;
        CertificateFilterRequestParameter resultFilter;

        if (request.get("filter") == null) {
            resultList = sort(request, page, size);
        } else {
            resultFilter = findFilter(request);
            resultList = resultFilter.filterOutOurCertificates(request, reviews, page, size);
        }
        return resultList;
    }

    public int findAllCount(
        Map<String, String> request, List<TagPOJO> reviews) {
        int resultCount;
        CertificateFilterRequestParameter resultFilter;

        if (request.get("filter") == null) {
            resultCount = certificateInternalService.getAllCertificateCount();
        } else {
            resultFilter = findFilter(request);
            resultCount = resultFilter.getCountFoundPOJO(request, reviews);
        }
        return resultCount;
    }

    private List<CertificatePOJO> sort(Map<String, String> request, int page, int size) {
        List<CertificatePOJO> result;
        try {
            if (request.get("sort") == null) {
                result = certificateInternalService.findAll(page, size);
            } else {
                result = certificateSortRequestParameterList.stream()
                    .filter(certificateFilter -> certificateFilter
                        .getType()
                        .equals(request.get("sort")))
                    .findFirst()
                    .get()
                    .sortOurCertificates(request, page, size);
            }
        } catch (NoSuchElementException e) {
            throw new ServiceBadRequestException(
                new InvalidDataMessage(ErrorTextMessageConstants.SORT_TYPE_NOT_EXIST)
            );
        }
        return result;
    }

    public Map<String, Object> filterAndSetParams(Map<String, String> parameters) {
        Map<String, Object> returnedParams = new HashMap<>();
        filters.forEach(complexFilter -> {
            String param = parameters.get(complexFilter.getType());
            if (param != null) {
                try {
                    returnedParams.put(complexFilter.getType(), complexFilter.setType(param));
                } catch (RuntimeException e) {
                    throw new ServiceBadRequestException(
                        new InvalidDataMessage("Invalid parameter data!")
                    );
                }

            }
        });

        return returnedParams;
    }

    private StringBuilder buildQuery(Map<String, String> parameters) {
        StringBuilder result = new StringBuilder();
        boolean isFirst = true;
        for (ComplexFilter filter : filters) {
            String param = parameters.get(filter.getType());
            if (param != null) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    result.append(" and ");
                }
                result.append(filter.getPart());
            }
        }
        return result;
    }

    public String filterAnd(Map<String, String> parameters, List<TagPOJO> reviews) {
        StringBuilder result;

        if (reviews == null || reviews.size() == 0) {
            result = new StringBuilder("select c from certificate c where ");
            StringBuilder buffResult = buildQuery(parameters);
            if (buffResult.toString().isEmpty()) {
                throw new ServiceBadRequestException(new InvalidDataMessage("Equals search parameters!"));
            }
            result.append(buffResult);
        } else {
            result = new
                StringBuilder("select c from certificate c " +
                "join c.reviews t where ");
            appender(parameters, reviews, result);
            result.append("') group by c.id");
        }

        return result.toString();
    }

    public String filterAndGetCount(Map<String, String> parameters, List<TagPOJO> reviews) {
        StringBuilder result;

        if (reviews == null || reviews.size() == 0) {
            result = new StringBuilder("select COUNT(c) from certificate c where ");
            StringBuilder buffResult = buildQuery(parameters);
            if (buffResult.toString().isEmpty()) {
                throw new ServiceBadRequestException(new InvalidDataMessage("Equals search parameters!"));
            }
            result.append(buffResult);
        } else {
            result = new
                StringBuilder("select COUNT(c) from certificate c " +
                "join c.reviews t where ");
            appender(parameters, reviews, result);
            result.append("')");

        }
        return result.toString();
    }

    private void appender(Map<String, String> parameters, List<TagPOJO> reviews,
        StringBuilder result) {
        StringBuilder buffResult = buildQuery(parameters);
        result.append(buffResult);
        if (!buffResult.toString().isEmpty()) {
            result.append(" and t.name IN ('");
        } else {
            result.append(" t.name IN ('");
        }

        for (int i = 0; i < reviews.size() - 1; i++) {
            result.append(reviews.get(i).getName());
            result.append("', '");
        }
        result.append(reviews.get(reviews.size() - 1).getName());
    }

    @Autowired
    public void setCertificateInternalService(
        CertificateInternalService certificateInternalService) {
        this.certificateInternalService = certificateInternalService;
    }
}
