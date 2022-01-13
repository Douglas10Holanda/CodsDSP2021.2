package UNIVERSIDADE.APLICACAO.UI;

import UNIVERSIDADE.APLICACAO.CONTROLLER.Metodos;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("UNIVERSIDADE.APLICACAO.ENTITY")
@EnableJpaRepositories("UNIVERSIDADE.APLICACAO.DAO")
@ComponentScan("UNIVERSIDADE.APLICACAO")
@SpringBootApplication
public class Main {
    public static void main(String[] args){
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Metodos.class);
        builder.headless(false).run(args);
    }
}