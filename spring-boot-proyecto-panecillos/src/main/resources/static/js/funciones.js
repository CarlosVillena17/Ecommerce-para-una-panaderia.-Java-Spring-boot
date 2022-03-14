
function eliminar(id) {
	/*
		swal({
			title: "Desea eliminar el producto?",
			text: "Once deleted, you will not be able to recover this imaginary file!",
			icon: "warning",
			buttons: true,
			dangerMode: true,
		})
			.then((willDelete) => {
				if (willDelete) {
					$.ajax({
						url: "/delete/" + id,
						success: function(res) {
							console.log(res);
						}
					});
					swal("Poof! Your imaginary file has been deleted!", {
						icon: "success",
					})
						.then((willDelete) => {
							if (willDelete) {
								location.href = "/carrito";
							}
						});
				} else {
					swal("Your imaginary file is safe!");
				}
			});
	*/
	Swal.fire({
		title: '¿Desea eliminar el producto del carrito?',
		icon: 'warning',
		showCancelButton: true,
		confirmButtonColor: '#3085d6',
		cancelButtonColor: '#d33',
		confirmButtonText: 'Si, eliminar'
	}).then((result) => {
		if (result.isConfirmed) {
			$.ajax({
				url: "/delete/" + id,
				success: function(res) {
					console.log(res);
				}
			});
			Swal.fire(
				'Eliminado',
				'Producto eliminado del carrito',
				'success'
			)
				.then((result) => {
					if (result.isConfirmed) {
						location.href = "/carrito";
					}
				});

		}
	})

}

function eliminarProducto(id) {
	
	Swal.fire({
		title: '¿Desea eliminar el producto?',
		icon: 'warning',
		showCancelButton: true,
		confirmButtonColor: '#3085d6',
		cancelButtonColor: '#d33',
		confirmButtonText: 'Si, eliminar'
	}).then((result) => {
		if (result.isConfirmed) {
			$.ajax({
				url: "/admin/deleteProducto/" + id,
				success: function(res) {
					console.log(res);
				}
			});
			Swal.fire(
				'Eliminado',
				'Producto eliminado',
				'success'
			)
				.then((result) => {
					if (result.isConfirmed) {
						location.href = "/admin/productos";
					}
				});

		}
	})

}

function procesar() {
	let timerInterval
	Swal.fire({
		title: 'Estamos procesando su compra',
		html: 'Por favor espere un momento',
		timer: 5000,
		timerProgressBar: true,
		didOpen: () => {
			Swal.showLoading()
			const b = Swal.getHtmlContainer().querySelector('b')
			timerInterval = setInterval(() => {
				b.textContent = Swal.getTimerLeft()
			}, 100)
		},
		willClose: () => {
			clearInterval(timerInterval)
		}
	}).then((result) => {
		/* Read more about handling dismissals below */
		if (result.dismiss === Swal.DismissReason.timer) {
			location.href = "/procesarcompra";
		}
	})
}

