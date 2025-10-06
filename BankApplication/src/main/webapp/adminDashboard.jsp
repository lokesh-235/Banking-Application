<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>
<style>
body {
  margin: 0;
  padding: 0;
  font-family: Arial, sans-serif;
  background: black;
  color: white;
  display: flex;
  flex-direction: column;
  align-items: center;
  min-height: 100vh;
}

h1 {
  margin-top: 30px;
  color: cyan;
  text-shadow: 0 0 15px cyan;
}

h2 {
  color: #ddd;
  margin-bottom: 30px;
}

/* ===== Navigation Menu ===== */
ul {
  list-style: none;
  padding: 0;
  margin: 20px 0;
  display: flex;
  flex-direction: column;
  gap: 15px;
  width: 250px;
}

ul li {
  text-align: center;
}

ul li a {
  display: block;
  text-decoration: none;
  color: white;
  font-size: 18px;
  border: 2px solid cyan;
  border-radius: 10px;
  padding: 10px;
  transition: 0.3s;
  box-shadow: 0 0 10px cyan;
}

ul li a:hover {
  background: cyan;
  color: black;
  box-shadow: 0 0 20px cyan;
}

/* ===== Logout Button ===== */
button {
  margin-top: 30px;
  width: 200px;
  background: cyan;
  color: black;
  font-size: 18px;
  font-weight: bold;
  border: none;
  border-radius: 20px;
  padding: 12px;
  cursor: pointer;
  transition: 0.3s;
  box-shadow: 0 0 5px cyan;
}

button:hover {
  background: none;
  color: white;
  border: 2px solid cyan;
}
</style>
</head>
<body>

<h1>Admin Dashboard</h1>
<h2>Welcome ${empName}</h2>

<ul>
  <li><a href="/BankApplication/createAccount">Create Account</a></li>
  <li><a href="/BankApplication/creditAmount">Credit Amount</a></li>
  <li><a href="/BankApplication/debitAmount">Debit Amount</a></li>
  <li><a href="/BankApplication/transferAmount">Transfer Amount</a></li>
  <li><a href="/BankApplication/viewAccount">View Account</a></li>
</ul>

<button onclick="window.location.href='adminLogout'">Logout</button>

</body>
</html>
