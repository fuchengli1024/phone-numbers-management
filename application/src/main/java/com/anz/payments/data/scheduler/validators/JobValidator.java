package com.anz.payments.data.scheduler.validators;

import com.anz.payments.data.scheduler.exception.BadRequestException;
import com.anz.payments.data.scheduler.model.Job;
import org.springframework.stereotype.Component;

import static com.anz.payments.data.scheduler.constants.SchedulerConstants.JOB_JMS_TOPIC;
import static com.anz.payments.data.scheduler.constants.SchedulerConstants.JOB_REST_ENDPOINT;

/**
 * @author : Fucheng Li
 * @since : 2/09/2021, Thu
 **/
@Component
public class JobValidator {

    public void jobValidation(com.anz.payments.data.scheduler.model.Job job) {
        if (null == job.getType()) {
            throw new BadRequestException("Job type : " + job.getType() + " is not support for now!");
        }
        if (job.getType().equals(Job.TypeEnum.RESTAPI)) {
            if (!job.getConnectionProperties().containsKey(JOB_REST_ENDPOINT)) {
                throw new BadRequestException("the endpoint is required for rest api job");
            }
        } else {
            if (job.getType().equals(Job.TypeEnum.JMS)) {
                if (!job.getConnectionProperties().containsKey(JOB_JMS_TOPIC)) {
                    throw new BadRequestException("the JMS topic is required for jms job");
                }
            }
        }

    }

}
