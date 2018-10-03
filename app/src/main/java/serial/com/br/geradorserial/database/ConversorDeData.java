package serial.com.br.geradorserial.database;

import android.arch.persistence.room.TypeConverter;

import java.sql.Date;
import java.text.ParseException;
import java.util.Calendar;

public class ConversorDeData {

    @TypeConverter
    public static Long getSqlDate(Calendar calendar){
        if (calendar != null){
            return calendar.getTime().getTime();
        }

        return null;
    }

    @TypeConverter
    public static Calendar getLocalDate(Long tempoEmMilisegundos){
        Calendar calendar = Calendar.getInstance();
        try{
            calendar.setTime(new Date(tempoEmMilisegundos));
            return calendar;
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return null;
    }
}
