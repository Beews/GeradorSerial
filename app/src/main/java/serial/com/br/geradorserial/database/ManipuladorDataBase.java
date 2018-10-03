package serial.com.br.geradorserial.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import serial.com.br.geradorserial.dao.EmpresaDao;
import serial.com.br.geradorserial.models.Empresa;

@Database(entities = {Empresa.class}, version = 4, exportSchema = false)
@TypeConverters(ConversorDeData.class)
public abstract class ManipuladorDataBase extends RoomDatabase {

    public abstract EmpresaDao getEmpresaDao();
}
