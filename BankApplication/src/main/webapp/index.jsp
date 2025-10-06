<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome - Andhra Bank</title>

<style>
/* ===== Global ===== */
body {
  margin: 0;
  padding: 0;
  font-family: Arial, sans-serif;
  background: black;
  color: white;
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

/* ===== Navbar ===== */
nav {
  width: 100%;
  background: #111;
  border-bottom: 2px solid cyan;
  box-shadow: 0 0 10px cyan;
  position: sticky;
  top: 0;
  z-index: 100;
}

nav ul {
  list-style: none;
  display: flex;
  justify-content: flex-end;
  margin: 0;
  padding: 15px 30px;
}

nav ul li {
  margin-left: 30px;
}

nav ul li a {
  text-decoration: none;
  color: white;
  font-size: 18px;
  transition: 0.3s;
}

nav ul li a:hover {
  color: cyan;
  text-shadow: 0 0 8px cyan;
}

/* ===== Main Welcome Section ===== */
main {
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
}

main h1 {
  font-size: 3em;
  color: cyan;
  
}


footer {
  background: #111;
  color: white;
  text-align: center;
  padding: 15px;
  border-top: 2px solid cyan;
  box-shadow: 0 0 10px cyan;
}

</style>
</head>
<body>

<nav>
  <ul>
    <li><a href="#">About Us</a></li>
    <li><a href="#">Contact Us</a></li>
    <li><a href="/BankApplication/adminLogin">Admin Login</a></li>
  </ul>
</nav>

<main>
  <h1>WELCOME TO ANDHRA BANK</h1>
</main>

<footer>
  &copy; 2025 Andhra Bank | All Rights Reserved
</footer>

</body>
</html>
