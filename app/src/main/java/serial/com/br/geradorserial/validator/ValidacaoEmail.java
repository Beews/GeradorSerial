package serial.com.br.geradorserial.validator;

import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

public class ValidacaoEmail implements Validador{

    private final TextInputLayout textInputCampo;
    private final EditText editTextCampo;
    private final ValidacaoPadrao validacaoPadrao;

    public ValidacaoEmail(TextInputLayout textInputCampo) {
        this.textInputCampo = textInputCampo;
        this.editTextCampo = this.textInputCampo.getEditText();
        this.validacaoPadrao = new ValidacaoPadrao(this.textInputCampo);
    }

    private boolean validaEmail(String texto){

        if (texto.matches(".+@.+\\..+")){
            return true;
        }

        textInputCampo.setError("E-mail inválido");
        return false;
    }

    @Override
    public boolean estaValido(){
        if (!validacaoPadrao.estaValido()) return false;
        String texto = editTextCampo.getText().toString();
        if (!validaEmail(texto)) return false;
        return true;
    }
}
