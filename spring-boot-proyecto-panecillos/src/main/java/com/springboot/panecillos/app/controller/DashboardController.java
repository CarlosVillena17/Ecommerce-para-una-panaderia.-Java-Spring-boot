package com.springboot.panecillos.app.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.panecillos.app.dao.IDetalleCompra;
import com.springboot.panecillos.app.models.domain.Compras;
import com.springboot.panecillos.app.models.domain.DetalleCompras;
import com.springboot.panecillos.app.models.domain.Producto;
import com.springboot.panecillos.app.models.domain.Usuario;
import com.springboot.panecillos.app.service.IComprasService;
import com.springboot.panecillos.app.service.IProductoService;
import com.springboot.panecillos.app.service.IUsuarioService;

@Controller
@RequestMapping("/admin")
public class DashboardController {
	@Autowired
	private IUsuarioService iUsuarioService;
	
	@Autowired
	private IComprasService icomprasService;
	
	@Autowired 
	private IDetalleCompra iDetalleCompra;
	
	@Autowired
	private IProductoService iproductoService;
	private final Logger log=LoggerFactory.getLogger(getClass());
	
	@GetMapping("/index")
	public String index() {
		return "dashboard/index";
	}
	
	@GetMapping("/clientes")
	public String clientes() {
		return "dashboard/clientecompras";
	}
	
	@GetMapping("/clientes/{id}")
	public String misCompras(@PathVariable Long id ,Model model) {
		List<Compras> compras=icomprasService.comprasDelUsuario(id);
		model.addAttribute("compras", compras);
		return "dashboard/miscompras";
	}
	
	@GetMapping("/detalleMisCompras/{id}")
	public String detalleMisCompras(@PathVariable Long id, Model model) {
		List<DetalleCompras> detalleCompras=iDetalleCompra.detallesCompras(id);
		model.addAttribute("detalle", detalleCompras);
		return "dashboard/detallecompras";
	}
	
	@GetMapping("/productos")
	public String productos(Model model) {
		List<Producto> productos= iproductoService.findAll();
		model.addAttribute("productos", productos);
		return "dashboard/productos";
	}
	
	@GetMapping("/deleteProducto/{id}")
	public String productosDelete(@PathVariable Long id,Model model) {
		iproductoService.eliminar(id);
		return "redirect:/admin/productos";
	}
	
	@GetMapping("/crear")
	public String productosCrear(Model model) {
		Producto producto=new Producto();
		model.addAttribute("producto", producto);
		return "dashboard/crearProducto";
	}
	
	@PostMapping("/crear")
	public String procesarProducto(Producto producto , Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash) {
		if(!foto.isEmpty()) {
			//String rootPath="C://Temp//uploads";
			String uniqueFilename=UUID.randomUUID().toString()+"_"+foto.getOriginalFilename();
			Path rootPath=Paths.get("uploads").resolve(foto.getOriginalFilename());
			Path rootAbsolutPah=rootPath.toAbsolutePath();
			log.info("rootPath: "+rootPath);
			log.info("rootAbsolutPath: "+rootAbsolutPah);
			try {
				/*
				byte[] bytes=foto.getBytes();
				Path rutaCompleta=Paths.get(rootPath+"/"+foto.getOriginalFilename());
				Files.write(rutaCompleta, bytes);
				*/
				Files.copy(foto.getInputStream(), rootAbsolutPah);
				
				producto.setFoto(foto.getOriginalFilename());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		iproductoService.guardar(producto);
		flash.addFlashAttribute("success", "Producto creado correctamente");
		return "redirect:/admin/productos";
	}
	
	@GetMapping("/editarProducto/{id}")
	public String editarProducto(@PathVariable Long id,Model model) {
		Producto producto=iproductoService.buscarPorId(id);
		model.addAttribute("producto", producto);
		return "dashboard/crearProducto";
	}
	
	@GetMapping("/graficos")
	public String graficos(Model model) {
		
		return "dashboard/graficos";
	}
	
	@ModelAttribute("usuarios")
	public List<Usuario> listarUsuarios(){
		return iUsuarioService.listar();
	}
	@ModelAttribute("usuariosRegistrados")
	public Integer usuariosRegistrados(){
		return iUsuarioService.listar().size();
	}
	
}
