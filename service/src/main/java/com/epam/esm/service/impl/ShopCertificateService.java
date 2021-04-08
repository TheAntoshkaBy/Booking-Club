package com.epam.esm.service.impl;

import com.epam.esm.pojo.CertificatePOJO;
import com.epam.esm.pojo.TagPOJO;
import com.epam.esm.service.CertificateInternalService;
import com.epam.esm.service.CertificateService;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ShopCertificateService implements CertificateInternalService, CertificateService {

    @Override
    public List<CertificatePOJO> findAll(Map<String, String> params, List<TagPOJO> tags, int page, int size) {
        return null;
    }

    @Override
    public int getCertificatesCount(Map<String, String> request, List<TagPOJO> tags) {
        return 0;
    }

    @Override
    public List<CertificatePOJO> findAll(int page, int size) {
        return null;
    }

    @Override
    public CertificatePOJO find(long id) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void update(long id, CertificatePOJO certificate) {

    }

    @Override
    public void updatePath(long id, CertificatePOJO newCertificateData) {

    }

    @Override
    public CertificatePOJO create(CertificatePOJO certificate) {
        return null;
    }

    @Override
    public void addTag(long id, TagPOJO tag) {

    }

    @Override
    public void addTag(long idCertificate, long idTag) {

    }

    @Override
    public void deleteTag(long idCertificate, long idTag) {

    }

    @Override
    public int getAllCertificateCount() {
        return 0;
    }

    @Override
    public List<CertificatePOJO> findAllComplex(Map<String, String> request, List<TagPOJO> tags, int page, int size) {
        return null;
    }

    @Override
    public int getCountComplex(Map<String, String> request, List<TagPOJO> tags) {
        return 0;
    }

    @Override
    public int findByAllCertificatesByIdThresholdCount(long id) {
        return 0;
    }

    @Override
    public List<CertificatePOJO> findAllCertificatesByDate(int page, int size) {
        return null;
    }

    @Override
    public List<CertificatePOJO> findAllCertificatesByIdThreshold(long id, int page, int size) {
        return null;
    }

    @Override
    public List<CertificatePOJO> findAllCertificatesByTag(TagPOJO tag, int page, int size) {
        return null;
    }

    @Override
    public List<CertificatePOJO> findByAllCertificatesByNamePart(String text) {
        return null;
    }


    /*private CertificateServiceRequestParameterHandler certificateServiceRequestParameterHandler;
    private final TagValidator tagValidator;
    private final BookRepository bookRepository;
    private final ReviewRepository reviewRepository;
    private final PojoConverter<CertificatePOJO, Book> converter;
    private final PojoConverter<TagPOJO, Review> tagConverter;
    private final CertificateValidator certificateValidator;

    @Autowired
    public ShopCertificateService(TagValidator tagValidator,
                                  BookRepository bookRepository,
                                  ReviewRepository reviewRepository, CertificatePojoConverter converter,
                                  PojoConverter<TagPOJO, Review> tagConverter,
                                  CertificateValidator certificateValidator) {
        this.tagValidator = tagValidator;
        this.bookRepository = bookRepository;
        this.reviewRepository = reviewRepository;
        this.converter = converter;
        this.tagConverter = tagConverter;
        this.certificateValidator = certificateValidator;
    }

    @Autowired
    public void setCertificateServiceRequestParameterHandler(
        CertificateServiceRequestParameterHandler certificateServiceRequestParameterHandler) {
        this.certificateServiceRequestParameterHandler = certificateServiceRequestParameterHandler;
    }

    *//**
     * @param params Request params for choice selection of certificate display type
     * @return Certificate list
     *//*
    @Override
    public List<CertificatePOJO> findAll(Map<String, String> params, List<TagPOJO> reviews,
                                         int page, int size
    ) {
        return certificateServiceRequestParameterHandler.find(params, reviews, page, size);
    }

    public List<CertificatePOJO> findAllComplex(Map<String, String> request, List<TagPOJO> reviews,
                                                int page, int size) {
        page = PojoConverter.convertPaginationPageToDbOffsetParameter(page, size);
        Map<String, Object> parametrizedRequest = certificateServiceRequestParameterHandler
            .filterAndSetParams(request);
        String query = certificateServiceRequestParameterHandler.filterAnd(request, reviews);

        return converter.convert(bookRepository
            .findAllComplex(query, parametrizedRequest, --page, size));
    }

    @Override
    public int getCertificatesCount(Map<String, String> request, List<TagPOJO> reviews) {
        return certificateServiceRequestParameterHandler.findAllCount(request, reviews);
    }

    public int getCountComplex(Map<String, String> request, List<TagPOJO> reviews) {
        Map<String, Object> parametrizedRequest = certificateServiceRequestParameterHandler
            .filterAndSetParams(request);
        String query = certificateServiceRequestParameterHandler.filterAndGetCount(request, reviews);

        return bookRepository.findCountComplex(query, parametrizedRequest);
    }

    @Override
    public int findByAllCertificatesByIdThresholdCount(long id) {
        return bookRepository.findCountAllByIdThreshold(id);
    }

    @Override
    public List<CertificatePOJO> findAll(int page, int size) {
        page = PojoConverter.convertPaginationPageToDbOffsetParameter(page, size);

        return converter.convert(bookRepository.findAll(--page, size));
    }

    @Override
    public CertificatePOJO find(long id) {
        certificateValidator.checkId(id);
        return new CertificatePOJO(bookRepository.findById(id));
    }

    @Override
    public List<CertificatePOJO> findAllCertificatesByDate(int page, int size) {
        return converter.convert(bookRepository.findAllByDate(page, size));
    }

    @Override
    public int getAllCertificateCount() {
        return bookRepository.getBookCount();
    }

    @Deprecated
    @Override
    public List<CertificatePOJO> findAllCertificatesByIdThreshold(long id, int page, int size) {
        return converter.convert(bookRepository.findAllByIdThreshold(id, --page, size));
    }

    @Deprecated
    @Override
    public List<CertificatePOJO> findAllCertificatesByTag(TagPOJO tag, int page, int size) {
        tagValidator.isCorrectTag(tag);

        return converter.convert(bookRepository.findByTagName(tag.getName(), page, size));
    }

    @Override
    public void delete(long id) {
        bookRepository.delete(id);
    }

    @Override
    public void update(long id, CertificatePOJO certificate) {
        certificateValidator.checkId(id);

        certificate.setModification(new Date());
        Book updatedBook = bookRepository.findById(id);
        Book updateDataBook = converter.convert(certificate);
        bookRepository
            .update(updatedBook, updateDataBook);
    }

    @Override
    public void updatePath(long id, CertificatePOJO newCertificateData) {
        Book bookUpdateData = converter.convert(newCertificateData);
        Book updatedBook = bookRepository.findById(id);
        if(bookUpdateData.getPrice() != null){
            updatedBook.setPrice(bookUpdateData.getPrice());
        }
        if(bookUpdateData.getName() != null){
            updatedBook.setName(bookUpdateData.getName());
        }
        if(bookUpdateData.getDurationDays() != null){
            updatedBook.setDurationDays(bookUpdateData.getDurationDays());
        }
    }

    @Override
    public CertificatePOJO create(CertificatePOJO certificate) {
        certificate.setCreationDate(new Date());

        return new CertificatePOJO(bookRepository
            .create(converter.convert(certificate)));
    }

    @Override
    public void addTag(long id, TagPOJO tag) {
        tagValidator.isCorrectTag(tag);
        bookRepository
            .addTag(id, reviewRepository.create(tagConverter.convert(tag)).getId());
    }

    @Override
    public void addTag(long idCertificate, long idTag) {
        Review review = reviewRepository.findById(idTag);
        Book book = bookRepository.findById(idCertificate);

        bookRepository.addTag(book, review);
    }

    @Override
    public void deleteTag(long idCertificate, long idTag) {
        Book buffBook = bookRepository.findById(idCertificate);

        if (buffBook == null) {
            throw new ServiceBadRequestException(
                new InvalidDataMessage(ErrorTextMessageConstants.NOT_FOUND_CERTIFICATE));
        }

        Optional<Review> buffTag = buffBook
            .getReviews()
            .stream()
            .filter(tag -> tag.getId() == idTag).findFirst();
        if (buffTag.isPresent()) {
            bookRepository.deleteTag(idCertificate, buffTag.get());
        } else {
            throw new ServiceBadRequestException(
                new InvalidDataMessage(ErrorTextMessageConstants.NOT_FOUND_TAG));
        }
    }

    @Override
    public List<CertificatePOJO> findByAllCertificatesByNamePart(String text) {
        String foundedText = "%";
        foundedText += text;
        foundedText += "%";

        return converter.convert(bookRepository
            .findAllByNamePart(foundedText)
        );
    }*/
}
