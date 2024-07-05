import { Box, useTheme } from "@mui/material";
import { DataGrid, GridToolbar } from "@mui/x-data-grid";
import { tokens } from "../../theme";
import Header from "../../components/Header";
import axios from "axios";
import React, {useEffect, useState} from "react";
import { Alarm } from "@mui/icons-material";

const baseUrl = "http://localhost:8081/api/auth/getUsers"

const Invoices = () => {
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);
  const [post, setPost] = useState(null);

  useEffect(() => {
    axios.get(baseUrl).then((response) => {
      setPost(response.data);
    }).catch((error) => {Alarm(error);});
      
  }, []);

  if (!post) { return null;}

  const columns = [
    { field: "id", headerName: "ID" },
    {
      field: "firstName",
      headerName: "First Name",
      flex: 1,
      cellClassName: "name-column--cell",
    },
    {
      field: "lastName",
      headerName: "Last Name",
      flex: 1,
      cellClassName: "name-column--cell",
    },
    {
      field: "email",
      headerName: "Email",
      flex: 1,
    },
    {
      field: "contact",
      headerName: "Phone Number",
      flex: 1,
    },
    {
      field: "address",
      headerName: "지번주소",
      flex: 1,
    },
    {
      field: "roadAddress",
      headerName: "도로주소",
      flex: 1,
    },
  ];

  return (
    <Box m="20px">
      <Header title="사용자 명단" subtitle="List of User" />
      <Box
        m="40px 0 0 0"
        height="75vh"
        sx={{
          "& .MuiDataGrid-root": {
            border: "none",
          },
          "& .MuiDataGrid-cell": {
            borderBottom: "none",
          },
          "& .name-column--cell": {
            color: colors.greenAccent[300],
          },
          "& .MuiDataGrid-columnHeaders": {
            backgroundColor: colors.blueAccent[700],
            borderBottom: "none",
          },
          "& .MuiDataGrid-virtualScroller": {
            backgroundColor: colors.primary[400],
          },
          "& .MuiDataGrid-footerContainer": {
            borderTop: "none",
            backgroundColor: colors.blueAccent[700],
          },
          "& .MuiCheckbox-root": {
            color: `${colors.greenAccent[200]} !important`,
          },
        }}
      >
        <DataGrid 
            checkboxSelection 
            rows={post.record} 
            columns={columns} 
            components={{ Toolbar: GridToolbar }} />
      </Box>
    </Box>
  );
};

export default Invoices;
