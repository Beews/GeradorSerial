package serial.com.br.geradorserial.validator;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.widget.EditText;

import br.com.caelum.stella.format.CNPJFormatter;
import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.InvalidStateException;

public class ValidacaoCNPJ implements Validador{

    private final TextInputLayout textInputCampo;
    private final EditText editTextCampo;
    private final ValidacaoPadrao validacaoPadrao;
    private final CNPJFormatter formatador = new CNPJFormatter();


    public ValidacaoCNPJ(TextInputLayout textInputCampo) {
        this.textInputCampo = textInputCampo;
        this.editTextCampo = textInputCampo.getEditText();
        this.validacaoPadrao = new ValidacaoPadrao(textInputCampo);
    }

    private boolean validaCNPJComQuatorzeDigito(String texto) {
        if (texto.length() != 14){
            textInputCampo.setError("O CNPJ precisa ter 14 dígitos");
            return false;
        }
        return true;
    }

    private boolean validaCalculoCNPJ(String texto) {
        try {
            CNPJValidator cnpjValidator = new CNPJValidator();
            cnpjValidator.assertValid(texto);
        }catch (InvalidStateException e){
            textInputCampo.setError("CNPJ inválido");
            return false;
        }
        return true;
    }

    @Override
    public boolean estaValido(){
        if (!validacaoPadrao.estaValido()) return false;

        String cnpj = getCNPJ();
        String cnpjSemFormatacao = removeFormatacao(cnpj);

        if (!validaCNPJComQuatorzeDigito(cnpjSemFormatacao)) return false;
        if (!validaCalculoCNPJ(cnpjSemFormatacao)) return false;
        adicionaFormatacao(cnpjSemFormatacao);

        return true;
    }

    public void adicionaFormatacao(String texto) {
        String cnpjFormatado = formatador.format(texto);
        editTextCampo.setText(cnpjFormatado);
    }

    public String removeFormatacao(String textoCNPJ) {
        try {
            String cnpjSemFormato = new CNPJFormatter().unformat(textoCNPJ);

            return cnpjSemFormato;
        }catch (IllegalArgumentException e){
            Log.e("Erro formatação CNPJ", e.getMessage());
        }

        return textoCNPJ;
    }

    @NonNull
    private String getCNPJ() {
        return editTextCampo.getText().toString();
    }
}
