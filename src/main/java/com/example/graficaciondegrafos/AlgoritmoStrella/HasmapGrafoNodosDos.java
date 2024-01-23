package com.example.graficaciondegrafos.AlgoritmoStrella;
import com.example.graficaciondegrafos.Models.Coordenadas;
import com.example.graficaciondegrafos.Models.Nodo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.util.*;
public class HasmapGrafoNodosDos {

    private ObservableList<Nodo> listaLibre = FXCollections.observableArrayList();
    private ObservableList<Nodo> listCerrada = FXCollections.observableArrayList();
    private ArrayList<Double> distanciahn;
    private HashMap<String, ObservableList<Nodo>> hashMap = new HashMap<>();
    public HasmapGrafoNodosDos() {
        distanciahn = new ArrayList();
    }

    public void calcular_distanciasgn(ArrayList<Coordenadas> coordenadas, int inicio){
        Coordenadas coordenadasF = coordenadas.get(inicio);
        double xf = coordenadasF.getX();
        double yf = coordenadasF.getY();
        for (int i = 0; i < coordenadas.size(); i++) {
            Coordenadas coordinated = coordenadas.get(i);
            double x = coordinated.getX();
            double y = coordinated.getY();
            double distance = Math.sqrt(Math.pow(xf - x, 2) + Math.pow(yf - y, 2));
            distanciahn.add(distance);
        }
        for (String clave : hashMap.keySet()) {
            ObservableList<Nodo> lista = hashMap.get(clave);
            for (int i = 0; i<lista.size();i++) {
                for(int j=0; j<distanciahn.size();j++){
                    if(lista.get(i).getName().equals(coordenadas.get(j).getName())){
                        lista.get(i).setHn(distanciahn.get(j));
                    }
                }
            }
        }
    }

    public void initializable_grafo(String nodoI,int noI, String nodoF, int noF) {
        double fn = 0;
        // declaracion del nodo origen de partida
        Nodo nodoOrigen = new Nodo(nodoI, "NULL", distanciahn.get(noI), (double) 0, distanciahn.get(noF));
        String padre = nodoOrigen.getName();
        int indiceInicial = nodoOrgienclave(nodoI);
        List<String> claves = new ArrayList<>(getHashMap().keySet());

        // Utiliza una cola de prioridad para mantener los nodos ordenados por fn
        PriorityQueue<Nodo> colaPrioridad = new PriorityQueue<>(Comparator.comparingDouble(Nodo::getFn));
        listaLibre.add(nodoOrigen);
        colaPrioridad.add(nodoOrigen);

        float acumulador = 0;
        int nuevo = -1;
        // Inicio de *a
        for (int i = indiceInicial; i < claves.size(); i++) {
            // PASO 3: retornar error si la lista esta vacia
            if(listaLibre.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.WARNING,"ERROR LIST EMPTY");
                alert.show();
                System.out.println("ERROR LIST EMPTY");
                return;
            }
            // PASO 4: Obtener el nodo con el valor fn más bajo de la cola de prioridad
            Nodo hijo = colaPrioridad.poll();
            if (hijo != null) {
                // PASO 5: mover n a la lista cerrada
                colaPrioridad.remove(hijo);
                listaLibre.remove(hijo);
                listCerrada.add(hijo);
                padre = hijo.getName();
                nuevo = nodoOrgienclave(padre);
                acumulador += hijo.getGn();
                if (nuevo == -1) {
                    System.out.println(i+" ERROR INESPERADO");
                    break;
                }
            }
            String clave = claves.get(nuevo);
            if (clave.equals(nodoF)) { // Indicador de que encontro el camino
                System.out.println("Encontré todo");
                break;
            }
            i = nuevo - 1;
            ObservableList<Nodo> valor = getHashMap().get(clave);
            for (int j = 0; j < valor.size(); j++) {
                boolean nodoEnAbierta = false;
                boolean nodoEnCerrada = false;
                Nodo nodosGrafo = null;
                for (Nodo nodoL : listaLibre) {
                    if (nodoL.getName().equals(valor.get(j).getName())) {
                        nodoEnAbierta = true;
                        nodosGrafo = nodoL;
                        break;
                    }
                }
                for (Nodo nodoC : listCerrada) {
                    if (nodoC.getName().equals(valor.get(j).getName())) {
                        nodoEnCerrada = true;
                        break;
                    }
                }
                double gn = valor.get(j).getGn() + acumulador;
                fn = gn + valor.get(j).getHn();
                valor.get(j).setFn(fn);
                valor.get(j).setPadre(padre);
                if (nodoEnAbierta == false && nodoEnCerrada == false) {
                    listaLibre.add(valor.get(j));
                    colaPrioridad.add(valor.get(j)); // Agregar a la cola de prioridad
                } else if (nodoEnAbierta == true && nodoEnCerrada == false) {
                    if(nodosGrafo!=null){
                        int indez = listaLibre.indexOf(nodosGrafo);
                        if (indez != -1 && valor.get(j).getFn() < listaLibre.get(indez).getFn()) {
                            listaLibre.set(indez, valor.get(j));
                            colaPrioridad.add(valor.get(j)); // Agregar a la cola de prioridad
                        }
                    }
                }
            }
        }
    }

    public int nodoOrgienclave(String pass) {
        String claveBuscada = pass;
        int indiceEncontrado = -1;
        int currentIndex = 0;
        for (String clave : getHashMap().keySet()) {
            if (clave.equals(claveBuscada)) {
                indiceEncontrado = currentIndex;
                break;
            }
            currentIndex++;
        }
        return indiceEncontrado;
    }

    public Stack<Nodo> lamejorRutaOptima(ObservableList<Nodo> listCerrada){
        Stack <Nodo> stack = new Stack();
        String padre = listCerrada.get(listCerrada.size()-1).getPadre();;
        for(int i = listCerrada.size()-1; i>=0;i--){
            if(listCerrada.size()-1 == i){
                stack.add(listCerrada.get(i));
            }
            if(listCerrada.get(i).getName().equals(padre)){
                System.out.println(listCerrada.get(i).getName()+ i);
                stack.add(listCerrada.get(i));
                padre = listCerrada.get(i).getPadre();
            }
            if(listCerrada.get(i).getPadre().equals("NULL")){
                stack.add(listCerrada.get(i));
                return stack;
            }

        }
        return stack;
    }

    public HashMap<String, ObservableList<Nodo>> getHashMap() {
        return hashMap;
    }

    public void setHashMap(HashMap<String, ObservableList<Nodo>> hashMap) {
        this.hashMap = hashMap;
    }

    public ObservableList<Nodo> getListaLibre() {
        return listaLibre;
    }

    public void setListaLibre(ObservableList<Nodo> listaLibre) {
        this.listaLibre = listaLibre;
    }

    public ObservableList<Nodo> getListCerrada() {
        return listCerrada;
    }

    public void setListCerrada(ObservableList<Nodo> listCerrada) {
        this.listCerrada = listCerrada;
    }
}

