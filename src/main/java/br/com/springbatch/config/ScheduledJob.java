package br.com.springbatch.config;

import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/*
 * Poderia usar essa classe para chamar o Job através de um Scheduled e depois encerrar ela conforme vai chamando o método toggle
 */

@Component
public class ScheduledJob {

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job job;
    private final AtomicBoolean enabled = new AtomicBoolean(false);

    @Scheduled(fixedRate = 1000)
    void execute() {
        if (enabled.get()) {
        	try {
    			JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
    					.toJobParameters();
    			jobLauncher.run(job, jobParameters);
    			toggle();
    		} catch (Exception e) {
    			//logger.info(e.getMessage());
    		}
        }
    }

    public void toggle() {
        enabled.set(!enabled.get());
    }

}
