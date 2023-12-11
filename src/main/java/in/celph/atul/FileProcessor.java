package in.celph.atul;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Component
public class FileProcessor {

    @Value("${app.file.value.separator}")
    private String separator;

    @Autowired
    private FileValidator fileValidator;

    @Autowired
    private HeaderProcessor headerProcessor;

    @Autowired
    private ExternalService externalService;

    public void process(final String filePath) throws IOException {
        log.info("Initiating file processing...");
        fileValidator.validate(filePath);
        final String headerRowInSnakeCase = getHeaderRowInSnakeCaseFromFile(filePath);
        Map<String, Integer> headerMap = headerProcessor.process(headerRowInSnakeCase);
        Files
                .lines(Paths.get(filePath))
                .skip(1)
                .map(line -> createPayloadMap(headerMap, line))
                .forEach(data -> externalService.makePostCall(data));
        log.info("File processing completed successfully.");
    }

    private String getHeaderRowInSnakeCaseFromFile(final String filePath) throws IOException {
        return Files
                .lines(Paths.get(filePath))
                .findFirst()
                .orElseThrow(RuntimeException::new)
                .replaceAll("\\s+","_")
                .toLowerCase();
    }

    private Map<String, String> createPayloadMap(Map<String, Integer> headerMap, String line){
        final String[] data = line.split(separator);
        Map<String, String> dataMap = new HashMap<>();
        for(Map.Entry<String, Integer> entry: headerMap.entrySet()){
            dataMap.put(entry.getKey(), data[entry.getValue()]);
        }
        return dataMap;
    }
}
