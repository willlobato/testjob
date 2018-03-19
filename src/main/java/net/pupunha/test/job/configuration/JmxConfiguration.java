package net.pupunha.test.job.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.MBeanExporter;

import java.util.HashMap;
import java.util.Map;

@Configuration
//@EnableMBeanExport
public class JmxConfiguration {

    @Autowired
    private ThreadMonitoring threadMonitoring;

    @Bean
    public MBeanExporter exporter()  {
        final MBeanExporter exporter = new MBeanExporter();
        exporter.setAutodetect(true);
        exporter.setExcludedBeans("dataSource");
//        exporter.registerManagedResource(threadMonitoring);
        Map<String, Object> map = new HashMap<>();
        map.put("bean:name=testBean5", threadMonitoring);
        exporter.setBeans(map);
        return exporter;
    }

}
