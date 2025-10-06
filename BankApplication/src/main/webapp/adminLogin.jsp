<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Floating Labels</title>
<style>
body{
  margin:auto;
  width:90%;
  min-height:100vh;
  display:flex;
  justify-content:center;
  align-items:center;
  flex-direction:column;
  background:black;
  color:white;
}

form{
text-align : center;
border : 2px solid cyan;
padding : 30px;
box-shadow:0 0 20px skyblue;
border-radius : 20px;
}

.input-group{
  position:relative;
  margin:20px 0;
}

.input-group input{
  min-width:300px;
  min-height:30px;
  background:transparent;
  color:white;
  font-size:20px;
  padding:10px 15px;
  border:2px solid #555;
  border-radius:5px;
  outline:none;
}

.input-group input:focus{
  border-color:skyblue;
  box-shadow:0 0 8px skyblue;
}

.input-group label{
  position:absolute;
  top:50%;
  left:15px;
  transform:translateY(-50%);
  pointer-events:none;
  transition:all 0.3s ease;
  color:white;
}

.input-group input:focus+label,
.input-group input:not(:placeholder-shown)+label{
  top:-5px;
  left:10px;
  background:black;
  color:skyblue;
  font-size:16px;
  font-family: Verdana, sans-serif;
  padding:0 5px;
}

button{
width : 60%;
background : cyan;
box-shadow:0 0 8px skyblue;
border : skyblue;
font-size : 1.5em;
padding : 5px 10px 5px 10px;
border-radius : 20px;
transition : 2s ease;
cursor : pointer;
}

button:hover{
background : none;
color : white;
border : 2px solid white;
}
</style>
</head>
<body>
<form method="post" action="adminLogin">
<h2>Admin Login Form</h2>
<br>
<div class="input-group">
  <input type="text" placeholder=" " name="email" required />
  <label>user name/email</label>
</div>


<div class="input-group">
  <input type="password" placeholder=" " name="password" required />
  <label>password</label>
</div>

<button>login</button>
<br>
<p class="message">
    ${msg}
</p>


</form>


</body>
</html>


