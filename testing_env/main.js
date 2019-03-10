$(window).on('scroll', function(){
    if($(window).scrollTop()){
        $('nav').addClass('black');
        $('a').removeClass('stationary');
    }else{
         $('nav').removeClass('black');
         $('a').addClass('stationary');    
    }
    
})