package net.pupunha.test.job.configuration;

import org.apache.tomcat.util.threads.TaskThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ThreadConfiguration {

//    @Bean
//    public TaskExecutor taskExecutor() {
//        return new SimpleAsyncTaskExecutor("spring_batch");
//    }

//    @Bean
//    public ThreadPoolExecutor threadPoolExecutor() {
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor();
//        threadPoolExecutor.
//    }

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setThreadFactory(taskThreadFactory());
        taskExecutor.setCorePoolSize(50);
//        taskExecutor.setQueueCapacity(10);
        taskExecutor.setMaxPoolSize(50);
        return taskExecutor;
    }

    @Bean
    public TaskThreadFactory taskThreadFactory() {
        String prefix = "ThreadJob-";

        TaskThreadFactory taskThreadFactory = new TaskThreadFactory(prefix, true, 1);
        return taskThreadFactory;
    }

}
