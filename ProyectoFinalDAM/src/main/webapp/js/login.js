
  function manejarLogin(xhr, status, args) {
    if (!args.validationFailed && args.estaLogeado) {
      setTimeout(function() {
        window.location = args.view;
      }, 500);
    }
  }