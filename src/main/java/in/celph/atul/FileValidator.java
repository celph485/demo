package in.celph.atul;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FileValidator {

    /**
     * TODO: implement file validation logic here and in case of illegal file throw unchecked exception to halt
     *
     * @param filePath
     */
    public void validate(final String filePath){
        log.info("Initiating file validation...");


        log.info("File validation completed successfully.");
    }
}
