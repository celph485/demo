package in.celph.atul;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class App implements CommandLineRunner {

    @Autowired
    private FileProcessor fileProcessor;

    public static void main(String... args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        final String filePath = "C:\\Users\\f3931\\src\\atul\\demo\\MOCK_DATA_2.csv";
        fileProcessor.process(filePath);
    }
}
