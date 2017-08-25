package gpw.ejb;

import java.util.List;

import javax.ejb.Local;

import gpw.dominio.pedido.EstadoPedido;
import gpw.dominio.pedido.Pedido;
import gpw.dominio.pedido.PedidoLinea;
import gpw.dominio.persona.Departamento;
import gpw.dominio.persona.Localidad;
import gpw.dominio.persona.TipoDoc;
import gpw.dominio.producto.Producto;
import gpw.dominio.producto.TipoProd;
import gpw.dominio.usuario.UsuarioWeb;
import gpw.exceptions.PersistenciaException;
import gpw.types.Fecha;

@Local
public interface GpWebStatelessLocal {
	
	/**
	 * usuario
	 */
	public String loginUsuario(String nombreUsuario, String password) throws PersistenciaException;
	public UsuarioWeb obtenerUsuario(String nombreUsuario) throws PersistenciaException;
//	public UsuarioWeb obtenerUsuarioPorId(String nombreUsuario, String password) throws PersistenciaException;
	public Integer guardarUsuario(UsuarioWeb usr) throws PersistenciaException;
	public Integer modificarUsuario(UsuarioWeb usr, Boolean modificaPasswd) throws PersistenciaException;
	public Integer eliminarUsuario(UsuarioWeb usr) throws PersistenciaException;
	
	/**
	 * persona
	 */
	//tipo doc
	public TipoDoc obtenerTipoDocPorId(Integer id) throws PersistenciaException;
	public List<TipoDoc> obtenerListaTipoDoc() throws PersistenciaException;
	//dep y loc
	public List<Departamento> obtenerListaDepartamentos() throws PersistenciaException;
	public Departamento obtenerDepartamentoPorId(Integer id) throws PersistenciaException;
	public List<Localidad> obtenerListaLocPorDep(Integer idDep) throws PersistenciaException;
	public Localidad obtenerLocalidadPorId(Integer idLoc) throws PersistenciaException;
	
	/**
	 * producto
	 */
	//tipo prod
	public List<TipoProd> obtenerListaTipoProd() throws PersistenciaException;
	//producto
	public Producto obtenerProductoPorId(Integer id) throws PersistenciaException;
	public List<Producto> obtenerListaProductoPorTipo(Integer tipoProd) throws PersistenciaException;
	
	/**
	 * pedido
	 */
	public Pedido obtenerPedidoPorId(Long idPersona, Fecha fechaHora) throws PersistenciaException;
	public List<Pedido> obtenerListaPedido(EstadoPedido ep, Long idPersona, Fecha fechaDesde, Fecha fechaHasta) throws PersistenciaException;
	public Integer guardarPedido(Pedido pedido) throws PersistenciaException;
	public Integer modificarPedido(Pedido pedido, List<PedidoLinea> lineaLineasNuevas) throws PersistenciaException;
	public Integer modificarEstadoPedido(Pedido pedido) throws PersistenciaException;
	
}
