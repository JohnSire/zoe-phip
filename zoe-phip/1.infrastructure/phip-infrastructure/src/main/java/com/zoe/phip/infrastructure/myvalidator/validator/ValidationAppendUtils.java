package com.zoe.phip.infrastructure.myvalidator.validator;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.zoe.phip.infrastructure.bean.BeanFactory;
import com.zoe.phip.infrastructure.exception.BusinessException;
import org.apache.log4j.Logger;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by huangyinfu on 2016/4/11.
 */
public class ValidationAppendUtils {
    //private static Validator validator =  Validation.buildDefaultValidatorFactory().getValidator();
    private static Validator validator = ((ValidatorFactory) BeanFactory.getBean("validator")).getValidator();

    public static final Logger log = Logger.getLogger(ValidationAppendUtils.class);

    /**
     * 抽出公共方法
     *
     * @param obj
     * @param <T>
     * @return
     * @throws BusinessException
     */
    public static <T> ValidationResult validateEntity(T obj) throws BusinessException {
        ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
        if (CollectionUtils.isNotEmpty(set)) {
            result.setHasErrors(true);
            Map<String, String> errorMsg = new HashMap<String, String>();
            //    String s ="";
            StringBuffer s = new StringBuffer();
            for (ConstraintViolation<T> cv : set) {
                errorMsg.put(cv.getPropertyPath().toString(), cv.getMessage());
                //  s += "属性名："+cv.getPropertyPath().toString()+"的值："+cv.getMessage()+";";
                s.append(cv.getMessage() + ";");
            }
            result.setErrorMsg(errorMsg);

            log.debug("--------------ValidationResult开始------------: ");
            log.debug(s.toString());
            log.debug("-----------------ValidationResult结束-----------");

            result.setErrorMessage(s.toString());
        }
        return result;
    }

    public static <T> ValidationResult validateProperty(T obj, String propertyName) {
        ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<T>> set = validator.validateProperty(obj, propertyName, Default.class);
        if (CollectionUtils.isNotEmpty(set)) {
            result.setHasErrors(true);
            Map<String, String> errorMsg = new HashMap<String, String>();
            for (ConstraintViolation<T> cv : set) {
                errorMsg.put(propertyName, cv.getMessage());
            }
            result.setErrorMsg(errorMsg);
        }
        return result;
    }


    public static <T> ValidationResult validateEntity(T obj, Class<?>... paramVarArgs) throws BusinessException {
        ValidationResult result = new ValidationResult();

        Set<ConstraintViolation<T>> set = validator.validate(obj, paramVarArgs);
        if (CollectionUtils.isNotEmpty(set)) {
            result.setHasErrors(true);
            Map<String, String> errorMsg = new HashMap<String, String>();
            //    String s ="";
            StringBuffer s = new StringBuffer();
            for (ConstraintViolation<T> cv : set) {
                errorMsg.put(cv.getPropertyPath().toString(), cv.getMessage());
                //  s += "属性名："+cv.getPropertyPath().toString()+"的值："+cv.getMessage()+";";
                s.append(cv.getMessage() + ";");
            }
            result.setErrorMsg(errorMsg);

            log.debug("--------------ValidationResult开始------------: ");
            log.debug(s.toString());
            log.debug("-----------------ValidationResult结束-----------");

            result.setErrorMessage(s.toString());
        }
        return result;
    }
}
