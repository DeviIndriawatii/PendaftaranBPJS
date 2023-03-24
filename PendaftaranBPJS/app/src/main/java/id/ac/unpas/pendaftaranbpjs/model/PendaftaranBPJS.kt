package id.ac.unpas.pendaftaranbpjs.model


import androidx.room.Entity
import androidx.room.PrimaryKey

data class PendaftaranBPJS(
    @PrimaryKey val id: String,
    val nama: String,
    val nik: String,
    val telepon: String,
    val alamat: String
)
