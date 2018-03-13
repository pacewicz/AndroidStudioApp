package acevedochipasociados.androidstudioapp;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by digital on 11/03/2018.
 */

public class Grafo {
    static boolean[][] Adj;
    int Vertices, Aristas;
    boolean visitado[];
    ArrayList<Integer> VerticesCorte;
    ArrayList<ArrayList<Integer>> AdjL;
    int Pares = 0, Impares = 0, Mayor = 0;
    int[] Conjunto;
    String[] Enlaces;
    boolean EsConexo = false, EsRegular = false, EsBipartido = false, EsArbol = false;

    public Grafo(int v, int a) {
        this.Vertices = v;
        this.Aristas = a;
        Enlaces = new String[a + 1];
        Adj = new boolean[v + 1][v + 1];
        Conjunto = new int[Vertices + 1];
    }
    int h = 1;

    public void CrearGrafo(int a, int b) {

        Adj[b][a] = true;
        Adj[a][b] = true;
        Enlaces[h] = a + "-" + b;
        h++;
    }

    public String Factorizable_1() {
        if (EsConexo) {
            if (EsBipartido && EsRegular && Mayor > 1) {
                return "Es 1-Factorizable";
            } else if (EsRegular && Mayor == Vertices - 1 && Vertices % 2 == 0) {
                return "Es 1-Factorizable";
            }
        }
        return "No es 1-Factorizable";
    }

    public String Factorizable_2() {
        if (EsConexo) {
            if (EsRegular && Mayor % 2 == 0) {
                return "Es 2-Factorizable";
            }
        }
        return "No es 2-Factorizable";
    }

    public boolean Adyacencia(ArrayList<Integer> Vertices, int Clique) {
        int Nodos = 0;
        for (int i = 0; i < Vertices.size(); i++) {
            int Grados = 0;
            for (int j = 0; j < Vertices.size(); j++) {
                if (i != j) {
                    if (Adj[Vertices.get(i)][Vertices.get(j)]) {
                        Grados++;
                    }
                }
            }
            if (Grados >= Clique - 1) {
                Nodos++;
            }
        }
        return Nodos == Clique;

    }

    public boolean Completo(ArrayList<Integer> Nodos, int Clique) {
        for (int i = 0; i < Nodos.size(); i++) {
            ArrayList<Integer> Noditos = new ArrayList();
            Noditos.add(Nodos.get(i));
            for (int j = 0; j < Nodos.size(); j++) {
                if (i != j) {
                    if (Adj[Nodos.get(i)][Nodos.get(j)]) {
                        Noditos.add(Nodos.get(j));
                    }
                }
            }
            if (Noditos.size() >= Clique && Adyacencia(Noditos, Clique)) {
                return true;
            }
        }
        return false;
    }

    public int Clique() {
        int Clique = Mayor(Vertices) + 1;

        if (EsArbol) {
            return 2;
        }
        if (EsRegular && Grado(1, Vertices) == Vertices - 1) {
            return Clique;
        } else {
            Clique--;
            int Grado = Clique - 1;
            while (Grado >= 2) {
                ArrayList<Integer> Pivotes = new ArrayList();
                for (int i = 1; i <= Vertices; i++) {
                    if (Grado(i, Vertices) >= Grado) {
                        Pivotes.add(i);
                    }
                }
                if (Completo(Pivotes, Clique)) {
                    return Clique;
                }
                Grado = Grado - 1;
                Clique = Clique - 1;
            }
        }
        return Clique;
    }

    public String Euleriano(int Vertices) {
        if (EsConexo) {
            if (Menor(Vertices) == 1) {
                return "No es Euleriano";
            }
            if (EsArbol) {
                return "No es Euleriano";
            }
            for (int i = 1; i <= Vertices; i++) {
                if (Grado(i, Vertices) % 2 != 0) {
                    return "No es Euleriano";
                }
            }
            return "Es Euleriano";
        } else {
            return "No es Euleriano";
        }
    }

    public String Hamiltoniano(int Vertices) {
        if (EsConexo) {
            String Si = "Es Hamiltoniano";
            String No = "No " + Si;
            if (Impares / 2 == Vertices) {
                return Si;
            }
            if (Pares == Impares) {
                return Si;
            }
            if (Menor(Vertices) == 1) {
                return No;
            }
            if (EsArbol) {
                return No;
            }
            if (EsRegular) {
                return Si;
            }

            for (int i = 1; i <= Vertices; i++) {
                if (Grado(i, Vertices) < Vertices / 2) {
                    return No;
                }
            }
            return Si;
        } else {
            return "No es Hamiltoniano";
        }
    }

    public String R_Regular(int Vertices) {
        if (Mayor(Vertices) == Menor(Vertices)) {
            EsRegular = true;
            return "Es " + Grado(1, Vertices) + "-Regular";
        } else {
            return "No es Regular";
        }
    }

    public int Grado(int nodo, int v) {
        int cont = 0;
        for (int i = 1; i <= v; i++) {
            if (Adj[i][nodo] == true) {
                cont++;
            }
        }

        return cont;
    }

    public int Mayor(int v) {
        int mayor = -1;
        for (int i = 1; i <= v; i++) {

            if (Grado(i, v) > mayor) {
                mayor = Grado(i, v);
            }
            if (Grado(i, v) % 2 == 0) {
                Pares += 1;
            } else {
                Impares += 1;
            }
        }
        Mayor = mayor;
        return mayor;
    }

    public int Menor(int v) {
        int menor = 8000;
        for (int i = 1; i <= v; i++) {

            if (Grado(i, v) < menor) {
                menor = Grado(i, v);
            }
        }
        return menor;

    }

    public void DFS(int v) {
        visitado[v] = true;
        for (int i = 1; i <= Vertices; ++i) {
            if (Adj[v][i] && !visitado[i]) {
                DFS(i);
            }
        }
    }

    public void DFSBipartido(int v, int C) {

        visitado[v] = true;
        Conjunto[v] = C;
        for (int i = 1; i <= Vertices; ++i) {
            if (Adj[v][i] == true && visitado[i] == false) {
                if (C == 1) {
                    DFSBipartido(i, 2);
                } else {
                    DFSBipartido(i, 1);
                }
            }
        }
    }
    int Ind = 0;

    public void DFSIndependiente(int v) {

        visitado[v] = true;
        Ind++;
        for (int i = 1; i <= Vertices; ++i) {
            if (Adj[v][i] == true) {
                visitado[i] = true;
            }
        }
        int j = 1, k = 1, menor = Aristas + 1, indice = 0;
        while (k <= Vertices) {
            while (j <= Vertices) {
                if (Adj[k][j] == true && visitado[j] == false) {
                    int grado = Grado(j, Vertices);
                    if (grado < menor) {
                        menor = grado;
                        indice = j;
                    }
                }
                j++;
            }
            if (visitado[k] == false) {
                int grado = Grado(k, Vertices);
                if (grado < menor) {
                    menor = grado;
                    indice = k;
                }
            }
            k++;
        }
        if (indice != 0) {
            DFSIndependiente(indice);
        }
    }
    boolean[] Avisitada;
    String[] Saturados;

    public String EmparejamientoPerfecto() {
        boolean Empa = false;
        String Respuesta = "";
        if (Vertices % 2 == 0 && EsBipartido == false) {
            Respuesta = "Si tiene Emparejamiento Perf";
        } else //Saturados=new String[Vertices+1];
        {
            if (EsBipartido == true && EsRegular == true) {
                Respuesta = "Si tiene Emparejamiento Perf";
            } else {
                int i = 1;
                while (i <= Vertices && Empa == false) {
                    Sat = 0;
                    Saturados = new String[Vertices + 1];
                    Avisitada = new boolean[Aristas + 1];
                    for (int j = 1; j <= Aristas; j++) {
                        Avisitada[j] = false;
                    }
                    RecorrerAristas(i);
                    if (Sat == Vertices) {
                        Empa = true;
                    } else {
                        i++;
                    }
                }

                //int i=1;
                /*while(Empa==true && i<=Vertices){
            int k=1;
            boolean Esta=false;
            while(Esta==false && k<=Sat){
                if (Integer.parseInt(Saturados[k]) == i ) {
                    Esta=true;
                }
                k++;
            }
            if (Esta==false) {
                Empa=false;
            }else{
            i++;
            }
        }*/
                if (Empa) {
                    Respuesta = "Si tiene Emparejamiento Perf";
                } else {
                    Respuesta = "No tiene Emparejamiento Perf";
                }
            }
        }
        return Respuesta;
    }
    int Sat = 0;

    public void RecorrerAristas(int A) {
        Avisitada[A] = true;
        String[] Nodos = Enlaces[A].split("-");
        String nA = Nodos[0], nB = Nodos[1];
        boolean Swesta = false;
        int k = 1;
        while (k <= Sat && Swesta == false) {
            if (nA.equals(Saturados[k])) {
                Swesta = true;
            }
            k++;
        }
        if (Swesta == false) {

            Swesta = false;
            k = 1;
            while (k <= Sat && Swesta == false) {
                if (nB.equals(Saturados[k])) {
                    Swesta = true;
                }
                k++;
            }
            if (Swesta == false) {
                Sat++;
                Saturados[Sat] = nA;
                Sat++;
                Saturados[Sat] = nB;

            }
        }
        for (int i = 1; i <= Aristas; i++) {
            if (i != A) {
                Nodos = Enlaces[i].split("-");
                if (!Nodos[0].equals(nA) && !Nodos[1].equals(nB) && Avisitada[i] == false) {
                    RecorrerAristas(i);

                }
            }
        }
    }

    public void DFSAdjL(int v) {

        visitado[v] = true;
        int size = AdjL.get(v).size();
        for (int i = 0; i < size; ++i) {
            int ad = AdjL.get(v).get(i);
            if (!visitado[ad]) {
                DFS(ad);
            }
        }
    }

    public int Puentes() {
        int Puentes = 0;
        for (int i = 1; i <= Aristas; i++) {
            String[] Nodos = Enlaces[i].split("-");
            String nA = Nodos[0], nB = Nodos[1];
            Adj[Integer.parseInt(nA)][Integer.parseInt(nB)] = false;
            Adj[Integer.parseInt(nB)][Integer.parseInt(nA)] = false;
            String Puente = esconexo();
            if (!Puente.equals("Es Conexo")) {
                Puentes++;
            }
            ReiniciarAdj();
        }
        ReiniciarAdj();
        return Puentes;

    }

    public int VerticesCorte() {
        VerticesCorte = new ArrayList();
        int VerticesCortes = 0, NumVertices = Vertices;
        String[] Enlacescorte = new String[Aristas * 2];
        //int f;
        for (int k = 1; k <= Vertices; k++) {
            //f=0;
            boolean[][] AdjCorte = new boolean[Vertices + 1][Vertices + 1];
            int Posv = 0;
            for (int i = 1; i <= Vertices; i++) {
                if (i != k) {
                    Posv++;
                    int Posa = 0;
                    for (int j = 1; j <= Vertices; j++) {
                        if (j != k) {
                            Posa++;
                            /*if (Adj[i][j]==true) {
                                      f++;
                                      Enlacescorte[f]= i + "-" + j;
                                    }*/
                            AdjCorte[Posv][Posa] = Adj[i][j];
                        }
                    }
                }
            }
            /*for (int i = 1; i <= f; i++) {
               System.out.println(Enlacescorte[i]);
           }*/
            Adj = AdjCorte;
            Vertices = Vertices - 1;
            String Corte = esconexo();
            if (!Corte.equals("Es Conexo")) {
                VerticesCortes++;
                VerticesCorte.add(k);

            }
            ReiniciarAdj();
            Vertices = NumVertices;
        }
        ReiniciarAdj();
        Vertices = NumVertices;
        return VerticesCortes;
    }

    public void ReiniciarAdj() {
        for (int i = 1; i <= Vertices; i++) {
            for (int j = 1; j <= Vertices; j++) {
                Adj[i][j] = false;
            }
        }
        for (int i = 1; i <= Aristas; i++) {
            String[] Nodos = Enlaces[i].split("-");
            String nA = Nodos[0], nB = Nodos[1];
            Adj[Integer.parseInt(nA)][Integer.parseInt(nB)] = true;
            Adj[Integer.parseInt(nB)][Integer.parseInt(nA)] = true;
        }
    }

    public String esconexo() {
        visitado = new boolean[Vertices + 1];
        DFS(1);
        for (int i = 1; i <= Vertices; i++) {
            if (!visitado[i]) {
                return "No es Conexo k(g)= " + componentes();
            }
        }
        EsConexo = true;
        return "Es Conexo";
    }

    public int componentes() {
        int comp = 0;
        visitado = new boolean[Vertices + 1];
        for (int i = 1; i <= Vertices; i++) {
            if (!visitado[i]) {
                DFS(i);
                comp++;
            }
        }
        return comp;
    }

    public String Bipartido() {
        String Bipartido = "No es Bipartido";
        if (EsArbol) {
            Bipartido = "Es Bipartido";
            EsBipartido = true;
        } else if (EsConexo) {
            int l=1;
            boolean swConjuntos = false;
            while(swConjuntos==false && l<=Vertices){
                for (int i = 1; i <= Vertices; i++) {
                    visitado[i] = false;
                }
                for (int i = 10; i <= Vertices; i++) {
                    Conjunto[i]=0;
                }
                DFSBipartido(l, 1);
                int i = 1;
                swConjuntos=true;
                while (i <= Vertices && swConjuntos == true) {
                    int k = 1;
                    while (k <= Vertices && swConjuntos == true) {
                        if (Adj[i][k]) {
                            if (Conjunto[i] == Conjunto[k]) {
                                swConjuntos = false;
                            }
                        }
                        k++;
                    }
                    i++;
                }
                if (swConjuntos) {
                    Bipartido = "Es Bipartido";
                    EsBipartido = true;

                }else{
                    l++;
                }
            }
        }

        return Bipartido;
    }

    boolean swBuscaCiclo = true;
    int NumCiclo = 0;
    int CicloN = 0;
    int[] NumeroCiclo = new int[1000000];




    public String esArbol() {
        if (EsConexo && Aristas == Vertices - 1) {
            return "Es un Arbol";
        }
        return "No es Arbol";
    }

    public int Coloreado() {
        int color = 1;
        int[] Coloreado = new int[Vertices + 1];
        if (EsRegular && Mayor == (Vertices - 1)) {
            color = Vertices;
        } else if (EsBipartido) {
            color = 2;
        } else {

            LinkedList<Integer> NodosColor = Gradientes();
            for (int i = 1; i <= Vertices; i++) {
                Coloreado[i] = 0;
            }
            for (int i = 0; i < Vertices; i++) {
                LinkedList<Integer> colorAdj = new LinkedList();
                for (int j = 1; j <= Vertices; j++) {
                    if (Adj[NodosColor.get(i)][j] == true) {
                        if (Coloreado[j] > 0) {
                            colorAdj.add(Coloreado[j]);
                        }
                    }
                }
                if (colorAdj.isEmpty()) {
                    Coloreado[NodosColor.get(i)] = 1;
                } else {
                    int colorG = 1;
                    boolean Noesta = false;
                    while (Noesta == false) {
                        if (!colorAdj.contains(colorG)) {
                            Noesta = true;
                            Coloreado[NodosColor.get(i)] = colorG;
                        } else {
                            colorG++;
                            if (colorG > color) {
                                color = colorG;
                            }
                        }
                    }
                }
            }

        }

        return color;
    }

    public LinkedList<Integer> Gradientes() {
        LinkedList<Integer> VerticesG = new LinkedList();
        int mayor = -1, Indice = 0;
        for (int j = 1; j <= Vertices; j++) {
            int Num = Grado(j, Vertices);
            if (Num > mayor) {
                mayor = Num;
                Indice = j;
            }
        }
        VerticesG.add(Indice);
        for (int i = 1; i <= Vertices; i++) {
            mayor = -1;
            for (int j = 1; j <= Vertices; j++) {
                if (!VerticesG.contains(j)) {
                    int Num = Grado(j, Vertices);
                    if (Num > mayor) {
                        mayor = Num;
                        Indice = j;
                    }
                }
            }
            if (mayor != -1) {
                VerticesG.add(Indice);
            }

        }

        return VerticesG;
    }

    int[][] d;

    public void BFS(int ini) {
        LinkedList<pair> cola = new LinkedList();
        cola.add(new pair(ini, 0));
        visitado = new boolean[Vertices + 1];
        visitado[ini] = true;
        while (!cola.isEmpty()) {
            pair v = cola.poll();
            //System.out.println("Estoy en el vertice " + v.a + " con distancia " + v.b);
            d[v.a][ini] = v.b;
            d[ini][v.a] = v.b;
            for (int i = 1; i <= Vertices; ++i) {
                if (Adj[i][v.a] && !visitado[i]) {
                    cola.add(new pair(i, v.b + 1));
                    visitado[i] = true;
                }
            }
        }
    }

    public String adyacencia() {
        String dat = "";
        int[][] Adyacencia = new int[Vertices + 1][Vertices + 1];
        for (int i = 1; i <= Vertices; i++) {
            for (int j = 1; j <= Vertices; j++) {
                if (Adj[i][j]) {
                    Adyacencia[i][j] = 1;
                }
            }
        }
        for (int x = 1; x < Adyacencia.length; x++) {
            dat += "";
            for (int y = 1; y < Adyacencia.length; y++) {
                dat += Adyacencia[x][y];
                if (y != Adyacencia[x].length - 1) {
                    dat += " ";
                }
            }
            dat += "\n";
        }
        return dat;
    }
    public void Distancias() {
        d = new int[Vertices + 1][Vertices + 1];
        for (int i = 1; i <= Vertices; i++) {
            BFS(i);
        }
    }

}

class pair {

    int a, b;

    public pair(int a, int b) {
        this.a = a;
        this.b = b;
    }
}
