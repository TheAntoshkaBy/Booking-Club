package com.epam.esm.service.impl.handler.and;

import org.springframework.stereotype.Component;

@Component
public class ByNameComplexPart implements ComplexFilter {

    @Override
    public String getPart() {
        return "c.name = :name";
    }

    @Override
    public String getType() {
        return "name";
    }

    @Override
    public Object setType(String param) {
        return param;
    }
}
