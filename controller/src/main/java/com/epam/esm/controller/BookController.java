package com.epam.esm.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.epam.esm.converter.BookConverter;
import com.epam.esm.dto.BookDTO;
import com.epam.esm.service.BookService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("books")
public class BookController {

    private BookService service;
    private BookConverter converter;

    @Autowired
    public void setConverter(BookConverter converter) {
        this.converter = converter;
    }

    @Autowired
    public void setService(@Qualifier("bookServiceJPA") BookService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addBook(@RequestBody @Valid BookDTO book) {
        BookDTO newBook = new BookDTO(service.create(converter.convert(book)));

        return ResponseEntity.created(linkTo(methodOn(BookController.class).findById(newBook.getId())).toUri())
            .body(newBook.getModel());
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<BookDTO>> findById(@PathVariable long id) {
        BookDTO bookDTO = new BookDTO(service.find(id));

        return new ResponseEntity<>(bookDTO.getModel(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        service.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*
    @GetMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CertificateList> find(@RequestParam Map<String, String> params,
                                                @RequestBody(required = false) List<TagDTO> tags) {
        int page = ControllerUtils
            .getValidPaginationParam(params.get(ControllerParamNames.PAGE_PARAM_NAME),
                ControllerParamNames.PAGE_PARAM_NAME);
        int size = ControllerUtils
            .getValidPaginationParam(params.get(ControllerParamNames.SIZE_PARAM_NAME),
                ControllerParamNames.SIZE_PARAM_NAME);

        List<TagPOJO> tagsPojo = null;
        if (tags != null) {
            tagsPojo = tags
                .stream()
                .map(tagConverter::convert)
                .collect(Collectors.toList());
        }

        List<CertificatePOJO> certificates = service
            .findAll(params, tagsPojo, page, size);
        int certificatesCount = service.getCertificatesCount(params, tagsPojo);

        CertificateList certificateList = new CertificateList
            .CertificateListBuilder(certificates, converter)
            .page(page)
            .size(size)
            .parameters(params)
            .resultCount(certificatesCount)
            .build();

        return new ResponseEntity<>(certificateList, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteCertificate(@PathVariable Integer id) {
        service.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/{id}",
        consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<CertificateDTO>> updateCertificate(
        @RequestBody @Valid CertificateDTO certificate,
        @PathVariable int id) {
        service.update(id, converter.convert(certificate));
        CertificateDTO updatedCertificate = new CertificateDTO(service.find(id));

        return new ResponseEntity<>(updatedCertificate.getModel(), HttpStatus.OK);
    }

    @PatchMapping(path = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<CertificateDTO>> updateCertificatePrice(
        @RequestBody CertificateDTO certificateDTO,
        @PathVariable long id) {
        service.updatePath(id, converter.convert(certificateDTO));
        CertificateDTO updatedCertificate = new CertificateDTO(service.find(id));

        return new ResponseEntity<>(updatedCertificate.getModel(), HttpStatus.OK);
    }

    @PostMapping(path = "{id}/tags", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<CertificateDTO>> addTagToCertificate(
        @PathVariable Integer id, @RequestBody @Valid TagDTO tag) {
        service.addTag(id, tagConverter.convert(tag));
        CertificateDTO editCertificate = new CertificateDTO(service.find(id));

        return new ResponseEntity<>(editCertificate.getModel(), HttpStatus.OK);
    }

    @PatchMapping(path = "{id}/tags", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<CertificateDTO>> addTagToCertificate(
        @PathVariable Integer id,
        @Valid @RequestBody AddedTagDTO tagDTOId) {
        service.addTag(id, tagDTOId.getAddedTag());
        CertificateDTO editCertificate = new CertificateDTO(service.find(id));

        return new ResponseEntity<>(editCertificate.getModel(), HttpStatus.OK);
    }*/
}
