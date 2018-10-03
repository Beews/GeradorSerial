package serial.com.br.geradorserial.ui.activty;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.stella.format.CNPJFormatter;
import serial.com.br.geradorserial.R;
import serial.com.br.geradorserial.formatter.FormataTelefoneComDdd;
import serial.com.br.geradorserial.models.Empresa;
import serial.com.br.geradorserial.validator.ValidacaoCNPJ;
import serial.com.br.geradorserial.validator.ValidacaoEmail;
import serial.com.br.geradorserial.validator.ValidacaoPadrao;
import serial.com.br.geradorserial.validator.ValidacaoTelefone;
import serial.com.br.geradorserial.validator.Validador;

import static serial.com.br.geradorserial.ui.activty.EmpresaActivityConstantes.CHAVE_EMPRESA;
import static serial.com.br.geradorserial.ui.activty.EmpresaActivityConstantes.CHAVE_POSICAO;
import static serial.com.br.geradorserial.ui.activty.EmpresaActivityConstantes.POSICAO_INVALIDA;

public class FormularioActivity extends AppCompatActivity {


    private List<Validador> validadores = new ArrayList<>();
    private TextInputLayout textInputNomeCompleto;
    private TextInputLayout textInputCNPJ;
    private TextInputLayout textInputTelefone;
    private TextInputLayout textInputEmail;
    private TextInputLayout textInputContaSenha;

    private Empresa empresa = new Empresa();
    private ValidacaoCNPJ validadorCNPJ;
    private ValidacaoTelefone validadorTelefone;
    private int posicaoRecebida = POSICAO_INVALIDA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        setTitle("Inclusão de Cliente");

        inicializaCampos();

        Intent dadosRecebidos = getIntent();
        if (dadosRecebidos.hasExtra(CHAVE_EMPRESA)){
            setTitle("Alteração de Cliente'");
            this.empresa = dadosRecebidos.getParcelableExtra(CHAVE_EMPRESA);
            posicaoRecebida = dadosRecebidos.getIntExtra(CHAVE_POSICAO, POSICAO_INVALIDA);

            preencheCampos(this.empresa);
        }
    }

    private void preencheCampos(Empresa empresa) {
        textInputNomeCompleto.getEditText().setText(empresa.getNome());
        textInputCNPJ.getEditText().setText(empresa.getCnpj());
        textInputTelefone.getEditText().setText(empresa.getTelefone());
        textInputEmail.getEditText().setText(empresa.getEmail());
        textInputContaSenha.getEditText().setText(empresa.getConta());

        validadorCNPJ.adicionaFormatacao(textInputCNPJ.getEditText().getText().toString());
        validadorTelefone.adicionaFormatacao(textInputTelefone.getEditText().getText().toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario_empresa, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_formulario_empresa_cadastro:
                boolean formularioEstaValido = true;

                for (Validador validador : validadores) {
                    if (!validador.estaValido()){
                        formularioEstaValido = false;
                    }
                }

                if (formularioEstaValido){
                    criaNovaEmpresa();
                    retornaEmpresa();
                    finish();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void retornaEmpresa() {
        Intent resultadoDeRetorno = new Intent();
        resultadoDeRetorno.putExtra(CHAVE_EMPRESA, this.empresa);
        resultadoDeRetorno.putExtra(CHAVE_POSICAO, posicaoRecebida);
        setResult(Activity.RESULT_OK, resultadoDeRetorno);
    }

    private void criaNovaEmpresa() {
        this.empresa.setNome(textInputNomeCompleto.getEditText().getText().toString());
        this.empresa.setCnpj(new CNPJFormatter()
                .unformat(textInputCNPJ.getEditText().getText().toString()));
        this.empresa.setTelefone(new FormataTelefoneComDdd()
                .remove(textInputTelefone.getEditText().getText().toString()));
        this.empresa.setEmail(textInputEmail.getEditText().getText().toString());
        this.empresa.setConta(textInputContaSenha.getEditText().getText().toString());
    }

    private void inicializaCampos() {
        configuraCampoNomeCompleto();
        configuraCampoCnpj();
        configuraCampoTelefone();
        configuraCampoEmail();
        configuraCampoContaSenha();
    }

    private void configuraCampoContaSenha() {
        textInputContaSenha = findViewById(R.id.formulario_conta_senha);
        validacaoDeCampoPadrao(textInputContaSenha);
    }

    private void configuraCampoEmail() {
        textInputEmail = findViewById(R.id.formulario_email);
        EditText editTextEmail = textInputEmail.getEditText();
        final ValidacaoEmail validador = new ValidacaoEmail(textInputEmail);
        validadores.add(validador);

        editTextEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus){
                    validador.estaValido();
                }
            }
        });
    }

    private void configuraCampoTelefone() {
        textInputTelefone = findViewById(R.id.formulario_telefone);
        final EditText editTextTelefone = textInputTelefone.getEditText();
        validadorTelefone = new ValidacaoTelefone(textInputTelefone);
        final FormataTelefoneComDdd formatador = new FormataTelefoneComDdd();
        validadores.add(validadorTelefone);

        editTextTelefone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus){
                    String telefoneComDddSemFormato = formatador.remove(editTextTelefone.getText().toString());
                    editTextTelefone.setText(telefoneComDddSemFormato);
                }else{
                    validadorTelefone.estaValido();
                }
            }
        });
    }

    private void configuraCampoCnpj() {
        textInputCNPJ = findViewById(R.id.formulario_cnpj);
        final EditText editTextCNPJ = textInputCNPJ.getEditText();
        validadorCNPJ = new ValidacaoCNPJ(textInputCNPJ);
        validadores.add(validadorCNPJ);

        editTextCNPJ.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus){
                    removeFormatacaoCNPJ(editTextCNPJ, validadorCNPJ);
                }else {
                    validadorCNPJ.estaValido();
                }
            }
        });
    }

    private void removeFormatacaoCNPJ(EditText editTextCNPJ, ValidacaoCNPJ validador) {
        String cnpjSemFormatacao = validador.removeFormatacao(editTextCNPJ.getText().toString());
        editTextCNPJ.setText(cnpjSemFormatacao);
    }

    private void configuraCampoNomeCompleto() {
        textInputNomeCompleto = findViewById(R.id.formulario_nome_completo);
        validacaoDeCampoPadrao(textInputNomeCompleto);
    }

    private void validacaoDeCampoPadrao(final TextInputLayout textInputCampo) {
        final EditText campoEditText = textInputCampo.getEditText();
        final ValidacaoPadrao validador = new ValidacaoPadrao(textInputCampo);
        validadores.add(validador);

        campoEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus){
                    validador.estaValido();
                }
            }
        });
    }
}
