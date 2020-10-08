package nsi.contractManagement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author Tao
 */
@EnableOpenApi
@SpringBootApplication
@MapperScan("nsi.contractManagement.mapper")
public class ContractManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContractManagementApplication.class, args);
    }

}
