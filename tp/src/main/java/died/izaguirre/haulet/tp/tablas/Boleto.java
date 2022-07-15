package died.izaguirre.haulet.tp.tablas;

import died.izaguirre.haulet.tp.tablas.linea.Linea;

public class Boleto {

	private Integer id;
	private Linea linea;
	private Double monto;
	
	public Boleto() {}
	
	public Boleto(Linea linea, Double monto) {
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

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}
	
	@Override
	public boolean equals(Object obj) {
		Boleto otro = (Boleto) obj;
		return ((otro == this) || (this.getId() == otro.getId()));
	}

}
