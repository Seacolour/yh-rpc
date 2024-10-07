package com.hang.yhrpc.utils.config;


/**
 * 配置文件加载器
 */
public interface ConfigLoader {
    <T> T loadConfig(Class<T> tClass, String prefix, String filePath);
}

