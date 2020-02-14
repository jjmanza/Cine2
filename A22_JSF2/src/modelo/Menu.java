package modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "menu")
public class Menu implements Serializable{
	private static final long serialVersionUID = -8402715218617288323L;

	@Id
	@Column(name = "codigo")
	private Integer codigo;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "url")
	private String url;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo")
	private EnumModelos.TipoMenu tipo;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipoUsuario")
	private EnumModelos.TipoUsuario tipoUsuario;
	
	@ManyToOne
	@JoinColumn(name="codigo_submenu")
	private Menu submenu;

	@OneToMany(mappedBy="submenu")
	private List<Menu> listaMenus;
	
	@Column(name = "estado")
	private Boolean estado;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public EnumModelos.TipoMenu getTipo() {
		return tipo;
	}

	public void setTipo(EnumModelos.TipoMenu tipo) {
		this.tipo = tipo;
	}

	public EnumModelos.TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(EnumModelos.TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public Menu getSubmenu() {
		return submenu;
	}

	public void setSubmenu(Menu submenu) {
		this.submenu = submenu;
	}

	public List<Menu> getListaMenus() {
		return this.listaMenus;
	}

	public void setListaMenus(List<Menu> listaMenus) {
		this.listaMenus = listaMenus;
	}

	public Menu addListaMenus(Menu listaMenus) {
		getListaMenus().add(listaMenus);
		listaMenus.setSubmenu(this);

		return listaMenus;
	}

	public Menu removeListaMenus(Menu listaMenus) {
		getListaMenus().remove(listaMenus);
		listaMenus.setSubmenu(null);

		return listaMenus;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
}
