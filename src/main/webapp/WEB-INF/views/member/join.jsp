<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>회원 가입</title>
</head>
<body>
    <h1>회원 가입</h1>
    <form method='post' action="/member/join">
        <div>
            <label>Login ID:</label>
            <input type="text" name="loginId" required>
        </div>
        <div>
            <label>Name:</label>
            <input type="text" name="name" required>
        </div>
        <div>
            <label >Password:</label>
            <input type="password" name="password" required>
        </div>
        <div>
            <label>Gender:</label>
            <select name="gender" required>
                <option value="M">M</option>
                <option value="W">W</option>
            </select>
        </div>
        <div>
            <label>Birthdate:</label>
            <input type="date" name="birthdate" required>
        </div>
        <div>
            <label>City ID:</label>
            <input type="number" name="cityId" required>
        </div>
        <div>
            <label>Prefer Branch ID:</label>
            <input type="number" name="preferBranchId" required>
        </div>
        <div>
            <input type="submit" value="회원 가입">
        </div>
        
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    </form>
</body>
</html>
