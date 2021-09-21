package home.kirill.scheduledrestapi.prop;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class PropertiesReader {

    protected static Logger logger = Logger.getLogger(PropertiesReader.class);

    private static PropertiesConfiguration instance;

    protected static PropertiesConfiguration getInstance() {
        if (instance == null) {
            try {
                instance = new PropertiesConfiguration();
                instance.setFileName("application.properties");
                instance.setListDelimiter('~');
                instance.load();
                instance.setReloadingStrategy(new FileChangedReloadingStrategy());
            } catch (ConfigurationException e) {
                logger.error("Произошла ошибка при чтении параметров из application.properties", e);
                throw new RuntimeException("Произошла ошибка при чтении параметров из application.properties", e);
            }
        }
        return instance;
    }

    public static String getString(String key, String defaultValue) throws Exception {
        PropertiesConfiguration instance = getInstance();
        if (checkForDoubleKey(key, instance)) return instance.getString(key, defaultValue);
        logger.error("Произошла ошибка при чтении параметров из application.properties: содержится более одного значения " +
                "для ключа \"" + key + "\"");
        throw new Exception("Найдено более двух значений для ключа \"" + key + "\"");
    }

    public static String getString(String key) throws Exception {
        PropertiesConfiguration instance = getInstance();
        if (checkForDoubleKey(key, instance)) return instance.getString(key, null);
        logger.error("Произошла ошибка при чтении параметров из application.properties: содержится более одного значения " +
                "для ключа \"" + key + "\"");
        throw new Exception("Найдено более двух значений для ключа \"" + key + "\"");
    }

    public static Integer getInteger(String key, Integer defaultValue) throws Exception {
        String value = getString(key);
        if (StringUtils.isNotBlank(value) && StringUtils.isNumeric(value)) {
            return Integer.valueOf(value);
        }
        return defaultValue;
    }

    public static Long getLong(String key, Long defaultValue) throws Exception {
        String value = getString(key);
        if (StringUtils.isNotBlank(value) && StringUtils.isNumeric(value)) {
            return Long.valueOf(value);
        }
        return defaultValue;
    }

    public static boolean checkForDoubleKey(String key, PropertiesConfiguration instance){
        String[] keys = instance.getStringArray(key);
        return keys.length <= 1;
    }
}
