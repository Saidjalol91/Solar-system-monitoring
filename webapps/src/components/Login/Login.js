import React, { useState, useEffect } from "react";
import basestyle from "../Base.module.css";
import loginstyle from "./Login.module.css";
import axios from "axios";
import { useNavigate, NavLink } from "react-router-dom";
import { Button } from "@mui/material";
const Login = ({onLogin}) => {
  const navigate = useNavigate();
  const [formErrors, setFormErrors] = useState({});
  const [isSubmit, setIsSubmit] = useState(false);
  const [dataToSend, setDataToSend] = useState();
  const [authenticated, setAuthenticated] = useState(localStorage.getItem(localStorage.getItem("authenticated") || false));
  const [username, setUserDetails] = useState({
    username: "",
    password: "",
  });

  const changeHandler = (e) => {
    const { name, value } = e.target;
    setUserDetails({
      ...username,
      [name]: value,
    });
  };
  const validateForm = (values) => {
    const error = {};
   
    if (!values.username) {
      error.username = "username is required";
    }
  
    if (!values.password) {
      error.password = "Password is required";
    }
    return error;
  };

  const loginHandler = (e) => {
    e.preventDefault();
    setFormErrors(validateForm(username));
    setIsSubmit(true);
    console.log("formError value", formErrors);
    console .log("USer value ", username);
    console.log("Submit value", isSubmit);  
  };

  useEffect(() => {
    if (Object.keys(formErrors).length === 0 && isSubmit) {
      console.log(username);
      axios.post("http://localhost:8081/api/auth/login", username).then((res) => {
        const response = JSON.stringify(res);
        onLogin(response);
        navigate("/dash", { replace: true });
      });
    }
  }, [formErrors, onLogin]);

  return (
    <div className={loginstyle.body}>
      <div className={loginstyle.login}>
        <form>
          <h1>태양광 모니터링</h1>
          <input
            name="username"
            id="username"
            placeholder="user"
            onChange={changeHandler}
            value={username.username}
          />
          <p className={basestyle.error}>{formErrors.username}</p>
          <input
            type="password"
            name="password"
            id="password"
            placeholder="Password"
            onChange={changeHandler}
            value={username.password}
          />
          <p className={basestyle.error}>{formErrors.password}</p>
          <button className={basestyle.button_common} onClick={loginHandler}>
            Login
          </button>
        </form>
        <div style={{ position: "flex" }}>
          <Button>
            <NavLink to="/findpass">Find you password </NavLink>
          </Button>
          <Button>
            <NavLink to="/signup"> Register Now</NavLink>
          </Button>
        </div>
      </div>
    </div>
  );
};
export default Login;
