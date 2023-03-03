package com.example.gates.Repositories;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class NewsRepositoryTest {
    @Autowired
    NewsRepository newsRepository;

    @Test
    public void findAll(){
        assertNotNull(newsRepository.findAll());
    }

}

