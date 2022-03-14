package com.springboot.panecillos.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.panecillos.app.dao.IDetalleCompra;
import com.springboot.panecillos.app.models.domain.CarritoCompras;
import com.springboot.panecillos.app.models.domain.Categoria;
import com.springboot.panecillos.app.models.domain.Compras;
import com.springboot.panecillos.app.models.domain.DetalleCompras;
import com.springboot.panecillos.app.models.domain.Pago;
import com.springboot.panecillos.app.models.domain.Producto;
import com.springboot.panecillos.app.models.domain.Usuario;
import com.springboot.panecillos.app.service.CarritoServiceImpl;
import com.springboot.panecillos.app.service.ICarritoService;
import com.springboot.panecillos.app.service.ICategoriaService;
import com.springboot.panecillos.app.service.IComprasService;
import com.springboot.panecillos.app.service.IPagoService;
import com.springboot.panecillos.app.service.IProductoService;
import com.springboot.panecillos.app.service.IUsuarioService;

@SessionAttributes("producto")
@Controller
public class IndexController {
	private Usuario usuarioPrincipal=null;
	
	@Autowired
	private IProductoService productoService;
	
	@Autowired 
	private ICategoriaService categoriaService; 

	@Autowired
	private ICarritoService carritoService;
	
	
	@Autowired
	private IDetalleCompra detalleCompraService;
	
	@Autowired 
	private IComprasService comprasService; 
	
	@Autowired 
	private IPagoService pagoService; 
	
	@Autowired
	private IUsuarioService iUsuarioService; 
	
	int item=0;
	@GetMapping("/index")
	public String index(Model model) {
		Categoria categoria=categoriaService.findOne(1L);
		Categoria categoriaPasteles=categoriaService.findOne(2L);
		model.addAttribute("categoriaPasteles", categoriaPasteles);
		model.addAttribute("categoriaPan", categoria);
		return "index";
	}
	
	@GetMapping("/productos")
	public String listaProductos(Model model) {
		List<Producto> productos=productoService.findAll();
		model.addAttribute("productos", productos);
		return "productos";
	}
	
	
	@GetMapping("/detalle/{id}")
	public String categorias(@PathVariable Long id, Model model) {
		Producto producto=productoService.buscarPorId(id);
		model.addAttribute("producto", producto);
		return "detalleproducto";
	}
	
	@PostMapping("/agregarcarrito")
	public String agregarCarrito(@RequestParam Long id, @RequestParam Integer cantidad) {
		item=item+1;
		Producto  producto=productoService.buscarPorId(id);
		CarritoCompras carrito=new CarritoCompras(); 
		carrito.setIdproducto(producto.getId());
		carrito.setCantidad(cantidad);
		carrito.setItem(item);
		carrito.setNombre(producto.getNombre());
		carrito.setPrecioCompra(producto.getPrecio());
		carrito.setSubTotal(Math.round(cantidad*producto.getPrecio()*100.0)/100.0);
		carrito.setFoto(producto.getFoto());
		carritoService.guardar(carrito);
		return "redirect:/index"; 
	}

	@GetMapping("/carrito")
	public String carro(Model model) {
		return "carrito";
	}
	
	@GetMapping("/delete/{idproducto}")
	public String eliminar(@PathVariable Long idproducto) {
		carritoService.eliminar(idproducto);
		return "redirect:/carrito";
	}
	
	@GetMapping("/descuento")
	public String descuento(@RequestParam String cupon, Model model) {
		System.out.println(cupon);
		if(cupon.equals("gestion")) {
			carritoService.descuento(30.00);
		}
		return "redirect:/carrito";
	}
	
	@GetMapping("/compra")
	public String compra() {
		return "compra";
	}
	
	@GetMapping("/procesarcompra")
	public String procesarcompra() {
		Compras compra=new Compras(); 
		Pago pago=new Pago(); 
		pago.setMonto(totalPagar());
		pagoService.guardar(pago);
		compra.setMonto(totalPagar());
		compra.setPago(pago);
		compra.setIdcliente(usuarioPrincipal.getId());
		//iUsuarioService.guardar(usuarioPrincipal);
		for(CarritoCompras carrito: this.carrito()) {
			DetalleCompras detalleCompras=new DetalleCompras(); 
			Producto producto=new Producto(); 
			producto.setId(carrito.getIdproducto());
			detalleCompras.setPrecioCompra(carrito.getPrecioCompra());
			detalleCompras.setCantidad(carrito.getCantidad());
			detalleCompras.setProducto(producto);
			detalleCompraService.save(detalleCompras);
			pagoService.guardar(pago);
			compra.agregar(detalleCompras);
			comprasService.guardar(compra);
		}
		carritoService.descuento(0.0);
		carritoService.listar().clear();
		return "redirect:/index";
	}
	
	@GetMapping("/login")
	public String iniciarSesion() {
		return "login";
	}
	
	@PostMapping("/login")
	public String procesarFormulario(Usuario usuario, RedirectAttributes flash) {
		iUsuarioService.guardar(usuario);
		flash.addFlashAttribute("success", "usuario creado correctamente");
		return "redirect:/login"; 
	}
	
	@PostMapping("/iniciar")
	public String iniciar(@RequestParam String email, @RequestParam String password, RedirectAttributes flash) {
		Usuario usuario= iUsuarioService.buscarPorCorreoyNombre(email, password);
		if(email.equals("panecillos@gmail.com")){
			return "redirect:/admin/index";
		}

		if(usuario==null) {
			flash.addFlashAttribute("error", "El usuario es incorrecto");
			return "redirect:/login";
		}
		
		
		this.usuarioPrincipal=usuario;
		return "redirect:/index";
	}
	
	@GetMapping("/cerrarSesion")
	public String cerrarSession() {
		this.usuarioPrincipal=null;
		carritoService.descuento(0.0);
		return "redirect:/index";
	}
	
	@GetMapping("/miscompras")
	public String misCompras(Model model) {
		if(this.usuarioPrincipal==null) {
			return "redirect:/login";
		}
		List<Compras> compras=comprasService.comprasDelUsuario(usuarioPrincipal.getId());
		model.addAttribute("compras", compras);
		return "miscompras";
	}
	
	@GetMapping("/detalleMisCompras/{id}")
	public String detalleMisCompras(@PathVariable Long id, Model model) {
		List<DetalleCompras> detalleCompras=detalleCompraService.detallesCompras(id);
		model.addAttribute("detalle", detalleCompras);
		return "detallemiscompras";
	}
	
	@GetMapping("/filtrarCategoria/{id}")
	public String filtrarCategoria(@PathVariable Long id, Model model) {
		Categoria categoria=categoriaService.findOne(id);
		model.addAttribute("productos", categoria.getProducto());
		return "filtrar"; 
	}
	@GetMapping("/buscar")
	public String buscar(@RequestParam String producto,Model model) {
		List<Producto> productos=productoService.listarProductosBusqueda(producto);
		model.addAttribute("productos", productos);
		return "filtrar"; 
	}
	

	@ModelAttribute("totalPagar")
	public Double totalPagar() {
		return Math.round(carritoService.calcularTotal()*100.0)/100.0 ;
	}
	
	@ModelAttribute("totalPagarSinDescuento")
	public Double totalPagarSinDescuento() {
		return Math.round(carritoService.calcularTotalSinDescuento()*100.0)/100.0;  
	}
	
	@ModelAttribute("listarCarrito")
	public List<CarritoCompras> carrito(){
		return carritoService.listar();
	}
	
	@ModelAttribute("usuarioPrincipal")
	public Usuario usuario() {
		return this.usuarioPrincipal;
	}
}
