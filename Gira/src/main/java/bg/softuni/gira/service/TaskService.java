package bg.softuni.gira.service;

import bg.softuni.gira.bindingModel.TaskDto;
import bg.softuni.gira.dao.TaskRepository;
import bg.softuni.gira.enumeration.ClassificationEnum;
import bg.softuni.gira.enumeration.ProgressEnum;
import bg.softuni.gira.model.Classification;
import bg.softuni.gira.model.Task;
import bg.softuni.gira.model.User;
import bg.softuni.gira.session.UserSession;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;
    private final ClassificationService classificationService;
    private final UserService userService;
    private final UserSession userSession;

    public TaskService(TaskRepository taskRepository, ModelMapper modelMapper, ClassificationService classificationService, UserService userService, UserSession userSession) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
        this.classificationService = classificationService;
        this.userService = userService;
        this.userSession = userSession;
    }

    public boolean addTask(TaskDto taskDto) {
        String name = taskDto.getName();
        String classification = taskDto.getClassification();

        if (taskRepository.findByName(name).isPresent()) {
            return false;
        }

        ClassificationEnum classificationEnum = ClassificationEnum.valueOf(classification);
        Classification classificationEntity = classificationService.findClassificationByName(classificationEnum);
        User userEntity = userService.findUserById(userSession.getId());
        Task taskEntity = modelMapper.map(taskDto, Task.class);
        taskEntity.setProgress(ProgressEnum.OPEN);
        taskEntity.setClassification(classificationEntity);
        taskEntity.setUser(userEntity);

        taskRepository.save(taskEntity);
        return true;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public void updateTask(Long taskId) {
        Task taskEntityById = taskRepository.getById(taskId);
        ProgressEnum taskProgress = taskEntityById.getProgress();

        if (taskProgress.name().toUpperCase(Locale.ROOT).equals("COMPLETED")) {
            taskRepository.delete(taskEntityById);
            return;
        }

        switch (taskProgress.name().toUpperCase(Locale.ROOT)) {
            case "OPEN" -> taskEntityById.setProgress(ProgressEnum.IN_PROGRESS);
            case "IN_PROGRESS" -> taskEntityById.setProgress(ProgressEnum.COMPLETED);
        }
        taskRepository.save(taskEntityById);
    }
}