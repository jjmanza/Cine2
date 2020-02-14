package modelo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario implements Serializable{
	private static final long serialVersionUID = 1919618119866719613L;	
	@Id
	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="codigo", nullable = false)
	private Persona codigo;
	
	@Column(name="usuario", nullable = false, unique = true)
	private String usuario;

	@Column(name="clave", nullable = false)
	private String clave;

	@Enumerated(EnumType.STRING)
	@Column(name="tipo", columnDefinition = "default 'O'")
	private EnumModelos.TipoUsuario tipo;
	
	@Column(name="estado")
	private Boolean estado;

	public Persona getCodigo() {
		return codigo;
	}

	public void setCodigo(Persona codigo) {
		this.codigo = codigo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
	
	public EnumModelos.TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(EnumModelos.TipoUsuario tipo) {
		this.tipo = tipo;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
}