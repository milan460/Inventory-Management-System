
const URL = 'http://localhost:8080/admins';

let allAdmins = [];


document.addEventListener('DOMContentLoaded', () => {

    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = () => {

        if(xhr.readyState === 4){

            let admins = JSON.parse(xhr.responseText);

            admins.forEach( (admin) => {
                
                allAdmins.push(admin);
            });
        }
    };


    xhr.open('GET', URL);

    xhr.send();

    document.getElementById('login-form').addEventListener('submit', handleLogin);
});




//retrieve the form data and compare with allAdmins array
function handleLogin(event){

    event.preventDefault();
    let inputData = new FormData(document.getElementById('login-form'));


    let loginAdmin = {
        company : inputData.get('admin-company'),
        username : inputData.get('admin-username'),
        password : inputData.get('admin-password')
    }

    let matchedAdmin = allAdmins.filter( (admin) => {
        if(admin.company === loginAdmin.company && admin.username === loginAdmin.username && admin.password === loginAdmin.password){
            return admin;
        }
    });
    
    if(matchedAdmin.length === 0){
        alert('Invalid Credentials');
    }
    else {
        const {password, email, ...adminStorage} = matchedAdmin[0];
        // localStorage.setItem('admin', JSON.stringify(adminStorage));
        
        //stores the admin in the session storage to mimic user sessions to allow to use the admin's id for use in other files
        sessionStorage.setItem('admin', JSON.stringify(adminStorage))

        //redirects to the home page after successful login
        window.location.href = '../HTML/home.html';
    }
    

}
