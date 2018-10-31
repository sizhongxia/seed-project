package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.SpProject;
import com.company.project.service.SpProjectService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by SiZhongXia on 2018/10/31.
*/
@RestController
@RequestMapping("/sp/project")
public class SpProjectController {
    @Resource
    private SpProjectService spProjectService;

    @PostMapping("/add")
    public Result<?> add(SpProject spProject) {
        spProjectService.save(spProject);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result<?> delete(@RequestParam Integer id) {
        spProjectService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result<?> update(SpProject spProject) {
        spProjectService.update(spProject);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result<?> detail(@RequestParam Integer id) {
        SpProject spProject = spProjectService.findById(id);
        return ResultGenerator.genSuccessResult(spProject);
    }

    @PostMapping("/list")
    public Result<?> list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SpProject> list = spProjectService.findAll();
        PageInfo<SpProject> pageInfo = new PageInfo<SpProject>(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
