/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

/**
 *
 * @author the_b
 */
public class MagUtil {

    public static final String[] MagArray = {"Moneda", "Volumen", "Masa", "Temperatura", "Distancia"};

    // Distancia//
    public static final String[] UniDistancia = {"Centimetro", "Metro", "Kilometro", "Milimetro", "Milla", "Yarda", "Pie",
        "Pulgada"};
    public static final double[] MagDistancia = {100, 1, 0.001, 1000, 0.000621, 1.09361, 3.28084, 39.39701};

    // Masa//
    public static final String[] UniMasa = {"Gramo", "Miligramo", "Kilogramo", "Onza", "Libra"};
    public static final double[] MagMasa = {1.0, 1000.0, 0.001, 0.035274, 0.002205};

    // Volumen//
    public static final String[] UniVolumen = {"Litro", "Mililitro", "Galon", "Cuarto", "Pinta"};
    public static final double[] MagVolumen = {1, 1000, 0.264172, 1.05669, 2.11338};

    // Moneda//
    public static final String[] Moneda = {"USD", "EUR", "GBP", "JPY", "KRW", "DOP", "MXN", "ARS", "BRL"};
    public static final double[] valorMoneda = MonedaAPI.getExchangeRates(Moneda);

    // Temperatura//
    public static final String[] MagTemperatura = {"Kelvin", "Celsius", "Fahrenheit"};
}
