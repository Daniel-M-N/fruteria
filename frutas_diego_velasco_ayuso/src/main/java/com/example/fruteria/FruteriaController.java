package com.example.fruteria;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.*;

public class FruteriaController {

    @FXML private TableView<Fruta> tableFrutas;
    @FXML private TableColumn<Fruta, String> colNombre;
    @FXML private TableColumn<Fruta, Double> colPrecioKg;
    @FXML private TableColumn<Fruta, Double> colStockKg;
    @FXML private TextField txtNombre;
    @FXML private TextField txtPrecioKg;
    @FXML private TextField txtStockKg;

    private ObservableList<Fruta> frutaData = FXCollections.observableArrayList();
    private Connection conn;

    // Método para inicializar la conexión a la base de datos
    public void initialize() {
        conectarBaseDatos();
        cargarFrutas();

        // Configurar la tabla
        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        colPrecioKg.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrecioKg()).asObject());
        colStockKg.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getStockKg()).asObject());
    }

    private void conectarBaseDatos() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:fruteria.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void cargarFrutas() {
        frutaData.clear();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM frutas");

            while (rs.next()) {
                frutaData.add(new Fruta(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("precioKg"), rs.getDouble("stockKg")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        tableFrutas.setItems(frutaData);
    }

    @FXML
    private void agregarFruta() {
        String nombre = txtNombre.getText();
        double precioKg = Double.parseDouble(txtPrecioKg.getText());
        double stockKg = Double.parseDouble(txtStockKg.getText());

        try {
            String sql = "INSERT INTO frutas (nombre, precioKg, stockKg) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombre);
            stmt.setDouble(2, precioKg);
            stmt.setDouble(3, stockKg);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        cargarFrutas();  // Recargar la lista de frutas
    }

    @FXML
    private void actualizarFruta() {
        Fruta selectedFruta = tableFrutas.getSelectionModel().getSelectedItem();
        if (selectedFruta != null) {
            String nombre = txtNombre.getText();
            double precioKg = Double.parseDouble(txtPrecioKg.getText());
            double stockKg = Double.parseDouble(txtStockKg.getText());

            try {
                String sql = "UPDATE frutas SET nombre = ?, precioKg = ?, stockKg = ? WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, nombre);
                stmt.setDouble(2, precioKg);
                stmt.setDouble(3, stockKg);
                stmt.setInt(4, selectedFruta.getId());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            cargarFrutas();  // Recargar la lista de frutas
        }
    }

    @FXML
    private void eliminarFruta() {
        Fruta selectedFruta = tableFrutas.getSelectionModel().getSelectedItem();
        if (selectedFruta != null) {
            try {
                String sql = "DELETE FROM frutas WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, selectedFruta.getId());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            cargarFrutas();  // Recargar la lista de frutas
        }
    }
}
