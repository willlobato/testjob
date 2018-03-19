package net.pupunha.test.job.configuration;

import lombok.extern.slf4j.Slf4j;
import net.pupunha.test.job.domain.Songs;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Configuration
@Slf4j
public class SongJobConfiguration {

    private static int CHUNK = 1000;
    public Integer count = 0;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

//    @Autowired
//    public TaskExecutor taskExecutor;

    @Autowired
    public TaskExecutor threadPoolTaskExecutor;


    @Bean
    public Job job(Step stepInitial) throws Exception {
        return jobBuilderFactory.get("job")
                .incrementer(new RunIdIncrementer())
                .start(stepInitial)
                .build();
    }

    @Bean
    public Step stepInitial(StepBuilderFactory stepBuilderFactory,
                            ItemReader<Songs> reader,
                            ItemWriter<Songs> writer) {
        return stepBuilderFactory.get("stepInitial")
                .<Songs, Songs>chunk(CHUNK)
                .reader(reader)
                .writer(writer)
                .taskExecutor(threadPoolTaskExecutor)
                .throttleLimit(20)
                .build();
    }

    @Bean
    @StepScope
    public JpaPagingItemReader<Songs> reader() throws Exception {
        JpaPagingItemReader<Songs> reader = new JpaPagingItemReader<>();
        reader.setQueryString("select s from Songs s");
        reader.setEntityManagerFactory(entityManagerFactory);
        reader.setPageSize(CHUNK);
        reader.afterPropertiesSet();
        reader.setSaveState(true);
        return reader;
    }

    @Bean
    @StepScope
    public ItemWriter<Songs> writer() {
        ItemWriter writer = new ItemWriter<Songs>() {
            @Override
            public void write(List<? extends Songs> list) throws Exception {
//                System.out.println(list.size());
                log.info("{} - {}",list.size(), ++count);
                list = null;
            }
        };
        return writer;
    }

}
