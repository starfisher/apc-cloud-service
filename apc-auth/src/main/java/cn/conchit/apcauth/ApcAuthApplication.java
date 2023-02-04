package cn.conchit.apcauth;

import cn.conchit.ams.api.OAuthClientFeignClient;
import cn.conchit.ams.api.UserFeignClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackageClasses = {UserFeignClient.class, OAuthClientFeignClient.class})
public class ApcAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApcAuthApplication.class, args);
    }

}
