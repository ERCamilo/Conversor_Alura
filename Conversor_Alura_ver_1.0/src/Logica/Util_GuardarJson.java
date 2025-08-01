package Logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Util_GuardarJson {

	public static String getUbicacion() {
		try {
			String jarPath = Util_GuardarJson.class.getProtectionDomain().getCodeSource().getLocation().toURI()
					.getPath();
			String jsonFilePath = new File(jarPath).getParent() + File.separator + "archivo.json";
			return jsonFilePath;
		} catch (URISyntaxException e) {

			e.printStackTrace();
			return null;
		}
	}

	public static void guardarArchivo(JsonObject jsonObject) {

		Gson gson = new Gson();
		String jsonString = gson.toJson(jsonObject);

		try (FileWriter writer = new FileWriter(getUbicacion())) {
			writer.write(jsonString);
			System.out.println("JSON guardado en el archivo: " + getUbicacion());

		} catch (IOException e) {
			
		}

	}

	public static JsonObject obtenerJson() {

		try (BufferedReader reader = new BufferedReader(new FileReader(getUbicacion()))) {
			StringBuilder jsonContent = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				jsonContent.append(line);
			}
			Gson gson = new Gson();
			return gson.fromJson(jsonContent.toString(), JsonObject.class);
		} catch (IOException e) {
			e.printStackTrace();

			return null;
		}

	}

}
