package com.epam.esm.service.support;

import static org.springframework.data.domain.Sort.by;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


public class OffsetBasedPageRequest implements Pageable {
    private int limit;
    private int offset;

    private final Sort sort = by(Sort.Direction.DESC, "id");
    public OffsetBasedPageRequest(int limit, int offset) {
        if (limit < 1) {
            throw new IllegalArgumentException("Limit must not be less than one!");
        }
        if (offset < 0) {
            throw new IllegalArgumentException("Offset index must not be less than zero!");
        }
        this.limit = limit;
        this.offset = offset;
    }
    @Override
    public int getPageNumber() {
        return offset / limit;
    }
    @Override
    public int getPageSize() {
        return limit;
    }
    @Override
    public long getOffset() {
        return offset;
    }
    @Override
    public Sort getSort() {
        return sort;
    }
    @Override
    public Pageable next() {
        return new OffsetBasedPageRequest(getPageSize(), (int) (getOffset() + getPageSize()));
    }
    public Pageable previous() {
        return hasPrevious() ?
            new OffsetBasedPageRequest(getPageSize(), (int) (getOffset() - getPageSize())): this;
    }
    @Override
    public Pageable previousOrFirst() {
        return hasPrevious() ? previous() : first();
    }
    @Override
    public Pageable first() {
        return new OffsetBasedPageRequest(getPageSize(), 0);
    }
    @Override
    public boolean hasPrevious() {
        return offset > limit;
    }
}