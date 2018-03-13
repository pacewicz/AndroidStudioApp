package acevedochipasociados.androidstudioapp;

/**
 * Created by digital on 12/03/2018.
 */
import  java.lang.reflect.Array;
import java.util.ArrayList;

/**
 *
 * @author digital
 */
public class HammingCodification {
    int Errors;

    public HammingCodification() {
        this.Errors=0;
    }



    public ArrayList<String> ToASCII(String Text){
        ArrayList<String> Codes = new ArrayList<>();
        for (int x=0;x<Text.length();x++){
            //System.out.println(Text.charAt(x) + " = " + Text.codePointAt(x));
            int L = Text.codePointAt(x);
            Codes.add(Integer.toBinaryString(L));
        }
        return Codes;
    }

    public String Codewords(ArrayList<String> ASCII){
        String Codewords = "";
        for (String PalDatos : ASCII) {
            boolean[] Palabra = new boolean[8];
            boolean[] Codigo = new boolean[12];
            if (PalDatos.length()< 8) {
                while(PalDatos.length()< 7){
                    PalDatos =  "0" + PalDatos;
                }
            }
            for (int i = 0; i < PalDatos.length(); i++) {
                if(PalDatos.substring(i,i+1).equals("1")) {
                    Palabra[i+1]=true;
                }else{
                    Palabra[i+1]=false;
                }
            }
            int j = 7;
            for (int i = 1; i <= 11; i++) {
                if (i!=1 && i!=2 && i!=4 && i!=8) {
                    Codigo[i]=Palabra[j];
                    j=j-1;
                }
            }
            Codigo[1]= Codigo[3]^Codigo[5]^Codigo[7]^Codigo[9]^Codigo[11];
            Codigo[2]= Codigo[3]^Codigo[6]^Codigo[7]^Codigo[10]^Codigo[11];
            Codigo[4]= Codigo[5]^Codigo[6]^Codigo[7];
            Codigo[8]= Codigo[9]^Codigo[10]^Codigo[11];
            String Code="";
            for (int i = 11; i >= 1; i--) {
                if (Codigo[i]) {
                    Code = Code + "1";
                }else{
                    Code = Code + "0";
                }
            }
            Codewords = Codewords + Code + "\n";
        }
        return Codewords;
    }

    public String ArrayToString (ArrayList<String> Lines,boolean sw){
        String Aux = "";
        for(String line: Lines){
            Aux = Aux + line + "\n";
        }
        return Aux;
    }

    public String Correccion(ArrayList<String> Codes){
        String Codewords = "";
        for (String Code : Codes) {
            boolean[] Palabra = new boolean[12];
            for (int i = Code.length()-1; i >= 0; i--) {
                if(Code.charAt(i)=='1') {
                    Palabra[11-(i)]=true;
                }else{
                    Palabra[11-(i)]=false;
                }
            }
            String Codid = "";
            for (int i = 1; i <= 11; i++) {
                if (Palabra[i]) {
                    Codid = Codid + "1";
                }else{
                    Codid = Codid + "0";
                }
            }
            boolean C1,C2,C4,C8;
            String c1,c2,c4,c8;
            C1=Palabra[1]^Palabra[3]^Palabra[5]^Palabra[7]^Palabra[9]^Palabra[11];
            C2=Palabra[2]^Palabra[3]^Palabra[6]^Palabra[7]^Palabra[10]^Palabra[11];
            C4=Palabra[4]^Palabra[5]^Palabra[6]^Palabra[7];
            C8=Palabra[8]^Palabra[9]^Palabra[10]^Palabra[11];
            if (C8) {
                c8="1";
            }else{
                c8="0";
            }
            if (C4) {
                c4="1";
            }else{
                c4="0";
            }
            if (C2) {
                c2="1";
            }else{
                c2="0";
            }
            if (C1) {
                c1="1";
            }else{
                c1="0";
            }
            int malo = Integer.parseInt(c8+c4+c2+c1,2);
            //System.out.println(malo + " " + Code);
            if (malo!=0) {
                Palabra[malo] = !Palabra[malo];
                String C = "";
                for (int i = 11; i >= 1; i--) {
                    if (Palabra[i]) {
                        C = C + "1";
                    }else{
                        C = C + "0";
                    }
                }
                Codewords = Codewords + C + "\n";
                Errors = Errors + 1;
            }else{
                Codewords = Codewords + Code + "\n";
            }
        }
        return Codewords;
    }

}
