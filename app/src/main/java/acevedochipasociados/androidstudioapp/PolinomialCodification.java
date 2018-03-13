package acevedochipasociados.androidstudioapp;

/**
 * Created by digital on 12/03/2018.
 */
import java.math.BigInteger;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author FAMILIA
 */
public class PolinomialCodification {

    public ArrayList<String> BlocksWords (char[] Letters){
        ArrayList<String> Blocks = new ArrayList();
        String Block = "";
        for (int i = 0 ; i < Letters.length ; i++) {
            String hex = Integer.toHexString((int)Letters[i]);
            Block += CompleteHex(new BigInteger(hex,16).toString(2));
            if (Block.length() == 128) {
                Blocks.add(Block);
                Block = "";
            }
        }
        if (Letters.length < 16 || Blocks.size() < ((int)((Letters.length)/16) + 1) ){
            Blocks.add(Block);
        }
        return Blocks;
    }

    public String CompleteHex(String hex){
        while(hex.length() < 8){
            hex = "0" + hex;
        }
        return hex;
    }
    public int[] AddXOR (int[] A,int[] B){
        int[] C = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            C[i] = (A[i]+ B[i])%2;
        }
        return C;
    }
    public int[] MoveLeft(int[] A){
        int[] B = new int[A.length];
        for (int i = 0; i < A.length -1 ; i++) {
            B[i] = A[i+1];
        }
        return B;
    }
    public int[] Multiply(int[] A, int value){
        int[] B = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = (A[i]*value);
        }
        return B;
    }
    public int[] Take(int[] A, int p){
        int[] B = new int[p];
        for (int i = 0; i < p; i++) {
            B[i] = A[i];
        }
        return B;
    }
    public int[] Take(int[] A, int a,int b){
        int[] B = new int[b - a];
        for (int i = a; i < b; i++) {
            B[i-a] = A[i];
        }
        return B;
    }
    public int[] Divide_Mod(int[] G, int[] T){
        int[] Aux = Take(T,G.length);
        int[] Aux1 = new int[G.length];
        int Next = G.length;
        while(Next < T.length){
            Aux1 = Multiply(G,(G[0]*Aux[0]));
            Aux = MoveLeft(AddXOR(Aux,Aux1));
            Aux[Aux.length-1]= T[Next];
            Next++;
        }
        return Take(Aux,1,Aux.length);
    }
    public int[] StringToint(String Word){
        int[] A = new int[Word.length()];
        for (int i = 0; i < Word.length(); i++) {
            A[i] = Integer.parseInt(Word.substring(i, i+1));
        }
        return A;
    }
    public String ArrayToString (ArrayList<String> Lines){
        String Aux = "";
        for(String line: Lines){
            Aux += line;
        }
        return Aux;
    }
    public String ArrayToString (ArrayList<String> Lines,boolean sw){
        String Aux = "";
        for(String line: Lines){
            Aux = Aux + line + "\n";
        }
        return Aux;
    }
    public int[] Join(int[] A,int[] B){
        int[] C = new int[A.length + B.length];
        for (int i = 0; i < A.length; i++) {
            C[i] = A[i];
        }
        for (int i = A.length; i < C.length; i++) {
            C[i] = B[i-A.length];
        }
        return C;
    }
    public String intToString(int[] A){
        String B = "";
        for (int i = 0; i < A.length; i++) {
            B += A[i];
        }
        return B;
    }
    public ArrayList<String> CodeWordsGenerator(ArrayList<String> DateWords,int[] G){
        ArrayList<String> CodeWords = new ArrayList();
        int[] Aux = new int[G.length-1];
        for(String Word: DateWords){
            int[] W = StringToint(Word);
            int[] W1 = Join(W,Aux);
            int[] Mod = Divide_Mod(G,W1);
            int[] CodeWord = Join(W,Mod);
            CodeWords.add(intToString(CodeWord));
        }
        /*for (String W:CodeWords) {
            System.out.println(W);
        }*/
        return CodeWords;
    }

    public boolean Detection(ArrayList<String> DateWords,int[] G){
        ArrayList<String> Aux = DateWords;
        for (String Word: Aux) {
            int[] w = StringToint(Word);
            int[] Mod = Divide_Mod(G,w);
            for (int i: Mod) {
                if (i!=0) {
                    return false;
                }
            }
        }
        return true;
    }
}
