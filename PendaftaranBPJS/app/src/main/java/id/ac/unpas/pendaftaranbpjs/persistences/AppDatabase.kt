package id.ac.unpas.pendaftaranbpjs.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.unpas.functionalcompose.model.PendaftaranBPJS
import id.ac.unpas.pendaftaranbpjs.model.PendaftaranBPJS

@Database(entities = [PendaftaranBPJS::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun PendaftaranBPJS(): PendaftaranBPJSDao
}