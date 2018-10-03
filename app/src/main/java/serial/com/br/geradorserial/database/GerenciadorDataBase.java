package serial.com.br.geradorserial.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

public class GerenciadorDataBase {

    public ManipuladorDataBase gerarDataBase(Context context){

        ManipuladorDataBase dataBase = Room.databaseBuilder(context, ManipuladorDataBase.class, "GeradorSerial")
                   .allowMainThreadQueries()
                   .addMigrations(tresParaQuarto())
                   .build();
           return dataBase;
    }

    private Migration tresParaQuarto() {
        return new Migration(3, 4) {
            @Override
            public void migrate(@NonNull SupportSQLiteDatabase database) {
                String query = "ALTER TABLE Empresa ADD COLUMN vencimento INTEGER";
                database.execSQL(query);
            }
        };
    }
}
