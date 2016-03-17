package com.zoe.phip.infrastructure.logger;

import org.slf4j.LoggerFactory;

/**
 * Created by 文彬 on 2016/3/17.
 */
public final class LoggerImpl implements Logger {

    private final org.slf4j.Logger debugLogger;
    private final org.slf4j.Logger warnLogger;
    private final org.slf4j.Logger infoLogger;
    private final org.slf4j.Logger errorLogger;

    public LoggerImpl() {
        debugLogger = LoggerFactory.getLogger("com.zoe.phip.debug");
        warnLogger = LoggerFactory.getLogger("com.zoe.phip.warn");
        infoLogger = LoggerFactory.getLogger("com.zoe.phip.info");
        errorLogger = LoggerFactory.getLogger("com.zoe.phip.error");
    }


    @Override
    public boolean isDebugEnabled() {
        if (debugLogger == null)
            return false;
        return debugLogger.isDebugEnabled();
    }

    @Override
    public boolean isInfoEnabled() {
        if (infoLogger == null)
            return false;
        return infoLogger.isInfoEnabled();
    }

    @Override
    public boolean isWarnEnabled() {
        if (warnLogger == null)
            return false;
        return warnLogger.isWarnEnabled();
    }

    @Override
    public boolean isErrorEnabled() {
        if (errorLogger == null)
            return false;
        return errorLogger.isErrorEnabled();
    }

    @Override
    public void debug(String message) {
        if (isDebugEnabled())
            debugLogger.debug(message);
    }

    @Override
    public void debug(String message, Object... objects) {
        if (isDebugEnabled())
            debugLogger.debug(message, objects);
    }

    @Override
    public void debug(String message, Exception ex) {
        if (isDebugEnabled())
            debugLogger.debug(message, ex);
    }

    @Override
    public void info(String message) {
        if (isInfoEnabled())
            infoLogger.info(message);
    }

    @Override
    public void info(String message, Object... objects) {
        if (isInfoEnabled())
            infoLogger.info(message, objects);
    }

    @Override
    public void info(String message, Exception ex) {
        if (isInfoEnabled())
            infoLogger.info(message, ex);
    }

    @Override
    public void warn(String message) {
        if (isWarnEnabled())
            warnLogger.warn(message);
    }

    @Override
    public void warn(String message, Object... objects) {
        if (isWarnEnabled())
            warnLogger.warn(message, objects);
    }

    @Override
    public void warn(String message, Exception ex) {
        if (isWarnEnabled())
            warnLogger.warn(message, ex);
    }

    @Override
    public void error(String message) {
        if (isErrorEnabled())
            errorLogger.error(message);
    }

    @Override
    public void error(String message, Object... objects) {
        if (isErrorEnabled())
            errorLogger.error(message, objects);
    }

    @Override
    public void error(String message, Exception ex) {
        if (isErrorEnabled())
            errorLogger.error(message, ex);
    }
}
