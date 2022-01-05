package com.arm.travel.controller;

import com.arm.travel.commons.anno.ArmRateLimiter;
import com.arm.travel.commons.enums.LimiterType;
import com.travel.common.resultex.domain.R;
import com.travel.common.resultex.enums.BizErrEnum;
import com.travel.common.resultex.enums.OrderErrEnum;
import com.travel.common.resultex.ex.BusinessException;
import com.travel.common.resultex.ex.OrderException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @date 2022/1/1 23:34
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    @ArmRateLimiter(limit = 5, timeout = 3, limitType = LimiterType.IP)
    public R hello() {
        return R.ok("i am ok");
    }

    @GetMapping("/v2")
    public R hello2() {
        try {
            int m = 2 /0;
        } catch (Exception e) {
            throw new BusinessException(BizErrEnum.USER_PWR_STATUS);
        }
        return R.ok("i am ok");
    }

    @GetMapping("/v3")
    public R hello3() {
        try {
            int m = 2 /0;
        } catch (Exception e) {
            throw new OrderException(OrderErrEnum.ORDER_ERROR_STATUS);
        }
        return R.ok("i am ok");
    }
}
