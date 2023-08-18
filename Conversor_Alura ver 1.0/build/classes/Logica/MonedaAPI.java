package Logica;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class MonedaAPI {

    public static double[] exchangeRates;
    static String urlStr = "https://v6.exchangerate-api.com/v6/c240a8b2c00809914de7aba0/latest/USD";

    public static double[] getExchangeRates(String[] Moneda) {
        if (exchangeRates != null) {
            return exchangeRates;
        } else {

            exchangeRates = new double[Moneda.length];
            try {
                // Making Request
                URL url = new URL(urlStr);
                HttpURLConnection request = (HttpURLConnection) url.openConnection();
                request.connect();

                // Convert to JSON
                JsonParser jp = new JsonParser();
                JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
                JsonObject jsonObj = root.getAsJsonObject();

                // Accessing conversion rates object
                JsonObject conversionRates = jsonObj.getAsJsonObject("conversion_rates");

                // Store conversion rates in a map
                for (int i = 0; i < Moneda.length; i++) {
                    exchangeRates[i] = conversionRates.get(Moneda[i]).getAsDouble();

                }
                System.out.println(Arrays.toString(exchangeRates));

                return exchangeRates;

            } catch (Exception e) {
                e.printStackTrace();
            }

            JOptionPane.showMessageDialog(null, "Hubo un error con el servidor");
            System.out.println("Hubo un problema en la conexion con el servidor");
            System.out.println("Se utilizaran la taza de cambido del 8/08/2023");

             double [] $default={1.0, 0.9092, 0.7831, 142.3367, 1305.2606, 56.1174, 17.0678, 278.9805, 4.8766};
             return exchangeRates = $default;
        }
    }
}

/**
 * ************************
 * public static void main(String[] args) { MonedaAPI.getExchangeRates();
 * System.out.println("Tasa de cambio de USD a EUR: " );
 *
 * double[]taza = MonedaAPI.getExchangeRates();
 *
 *
 * JComboBox<String> box1 = new JComboBox<>(monedaAPI.MONEDA); JComboBox<String>
 * box2 = new JComboBox<>(monedaAPI.MONEDA);
 *
 * JOptionPane.showOptionDialog(null, box1, "Conversor", JOptionPane.OK_OPTION,
 * JOptionPane.QUESTION_MESSAGE, null, null, null);
 *
 * int a= box1.getSelectedIndex();
 *
 * JOptionPane.showOptionDialog(null, box2, "Conversor", JOptionPane.OK_OPTION,
 * JOptionPane.QUESTION_MESSAGE, null, null, null); int b =
 * box2.getSelectedIndex();
 *
 * double c = Double.parseDouble( JOptionPane.showInputDialog("Ingrese Monto"));
 *
 * double resultado = c*taza[b]/taza[a];
 *
 *
 * JOptionPane.showConfirmDialog(null, " El resultado es : "+resultado);
 *
 *
 *
 *
 * } }
 */
