document.addEventListener('DOMContentLoaded', function() {
    // Navbar Scroll Effect
    const navbar = document.querySelector('.navbar');
    const navLinks = document.querySelectorAll('.nav-links a');
    const userIcons = document.querySelectorAll('.user-icon');

    function checkScroll() {
        if (window.scrollY > 50) {
            navbar.classList.add('navbar-scrolled');
            // Cambiar color de los enlaces e iconos
            navLinks.forEach(link => {
                if (!link.classList.contains('btn-reservar')) {
                    link.style.color = '#333';
                }
            });
            userIcons.forEach(icon => {
                icon.style.color = '#333';
            });
        } else {
            navbar.classList.remove('navbar-scrolled');
            // Restaurar color original de los enlaces e iconos
            navLinks.forEach(link => {
                if (!link.classList.contains('btn-reservar')) {
                    link.style.color = '#fff';
                }
            });
            userIcons.forEach(icon => {
                icon.style.color = '#fff';
            });
        }
    }

    // Verificar el scroll al cargar la página
    checkScroll();

    // Escuchar el evento de scroll
    window.addEventListener('scroll', checkScroll);

    // Menú móvil
    const burger = document.querySelector('.burger');
    const nav = document.querySelector('.nav-links');

    if (burger) {
        burger.addEventListener('click', () => {
            nav.classList.toggle('nav-active');
            
            // Animar los enlaces
            const navLinks = document.querySelectorAll('.nav-links li');
            navLinks.forEach((link, index) => {
                if (link.style.animation) {
                    link.style.animation = '';
                } else {
                    link.style.animation = `navLinkFade 0.5s ease forwards ${index / 7 + 0.3}s`;
                }
            });

            // Animación del burger
            burger.classList.toggle('toggle');
        });
    }

    // Hero Slider
    const slider = document.querySelector('.slider-container');
    const slides = document.querySelectorAll('.slide');
    const prevBtn = document.querySelector('.prev-slide');
    const nextBtn = document.querySelector('.next-slide');
    const dotsContainer = document.querySelector('.slider-dots');

    if (slider && slides.length > 0) {
        let currentSlide = 0;
        const slideCount = slides.length;

        // Crear dots
        slides.forEach((_, index) => {
            const dot = document.createElement('div');
            dot.classList.add('dot');
            if (index === 0) dot.classList.add('active');
            dot.addEventListener('click', () => goToSlide(index));
            dotsContainer.appendChild(dot);
        });

        const dots = document.querySelectorAll('.dot');

        function updateDots() {
            dots.forEach((dot, index) => {
                dot.classList.toggle('active', index === currentSlide);
            });
        }

        function goToSlide(n) {
            currentSlide = (n + slideCount) % slideCount;
            slider.style.transform = `translateX(-${currentSlide * 100}%)`;
            updateDots();
        }

        if (prevBtn && nextBtn) {
            prevBtn.addEventListener('click', () => goToSlide(currentSlide - 1));
            nextBtn.addEventListener('click', () => goToSlide(currentSlide + 1));
        }

        // Auto slide
        setInterval(() => goToSlide(currentSlide + 1), 5000);
    }
});