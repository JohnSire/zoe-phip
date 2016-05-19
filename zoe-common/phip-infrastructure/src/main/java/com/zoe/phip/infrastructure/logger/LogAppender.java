package com.zoe.phip.infrastructure.logger;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Priority;

/**
 * Created by zengjiyang on 2016/3/21.
 */
public final class LogAppender extends DailyRollingFileAppender {

    @Override
    public boolean isAsSevereAsThreshold(Priority priority) {
        //只判断是否相等，而不判断优先级 123
        return this.getThreshold().equals(priority);
    }
}