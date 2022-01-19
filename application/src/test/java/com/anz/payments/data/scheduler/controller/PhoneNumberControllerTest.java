package com.anz.payments.data.scheduler.controller;

import com.anz.payments.data.scheduler.service.JobAuditService;
import com.anz.payments.data.scheduler.service.SchedulerJobService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author : Fucheng Li
 * @since : 20/09/2021, Mon
 **/
@ContextConfiguration(classes = { JobController.class })
@WebMvcTest
public class JobControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SchedulerJobService schedulerJobService;

    @MockBean
    private JobAuditService jobAuditService;

    @InjectMocks
    private JobController jobController;

    @Test
    public void testAddJob() throws Exception {

        String job ="{ \"name\":\"reporting\",\"group\":\"batch\"}";

        MvcResult result = mockMvc.perform(post("/v1/scheduler/jobs")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(job)).andExpect(status().isOk())
                .andReturn();

    }

    @Test
    public void testGetJob() throws Exception {

        String job ="{ \"name\":\"reporting\",\"group\":\"batch\"}";

        MvcResult result = mockMvc.perform(get("/v1/scheduler/jobs")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content(job)
        ).andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testGetJobByKey() throws Exception {

        String job ="{ \"name\":\"reporting\",\"group\":\"batch\"}";

        MvcResult result = mockMvc.perform(get("/v1/scheduler/jobs/jobGroup/jobName")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content(job)
        ).andExpect(status().isOk())
                .andReturn();
    }

}
