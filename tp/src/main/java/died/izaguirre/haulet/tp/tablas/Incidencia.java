package died.izaguirre.haulet.tp.tablas;

import java.time.LocalDate;
import java.util.Date;

public class Incidencia implements Comparable<Incidencia> {

	private Integer id;
	private Parada parada;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private Boolean estaResuelto;

	// Tienen mas prioridad las activas de mayor duracion
	// ????? comprobar -> Si 2 incidencias no tienen fecha de fin, se considerará
	// que aquella con la fechaInicial más cercana será la de mayor duracion

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

	public Boolean getEstaResuelto() {
		return estaResuelto;
	}

	public void setEstaResuelto(Boolean estaResuelto) {
		this.estaResuelto = estaResuelto;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setParada(Parada parada) {
		this.parada = parada;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	@Override
	public boolean equals(Object obj) {
		Incidencia otro = (Incidencia) obj;
		return ((otro == this) || (this.getId() == otro.getId()));
	}

}
