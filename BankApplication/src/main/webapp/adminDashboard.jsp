<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>Admin Dash Board</h1>
<h2>Welcome ${empName}</h2>

<ul>
<li><a href="/BankApplication/createAccount">create Account</a></li>
<li><a href="/BankApplication/creditAmount">credit amount</a></li>
<li><a href="/BankApplication/debitAmount">debit amount</a></li>
<li><a href="/BankApplication/transferAmount">transfer amount</a></li>
<li><a href="/BankApplication/viewAccount">view account</a></li>
</ul>
<button onclick="window.location.href='adminLogout'">Logout</button>

</body>
</html>