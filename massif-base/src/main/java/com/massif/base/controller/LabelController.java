package com.massif.base.controller;

import com.massif.base.entity.Label;
import com.massif.base.service.ILabelService;
import com.massif.common.entity.PageResult;
import com.massif.common.entity.Result;
import com.massif.common.entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("标签管理")
public class LabelController {

    private ILabelService labelService;

    @Autowired
    public void setLabelService(ILabelService labelService) {
        this.labelService = labelService;
    }

    @ApiOperation("分页列表")
    @GetMapping("/pageList")
    public Result<PageResult<Label>> getLabelPage(Label label,
                                                  @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                                                  @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize) {

        return new Result<PageResult<Label>>(true, StatusCode.SUCCESS, labelService.getLabelList(label, pageNum, pageSize));
    }

}
