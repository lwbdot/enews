


;(chechModifyPasswordFormData = function () {
	var password = document.getElementById("password").value;
    var password2 = document.getElementById("password2").value;
    if((password=="")||(password.length < 6)){
        alert("Please input password, at least 6 characters!");
        return false;
    }
    if((password2=="")||(password2 != password)){
        alert("The passwords you input twice are different!");
        return false;
    }
    return true;
});

;(chechModifyUserInfoFormData = function () {
	 var userEmail = document.getElementById("userEmail").value;
	 if (userEmail=="") {
	     alert("Your email must be specified!");
	     return false;
	 }
	 return true;
});

