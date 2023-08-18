package Logica;

/**
 * @version 0.1.0 beta
 * @author ErCamilo
 */
public class Conversor {

    private double[] valoresBase;
    static private String[] Magnitudes;
    protected double Resultado;
    private String Magbase;

    /**
     * *******************************************************************************
     */
    public Conversor() {

    }

    public void setMagnitudes(String MagConversion) {
        System.out.println(MagConversion);
        System.out.println("-----------------------");

        int y;// Array.length

        switch (MagConversion) {

            case "Moneda" -> {
                y = MagUtil.valorMoneda.length;
                this.Magbase = "1 USD";
                this.valoresBase = new double[y];
                Conversor.Magnitudes = new String[y];

                this.valoresBase = MagUtil.valorMoneda;
                Conversor.Magnitudes = MagUtil.Moneda;
            }

            case "Distancia" -> {
                y = MagUtil.MagDistancia.length;
                this.Magbase = "1 Metro";

                this.valoresBase = new double[y];
                Conversor.Magnitudes = new String[y];
                this.valoresBase = MagUtil.MagDistancia;
                Conversor.Magnitudes = MagUtil.UniDistancia;
            }

            case "Masa" -> {
                y = MagUtil.MagMasa.length;
                this.Magbase = "1 Gramo";

                this.valoresBase = new double[y];
                Conversor.Magnitudes = new String[y];
                this.valoresBase = MagUtil.MagMasa;
                Conversor.Magnitudes = MagUtil.UniMasa;
            }

            case "Volumen" -> {
                y = MagUtil.MagVolumen.length;
                this.Magbase = "1 Litro";

                this.valoresBase = new double[y];
                Conversor.Magnitudes = new String[y];
                this.valoresBase = MagUtil.MagVolumen;
                Conversor.Magnitudes = MagUtil.UniVolumen;
            }

            case "Temperatura" -> {
                y = MagUtil.MagTemperatura.length;

                this.valoresBase = new double[y];
                Conversor.Magnitudes = new String[y];
                Conversor.Magnitudes = MagUtil.MagTemperatura;
            }
        }
    }

    private int getIndex(String mag) {

        for (int i = 0; i < Magnitudes.length; i++) {
            if (Magnitudes[i].equals(mag)) {

                return i;
            }
        }
        throw new IllegalArgumentException("La magnitud " + "'" + mag + "'" + " no ha sido encontrada");
    }

    public void setResultado(double valorAConvertir, String MagEntrada, String MagSalida) {

        double magEntrada = valoresBase[getIndex(MagEntrada)];
        double magSalida = valoresBase[getIndex(MagSalida)];

        System.out.println("Logica.Conversor.setResultado");
        System.out.println("La magnitud base es: "+ Magbase);
        
        System.out.println(Magbase + " = " + magEntrada + " " + MagEntrada + "s");
        
        System.out.println(Magbase + " = " + magSalida + " " + MagSalida + "s");

        this.Resultado = valorAConvertir * (magSalida / magEntrada);

        System.out.println("Conversion de : " + MagEntrada + "->" + MagSalida);
        System.out.println(valorAConvertir + " " + MagEntrada + " -> " + Resultado + " " + MagSalida);

    }

    public double getResultado() {
        return this.Resultado;
    }

    public double getResultadoRedondeado(int cantDecimales) {

        double exp;
        double resultado;
        if (cantDecimales <= 18 || cantDecimales >= 0) {
            exp = Math.pow(10, cantDecimales);
        } else {
            exp = Math.pow(10, 18);
        }

        resultado = ((long) (this.Resultado * exp)) / (exp);

        return resultado;

    }

    public String[] getMagnitudes() {
        return Conversor.Magnitudes;
    }

    public static String[] getMagnitudes2() {
        return Magnitudes;
    }

    public static boolean validadNumero(double valorAConvertir) {
        boolean valido;

        String valor = Double.toString(valorAConvertir);

        valido = (!valor.matches("-?\\d+(\\.\\d+)?"));

        return valido;
    }
}// fin de main/
