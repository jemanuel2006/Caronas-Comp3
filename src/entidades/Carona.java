package entidades;

import java.sql.Date;

public class Carona {
	private Date dia;
	private Date hora_saida;
	public Date getDia() {
		return dia;
	}
	public void setDia(Date dia) {
		this.dia = dia;
	}
	public Date getHora_saida() {
		return hora_saida;
	}
	public void setHora_saida(Date hora_saida) {
		this.hora_saida = hora_saida;
	}
}
