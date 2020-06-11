/*
 * Copyright (c) 2020 the original author or authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.exadel.frs.core.trainservice.controller;

import static com.exadel.frs.core.trainservice.system.global.Constants.API_V1;
import static com.exadel.frs.core.trainservice.system.global.Constants.X_FRS_API_KEY_HEADER;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.exadel.frs.core.trainservice.config.WebMvcTestContext;
import com.exadel.frs.core.trainservice.dao.ModelDao;
import com.exadel.frs.core.trainservice.entity.mongo.Model;
import com.exadel.frs.core.trainservice.filter.SecurityValidationFilter;
import com.exadel.frs.core.trainservice.repository.mongo.FacesRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = {SecurityValidationFilter.class}
        ))
@WebMvcTestContext
public class ModelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final static String API_KEY = "model_key";

    @MockBean
    private ModelDao modelDao;

    @Test
    public void updateModelKeySuccess() throws Exception {
        val newModelKey = UUID.randomUUID().toString();

        doReturn(Model.builder().build())
                .when(modelDao).updateModelApiKey(any(), any());

        mockMvc.perform(put(API_V1 + "/models/api-key?new_model_api_key=" + newModelKey).header(X_FRS_API_KEY_HEADER, API_KEY))
               .andExpect(status().isOk());
    }
}