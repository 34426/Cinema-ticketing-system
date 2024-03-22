package com.movie.web.controller.system;

import com.movie.common.response.ResponseResult;
import com.movie.common.utils.ApplicationContextUtils;
import com.movie.system.domin.SysSession;
import com.movie.system.domin.vo.SysSessionVo;
import com.movie.system.service.impl.SysSessionServiceImpl;
import com.movie.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class SysSessionController extends BaseController {

    @Autowired
    private SysSessionServiceImpl sysSessionService;

    /**
     * 根据vo中的条件查询所有场次，如果在前台购票部分注意设置pageSize=100或者其他大一些的数
     *
     * @param sysSessionVo
     * @return
     */
    @GetMapping("/sysSession")
    public ResponseResult findByVo(SysSessionVo sysSessionVo) {
        startPage();
        List<SysSession> list = sysSessionService.findByVo(sysSessionVo);
        return getResult(list);
    }

    @GetMapping("/sysSession/find/{id}")
    public ResponseResult findSessionById(@PathVariable Long id) {
        ApplicationContextUtils.getBean("cancelTimeoutBill");
        return getResult(sysSessionService.findSessionById(id));
    }


    @GetMapping("/sysSession/isAbleEdit")
    public ResponseResult findSessionByMovieIdOrHallId(SysSession sysSession) {
        return getResult(sysSessionService.findSessionByMovieIdOrHallId(sysSession));
    }

    @PostMapping("/sysSession")
    public ResponseResult addSession(@RequestBody SysSession sysSession) {
        return getResult(sysSessionService.addSession(sysSession));
    }

    @PutMapping("/sysSession")
    public ResponseResult updateSession(@RequestBody SysSession sysSession) {
        return getResult(sysSessionService.updateSession(sysSession));
    }

    @DeleteMapping("/sysSession/{ids}")
    public ResponseResult deleteSession(@PathVariable Long[] ids) {
        return getResult(sysSessionService.deleteSession(ids));
    }

}
