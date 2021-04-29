package com.epam.esm.service.impl;

import com.epam.esm.service.OrderService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ShopOrderService implements OrderService {

    @Override
    public List<CertificateOrderPOJO> findAll(int page, int size) {
        return null;
    }

    @Override
    public CertificateOrderPOJO find(long id) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public CertificateOrderPOJO create(CertificateOrderPOJO order, UserPOJO userPOJO) {
        return null;
    }

    @Override
    public List<CertificateOrderPOJO> findAllByOwner(long id, int offset, int limit) {
        return null;
    }

    @Override
    public List<CertificateOrderPOJO> findAllByOwner(long id) {
        return null;
    }

    @Override
    public int ordersCountByOwner(long id) {
        return 0;
    }

    @Override
    public CertificateOrderPOJO addCertificates(long OrderId, List<Long> certificatesId) {
        return null;
    }

    @Override
    public int getOrdersCount() {
        return 0;
    }

   /* private final OrderRepository repository;
    private final BookRepository bookRepository;
    private final PojoConverter<CertificateOrderPOJO, Order> converter;
    private final PojoConverter<UserPOJO, User> userConverter;
    private final OrderValidator validator;
    private final UserValidator userValidator;
    private final CertificateValidator certificateValidator;

    @Autowired
    public ShopOrderService(OrderRepository repository, BookRepository bookRepository,
        PojoConverter<CertificateOrderPOJO, Order> converter,
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
    public List<CertificateOrderPOJO> findAll(int page, int size) {
        page = PojoConverter.convertPaginationPageToDbOffsetParameter(page, size);
        List<Order> orders = repository.findAll(--page, size);
        return converter.convert(orders);
    }

    @Override
    public CertificateOrderPOJO find(long id) {
        validator.checkId(id);
        return new CertificateOrderPOJO(repository.findById(id));
    }

    @Override
    public void delete(long id) {
        validator.checkId(id);
        repository.delete(id);
    }

    @Override
    public CertificateOrderPOJO create(CertificateOrderPOJO order, UserPOJO userPOJO) {
        order.setCost(new BigDecimal(0));
        order.setCreatedDate(new Date());

        return new CertificateOrderPOJO(
            repository.create(converter.convert(order), userConverter.convert(userPOJO)));
    }

    @Override
    public List<CertificateOrderPOJO> findAllByOwner(long id, int page, int size) {
        page = PojoConverter.convertPaginationPageToDbOffsetParameter(page, size);
        userValidator.checkId(id);
        return converter.convert(repository.findAllByOwner(id, --page, size));
    }

    @Override
    public List<CertificateOrderPOJO> findAllByOwner(long id) {
        userValidator.checkId(id);
        return converter.convert(repository.findAllByOwner(id));
    }

    @Override
    public int ordersCountByOwner(long id) {
        userValidator.checkId(id);
        return repository.getOrdersCountByOwner(id);
    }

    @Override
    public CertificateOrderPOJO addCertificates(long orderId, List<Long> certificatesId) {
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

        return new CertificateOrderPOJO(
            repository.addCertificates(order, books, summaryPrice));
    }

    @Override
    public int getOrdersCount() {
        return repository.getOrdersCount();
    }*/
}
