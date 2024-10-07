package com.hang.yhrpc.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;
import cn.hutool.setting.yaml.YamlUtil;

public class ConfigUtils {

    /**
     * 加载配置对象，支持 properties、yaml、yml 文件
     *
     * @param tClass 配置类
     * @param prefix 配置前缀
     * @param <T>    泛型
     * @return 配置对象
     */
    public static <T> T loadConfig(Class<T> tClass, String prefix) {
        return loadConfig(tClass, prefix, "");
    }

    /**
     * 加载配置对象，支持区分环境，同时支持 properties、yaml、yml 文件
     *
     * @param tClass      配置类
     * @param prefix      配置前缀
     * @param environment 环境
     * @param <T>         泛型
     * @return 配置对象
     */
    public static <T> T loadConfig(Class<T> tClass, String prefix, String environment) {
        StringBuilder configFileBuilder = new StringBuilder("application");
        if (StrUtil.isNotBlank(environment)) {
            configFileBuilder.append("-").append(environment);
        }

        String[] supportedFormats = {".properties", ".yaml", ".yml"};
        T config = null;

        for (String format : supportedFormats) {
            String configFileName = configFileBuilder.toString() + format;
            if (configFileExists(configFileName)) {
                System.out.println("Found configuration file: " + configFileName);
                if (format.equals(".properties")) {
                    Props props = new Props(configFileName);
                    System.out.println("Listening to file: " + configFileName);
                    config = props.toBean(tClass, prefix);
                } else if (format.equals(".yaml") || format.equals(".yml")) {
                    config = YamlUtil.loadByPath(configFileName, tClass);
                }
                if (config != null) {
                    break;
                }
            }
        }

        return config;
    }

    /**
     * 判断配置文件是否存在
     *
     * @param fileName 配置文件名
     * @return 是否存在
     */
    private static boolean configFileExists(String fileName) {
        return ConfigUtils.class.getClassLoader().getResource(fileName) != null;
    }
}

