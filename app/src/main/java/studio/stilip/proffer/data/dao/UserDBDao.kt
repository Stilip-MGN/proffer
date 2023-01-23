package studio.stilip.proffer.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import studio.stilip.proffer.data.entities.UserForDB

@Dao
interface UserDBDao {

    @Query("SELECT * FROM ${UserForDB.TABLE_NAME}")
    fun getUsers(): List<UserForDB>

    @Insert
    fun insertUser(user: UserForDB): Completable

    @Delete
    fun deleteUser(user: UserForDB): Completable
}