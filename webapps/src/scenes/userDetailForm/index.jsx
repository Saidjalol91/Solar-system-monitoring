import { Box, Button } from "@mui/material";
import { Formik } from "formik";
import * as yup from "yup";
import useMediaQuery from "@mui/material/useMediaQuery";
import Header from "../../components/Header";
import PopupComp from "../../components/Popup/PopupComp";
import axios from "axios";
import React, { useState, useEffect } from "react";
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
} from "@mui/material";

const UserDetailForm = () => {
  const [data, setData] = useState([]);

  const [formData, setFormData] = useState({
    phoneNum: "",
    password: "",
    password_retype: "",
  });

  const [showPopup, setShowPopup] = useState(false);

  function createData(name, calories) {
    return { name, calories };
  }

  const rows = [
    createData("이름", "싸이드차롤"),
    createData("아니디", "AATEST"),
    createData("사용자 그룹", "Administartor"),
    createData("담당 프로젝트", "마을태양광"),
  ];

  useEffect(() => {
    const fetchData = async () => {
      await fetch(`http://localhost:8081/api/auth/currentUser`)
        .then((res) => res.json())
        .then((result) => {
          setData(result);
          console.log(result);
        });
    };

    fetchData();
  }, []);

  const handleFormSubmit = () => {
    // shu yerda axios library ishlatib submit qilinadi

    if (formData.length > 0) {
      axios
        .post("http://localhost:8081/api/auth/passchange", formData)
        .then((res) => {
          const response = JSON.parse(res);
        });
    }
    console.log("Form Data of to send server : ", formData);
    setShowPopup(false);
  };

  const closePopup = () => {
    setShowPopup(false);
  };

  const openPopup = () => {
    setShowPopup(true);
  };

  const handleInputChange = (name, value) => {
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  return (
    <Box m="20px">
      <Header title="My Page" subtitle="User detail Information" />
      <>
        <TableContainer component={Paper}>
          <Table sx={{ minWidth: 650 }} aria-label="simple table">
            <colgroup>
              <col style={{ width: "25%" }} />
              <col style={{ width: "75%" }} />
            </colgroup>
            <TableBody>
              {rows.map((row) => (
                <TableRow
                  key={row.name}
                  sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
                >
                  <TableCell
                    component="th"
                    scope="row"
                    sx={{
                      backgroundColor: "grey",
                      fontWeight: "500",
                      padding: "8px 10px",
                      border: "1px solid #eaeaea",
                      verticalAlign: "middle",
                      fontFamily: "Bold",
                      fontSize: "16px",
                      color: "black",
                      width: "25%",
                      textAlign: "center",
                    }}
                  >
                    {row.name}
                  </TableCell>
                  <TableCell
                    sx={{
                      backgroundColor: "white",
                      fontFamily: "Bold",
                      fontSize: "16px",
                      color: "black",
                    }}
                    align="left"
                  >
                    {row.calories}
                  </TableCell>
                </TableRow>
              ))}
              <TableRow>
                <TableCell
                  component="th"
                  scope="row"
                  sx={{
                    backgroundColor: "grey",
                    fontFamily: "Bold",
                    fontSize: "16px",
                    color: "black",
                    width: "25%",
                    fontWeight: "500",
                    padding: "8px 10px",
                    border: "1px solid #eaeaea",
                    verticalAlign: "middle",
                    textAlign: "center",
                  }}
                >
                  비밀번호변경
                </TableCell>

                <TableCell
                  sx={{
                    backgroundColor: "white",
                    fontFamily: "Bold",
                    fontSize: "16px",
                    color: "black",
                  }}
                >
                  <button onClick={openPopup}>
                    비밀번호 변경
                    {showPopup                   
                    && (
                      <PopupComp
                        onFormSubmit={handleFormSubmit}
                        onInputChange={handleInputChange}
                        onClose={closePopup}
                      />
                    )}
                  </button>
                </TableCell>
              </TableRow>
              <TableRow>
                <TableCell
                  component="th"
                  scope="row"
                  sx={{
                    backgroundColor: "grey",
                    fontFamily: "Bold",
                    fontSize: "16px",
                    color: "black",
                    width: "25%",
                    textAlign: "center",
                    fontWeight: "500",
                    padding: "8px 10px",
                    border: "1px solid #eaeaea",
                    verticalAlign: "middle",
                  }}
                >
                  휴대폰번호
                </TableCell>

                <TableCell
                  sx={{
                    backgroundColor: "white",
                    fontFamily: "Bold",
                    fontSize: "16px",
                    color: "black",
                  }}
                >
                  <input
                    type="text"
                    value="010-7878-8989"
                    className="name"
                    name="name"
                    sx={{
                      backgroundColor: "grey",
                      color: "black",
                      fontFamily: "Bold",
                      fontSize: "16px",
                      padding: "10px",
                    }}
                  />
                </TableCell>
              </TableRow>
            </TableBody>
          </Table>
        </TableContainer>
      </>
      <div
        style={{
          display: "flex",
          justifyContent: "center",
          margin: "20px",
          padding: "10px",
        }}
      >
        <Button
          style={{
            backgroundColor: "green",
            margin: "10px",
            fontSize: "18px",
            width: "150px",
          }}
        >
          저장
        </Button>
        <Button
          style={{
            backgroundColor: "red",
            margin: "10px",
            fontSize: "18px",
            width: "150px",
          }}
        >
          닫기
        </Button>
      </div>
    </Box>
  );
};

export default UserDetailForm;
