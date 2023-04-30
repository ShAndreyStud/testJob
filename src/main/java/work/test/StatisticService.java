package work.test;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticService {

    @Autowired
    private MeterRegistry registry;

    public void increaseCount(String name) {
        String counterName = "counter.name." + name;
        registry.counter(counterName).increment(1);
    }
}