/**
 * Permite cambiar en el dropdown el valor que cogemos
 */

$(document).ready(function(){
    $(".dropdown-menu a.dhont").on("click", function(){
        $(".btn.dropdown-toggle").html('DHondt <span class="caret"></span>');
    });
    $(".dropdown-menu a.sainte").on("click", function(){
        $(".btn.dropdown-toggle").html('Sainte-Lagüe <span class="caret caret-up"></span>');
    });
});