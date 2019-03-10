package com.hekrxe.flow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

/**
 * @author tanhuayou on 2019/02/25
 */
@SpringBootApplication
public class MainTester implements CommandLineRunner {
    @Autowired
    private TypeBeanFactory flowBeanFactory;

    public static void main(String[] args) {
        SpringApplication.run(MainTester.class, args);
    }

    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
        long t1 = System.currentTimeMillis();
        List<FlowListener> all = flowBeanFactory.getAll(FlowListener.class);
        long t2 = System.currentTimeMillis();
        System.out.println(all.hashCode());
        long t3 = System.currentTimeMillis();
        all = flowBeanFactory.getAll(FlowListener.class);
        long t4 = System.currentTimeMillis();
        System.out.println(all.hashCode());
        all.forEach(fp -> {
            fp.onFlowEvent(null);
        });
        System.out.println((t2 - t1) + "\t" + (t4 - t3));
    }
}
