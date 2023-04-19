<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    
    
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Turismo Tierra Media LC</title>
	

<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
	
	<!-- link de fontawesome -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
	
	<!-- link hoja de estilo css -->
	<style>
     <%@ include file="../css/index.css"%>
</style>
	
</head>







<body>

<!-- comienza la seccion del header -->

<header>


    <a href="#" class="logo"><span>T</span>urismo Tierra media</a>
   <!--  
    <nav class="navbar">
</nav>
 --> 
   <div class= "icons">
      <i class="fas fa-user" id="login-btn"></i>
   </div>
   
</header>


<!-- termina la seccion del header -->

<!-- contenedor login  -->

<div class="login-form-container">
   
     <i class="fas fa-times" id="form-close"></i>

   <form action="//localhost:8080/AppWebTierraMedia/login" method="post">
   <h3>login</h3>
   <input type="email" class="box" placeholder="email" name="email">
   <input type="password" class="box" placeholder="contraseña" name="password">
   <button type="submit" class="btn btn-primary">Ingresar</button>
  <!-- <input type="submit" value="iniciar sesion" class="btn">-->
   </form>
     
</div>

<!-- seccion home empieza -->
<section class="home" id="home">
     <div class="content">
         <h3>comparte una aventura</h3>
         <p>descubre nuevos lugares con nosotros, la aventura te espera</p>
         <a href="#" class="btn">descubre mas</a>
     </div>
     
     <div class="video-container">
        <video src="img/video.mp4" loop autoplay muted></video>
     </div>

</section>
<!-- seccion home termina -->

<!-- seccion slider comienza -->

<section class="gallery" id="gallery">

 <h1 class="heading">
   <span>g</span>
   <span>a</span>
   <span>l</span>
   <span>e</span>
   <span>r</span>
   <span>i</span>
   <span>a</span>
  </h1>
  
  
    <div class="swiper review-slider">
  
      <div class="swiper-wrapper">
      
         <div class="swiper-slide">
         
            <div class="box">
              <img src="img/bosque.jpg" alt="">
              <h3>bosque negro</h3>
              <div class="stars">
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
              </div>
            </div>
         
         </div>
      
          <div class="swiper-slide">
         
            <div class="box">
              <img src="img/erebor.jpg" alt="">
              <h3>erebor</h3>
              <div class="stars">
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="far fa-star"></i>
              </div>
            </div>
         
         </div>
         
          <div class="swiper-slide">
         
            <div class="box">
              <img src="img/helm.jpg" alt="">
              <h3>helm</h3>
   
              <div class="stars">
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              </div>
            </div>
         
         </div>
         
          <div class="swiper-slide">
         
            <div class="box">
              <img src="img/shrine.jpg" alt="">
              <h3>la comarca</h3>
              <div class="stars">
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              </div>
            </div>
         
         </div>
         
          <div class="swiper-slide">
         
            <div class="box">
              <img src="img/Loth.jpg" alt="">
              <h3>lothlorien</h3>

              <div class="stars">
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="far fa-star"></i>
              </div>
            </div>
         
         </div>
         
          <div class="swiper-slide">
         
            <div class="box">
              <img src="img/mordor.jpeg" alt="">
              <h3>mordor</h3>
              <div class="stars">
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              </div>
            </div>
         
         </div>
         
         
          <div class="swiper-slide">
         
            <div class="box">
              <img src="img/moria.jpg" alt="">
              <h3>moria</h3>

              <div class="stars">
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="far fa-star"></i>
              </div>
            </div>
         
         </div>
         
          <div class="swiper-slide">
         
            <div class="box">
              <img src="img/tirith.jpg" alt="">
              <h3>minas tirith</h3>
              <div class="stars">
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
              </div>
            </div>
         
         </div>
      
      
      
      </div>
  
  </div>
 
  
 </section>



<!-- seccion slider termina  -->




<!-- footer section starts -->

<section class="footer">
   <div class="box-container">
      
       <div class="box">
         <h3>github</h3>
         <a href="https://github.com/ErCanaro" target="_blank" rel="noopener noreferrer">Enzo Bulacio</a>
         <a href="https://github.com/JosCorSgo" target="_blank" rel="noopener noreferrer">Jose Cordoba</a>
         <a href="https://github.com/PaulaSuescun" target="_blank" rel="noopener noreferrer">Paula Suescun</a>
         <a href="https://github.com/AlanBarani" target="_blank" rel="noopener noreferrer">Alan Barani</a>
         <a href="https://github.com/Mauricio12Sanchez" target="_blank" rel="noopener noreferrer">Mauro Sanchez</a>
         <a href="https://github.com/AvalosEmanuel" target="_blank" rel="noopener noreferrer">Ema Avalos</a>
         
      </div>    
      <div class="box">
      
  
      <img src="img/lecodefooter.png" alt="">
      
      
      </div>
      
       <div class="box">
         <h3>linkedin</h3>
         <a href="https://www.linkedin.com/in/alan-jes%C3%BAs-barani-9a021a227" target="_blank" rel="noopener noreferrer">Alan Barani</a>
         <a href="https://www.linkedin.com/in/alan-jes%C3%BAs-barani-9a021a227" target="_blank" rel="noopener noreferrer">Ema Avalos</a>
         <a href="#">Paula Suescun</a>
         <a href="#">Enzo Bulacio</a>
         <a href="#">Jose Cordoba</a>
         <a href="#">Mauro Sanchez</a>
      </div>
       
       
   </div>
   
   <h1 class="credit">creado por <span>Le Code</span></h1>

</section>


<!-- footer section ends -->








<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>



<!--  custom js file link -->
<script src="js/indexscript.js"></script>

</body>
</html>
