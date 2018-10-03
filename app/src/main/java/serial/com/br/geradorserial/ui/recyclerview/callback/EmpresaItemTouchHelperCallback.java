package serial.com.br.geradorserial.ui.recyclerview.callback;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import serial.com.br.geradorserial.database.GerenciadorDataBase;
import serial.com.br.geradorserial.models.Empresa;
import serial.com.br.geradorserial.ui.recyclerview.adapter.ListaEmpresasAdapter;

public class EmpresaItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private Context context;
    private final ListaEmpresasAdapter adapter;
    private TextView buttonTextView;

    public EmpresaItemTouchHelperCallback(Context context, ListaEmpresasAdapter adapter, TextView buttonTextView) {
        this.context = context;
        this.adapter = adapter;
        this.buttonTextView = buttonTextView;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int marcacoesDeDeslize = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        int marcacoesDeArrastar = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT
                | ItemTouchHelper.DOWN | ItemTouchHelper.UP;
        return makeMovementFlags(marcacoesDeArrastar, marcacoesDeDeslize);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int posicaoInicial = viewHolder.getAdapterPosition();
        int posicaoFinal = target.getAdapterPosition();

        adapter.trocaPosicao(posicaoInicial, posicaoFinal);
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        final int posicaoDeDeslize = viewHolder.getAdapterPosition();

        List<Empresa> listaDeEmpresas = adapter.getEmpresas();
        final Empresa empresa = listaDeEmpresas.get(posicaoDeDeslize);

        adapter.remove(posicaoDeDeslize);
        new GerenciadorDataBase().gerarDataBase(context)
                .getEmpresaDao().deletar(empresa);

        Snackbar.make(buttonTextView, "Cliente deletado", Snackbar.LENGTH_SHORT)
                .setAction("Desfazer", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new GerenciadorDataBase().gerarDataBase(context)
                                .getEmpresaDao().inserir(empresa);
                        adapter.desfazerRemocao(empresa, posicaoDeDeslize);
                    }
                })
                .show();
    }
}
