package studio.stilip.proffer.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import studio.stilip.proffer.data.dao.UserDBDao
import studio.stilip.proffer.data.entities.UserForDB

@Database(
    entities = [
        UserForDB::class,
    ],
    version = 2
)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDBDao(): UserDBDao
}