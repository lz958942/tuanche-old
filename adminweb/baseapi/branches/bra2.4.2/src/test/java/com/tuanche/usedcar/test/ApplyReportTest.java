package com.tuanche.usedcar.test;


import com.tuanche.baseapi.service.HttpsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:/spring/baseapi-application-common.xml"})
public class ApplyReportTest {


    @Resource(name = "httpsService")
    private HttpsService httpsService;


    @Test
    public void testapplyInfoReport() {
        try {
            final String url = "http://localhost:8080/stores/applyInfo.shtml";

            String out = httpsService.sendPost(url, "");
            System.out.println("响应消息：" + out);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

}
