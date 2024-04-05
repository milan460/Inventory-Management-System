const URL = 'http://localhost:8080/items';

let allWarehouses = [];

let allItems = [];

let currentItemIDToDelete = null;

const admin = JSON.parse(sessionStorage.getItem('admin'));

document.addEventListener('DOMContentLoaded', () => {

    getAllWarehouses();
    getAllItems();

    const navBrand = document.querySelector('.navbar-brand')
    navBrand.innerText = admin.company


    //event listener update Modal
    let updateModal = document.getElementById('update-modal');
    if(updateModal){
        updateModal.addEventListener('shown.bs.modal', (event) => {
            document.querySelectorAll('.edit-btn').forEach( (button) => {
                button.addEventListener('click', () => {
                    const itemId = button.getAttribute('data-item-id');
                    
                    const warehouseId = button.getAttribute('data-warehouse-id');
                    
                    const updateForm = document.getElementById('update-item-form');
                    updateForm.setAttribute('data-item-id', itemId);
                    updateForm.setAttribute('data-warehouse-id', warehouseId);
                    
                });
            });
            
            let updateForm = document.getElementById('update-item-form');
            
            updateForm.addEventListener('submit', (event) => handleUpdateForm(event))
        })
    }


    //event listener delete modal
    let deleteModal = document.getElementById('delete-modal');
    if(deleteModal){
        deleteModal.addEventListener('shown.bs.modal', (event) => {
            document.querySelectorAll('.delete-btn').forEach( (button) => {
                button.addEventListener('click', () => {
                    currentItemIDToDelete = button.getAttribute('data-item-id');
                });
            })

            let deleteForm = document.getElementById('delete-item-form');
            deleteForm.addEventListener('submit', (event) => handleDeleteForm(event, currentItemIDToDelete));
        })
    }

    //event listener for create form
    let createForm = document.getElementById('create-item-form')
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

    document.getElementById('btn-close').addEventListener('click', () => {
        document.getElementById('create-item-form').reset();
    });
    
});

/**
 * Functionality to handle retrieving or GET Requests for Items and Warehouses
 */

//retrieve all the items that match the admin id
function getAllItems(){

    fetch(URL + '/admin/' + admin.id, {
        method : 'GET',
        headers : {
            'Content-Type' : 'application/json',
        },
    }).then( (data) => {

        return data.json();

    }).then( (items) => {
        
        items.forEach( item => {
            addItemsToTable(item)
            allItems.push(item)
        });
        

    }).catch((error) => {
        console.error(error);
    })

}

//retrieving all the warehouses that match the admin id and adding to the warehouse array
function getAllWarehouses(){

   
    fetch('http://localhost:8080/warehouses', {
        method : 'GET',
        headers : {
            'Content-Type' : 'application/json',
        },
    }).then( (data) => {

        return data.json();

    }).then( (warehouses) => {

        warehouses.filter( warehouse => warehouse.admin.id === admin.id)
        .forEach( warehouse => {
            
            allWarehouses.push(warehouse);
            

        })
        
        populateWarehouseDropdown(allWarehouses);
        

    }).catch((error) => {
        console.error(error);
    });

}

/**
 * functionality of the creating items and POST request 
 */

//handle submission of creating item form
function handleCreateForm(event){
    event.preventDefault();

    let inputData = new FormData(document.getElementById('create-item-form'));

    const newItemData = {
        name : inputData.get('item-name'),
        itemType : inputData.get('item-type'),
        quantity : inputData.get('item-quantity'),
        warehouse : {
            id : parseInt(inputData.get('item-Warehouse')),
            admin : {
                id : admin.id
            }
        }
    }

    fetch(URL + '/createItem', {
        method : 'POST',
        headers : {
            'Content-Type' : 'application/json',
        },
        body : JSON.stringify(newItemData)
    }).then( (data) => {

        return data.json();

    }).then( (newItem) => {
        const warehouse = allWarehouses.find( warehouse => warehouse.id === newItem.warehouse.id)
        newItem.warehouse.name = warehouse.name
        addItemsToTable(newItem);
        document.getElementById('create-btn-close').click()
        cancelForm();
    })

   
}

function addItemsToTable(item){
   

    //creating the table rows and the table data elements
    let tr = document.createElement('tr');
    let name = document.createElement('td');
    let itemType = document.createElement('td');
    let quantity = document.createElement('td');
    let warehouse = document.createElement('td');
    let warehouseId = document.createElement('td');
    let editBtn = document.createElement('td');
    let deleteBtn = document.createElement('td');

    //assigning the item properties to the table data
    name.innerText = item.name;
    itemType.innerText = item.itemType;
    quantity.innerText = item.quantity;
    warehouse.innerText = item.warehouse.name;
    warehouseId.innerText = item.warehouse.id;

    editBtn.innerHTML = `<button type="button" id="edit-button" class="btn btn-primary edit-btn" data-bs-toggle="modal" data-bs-target="#update-modal" data-item-id="${item.id}" data-warehouse-id="${item.warehouse.id}">Edit</button>`;
    deleteBtn.innerHTML = `<button type="button" class="btn btn-danger delete-btn" id="delete-button" data-bs-toggle="modal" data-bs-target="#delete-modal" data-item-id="${item.id}" data-warehouse-id="${item.warehouse.id}">Delete</button>`;

    //appending the table data to the table rows
    tr.appendChild(name);
    tr.appendChild(itemType);
    tr.appendChild(quantity);
    tr.appendChild(warehouse);
    tr.appendChild(warehouseId)
    tr.appendChild(editBtn);
    tr.appendChild(deleteBtn);

    //set the attributes for each table row
    tr.setAttribute('id', 'tr-' + item.id);

    //adding the table rows to the table body
    document.getElementById('item-table-body').appendChild(tr);
}

//populates the warehouse section in the form with the current warehouses
function populateWarehouseDropdown(warehouses){
   
    const selectTags = document.querySelectorAll('.warehouse-select')
    
    selectTags.forEach( selectTag => {
        warehouses.forEach( warehouse => {
            
            const optionTag = document.createElement('option')
            optionTag.value = warehouse.id;
            optionTag.innerText = warehouse.name;
            selectTag.appendChild(optionTag);
        })
    });   
}

/**
 * Functionality for Updating Items and PUT Request
 */


//handles submission of the update form and fetches the response of the PUT request
function handleUpdateForm(event){
    event.preventDefault();

   
    const updateForm = document.getElementById('update-item-form');
    const itemId = updateForm.getAttribute('data-item-id');

    let inputData = new FormData(document.getElementById('update-item-form'));
    const warehouseId = parseInt(inputData.get('item-Warehouse'))
    

    let itemFormData = {
        id : itemId,
        name : inputData.get('item-name'),
        itemType : inputData.get('item-type'),
        quantity : inputData.get('item-quantity'),
        warehouse : {
            id : warehouseId,
            admin : {
                id : admin.id
            }
        }
    }

   

    fetch(URL + '/updateItem', {
        method : 'PUT',
        headers : {
            'Content-Type' : 'application/json',
        },
        body : JSON.stringify(itemFormData)
    }).then( (data) => {

        return data.json()

    }).then( (updatedItem) => {
        updateItemInTable(updatedItem);
        document.getElementById('update-btn-close').click()
        cancelForm();
    }).catch( (error) => {
        console.error(error);
    });

    //function to add the updated Item to the table
    function updateItemInTable(updatedItem){
        
        
        document.getElementById('tr-' + updatedItem.id).innerHTML = 
        `
        <td>${updatedItem.name}</td>
        <td>${updatedItem.itemType}</td>
        <td>${updatedItem.quantity}</td>
        <td>${updatedItem.warehouse.name}</td>
        <td>${updatedItem.warehouse.id}</td>
        <td><button type="button" id="edit-button" class="btn btn-primary edit-btn" data-bs-toggle="modal" data-bs-target="#update-modal" data-item-id="${updatedItem.id}" data-warehouse-id="${updatedItem.warehouse.id}">Edit</button></td>
        <td><button type="button" class="btn btn-danger delete-btn" id="delete-button" data-bs-toggle="modal" data-bs-target="#delete-modal" data-item-id="${updatedItem.id}" data-warehouse-id="${updatedItem.warehouse.id}">Delete</button></td>
        `
    }
}


/**
 * functionality for DELETE Requests and Deleting Items
 */

//function to handle the delete submission
function handleDeleteForm(event, itemId){
    event.preventDefault();

    
    

    fetch(URL + '/deleteItem/' + itemId, {
        method : 'DELETE',
        headers : {
            'Content-Type' : 'application/json',
        },
    }).then( (data) => {

        if(data.status === 204){
            deleteItemFromTable(itemId)
            document.getElementById('delete-btn-close').click();
        }

    }).catch( (error) => {
        console.error(error);
    })


    
}


//delete item from the table
function deleteItemFromTable(itemId){
    const itemElement = document.getElementById('tr-' + itemId);
    itemElement.remove();
}


//resets the form fields
function cancelForm(){
    document.getElementById('update-item-form').reset();
    document.getElementById('create-item-form').reset();
}



