package com.lin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lin.entity.Log;
import org.springframework.stereotype.Service;

public interface ILogService {
    Integer insert(Log log);

   Page<Log> findLogPage(Page<Log> page);
}
