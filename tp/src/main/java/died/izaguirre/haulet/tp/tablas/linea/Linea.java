package died.izaguirre.haulet.tp.tablas.linea;

public class Linea {

	private Integer id;
	private String tipo;
	private Integer capSentado;
	private String nombre;
	private String color;
	private Integer capParado;
	private Boolean tieneAire;
	private Boolean tieneWifi;

	public Linea() {
	}

	public Linea(String tipo, Integer capSentado, String nombre, String color, Integer capParado, Boolean tieneAire,
			Boolean tieneWifi) {
		this();
		this.tipo = tipo;
		this.capSentado = capSentado;
		this.nombre = nombre;
		this.color = color;
		this.capParado = capParado;
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
	
	@Override
	public boolean equals(Object obj) {
		Linea otro = (Linea) obj;
		return ((otro == this) || (this.getId() == otro.getId()));
	}

}
