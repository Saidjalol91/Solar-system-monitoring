import React, { useEffect, useState } from "react";
import basestyle from "../Base.module.css";
import registerstyle from "./Register.module.css";
import axios from "axios";

import { useNavigate, NavLink } from "react-router-dom";
const Register = () => {
  const navigate = useNavigate();

  const [formErrors, setFormErrors] = useState({});
  const [isSubmit, setIsSubmit] = useState(false);
  const [user, setUserDetails] = useState({
    username: "",
    email: "",
    password: "",
    confirm_passwd: "",
  });

  const changeHandler = (e) => {
    const { name, value } = e.target;
    setUserDetails({
      ...user,
      [name]: value,
    });
  };

  const validateForm = (values) => {
    const error = {};
    const regex = /^[^\s+@]+@[^\s@]+\.[^\s@]{2,}$/i;
    if (!values.username) {
      error.username = "The user ID is required";
    }
    if (!values.email) {
      error.email = "Email is required";
    } else if (!regex.test(values.email)) {
      error.email = "This is not a valid email format!";
    }
    if (!values.password) {
      error.password = "Password is required";
    } else if (values.password.length < 4) {
      error.password = "Password must be more than 4 characters";
    } else if (values.password.length > 10) {
      error.password = "Password cannot exceed more than 10 characters";
    }
    if (!values.confirm_passwd) {
      error.confirm_passwd = "Confirm Password is required";
    } else if (values.confirm_passwd !== values.password) {
      error.confirm_passwd = "Confirm password and password should be same";
    }
    return error;
  };
  const signupHandler = (e) => {
    e.preventDefault();

    setFormErrors(validateForm(user));

    console.log("what is object jey value " + formErrors);

    if (!formErrors) {
      setIsSubmit(true);
    }

   
      console.log("something entered here");
      axios.post("http://localhost:8081/api/auth/register", user).then((res) => {
          console.log("what is reso[ponse data from server " + JSON.stringify(res));
          JSON.stringify(res);
          console.log("what is status code " + res.status);
          if(res.status == 200){
             alert("new user register");
             navigate("/login", { replace: true });
         }});
    



    // // console.log("next step" + isSubmit);

    // //     if (!formErrors) {
    // //         setIsSubmit(true);
    // //         axios.post("http://localhost:8081/api/auth/register", user).then((res) => {
    // //         console.log("what is reso[ponse data from server " + JSON.stringify(res));
    // //         JSON.stringify(res);
    // //         console.log("what is status code " + res.status);
    // //         if(res.status == 200){
    // //            alert("new user register");
    // //            navigate("/login", { replace: true });
    // //     }else{
    // //       alert("Something went wrong")
    // //     }
        
    // //   });


    //   if (Object.keys(formErrors).length === 0 && isSubmit) {
    //     //     console.log(user);
    //     //     axios.post("http://localhost:8081/api/auth/register", user).then((res) => {
    //     //       alert(res.data.message);
    //     //       navigate("/login", { replace: true });

    //      axios.post("http://localhost:8081/api/auth/register", user).then((res) => {
    //       console.log("what is reso[ponse data from server " + JSON.stringify(res));
    //       JSON.stringify(res);
    //       console.log("what is status code " + res.status);
    //       if(res.status == 200){
    //          alert("new user register");
    //          navigate("/login", { replace: true });
    //      }}


    //   }
    

    

    // setIsSubmit(true);
    // if (!formErrors) {
    //   setIsSubmit(true);
    // }
  };

  useEffect(() => {
    if (Object.keys(formErrors).length === 0 && isSubmit) {
      // console.log(user);
      // axios.post("http://localhost:8081/api/auth/register", user).then((res) => {
      //   alert(res.data.message);
      //   navigate("/login", { replace: true });
      // });

      axios.post("http://localhost:8081/api/auth/register", user).then((res) => {
          console.log("what is reso[ponse data from server " + JSON.stringify(res));
          JSON.stringify(res);
          console.log("what is status code " + res.status);
          if(res.status == 200){
             alert("new user register");
             navigate("/login", { replace: true });
         }});
    }
  }, [formErrors]);
  return (
    <>
      <div className={registerstyle.register}>
        <form>
          <h1>Create your account</h1>
          <input
            type="text"
            name="username"
            id="username"
            placeholder="User ID"
            onChange={changeHandler}
            value={user.username}
          />
          <p className={basestyle.error}>{formErrors.name}</p>
          <input
            type="email"
            name="email"
            id="email"
            placeholder="Email"
            onChange={changeHandler}
            value={user.email}
          />
          <p className={basestyle.error}>{formErrors.email}</p>
          <input
            type="password"
            name="password"
            id="password"
            placeholder="Password"
            onChange={changeHandler}
            value={user.password}
          />
          <p className={basestyle.error}>{formErrors.password}</p>
          <input
            type="password"
            name="confirm_passwd"
            id="confirm_passwd"
            placeholder="Confirm Password"
            onChange={changeHandler}
            value={user.confirm_passwd}
          />
          <p className={basestyle.error}>{formErrors.cpassword}</p>
          <button className={basestyle.button_common} onClick={signupHandler}>
            Register
          </button>
        </form>
        <NavLink to="/login">Already registered? Login</NavLink>
      </div>
    </>
  );
};
export default Register;
