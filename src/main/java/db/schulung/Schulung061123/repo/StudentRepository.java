package db.schulung.Schulung061123.repo;

import db.schulung.Schulung061123.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByName(String name);

    List<Student> findByNameStartsWithIgnoreCase(String name);

    @Query(value = "select s from Student s where s.name like concat('%', :name)", nativeQuery = true)
    List<Student> findByNameEndsWith(String name);

    @Query("""
            select s from Student s
            where upper(s.name) like upper(concat(?1, '%')) 
            or upper(s.name) like upper(concat('%', ?1))
            """)
    List<Student> findByPrefixOrSuffix(String name);
}
