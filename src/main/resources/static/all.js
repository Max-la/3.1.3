
function edit() {

    let id = document.getElementById('editid').value
    let name = document.getElementById('editname').value;
    let lastName = document.getElementById('editlastName').value;
    let password = document.getElementById('editpassword').value;
    let roles = document.getElementById('editrole').value;

    console.log(name)

    let rol = [];
    if (roles === "ADMIN") {
        let role = {
            'id': 1,
            'name': 'ADMIN'
        }
        rol.push(role)
    }
    if (roles === "USER") {
        let role = {
            'id': 2,
            'name': 'USER'
        }
        rol.push(role)
    }

    fetch('http://localhost:8080/api/edit', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json;charset=UTF-8',
            'Accept': 'application/json'
        },
        body: JSON.stringify({
            id,
            name,
            lastName,
            password,
            'roles': rol
        })
    })
        .then(res => {

            console.log(res.json());
            location.assign('http://localhost:8080/admin')
        })

}
function getDelete(id){
    fetch('http://localhost:8080/api/delete/' + id, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json;charset=UTF-8',
            'Accept': 'application/json'
        }
    })
        .then(res => {
            $('#' + id).remove();
            location.assign('http://localhost:8080/admin')
        });
}
function getModalDelete(id){
    fetch('http://localhost:8080/api/getUserById/' + id)
        .then(res => res.json())
        .then(user => {
            let modal = document.getElementById('modalData')
            modal.innerHTML = '                            <div class="modal fade" id="delete"  role="dialog"\n' +
                '                                 aria-hidden="true">\n' +
                '                                <div class="modal-dialog">\n' +
                '                                    <div class="modal-content">\n' +
                '                                        <div class="modal-header">\n' +
                '                                            <h5 class="modal-title">Delete user</h5>\n' +
                '                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">\n' +
                '                                                <span aria-hidden="true">&times;</span>\n' +
                '                                            </button>\n' +
                '                                        </div>\n' +
                '                                        <div class="modal-body">\n' +
                '                                            <div class="row justify-content-center align-items-center">\n' +
                '                                                <form class="text-center" method="DELETE"\n' +
                '                                                      >\n' +
                '                                                    <div class="form-group font-weight-bold">\n' +
                '                                                        <label >Id</label>\n' +
                '                                                        <input type="number" class="form-control" name="id" \n' +
                '                                                               value="'+user.id+'" readonly>\n' +
                '                                                        <label >Name</label>\n' +
                '                                                        <input type="text" class="form-control" value="'+user.name+'"\n' +
                '                                                               name="name" readonly>\n' +
                '                                                        <label>LastName</label>\n' +
                '                                                        <input type="text" class="form-control"\n' +
                '                                                               value="'+user.lastName+'"\n' +
                '                                                               name="lastName" readonly>\n' +
                '                                                        <label>Password</label>\n' +
                '                                                        <input type="text" class="form-control"\n' +
                '                                                               value="'+user.password+'"\n' +
                '                                                               name="password" readonly>\n' +
                '\n' +
                '                                                        <label>Role</label>\n' +
                '                                                        <select class="form-select w-100" size="2" id="role"\n' +
                '                                                                name="role" readonly="role">\n' +
                '                                                        <option value="ADMIN">ADMIN</option>\n' +
                '                                                        <option value="USER">USER</option>\n' +
                '                                                        </select>\n' +
                '                                                    </div>\n' +
                '                                                </form>\n' +
                '                                            </div>\n' +
                '                                        </div>\n' +
                '                                        <div class="modal-footer">\n' +
                '                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close\n' +
                '                                            </button>\n' +
                '                                            <button type="button" class="btn btn-danger" onclick="getDelete('+user.id+')" \n' +
                '                                                    >Delete\n' +
                '                                            </button>\n' +
                '                                        </div>\n' +
                '                                    </div>\n' +
                '                                </div>\n' +
                '                            </div>';
            $("#delete").modal();

        });
}
function getModalEdit(id){
    fetch('http://localhost:8080/api/getUserById/' + id)
        .then(res => res.json())
        .then(user => {
            let modal = document.getElementById('modalData');
            modal.innerHTML ='<div class="modal fade"  id="modalEdit" role="dialog" aria-hidden="true">\n' +
                '                            <div class="modal-dialog">\n' +
                '                                <div class="modal-content">\n' +
                '                                    <div class="modal-header">\n' +
                '                                        <h5 class="modal-title">Edit</h5>\n' +
                '                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">\n' +
                '                                            <span aria-hidden="true">&times;</span>\n' +
                '                                        </button>\n' +
                '                                    </div>\n' +
                '                                    <div class="modal-body">\n' +
                '                                        <div class="row justify-content-center align-items-center">\n' +
                '                                            <form class="text-center" method="PUT" >\n' +
                '                                                <div class="form-group font-weight-bold">\n' +
                '                                                    <label>Id</label>\n' +
                '                                                    <input type="number" class="form-control" id="editid"\n' +
                '                                                           name="id" value="'+user.id+'" readonly>\n' +
                '                                                    <label>Name</label>\n' +
                '                                                    <input type="text" class="form-control" id="editname" placeholder="name" \n' +
                '                                                           value="'+user.name+'">\n' +
                '                                                    <label>LastName</label>\n' +
                '                                                    <input type="text" class="form-control" id="editlastName" placeholder="lastName" \n' +
                '                                                           value="'+user.lastName+'">\n' +
                '                                                    <label>Password</label>\n' +
                '                                                    <input type="password" class="form-control"\n' +
                '                                                           id="editpassword"  placeholder="password">\n' +
                '                                                    <label>Role</label>\n' +
                '                                                    <select class="form-select w-100" size="2" id="editrole"\n' +
                '                                                            name="roles">\n' +
                '                                                        <option value="ADMIN" selected>ADMIN</option>\n' +
                '                                                        <option value="USER" selected>USER</option>\n' +
                '                                                    </select>\n' +
                '                                                </div>\n' +
                '                                            </form>\n' +
                '                                        </div>\n' +
                '                                    </div>\n' +
                '                                    <div class="modal-footer">\n' +
                '                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close\n' +
                '                                        </button>\n' +
                '                                        <button type="submit" data-dismiss="modal" class="btn btn-info" onclick="edit()">Edit\n' +
                '                                        </button>\n' +
                '                                    </div>\n' +
                '                                </div>\n' +
                '                            </div>\n' +
                '                        </div>';
            $("#modalEdit").modal();
        });
}

$(document).ready(function () {
    getAllUsers();
});
    const admin = document.getElementById('allUsers')
    const del = document.getElementById('delete')
    let output = "";

  async  function getAllUsers() {
      await  fetch('http://localhost:8080/api')
            .then((response) => response.json())
            .then((users) => {
                users.forEach(user => {

                    let rolesAsString = "";
                    user.roles.forEach(role => rolesAsString += role.name + " ");

                    let temp = "";
                    temp += "<tr>"
                    temp += "<td>" + user.id + "</td>"
                    temp += "<td>" + user.name + "</td>"
                    temp += "<td>" + user.lastName + "</td>"
                    temp += "<td>" + rolesAsString + "</td>"
                    temp += "<td>"
                    temp += "<button type='button' class='btn btn-info edit-btn'  onclick='getModalEdit(" + user.id + ")' data-toggle='modal'>Edit</button>"
                    temp += "</td>"
                    temp += "<td>"
                    temp += "<button type='button' class='btn btn-danger' onclick='getModalDelete("+user.id+")' data-toggle='modal' >Delete</button>"
                    temp += "</td>"

                    output += temp;
                    console.log(users)
                });
                admin.innerHTML = output;
            });

  }

async function addNewUser() {
  await  document.getElementById('new').addEventListener('submit', e => {
        e.preventDefault()

        let name = document.getElementById('newname').value;
        let lastName = document.getElementById('newlastName').value;
        let password = document.getElementById('newpassword').value;
        let roles = document.getElementById('newrole').value;

        let rol = [];
        if (roles === "ADMIN") {
            let role = {
                'id': 1,
                'name': 'ADMIN'
            }
            rol.push(role)
        }
        if (roles === "USER") {
            let role = {
                'id': 2,
                'name': 'USER'
            }
            rol.push(role)
        }

        fetch('http://localhost:8080/api/create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=UTF-8',
                'Accept': 'application/json'
            },
            body: JSON.stringify({
                name,
                lastName,
                password,
                'roles': rol
            })
        })
            .then(res =>
                console.log(res.json()))
            .then(data => location.assign('http://localhost:8080/admin'))
    })
}

















