package com.arm.travel.controller;

import com.arm.travel.commons.anno.PugRateLimiter;
import com.travel.common.resultex.domain.R;
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
    @PugRateLimiter(limit = 5, timeout = 3)
    public R hello() {
        return R.success("i am ok");
    }

    @GetMapping("/v2")
    public R hello2() {
        int m = 2 /0;
        return R.success("i am ok");
    }
}
