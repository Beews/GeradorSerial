package serial.com.br.geradorserial.ui.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import br.com.caelum.stella.format.CNPJFormatter;
import serial.com.br.geradorserial.R;
import serial.com.br.geradorserial.models.Empresa;
import serial.com.br.geradorserial.ui.recyclerview.adapter.listener.OnItemClickListener;

public class ListaEmpresasAdapter extends RecyclerView.Adapter<ListaEmpresasAdapter.EmpresaViewHolder> {

    private List<Empresa> listaDeEmpresas;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public ListaEmpresasAdapter(Context context, List<Empresa> empresas){
        this.listaDeEmpresas = empresas;
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ListaEmpresasAdapter.EmpresaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewCriada = LayoutInflater.from(context)
                .inflate(R.layout.lista_item, parent, false);

        return new EmpresaViewHolder(viewCriada);
    }

    @Override
    public void onBindViewHolder(ListaEmpresasAdapter.EmpresaViewHolder holder, int position) {
        Empresa empresa = listaDeEmpresas.get(position);
        holder.vincula(empresa);
    }

    @Override
    public int getItemCount() {
        return listaDeEmpresas.size();
    }

     public List<Empresa> getEmpresas(){
        return this.listaDeEmpresas;
     }


    public void adiciona(Empresa empresa) {
        listaDeEmpresas.add(empresa);
        notifyDataSetChanged();
    }

    public void altera(int posicao, Empresa empresa) {
        listaDeEmpresas.set(posicao, empresa);
        notifyDataSetChanged();
    }

    public void remove(int posicao) {
        listaDeEmpresas.remove(posicao);
        notifyItemRemoved(posicao);
    }

    public void trocaPosicao(int posicaoInicial, int posicaoFinal) {
        Collections.swap(listaDeEmpresas, posicaoInicial, posicaoFinal);
        notifyItemMoved(posicaoInicial, posicaoFinal);
    }

    public void desfazerRemocao(Empresa empresa, int posicao) {
        listaDeEmpresas.add(posicao, empresa);
        notifyItemInserted(posicao);
    }

    public class EmpresaViewHolder extends RecyclerView.ViewHolder {

        private final TextView descricao;
        private final TextView cnpj;
        private final Button buttonGerarSerial;
        private Empresa empresa;

        public EmpresaViewHolder(View itemView) {
            super(itemView);

            descricao = itemView.findViewById(R.id.listitem_descricao);
            cnpj = itemView.findViewById(R.id.listitem_cnpj);
            buttonGerarSerial = itemView.findViewById(R.id.listitem_button_gerar_serial);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(empresa, getAdapterPosition());
                }
            });

            buttonGerarSerial.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onClickListenerButton(empresa, getAdapterPosition());
                }
            });
        }

        public void vincula(Empresa empresa){
            this.empresa = empresa;
            descricao.setText(empresa.getNome());
            cnpj.setText(new CNPJFormatter().format(empresa.getCnpj()));
        }
    }
}
