package com.griddynamics.esgraduationproject;

import com.griddynamics.esgraduationproject.service.TypeaheadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;

@SpringBootApplication
@Slf4j
public class Application implements CommandLineRunner {
    private static final String CREATE_NEW_INDEX_ARG = "createNewIndex";

    @Autowired
    TypeaheadService typeaheadService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, CREATE_NEW_INDEX_ARG);
    }

    @Override
    public void run(String... strings) throws IOException {
        List<String> args = asList(strings);
        boolean needToCreateNewIndex = args.contains(CREATE_NEW_INDEX_ARG);
        if (needToCreateNewIndex) {
            typeaheadService.createIndex();
            typeaheadService.deletePreviousIndices();
        }
    }
}
