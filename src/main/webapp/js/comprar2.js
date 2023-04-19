let formBtn = document.querySelector('#login-btn');
let loginForm = document.querySelector('.login-form-container');
let formClose = document.querySelector('#form-close');


window.onscroll = () =>{
	loginForm.classList.remove('active');
}


formBtn.addEventListener('click', () =>{
	loginForm.classList.add('active');
});

formClose.addEventListener('click', () =>{
	loginForm.classList.remove('active');
});

var swiper = new Swiper(".review-slider", {
    spaceBetween: 20,
    loop:true,
    autoplay: {
        delay: 2500,
        disableOnInteraction: false,
        watchSlidesVisibility: true,
            watchSlidesProgress: true,
    },
    breakpoints: {
        640: {
          slidesPerView: 1,
        },
        768: {
          slidesPerView: 1,
        },
        1024: {
          slidesPerView: 1,
        },
    },
});


