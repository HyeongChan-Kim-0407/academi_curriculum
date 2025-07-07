// testJs.js
document.getElementById("tagH1").addEventListener("click", function (evt) {
  alert("권준민 바보!!");
  evt.target.innerHTML = "권준민 똥멍청이";
  evt.target.style.color = "red";
});
