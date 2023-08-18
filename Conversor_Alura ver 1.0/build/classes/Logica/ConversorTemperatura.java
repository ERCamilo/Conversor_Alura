package Logica;

public class ConversorTemperatura extends Conversor {

    //private double Resultado;
    @Override
    public void setResultado(double valorAConvertir, String MagEntrada, String MagSalida) {
        System.out.println(MagEntrada + " -> " + MagSalida);
        if (MagEntrada.equals(MagSalida)) {
            this.Resultado = valorAConvertir;

        } else {

            String x = MagEntrada + "To" + MagSalida;
            System.out.println(x);

            switch (x) {

                case "FahrenheitToCelsius":
                    this.Resultado = (valorAConvertir - 32) / 1.8;

                    break;

                case "CelsiusToFahrenheit":

                    this.Resultado = valorAConvertir * 1.8 + 32;

                    break;

                case "KelvinToCelsius":

                    this.Resultado = valorAConvertir - 273.15;

                    break;

                case "CelsiusToKelvin":
                    this.Resultado = valorAConvertir + 273.15;

                    break;

                case "FahrenheitToKelvin":
                    this.Resultado = ((valorAConvertir - 32) / 1.8) + 273.15;
                    break;

                case "KelvinToFahrenheit":
                    this.Resultado = (valorAConvertir - 273.15) * 1.8 + 32;
                    break;

                default:
                    System.out.println("No encontrado");
            }
        }

        System.out.println(valorAConvertir + " " + MagEntrada + " = " + this.Resultado + " " + MagSalida);

    }

}//fin class

