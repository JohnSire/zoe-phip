package com.zoe.phip.infrastructure.util;

import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiuyungen on 2016/2/26.
 */
public final class ResourceUtil {

    private static final ResourcePatternResolver ResourcePatternResolver = new PathMatchingResourcePatternResolver();

    public static org.springframework.core.io.Resource[] getResource(String basePackage, String pattern) throws IOException {
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + ClassUtils.convertClassNameToResourcePath(basePackage) + "/" + pattern;
        org.springframework.core.io.Resource[] resources = ResourcePatternResolver.getResources(packageSearchPath);
        return resources;
    }

    public static List<Resource> searchResource(String basePackage, String pattern) {
        ArrayList<Resource> resourceList = new ArrayList<Resource>();
        try {
            org.springframework.core.io.Resource[] resources = getResource(basePackage, pattern);
            for (org.springframework.core.io.Resource resource : resources) {
                resourceList.add(new Resource(resource.toString(), StringUtil.inputStream2String(resource.getInputStream())));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resourceList;
    }

    public static String loadEmbeddedResource(String path) {
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        try {
            if (stream != null)
                return StringUtil.inputStream2String(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
