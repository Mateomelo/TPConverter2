import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rates")
class Rate(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "code") val code: String,
    @ColumnInfo(name = "label") val label: String,
    @ColumnInfo(name = "value") val value: Double,
    @ColumnInfo(name = "flag") val flag: String
) {
    // vous pouvez également ajouter des méthodes ici si nécessaire
}
