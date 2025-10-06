<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Account</title>

<style>
body{
  margin:auto;
  width:90%;
  min-height:100vh;
  background:black;
  color:white;
  font-family: Verdana, sans-serif;
  padding:40px 20px;
}

h2, h3 {
  color: cyan;
  text-align: center;
}

.details {
  display:flex;
  justify-content: space-between;
  align-items: center;
  padding: 20px;
}

form{
  margin:30px auto;
  text-align:center;
  border: 2px solid cyan;
  padding: 30px;
  box-shadow:0 0 20px skyblue;
  border-radius: 20px;
  max-width: 400px;
  background: #111;
}

.input-group{
  position:relative;
  margin:20px 0;
}

.input-group input{
  width:90%;
  min-height:40px;
  background:transparent;
  color:white;
  font-size:18px;
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
  font-size:14px;
  padding:0 5px;
}

button{
  width : 70%;
  background : cyan;
  box-shadow:0 0 8px skyblue;
  border : none;
  font-size : 1.2em;
  padding : 10px;
  border-radius : 20px;
  transition : 0.3s ease;
  cursor : pointer;
  margin-top: 15px;
}

button:hover{
  background : none;
  color : white;
  border : 2px solid cyan;
}

.message{
  color:orange;
  margin-top: 10px;
}

.accountDetails, .transactions {
  margin:40px auto;
  width:90%;
  max-width:1000px;
  background:#111;
  border:2px solid cyan;
  border-radius:15px;
  padding:20px;
  box-shadow:0 0 15px skyblue;
}

.accountDetails p {
  font-size: 18px;
  margin:8px 0;
}

table {
  width:100%;
  border-collapse: collapse;
  margin-top:20px;
}

table th, table td {
  border:1px solid #444;
  padding:10px;
  text-align:center;
}

table th {
  background:cyan;
  color:black;
}

.credit-row { color:lime; }
.debit-row { color:tomato; }
.transfer-row { color:yellow; }
</style>
</head>
<body>

<div class="details">
   <h2>Welcome ${empName}</h2>
</div>

<form method="post" action="viewAccount" class="form">
  <h2>View Account</h2>
  <div class="input-group">
    <input type="text" placeholder=" " name="acno" required />
    <label>Account Number</label>
  </div>
  <button type="submit">View Account</button>
  <p class="message">${msg}</p>
</form>

<c:if test="${not empty account}">
<div class="accountDetails" id="transactionsSection">
  <h2>Account Details</h2>
  <p><b>Name:</b> ${account.name}</p>
  <p><b>Email:</b> ${account.email}</p>
  <p><b>Account Number:</b> ${account.accountNumber}</p>
  <p><b>Current Balance:</b> ₹${account.balance}</p>
</div>
</c:if>

<c:if test="${not empty transactions}">
<div class="transactions" id="transactionsSection">
  <h2>Transaction History</h2>
  <table>
    <thead>
      <tr>
        <th>ID</th>
        <th>From Account</th>
        <th>To Account</th>
        <th>Type</th>
        <th>Amount</th>
        <th>Description</th>
        <th>Date</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="t" items="${transactions}">
        <tr class="${t.transactionType eq 'credit' ? 'credit-row' : (t.transactionType eq 'debit' ? 'debit-row' : 'transfer-row')}">
          <td>${t.transactionId}</td>
          <td>${t.fromAccountNumber}</td>
          <td>${t.toAccountNumber}</td>
          <td>${t.transactionType}</td>
          <td>₹${t.amount}</td>
          <td>${t.description}</td>
          <td>${t.transactionDate}</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</div>
</c:if>

<script>
window.onload = function() {
  var transDiv = document.getElementById("transactionsSection");
  if (transDiv) {
    transDiv.scrollIntoView({ behavior: "smooth" });
  }
};
</script>

</body>
</html>
