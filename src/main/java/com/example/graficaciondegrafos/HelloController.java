package com.example.graficaciondegrafos;

import com.example.graficaciondegrafos.AlgoritmoStrella.HasmapGrafoNodosDos;
import com.example.graficaciondegrafos.Models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;

public class HelloController implements Initializable {

    @FXML
    private Button btn_calcular;

    @FXML
    private Button circulo;

    @FXML
    private ComboBox<String> cmb_boxFin;

    @FXML
    private ComboBox<String> cmb_boxIncio;

    @FXML
    private Button limpiar;

    @FXML
    private Button linea;

    @FXML
    private AnchorPane root;
    @FXML
    private TableView<Nodo> tableview_lisAbierta;

    @FXML
    private TableView<Nodo> tableview_lisCerrada;

    @FXML
    private TableColumn<Nodo, String> tbl_fn_A;

    @FXML
    private TableColumn<Nodo, String> tbl_fn_C;

    @FXML
    private TableColumn<Nodo, String> tbl_n_A;

    @FXML
    private TableColumn<Nodo, String> tbl_n_C;
    @FXML
    private TableColumn<Nodo, String> tbl_p_A;
    @FXML
    private TableColumn<Nodo, String> tbl_p_C;
    private double x = 0;
    private double y = 0;
    private double xf = 0;
    private double yf = 0;
    @FXML
    private Button rutamascorta;
    boolean a = false;
    @FXML
    void onclick(ActionEvent event) { // B
        if(circulo == event.getSource()){
            a = true;
        }else{
            a = false;
        }
        if(limpiar == event.getSource()){
            root.getChildren().clear();
            tableview_lisAbierta.getItems().clear();
            tableview_lisCerrada.getItems().clear();
            getHashMap().clear();
            list.clear();
            count = 0;
            sol = 0;
            cmb_boxIncio.getItems().clear();
            cmb_boxFin.getItems().clear();
        }
    }

    @FXML
    void OnlickActionCalcular(ActionEvent event) {
        tableview_lisAbierta.getItems().clear();
        tableview_lisCerrada.getItems().clear();
       String nodoI = cmb_boxIncio.getSelectionModel().getSelectedItem();
       String nodoF = cmb_boxFin.getSelectionModel().getSelectedItem();
       reinicioColores();
       int indexF = indexLetra(nodoF);
       int indexI = indexLetra(nodoI);
        for (String clave : hashMap.keySet()) {
            // Obtén el valor asociado a la clave actual
            ObservableList<Nodo> lista = hashMap.get(clave);
            // Realiza alguna operación con la clave y la lista
            System.out.println("Clave: " + clave);
            for (Nodo no: lista) {
                System.out.println(no.getName());
            }
        }
       HasmapGrafoNodosDos hasmapGrafoNodosDos = new HasmapGrafoNodosDos();
       hasmapGrafoNodosDos.setHashMap(getHashMap());
       hasmapGrafoNodosDos.calcular_distanciasgn(getList(),indexF); // Indico donde estara mi nodo final que quiero calcular
       hasmapGrafoNodosDos.initializable_grafo(nodoI,indexI,nodoF,indexF);
       tableview_lisAbierta.setItems(hasmapGrafoNodosDos.getListaLibre());
       tableview_lisCerrada.setItems(hasmapGrafoNodosDos.getListCerrada());

       Stack <Nodo> a = hasmapGrafoNodosDos.lamejorRutaOptima(tableview_lisCerrada.getItems());
       System.out.println(a.size());
        for (int i= 0; i<root.getChildren().size();i++) {
            Node node = root.getChildren().get(i); // Obtiene el elemento en la posición i
            // Verifica si el elemento es un StackPane
            if (node instanceof StackPane) {
                StackPane stackPane = (StackPane) node; // Convierte si es un StackPane
                Label label = (Label) stackPane.getChildren().get(1);
                for(int j = 0; j<a.size();j++){
                        if(a.get(j).getName().equals(label.getText())){
                            Circle circle = (Circle) stackPane.getChildren().get(0); // Supongo que el círculo es el primer hijo del StackPane
                            circle.setFill(Color.AQUAMARINE); // Cambia el color del círculo a rojo
                            circle.setRadius(25); // Cambia el radio del círculo a 29 (ajusta según tus necesidades)
                        }
                    }
                }
        }

    }

    public void reinicioColores(){
        for (int i= 0; i<root.getChildren().size();i++) {
            Node node = root.getChildren().get(i); // Obtiene el elemento en la posición i
            // Verifica si el elemento es un StackPane
            if (node instanceof StackPane) {
                StackPane stackPane = (StackPane) node; // Convierte si es un StackPane
                Circle circle = (Circle) stackPane.getChildren().get(0); // Supongo que el círculo es el primer hijo del StackPane
                circle.setFill(Color.rgb(255,48,68,0.7)); // Cambia el color del círculo a rojo
                circle.setRadius(25); // Cambia el radio del círculo a 29 (ajusta según tus necesidades)
            }
        }
    }



    public int indexLetra(String nodoF){
        int index = -1;
        for(int i = 0; i< abecedario.length;i++){
            if(abecedario[i].equals(nodoF)){
                index = i;
                return index;
            }
        }
        return index;
    }
    private int count = 0;
    private String[] abecedario = {
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
            "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    };
    private int sol = 0;
    private double radius = 0;
    private String nameI;
    private String nameF;

    private ArrayList<Coordenadas> list = new ArrayList();

    public ArrayList<Coordenadas> getList() {
        return list;
    }

    public void setList(ArrayList<Coordenadas> list) {
        this.list = list;
    }

    @FXML
    void OnMouseClicked(MouseEvent event) {
        if(!a){
            Medio medio = (Medio) mediator.getMessage(component1);
            if(medio == null){
                return;
            }
            if(sol == 0){
                x = medio.getX() + radius;
                y = medio.getY() + radius;
                nameI = medio.getName();
            } else if (sol == 1) {
                xf = medio.getX() + radius;
                yf = medio.getY() +radius;
                nameF = medio.getName();
                double distance = Math.sqrt(Math.pow(xf - x, 2) + Math.pow(yf - y, 2));
                // Crear un formato para dos decimales
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                String dis = decimalFormat.format(distance);
                ConnectionLine connectionLine = new ConnectionLine(x,y,xf,yf,dis);
                root.getChildren().add(connectionLine); // Agregar al contenedor raíz (root)
                connectionLine.toBack();
                Nodo nodonew = new Nodo(nameI,"NUll",(double) 0,distance,(double)0);
                crearGrafohasmap(nameI,nameF,nodonew);
                sol = -1;
                // Cambio de 1 a -1 para reiniciar el proceso
            }
            sol++;
        }else{
            if(count>25){
                return;
            }
            if (event.getButton() == MouseButton.PRIMARY) {
                llenar_hasmap();
                cmb_boxIncio.getItems().add(abecedario[count]);
                cmb_boxFin.getItems().add(abecedario[count]);
                System.out.println(getHashMap().size());
                double x = event.getX();
                double y = event.getY();
                Label name = new Label(abecedario[count]);
                GraficacionGrafoNodo nodo = new GraficacionGrafoNodo(name,x,y);
                radius = nodo.getCircle().getRadius();
                root.getChildren().add(nodo.getStackPane());  // root es el contenedor donde deseas agregar los StackPane
                list.add(new Coordenadas(x,y,name.getText()));
            }
            count++;
        }
    }

    private HashMap<String, ObservableList<Nodo>> hashMap = new HashMap<>();

    public HashMap<String, ObservableList<Nodo>> getHashMap() {
        return hashMap;
    }
    public void setHashMap(HashMap<String, ObservableList<Nodo>> hashMap) {
        this.hashMap = hashMap;
    }

    public void crearGrafohasmap(String nodoI, String nodoF, Nodo nodo){
        try {
            Nodo nodoNew = nodo.clone();
            ObservableList<Nodo> observableListL = getHashMap().get(nodoI);
            nodo.setName(nodoF);
            boolean check = false;
            check = validarduplicidad(observableListL,nodoF);
            if(check != true){
                observableListL.add(nodo);
                getHashMap().put(nodoI,observableListL);
            }
            ObservableList<Nodo> observableListN = getHashMap().get(nodoF);
            nodoNew.setName(nodoI);
            check = validarduplicidad(observableListN,nodoI);
            if(check != true){
                observableListN.add(nodoNew);
                getHashMap().put(nodoF,observableListN);
            }
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean validarduplicidad(ObservableList<Nodo> observableListL, String nodo){
        for (Nodo n: observableListL) {
            if(n.getName().equals(nodo)){
                return true;
            }
        }
        return false;
    }

    public void llenar_hasmap(){
        ObservableList<Nodo> nodoObservableList = FXCollections.observableArrayList();
        hashMap.put(abecedario[count], nodoObservableList);
    }
    private Component component1;
    private Mediator mediator;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mediator = Mediator.getInstance();
        component1 = new Component(mediator, "Component2");
        mediator.add(component1);
        tbl_fn_A.setCellValueFactory(new PropertyValueFactory<>("fn"));
        tbl_n_A.setCellValueFactory(new PropertyValueFactory<>("name"));
        tbl_p_A.setCellValueFactory(new PropertyValueFactory<>("padre"));
        tbl_fn_C.setCellValueFactory(new PropertyValueFactory<>("fn"));
        tbl_n_C.setCellValueFactory(new PropertyValueFactory<>("name"));
        tbl_p_C.setCellValueFactory(new PropertyValueFactory<>("padre"));
    }

}