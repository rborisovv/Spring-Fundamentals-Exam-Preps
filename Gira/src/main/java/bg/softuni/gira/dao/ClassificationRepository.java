package bg.softuni.gira.dao;

import bg.softuni.gira.enumeration.ClassificationEnum;
import bg.softuni.gira.model.Classification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassificationRepository extends JpaRepository<Classification, Long> {
    Classification findClassificationByClassificationName(ClassificationEnum classificationEnum);
}