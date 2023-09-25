package Logica;

import java.util.Arrays;
import javax.swing.JOptionPane;
import com.google.gson.JsonObject;

public class MonedaAPI {

   private static double[] tasaDeCambio;

   private static boolean tasaDeCambioEsNULL() {

        boolean esNull = (tasaDeCambio == null);
        tasaDeCambio = (!esNull) ? tasaDeCambio : new double[ListaMagnitudes.Moneda.length];
        return esNull;

    }
   //Obtiene un array con las tasas de cambio
    public static double[] getTasaDeCambio(String[] Moneda) {

        if (!tasaDeCambioEsNULL()) {
            return tasaDeCambio;
        } else {

            try {

                JsonObject jsonObj = API_Conexion.getFrom_ArchivoJson();
                JsonObject tasaDeConversion = jsonObj.getAsJsonObject("conversion_rates");

                for (int i = 0; i < Moneda.length; i++) {
                    tasaDeCambio[i] = tasaDeConversion.get(Moneda[i]).getAsDouble();
                }

                System.out.println(Arrays.toString(Moneda));
                System.out.println(Arrays.toString(tasaDeCambio));
                System.out.println("");

                return tasaDeCambio;

            } catch (Exception e) {
                e.printStackTrace();
            }

            double[] tasaDefault = tasaDeCambioDefault();

            return tasaDeCambio = tasaDefault;
        }

    }
    
    //se utiliza en caso de que no se pueda conectar al API de exchangerate
    
    private static double[] tasaDeCambioDefault() {
        JOptionPane.showMessageDialog(null, "Hubo un error con el servidor");
        JOptionPane.showMessageDialog(null, "Se utilizaran la taza de cambido del 8/08/2023");

        System.out.println("Hubo un problema en la conexion con el servidor");
        System.out.println("Se utilizaran la taza de cambido del 8/08/2023");

        double[] tasaDefault = {1.0, 0.9092, 0.7831, 142.3367, 1305.2606, 56.1174, 17.0678, 278.9805, 4.8766};
        System.out.println(Arrays.toString(tasaDefault));

        return tasaDeCambio = tasaDefault;
    }

}
