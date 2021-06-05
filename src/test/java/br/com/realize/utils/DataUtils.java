package br.com.realize.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DataUtils {

    public static String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }
    public static String getDataDiferencaDias(Integer qtdDias){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, qtdDias);
        return getDataFormatada(cal.getTime());
    }

    public static String getDataFormatada(Date data){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(data);
    }

    public static Object getDataHoraAtual(){
        // data/hora atual
        LocalDateTime agora = LocalDateTime.now();


// formatar a data
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("uuuu/MM/dd");
        String dataFormatada = formatterData.format(agora);

/* formatar a hora
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        String horaFormatada = formatterHora.format(agora);*/
        return null;
    }

}
