package utilidades;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Data {
	
	public static String dataHora(Date agora) {
		String formato = "dd-MM-yyyy HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(formato);
		String dataFormatada = sdf.format(agora);
		
		return dataFormatada;
		
	}

}
