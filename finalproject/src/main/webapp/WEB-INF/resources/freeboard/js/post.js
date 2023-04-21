/* list button */
let listBtn = document.querySelector("#listBtn");

listBtn.addEventListener('click', function () {
    location.href="/freeboard/list?currPage=${param.currPage}&amount=${param.amount}";
})

/* remove button */
let removeBtn = document.querySelector("#removeBtn");

removeBtn.addEventListener('click', function() {
    let form = document.querySelector('form');

    form.setAttribute('method', 'POST');
    form.setAttribute('action', '/freeboard/remove?currPage=${param.currPage}&amount=${param.amount}');
    form.submit();
})