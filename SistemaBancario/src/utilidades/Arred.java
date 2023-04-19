package utilidades;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Arred {
	
	public static double dois (double valor, int scala) {
		
		BigDecimal bigDecimal = new BigDecimal(valor).setScale(scala, RoundingMode.HALF_EVEN);
		return bigDecimal.doubleValue();
		
	}
	
}
