package id.ac.unpas.pendaftaranbpjs.persistences

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.unpas.functionalcompose.model.PendaftaranBPJS
import id.ac.unpas.pendaftaranbpjs.model.PendaftaranBPJS
import id.ac.unpas.pendaftaranbpjs.model.PendaftaranBPJS

@Dao
interface PendaftaranBPJSDao {
    @Query("SELECT * FROM PendaftaranBPJS")
    fun loadAll(): LiveData<List<PendaftaranBPJS>>
    @Query("SELECT * FROM PendaftaranBPJS WHERE id = :id")
    fun find(id: String): PendaftaranBPJS?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: PendaftaranBPJS)
    @Delete
    fun delete(item: PendaftaranBPJS)
}