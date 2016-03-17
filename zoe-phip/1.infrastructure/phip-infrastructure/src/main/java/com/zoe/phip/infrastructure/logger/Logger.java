package com.zoe.phip.infrastructure.logger;

/**
 * Created by 文彬 on 2016/3/17.
 */
public interface Logger {

    boolean isDebugEnabled();

    boolean isInfoEnabled();

    boolean isWarnEnabled();

    boolean isErrorEnabled();

    void debug(String message);

    void debug(String message,Object... objects);

    void debug(String message,Exception ex);

    void info(String message);

    void info(String message,Object... objects);

    void info(String message,Exception ex);

    void warn(String message);

    void warn(String message,Object... objects);

    void warn(String message,Exception ex);

    void error(String message);

    void error(String message,Object... objects);

    void error(String message,Exception ex);
}
