package com.griddynamics.esgraduationproject.service;

import com.griddynamics.esgraduationproject.model.TypeaheadServiceRequest;
import com.griddynamics.esgraduationproject.model.TypeaheadServiceResponse;

import java.io.IOException;

public interface TypeaheadService {
    TypeaheadServiceResponse getServiceResponse(TypeaheadServiceRequest request);

    void createIndex() throws IOException;

    void deletePreviousIndices() throws IOException;
}
