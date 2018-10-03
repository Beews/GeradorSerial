package serial.com.br.geradorserial.criptografia.logica.businessRule;

public class TabelaCode {
    public final static String onMes(String caso){
        String retorno = "error";
        String s = caso;
        switch (s) {

            case "1":  retorno = "#"; break;
            case "2":  retorno = "@"; break;
            case "3":  retorno = "Y"; break;
            case "4":  retorno = "$"; break;
            case "5":  retorno = "&"; break;
            case "6":  retorno = "T"; break;
            case "7":  retorno = "Q"; break;
            case "8":  retorno = "K"; break;
            case "9":  retorno = "X"; break;
            case "10": retorno = "Z"; break;
            case "11": retorno = "M"; break;
            case "12": retorno = "F"; break;

        }
        return retorno;
    }

    public final static String oFFMes(String caso){
        String retorno = "error";
        String s = caso;
        switch (s) {

            case "#": retorno = "1" ; break;
            case "@": retorno = "2" ; break;
            case "Y": retorno = "3" ; break;
            case "$": retorno = "4" ; break;
            case "&": retorno = "5" ; break;
            case "T": retorno = "6" ; break;
            case "Q": retorno = "7" ; break;
            case "K": retorno = "8" ; break;
            case "X": retorno = "9" ; break;
            case "Z": retorno = "10"; break;
            case "M": retorno = "11"; break;
            case "F": retorno = "12"; break;

        }
        return retorno;
    }

    public final static String onDia(String caso){
        String retorno = "error";
        String s = caso;
        switch (s) {

            case "1":  retorno = "M"; break;
            case "2":  retorno = "Q"; break;
            case "3":  retorno = "V"; break;
            case "4":  retorno = "R"; break;
            case "5":  retorno = "I"; break;
            case "6":  retorno = "Z"; break;
            case "7":  retorno = "P"; break;
            case "8":  retorno = "A"; break;
            case "9":  retorno = "E"; break;
            case "10": retorno = "T"; break;
            case "11": retorno = "X"; break;
            case "12": retorno = "Y"; break;
            case "13": retorno = "9"; break;
            case "14": retorno = "W"; break;
            case "15": retorno = "6"; break;
            case "16": retorno = "B"; break;
            case "17": retorno = "5"; break;
            case "18": retorno = "F"; break;
            case "19": retorno = "#"; break;
            case "20": retorno = "7"; break;
            case "21": retorno = "H"; break;
            case "22": retorno = "G"; break;
            case "23": retorno = "4"; break;
            case "24": retorno = "D"; break;
            case "25": retorno = "S"; break;
            case "26": retorno = "8"; break;
            case "27": retorno = "2"; break;
            case "28": retorno = "C"; break;
            case "29": retorno = "1"; break;
            case "30": retorno = "N"; break;
            case "31": retorno = "3"; break;

        }
        return retorno;
    }


    public final static String offDia(String caso){
        String retorno = "error";
        String s = caso;
        switch (s) {

            case "M": retorno = "1" ; break;
            case "Q": retorno = "2" ; break;
            case "V": retorno = "3" ; break;
            case "R": retorno = "4" ; break;
            case "I": retorno = "5" ; break;
            case "Z": retorno = "6" ; break;
            case "P": retorno = "7" ; break;
            case "A": retorno = "8" ; break;
            case "E": retorno = "9" ; break;
            case "T": retorno = "10"; break;
            case "X": retorno = "11"; break;
            case "Y": retorno = "12"; break;
            case "9": retorno = "13"; break;
            case "W": retorno = "14"; break;
            case "6": retorno = "15"; break;
            case "B": retorno = "16"; break;
            case "5": retorno = "17"; break;
            case "F": retorno = "18"; break;
            case "#": retorno = "19"; break;
            case "7": retorno = "20"; break;
            case "H": retorno = "21"; break;
            case "G": retorno = "22"; break;
            case "4": retorno = "23"; break;
            case "D": retorno = "24"; break;
            case "S": retorno = "25"; break;
            case "8": retorno = "26"; break;
            case "2": retorno = "27"; break;
            case "C": retorno = "28"; break;
            case "1": retorno = "29"; break;
            case "N": retorno = "30"; break;
            case "3": retorno = "31"; break;

        }
        return retorno;
    }

    public final static String onMain(String caso){

        String retorno = "error";
        String s = caso;
        switch (s) {

            case "1":  retorno = "QU"; break;
            case "2":  retorno = "WB"; break;
            case "3":  retorno = "RX"; break;
            case "4":  retorno = "XZ"; break;
            case "5":  retorno = "ZW"; break;
            case "6":  retorno = "KY"; break;
            case "7":  retorno = "YN"; break;
            case "8":  retorno = "QM"; break;
            case "9":  retorno = "WJ"; break;
            case "01": retorno = "RH"; break;
            case "02": retorno = "XA"; break;
            case "03": retorno = "Z0"; break;
            case "04": retorno = "K6"; break;
            case "05": retorno = "Y3"; break;
            case "06": retorno = "Q8"; break;
            case "07": retorno = "W2"; break;
            case "08": retorno = "R9"; break;
            case "09": retorno = "X1"; break;
            case "10": retorno = "Z4"; break;
            case "11": retorno = "K5"; break;
            case "12": retorno = "Y7"; break;
            case "13": retorno = "QG"; break;
            case "14": retorno = "WU"; break;
            case "15": retorno = "RV"; break;
            case "16": retorno = "XX"; break;
            case "17": retorno = "ZZ"; break;
            case "18": retorno = "KW"; break;
            case "19": retorno = "YY"; break;
            case "20": retorno = "QN"; break;
            case "21": retorno = "WM"; break;
            case "22": retorno = "RJ"; break;
            case "23": retorno = "XH"; break;
            case "24": retorno = "ZA"; break;
            case "25": retorno = "K0"; break;
            case "26": retorno = "Y6"; break;
            case "27": retorno = "Q3"; break;
            case "28": retorno = "W8"; break;
            case "29": retorno = "R2"; break;
            case "30": retorno = "X9"; break;
            case "31": retorno = "Z1"; break;
            case "32": retorno = "K4"; break;
            case "33": retorno = "Y5"; break;
            case "34": retorno = "Q7"; break;
            case "35": retorno = "WG"; break;
            case "36": retorno = "RU"; break;
            case "37": retorno = "XB"; break;
            case "38": retorno = "ZV"; break;
            case "39": retorno = "KX"; break;
            case "40": retorno = "YZ"; break;
            case "41": retorno = "QY"; break;
            case "42": retorno = "WN"; break;
            case "43": retorno = "RM"; break;
            case "44": retorno = "XJ"; break;
            case "45": retorno = "ZH"; break;
            case "46": retorno = "KA"; break;
            case "47": retorno = "Y0"; break;
            case "48": retorno = "Q6"; break;
            case "49": retorno = "W3"; break;
            case "50": retorno = "R8"; break;
            case "51": retorno = "X2"; break;
            case "52": retorno = "Z9"; break;
            case "53": retorno = "K1"; break;
            case "54": retorno = "Y4"; break;
            case "55": retorno = "Q5"; break;
            case "56": retorno = "W7"; break;
            case "57": retorno = "RG"; break;
            case "58": retorno = "XU"; break;
            case "59": retorno = "ZB"; break;
            case "60": retorno = "QB"; break;
            case "61": retorno = "WV"; break;
            case "62": retorno = "R0"; break;
            case "63": retorno = "X0"; break;
            case "64": retorno = "Z7"; break;
            case "65": retorno = "K3"; break;
            case "66": retorno = "Y8"; break;
            case "67": retorno = "Q9"; break;
            case "68": retorno = "WX"; break;
            case "69": retorno = "R3"; break;
            case "71": retorno = "YX"; break;
            case "72": retorno = "QZ"; break;
            case "73": retorno = "WW"; break;
            case "74": retorno = "RY"; break;
            case "75": retorno = "XN"; break;
            case "76": retorno = "ZM"; break;
            case "77": retorno = "KJ"; break;
            case "78": retorno = "YH"; break;
            case "79": retorno = "QA"; break;
            case "80": retorno = "W0"; break;
            case "81": retorno = "R6"; break;
            case "82": retorno = "X3"; break;
            case "83": retorno = "Z8"; break;
            case "84": retorno = "K2"; break;
            case "85": retorno = "Y9"; break;
            case "86": retorno = "Q1"; break;
            case "87": retorno = "W4"; break;
            case "88": retorno = "R5"; break;
            case "89": retorno = "X7"; break;
            case "90": retorno = "YG"; break;
            case "91": retorno = "QX"; break;
            case "92": retorno = "WZ"; break;
            case "93": retorno = "R4"; break;
            case "94": retorno = "X6"; break;
            case "95": retorno = "ZJ"; break;
            case "96": retorno = "KH"; break;
            case "97": retorno = "Y2"; break;
            case "98": retorno = "Q2"; break;
            case "99": retorno = "W9"; break;
            case "00": retorno = "R1"; break;
        }
        return retorno;
    }

    public static String offMain(String caso){

        String retorno = "error";
        String s = caso;
        switch (s) {

            case "QU": retorno ="1"  ; break;
            case "WB": retorno ="2"  ; break;
            case "RX": retorno ="3"  ; break;
            case "XZ": retorno ="4"  ; break;
            case "ZW": retorno ="5"  ; break;
            case "KY": retorno ="6"  ; break;
            case "YN": retorno ="7"  ; break;
            case "QM": retorno ="8"  ; break;
            case "WJ": retorno ="9"  ; break;
            case "RH": retorno ="01" ; break;
            case "XA": retorno ="02" ; break;
            case "Z0": retorno ="03" ; break;
            case "K6": retorno ="04" ; break;
            case "Y3": retorno ="05" ; break;
            case "Q8": retorno ="06" ; break;
            case "W2": retorno ="07" ; break;
            case "R9": retorno ="08" ; break;
            case "X1": retorno ="09" ; break;
            case "Z4": retorno ="10" ; break;
            case "K5": retorno ="11" ; break;
            case "Y7": retorno ="12" ; break;
            case "QG": retorno ="13" ; break;
            case "WU": retorno ="14" ; break;
            case "RV": retorno ="15" ; break;
            case "XX": retorno ="16" ; break;
            case "ZZ": retorno ="17" ; break;
            case "KW": retorno ="18" ; break;
            case "YY": retorno ="19" ; break;
            case "QN": retorno ="20" ; break;
            case "WM": retorno ="21" ; break;
            case "RJ": retorno ="22" ; break;
            case "XH": retorno ="23" ; break;
            case "ZA": retorno ="24" ; break;
            case "K0": retorno ="25" ; break;
            case "Y6": retorno ="26" ; break;
            case "Q3": retorno ="27" ; break;
            case "W8": retorno ="28" ; break;
            case "R2": retorno ="29" ; break;
            case "X9": retorno ="30" ; break;
            case "Z1": retorno ="31" ; break;
            case "K4": retorno ="32" ; break;
            case "Y5": retorno ="33" ; break;
            case "Q7": retorno ="34" ; break;
            case "WG": retorno ="35" ; break;
            case "RU": retorno ="36" ; break;
            case "XB": retorno ="37" ; break;
            case "ZV": retorno ="38" ; break;
            case "KX": retorno ="39" ; break;
            case "YZ": retorno ="40" ; break;
            case "QY": retorno ="41" ; break;
            case "WN": retorno ="42" ; break;
            case "RM": retorno ="43" ; break;
            case "XJ": retorno ="44" ; break;
            case "ZH": retorno ="45" ; break;
            case "KA": retorno ="46" ; break;
            case "Y0": retorno ="47" ; break;
            case "Q6": retorno ="48" ; break;
            case "W3": retorno ="49" ; break;
            case "R8": retorno ="50" ; break;
            case "X2": retorno ="51" ; break;
            case "Z9": retorno ="52" ; break;
            case "K1": retorno ="53" ; break;
            case "Y4": retorno ="54" ; break;
            case "Q5": retorno ="55" ; break;
            case "W7": retorno ="56" ; break;
            case "RG": retorno ="57" ; break;
            case "XU": retorno ="58" ; break;
            case "ZB": retorno ="59" ; break;
            case "QB": retorno ="60" ; break;
            case "WV": retorno ="61" ; break;
            case "R0": retorno ="62" ; break;
            case "X0": retorno ="63" ; break;
            case "Z7": retorno ="64" ; break;
            case "K3": retorno ="65" ; break;
            case "Y8": retorno ="66" ; break;
            case "Q9": retorno ="67" ; break;
            case "WX": retorno ="68" ; break;
            case "R3": retorno ="69" ; break;
            case "YX": retorno ="71" ; break;
            case "QZ": retorno ="72" ; break;
            case "WW": retorno ="73" ; break;
            case "RY": retorno ="74" ; break;
            case "XN": retorno ="75" ; break;
            case "ZM": retorno ="76" ; break;
            case "KJ": retorno ="77" ; break;
            case "YH": retorno ="78" ; break;
            case "QA": retorno ="79" ; break;
            case "W0": retorno ="80" ; break;
            case "R6": retorno ="81" ; break;
            case "X3": retorno ="82" ; break;
            case "Z8": retorno ="83" ; break;
            case "K2": retorno ="84" ; break;
            case "Y9": retorno ="85" ; break;
            case "Q1": retorno ="86" ; break;
            case "W4": retorno ="87" ; break;
            case "R5": retorno ="88" ; break;
            case "X7": retorno ="89" ; break;
            case "YG": retorno ="90" ; break;
            case "QX": retorno ="91" ; break;
            case "WZ": retorno ="92" ; break;
            case "R4": retorno ="93" ; break;
            case "X6": retorno ="94" ; break;
            case "ZJ": retorno ="95" ; break;
            case "KH": retorno ="96" ; break;
            case "Y2": retorno ="97" ; break;
            case "Q2": retorno ="98" ; break;
            case "W9": retorno ="99" ; break;
            case "R1": retorno ="00" ; break;

        }
        return retorno;
    }
}
