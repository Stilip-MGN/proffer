package studio.stilip.proffer.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = UserForDB.TABLE_NAME)
class UserForDB(
    @PrimaryKey
    @ColumnInfo(name = "user_id")
    val id: Int,

    @ColumnInfo(name = "user_name")
    val name: String,

    @ColumnInfo(name = "user_login")
    val login: String,

    @ColumnInfo(name = "user_city")
    val city: String,

    @ColumnInfo(name = "user_phone")
    val phone: String = "",

    @ColumnInfo(name = "user_mail")
    val mail: String,

    ) {
    companion object {
        const val TABLE_NAME = "user"
    }
}