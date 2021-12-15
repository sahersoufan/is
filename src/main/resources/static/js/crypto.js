
function include(file) {

    var script  = document.createElement('script');
    script.src  = file;
    script.type = 'text/javascript';
    script.defer = true;

    document.getElementsByTagName('head').item(0).appendChild(script);

}

/* Include Many js files */
include('https://cdnjs.cloudflare.com/ajax/libs/cryptojs/3.1.2/rollups/aes.js');
function crypto() {

}
