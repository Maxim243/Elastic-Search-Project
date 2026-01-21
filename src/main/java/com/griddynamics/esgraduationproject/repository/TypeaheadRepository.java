package com.griddynamics.esgraduationproject.repository;

import com.griddynamics.esgraduationproject.model.TypeaheadServiceRequest;
import com.griddynamics.esgraduationproject.model.TypeaheadServiceResponse;

import java.io.IOException;

public interface TypeaheadRepository {
    TypeaheadServiceResponse getAllTypeaheads(TypeaheadServiceRequest request);

    TypeaheadServiceResponse getTypeaheadsByQuery(TypeaheadServiceRequest request);

    void createIndex() throws IOException;

    void deletePreviousIndices(String indexPrefix, Long keepIndices) throws IOException;
}
