package died.izaguirre.haulet.tp.tablas;

import java.time.LocalDate;

public class Incidencia implements Comparable<Incidencia> {
	
	private int id;
	private Parada parada;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private boolean estaResuelto;
	
	//Tienen mas prioridad las activas de mayor duracion 
	// ????? comprobar -> Si 2 incidencias no tienen fecha de fin, se considerará que aquella con la fechaInicial más cercana será la de mayor duracion
	
	@Override
	public int compareTo(Incidencia o) {						
		return 0;
	}

	public int getId() {
		return id;
	}

	public Parada getParada() {
		return parada;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public boolean isEstaResuelto() {
		return estaResuelto;
	}
	
	
	
	
	

}
