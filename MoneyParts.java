package com.avantica.moneyparts;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Hello world!
 */
public final class MoneyParts {

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        new MoneyParts().build("10.5");
    }

    public String[][] build(String monto) {
        String[][] montoBuild = null;
        double montoNum = Double.parseDouble(monto);
        List<String> denominaciones = Arrays.asList("0.05", "0.1", "0.2", 
                "0.5", "1", "2", "5", "10", "20", "50", "100", "200");
        List<String> denomValidos = denominaciones.stream()
                .filter(denom -> Double.parseDouble(denom) <= montoNum)
                .collect(Collectors.toList());

        String montoAcumFinal = "";
        for (String denom : denomValidos) {
            double denomNum = Double.parseDouble(denom);
            double denomSum = 0;
            String montoAcum = "";

            BigDecimal montoNumDecimal = BigDecimal.valueOf(montoNum);
            BigDecimal denomSumDecimal = BigDecimal.valueOf(denomSum);
            BigDecimal denomNumDecimal = BigDecimal.valueOf(denomNum);
            
            while ((montoNumDecimal.subtract(denomSumDecimal)).compareTo(denomNumDecimal) >= 0) {    
                denomSumDecimal = denomSumDecimal.add(denomNumDecimal);
                montoAcum = montoAcum + denomNum + ";";
            }

            if (montoNumDecimal.compareTo(denomSumDecimal) == 0) {
                montoAcumFinal = montoAcumFinal + montoAcum + "|";
            }
        }

        String[] valoresIni = montoAcumFinal.split("\\|");
        montoBuild = new String[valoresIni.length][];

        for (int i = 0; i < valoresIni.length; i++) {
            montoBuild[i] = valoresIni[i].split(";");
        }

        return montoBuild;
    }

}
