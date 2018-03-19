package net.pupunha.test.job.configuration;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Bean
    public JobRepository jobRepository() throws Exception {
        MapJobRepositoryFactoryBean jobRepository = new MapJobRepositoryFactoryBean();
        jobRepository.setTransactionManager(transactionManager());
        return jobRepository.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws Exception {
        ResourcelessTransactionManager transactionManager = new ResourcelessTransactionManager();
        return transactionManager;
    }

    @Bean
    public JobLauncher jobLauncher() throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository());
        jobLauncher.afterPropertiesSet();
        return jobLauncher;
    }

}