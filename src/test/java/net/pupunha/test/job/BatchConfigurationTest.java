package net.pupunha.test.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchConfigurationTest {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private Job job;

    @Autowired
    private JobLauncher jobLauncher;

    @Bean
    public JobLauncherTestUtils jobLauncherTestUtils() {
        JobLauncherTestUtils jobLauncherTestUtils = new JobLauncherTestUtils();
        jobLauncherTestUtils.setJobRepository(jobRepository);
        jobLauncherTestUtils.setJob(job);
        jobLauncherTestUtils.setJobLauncher(jobLauncher);
        return jobLauncherTestUtils;
    }

}
