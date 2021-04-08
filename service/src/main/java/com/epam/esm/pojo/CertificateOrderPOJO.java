package com.epam.esm.pojo;

import com.epam.esm.entity.BookingClubOrder;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CertificateOrderPOJO {

    private Long id;
    private BigDecimal cost;
    private String description;
    private Date createdDate;

    private UserPOJO owner;

    private List<CertificatePOJO> certificates;

    public CertificateOrderPOJO(BookingClubOrder bookingClubOrder) {
       /* CertificatePojoConverter converter = new CertificatePojoConverter();
        this.id = order.getId();
        this.cost = order.getCost();

        if (order.getOwner() != null) {
            this.owner = new UserPOJO(order.getOwner().getId(),
                                      order.getOwner().getName(),
                                      order.getOwner().getSurname(),
                                      order.getOwner().getLogin(),
                                      order.getOwner().getPassword(),
                                      order.getOwner().getRoles(),
                                      order.getOwner().getEmail());
        } else {
            this.owner = null;
        }
        if(order.getBooks() != null){
            this.certificates = converter.convert(order.getBooks());
        }
        this.description = order.getDescription();
        this.createdDate = order.getCreateTime();*/
    }

    public CertificateOrderPOJO(String description) {
        this.description = description;
    }
}
