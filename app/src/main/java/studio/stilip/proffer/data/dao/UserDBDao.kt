package studio.stilip.proffer.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single
import studio.stilip.proffer.data.entities.UserForDB

@Dao
interface UserDBDao {

    @Query("SELECT * FROM ${UserForDB.TABLE_NAME}")
    fun getUsers(): Single<List<UserForDB>>

    @Insert
    fun insertUser(user: UserForDB): Completable

    @Query("DELETE  FROM ${UserForDB.TABLE_NAME}")
    fun deleteUser(): Completable
}