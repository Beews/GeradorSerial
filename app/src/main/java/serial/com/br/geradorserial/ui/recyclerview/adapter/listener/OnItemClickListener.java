package serial.com.br.geradorserial.ui.recyclerview.adapter.listener;

import serial.com.br.geradorserial.models.Empresa;

public interface OnItemClickListener {

    void onItemClick(Empresa empresa, int posicao);
    void onClickListenerButton(Empresa empresa, int posicao);
}
