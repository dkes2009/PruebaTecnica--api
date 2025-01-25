package com.ApiPruebatecnica.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class Utils {
    private static int idHilo = 0;
    private transient int indicador = 0;
    private static final Logger logger = LoggerFactory.getLogger(Utils.class);
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_hh:mm:ss");
    private String message;

    public void logApi(int respuestaApi, String mensaje, String ip, int consecutivo, String nombreEndpoint) {
        try {
            String fecha = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            File dir = new File("Logs-ApiPruebatecnica");
            if (!dir.exists()) {
                dir.mkdir();
            }
            String fileName = dir + File.separator + fecha + "-TramasApi.txt";
            message = respuestaApi == 0 ? consecutivo + "  RECIBO: " + simpleDateFormat.format(new Date()) + "   " + nombreEndpoint + " :  " + mensaje +  " IP_CONSUMO: " + ip : consecutivo + "  RESPONDO: " + simpleDateFormat.format(new Date()) + "   " + nombreEndpoint + " :  " + mensaje  +"  IP_CONSUMO: " + ip + "\n";
            imprimir(message, fileName);
        } catch (Exception e) {
            message = "Error Utils: fall in logs ";
            logApiError("ERROR AL GUARDAR EL LOG" + message);
            logger.error(e.getMessage(), e);

        }
    }
    public void logApiError(String mensaje) {
        try {
            String fecha = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            File dir = new File( "Logs-ApiPruebatecnica");
            if (!dir.exists()) {
                dir.mkdir();
            }
            String fileName = dir + File.separator + fecha + "-Errores.txt";
            message = simpleDateFormat.format(new Date()) + " ------> " + mensaje + "\n";
            imprimir(message, fileName);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }



    private void imprimir(final String message, String name) {
        synchronized (this) {
            try (final FileWriter fileWriter = new FileWriter(name, true); final BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                bufferedWriter.write(message);
                bufferedWriter.newLine();
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public int consecutivo() {
        synchronized (this) {
            if (idHilo == 9999)
                idHilo = 0;
            this.indicador = ++idHilo;
            return indicador;
        }
    }
}
