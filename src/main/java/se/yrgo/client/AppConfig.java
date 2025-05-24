package se.yrgo.client;

import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.*;

@Configuration
@ComponentScan(basePackages = "se.yrgo")
@EnableTransactionManagement
public class AppConfig {
}
