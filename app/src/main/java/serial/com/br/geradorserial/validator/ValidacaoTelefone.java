package serial.com.br.geradorserial.validator;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import serial.com.br.geradorserial.formatter.FormataTelefoneComDdd;

public class ValidacaoTelefone implements Validador {

    private final TextInputLayout textInputCampo;
    private final EditText editTextCampo;
    private final ValidacaoPadrao validacaoPadrao;
    private final FormataTelefoneComDdd formatador = new FormataTelefoneComDdd();

    public ValidacaoTelefone(TextInputLayout textInputCampo) {
        this.textInputCampo = textInputCampo;
        this.editTextCampo = textInputCampo.getEditText();
        this.validacaoPadrao = new ValidacaoPadrao(textInputCampo);
    }

    private boolean validaTelefoneComDezOuOnzeDigitos(String texto){
        int digitosTelefone = texto.length();
        if (digitosTelefone < 10 || digitosTelefone > 11){
            textInputCampo.setError("Telefone deve ter 10 ou 11 digitos ");
            return false;
        }
        return true;
    }

    @Override
    public boolean estaValido(){
        if (!validacaoPadrao.estaValido()) return false;
        String texto = getTelefone();
        String telefoneComDddSemFormatacao = new FormataTelefoneComDdd().remove(texto);

        if (!validaTelefoneComDezOuOnzeDigitos(telefoneComDddSemFormatacao)) return false;
        adicionaFormatacao(telefoneComDddSemFormatacao);

        return true;
    }

    public void adicionaFormatacao(String telefoneComDdd) {
/*        StringBuilder stringBuilder = new StringBuilder();
        int digitos = telefoneComDdd.length();
        for (int i = 0; i < digitos; i++) {
            if (i == 0) {
                stringBuilder.append("(");
            }

            char digito = telefoneComDdd.charAt(i);
            stringBuilder.append(digito);

            if (i == 1) {
                stringBuilder.append(") ");
            }
            if (digitos == 10 && i == 5 || digitos == 11 && i == 6){
                stringBuilder.append("-");
            }
        }

        String telefoneComDddFormatado = stringBuilder.toString();*/

        String telefoneComDddFormatado = formatador.formata(telefoneComDdd);
        editTextCampo.setText(telefoneComDddFormatado);
    }



    @NonNull
    private String getTelefone() {
        return editTextCampo.getText().toString();
    }
}
