package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {


    public String port;
    public String m_l;
    public String cf_i;
    public String cf_add;

    @Autowired
    public EnvController(@Value("${PORT:NOT SET}")String port,
                         @Value("${MEMORY_LIMIT:NOT SET}")String m_l,
                         @Value("${CF_INSTANCE_INDEX:NOT SET}")String cf_i,
                         @Value("${CF_INSTANCE_ADDR:NOT SET}")String cf_add) {

        this.port = port;
        this.m_l = m_l;
        this.cf_i = cf_i;
        this.cf_add = cf_add;


    }

    @GetMapping("/env")
    public Map<String, String> getEnv() {
        Map<String, String> map = new HashMap<>();
        map.put("PORT",port);
        map.put("MEMORY_LIMIT",m_l);
        map.put("CF_INSTANCE_INDEX",cf_i);
        map.put("CF_INSTANCE_ADDR",cf_add);
        return map;
    }
}
