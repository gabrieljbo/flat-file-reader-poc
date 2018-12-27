package dev.gabrieljbo.poc.processing;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = { "dev.gabrieljbo.poc" })
public class AppConfig {
}
