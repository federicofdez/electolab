/**
 * Permite cambiar en el dropdown el valor que cogemos
 */

$(document).ready(function(){
    $(".dropdown-menu a.dhont").on("click", function(){
        $(".sistema").html('DHondt <span class="caret"></span>');
    });
    $(".dropdown-menu a.sainte").on("click", function(){
        $(".sistema").html('Sainte-Lagüe <span class="caret caret-up"></span>');
    });
    $(".dropdown-menu a.madrid").on("click", function(){
        $("numCA").html('Madrid <span class="caret caret-up"></span>');
    });
    $(".dropdown-menu a.barcelona").on("click", function(){
        $("numCA").html('Barcelona <span class="caret caret-up"></span>');
    });
     $(".dropdown-menu a.provincias").on("click", function(){
        $(".circunscripciones").html('Provincias <span class="caret caret-up"></span>');
    });
    $(".dropdown-menu a.comunidades").on("click", function(){
        $(".circunscripciones").html('Comunidades <span class="caret caret-up"></span>');
    });
     $(".dropdown-menu a.espana").on("click", function(){
        $(".circunscripciones").html('España <span class="caret caret-up"></span>');
    });


});
