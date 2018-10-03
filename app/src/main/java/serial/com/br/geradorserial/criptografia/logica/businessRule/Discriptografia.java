package serial.com.br.geradorserial.criptografia.logica.businessRule;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Discriptografia extends Logica {

    private String 	valorDIASmaisUSER = "";
    private long 	diasDeValidacao = 0;
    private long	userDiaParte1 = 0;
    private long 	userDiaParte2 = 0;
    private String	cnpjParte1 = "";
    private String	cnpjParte2 = "";
    private int 	mes = 0;
    private int 	dia = 0;

    public Discriptografia(String serial, String user, String cnpj) throws RuntimeException {
        super(serial, user, cnpj);
        if(user.length()<3||user.length()>8||cnpj.length()<18||serial.length()<16)
            throw new RuntimeException("\nERRO NA VALIDAÇÃO"
                    +"\nOs'Validadantes' estão fora do padrão");
        else
            discriptografar();
    }

    public long getQuantosDiasParaVencer() {
        if (isSerialValido())
            return this.diasDeValidacao;
        return 0;
    }

    public LocalDate getDataDeVencimento() {
        if (isSerialValido()) {
            return super.getInicio().plusDays(calculoDias());
        }
        throw new RuntimeException("\nERRO NA VALIDAÇÃO"+
                "\nEste serial esta invalido erro:01 ");
    }



    public String getDataDeVencimentoString(String padrao) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(padrao);
        LocalDate dataDeVencimento = getDataDeVencimento();
        return dataDeVencimento.toString(dateTimeFormatter);
    }

    public String getDataDeVencimentoString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd 'de' MMMM 'de' yyyy");
        LocalDate dataDeVencimento = getDataDeVencimento();
        return dataDeVencimento.toString(dateTimeFormatter);
    }

    public String getDiaDaSemanaDeVencimento() {
        int dayOfWeek = getDataDeVencimento().getDayOfWeek();
        return Integer.toString(dayOfWeek);
    }

    public boolean isSerialValido() {
        if (condicaoPrimaria())
            return false;
        else
        if (condicaoSecundaria())
            return false;
        else
        if (condicaoTernaria())
            return false;
        else
            return true;

    }

    private boolean condicaoTernaria() {
        return super.getInicio().plusDays(calculoDias()).getDayOfMonth() != dia
                || super.getInicio().plusDays(calculoDias()).getMonthOfYear() != mes;
    }

    private boolean condicaoSecundaria() {
        return (Long.parseLong(cnpjParte2) - cnpjParte(PARTE2) != userDiaParte2)
                || (Long.parseLong(cnpjParte1) - cnpjParte(PARTE1) != userDiaParte1);
    }

    private boolean condicaoPrimaria() {
        return this.cnpjParte1.contains("error") || this.cnpjParte2.contains("error");
    }

    private void discriptografar()  {



        cnpjParte1 = extrairCNPJ(super.getSerial().substring(8, 12));
        cnpjParte2 = extrairCNPJ(super.getSerial().substring(12, 16));
        valorDIASmaisUSER = extrairDIAeUSER(super.getSerial().substring(2, 8));
        mes = Integer.parseInt(TabelaCode.oFFMes(super.getSerial().substring(1, 2)));
        dia = Integer.parseInt(TabelaCode.offDia(super.getSerial().substring(0, 1)));
        diasDeValidacao = calculoDias() - super.diferenciaEmDiasHoje();
        if(diasDeValidacao < 0)
            throw new RuntimeException("\nERRO NA VALIDAÇÃO"+
                    "\nEste serial esta invalido erro:03 (" + diasDeValidacao +").");

    }

    private int calculoDias() {
        if (!valorDIASmaisUSER.contains("error"))
            return Integer.parseInt(valorDIASmaisUSER) / usuarioStringParaInt();
        else{
            throw new RuntimeException("\nERRO NA VALIDAÇÃO"+
                    "\nEste serial esta invalido erro:02");
        }
    }

    private String extrairCNPJ(String cnpj) {
        String cnpjOff = "";
        cnpjOff = cnpjOff.concat(TabelaCode.offMain(cnpj.substring(0, 2)));
        cnpjOff = cnpjOff.concat(TabelaCode.offMain(cnpj.substring(2, 4)));
        return cnpjOff;
    }

    private String extrairDIAeUSER(String base) {
        String decodificacao = "";
        decodificacao = decodificacao.concat(TabelaCode.offMain(base.substring(0, 2)));
        decodificacao = decodificacao.concat(TabelaCode.offMain(base.substring(2, 4)));
        decodificacao = decodificacao.concat(TabelaCode.offMain(base.substring(4, 6)));
        if (!decodificacao.contains("error")) {
            userDiaParte1 = Long.parseLong(decodificacao.substring(0, 3));
            userDiaParte2 = Long.parseLong(decodificacao.substring(3, 6));
        }else
            throw new RuntimeException("\nERRO NA VALIDAÇÃO"+
                    "\nEste serial esta invalido erro:04");

        return decodificacao;
    }
}
