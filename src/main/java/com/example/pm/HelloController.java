package com.example.pm;

import Domain.Piesa;
import Repository.ExceptionRepository;
import Repository.IRepository;
import Service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class HelloController {
    Service service;
    ObservableList<Piesa> pieseList;

    @FXML
    private Button addButton;

    @FXML
    private TextField durataTextField;

    @FXML
    private TextField formatieTextField;

    @FXML
    private TextField genTextField;

    @FXML
    private ListView<Piesa> pieseListView;

    @FXML
    private TextField titluTextField;

    @FXML
    private Label welcomeText;

    @FXML
    void onAddButtonMouseClicked(MouseEvent event) throws ExceptionRepository {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Input");
        alert.setHeaderText(null);

        int maxId = 0;
        for (Piesa p : pieseList) {
            if (p.getId() > maxId) {
                maxId = p.getId();
            }
        }
        int id = maxId + 1;
        String titlu = titluTextField.getText();
        String formatie = formatieTextField.getText();
        String gen = genTextField.getText();
        String durata = durataTextField.getText();

        if (Objects.equals(titlu, "") || Objects.equals(formatie, "") || Objects.equals(gen, "") || Objects.equals(durata, "")) {
            alert.setContentText("Ati lasat unul sau mai multe campuri necompletate");
            alert.showAndWait();
            return;
        }

        Piesa p = new Piesa(id, formatie, titlu, gen, durata);

        String[] durata1 = p.getDurata().split(":");
        int minute = Integer.parseInt(durata1[0]);
        int secunde = Integer.parseInt(durata1[1]);

        if (minute < 0 || minute > 10) {
            alert.setContentText("Durata trebuie sa fie intre 0 si 10 minute");
            alert.showAndWait();
            return;
        }

        service.add(p);
        pieseList.add(p);
        pieseList.sort(new Comparator<Piesa>() {
            @Override
            public int compare(Piesa o1, Piesa o2) {
                if (o1.getFormatie().compareTo(o2.getFormatie()) == 0) {
                    String[] durata1 = o1.getDurata().split(":");
                    String[] durata2 = o2.getDurata().split(":");
                    int durata1Int = Integer.parseInt(durata1[0]) * 60 + Integer.parseInt(durata1[1]);
                    int durata2Int = Integer.parseInt(durata2[0]) * 60 + Integer.parseInt(durata2[1]);
                    return durata1Int - durata2Int;
                }
                return o1.getFormatie().compareTo(o2.getFormatie());
            }
        });
        pieseListView.setItems(pieseList);
    }
    @FXML
    private Button listaRedareButton;

    @FXML
    private TextField listaRedareTextField;
    @FXML
    private ListView<Piesa> redareListView;

    @FXML
    void onListaRedareButtonClicked(MouseEvent event) {
        ArrayList<Piesa> piese = new ArrayList<>();
        pieseList = FXCollections.observableArrayList(service.getAll());
        int timpLista = 0;
        int tryed = 10000;
        while (timpLista < 15 && tryed > 0) {
            int randomId = (int) (Math.random() * pieseList.size());
            Piesa p = pieseList.get(randomId);
            String lastMusicGent = "";
            if (piese.size() > 0) {
                lastMusicGent = piese.get(piese.size() - 1).getGenMuzical();
            }
            if (Objects.equals(lastMusicGent, p.getGenMuzical())) {
                tryed--;
                continue;
            }
            String[] durata1 = p.getDurata().split(":");
            int minute = Integer.parseInt(durata1[0]);
            int secunde = Integer.parseInt(durata1[1]);
            if (timpLista + minute <= 15) {
                piese.add(p);
                timpLista += minute;
            }
            tryed--;
        }

        if (timpLista < 15) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lista prea scurta");
            alert.setHeaderText(null);
            alert.setContentText("Nu exista suficiente melodii pentru a crea o lista de redare de 15 minute");
            alert.showAndWait();
            return;
        } else {
            ObservableList<Piesa> pieseRedare = FXCollections.observableArrayList(piese);
            redareListView.setItems(pieseRedare);
        }
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    public HelloController(Service service) {
        this.service = service;
    }
    public void initialize() {
        pieseList = FXCollections.observableArrayList(service.getAll());
        pieseList.sort(new Comparator<Piesa>() {
            @Override
            public int compare(Piesa o1, Piesa o2) {
                if (o1.getFormatie().compareTo(o2.getFormatie()) == 0) {
                    String[] durata1 = o1.getDurata().split(":");
                    String[] durata2 = o2.getDurata().split(":");
                    int durata1Int = Integer.parseInt(durata1[0]) * 60 + Integer.parseInt(durata1[1]);
                    int durata2Int = Integer.parseInt(durata2[0]) * 60 + Integer.parseInt(durata2[1]);
                    return durata1Int - durata2Int;
                }
                return o1.getFormatie().compareTo(o2.getFormatie());
            }
        });
        pieseListView.setItems(pieseList);
    }
}