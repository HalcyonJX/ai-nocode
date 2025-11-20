package com.halcyon.ainocode.ai;

import com.halcyon.ainocode.ai.model.HtmlCodeResult;
import com.halcyon.ainocode.ai.model.MultiFileCodeResult;
import com.halcyon.ainocode.model.enums.CodeGenTypeEnum;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AiCodeGeneratorServiceTest {

    @Resource
    private AiCodeGeneratorService aiCodeGeneratorService;

    @Test
    void generateHtmlCode() {
        HtmlCodeResult result = aiCodeGeneratorService.generateHtmlCode("做个超级简单的工作记录小工具，不超过50行代码");
        Assertions.assertNotNull(result);
    }

    @Test
    void generateMultiFileCode() {
        MultiFileCodeResult multiFileCode = aiCodeGeneratorService.generateMultiFileCode("做个超级简单的留言板，每个文件不超过50行代码");
        Assertions.assertNotNull(multiFileCode);
    }



}