package Logica;

import java.util.Arrays;
import javax.swing.JOptionPane;
import com.google.gson.JsonObject;

public class MonedaAPI {

	static double[] exchangeRates;

	static boolean exRatesEsNull() {

		boolean esNull = (exchangeRates == null);
		exchangeRates = (!esNull) ? exchangeRates : new double[MagUtil.Moneda.length];
		return esNull;

	}
	

	static double[] getExchangeRates(String[] Moneda) {

		if (!exRatesEsNull()) {
			return exchangeRates;
		} else {

			try {

				JsonObject jsonObj = API_Conexion.getFrom_ArchivoJson();
				JsonObject conversionRates = jsonObj.getAsJsonObject("conversion_rates");

				for (int i = 0; i < Moneda.length; i++) {
					exchangeRates[i] = conversionRates.get(Moneda[i]).getAsDouble();
				}

				System.out.println(Arrays.toString(Moneda));
				System.out.println(Arrays.toString(exchangeRates));
				System.out.println("");

				return exchangeRates;

			} catch (Exception e) {
				e.printStackTrace();
			}

			double[] $default = $default();

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

}

