
const URL = 'http://localhost:8080/warehouses';

let allWarehouses = [];

const admin = JSON.parse(sessionStorage.getItem('admin'));

document.addEventListener('DOMContentLoaded', () => {

    getAllWarehouses();

    const navBrand = document.querySelector('.navbar-brand')
    navBrand.innerText = admin.company


    /**
     * Handles the update modal
     */
    //event listener for the update model after it has been loaded to passover the warehouseId from the button to the form input
    let updateModal = document.getElementById('update-modal');
    if(updateModal){
        updateModal.addEventListener('shown.bs.modal', (event) => {
            document.querySelectorAll('.edit-btn').forEach( (button) => {
                button.addEventListener('click', () => {
                    const warehouseId = button.getAttribute('data-warehouse-id');
                    const updateForm = document.getElementById('update-warehouse-form');
                    updateForm.setAttribute('data-warehouse-id', warehouseId);
                    
                });
            });

            let updateForm = document.getElementById('update-warehouse-form');
            updateForm.addEventListener('submit', (event) => handleUpdateForm(event))
            
        });
    }


    //event listener for the delete modal after it has been loaded to passover the warehouseId from the button to the form input
    let deleteModal = document.getElementById('delete-modal');
    if(deleteModal){
        deleteModal.addEventListener('shown.bs.modal', (event) => {
            document.querySelectorAll('.delete-btn').forEach( (button) => {
                button.addEventListener('click', () => {
                    const warehouseId = button.getAttribute('data-warehouse-id');
                    const deleteForm = document.getElementById('delete-warehouse-form');
                    deleteForm.setAttribute('data-warehouse-id', warehouseId);
                });
            });

            let deleteForm = document.getElementById('delete-warehouse-form');
            deleteForm.addEventListener('submit', (event) => handleDeleteForm(event))
        });
    }



        //create eventlistener for the form that creates the warehouse
    let createForm = document.getElementById('create-warehouse-form')
    if(createForm){
        createForm.addEventListener('submit', handleCreateForm)
    }

    //handles the logout
    let logoutBtn = document.getElementById('logout');
    if(logoutBtn){
        logoutBtn.addEventListener('click', () => {
            sessionStorage.clear();
            window.location.href = '../HTML/login.html';
        })
    }
    

});



/*
 * Functions to handle the retrieving or GET requests and adding to the warehouse table
 */
//function to fetch all warehouses from the database
function getAllWarehouses(){

   
    fetch(URL, {
        method : 'GET',
        headers : {
            'Content-Type' : 'application/json',
        },
    }).then( (data) => {

        return data.json();

    }).then( (warehouses) => {

        warehouses.filter( warehouse => warehouse.admin.id === admin.id)
        .forEach( warehouse => {
            console.log(warehouse)
            addWarehouseToTable(warehouse);
            allWarehouses.push(warehouse);

        })
        

    }).catch((error) => {
        console.error(error);
    });

}


/*
 * functions that handle creating and POST warehouses 
 *
 */

//handling submission of creating warehouse form
function handleCreateForm(event){
    event.preventDefault();

    let inputData = new FormData(document.getElementById('create-warehouse-form'));


    const newWarehouseData = {
        name : inputData.get('warehouse-name'),
        address : inputData.get('warehouse-address'),
        category : inputData.get('warehouse-category'),
        maxCapacity : inputData.get('warehouse-capacity'),
        admin : {
            id : admin.id
        }
    }

  

    fetch(URL + '/createWarehouse', {
        method : 'POST',
        headers : {
            'Content-Type' : 'application/json',
        },
        body : JSON.stringify(newWarehouseData)
    }).then( (data) => {

        return data.json();
    }).then( (newWarehouse) => {
        addWarehouseToTable(newWarehouse);
        document.getElementById('create-btn-close').click()
        cancelForm();
    }).catch((error) => {
        console.error(error);
    })

}



//this function handles added warehouses to the table
function addWarehouseToTable(warehouse){

    
    //creating the table row and table data elements 
    let tr = document.createElement('tr'); 
    let name = document.createElement('td');
    let address = document.createElement('td');
    let category = document.createElement('td');
    let maxCapacity = document.createElement('td');
    let editBtn = document.createElement('td');
    let deleteBtn = document.createElement('td')

    //assigning the warehouse properties to the table data
    name.innerText = warehouse.name;
    address.innerText = warehouse.address;
    category.innerText = warehouse.category;
    maxCapacity.innerText = warehouse.maxCapacity

    editBtn.innerHTML = `<button type="button" id="edit-button" class="btn btn-primary edit-btn" data-bs-toggle="modal" data-bs-target="#update-modal" data-warehouse-id="${warehouse.id}">Edit</button>`;

    deleteBtn.innerHTML = `<button type="button" class="btn btn-danger delete-btn" id="delete-button" data-bs-toggle="modal" data-bs-target="#delete-modal" data-warehouse-id="${warehouse.id}">Delete</button>`;

    //appending the table data to the table rows
    tr.appendChild(name);
    tr.appendChild(address);
    tr.appendChild(category);
    tr.appendChild(maxCapacity);
    tr.appendChild(editBtn);
    tr.appendChild(deleteBtn);


    tr.setAttribute('id', 'tr-' + warehouse.id);

    //adding the table rows to the table body
    document.getElementById('warehouse-table-body').appendChild(tr);

}



/*
 *   Functions to handle updating and PUT requests regarding Warehouses
*/

function handleUpdateForm(event){ 
    event.preventDefault();

    
    const updateForm = document.getElementById('update-warehouse-form')
    const warehouseId = updateForm.getAttribute('data-warehouse-id');

    let inputData = new FormData(document.getElementById('update-warehouse-form'));

    
    


    let warehouseFormData = {
        id : warehouseId,
        name : inputData.get('warehouse-name'),
        address : inputData.get('warehouse-address'),
        category : inputData.get('warehouse-category'),
        maxCapacity : inputData.get('warehouse-capacity'),
        admin : {
            id : admin.id
        }
    }


    fetch(URL + '/updateWarehouse', {
        method : 'PUT',
        headers : {
            'Content-Type' : 'application/json',
        },
        body : JSON.stringify(warehouseFormData)
    }).then((data) => {

        return data.json()
    }).then((updatedWarehouse) => {
        updateWarehouseInTable(updatedWarehouse);
        document.getElementById('update-btn-close').click()
        cancelForm();
    }).catch((error) => {

        console.error(error);
    });

}

//functions to add the updated warehouse to the table
function updateWarehouseInTable(updatedWarehouse){

    document.getElementById('tr-' + updatedWarehouse.id).innerHTML = 
    `
    <td>${updatedWarehouse.name}</td>
    <td>${updatedWarehouse.address}</td>
    <td>${updatedWarehouse.category}</td>
    <td>${updatedWarehouse.maxCapacity}</td> 
    <td><button type="button" id="edit-button" class="btn btn-primary edit-btn" data-bs-toggle="modal" data-bs-target="#update-modal" data-warehouse-id="${updatedWarehouse.id}">Edit</button></td>
    <td><button type="button" class="btn btn-danger delete-btn" id="delete-button" data-bs-toggle="modal" data-bs-target="#delete-modal" data-warehouse-id="${updatedWarehouse.id}">Delete</button></td>
    `
}

/**
 * functionality for DELETE Requests and Deleting warehouses
 */

//function to handle the delete submission
function handleDeleteForm(event){
    event.preventDefault();
    

    const deleteForm = document.getElementById('delete-warehouse-form');
    const warehouseId = deleteForm.getAttribute('data-warehouse-id');


    fetch(URL + '/deleteWarehouse/' + warehouseId, {
        method : 'DELETE',
        headers : {
            'Content-Type' : 'application/json',
        },
    }).then( (data) => {

        if(data.status === 204){
            
            deleteWarehouseFromTable(warehouseId);
            document.getElementById('delete-btn-close').click();
        }
    }).catch( (error) => {
        console.error(error);
    })
}

//removing the warehouse from the table
function deleteWarehouseFromTable(warehouseId){
    const warehouseElement = document.getElementById('tr-' + warehouseId);
    warehouseElement.remove();
}

function cancelForm(){
    document.getElementById('update-warehouse-form').reset()
    document.getElementById('create-warehouse-form').reset()
}