package com.lin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lin.entity.Log;
import com.lin.mapper.LogMapper;
import com.lin.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class LogServiceImpl implements ILogService {
    @Autowired
    private  LogMapper logMapper;
    @Override
    public Integer insert(Log log) {
        return logMapper.insert(log);
    }

    @Override
    public Page<Log> findLogPage(Page<Log> page) {
        return logMapper.findLogPage(page);
    }
}
