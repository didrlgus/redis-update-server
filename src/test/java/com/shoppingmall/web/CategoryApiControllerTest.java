package com.shoppingmall.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingmall.dto.CategoryRequestDto;
import com.shoppingmall.repository.CategoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryApiControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private CategoryRepository categoryRepository;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @After
    public void after() {
        categoryRepository.deleteAll();
    }

    @Test
    public void addFirstCategoryAuthTest() throws Exception {
        callPostFirstCategoryAPI(status().is4xxClientError());
    }

    @WithMockUser(authorities = { "ROLE_ADMIN" })
    @Test
    public void addFirstCategoryTest() throws Exception {
        callPostFirstCategoryAPI(status().isOk());
    }

    @WithMockUser(authorities = { "ROLE_ADMIN" })
    @Test
    public void updateCategoryTest() throws Exception {
        callPostFirstCategoryAPI(status().isOk());

        callPutCategoryAPI(status().isOk());
    }

    private void callPostFirstCategoryAPI(ResultMatcher status) throws Exception {
        mockMvc.perform(post("/v1/categories/first")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(makeFirstCategoryRequestDto())))
                .andExpect(status);
    }

    private void callPutCategoryAPI(ResultMatcher status) throws Exception {
        mockMvc.perform(put("/v1/categories/" + 1L)
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(makeUpdateCategoryRequestDto())))
                .andExpect(status);
    }

    private CategoryRequestDto.firstCategory makeFirstCategoryRequestDto() {
        return CategoryRequestDto.firstCategory.builder()
                .catNm("test")
                .useYn('Y')
                .build();
    }

    private CategoryRequestDto makeUpdateCategoryRequestDto() {
        return CategoryRequestDto.builder().catNm("update-test").useYn('N').build();
    }
}
