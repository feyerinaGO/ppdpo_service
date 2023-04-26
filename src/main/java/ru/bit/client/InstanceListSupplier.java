package ru.bit.client;

import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;


public class InstanceListSupplier implements ServiceInstanceListSupplier {
    private final String serviceId;

    public InstanceListSupplier(String serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public String getServiceId() {
        return serviceId;
    }

    @Override
    public Flux<List<ServiceInstance>> get() {
        return Flux.just(Arrays
                .asList(new DefaultServiceInstance(serviceId + "1", serviceId, "localhost", 8200, false),
                        new DefaultServiceInstance(serviceId + "2", serviceId, "localhost", 8201, false)));
    }
}
