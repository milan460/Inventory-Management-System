const admin = JSON.parse(sessionStorage.getItem('admin'));

document.addEventListener('DOMContentLoaded', () => {

    const navBrand = document.querySelector('.navbar-brand')
    navBrand.innerText = admin.company

    const welcomeAdmin = document.getElementById('admin-name');
    welcomeAdmin.innerText = `Welcome, ${admin.firstName} ${admin.lastName}!`;

    //handles the logout
    let logoutBtn = document.getElementById('logout');
    if(logoutBtn){
        logoutBtn.addEventListener('click', () => {
            sessionStorage.clear();
            window.location.href = '../HTML/login.html';
        })
    }
});