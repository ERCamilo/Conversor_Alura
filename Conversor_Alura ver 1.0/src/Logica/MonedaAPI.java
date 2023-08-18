package Logica;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

import javax.swing.JOptionPane;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MonedaAPI {

	static double[] exchangeRates;
	static String key = "";
	static String urlStr = "https://v6.exchangerate-api.com/v6/"+key+"/latest/USD";

	static void lastUpdateInLocalTime(JsonObject jsonObj) {
		long unixTime = jsonObj.get("time_last_update_unix").getAsLong();

		Date date = new Date(unixTime * 1000);

		SimpleDateFormat lastUpDate = new SimpleDateFormat("dd-MM-yyy hh:mm a");

		lastUpDate.setTimeZone(TimeZone.getDefault());

		String atLocalTime = lastUpDate.format(date).toString();


		System.out.println("Ultima actualizacion " + atLocalTime);
		System.out.println("");
		
		JOptionPane.showMessageDialog(null, "Ultima actualizacion " + atLocalTime);

	}

	@SuppressWarnings("deprecation")
	static JsonObject getJsonforAPI() {

		try {
			URL url = new URL(urlStr);
			HttpURLConnection request = (HttpURLConnection) url.openConnection();
			request.connect();

			// Convert to JSON
			JsonParser jp = new JsonParser();
			JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
			JsonObject jsonObj = root.getAsJsonObject();
			
			Util_GuardarJson.guardarArchivo(jsonObj);
			

			return jsonObj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	static double[] getExchangeRates(String[] Moneda) {
		if (exchangeRates != null) {
			return exchangeRates;
		} else {

			exchangeRates = new double[Moneda.length];
			try {

				JsonObject jsonObj = boulean();

				JsonObject conversionRates = jsonObj.getAsJsonObject("conversion_rates");

				for (int i = 0; i < Moneda.length; i++) {
					exchangeRates[i] = conversionRates.get(Moneda[i]).getAsDouble();

				}
				lastUpdateInLocalTime(jsonObj);
				System.out.println(Arrays.toString(Moneda));
				System.out.println(Arrays.toString(exchangeRates));
				System.out.println("");

				return exchangeRates;

			} catch (Exception e) {
				e.printStackTrace();
			}

			double [] $default = $default();

			return exchangeRates = $default;
		}

	}

	
	static double[] $default() {
		JOptionPane.showMessageDialog(null, "Hubo un error con el servidor");
		JOptionPane.showMessageDialog(null, "Se utilizaran la taza de cambido del 8/08/2023");

		System.out.println("Hubo un problema en la conexion con el servidor");
		System.out.println("Se utilizaran la taza de cambido del 8/08/2023");

		double[] $default = { 1.0, 0.9092, 0.7831, 142.3367, 1305.2606, 56.1174, 17.0678, 278.9805, 4.8766 };
		System.out.println(Arrays.toString($default));

		return exchangeRates = $default;
	}


	static JsonObject boulean () {
		
	
		if (Util_GuardarJson.obtenerJson()!= null) {
			System.out.println("Obteniendo datos de Archivo.json");
			return Util_GuardarJson.obtenerJson();
			
		}else {
			System.out.println("Obteniendo datos des de la API");
			return getJsonforAPI();
		}
	}
	
}

/**
 * ************************ public static void main(String[] args) {
 * MonedaAPI.getExchangeRates(); System.out.println("Tasa de cambio de USD a
 * EUR: " );
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
