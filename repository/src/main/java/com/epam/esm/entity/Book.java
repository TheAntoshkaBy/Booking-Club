package com.epam.esm.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "book")
@Table(name = "book")
/*@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
        name = "findByNameProcedure",
        procedureName = "return_t_certificate3",
        resultClasses = { Book.class},
        parameters = {
            @StoredProcedureParameter
                (
                    name = "text",
                    type = String.class
                )
        })
})*/
@Data
@NoArgsConstructor
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "count")
    private int count;

    @Column(name = "description")
    private String description;

    @Column(name = "date_of_creation")
    private Date creationDate;

    @Column(name = "author")
    private String author;

    public Book(String name, String description, String author, int count, Date creationDate) {
        this.name = name;
        this.count = count;
        this.description = description;
        this.creationDate = creationDate;
        this.author = author;
    }

    /* @OneToMany(mappedBy="rewiew", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private Set<Review> review;*/

//    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//    private Set<BookingClubOrder> bookingClubOrders;
}
