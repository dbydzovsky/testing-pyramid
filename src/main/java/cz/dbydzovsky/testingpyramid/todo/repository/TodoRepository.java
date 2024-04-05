package cz.dbydzovsky.testingpyramid.todo.repository;

import cz.dbydzovsky.testingpyramid.todo.model.TodoEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends PagingAndSortingRepository<TodoEntity,
        Long> {

}