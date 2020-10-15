package com.github.jsoncat.core.springmvc.resolver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jsoncat.annotation.springmvc.RequestBody;
import com.github.jsoncat.core.springmvc.entity.MethodDetail;

import java.lang.reflect.Parameter;

/**
 * process @RequestBody annotation
 *
 * @author shuang.kou
 * @createTime 2020年09月28日 14:01:00
 **/
public class RequestBodyParameterResolver implements ParameterResolver {
    private static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
    }

    @Override
    public Object resolve(MethodDetail methodDetail, Parameter parameter) {
        Object param = null;
        RequestBody requestBody = parameter.getDeclaredAnnotation(RequestBody.class);
        if (requestBody != null) {
            try {
                param = OBJECT_MAPPER.readValue(methodDetail.getJson(), parameter.getType());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return param;
    }
}
