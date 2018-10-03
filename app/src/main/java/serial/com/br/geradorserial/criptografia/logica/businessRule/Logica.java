package serial.com.br.geradorserial.criptografia.logica.businessRule;

import org.joda.time.Days;
import org.joda.time.LocalDate;

public class Logica {

    protected final boolean PARTE1 = true;
    protected final boolean PARTE2 = false;
    private String user;
    private String cnpj;
    private LocalDate data;
    private LocalDate inicio = new LocalDate(2017, 01, 01);

    protected LocalDate getData() {
        return data;
    }

    protected LocalDate getInicio() {
        return inicio;
    }

    protected String getSerial() {
        return serial;
    }

    private LocalDate dataHoje = LocalDate.now();
    private String serial;

    public Logica(String user, String cnpj, LocalDate dataPrazo) {
        this.user = user;
        this.cnpj = cnpj;
        this.data = dataPrazo;
    }

    public Logica(String serial, String user, String cnpj) {
        this.serial = serial;
        this.user = user;
        this.cnpj = cnpj;
    }

    protected long diferenciaEmDias() {
        Days diasDeDiferenca = Days.daysBetween(inicio, data);
        return diasDeDiferenca.getDays();
    }

    protected long diferenciaEmDiasHoje() {
        Days diasDeDiferenca = Days.daysBetween(inicio, dataHoje);
        return diasDeDiferenca.getDays();
    }

    protected long cnpjParte(boolean padrao) {
        if (!padrao)
            return cnpjAlgoritimo(9, 18);
        else
            return cnpjAlgoritimo(0, 9);

    }

    protected int usuarioStringParaInt() {
        int userValorNumero = 0;
        char[] arrayChar1 = user.toCharArray();
        for (int c = 0; c < arrayChar1.length; c++) {
            char v = arrayChar1[c];
            if (v > 0)
                userValorNumero = (userValorNumero + v);
        }
        return userValorNumero;
    }

    private long cnpjAlgoritimo(int PosInicio, int PosFinal) {
        String cnpj2 = cnpj.substring(PosInicio, PosFinal);
        char[] arrayChar2 = cnpj2.toCharArray();
        long cnpjValorNumeroParte2 = 1;
        for (int c = 0; c < arrayChar2.length; c++) {
            long v = Character.toString(arrayChar2[c]).hashCode();
            if (v > 0)
                cnpjValorNumeroParte2 = (cnpjValorNumeroParte2 + v);
        }
        return cnpjValorNumeroParte2;
    }
}
