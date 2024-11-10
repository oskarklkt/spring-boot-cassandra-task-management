package org.example.taks_management.repository;

import org.example.taks_management.table.Task;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends CassandraRepository<Task, UUID> {
    Optional<Task> findByTaskId(UUID taskId);
    void deleteByTaskId(UUID taskId);
}
