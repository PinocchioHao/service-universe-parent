package com.example.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ProcessLogger {
    private static final Logger log = LoggerFactory.getLogger(ProcessLogger.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void log(String caseId, String activity, String resource) {
        try {
            Map<String, Object> event = new HashMap<>();
            event.put("caseId", caseId);
            event.put("activity", activity);
            event.put("timestamp", LocalDateTime.now().toString());
            event.put("resource", resource);

            String json = mapper.writeValueAsString(event);

            // 打印到日志文件（info 级别）
            log.info(json);

        } catch (Exception e) {
            log.error("日志写入失败", e);
        }
    }
}
