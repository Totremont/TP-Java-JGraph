package died.izaguirre.haulet.tp.tablas;

import died.izaguirre.haulet.tp.tablas.linea.Linea;

public class Boleto {

	private Integer id;
	private Linea linea;
	private int monto;
	
	public Boleto() {}
	
	public Boleto(Linea linea, int monto) {
		this();
		this.linea = linea;
		this.monto = monto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Linea getLinea() {
		return linea;
	}

	public void setLinea(Linea linea) {
		this.linea = linea;
	}

	public int getMonto() {
		return monto;
	}

	public void setMonto(int ultimoPrecio) {
		this.monto = ultimoPrecio;
	}
	
	@Override
	public boolean equals(Object obj) {
		Boleto otro = (Boleto) obj;
		return ((otro == this) || (this.getId() == otro.getId()));
	}

}
