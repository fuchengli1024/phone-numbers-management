package com.anz.payments.data.scheduler.service;

import com.anz.payments.data.scheduler.exception.BadRequestException;
import com.anz.payments.data.scheduler.exception.NotFoundException;
import com.anz.payments.data.scheduler.model.Job;
import com.anz.payments.data.scheduler.model.JobResp;
import com.anz.payments.data.scheduler.service.impl.SchedulerJobServiceImpl;
import com.anz.payments.data.scheduler.validators.JobValidator;
import com.anz.payments.data.scheduler.validators.TriggerValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static com.anz.payments.data.scheduler.model.AuditBuilder.buildTriggerAudit;
import static com.anz.payments.data.scheduler.model.JobBuilder.*;
import static com.anz.payments.data.scheduler.model.TriggerBuilder.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * @author : Fucheng Li
 * @since : 20/09/2021, Mon
 **/
@ExtendWith(SpringExtension.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class SchedulerJobServiceTest {

    @TestConfiguration
    static class ContextConfiguration {

        @Bean
        SchedulerJobService getSchedulerJobService() {
            return new SchedulerJobServiceImpl();
        }

    }


    @Autowired
    public SchedulerJobService schedulerJobService;

    @MockBean
    TriggerService triggerService;




    @Test
    public void testGetAllScheduleJob() throws Exception{
        HashSet jobKeys = new HashSet();
        jobKeys.add(JobKey.jobKey("testJob","testGroup"));
        when(scheduler.getJobKeys(any())).thenReturn(jobKeys);
        when(scheduler.getJobDetail(any(JobKey.class))).thenReturn(buildJobDetail(buildSchedulerJob()));
        schedulerJobService.getAllScheduleJob();
    }
    @Test
    public void testGetJobByKey() throws Exception{
        Job job = buildSchedulerJob();
        when(scheduler.getJobDetail(any(JobKey.class))).thenReturn(buildJobDetail(job));
        schedulerJobService.getJobByKey("name","group");

    }

    @Test
    public void testRunJobOnceException() throws Exception{
        String expectedMessage = "Could not trigger job with key";
        Exception exception = assertThrows(BadRequestException.class, () -> {
            doThrow(new SchedulerException("Could not trigger job with key")).when(scheduler).triggerJob(any(JobKey.class));
            schedulerJobService.runJobOnce("name","group",null);
        });

        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }






}
