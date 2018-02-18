<!DOCTYPE html>
<html lang="en">
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <meta charset="UTF-8" />
    <title>Title</title>

    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>

    <script src="http://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function() {

            $("#file_table").on("click",".delete",function() {
                var id = this.getAttribute("id");
                $.ajax({
                    type : "DELETE",
                    url : "/file/"+id,
                    //data : "name=" + name + "&age=" + age,
                    success : function(data) {
                        if(data.msg == "success"){
                            $("#"+id).closest("tr").remove();
                        }
                    }
                });
            });

            $("#edit_div").on("click",".save",function() {
                var id = this.getAttribute("id");
                var textarea = $("#edit_textarea").val();
                $.ajax({
                    type : "PUT",
                    url : "/file/"+id,
                    data : "content="+textarea,
                    success : function(data) {
                        console.log(data);
                        if(data.msg == "success"){
                            alert("Saved Successfully")
                        }
                    }
                });
            });

            $("#file_table").on("click",".edit",function() {
                $("#edit_div").empty();
                var id = this.getAttribute("id");
                $.ajax({
                    type : "GET",
                    url : "/file/"+id,
                    //data : "name=" + name + "&age=" + age,
                    success : function(data) {
                        if(data.msg == "success"){
                            $("#edit_div").append("<textarea id='edit_textarea'>" + data.body + "</textarea>" +
                                "<button id='" + id + "' class='save'>SAVE</button>");
                        }
                    }
                });
            });

            $("#upload").on('submit', function(e){
                e.preventDefault();
                var form = e.target;
                console.log(form);
                var data = new FormData(form);
                console.log(data);
                $.ajax({
                    type : "POST",
                    url : "/file",
                    contentType: false,
                    data: data,
                    processData: false,
                    success : function(response) {
                        if(response.msg == "success"){
                            console.log(response);
                            $("#file_table").append("<tr><td>" + response.body.id + "</td><td>" + response.body.fileName + "</td><td><button id='" + response.body.id +
                                "' class='edit'>EDIT</button><button id='" + response.body.id + "' class='delete'>DELETE</button></td></tr>");
                        }
                    }

                });
            });

        });
    </script>
</head>
<body>
<form id="upload" enctype="multipart/form-data">
    <input style="float: right" type="file" name="file">
    <button style="float: right">ADD + </button>
</form>

<table id="file_table">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Actions</th>
    </tr>
<c:if test="${not empty files}">
    <c:forEach items="${files}" var="file">
        <tr>
            <td>${file.id}</td>
            <td>${file.fileName}</td>
            <td><button id="${file.id}" class="edit">EDIT</button><button id="${file.id}" class="delete">DELETE</button></td>
        </tr>
    </c:forEach>
</c:if>
</table>
<div id="edit_div"></div>
</body>


</html>