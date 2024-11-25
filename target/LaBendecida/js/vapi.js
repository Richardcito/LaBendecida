var vapiInstance = null;
  const assistant = "f06120ab-d31c-477a-8e7d-7e8251cfb5bb";
  const apiKey = "d97f0349-9a4a-4b87-935b-7fb1484ca9a2";
  const buttonConfig = {
    position: "bottom-right",
    offset: "40px",
    width: "50px",
    height: "50px",
    idle: { // Cuando el boton no esta activo
      color: `rgb(93, 254, 202)`,
      type: "pill", // or "round"
      title: "¿Deseas agendar una cita?", 
      subtitle: "Habla con tu Asistente IA", 
      icon: `https://unpkg.com/lucide-static@0.321.0/icons/phone.svg`,
    },
    loading: {
      color: `rgb(93, 124, 202)`,
      type: "pill", // or "round"
      title: "Conectando...",
      subtitle: "Espere un momento...",
      icon: `https://unpkg.com/lucide-static@0.321.0/icons/loader-2.svg`,
    },
    active: {
      color: `rgb(255, 0, 0)`,
      type: "pill", // or "round"
      title: "Llamada en progreso...",
      subtitle: "Cortar llamada.",
      icon: `https://unpkg.com/lucide-static@0.321.0/icons/phone-off.svg`,
    },
  };

  (function (d, t) {
    var g = document.createElement(t),
      s = d.getElementsByTagName(t)[0];
    g.src =
      "https://cdn.jsdelivr.net/gh/VapiAI/html-script-tag@latest/dist/assets/index.js";
    g.defer = true;
    g.async = true;
    s.parentNode.insertBefore(g, s);
  
    g.onload = function () {
      vapiInstance = window.vapiSDK.run({
        apiKey: apiKey,
        assistant: assistant,
        config: buttonConfig,
      });
  
      // Función para abrir el asistente
      function openAssistant() {
        vapiInstance.open();
        // Remover el evento después de abrir el asistente
        document.removeEventListener('mousemove', openAssistant);
      }
  
      // Agregar evento para detectar movimiento del mouse
      document.addEventListener('mousemove', openAssistant);
    };
  })(document, "script");