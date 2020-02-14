package modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "nota")
public class Nota implements Serializable {
	private static final long serialVersionUID = 294048456006182993L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo", nullable = false)
	private Integer codigo;
	
	@ManyToOne
	@JoinColumn(name = "codigo_persona", nullable = false)
	private Persona persona;
	
	@ManyToOne
	@JoinColumn(name = "codigo_categoria", nullable = false)
	private Categoria categoria;

	@Column(name = "encabezado", nullable = false)
	private String encabezado;
	
	@Column(name = "cuerpo", nullable = false)
	private String cuerpo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha", nullable = false, insertable=false, updatable = false)
	private Date fecha;
	
	@Column(name = "comentarioAdmin")
	private String comentarioAdmin;
	
	@Column(name = "valor")
	private String valor;

	@PrePersist
	public void setFecha() {
		this.fecha = new Date();
	}
	
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getEncabezado() {
		return encabezado;
	}

	public void setEncabezado(String encabezado) {
		this.encabezado = encabezado;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getComentarioAdmin() {
		return comentarioAdmin;
	}

	public void setComentarioAdmin(String comentarioAdmin) {
		this.comentarioAdmin = comentarioAdmin;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
}
