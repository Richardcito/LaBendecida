document.addEventListener('DOMContentLoaded', function() {
    // Inicializar ScrollReveal
    ScrollReveal().reveal('.hero h1', { delay: 200, origin: 'top' });
    ScrollReveal().reveal('.hero p', { delay: 400, origin: 'bottom' });
    ScrollReveal().reveal('.about-content', { delay: 200, origin: 'left' });
    ScrollReveal().reveal('.about-image', { delay: 400, origin: 'right' });
    ScrollReveal().reveal('.team-member', { delay: 200, origin: 'bottom', interval: 200 });
    ScrollReveal().reveal('.testimonial', { delay: 200, origin: 'bottom', interval: 200 });

    // Carrusel de testimonios
    const testimonialCarousel = document.querySelector('.testimonial-carousel');
    const testimonials = document.querySelectorAll('.testimonial');
    let currentIndex = 0;

    function showNextTestimonial() {
        currentIndex = (currentIndex + 1) % testimonials.length;
        testimonialCarousel.scrollTo({
            left: testimonials[currentIndex].offsetLeft,
            behavior: 'smooth'
        });
    }

    setInterval(showNextTestimonial, 5000);

    // Menú hamburguesa para dispositivos móviles
    const burger = document.querySelector('.burger');
    const nav = document.querySelector('.nav-links');
    const navLinks = document.querySelectorAll('.nav-links li');

    burger.addEventListener('click', () => {
        nav.classList.toggle('nav-active');

        navLinks.forEach((link, index) => {
            if (link.style.animation) {
                link.style.animation = '';
            } else {
                link.style.animation = `navLinkFade 0.5s ease forwards ${index / 7 + 0.3}s`;
            }
        });

        burger.classList.toggle('toggle');
    });
});

