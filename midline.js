function resizeMidLine(){
    let localHeight = getComputedStyle(document.getElementById("leftHalfImage")).height;
    document.getElementById("midLine").style.height = localHeight;
}

window.addEventListener('resize', resizeMidLine);

function setupMidLine(){
    document.getElementById("midLine").style.backgroundColor = "#fff";
    resizeMidLine();
}

window.addEventListener('load', setupMidLine);