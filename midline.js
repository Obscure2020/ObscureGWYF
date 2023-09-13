function resizeMidLine(){
    let localHeight = getComputedStyle(document.getElementById("leftHalfImage")).height;
    document.getElementById("midLine").style.height = localHeight;
}

window.addEventListener('resize', resizeMidLine);

function setupMidLine(){
    resizeMidLine();
    document.getElementById("midLine").style.backgroundColor = "#fff";
}

window.addEventListener('load', setupMidLine);