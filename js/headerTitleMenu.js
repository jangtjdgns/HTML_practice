var titleMenu = document.querySelectorAll(".title-menu");
var navScroll = document.querySelector("#header-sub");
console.log(navScroll);

titleMenu.forEach(function (p) {
    p.addEventListener("mouseover", function () {
        navScroll.style.height = "400px";
        navScroll.style.transform = "scaleY(1)";
        
    })

    // document.addEventListener("mouseout", function () {
    //     navScroll.style.height = "0";
    //     navScroll.style.transform = "scaleY(0)";
    // })
});


document.addEventListener("keydown", function (e) {
    if (e.key === 'Escape') {
        navScroll.style.height = "0";
        navScroll.style.transform = "scaleY(0)";
    }
});

        // window.addEventListener("click", function () {
        //     navScroll.style.height = "0";
        //     navScroll.style.transform = "scaleY(0)";
        // });
        