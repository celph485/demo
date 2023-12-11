package in.celph.atul;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
public class HeaderProcessor {

    @Value("${app.file.value.separator}")
    private String separator;

    @Value("${app.file.expected.headers}")
    private String expectedHeaders;

    public Map<String, Integer> process(final String headerRowInSnakeCase){
        log.info("Creating header map");
        String[] columns = expectedHeaders.split(separator);
        String[] fileHeaders = headerRowInSnakeCase.split(separator);

        return Arrays.stream(columns)
                .collect(Collectors.toMap(Function.identity(),
                        data -> ArrayUtils.indexOf(fileHeaders, data)));
    }
}
