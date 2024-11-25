document.addEventListener('DOMContentLoaded', function() {
    const profileForm = document.getElementById('profileForm');
    const btnChangePhoto = document.querySelector('.btn-change-photo');

    // Manejar cambio de foto de perfil
    btnChangePhoto.addEventListener('click', function() {
        const input = document.createElement('input');
        input.type = 'file';
        input.accept = 'image/*';
        
        input.onchange = function(e) {
            const file = e.target.files[0];
            if (file) {
                // Aquí iría la lógica para subir la imagen
                const formData = new FormData();
                formData.append('photo', file);
                
                fetch('/api/medico/update-photo', {
                    method: 'POST',
                    body: formData
                })
                .then(response => response.json())
                .then(data => {
                    if (data.success) {
                        // Actualizar la imagen en la UI
                        document.querySelector('.profile-image img').src = data.photoUrl;
                    }
                })
                .catch(error => console.error('Error:', error));
            }
        };
        
        input.click();
    });

    // Manejar actualización del perfil
    profileForm.addEventListener('submit', function(e) {
        e.preventDefault();
        
        const formData = new FormData(this);
        const data = Object.fromEntries(formData.entries());

        // Validar contraseñas si se están actualizando
        if (data.newPassword) {
            if (data.newPassword !== data.confirmPassword) {
                alert('Las contraseñas no coinciden');
                return;
            }
        }

        // Aquí iría la lógica para actualizar el perfil
        fetch('/api/medico/update-profile', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert('Perfil actualizado correctamente');
            }
        })
        .catch(error => console.error('Error:', error));
    });
}); 