package bg.softuni.gira.service;

import bg.softuni.gira.dao.ClassificationRepository;
import bg.softuni.gira.enumeration.ClassificationEnum;
import bg.softuni.gira.model.Classification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassificationService {
    private final ClassificationRepository classificationRepository;

    public ClassificationService(ClassificationRepository classificationRepository) {
        this.classificationRepository = classificationRepository;
    }

    public Classification findClassificationByName(ClassificationEnum classificationEnum) {
        return classificationRepository.findClassificationByClassificationName(classificationEnum);
    }

    public List<Classification> findAll() {
        return classificationRepository.findAll();
    }
}