package net.pupunha.test.job.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
@ManagedResource//(objectName="com.pramati.jmx.mbeans:name=SimpleCalculator)
public class ThreadMonitoring {

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @ManagedAttribute
    public int getActiveCount() {
        return threadPoolTaskExecutor.getActiveCount();
    }

    @ManagedAttribute
    public int getCorePoolSize() {
        return threadPoolTaskExecutor.getCorePoolSize();
    }

    @ManagedAttribute
    public int getMaxPoolSize() {
        return threadPoolTaskExecutor.getMaxPoolSize();
    }

    @ManagedAttribute
    public int getKeepAliveSeconds() {
        return threadPoolTaskExecutor.getKeepAliveSeconds();
    }

    @ManagedAttribute
    public int getPoolSize() {
        return threadPoolTaskExecutor.getPoolSize();
    }

}
