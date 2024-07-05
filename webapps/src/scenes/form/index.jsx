import { AlertTitle, Box, Button, TextField } from "@mui/material";
import { Formik } from "formik";
import * as yup from "yup";
import useMediaQuery from "@mui/material/useMediaQuery";
import Header from "../../components/Header";
import { useState } from "react";
import DaumPostcode from "react-daum-postcode";
import Popup from "reactjs-popup";
// import Popup from "../../components/Popup/Popup";
import axios from "axios";
import React from "react";
import Alert from "@mui/material/Alert";

const Form = () => {
  const isNonMobile = useMediaQuery("(min-width:600px)");
  const [addressUser, setAddressUser] = useState("");
  const [isOpen, setIsOpen] = useState(false);

  const handleFormSubmit = (values, { resetForm }) => {
    values.address = addressUser.address;
    values.roadAddress = addressUser.jibunAddress;
    console.log(values);
    axios({
      method: "POST",
      url: "http://localhost:8081/api/auth/insertUser",
      data: values,
      headers: { "Content-Type": "multipart/form-data" },
    })
      .then(function (res) {
        // alert("Successfully user Information has updated");
        <Alert variant="outlined" severity="success">
          <AlertTitle>Success</AlertTitle>
          Successfully user Information has updated
        </Alert>;
        console.log("response data", res.data);
      })
      .catch(function (res) {
        console.log(res);
      });

    resetForm({ values: "" });
    addressUser.address = " ";
    addressUser.jibunAddress = "";
  };

  const searchHandler = () => {
    setIsOpen(!isOpen);
    if (isOpen) {
      addressUser.jibunAddress = "";
      addressUser.address = "";
    }
  };

  const handleComplete = (data) => {
    setAddressUser(data);
    console.log("data", data);
  };

  return (
    <Box m="20px">
      <Header title="CREATE USER" subtitle="Create a New User Profile" />

      <Formik
        onSubmit={handleFormSubmit}
        initialValues={initialValues}
        validationSchema={checkoutSchema}
        // onChange  ={handledChange}
      >
        {({
          values,
          errors,
          touched,
          handleBlur,
          handleChange,
          handleSubmit,
        }) => (
          <form onSubmit={handleSubmit}>
            <Box
              display="grid"
              gap="30px"
              gridTemplateColumns="repeat(4, minmax(0, 1fr))"
              sx={{
                "& > div": { gridColumn: isNonMobile ? undefined : "span 8" },
              }}
            >
              <TextField
                fullWidth
                variant="filled"
                type="text"
                label="명"
                onBlur={handleBlur}
                onChange={handleChange}
                value={values.firstName}
                name="firstName"
                error={!!touched.firstName && !!errors.firstName}
                helperText={touched.firstName && errors.firstName}
                sx={{ gridColumn: "span 2" }}
              />
              <TextField
                fullWidth
                variant="filled"
                type="text"
                label="성"
                onBlur={handleBlur}
                onChange={handleChange}
                value={values.lastName}
                name="lastName"
                error={!!touched.lastName && !!errors.lastName}
                helperText={touched.lastName && errors.lastName}
                sx={{ gridColumn: "span 2" }}
              />
              <TextField
                fullWidth
                variant="filled"
                type="text"
                label="Email"
                onBlur={handleBlur}
                onChange={handleChange}
                value={values.email}
                name="email"
                error={!!touched.email && !!errors.email}
                helperText={touched.email && errors.email}
                sx={{ gridColumn: "span 2" }}
              />
              <TextField
                fullWidth
                variant="filled"
                type="text"
                label="휴대폰"
                onBlur={handleBlur}
                onChange={handleChange}
                value={values.contact}
                name="contact"
                error={!!touched.contact && !!errors.contact}
                helperText={touched.contact && errors.contact}
                sx={{ gridColumn: "span 2" }}
              />

              <TextField
                fullWidth
                variant="filled"
                type="text"
                onBlur={handleBlur}
                onChange={handleChange}
                value={values.role}
                label="role"
                name="role"
                error={!!touched.role && !!errors.role}
                helperText={touched.role && errors.role}
                sx={{ gridColumn: "span 2" }}
              />
              <TextField
                fullWidth
                variant="filled"
                type="text"
                onBlur={handleBlur}
                onChange={handleChange}
                value={values.project}
                label="project"
                name="project"
                error={!!touched.project && !!errors.project}
                helperText={touched.project && errors.project}
                sx={{ gridColumn: "span 2" }}
              />

              <TextField
                fullWidth
                variant="filled"
                type="text"
                //label="지번"
                onBlur={handleBlur}
                onChange={handleChange}
                value={addressUser.address}
                name="address"
                error={!!touched.address && !!errors.address}
                helperText={touched.address && errors.address}
                sx={{ gridColumn: "span 2" }}
              />

              <TextField
                fullWidth
                variant="filled"
                type="text"
                //label="도로명"
                onBlur={handleBlur}
                onChange={handleChange}
                value={addressUser.jibunAddress}
                name="jibunAddress"
                error={!!touched.jibunAddress && !!errors.jibunAddress}
                helperText={touched.jibunAddress && errors.jibunAddress}
                sx={{ gridColumn: "span 2" }}
              />

              <Button
                style={{
                  background: "white",
                  color: "black",
                  fontSize: "18px",
                  fontFamily: "bold",
                }}
                onClick={searchHandler}
              >
                주소 검색
              </Button>
              {isOpen && (
                <DaumPostcode
                  onComplete={handleComplete}
                  autoClose={true} // 값을 선택할 경우 사용되는 DOM을 제거하여 자동 닫힘 설정
                />
              )}
            </Box>
            <Box display="flex" justifyContent="center" mt="20px">
              <Button type="submit" color="secondary" variant="contained">
                Create New User
              </Button>
            </Box>
          </form>
        )}
      </Formik>
    </Box>
  );
};

const phoneRegExp =
  /^((\+[1-9]{1,4}[ -]?)|(\([0-9]{2,3}\)[ -]?)|([0-9]{2,4})[ -]?)*?[0-9]{3,4}[ -]?[0-9]{3,4}$/;

const checkoutSchema = yup.object().shape({
  firstName: yup.string().required("required"),
  lastName: yup.string().required("required"),
  email: yup.string().email("invalid email").required("required"),
  contact: yup
    .string()
    .matches(phoneRegExp, "Phone number is not valid")
    .required("required"),
  role: yup.string().required("required"),
});
const initialValues = {
  firstName: "",
  lastName: "",
  email: "",
  contact: "",
  role: "",
  project: "",
};

export default Form;
