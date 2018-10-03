package serial.com.br.geradorserial.validator;

import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

public class ValidacaoPadrao implements Validador{

    private final TextInputLayout textInputCampo;
    private final EditText editTextCampo;

    public ValidacaoPadrao(TextInputLayout textInputCampo) {
        this.textInputCampo = textInputCampo;
        this.editTextCampo = this.textInputCampo.getEditText();
    }

    private boolean validaCampoObrigatorio() {
        String texto = editTextCampo.getText().toString();
        if (texto.isEmpty()){
            textInputCampo.setError("Campo obrigatório");
            return false;
        }
        return true;
    }

    @Override
    public boolean estaValido(){
        if (!validaCampoObrigatorio()) return false;
        removeErroDeCampo();
        return true;
    }

    private void removeErroDeCampo() {
        textInputCampo.setError(null);
        textInputCampo.setErrorEnabled(false);
    }

}
