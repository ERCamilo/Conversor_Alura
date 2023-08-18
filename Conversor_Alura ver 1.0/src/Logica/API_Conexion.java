package Logica;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import javax.swing.JOptionPane;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class API_Conexion {

	static String key = "c240a8b2c00809914de7aba0";
	static String urlStr = "https://v6.exchangerate-api.com/v6/" + key + "/latest/USD";

	static String getFormatoTiempo(long milisegundos) {

		long segundos = milisegundos / 1000;
		long minutos = segundos / 60;
		segundos %= 60;
		long horas = minutos / 60;
		minutos %= 60;

		String formatoTiempo = String.format("%02d:%02d:%02d", horas, minutos, segundos);
		return formatoTiempo;
	}

	static boolean needtoUpdate(JsonObject jsonObj) {
		try {
			long unixLastTime = jsonObj.get("time_last_update_unix").getAsLong();
			long unixNextTime = jsonObj.get("time_next_update_unix").getAsLong();

			Date dateLast = new Date(unixLastTime * 1000);
			Date dateNext = new Date(unixNextTime * 1000);
			Date dateNow = new Date();

			// long nextUpdate = dateNext.getTime() - dateNow.getTime();
			long lastUpdate = dateNow.getTime() - dateLast.getTime();

			String a ="Tiempo transcurrido desde la última  actualización de la base de datos (" + getFormatoTiempo(lastUpdate) + ")";
			
			JOptionPane.showInternalMessageDialog(null, a);
			System.out.println(jsonObj.get("time_last_update_utc").toString());
			System.out.println(a);
			// System.out.println("Tiempo faltante para la proxima actualización ("
			// + getFormatoTiempo(nextUpdate) + ")");

			boolean updateNeeded = dateNext.getTime() <= dateNow.getTime();
			return updateNeeded;
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
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

	static JsonObject getFrom_ArchivoJson() {

		if (Util_GuardarJson.obtenerJson() != null && !needtoUpdate(Util_GuardarJson.obtenerJson())) {
			System.out.println("Obteniendo datos de Archivo.json");
			return Util_GuardarJson.obtenerJson();

		} else {
			System.out.println("Obteniendo datos desde la API");
			return getJsonforAPI();
		}
	}

}
