package acevedochipasociados.androidstudioapp;

import android.app.ActivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    TextView Texto;
    long Float,Sort,Gp,Total,Cod;
    Button boton;
    Process P = new Process();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Texto = (TextView) findViewById(R.id.Texto);
        boton = (Button)findViewById(R.id.boton);
        Texto.setMovementMethod(new ScrollingMovementMethod());
        boton.setOnClickListener(this);
    }

    final static String[] Graph3 = {"1 2" ,"2 3" ,"3 4" ,"4 5" ,"5 6" ,"6 7" ,"7 8" ,
            "8 9" ,"9 10" ,"10 11" ,"11 12" ,"12 13" ,"13 14" ,
            "14 15" ,"15 16" ,"16 17" ,"17 18" ,"18 19" ,"19 20" ,
            "20 21" ,"21 22" ,"22 23" ,"23 24" ,"24 25" ,"25 26" ,
            "26 27" ,"27 28" ,"28 29" ,"29 30" ,"30 31" ,"31 32" ,
            "32 33" ,"33 34" ,"34 35" ,"35 36" ,"36 37" ,"37 38" ,
            "38 39" ,"39 40" ,"40 41" ,"41 42" ,"42 43" ,"43 44" ,
            "44 45" ,"45 46" ,"46 47" ,"47 48" ,"48 49" ,"49 50" ,
            "50 1" ,"1 5" ,"1 10" ,"1 15" ,"1 20" ,"2 6" ,"2 11" ,
            "2 16" ,"2 21" ,"3 7" ,"3 12" ,"3 17" ,"3 22" ,"4 8" ,
            "4 13" ,"4 18" ,"4 23" ,"5 9" ,"5 14" ,"5 19" ,"5 24" ,
            "6 25" ,"6 30" ,"6 35" ,"6 40" ,"7 26" ,"7 31" ,"7 36" ,
            "7 41" ,"8 27" ,"8 32" ,"8 37" ,"8 42" ,"9 28" ,"9 33" ,
            "9 38" ,"9 43" ,"10 29" ,"10 34" ,"10 39" ,"10 44" ,"11 30" ,
            "11 35" ,"11 40" ,"11 45" ,"19 35" ,"19 40" ,"19 45" ,
            "19 50" ,"20 36" ,"20 41" ,"20 46" ,"21 37" ,
            "21 42" ,"21 47" ,"22 43" ,"22 44" ,"22 46" ,
            "24 35" ,"25 36" ,"27 37" ,"28 38" ,"29 39" ,
            "30 49" ,"15 37" ,"15 39" ,"15 42" ,"15 46" ,
            "16 43" ,"16 48" ,"16 49" ,"16 50" ,"13 27" ,
            "13 29" ,"13 32" ,"13 36" ,"18 48" ,"48 23" ,
            "48 27" ,"4 39" ,"4 37" ,"4 32" ,"9 37" ,"50 17" ,
            "50 20" ,"50 22" ,"1 38" ,"44 32" ,"38 21" ,
            "47 24" ,"47 25" ,"47 26" ,"33 45" ,"35 45" ,
            "40 25" ,"40 23" ,"40 18" ,"40 7" ,"45 16" ,
            "1 32" ,"12 27" ,"12 29" ,"12 31" ,"12 35" ,
            "12 40" ,"12 44" ,"37 48" ,"30 42" ,"30 48" ,
            "25 45" ,"30 45" ,"22 42" ,"17 37" ,"50 42" ,
            "50 32" ,"50 29" ,"50 27" ,"3 36" ,"3 32" ,
            "3 29" ,"11 32" ,"11 36" ,"18 32" ,"9 19" ,
            "14 26" ,"15 33" ,"15 35" ,"5 33" ,"5 35" ,
            "43 33" ,"46 33" ,"43 30" ,"30 46" ,"27 43" ,
            "27 46" ,"23 36" ,"23 38" ,"23 43" ,"23 46" ,
            "10 43" ,"10 38" ,"10 36" ,"10 33" ,"10 30" ,
            "19 30" ,"10 19" ,"6 26" ,"18 6" ,"18 39" ,
            "5 21" ,"5 25" ,"5 28" ,"5 31" ,"5 34" ,"5 41" ,
            "11 21" ,"11 25" ,"11 28" ,"11 31" ,"11 34" ,
            "11 50" ,"13 50" ,"15 50" ,"8 50" ,"1 43" ,
            "2 40" ,"2 35" ,"2 31" ,"2 28" ,"2 26" ,"3 44" ,
            "3 27" ,"3 23" ,"3 20" ,"3 18" ,"3 15" ,"38 25" ,
            "38 22" ,"38 13" ,"8 45" ,"8 46" ,"7 46" ,"7 47" ,
            "7 48" ,"1 17" ,"1 19" ,"1 29" ,"1 43" ,"41 9" ,
            "41 12" ,"41 14" ,"41 16" ,"41 21" ,"41 24" ,
            "41 30" ,"30 9" ,"30 12" ,"30 14" ,"30 16" ,"18 13" ,
            "18 37" ,"18 42" ,"18 45" ,"8 48" ,"8 28" ,"8 34" ,
            "8 36" ,"8 43" ,"6 15" ,"6 17" ,"6 19" ,"6 21" ,
            "6 29" ,"6 32" ,"6 34" ,"6 36" ,"6 43" ,"5 26" ,
            "5 30" ,"5 38" ,"5 40" ,"7 14" ,"7 24" ,"7 37" ,
            "9 16" ,"9 25" ,"9 31" ,"9 35" ,"9 42" ,"13 25" ,
            "13 31" ,"13 35" ,"13 39" ,"4 20" ,"4 22" ,"4 28" ,
            "4 6" ,"4 9" ,"6 9" ,"6 14" ,"18 21" ,"21 23" ,
            "18 23" ,"18 27" ,"23 30" ,"29 34" ,"34 36" ,"36 38" ,
            "38 41" ,"41 43" ,"43 46" ,"14 9" ,"14 20" ,"9 20" ,
            "20 23" ,"23 29" ,"20 29" ,"23 32" ,"32 45" ,"45 9" ,
            "21 17" ,"15 17" ,"15 24" ,"15 27" ,"18 12" ,"28 25" ,
            "22 28" ,"22 25" ,"46 42" ,"38 42" ,"35 38" ,"35 46" ,
            "49 41" ,"49 47" ,"48 34" ,"48 39" ,"34 39"};

    public void FloatingPoint(int iterations) {
        long watch = System.currentTimeMillis();
        double[] a1 = new double[iterations];
        double[] a2 = new double[iterations];

        for (int i = 0; i < iterations; i++)
        {
            a1[i] = P.Decimal();
            a2[i] = P.Decimal();
        }
        watch = System.currentTimeMillis() - watch;
        Texto.setText(Texto.getText().toString() +" Random*"+iterations*2+" : " + watch+ " ms \n");
        watch = System.currentTimeMillis();
        Texto.setText(Texto.getText().toString() +"Start : Floating Point^"+iterations+" \n");
        for (int i = 0; i < iterations; i++)
        {
            P.Cos(a1[i], 1000);
            P.Sin(a2[i], 1000);

        }
        watch = System.currentTimeMillis() - watch;
        Float = watch;
        Texto.setText(Texto.getText().toString() +" Execution Time : " + watch+ " ms \n");
    }

    public void Execute() {
        long watch = System.currentTimeMillis();
        Texto.setText(Texto.getText().toString() + "Start : Process \n");

        FloatingPoint(40);
        Sorts();
        executer();
        ExecuterCod();

        watch = System.currentTimeMillis() - watch;
        Total = watch;
        Texto.setText(Texto.getText().toString() + "Total Execution Time : " + watch + " ms \n");
    }


    public void Sorts() {
        int[] v = P.Generator();
        long watch = System.currentTimeMillis();
        Texto.setText(Texto.getText().toString() +"\nStart : Sorts \n");

        P.BubbleSort(v);
        P.InsertSort(v);
        P.SelectSort(v);
        P.QuickSort(v, 0, v.length - 1);

        watch = System.currentTimeMillis() - watch;
        Sort = watch;
        Texto.setText(Texto.getText().toString() + "Execution Time : " + watch + " ms \n");


    }

    public static void Creator(Grafo g) {

        for (String Graph11 : Graph3) {
            String[] aux = Graph11.split(" ");
            g.CrearGrafo(Integer.parseInt(aux[0]), Integer.parseInt(aux[1]));
        }
    }

    public void executer(){
        Texto.setText(Texto.getText().toString() + "\nStart: graph algoritms \n");
        Grafo g = new Grafo(50,326);
        Creator(g);
        long t = System.currentTimeMillis();
        //
        g.Distancias();
        g.componentes();
        g.esArbol();
        g.Euleriano(50);
        g.Factorizable_1();
        g.Factorizable_2();
        g.R_Regular(50);
        g.Puentes();
        g.Hamiltoniano(50);
        g.Clique();
        g.Coloreado();
        //

        long d = (System.currentTimeMillis()-t);
        Gp = d;
        Texto.setText(Texto.getText().toString() + "Execution Time :" + d + " ms \n");
    }

        @Override
    public void onClick(View view) {
        //Texto.setText(Texto.getText().toString() + "c:");
           Execute();
            double d = MemoryUsage();
                  Texto.setText(Texto.getText().toString() + "RAM:" + (int)d + " MB"  + "\n");
            Texto.setText(Texto.getText().toString() + "----------------------------------------------------------\n");
    }

    public void ExecuterCod(){
        String Chars = "Arittakeno yume o kakiatsume sagashi mono sagashini yuku no sa ONE PIECE! "
                + "rashinban nante jyutai no moto netsu ni ukasare kaji o toru no sa HOKORI ka butteta "
                + "takara no chizu mo tashikameta no nara densetsu jyanai! kojin teki na arashi wa dareka no "
                + "BAIORIZUMU nokkatte omoi sugose ba ii arittakeno yume o kakiatsume sagashi mono sagashini yuku no sa "
                + "POKETO no COIN , soreto YOU WANNA BE MY FRIEND? WE ARE, WE ARE ON THE CRUISE! WE ARE! "
                + "zembu mani ukete shinji chattemo kata o osarete iippo LIDU sa kondo aetanara hanasu tsumorisa sore kara no koto to kore kara no koto"
                + "tsumari itsumo PINCHI wa dareka ni " +"APPEAL dekiru ii CHANSU " +"ji ishiki kajyoo ni! " +
                "shimittareta yoru o buttobase! " +"takara bako ni KYOUMI wa nai kedo POKETO ni roman, soreto " +
                "YOU WANNA BE MY FRIEND? WE ARE, WE ARE ON THE CRUISE! WE ARE! " +"arittakeno yume o kakiatsume " +
                "sagashi mono sagashini yuku no sa " +"POKETO no COIN , soreto " +"YOU WANNA BE MY FRIEND? " +"WE ARE, WE ARE ON THE CRUISE! "
                +"WE ARE! " +"WE ARE! WE ARE!";


        long Time = System.currentTimeMillis();
        Texto.setText(Texto.getText().toString() +"\nStart Codification \n");
        PolinomialCodification P = new PolinomialCodification();
        P.CodeWordsGenerator(P.BlocksWords(Chars.toCharArray()), new int[]{0,1,1,0,0,0,1});

        HammingCodification H = new HammingCodification();
        ArrayList ASCII = H.ToASCII(Chars);
        H.ArrayToString(ASCII, true);
        H.Codewords(ASCII);
        Cod = (System.currentTimeMillis()-Time);
        Texto.setText(Texto.getText().toString() +"Execution Time : " + Cod + " ms \n");


    }

    public double  RAM(){
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);
        double availableMegs = mi.availMem / 0x100000L;
        return availableMegs;
    }

    public long MemoryUsage(){
        final Runtime runtime = Runtime.getRuntime();
        final long usedMemInMB=(runtime.totalMemory() - runtime.freeMemory()) / 1048576L;
        final long maxHeapSizeInMB=runtime.maxMemory() / 1048576L;
        final long availHeapSizeInMB = maxHeapSizeInMB - usedMemInMB;
        return usedMemInMB;
    }



}
