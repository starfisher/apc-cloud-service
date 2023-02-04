package cn.conchit.ams.api;

import cn.conchit.ams.dto.UserAuthDTO;
import cn.conchit.common.result.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
/**
 * @author： hetao
 */
@FeignClient(value = "ams-admin")
public interface UserFeignClient {

    @GetMapping("/api/v1/users/username/{username}")
    R<UserAuthDTO> getUserByUsername(@PathVariable String username);
}
