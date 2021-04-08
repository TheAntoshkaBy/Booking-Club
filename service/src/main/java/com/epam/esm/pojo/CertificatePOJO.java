package com.epam.esm.pojo;

import com.epam.esm.entity.Book;
import com.epam.esm.service.support.impl.TagPojoConverter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CertificatePOJO {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer durationDays;
    private List<TagPOJO> reviews;
    private Date creationDate;
    private Date modification;

    public CertificatePOJO(Long id, String name, String description, BigDecimal price,
                           Date creationDate, Date modification, Integer durationDays) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.durationDays = durationDays;
        this.creationDate = creationDate;
        this.modification = modification;
    }

    public CertificatePOJO(String name, String description, BigDecimal price,
                           Integer durationDays) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.durationDays = durationDays;
    }

    public CertificatePOJO(Book book) {
        /*TagPojoConverter converter = new TagPojoConverter();
        this.id = book.getId();
        this.name = book.getName();
        this.description = book.getDescription();
        this.price = book.getPrice();
        this.creationDate = book.getCreationDate();
        this.modification = book.getModification();
        this.durationDays = book.getDurationDays();
        this.reviews = converter.convert(book.getReviews());*/
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CertificatePOJO that = (CertificatePOJO) o;
        return Objects.equals(name, that.name) &&
            Objects.equals(description, that.description) &&
            Objects.equals(price, that.price) &&
            Objects.equals(durationDays, that.durationDays) &&
            Objects.equals(reviews, that.reviews);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, price, durationDays, reviews);
    }
}
