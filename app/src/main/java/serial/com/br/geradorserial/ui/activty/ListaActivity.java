package serial.com.br.geradorserial.ui.activty;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import serial.com.br.geradorserial.R;
import serial.com.br.geradorserial.dao.EmpresaDao;
import serial.com.br.geradorserial.database.GerenciadorDataBase;
import serial.com.br.geradorserial.models.Empresa;
import serial.com.br.geradorserial.ui.recyclerview.adapter.ListaEmpresasAdapter;
import serial.com.br.geradorserial.ui.recyclerview.adapter.listener.OnItemClickListener;
import serial.com.br.geradorserial.ui.recyclerview.callback.EmpresaItemTouchHelperCallback;

import static serial.com.br.geradorserial.ui.activty.EmpresaActivityConstantes.CHAVE_EMPRESA;
import static serial.com.br.geradorserial.ui.activty.EmpresaActivityConstantes.CHAVE_POSICAO;
import static serial.com.br.geradorserial.ui.activty.EmpresaActivityConstantes.CHAVE_SERIAL;
import static serial.com.br.geradorserial.ui.activty.EmpresaActivityConstantes.CODIGO_REQUISICAO_ALTERA_EMPRESA;
import static serial.com.br.geradorserial.ui.activty.EmpresaActivityConstantes.CODIGO_REQUISICAO_INSERE_EMPRESA;
import static serial.com.br.geradorserial.ui.activty.EmpresaActivityConstantes.POSICAO_INVALIDA;

public class ListaActivity extends AppCompatActivity {

    private TextView textButtonInsereEmpresa;
    private ListaEmpresasAdapter adapter;
    private EmpresaDao empresaDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        setTitle("Lista de Clientes");

        textButtonInsereEmpresa = findViewById(R.id.lista_button_insere_empresa);

        empresaDao = new GerenciadorDataBase().gerarDataBase(this).getEmpresaDao();

        List<Empresa> listaDeEmpresas = getEmpresas();
        configuraRecyclerView(listaDeEmpresas);
        clickDoInsereEmpresa();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Empresa empresaRecebida = new Empresa();
        if (data != null){
            empresaRecebida = data.getParcelableExtra(CHAVE_EMPRESA);
        }
        if (ehResultadoInsereEmpresa(requestCode, resultCode, data)){
            empresaDao.inserir(empresaRecebida);
            adapter.adiciona(empresaRecebida);
        }

        if (ehResultadoAlteraEmpresa(requestCode, resultCode, data)){
            int posicaoRecebida = data.getIntExtra(CHAVE_POSICAO, POSICAO_INVALIDA);

            if (posicaoRecebida > POSICAO_INVALIDA){
                empresaDao.alterar(empresaRecebida);
                adapter.altera(posicaoRecebida, empresaRecebida);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private boolean ehResultadoAlteraEmpresa(int requestCode, int resultCode, Intent data) {
        return requestCode == CODIGO_REQUISICAO_ALTERA_EMPRESA
                && resultCode == Activity.RESULT_OK
                && data.hasExtra(CHAVE_EMPRESA);
    }

    private boolean ehResultadoInsereEmpresa(int requestCode, int resultCode, Intent data) {
        return requestCode == CODIGO_REQUISICAO_INSERE_EMPRESA
                && resultCode == Activity.RESULT_OK
                && data.hasExtra(CHAVE_EMPRESA);
    }

    private void clickDoInsereEmpresa() {
        textButtonInsereEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vaiParaFormularioEmpresa = new Intent(ListaActivity.this, FormularioActivity.class);
                startActivityForResult(vaiParaFormularioEmpresa, CODIGO_REQUISICAO_INSERE_EMPRESA);
            }
        });
    }

    private List<Empresa> getEmpresas() {
        return new GerenciadorDataBase()
                    .gerarDataBase(this)
                    .getEmpresaDao()
                    .buscaEmpresas();
    }

    private void configuraRecyclerView(List<Empresa> todasEmpresas) {
        RecyclerView listaEmpresas = findViewById(R.id.lista_recyclerview);
        configuraAdapter(todasEmpresas, listaEmpresas);
        configuraItemTouchHelper(listaEmpresas);
    }

    private void configuraItemTouchHelper(RecyclerView listaEmpresas) {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(
                new EmpresaItemTouchHelperCallback(this, adapter, textButtonInsereEmpresa));
        itemTouchHelper.attachToRecyclerView(listaEmpresas);
    }

    private void configuraAdapter(List<Empresa> todasEmpresas, RecyclerView listaEmpresas) {
        adapter = new ListaEmpresasAdapter(this, todasEmpresas);
        listaEmpresas.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(Empresa empresa, int posicao) {
                Intent abreFormularioComEmpresa = new Intent(ListaActivity.this, FormularioActivity.class);
                abreFormularioComEmpresa.putExtra(CHAVE_EMPRESA, empresa);
                abreFormularioComEmpresa.putExtra(CHAVE_POSICAO, posicao);
                startActivityForResult(abreFormularioComEmpresa, CODIGO_REQUISICAO_ALTERA_EMPRESA);
            }

            @Override
            public void onClickListenerButton(Empresa empresa, int posicao) {
                Intent abreGeradorDeChave = new Intent(ListaActivity.this, GeradorDeChaveActivity.class);
                abreGeradorDeChave.putExtra(CHAVE_SERIAL, empresa);
                abreGeradorDeChave.putExtra(CHAVE_POSICAO, posicao);
                startActivity(abreGeradorDeChave);
            }
        });
    }
}
