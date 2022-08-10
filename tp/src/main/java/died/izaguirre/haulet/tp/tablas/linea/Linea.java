package died.izaguirre.haulet.tp.tablas.linea;

import died.izaguirre.haulet.tp.tablas.Parada;

public class Linea {

	private Integer id;
	private String tipo;
	private Integer capSentado;
	private String nombre;
	private String color;
	private Integer capParado;
	private Boolean tieneAire;
	private Boolean tieneWifi;
	private Parada origen;
	private Parada destino;

	public Linea() {
	}

	// Paradas
	private Linea(Parada origen, Parada destino) {
		this.origen = origen;
		this.destino = destino;
	}

	// Linea economica
	public Linea(String tipo, String nombre, String color, Integer capSentado, Integer porcentajeParado, Parada origen, Parada destino) {
		this(origen, destino);
		this.tipo = tipo;
		this.nombre = nombre;
		this.color = color;
		this.capSentado = capSentado;
		this.capParado = Math.round(capSentado * (porcentajeParado/100f));
		this.tieneWifi = false;
		this.tieneAire = false;
	}

	// Linea superior
	public Linea(String tipo, String nombre, String color, Integer capSentado, Boolean tieneAire, Boolean tieneWifi,
			Parada origen, Parada destino) {
		this(origen, destino);
		this.tipo = tipo;
		this.nombre = nombre;
		this.color = color;
		this.capSentado = capSentado;
		this.capParado = 0;
		this.tieneAire = tieneAire;
		this.tieneWifi = tieneWifi;
	}

	public Linea(String tipo, Integer capSentado, String nombre, String color, Integer capParado, Boolean tieneAire,
			Boolean tieneWifi) {
		this();
		this.tipo = tipo;
		this.capSentado = capSentado;
		this.nombre = nombre;
		this.color = color;
		this.capParado = Math.round(capSentado*(capParado/100f));
		this.tieneAire = tieneAire;
		this.tieneWifi = tieneWifi;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCapSentado() {
		return capSentado;
	}

	public void setCapSentado(Integer capSentado) {
		this.capSentado = capSentado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getCapParado() {
		return capParado;
	}

	public void setCapParado(Integer capParado) {
		this.capParado = capParado;
	}

	public Boolean getTieneAire() {
		return tieneAire;
	}

	public void setTieneAire(Boolean tieneAire) {
		this.tieneAire = tieneAire;
	}

	public Boolean getTieneWifi() {
		return tieneWifi;
	}

	public void setTieneWifi(Boolean tieneWifi) {
		this.tieneWifi = tieneWifi;
	}

	public Parada getOrigen() {
		return origen;
	}

	public void setOrigen(Parada origen) {
		this.origen = origen;
	}

	public Parada getDestino() {
		return destino;
	}

	public void setDestino(Parada destino) {
		this.destino = destino;
	}

	@Override
	public boolean equals(Object obj) {
		Linea otro = (Linea) obj;
		return ((otro == this) || (this.getId() == otro.getId()));
	}
	
	@Override
	public String toString() {
		return ("Línea: " + this.getNombre() + " (" + this.getTipo() + ")");
	}

}
