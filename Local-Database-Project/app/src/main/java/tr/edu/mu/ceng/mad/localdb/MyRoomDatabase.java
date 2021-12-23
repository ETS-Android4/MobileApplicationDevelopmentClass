package tr.edu.mu.ceng.mad.localdb;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Student.class}, version = 1)
public abstract class MyRoomDatabase extends RoomDatabase{
    public abstract StudentDAO studentDAO();
}
