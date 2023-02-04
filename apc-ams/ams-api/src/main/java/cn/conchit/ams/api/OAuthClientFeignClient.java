package cn.conchit.ams.api;

import cn.conchit.ams.dto.OAuth2ClientDTO;
import cn.conchit.common.result.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * @author： hetao
 */
@FeignClient(value = "ams-admin", contextId = "oauth-client")
public interface OAuthClientFeignClient {

    @GetMapping("/api/oauth-clients/getOAuth2ClientById")
    R<OAuth2ClientDTO> getOAuth2ClientById(@RequestParam String clientId);
}
