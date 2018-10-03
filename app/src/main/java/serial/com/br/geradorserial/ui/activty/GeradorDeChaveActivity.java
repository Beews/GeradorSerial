package serial.com.br.geradorserial.ui.activty;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.caelum.stella.format.CNPJFormatter;
import serial.com.br.geradorserial.R;
import serial.com.br.geradorserial.criptografia.logica.businessRule.Discriptografia;
import serial.com.br.geradorserial.criptografia.logica.security.Criptografia;
import serial.com.br.geradorserial.database.GerenciadorDataBase;
import serial.com.br.geradorserial.dialog.DataPickerFragment;
import serial.com.br.geradorserial.models.Empresa;

import static serial.com.br.geradorserial.ui.activty.EmpresaActivityConstantes.CHAVE_POSICAO;
import static serial.com.br.geradorserial.ui.activty.EmpresaActivityConstantes.CHAVE_SERIAL;
import static serial.com.br.geradorserial.ui.activty.EmpresaActivityConstantes.POSICAO_INVALIDA;

public class GeradorDeChaveActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {


    private TextView textNomeCliente;
    private TextView textVencimentoEm;
    private TextInputLayout campoDataLiberacao;
    private Button buttonGerarSerial;
    private FloatingActionButton buttonEnviarSerial;
    private TextInputLayout campoSerialValido;
    private TextView textDiaParaVencer;
    private TextView textDataVencimentoExtenso;
    private Empresa empresa;
    private int posicaoRecebida = POSICAO_INVALIDA;
    private EditText textDataLiberacao;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd/MM/yyyy");
    private EditText textSerialValido;
    private String serialValido = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerador_de_chave);
        setTitle("Gerar Serial");

        inicializaCampos();

        Intent dadosRecebidos = getIntent();
        if (dadosRecebidos.hasExtra(CHAVE_SERIAL)){
            this.empresa = dadosRecebidos.getParcelableExtra(CHAVE_SERIAL);
            posicaoRecebida = dadosRecebidos.getIntExtra(CHAVE_POSICAO, POSICAO_INVALIDA);
            preencheConfiguraCampos();
        }
    }

    private void preencheConfiguraCampos() {
        textNomeCliente.setText(empresa.getNome());
        if (empresa.getVencimento() != null){
            Calendar calendar = empresa.getVencimento();
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

            textVencimentoEm.setText(formatador.format(calendar.getTime()));
        }
        ocultaDiasVencimento();
    }

    private void inicializaCampos() {
        textNomeCliente = findViewById(R.id.gerador_de_chave_text_cliente);
        textVencimentoEm = findViewById(R.id.gerador_de_chave_text_vencimento);
        campoDataLiberacao = findViewById(R.id.gerador_de_chave_input_data);
        textDataLiberacao = campoDataLiberacao.getEditText();
        buttonGerarSerial = findViewById(R.id.gerador_de_chave_button_gerar);
        buttonEnviarSerial = findViewById(R.id.gerador_de_chave_button_enviar);
        campoSerialValido = findViewById(R.id.gerador_de_chave_serial_valido);
        textSerialValido = campoSerialValido.getEditText();
        textDiaParaVencer = findViewById(R.id.gerador_de_chave_dias_para_vencer);
        textDataVencimentoExtenso = findViewById(R.id.gerador_de_chave_vencimento_extenso);

        configuraCampoDataLiberacao();
        configuraButtonGerarSerial();
        configuraButtonEnviarSerial();
    }

    private void configuraButtonEnviarSerial() {
        buttonEnviarSerial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentEnviarSerial = new Intent();
                intentEnviarSerial.setAction(Intent.ACTION_SEND);
                intentEnviarSerial.putExtra(Intent.EXTRA_TEXT, serialValido);
                intentEnviarSerial.setType("text/plain");
                intentEnviarSerial.setPackage("com.whatsapp");
                startActivity(intentEnviarSerial);

                LocalDate dataVencimento = dateTimeFormatter.parseLocalDate(textDataLiberacao.getText().toString());
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date(dataVencimento.toDateTimeAtStartOfDay().getMillis()));

                empresa.setVencimento(calendar);
                new GerenciadorDataBase().gerarDataBase(GeradorDeChaveActivity.this)
                        .getEmpresaDao().alterar(empresa);
                finish();
            }
        });
    }

    private void configuraButtonGerarSerial() {
        buttonGerarSerial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String user = empresa.getConta();
                    String cnpj = new CNPJFormatter().format(empresa.getCnpj());
                    LocalDate data = dateTimeFormatter.parseLocalDate(textDataLiberacao.getText().toString());

                    serialValido = new Criptografia(user, cnpj, data).getSerial();
                    textSerialValido.setText(serialValido);
                    desocultaDiasVencimento(serialValido, user, cnpj);
                }catch (IllegalArgumentException e){
                    Toast.makeText(GeradorDeChaveActivity.this, "Data inválida!", Toast.LENGTH_SHORT).show();
                    ocultaDiasVencimento();
                    textSerialValido.setText("");
                    Log.e("Data invalida", "Mensagem original:" + e);
                    e.printStackTrace();
                }
            }
        });

    }

    private void desocultaDiasVencimento(String serial, String user, String cnpj) {
        textDiaParaVencer.setVisibility(View.VISIBLE);
        textDataVencimentoExtenso.setVisibility(View.VISIBLE);

        try {
            Discriptografia discriptografia = new Discriptografia(serial,user,cnpj );
            textDiaParaVencer.setText("Dias para vencer: " +
                    discriptografia.getQuantosDiasParaVencer());
            textDataVencimentoExtenso.setText("Data do vencimento extenso: " +
                    discriptografia.getDataDeVencimentoString());
        } catch (RuntimeException exception) {
            System.out.println(exception.toString());
        }
    }

    private void ocultaDiasVencimento() {
        textDiaParaVencer.setVisibility(View.INVISIBLE);
        textDataVencimentoExtenso.setVisibility(View.INVISIBLE);
    }


    private void configuraCampoDataLiberacao() {
        textDataLiberacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostraDatePicker();
            }
        });

        textDataLiberacao.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus){
                    mostraDatePicker();
                }
            }
        });
    }

    private void mostraDatePicker() {
        new DataPickerFragment().show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        DateTime dataRecebida = new DateTime(year, month + 1, dayOfMonth,0,0);

        textDataLiberacao.setText(dataRecebida.toString(dateTimeFormatter));
    }
}
