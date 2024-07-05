import React, { useState, useEffect } from "react";
import basestyle from "../Base.module.css";
import loginstyle from "./FindUser.module.css";
import axios from "axios";
import { useNavigate, NavLink } from "react-router-dom";
import { Button } from "@mui/material";
const FindUser = () => {
  const navigate = useNavigate();
  const [formErrors, setFormErrors] = useState({});
  const [isSubmit, setIsSubmit] = useState(false);
  const [user, setUserDetails] = useState({
    user: "",
    password: "",
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
    //const regex = /^[^\s+@]+@[^\s@]+\.[^\s@]{2,}$/i;
    if (!values.user) {
      error.user = "user Id is required";
    }
    // else if (!regex.test(values.user)) {
    //   error.user = "Please enter a valid user address";
    // }
    if (!values.password) {
      error.password = "Password is required";
    }
    return error;
  };
  return (
    <>
      <div className={loginstyle.find}>
        <form>
          <h1>Find user</h1>
          <input
            name="user"
            id="user"
            placeholder="user"
            //value={user.user}
          />
          <p className={basestyle.error}>{formErrors.user}</p>
          <input
            type="password"
            name="password"
            id="password"
            placeholder="Password"
            //value={user.password}
          />
          <p className={basestyle.error}>{formErrors.password}</p>
          <button className={basestyle.button_common}>
            Find User ID
          </button>
        </form>
        <NavLink to="/login">Go back Login</NavLink>
      </div>
    </>
  );
};
export default FindUser;
