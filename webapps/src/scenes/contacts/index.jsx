import { Box } from "@mui/material";
import { DataGrid, GridToolbar } from "@mui/x-data-grid";
import { tokens } from "../../theme";
import Header from "../../components/Header";
import { useTheme } from "@mui/material";
import axios from "axios";
import React from "react";

const baseUrl = "http://localhost:8081/api/auth/getListAllData"

const Contacts = () => {
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);

  const [post, setPost] = React.useState(null);

  React.useEffect(() =>{
    axios.get(baseUrl).then((response) => {
      setPost(response.data);
  });},[]);  

  if (!post) { return null;}

  const columns = [
    { field: "id", headerName: "ID", flex: 0.05 },
    // { field: "ID", headerName: "ID" },
    {
      field: "imei",
      headerName: "IMEI",
      flex: 2.2,
      type:"string",
      cellClassName: "name-column--cell",
    },
    {
      field: "currentDate",
      headerName: "날씨",
      type: "string",
      flex:1.4,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "currentTime",
      headerName: "시간",
      flex: 1.2,
    },
    // {
    //   field: "inverter_Id",
    //   headerName: "IVT_ID",
    //   flex: 1,
    // },
    // {
    //   field: "one_vlt_PV",
    //   headerName: "Volt",
    //   flex: 1,
    // },
    // {
    //   field: "one_cur_PV",
    //   headerName: "three_A",
    //   flex: 1,
    // },
    // {
    //   field: "one_PV_Out",
    //   headerName: "PV_Out",
    //   flex: 1,
    // },
    // {
    //   field: "one_sys_V",
    //   headerName: "Sys_V",
    //   flex: 1,
    // },
    // {
    //   field: "one_sys_Cur",
    //   headerName: "Sys_Cur",
    //   flex: 1,
    // },
    // {
    //   field: "one_Cur_Out",
    //   headerName: "Cur_Out",
    //   flex: 1,
    // },
    // {
    //   field: "one_Pw_Fc",
    //   headerName: "Pw_Fc",
    //   flex: 1,
    // },
    {
      field: "one_Frq",
      headerName: "Frq",
      flex: 1,
    },
    {
      field: "ttl_Gnr_Pwr",
      headerName: "Total_PW",
      flex: 1.5,
    },
    {
      field: "fail_Sts",
      headerName: "F_ST",
      flex: 1,
    },
    // {
    //   field: "three_vlt_PV",
    //   headerName: "삼상_PV",
    //   flex: 1,
    // },
    // {
    //   field: "three_cur_PV",
    //   headerName: "통신 상태",
    //   flex: 1,
    // },
    // {
    //   field: "three_out_PV",
    //   headerName: "통신 상태",
    //   flex: 1,
    // },
    // {
    //   field: "three_R_V",
    //   headerName: "통신 상태",
    //   flex: 1,
    // },
    // {
    //   field: "three_S_V",
    //   headerName: "통신 상태",
    //   flex: 1,
    // },
    // {
    //   field: "three_T_V",
    //   headerName: "통신 상태",
    //   flex: 1,
    // },
    // {
    //   field: "three_R_C",
    //   headerName: "통신 상태",
    //   flex: 1,
    // },
    // {
    //   field: "three_T_C",
    //   headerName: "통신 상태",
    //   flex: 1,
    // },
    // {
    //   field: "three_S_C",
    //   headerName: "통신 상태",
    //   flex: 1,
    // },
    // {
    //   field: "three_Cur_Out",
    //   headerName: "통신 상태",
    //   flex: 1,
    // },
    {
      field: "three_Pw_Fc",
      headerName: "통신 상태",
      flex: 1,
    }
  ];

  return (
    <Box m="20px">
      <Header
        title="RTU-105"
        subtitle="Recieving Data"
      />
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
          "& .MuiDataGrid-toolbarContainer .MuiButton-text": {
            color: `${colors.grey[100]} !important`,
          },
        }}
      >
        <DataGrid
          rows={post.myRecord}
          columns={columns}
          components={{ Toolbar: GridToolbar }}
          
        />
      </Box>
    </Box>
  );
};

export default Contacts;
