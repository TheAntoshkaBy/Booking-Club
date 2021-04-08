package com.epam.esm.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.Table;
import lombok.Data;

@Entity(name = "review")
@Table(name = "review")
/*@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery
        (
            name = "greatest_tag",
            procedureName = "greater_tag_func"
        )
})*/
@Data
public class Review implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long review_id;

    @Column(name = "topic")
    private String topic;

    @Column(name = "description")
    private String description;

    @Column(name = "date_of_creation")
    private Date creationDate;

    @Column(name = "rating")
    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    private User owner;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false, insertable = false, updatable = false)
    private Book book;

    public Review() {
    }
}
