package com.anz.payments.data.scheduler.controller;

import com.anz.payments.data.scheduler.api.JobsApi;
import com.anz.payments.data.scheduler.model.*;
import com.anz.payments.data.scheduler.service.JobAuditService;
import com.anz.payments.data.scheduler.service.SchedulerJobService;
import com.anz.payments.data.scheduler.utils.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.quartz.utils.Key;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Validated
@RequestMapping("v1/scheduler/jobs")
@Api(tags = {"Job"})
@Controller
public class PhoneNumbersController implements JobsApi {
    private static final Logger logger = LoggerFactory.getLogger(PhoneNumbersController.class);

    @Autowired
    SchedulerJobService schedulerJobService;

    @Autowired
    JobAuditService jobAuditService;




    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Job>> findJobs() {
        logger.info("[JobController] the method:find all Jobs is started");
        List<Job> jobList = schedulerJobService.getAllScheduleJob();
        logger.info("[JobController] the method:findJobs is execution over ");
        return new ResponseEntity(jobList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Job> addJob(@ApiParam(value = "", required = true) @Valid @RequestBody Job job) {
        logger.info("[JobController] add job is started, the param:{}", job);
        schedulerJobService.addJob(job);
        Message message = Message.success("success");
        message.setData(job);
        jobAuditService.createJobAudit(buildJobAudit(job, new Key(job.getName(),job.getGroup()),"JobAdd", HttpMethod.POST, message));
        return new ResponseEntity(message, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{jobGroup}/{jobName}")
    public ResponseEntity<Void> deleteJob(@ApiParam(value = "", required = true) @PathVariable("jobGroup") String jobGroup, @ApiParam(value = "", required = true) @PathVariable("jobName") String jobName) {
        logger.info("[JobController] delete a job start----------------");
        schedulerJobService.deleteJob(jobName, jobGroup);
        jobAuditService.createJobAudit(buildJobAudit(null,  new Key(jobName,jobGroup),"JobDelete", HttpMethod.DELETE, null));
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    @RequestMapping(value = "/{jobGroup}/{jobName}",
            method = RequestMethod.GET)
    public ResponseEntity<Job> getJob(@ApiParam(value = "", required = true) @PathVariable("jobGroup") String jobGroup, @ApiParam(value = "", required = true) @PathVariable("jobName") String jobName) {
        logger.info("[JobController] get a job is started param {}.{}", jobGroup,jobName);
        Job job = schedulerJobService.getJobByKey(jobName, jobGroup);
        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @RequestMapping(value = "/{jobGroup}/{jobName}",
            method = RequestMethod.PUT)
    public ResponseEntity<Job> updateJob(@ApiParam(value = "", required = true) @PathVariable("jobGroup") String jobGroup, @ApiParam(value = "", required = true) @PathVariable("jobName") String jobName, @ApiParam(value = "A JSON object containing job information") @Valid @RequestBody Job job) {
        logger.info("[JobController] the updateJob method is started, the param:{}.{}",jobGroup,jobName);
        schedulerJobService.updateJob(jobName, jobGroup, job);
        Message message = Message.success("success");
        message.setData(job);
        jobAuditService.createJobAudit(buildJobAudit(job,  new Key(jobName,jobGroup),"JobUpdate", HttpMethod.PUT, message));
        return new ResponseEntity(message, HttpStatus.OK);
    }

    @PatchMapping(value = "/{jobGroup}/{jobName}/runOnce")
    public ResponseEntity<Job> runAJobOnce(@ApiParam(value = "", required = true) @PathVariable("jobGroup") String jobGroup, @ApiParam(value = "", required = true) @PathVariable("jobName") String jobName) {
        logger.info("[JobController] run job once is started :--------------------------");
        schedulerJobService.runJobOnce(jobName, jobGroup, null);
        jobAuditService.createJobAudit(buildJobAudit(null,  new Key(jobName,jobGroup),"JonRunOnce", HttpMethod.PATCH, null));
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping(value = "/{jobGroup}/{jobName}/pause")
    public ResponseEntity<Job> pauseAJob(@PathVariable String jobName, @PathVariable String jobGroup){
        logger.info("[JobController] the url path:------------/pauseJob----------------");
        schedulerJobService.pauseJob(jobName,jobGroup);
        jobAuditService.createJobAudit(buildJobAudit(null,  new Key(jobName,jobGroup),"pauseJob", HttpMethod.PATCH, null));
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping(path="/{jobGroup}/{jobName}/resume")
    public ResponseEntity<Job> resumeAJob(@PathVariable String jobName, @PathVariable String jobGroup){
        logger.info("[JobController] the url path:------------/resumeJob----------------");
        schedulerJobService.resumeJob(jobName,jobGroup);
        jobAuditService.createJobAudit(buildJobAudit(null,  new Key(jobName,jobGroup),"resumeJob", HttpMethod.PATCH, null));
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/callback")
    public ResponseEntity<Void> jobCallback(@ApiParam(value = "", required = true) @Valid @RequestBody JobResp restJobResp) {
        logger.info("[JobController] the call back method is started, the param:{}", restJobResp);
        schedulerJobService.handleCallback(restJobResp);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    private JobAudit buildJobAudit(Object requestBody, Key key, String type, HttpMethod method, Message
            message){

        JobAudit jobAudit = new JobAudit();
        jobAudit.setTraceId(UUID.randomUUID().toString());
        jobAudit.setJobName(key.getName());
        jobAudit.setJobGroup(key.getGroup());
        jobAudit.setEventTime(new Date());
        jobAudit.setEventType(type);
        Request request = new Request();
        request.setMethod(method.toString());
        request.setBody(requestBody);
        request.setHeader(null);
        jobAudit.setRequest(request);
        Response response = new Response();
        response.setBody(message);
        if(method.equals(HttpMethod.DELETE)){
            response.setStatusCode(HttpStatus.NO_CONTENT.toString());
        }else {
            response.setStatusCode(HttpStatus.OK.toString());
        }
        jobAudit.setResponse(response);

        return jobAudit;
    }


}
