package com.halcyon.ainocode.core.saver;

import cn.hutool.core.util.StrUtil;
import com.halcyon.ainocode.ai.model.HtmlCodeResult;
import com.halcyon.ainocode.exception.ErrorCode;
import com.halcyon.ainocode.exception.ThrowUtils;
import com.halcyon.ainocode.model.enums.CodeGenTypeEnum;

/**
 * HTML代码文件保存器
 */
public class HtmlCodeFileSaverTemplate extends CodeFileSaverTemplate<HtmlCodeResult> {

    @Override
    protected CodeGenTypeEnum getCodeType() {
        return CodeGenTypeEnum.HTML;
    }

    @Override
    protected void saveFiles(HtmlCodeResult result, String baseDirPath) {
        // 保存 HTML 文件
        writeToFile(baseDirPath, "index.html", result.getHtmlCode());
    }

    @Override
    protected void validateInput(HtmlCodeResult result) {
        super.validateInput(result);
        // HTML 代码不能为空
        ThrowUtils.throwIf(StrUtil.isBlank(result.getHtmlCode()), ErrorCode.SYSTEM_ERROR, "HTML代码内容不能为空");
    }
}
