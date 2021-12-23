package tr.edu.mu.ceng.mad.localdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDAO {

    @Query("SELECT * from student")
    List<Student> getAll();

    @Query("SELECT * from student where studentId in (:ids)")
    List<Student> getStudentsById(int[] ids);

    @Query("SELECT * from student where first_name LIKE :name")
    List<Student> getStudentsByName(String name);

    @Insert
    void insert(Student student);
    @Insert
    void insert(Student ... student);

    @Delete
    void delete(Student student);

    @Update
    void update(Student student);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrUpdate(Student student);

}
