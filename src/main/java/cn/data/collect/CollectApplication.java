package cn.data.collect;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
@MapperScan("cn.data.collect.dao")
public class CollectApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(CollectApplication.class, args);
	}
}