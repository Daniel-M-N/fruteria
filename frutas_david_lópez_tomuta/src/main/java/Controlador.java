import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Controlador {
    @FXML private TableView<Fruta> tablaFrutas;
    @FXML private TableColumn<Fruta, String> colNombre;
    @FXML private TableColumn<Fruta, Double> colPrecio;
    @FXML private TableColumn<Fruta, Integer> colCantidad;

    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

        tablaFrutas.getItems().addAll(obtenerFrutas());
    }

    public ArrayList<Fruta> obtenerFrutas() {
        ArrayList<Fruta> lista = new ArrayList<>();
        try (Connection conn = Conexion.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM frutas")) {

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                double precio = rs.getDouble("precio");
                int cantidad = rs.getInt("cantidad");
                lista.add(new Fruta(nombre, precio, cantidad));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
