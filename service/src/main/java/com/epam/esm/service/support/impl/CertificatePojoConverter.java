package com.epam.esm.service.support.impl;

import com.epam.esm.entity.Book;
import com.epam.esm.pojo.CertificatePOJO;
import com.epam.esm.service.support.PojoConverter;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class CertificatePojoConverter /*implements PojoConverter<CertificatePOJO, Book>*/ {

   /* @Override
    public List<CertificatePOJO> convert(List<Book> books) {
        return books.stream()
            .map(CertificatePOJO::new)
            .collect(Collectors.toList());
    }

    @Override
    public Book convert(CertificatePOJO certificate) {
        if (certificate.getId() == null) {
            return new Book(certificate.getName(), certificate.getDescription(),
                                   certificate.getPrice(), certificate.getDurationDays()
            );
        } else {
            return new Book(certificate.getId(), certificate.getName(),
                                   certificate.getDescription(), certificate.getPrice(),
                                   certificate.getDurationDays()
            );
        }
    }*/
}
