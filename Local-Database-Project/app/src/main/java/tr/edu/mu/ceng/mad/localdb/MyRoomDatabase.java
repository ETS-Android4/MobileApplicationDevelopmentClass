package tr.edu.mu.ceng.mad.localdb;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Student.class}, version = 0)
public abstract class MyRoomDatabase extends RoomDatabase{
    public abstract StudentDAO studentDAO();
}
