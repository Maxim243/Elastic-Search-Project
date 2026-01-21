package com.griddynamics.esgraduationproject.service;

import com.griddynamics.esgraduationproject.model.TypeaheadServiceRequest;
import com.griddynamics.esgraduationproject.model.TypeaheadServiceResponse;
import com.griddynamics.esgraduationproject.repository.TypeaheadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TypeaheadServiceImpl implements TypeaheadService {

    @Value("${com.griddynamics.es.graduation.project.request.default.findByQuerySize}")
    private int defaultFindByQuerySize;
    @Value("${com.griddynamics.es.graduation.project.request.default.getAllSize}")
    private int defaultGetAllSize;
    @Value("${com.griddynamics.es.graduation.project.request.minQueryLength}")
    private int minQueryLength;
    @Value("${com.griddynamics.es.graduation.project.index}")
    private String prefixIndexName;
    @Value("${com.griddynamics.es.graduation.project.indices_amount}")
    private Long keepIndicesAmount;

    @Autowired
    private TypeaheadRepository typeaheadRepository;

    @Override
    public TypeaheadServiceResponse getServiceResponse(TypeaheadServiceRequest request) {
        prepareServiceRequest(request);
        if (request.isGetAllRequest()) {
            return typeaheadRepository.getAllTypeaheads(request);
        } else if (request.getTextQuery().length() < minQueryLength) {
            return new TypeaheadServiceResponse();
        } else {
            return typeaheadRepository.getTypeaheadsByQuery(request);
        }
    }

    private void prepareServiceRequest(TypeaheadServiceRequest request) {
        if (request.getSize() == null || request.getSize() <= 0) {
            request.setSize(request.isGetAllRequest() ? defaultGetAllSize : defaultFindByQuerySize);
        }
    }

    @Override
    public void createIndex() throws IOException {
        typeaheadRepository.createIndex();
    }

    @Override
    public void deletePreviousIndices() throws IOException {
        typeaheadRepository.deletePreviousIndices(prefixIndexName, keepIndicesAmount);
    }
}
