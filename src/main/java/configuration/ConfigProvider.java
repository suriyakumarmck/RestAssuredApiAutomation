package configuration;

import FrameworkCore.LoggerHook;
import org.apache.logging.log4j.Logger;
import org.json.simple.parser.ParseException;
import utilities.FileUtils;

import java.io.File;
import java.io.IOException;

public class ConfigProvider {

    private static Logger log = LoggerHook.log;

    public static Object getConfigObject(String configKey) throws IOException, ParseException {
        String projectDir = System.getProperty("user.dir");
        String pathSep = File.separator;
        String configFilePath = projectDir + pathSep + "src" + pathSep + "main" + pathSep + "resources" + pathSep + "environments.json";
        log.info("Configuration key: " + configKey);
        return FileUtils.getJsonFileAsJSONObject(configFilePath).get(configKey);
    }
}
