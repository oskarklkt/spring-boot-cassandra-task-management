package mapper;

import org.example.taks_management.table.Task;
import org.example.taks_management.table.TaskByUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    Task toTask(TaskByUser taskByUser);
    TaskByUser toTaskByUser(Task task);
}
