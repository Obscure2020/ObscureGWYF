function resizeMidLine(){
    let localHeight = getComputedStyle(document.getElementById("leftHalfImage")).height;
    document.getElementById("midLine").style.height = localHeight;
}
window.addEventListener('load', resizeMidLine);
window.addEventListener('resize', resizeMidLine);