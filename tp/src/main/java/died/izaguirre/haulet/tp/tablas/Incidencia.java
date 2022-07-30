package died.izaguirre.haulet.tp.tablas;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;

public class Incidencia implements Comparable<Incidencia> {

	private Integer id;
	private Parada parada;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private Boolean estaResuelto;
	private String descripción;
	private String motivo;

	// Tienen mas prioridad las activas de mayor duracion
	// ????? comprobar -> Si 2 incidencias no tienen fecha de fin, se considerará
	// que aquella con la fechaInicial más lejana será la de mayor duracion

	@Override
	public int compareTo(Incidencia o) {
		
		LocalDate fin = fechaFin != null ? fechaFin : LocalDate.now();
		LocalDate finOtro = o.getFechaFin() != null ? o.getFechaFin() : LocalDate.now();
		
		Duration duracionEsta = Duration.between(fechaInicio.atStartOfDay(), fin.atStartOfDay());
		Duration duracionOtra = Duration.between(o.getFechaInicio().atStartOfDay(),finOtro.atStartOfDay());
		if(estaResuelto && !o.getEstaResuelto()) return 1;
		else if(!estaResuelto && o.getEstaResuelto()) return -1;
		else return duracionOtra.compareTo(duracionEsta);
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
	
	
	
	public String getDescripción() {
		return descripción;
	}

	public void setDescripción(String descripción) {
		this.descripción = descripción;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	@Override
	public boolean equals(Object obj) {
		Incidencia otro = (Incidencia) obj;
		return ((otro == this) || (this.getId() == otro.getId()));
	}

}
