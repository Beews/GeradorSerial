package serial.com.br.geradorserial.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

public class DataPickerFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        DateTime dateTime = new DateTime();

        int dayOfMonth = dateTime.getDayOfMonth();
        int monthOfYear = dateTime.getMonthOfYear() - 1;
        int year = dateTime.getYear();

        return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity(),
                year, monthOfYear, dayOfMonth);
    }


}
