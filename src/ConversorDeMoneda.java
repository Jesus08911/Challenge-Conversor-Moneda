import com.alura.conversordemoda.api.API;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Scanner;

public class ConversorDeMoneda {

    public static void mostrarMenu() {

        System.out.println("""
                
                ********************************************
                Sea bienvenido/a al Conversor de moneda =]
                
                1) Dolar =>> Peso Mexicano
                2) Peso argentino =>> Dolar 
                3) Dolar =>> Real brasileño
                4) Real brasileño =>> Dolar
                5) Dolar =>> Peso colombiano
                6) Peso colombiano =>> Dolar
                7) Salir               
                
                ********************************************
                
                """);
    }

    public static void main(String[] args) {

        mostrarMenu();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Elija una opcion valida:");
        int opcion = scanner.nextInt();

        while (opcion > 7 || opcion < 1) {

            System.out.println("Opcion invalida elije una opcion del menu");

            opcion = scanner.nextInt();

        }
        if (opcion != 7) {
            System.out.println("Ingrese el monto:");
            double monto = scanner.nextDouble();

            String tipoMonedaFiltro = "";
            String tipoMonedaConsulta = "";
            String nombreMoneda = "";


            switch (opcion) {
                case 1: {
                    tipoMonedaFiltro = "MXN";
                    tipoMonedaConsulta = "USD";
                    nombreMoneda = "Peso Mexicano";
                    break;
                }

                case 2: {
                    tipoMonedaFiltro = "USD";
                    tipoMonedaConsulta = "ARS";
                    nombreMoneda = "Dolares";

                    break;
                }
                case 3: {
                    tipoMonedaFiltro = "BRL";
                    tipoMonedaConsulta = "USD";
                    nombreMoneda = "Real Brasileño";
                    break;
                }
                case 4: {
                    tipoMonedaFiltro = "USD";
                    tipoMonedaConsulta = "BRL";
                    nombreMoneda = "Dolares";
                    break;
                }
                case 5: {
                    tipoMonedaFiltro = "COP";
                    tipoMonedaConsulta = "USD";
                    nombreMoneda = "Peso colombiano";
                    break;
                }
                case 6: {
                    tipoMonedaFiltro = "USD";
                    tipoMonedaConsulta = "COP";
                    nombreMoneda = "Dolares";
                    break;
                }
            }

            API api = new API();

            String respuestaAPI = api.consultarApiExchangeRate(tipoMonedaConsulta);

            //Analizando respuesta Json
            JsonElement jsonElement = JsonParser.parseString(respuestaAPI);
            JsonObject jsonObject = jsonElement.getAsJsonObject();


            //Filtrado las monedas
            double tasa = jsonObject.get("conversion_rates").getAsJsonObject().get(tipoMonedaFiltro).getAsDouble();

            double respuesta = monto * tasa;

            System.out.println(" El monto convertido en " + nombreMoneda + " es: " + respuesta);
        } else {
            System.out.println("Saliendo de la aplicacion");
        }
    }
}