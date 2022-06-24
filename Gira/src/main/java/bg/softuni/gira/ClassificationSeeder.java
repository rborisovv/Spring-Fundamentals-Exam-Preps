package bg.softuni.gira;

import bg.softuni.gira.dao.ClassificationRepository;
import bg.softuni.gira.enumeration.ClassificationEnum;
import bg.softuni.gira.model.Classification;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ClassificationSeeder implements CommandLineRunner {
    private final ClassificationRepository classificationRepository;

    public ClassificationSeeder(ClassificationRepository classificationRepository) {
        this.classificationRepository = classificationRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (classificationRepository.count() == 0) {
            Arrays.stream(ClassificationEnum.values())
                    .map(Classification::new)
                    .forEach(classificationRepository::save);
        }
    }
}