package com.epam.esm.service.ext.impl;

import com.epam.esm.entity.BookingClubOrder;
import com.epam.esm.entity.User;
import com.epam.esm.service.ext.OrderService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class BookingClubOrderServiceJpa implements OrderService {

    @Override
    public List<BookingClubOrder> findAll(int page, int size) {
        return null;
    }

    @Override
    public BookingClubOrder findById(long id) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public BookingClubOrder create(BookingClubOrder order, User user) {
        return null;
    }

    @Override
    public BookingClubOrder create(BookingClubOrder bookingClubOrder) {
        return null;
    }

    @Override
    public BookingClubOrder update(BookingClubOrder bookingClubOrder, long id) {
        return null;
    }

    @Override
    public List<BookingClubOrder> findAllByOwner(long id, int offset, int limit) {
        return null;
    }

    @Override
    public int ordersCountByOwner(long id) {
        return 0;
    }

    @Override
    public int getOrdersCount() {
        return 0;
    }

   /* private final OrderRepository repository;
    private final BookRepository bookRepository;
    private final PojoConverter<BookingClubOrder, Order> converter;
    private final PojoConverter<UserPOJO, User> userConverter;
    private final OrderValidator validator;
    private final UserValidator userValidator;
    private final CertificateValidator certificateValidator;

    @Autowired
    public ShopOrderService(OrderRepository repository, BookRepository bookRepository,
        PojoConverter<BookingClubOrder, Order> converter,
        PojoConverter<UserPOJO, User> userConverter,
        OrderValidator validator, UserValidator userValidator,
        CertificateValidator certificateValidator) {
        this.repository = repository;
        this.bookRepository = bookRepository;
        this.converter = converter;
        this.userConverter = userConverter;
        this.validator = validator;
        this.userValidator = userValidator;
        this.certificateValidator = certificateValidator;
    }

    @Override
    public List<BookingClubOrder> findAll(int page, int size) {
        page = PojoConverter.convertPaginationPageToDbOffsetParameter(page, size);
        List<Order> orders = repository.findAll(--page, size);
        return converter.convert(orders);
    }

    @Override
    public BookingClubOrder find(long id) {
        validator.checkId(id);
        return new BookingClubOrder(repository.findById(id));
    }

    @Override
    public void delete(long id) {
        validator.checkId(id);
        repository.delete(id);
    }

    @Override
    public BookingClubOrder create(BookingClubOrder order, UserPOJO userPOJO) {
        order.setCost(new BigDecimal(0));
        order.setCreatedDate(new Date());

        return new BookingClubOrder(
            repository.create(converter.convert(order), userConverter.convert(userPOJO)));
    }

    @Override
    public List<BookingClubOrder> findAllByOwner(long id, int page, int size) {
        page = PojoConverter.convertPaginationPageToDbOffsetParameter(page, size);
        userValidator.checkId(id);
        return converter.convert(repository.findAllByOwner(id, --page, size));
    }

    @Override
    public List<BookingClubOrder> findAllByOwner(long id) {
        userValidator.checkId(id);
        return converter.convert(repository.findAllByOwner(id));
    }

    @Override
    public int ordersCountByOwner(long id) {
        userValidator.checkId(id);
        return repository.getOrdersCountByOwner(id);
    }

    @Override
    public BookingClubOrder addCertificates(long orderId, List<Long> certificatesId) {
        Order order = repository.findById(orderId);
        certificatesId.forEach(certificateValidator::checkId);
        List<Book> books = certificatesId
            .stream()
            .map(bookRepository::findById)
            .collect(Collectors.toList());
        BigDecimal summaryPrice = BigDecimal.valueOf(0);
        for (Book book : books) {
           // summaryPrice = summaryPrice.add(book.getPrice());
        }

        return new BookingClubOrder(
            repository.addCertificates(order, books, summaryPrice));
    }

    @Override
    public int getOrdersCount() {
        return repository.getOrdersCount();
    }*/
}
