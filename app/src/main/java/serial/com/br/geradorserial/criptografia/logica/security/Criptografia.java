package serial.com.br.geradorserial.criptografia.logica.security;

import org.joda.time.LocalDate;

import serial.com.br.geradorserial.criptografia.logica.businessRule.Logica;
import serial.com.br.geradorserial.criptografia.logica.businessRule.TabelaCode;

public class Criptografia extends Logica {
    public Criptografia(String user, String cnpj, LocalDate dataPrazo) {
        super(user, cnpj, dataPrazo);
    }

    public String getSerial(){
        String serial = "";
        serial= serial.concat(criptografiaCalculo4());
        serial= serial.concat(criptografiaCalculo1());
        serial= serial.concat(criptografiaCalculo2());
        serial= serial.concat(criptografiaCalculo3());

        return serial;


    }

    private String criptografiaCalculo1() {
        // usuario * dia + criptografia

        String criptografia1 = "";

        criptografia1 = codificarUSUARIO(criptografia1,0,2);
        criptografia1 = codificarUSUARIO(criptografia1,2,4);
        criptografia1 = codificarUSUARIO(criptografia1,4,6);
        return criptografia1;

    }

    private String codificarUSUARIO(String criptografia,int posInicio, int posFinal) {
        Long valor = calculoUsuarioVezesDias();
        String valorParaCriptografia = valor.toString();
        criptografia =
                criptografia.concat(TabelaCode.onMain(valorParaCriptografia.substring(posInicio, posFinal)));
        return criptografia;
    }

    private String criptografiaCalculo2() {
        String criptografia2 = "";

        criptografia2 = extrairPartesCNPJ(criptografia2,PARTE1);
        return criptografia2;
    }


    private String criptografiaCalculo3() {
        String criptografia3 = "";

        criptografia3 = extrairPartesCNPJ(criptografia3,PARTE2);
        return criptografia3;
    }




    private String extrairPartesCNPJ(String criptografia,boolean padrao) {
        if (Long.toString(calculoCNPJmaisCalculoDIAS(padrao) + cnpjParte(padrao)).length() <= 3) {
            criptografia = codificarCNPJ(criptografia, padrao,0, 1);
            criptografia = codificarCNPJ(criptografia, padrao,1, 3);

        } else {

            criptografia = codificarCNPJ(criptografia,padrao, 0, 2);
            criptografia = codificarCNPJ(criptografia,padrao, 2, 4);

        }
        return criptografia;
    }


    private String codificarCNPJ(String criptografia, boolean padrao, int posInicio, int posFim) {

        long valorParaCriptografia = calculoCNPJmaisCalculoDIAS(padrao);
        criptografia = criptografia.concat(TabelaCode
                .onMain(Long.toString(valorParaCriptografia + cnpjParte(padrao)).substring(posInicio, posFim)));
        return criptografia;
    }

    private String criptografiaCalculo4(){
        String criptografia4 = "";
        criptografia4 = criptografia4.concat(
                TabelaCode.onDia(Integer.toString(super.getData().getDayOfMonth())));
        criptografia4 = criptografia4.concat(
                TabelaCode.onMes(Integer.toString(super.getData().getMonthOfYear())));
        return criptografia4;

    }

    protected long calculoUsuarioVezesDias() {
        return usuarioStringParaInt() * diferenciaEmDias();
    }

    protected long calculoCNPJmaisCalculoDIAS(boolean padrao) {
        if (!padrao)
            return Long.parseLong(Long.toString(calculoUsuarioVezesDias()).substring(3, 6));
        else
            return Long.parseLong(Long.toString(calculoUsuarioVezesDias()).substring(0, 3));

    }
}
