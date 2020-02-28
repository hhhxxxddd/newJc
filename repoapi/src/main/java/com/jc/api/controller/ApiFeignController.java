package com.jc.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiFeignController {

    /**\
     * 审核通过，修改相应的标志位
     * @param stockOutHeadId
     * @return
     */
    @PostMapping(value = "/jc/afterAudit")
    Boolean afterAudit(@RequestParam Long stockOutHeadId){
        return false;
    }
}
