document.addEventListener('DOMContentLoaded', function() {
    const container = document.querySelector('.container');
    const registerLink = document.querySelector('.register-link');
    const loginLink = document.querySelector('.login-link');
    const signupForm = document.getElementById('signup-form');
    const loginForm = document.getElementById('login-form');
    const passwordInput = document.getElementById('password');
    const confirmPasswordInput = document.getElementById('confirm-password');

    // Función para validar contraseñas
    function validatePasswords() {
        const password = passwordInput.value;
        const confirmPassword = confirmPasswordInput.value;
        return password === confirmPassword;
    }

    // Manejar el registro
    signupForm.addEventListener('submit', function(e) {
        e.preventDefault();
        
        // Validar contraseñas antes de enviar
        if (!validatePasswords()) {
            alert('Las contraseñas no coinciden');
            return;
        }

        const formData = {
            nombre: this.querySelector('input[name="nombre"]').value,
            apellido: this.querySelector('input[name="apellido"]').value,
            email: this.querySelector('input[name="email"]').value,
            password: passwordInput.value
        };

        fetch(this.action, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: new URLSearchParams(formData)
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert(data.message);
                this.reset();
                container.classList.remove('active');
            } else {
                alert(data.message);
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error al procesar la solicitud');
        });
    });

    // Cambiar entre formularios
    registerLink.addEventListener('click', (e) => {
        e.preventDefault();
        container.classList.add('active');
    });

    loginLink.addEventListener('click', (e) => {
        e.preventDefault();
        container.classList.remove('active');
    });

    // Manejar el login
    loginForm.addEventListener('submit', function(e) {
        e.preventDefault();
        
        const formData = {
            email: this.querySelector('input[name="email"]').value,
            password: this.querySelector('input[name="password"]').value
        };

        fetch(this.action, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            
            body: new URLSearchParams(formData)
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                window.location.href = data.redirect;
            } else {
                alert(data.message);
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error al procesar la solicitud');
        });
    });
});