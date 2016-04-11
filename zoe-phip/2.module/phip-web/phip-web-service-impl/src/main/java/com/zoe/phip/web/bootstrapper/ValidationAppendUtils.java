package com.zoe.phip.web.bootstrapper;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.zoe.phip.infrastructure.exception.BusinessException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.*;

/**
 * Created by huangyinfu on 2016/4/11.
 */
public class ValidationAppendUtils {
    private static Validator validator =  Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> ValidationResult validateEntity(T obj) throws BusinessException {
        ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<T>> set = validator.validate(obj,Default.class);
        if( CollectionUtils.isNotEmpty(set) ){
            result.setHasErrors(true);
            Map<String,String> errorMsg = new HashMap<String,String>();
            String s ="";
            for(ConstraintViolation<T> cv : set){
                errorMsg.put(cv.getPropertyPath().toString(), cv.getMessage());
                s += "属性名："+cv.getPropertyPath().toString()+"的值："+cv.getMessage()+";";
            }
            result.setErrorMsg(errorMsg);
            throw new BusinessException(s,"实体保存非法！");
        }
        return result;
    }

    public static <T> ValidationResult validateProperty(T obj,String propertyName){
        ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<T>> set = validator.validateProperty(obj,propertyName,Default.class);
        if( CollectionUtils.isNotEmpty(set) ){
            result.setHasErrors(true);
            Map<String,String> errorMsg = new HashMap<String,String>();
            for(ConstraintViolation<T> cv : set){
                errorMsg.put(propertyName, cv.getMessage());
            }
            result.setErrorMsg(errorMsg);
        }
        return result;
    }
}
