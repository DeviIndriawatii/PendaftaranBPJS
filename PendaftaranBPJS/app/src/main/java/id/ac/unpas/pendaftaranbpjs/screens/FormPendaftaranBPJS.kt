package id.ac.unpas.pendaftaranbpjs.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.ac.unpas.pendaftaranbpjs.model.PendaftaranBPJS
import id.ac.unpas.pendaftaranbpjs.persistences.PendaftaranBPJSDao
import id.ac.unpas.pendaftaranbpjs.ui.theme.Purple700
import id.ac.unpas.pendaftaranbpjs.ui.theme.Teal200
import com.benasher44.uuid.uuid4

@Composable
fun FormPendaftaranBPJS(PendaftaranBPJSDao: (PendaftaranBPJSDao) -> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val nama = remember { mutableStateOf(TextFieldValue("")) }
    val nik = remember { mutableStateOf(TextFieldValue("")) }
    val telepon = remember { mutableStateOf(TextFieldValue("")) }
    val alamat = remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            label = { Text(text = "Nama") },
            value = nama.value,
            onValueChange = {
                nama.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            placeholder = { Text(text = "...") }
        )
        OutlinedTextField(
            label = { Text(text = "NIK") },
            value = nik.value,
            onValueChange = {
                nik.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                capitalization =
                KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text
            ),
            placeholder = { Text(text = "...") }
        )
        OutlinedTextField(
            label = { Text(text = "Telepon") },
            value = telepon.value,
            onValueChange = {
                telepon.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType =
                KeyboardType.Decimal
            ),
            placeholder = { Text(text = "...") }
        )
        OutlinedTextField(
            label = { Text(text = "Alamat") },
            value = alamat.value,
            onValueChange = {
                alamat.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType =
                KeyboardType.Decimal
            ),
            placeholder = { Text(text = "...") }
        )
        val loginButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Purple700,
            contentColor = Teal200
        )
        val resetButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Teal200,
            contentColor = Purple700
        )
        Row(modifier = Modifier.padding(4.dp).fillMaxWidth()) {
            Button(modifier = Modifier.weight(5f), onClick = {
                val namaValue = nama.value.text
                val nikValue = nik.value.text
                val teleponValue = telepon.value.text
                val alamatValue = alamat.value.text

                if (namaValue.isBlank() || nikValue.isBlank()) {
                    Toast.makeText(context, "Wajib diisi", Toast.LENGTH_SHORT)
                        .show()
                    return@Button
                }

                if (teleponValue.isBlank() && alamatValue.isBlank()) {
                    Toast.makeText(context, "Wajib diisi", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                val item =
                    PendaftaranBPJS(namaValue, nikValue, teleponValue, alamatValue)onSimpan(item)
                nama.value = TextFieldValue("")
                nik.value = TextFieldValue("")
                telepon.value = TextFieldValue("")
                alamat.value = TextFieldValue("")
            }, colors = loginButtonColors) {
                Text(
                    text = "Simpan",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
            Button(modifier = Modifier.weight(5f), onClick = {
                val id = uuid4().toString()
                val item = PendaftaranBPJS(id, nama.value.text, nik.value.text,
                    telepon.value.text, alamat.value.text)
                scope.launch {
                    PendaftaranBPJSDao.insertAll(item)
                }
                nama.value = TextFieldValue("")
                nik.value = TextFieldValue("")
                telepon.value = TextFieldValue("")
                alamat.value = TextFieldValue("")
            }, colors = resetButtonColors) {
                Text(
                    text = "Reset",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}