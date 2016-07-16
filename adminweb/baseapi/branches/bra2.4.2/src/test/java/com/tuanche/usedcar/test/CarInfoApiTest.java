package com.tuanche.usedcar.test;


import com.tuanche.baseapi.service.HttpsService;
import com.tuanche.framework.base.util.HttpClient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:/spring/baseapi-application-common.xml"})
public class CarInfoApiTest {


    @Resource(name = "httpsService")
    private HttpsService httpsService;


    @Test
    public void testapplyInfoReport() {
        try {
            final String url = "http://localhost:8080/baseapi2.4.2/carinfo/getCarInfoByCid";
            Map map = new HashMap<String,String>();
            map.put("cid", "435");
            String out =  HttpClient.post(url, map);
            System.out.println("响应消息：" + out);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

}
