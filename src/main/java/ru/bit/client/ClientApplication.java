package ru.bit.client;


import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import ru.bit.client.service.ServiceExchange;

@SpringBootApplication
public class ClientApplication {
    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(ClientApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        ServiceExchange serviceExchange = new ServiceExchange(ctx);

        while (true) {
            System.out.println(serviceExchange.exchange("RUB", "USD", 1000));
            Thread.sleep(5000);
        }
    }
}
